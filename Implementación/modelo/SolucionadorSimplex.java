package modelo;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Yordan Jiménez
 */
public class SolucionadorSimplex extends AbstractSolucionadorSimplex {

    public SolucionadorSimplex() {
    }

    @Override
    public ArrayList<DtoSimplex> solucionar(DtoSimplex dto) {
        boolean continuar = dto.esAcotado() && dto.esFactible();
        ArrayList<DtoSimplex> resultado = new ArrayList<>();
        resultado.add(dto);
        imprimir(dto);
        while (continuar) {
            dto = dto.clonarProfundo();
            dto = siguientePaso(dto);
            imprimir(dto);
            System.out.println(dto.getSolucion());
            continuar = !dto.esFinalizado();
            continuar &= dto.esAcotado() && dto.esFactible();
            resultado.add(dto);
        }
        return resultado;
    }

    @Override
    public DtoSimplex siguientePaso(DtoSimplex dto) {
        if (dto.esDosfases()) {
            return siguientePasoDosFases(dto);
        } else {
            return siguientePasoSimplex(dto);
        }
    }

    @Override
    public DtoSimplex completarProblema(DtoSimplex dto) {
        ArrayList<Integer> holgurasPositivas = new ArrayList<>();
        ArrayList<Integer> holgurasNegativas = new ArrayList<>();
        ArrayList<Integer> artificiales = new ArrayList<>();
        AbstractFraccion ladoDerecho;
        AbstractFraccion cero = new Fraccion(0);
        AbstractFraccion[][] matriz = dto.getMatriz();
        int[] restricciones = dto.getListaDesigualdades();
        if (dto.esMaximización()) {
            matriz[0] = negarCoeficientes(matriz[0]);
            dto.setNombreFilas(crearNombreFila(matriz.length, "z"));
        } else {
            dto.setNombreFilas(crearNombreFila(matriz.length, "-z"));
        }
        for (int contador = 1; contador < matriz.length; contador++) {
            AbstractFraccion[] fila = matriz[contador];
            ladoDerecho = fila[fila.length - 1];
            int restriccion = restricciones[contador - 1];
            switch (restriccion) {
                case 5://mayor igual  >=
                    if (ladoDerecho.menorQue(cero)) {
                        fila = negarCoeficientes(fila);
                        holgurasPositivas.add(contador);
                    } else {
                        holgurasNegativas.add(contador);
                        artificiales.add(contador);
                    }
                    break;
                case 6://menor igual <=
                    if (ladoDerecho.menorQue(cero)) {
                        fila = negarCoeficientes(fila);
                        holgurasNegativas.add(contador);
                        artificiales.add(contador);
                    } else {
                        holgurasPositivas.add(contador);
                    }
                    break;
                case 7:// igual
                    if (ladoDerecho.menorQue(cero)) {
                        fila = negarCoeficientes(fila);
                    }
                    artificiales.add(contador);
                    break;
            }
            matriz[contador] = fila;
        }
        int nuevasColumnas = holgurasNegativas.size() + holgurasPositivas.size()
                + artificiales.size();
        matriz = agregarColumnas(matriz, nuevasColumnas);
        matriz = agregarUnos(matriz, holgurasPositivas, true, dto.getVariablesBasicas());
        matriz = agregarUnos(matriz, holgurasNegativas, false,
                dto.getVariablesBasicas() + holgurasPositivas.size());
        dto.setVariablesHolgura(holgurasPositivas.size() + holgurasNegativas.size());
        matriz = agregarUnos(matriz, artificiales, true, dto.getVariablesBasicas()
                + dto.getVariablesHolgura());

        String[] nombreColumnas = dto.getNombreColumnas();
        nombreColumnas = agregarNombreVariables(nombreColumnas,
                holgurasNegativas.size() + holgurasPositivas.size(), "s");
        nombreColumnas = agregarNombreVariables(nombreColumnas,
                artificiales.size(), "a");

        String[] nombreFilas = dto.getNombreFilas();
        nombreFilas = agregarNombresFila(nombreFilas, holgurasPositivas,
                "s", dto.getVariablesBasicas());
        nombreFilas = agregarNombresFila(nombreFilas, artificiales,
                "a", dto.getVariablesBasicas() + holgurasPositivas.size()
                + holgurasNegativas.size());
        dto.setNombreFilas(nombreFilas);

        dto.setNombreColumnas(nombreColumnas);

        if (artificiales.size() > 0) {
            matriz = convertirDosFases(matriz, dto.getVariablesBasicas() + dto.getVariablesHolgura());
            dto.setDosfases(true);
            dto.setBloqueoDosFases(true);
            dto.setArtificialActual(dto.getVariablesHolgura()
                    + dto.getVariablesBasicas() - 1);
            nombreFilas = agregarNombreW(nombreFilas);
            dto.setNombreFilas(nombreFilas);
            dto.setCoordenadaPivote(new Point(artificiales.get(0)+1, dto.getVariablesHolgura()
                    + dto.getVariablesBasicas() - 1));
            dto.setOperaciones(siguientesOperacionesInicioDosfases(artificiales.get(0)));
            dto.setMensaje("Se han añadido la fila -w conjunto a las variables de holgura 's' y artificiales 'a', generando"
                    + "un problema de dos fases.");
        } else {
            dto.setCoordenadaPivote(siguientePivoteo(dto));
            dto = siguientesOperaciones(dto);
            dto.setMensaje("Se han añadido las variables de holgura 's' al problema original." );
        }
        dto.setMatriz(matriz);
        return dto;
    }

    @Override
    public String[] calcularRadio(DtoSimplex dto, int columna) {
        AbstractFraccion[] radios = obtenerRadios(dto.getMatriz(), columna);
        String[] resultado = new String[radios.length];
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = radios[i].toString(dto.esFormatoFraccional());
            
        }
        return resultado;
    }

    @Override
    public DtoSimplex siguientesOperaciones(DtoSimplex dto) {
        AbstractFraccion[][] matriz = dto.getMatriz();
        Point siguientePivote = dto.getCoordenadaPivote();
        AbstractFraccion coeficiente = matriz[siguientePivote.y][siguientePivote.x].clonar();
        String[] operaciones = new String[matriz.length];
        int indice1;
        if (dto.esDosfases()) {
            indice1 = siguientePivote.y - 1;
            operaciones[0] = generarOperacion(coeficiente, indice1, dto.esFormatoFraccional());
            indice1++;
        } else {
            indice1 = siguientePivote.y;
            operaciones[0] = generarOperacion(coeficiente, indice1, dto.esFormatoFraccional());
        }
        AbstractFraccion[] fila;
        int indiceOperacion;
        for (int contador = 0; contador < matriz.length; contador++) {
            if (contador < siguientePivote.y) {
                indiceOperacion = contador + 1;
                fila = matriz[contador];
            } else if (contador > siguientePivote.y) {
                indiceOperacion = contador;
                fila = matriz[contador];
            } else {
                continue;
            }
            coeficiente = fila[siguientePivote.x].clonar();
            coeficiente.hacerNegativa();
            operaciones[indiceOperacion] = generarOperacion(coeficiente,
                    indice1, contador, dto.esFormatoFraccional(),
                    dto.esDosfases());
        }
        dto.setOperaciones(operaciones);
        return dto;
    }

    /**
     * Crea un string que simboliza la operación fila a realizar según los datos
     * ingresados.
     *
     * @param coeficiente coeficiente del primer valor de la operación de fila.
     * @param fila1 Número de fila del primer valor de la operación de fila.
     * @param fila2 Número de fila del segundo valor de la operación de fila.
     * @param fraccional Indica el formato de salida del coeficiente.
     *
     * @return String que indica la operación de fila.
     */
    private String generarOperacion(AbstractFraccion coeficiente, int fila1,
            int fila2, boolean fraccional, boolean dosfases) {
        String resultado = coeficiente.toString(fraccional);
        if (dosfases) {
            resultado += " * F" + (fila1 - 1);
            if (fila2 == 0) {
                resultado += " + F0'";
                resultado += " -> F0'";
            } else {
                fila2--;
                resultado += " + F" + fila2;
                resultado += " -> F" + fila2;

            }
        } else {
            resultado += " * F" + fila1;
            resultado += " + F" + fila2;
            resultado += " -> F" + fila2;
        }
        return resultado;
    }

    /**
     * Crea un string que simboliza la operación fila a realizar según los datos
     * ingresados, se opera la misma fila.
     *
     * @param coeficiente coeficiente del primer valor de la operación de fila.
     * @param fila1 Número de fila del primer valor de la operación de fila.
     * @param fraccional Indica el formato de salida del coeficiente.
     * @return String que indica la operación de fila.
     *
     */
    private String generarOperacion(AbstractFraccion coeficiente, int fila1,
            boolean fraccional) {
        AbstractFraccion cero = new Fraccion(0);
        String resultado;
        if (coeficiente.iguales(cero)) {
            resultado = "F" + fila1 + " + 1 -> F" + fila1;
        } else {
            coeficiente = coeficiente.obtenerInverso();
            resultado = coeficiente.toString(fraccional);
            resultado += " * F" + fila1;
            resultado += " -> F" + fila1;
        }
        return resultado;
    }

    /**
     * Obtiene el índice del valor menor que se encuentre dentro de la lista de
     * fracciones.
     *
     * @param valores Arreglo con las fracciones donde se va a buscar el valor
     * menor.
     * @return Valor entro que representa el índice del valor menor del arreglo.
     */
    private int obtenerIndiceDelValorMenor(AbstractFraccion[] valores, int indiceIncio,
            int acotoFinal) {
        AbstractFraccion valorMenor = valores[indiceIncio];
        AbstractFraccion valor;
        int indice = indiceIncio;
        for (; indiceIncio < valores.length - acotoFinal; indiceIncio++) {
            valor = valores[indiceIncio];
            if (valor.menorQue(valorMenor)) {
                valorMenor = valor;
                indice = indiceIncio;
            }
        }
        return indice;
    }

    /**
     * * Aplica la división entre el lado derecho de un problema con una
     * columna, la operación se realiza dividiendo posiciones correspondientes
     * entre los índices de las listas.
     *
     * @param fracciones Valores de las filas y columnas que representan a un
     * problema de programación lineal.
     * @param columna Indice de la columna qcon la cual se generará los radio.
     * @return Arreglo de valores que representan los radios.
     */
    private AbstractFraccion[] obtenerRadios(AbstractFraccion[][] fracciones,
            int columna) {
        AbstractFraccion[] resultado = new Fraccion[fracciones.length];
        AbstractFraccion elementoLadoDerecho;
        AbstractFraccion elementoColumna;
        int ladoDerecho = fracciones[0].length - 1;
        for (int contador = 0; contador < resultado.length; contador++) {
            elementoLadoDerecho = fracciones[contador][ladoDerecho];
            elementoColumna = fracciones[contador][columna];
            resultado[contador] = generarRadio(elementoLadoDerecho, elementoColumna);
        }
        return resultado;
    }

    /**
     * Genera el radio entre dos elementos, el lado derecho y la columna, si el
     * elemento de la columna es menor o igual a cero, retorna una fracción con
     * el valor máximo del tipo de dato Double que representa nuestro inifinito
     * positivo.
     *
     * @param ladoDerecho Fracción del lado derecho.
     * @param columna Fracción de la columna.
     * @return Fracción que representa el radio.
     */
    private AbstractFraccion generarRadio(AbstractFraccion ladoDerecho,
            AbstractFraccion columna) {
        AbstractFraccion cero = new Fraccion(0, 1);//Este valor representa al valor entero 0. 
        if (columna.menorIgualQue(cero)
                || (ladoDerecho.dividir(columna).menorQue(cero))) {
            return new Fraccion(Double.MAX_VALUE);
        } else {
            return ladoDerecho.dividir(columna);
        }
    }

    /**
     * Realiza una operación de fila en una matriz de un problema de
     * programación lineal, multiplicando al elemento de la primera fila el
     * valor del operador que se indique. La operación de fila funciona de la
     * siguiente manera: (valorOperador * elemetofila1) + elementofila2
     *
     * @param fracciones Matriz con los elementos del problema de programación
     * lineal.
     * @param fila1 Índice de la fila que representa el primer operador de la
     * operación de fila a realizar.
     * @param fila2 Índice de la fila que representa el segundo operador de la
     * operación de fila a realizar.
     * @param valorOperador Valor que se multiplica por el primer para
     * normalizar el primer elemento de la operación de fila.
     * @return Arreglo de fracciones, que representan a la fila resultado luego
     * de realizar el pivoteo.
     */
    private AbstractFraccion[] pivotear(AbstractFraccion[][] fracciones, int fila1,
            int fila2, AbstractFraccion valorOperador) {
        int tamanoFila = fracciones[0].length;
        AbstractFraccion[] resultado = new AbstractFraccion[tamanoFila];
        AbstractFraccion elementoFila1;
        AbstractFraccion elementoFila2;
        for (int contador = 0; contador < tamanoFila; contador++) {
            elementoFila1 = fracciones[fila1][contador];
            elementoFila2 = fracciones[fila2][contador];
            elementoFila1 = elementoFila1.multiplicar(valorOperador);
            resultado[contador] = elementoFila1.sumar(elementoFila2);
        }
        return resultado;
    }

    /**
     * Verifica si un problema de programación lineal indicado por medio de los
     * coeficientes de la función objetivo posee una solución factible.
     *
     * @param valores Fracciones que simbolizan los coeficientes de la función
     * objetivo dentro de la matriz del simplex.
     * @return Valor booleano que indica si es factible.
     */
    private boolean verificarFactibilidad(AbstractFraccion[] valores) {
        AbstractFraccion valor;
        AbstractFraccion cero = new Fraccion(0);
        for (int contador = 0; contador < valores.length - 1; contador++) {
            valor = valores[contador];
            if (valor.menorQue(cero)) {
                return true;
            }
        }
        valor = valores[valores.length - 1];
        return valor.iguales(cero);
    }

    /**
     * Se encarga de verificar si el lado derecho generado indica que el
     * problema de programación lineal se encuentra acotado.
     *
     * @param ladoDerecho Valores del lado derecho del problema.
     * @return Valor booleano que indica si el problema esta acotado.
     */
    private boolean esAcotado(AbstractFraccion[] ladoDerecho) {
        AbstractFraccion elementoLadoDerecho;
        AbstractFraccion infinito = new Fraccion(Double.MAX_VALUE);
        for (int contador = 1; contador < ladoDerecho.length; contador++) {
            elementoLadoDerecho = ladoDerecho[contador];
            if (!elementoLadoDerecho.iguales(infinito)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Genera un 1 en la posición de la fila indicada por parametro. El proceso
     * se realiza por medio de una operación de fila, donde se divide todos los
     * elementos por el indicado.
     *
     * @param fila Contiene los coeficientes de la fila donde se realizará el
     * uno.
     * @param indiceElemento Índice dentro de la fila, donde se realizará un
     * uno.
     * @return Arreglo de fracciones que representan los coeficientes de la fila
     * luego de realizar la operación de fila.
     */
    private AbstractFraccion[] generarUno(AbstractFraccion[] fila, int indiceElemento) {
        AbstractFraccion elementoFila;
        AbstractFraccion[] resultado = new AbstractFraccion[fila.length];
        AbstractFraccion elemento = fila[indiceElemento];
        AbstractFraccion cero = new Fraccion(0);
        AbstractFraccion uno = new Fraccion(1);
        for (int contador = 0; contador < fila.length; contador++) {
            if (elemento.iguales(cero)) {
                elementoFila = fila[contador];
                resultado[contador] = elementoFila.sumar(uno);
            } else {
                elementoFila = fila[contador];
                resultado[contador] = elementoFila.dividir(elemento);
            }
        }
        return resultado;
    }

    /**
     * Realiza todas las operaciones de fila necesarias para crear una variable
     * entrante en la fila y columna indicada, no realiza operación de fila
     * donde esta la variable entrante.
     *
     * @param matriz Matriz con los elementos del problema de programación
     * lineal que simbolizan los coeficientes.
     * @param indiceFilaEntrante Índice de la fila que indica la variable
     * entrante.
     * @param indiceColumnaEntrante Índice de la columna que indica la variable
     * entrante.
     * @return Coeficientes resultado del problema de programación lineal luego
     * de aplicar las operaciones de fila.
     */
    private AbstractFraccion[][] realizarOperaciones(AbstractFraccion[][] matriz,
            int indiceFilaEntrante, int indiceColumnaEntrante) {
        AbstractFraccion coeficiente;
        for (int contador = 0; contador < matriz.length; contador++) {
            if (indiceFilaEntrante != contador) {
                coeficiente = generarCoeficiente(matriz, contador, indiceColumnaEntrante);
                matriz[contador] = pivotear(matriz, indiceFilaEntrante,
                        contador, coeficiente);
            }
        }
        return matriz;
    }

    /**
     * Valida si la función objetivo de un problema de programación lineal, ha
     * llegado haste el punto optimo posible dentro del método simplex. Todos
     * los coeficientes de variables de holgura, artificiales o básicas deben
     * ser mayor que cero.
     *
     * @param funcionObjetivo Arreglo de fracciones que representan a la función
     * objetivo del problema de programación lineal.
     * @return Valor booleano que indica si el problema de programación ha
     * llagado hasta su punto óptimo.
     */
    private boolean validarSimplexTerminado(AbstractFraccion[] funcionObjetivo) {
        AbstractFraccion coeficiente;
        AbstractFraccion cero = new Fraccion(0);
        for (int contador = 0; contador < funcionObjetivo.length - 1; contador++) {
            coeficiente = funcionObjetivo[contador];
            if (coeficiente.menorQue(cero)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Invierte el signo de los coeficientes que pertenecen a una fila de la
     * matriz.
     *
     * @param coeficientes Coeficientes de la fila.
     * @return Coeficientes de la fila con el signo invertido.
     */
    private AbstractFraccion[] negarCoeficientes(AbstractFraccion[] coeficientes) {
        for (AbstractFraccion coeficiente : coeficientes) {
            coeficiente.hacerNegativa();
        }
        return coeficientes;
    }

    /**
     * Agrega un uno a la matriz, que representa a una variable de holgura o
     * artificial. Este puede ser negativo o positivo.
     *
     * @param matriz Matriz con los coeficientes del problema de programación
     * lineal.
     * @param fila Fila donde se va a agregar el uno.
     * @param columna Columna donde se va a agregar el uno.
     * @param positivo Inidica el signo del uno a agregar.
     * @return Matriz con los coeficientes anteriores y ek uno agregado.
     */
    private AbstractFraccion[][] agregarUnoMatriz(AbstractFraccion[][] matriz,
            int fila, int columna, boolean positivo) {
        double valor = positivo ? 1 : -1;
        matriz[fila][columna] = new Fraccion(valor);
        return matriz;
    }

    /**
     * Amplia las columnas de la matriz del problema de programación lineal, con
     * el fin de agregar variables holguras y artificiales
     *
     * @param matriz Matriz con los coeficientes del problema de programación
     * lineal.
     * @param cantidad Cantidad de columnas que se agregarán a la matriz.
     * @return Nueva matriz con las columnas que representan las variables de
     * holgura y artificiales.
     */
    private AbstractFraccion[][] agregarColumnas(AbstractFraccion[][] matriz,
            int cantidad) {
        int totalAnterior = matriz[0].length;
        int totalNuevo = totalAnterior + cantidad;
        AbstractFraccion[][] nuevaMatriz = new AbstractFraccion[matriz.length][totalNuevo];
        AbstractFraccion cero = new Fraccion(0);
        for (int contadorFila = 0; contadorFila < nuevaMatriz.length; contadorFila++) {
            for (int contadorColumna = 0; contadorColumna < totalNuevo; contadorColumna++) {
                if (contadorColumna < totalAnterior - 1) {
                    nuevaMatriz[contadorFila][contadorColumna]
                            = matriz[contadorFila][contadorColumna];
                } else if (contadorColumna < nuevaMatriz[0].length - 1) {
                    nuevaMatriz[contadorFila][contadorColumna] = cero.clonar();
                } else {
                    nuevaMatriz[contadorFila][contadorColumna]
                            = matriz[contadorFila][totalAnterior - 1];
                }
            }
        }
        return nuevaMatriz;
    }

    /**
     * Agrega un uno positivo o negativo en la matriz, en las posiciones
     * indicadas por parámetro, iniciando en el índice de inicio que representa
     * la columna donde iniciará.
     *
     * @param matriz Matriz que representa los coeficientes del problema de
     * programación lineal.
     * @param posiciones Índices dentro de la matriz donde indica la fila donde
     * se añaderá un uno.
     * @param positivo Indica si el uno es positivo o negativo.
     * @param inicio Índice de la columna que representa donde iniciará a
     * agregar unos.
     * @return Matriz con los unos positivos o negativos agregados.
     */
    private AbstractFraccion[][] agregarUnos(AbstractFraccion[][] matriz,
            ArrayList<Integer> posiciones, boolean positivo, int inicio) {
        for (int contador = 0; contador < posiciones.size(); contador++) {
            matriz = agregarUnoMatriz(matriz, posiciones.get(contador),
                    inicio + contador - 1, positivo);
        }
        return matriz;
    }

    /**
     * Convierte la matriz con los coeficientes del problema del programación
     * lineal en un problema de dos fases, agregando la fila -w y los unos en
     * las columnas de las variables artificiales.
     *
     * @param matriz Matriz que representa los coeficientes del problema de
     * programación lineal.
     * @param inicioArtifiales Número de columna donde inicia las variables
     * artificiales
     * @return Mtriz con los coeficientes del problema convertido en problema de
     * dos fases.
     */
    private AbstractFraccion[][] convertirDosFases(AbstractFraccion[][] matriz,
            int inicioArtifiales) {
        AbstractFraccion[][] resultado = new AbstractFraccion[matriz.length + 1][matriz[0].length];
        AbstractFraccion[] funcionW = crearFuncionW(matriz[0].length, inicioArtifiales - 1);
        resultado[0] = funcionW;
        for (int contador = 1; contador < resultado.length; contador++) {
            resultado[contador] = matriz[contador - 1];
        }
        return resultado;
    }

    /**
     * Crea los coeficientes de la función objetivo cuando es un problema de dos
     * fases, agrega a la función objetivo las variables artificiales
     *
     * @param tamano Cantidad de coeficientes que llevará la fila.
     * @param inicioArtifiales Índice donde incian las columnas de las variables
     * artificiales.
     * @return Arreglo de fracciones que representan ls coeficientes de la
     * función objetivo w, con las variables artificiales agregadas.
     */
    private AbstractFraccion[] crearFuncionW(int tamano, int inicioArtifiales) {
        AbstractFraccion[] funcionW = new AbstractFraccion[tamano];
        AbstractFraccion cero = new Fraccion(0);
        AbstractFraccion uno = new Fraccion(1);
        for (int contador = 0; contador < funcionW.length; contador++) {
            if (contador < inicioArtifiales) {
                funcionW[contador] = cero.clonar();
            } else if (contador < tamano - 1) {
                funcionW[contador] = uno.clonar();
            } else {
                funcionW[contador] = cero.clonar();
            }
        }
        return funcionW;
    }

    /**
     * Genera un coeficiente con el signo inverso de el valor que se encuentra
     * en la posición indicada.
     *
     * @param matriz Matriz que representa los coeficientes del problema de
     * programación lineal.
     * @param indiceFila Índice de la fila donde se va a realizar el
     * coeficiente.
     * @param indiceColumna Índice de la columna donde se va a realizar el
     * coeficiente.
     * @return Coeficiente con el signo inverso del valor de la matriz indicada.
     */
    private AbstractFraccion generarCoeficiente(AbstractFraccion[][] matriz,
            int indiceFila, int indiceColumna) {
        AbstractFraccion[] fila = matriz[indiceFila];
        AbstractFraccion coeficiente = fila[indiceColumna].clonar();
        coeficiente.hacerNegativa();
        return coeficiente;
    }

    /**
     * Genera automaticamente la fila y columna que serán entrante en la
     * siguiente iteración, se generan los radios y se obtiene el menor, de
     * igual manera se busca la posición más pequeña dentro de los coeficientes
     * de la función objetivo del problema.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar el siguiente pivoteo.
     * @return Coordenada con fila y columna que indica donde será la siguiente
     * operación.
     */
    private Point siguientePivoteo(DtoSimplex dto) {
        AbstractFraccion[][] matriz = dto.getMatriz();
        int acoto = dto.esDosfases() ? 2 : 1;
        int columna = obtenerIndiceDelValorMenor(matriz[0], 0, 1);
        AbstractFraccion[] radios = obtenerRadios(matriz, columna);
        int fila = obtenerIndiceDelValorMenor(radios, acoto, 0);
        Point resultado = new Point(columna, fila);
        return resultado;
    }

    /**
     * Agrega nombre de variables a las columnas del problema de programación
     * lineal. Estas pueden ser artificiales "a" o de holgura "s", es indicado
     * por parámetro.
     *
     * @param nombres Arreglo con el nombre de las variables donde se van a
     * agregar los nuevos nombres.
     * @param cantidad Número nuevo de variables nuevas por agregar.
     * @param nuevoNombre Nombre que llevará la columna.
     * @return Arreglo con el nuevo nombre de las variables y las anteriores.
     */
    private String[] agregarNombreVariables(String[] nombres, int cantidad,
            String nuevoNombre) {
        String[] resultado = new String[nombres.length + cantidad];
        for (int contador = 0; contador < resultado.length; contador++) {
            if (contador < nombres.length) {
                resultado[contador] = nombres[contador];
            } else {
                resultado[contador] = nuevoNombre + (contador + 1);
            }
        }
        return resultado;
    }

    /**
     * Agrega a los nombres de fila seleccionada, un nombre de una variable
     * selecciona, este puede ser de holgura "s" o artificial "a".
     *
     * @param nombres Arreglo con el nombre de las variables seleccionadas donde
     * se van a agregar los nuevos nombres.
     * @param Indicesfila Índices de las filas donde se agregará un nombre.
     * @param nombreFila Nombre que llevará la fila.
     * @param indiceInicio Número en el cual se empezará a iterar los nombres de
     * las variables.
     * @return Arreglo con los nuevos nombres de las variables que se encuentran
     * seleccionadas en las filas.
     */
    private String[] agregarNombresFila(String[] nombres,
            ArrayList<Integer> Indicesfila, String nombreFila,
            int indiceInicio) {
        for (int contador = 0; contador < Indicesfila.size(); contador++) {
            int fila = Indicesfila.get(contador);
            nombres[fila] = nombreFila + indiceInicio;
            indiceInicio++;
        }
        return nombres;
    }

    /**
     * Agrega el nombre de la variable -w al inicio, al nombre de las filas del
     * problema de programación lineal.
     *
     * @param nombres Arreglo con el nombre de las variables seleccionadas donde
     * se van a agregar el -w.
     * @return Arreglo con los nuevos nombres de las variables que se encuentran
     * seleccionadas en las filas.
     */
    private String[] agregarNombreW(String[] nombres) {
        String[] resultado = new String[nombres.length + 1];
        resultado[0] = "-w";
        for (int contador = 1; contador < resultado.length; contador++) {
            resultado[contador] = nombres[contador - 1];
        }
        return resultado;
    }

    /**
     * Para problemas de minimización cambia el nombre de la fila z a -z.
     *
     * @param tamano Cantidad de filas del problema de programación lineal.
     * @param nombresCabeza Nombre que llevará la primera fila del problema de
     * programación lineal.
     * @return Arreglo con los nombres de las variables que se encuentran
     * seleccionadas en las filas.
     */
    private String[] crearNombreFila(int tamano, String nombreCabeza) {
        String[] nombres = new String[tamano];
        nombres[0] = nombreCabeza;
        return nombres;
    }

    /**
     * Obtiene la siguiente iteración de un problema de programación lineal
     * indicado, utilizando el método simplex ordinario.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @return Objeto de transferencia de datos con los datos de la siguiente
     * iteración.
     */
    private DtoSimplex siguientePasoSimplex(DtoSimplex dto) {
        AbstractFraccion[][] matriz = dto.getMatriz();
        Point coordernadaPivoteo = dto.getCoordenadaPivote();
        int columna = coordernadaPivoteo.x;
        AbstractFraccion[] radios = obtenerRadios(matriz, columna);
        if (esAcotado(radios)) {
            int fila = coordernadaPivoteo.y;
            matriz[fila] = generarUno(matriz[fila], columna);
            matriz = realizarOperaciones(matriz, fila, columna);
            dto.setCoordenadaPivote(siguientePivoteo(dto));
            dto.setMatriz(matriz);
            String nombreColumna = dto.getNombreColumna(columna);
            dto.setNombreFila(fila, nombreColumna);
            if (!validarSimplexTerminado(dto.getMatriz()[0])) {
                dto = siguientesOperaciones(dto);
            } else {
                dto.setSolucion(obtenerSolucion(dto));
                dto.setMensaje("Se ha finalizado lograndollegar a un estado óptimo para "
                        + "el problema de programación lineal ingresado.");
                dto.setFinalizado(true);
            }
            return dto;
        } else {
            dto.setAcotado(false);
            dto.setMensaje("El problema de programación lineal ingresado, no posee una solución acotada,"
                    + " dentro de un rango válido. Por favor revisar restricciones.");
            return dto;
        }
    }

    /**
     * Obtiene la siguiente iteración de un problema de programación lineal
     * indicado, utilizando el método simplex de dos fases.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @return Objeto de transferencia de datos con los datos de la siguiente
     * iteración.
     */
    private DtoSimplex siguientePasoDosFases(DtoSimplex dto) {
        if (verificarFactibilidad(dto.getMatriz()[0])) {
            boolean terminado = validarSimplexTerminado(dto.getMatriz()[0]);
            if (terminado && !dto.esBloqueoDosFases()) {
                dto = eliminarFilaW(dto);
                dto = eliminarColumnasArtificiales(dto);
                dto.setMensaje("Se ha finalizado la primera fase, se eliminaron las "
                        + "columnas de las variables artificiales 'a' y la fila que representa"
                        + " a la función w.");
                dto.setDosfases(false);
                dto.setCoordenadaPivote(siguientePivoteo(dto));
                dto = siguientesOperaciones(dto);
            } else if (dto.esBloqueoDosFases()) {
                dto = eliminarArtificiales(dto);
                dto.setMensaje("Primera etapa del método de dos fases, se eeliminan los 1's "
                        + "de la fila que representa a las variables artificiales en la función w.");
            } else {
                dto = siguientePasoSimplex(dto);
                dto.setFinalizado(false);
            }
            boolean factible = verificarFactibilidad(dto.getMatriz()[0]);
            dto.setFactible(factible);
            return dto;
        } else {
            dto.setFactible(false);
            dto.setMensaje("Se ha detectado que el problema de programación lineal, no posee "
                    + "una solución optima factible.");
            return dto;
        }
    }

    /**
     * Elimina los coeficientes de las variables artificiales de la función
     * objetivo, al inicio del método de dos fases.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @return Objeto de transferencia de datos con los datos despues de
     * realizar la eliminación de las variables artificiales..
     */
    private DtoSimplex eliminarArtificiales(DtoSimplex dto) {
        AbstractFraccion[][] matriz = dto.getMatriz();
        String[] nombreColumnas = dto.getNombreColumnas();
        String[] nombreFilas = dto.getNombreFilas();
        AbstractFraccion unoNegativo = new Fraccion(-1);
        int columna, fila;
        columna = dto.getArtificialActual();
        String nombreArtificial = nombreColumnas[columna];
        fila = buscarIndice(nombreFilas, nombreArtificial);
        matriz[0] = pivotear(matriz, fila, 0, unoNegativo);
        columna++;
        dto.setArtificialActual(columna);
        if (matriz[0].length - 1 == columna) {
            dto.setBloqueoDosFases(false);
            dto.setCoordenadaPivote(siguientePivoteo(dto));
            dto = siguientesOperaciones(dto);
        }else{
            columna = dto.getArtificialActual();
            nombreArtificial = nombreColumnas[columna];
            fila = buscarIndice(nombreFilas, nombreArtificial);
            dto.setOperaciones(siguientesOperacionesInicioDosfases(fila-1));
            dto.setCoordenadaPivote(new Point(fila, columna));
        }
        dto.setMatriz(matriz);
        return dto;
    }

    /**
     * Busca dentro de una lista de string el elemento indicado por parametro.
     *
     * @param nombres Lista de nombres.
     * @param nombre Elemento a buscar dentro de la lista.
     * @return Índice del elemento que se busco.
     */
    private int buscarIndice(String[] nombres, String nombre) {
        for (int contador = 0; contador < nombres.length; contador++) {
            String elementoNombre = nombres[contador];
            if (elementoNombre.equals(nombre)) {
                return contador;
            }
        }
        return -1;
    }

    /**
     * Elimina la primera fila, agregada por el método de dos fases.
     *
     * @param dto Contiene los datos del problema de programación lineal.
     * @return Objeto de transferencia de datos con los datos despues eliminar
     * la primera fila.
     */
    private DtoSimplex eliminarFilaW(DtoSimplex dto) {
        AbstractFraccion[][] matriz = dto.getMatriz();
        AbstractFraccion[][] resultado = new AbstractFraccion[matriz.length - 1][matriz[0].length];
        for (int contador = 0; contador < resultado.length; contador++) {
            resultado[contador] = matriz[contador + 1];
        }
        dto.setMatriz(resultado);
        dto.setNombreFilas(eliminarNombreW(dto.getNombreFilas()));
        return dto;
    }

    /**
     * Elimina las columnas que representan a las variables artificiales después
     * del método de dos fases.
     *
     * @param dto Contiene los datos del problema de programación lineal.
     * @return Objeto de transferencia de datos con los datos despues eliminar
     * las columnas que representan las variables artificiales.
     */
    private DtoSimplex eliminarColumnasArtificiales(DtoSimplex dto) {
        int indiceInicioArtificiales = dto.getVariablesBasicas()
                + dto.getVariablesHolgura() - 1;
        AbstractFraccion[][] matriz = dto.getMatriz();
        int cantidadArtificiales = matriz[0].length - indiceInicioArtificiales - 1;
        int totalNuevo = matriz[0].length - cantidadArtificiales;
        AbstractFraccion[][] resultado = new AbstractFraccion[matriz.length][totalNuevo];
        for (int contadorFila = 0; contadorFila < resultado.length; contadorFila++) {
            for (int contadorColumna = 0; contadorColumna < resultado[0].length; contadorColumna++) {
                if (contadorColumna == resultado[0].length - 1) {
                    resultado[contadorFila][contadorColumna] = matriz[contadorFila][matriz[0].length - 1];
                } else {
                    resultado[contadorFila][contadorColumna] = matriz[contadorFila][contadorColumna];
                }
            }
        }
        dto.setMatriz(resultado);
        dto.setNombreColumnas(eliminarNombresColumnas(dto.getNombreColumnas(), indiceInicioArtificiales));
        return dto;
    }

    /**
     * Elimina el primer nombre del arreglo de nombres de fila, principalmente
     * para el paso de la primera fase a la segunda, quitando del w de esta
     * lista.
     *
     * @param nombresFila Arreglo con los nombres de las filas.
     * @return Arreglo con el nombre de las filas, sin el primer elemento.
     */
    private String[] eliminarNombreW(String[] nombresFila) {
        String[] resultado = new String[nombresFila.length - 1];
        for (int contador = 0; contador < resultado.length; contador++) {
            resultado[contador] = nombresFila[contador + 1];
        }
        return resultado;
    }

    /**
     * Elimina de los nombres de las columnas, las columnas que representan a
     * las artificiales.
     *
     * @param nombresColumnas Arreglo con los nombres de las columnas.
     * @param indiceInicioArtificiales Índice donde comienzan las variables
     * artificiales.
     * @return Arreglo con el nuevo nombre de las columnas.
     */
    private String[] eliminarNombresColumnas(String[] nombresColumnas,
            int indiceInicioArtificiales) {
        int cantidadArtificiales = nombresColumnas.length
                - indiceInicioArtificiales;
        int totalNuevo = nombresColumnas.length - cantidadArtificiales;
        String[] resultado = new String[totalNuevo];
        for (int contador = 0; contador < resultado.length; contador++) {
            resultado[contador] = nombresColumnas[contador];
        }
        return resultado;
    }

    /**
     * Crea Strings que representan las operaciones de fila para eliminar el uno
     * de las variables artificiales en la función objetivo de un problema de
     * dos fases.
     *
     * @param indice Índice de la fila donde se encuentra seleccionada la
     * variable artificial.
     * @return Arreglo de Strings con las operaciones a realizar.
     */
    private String[] siguientesOperacionesInicioDosfases(int indice) {
        String[] resultado = new String[1];
        resultado[0] = "-1 F" + indice + " + F0' -> F0'";
        return resultado;
    }
    
    /**
     * Retorna un string que simboliza el estado de las variables de la función objetivo
     * al finalizar el metodo del simplex.
     * @param dto Contiene los datos del problema de programación lineal.
     * @return String con los datos de la solución.
     */
    private String obtenerSolucion(DtoSimplex dto){
        String resultado = "";
        String[] nombreFilas = dto.getNombreFilas();
        String[] nombreColumnas = dto.getNombreColumnas();
        resultado += nombreFilas[0]+ " = "
                +dto.getMatriz()[0][dto.getMatriz()[0].length-1].toString(dto.esFormatoFraccional()) + "\n";
        for (int contador = 0; contador < dto.getVariablesHolgura()-1; contador++) {
            String columna = nombreColumnas[contador];
            int indice = buscarIndice(nombreFilas, columna);
            if(indice == -1){
                resultado += columna + " = 0";
            }else{
                resultado += columna+ " = " + 
                        dto.getMatriz()[indice][dto.getMatriz()[0].length-1].toString(dto.esFormatoFraccional());
            }
            resultado += "\n";
        }
        return resultado;
    }
    
    void imprimir(DtoSimplex dto) {
        AbstractFraccion[][] m = dto.getMatriz();
        String c = "";
        String[] f = dto.getNombreFilas();
        String[] col = dto.getNombreColumnas();
        String s = "";
        String of = dto.getOperaciones();
        s +=of;
        s += '\n';
        c += s;
        c += " Acotado: " + dto.esAcotado() + "\n";
        c += " Factible: " + dto.esFactible() + "\n";
        c += " Terminado: " + dto.esFinalizado() + "\n";
        c += " Dos fases: " + dto.esDosfases() + "\n";
        c += " Bloqueo: " + dto.esBloqueoDosFases() + "\n";
        c += " punto: " + dto.getCoordenadaPivote() + "\n";
        s = "     ";
        for (int contador = 0; contador < col.length; contador++) {
            s += col[contador];
            s += "     ";
        }
        s += "\n";
        c += s;
        for (int contadorFila = 0; contadorFila < m.length; contadorFila++) {
            s = "";
            s += f[contadorFila];
            s += "     ";
            for (int contadorColumna = 0; contadorColumna < m[0].length; contadorColumna++) {
                s += m[contadorFila][contadorColumna].toString();
                s += "     ";
            }
            s += "\n";
            c += s;
        }
        System.out.println(c);
    }
}
