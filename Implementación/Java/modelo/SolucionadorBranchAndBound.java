package modelo;

import modelo.AbstractFraccion;
import dto.DtoSimplex;
import java.util.ArrayList;
import modelo.parser.sym;

/**
 *
 * @author Yordan Jiménez
 */
public class SolucionadorBranchAndBound extends SolucionadorSimplex {

    /**
     * Constructor vacío.
     */
    private NodoBranchAndBound arbol;
    private ArrayList<NodoBranchAndBound> hojas;

    public SolucionadorBranchAndBound() {
        hojas = new ArrayList<>();
    }

    @Override
    public ArrayList<DtoSimplex> solucionar(DtoSimplex dto) {
        ArrayList<DtoSimplex> resultado = new ArrayList<>();
        boolean continuar = true;
        while (continuar) {
            DtoSimplex pasoActual = this.siguientePaso(dto.clonarSinCompletarProfundo());
            resultado.add(pasoActual);
            continuar = !pasoActual.esFinalizado();
            continuar &= pasoActual.esFactible();
        }
        return resultado;
    }

    @Override
    public DtoSimplex siguientePaso(DtoSimplex dto) {
        boolean factible, finalizado;
        if (arbol == null) {
            DtoSimplex problema = super.completarProblema(dto.clonarSinCompletarProfundo());
            ArrayList<DtoSimplex> solucion;
            solucion = super.solucionar(problema);
            problema = solucion.get(solucion.size() - 1);
            NodoBranchAndBound nodo;
            nodo = generarNuevoNodo(null, dto, problema, "1");
            arbol = nodo;
            hojas.add(nodo);
        } else {
            int indiceSiguienteNodo = buscarNodo(dto.esMaximización(), false);
            generarNuevosProblemas(indiceSiguienteNodo);
            acotarNodos(dto.esMaximización());
        }
        String mensaje = "";
        finalizado = validarBranchAndBoundTerminado();
        factible = haySolucionFactible();
        if (finalizado && factible) {
            NodoBranchAndBound optimo = hojas.get(buscarNodo(dto.esMaximización(), true));
            mensaje = optimo.obtenerTodasRestricciones();
            mensaje += "Valor de las variables:\n";
            mensaje += optimo.valorVariables("");
            optimo.setOptimo(true);
        } else if (!factible) {
            mensaje = "El problema no posee una solución entera óptima factible.";
        } else {
            int indiceSiguienteNodo = buscarNodo(dto.esMaximización(), false);
            AbstractFraccion[] ValorVariables= hojas.get(indiceSiguienteNodo).getValorVariables();
            ValorVariables = obtenerParteDecimal(ValorVariables);
            int indiceAhora = obtenerIndiceDelValorMayor(ValorVariables, 0, 0);
            String variable = dto.getNombreColumnas()[indiceAhora];
            String problema = "Problema " + hojas.get(indiceSiguienteNodo).getIndiceProblema();
            mensaje = "Se iteró sobre el arbol de soluciones.\n Siguiente Iteración :"
                    + problema + "\n"
                    +"Sobre la variable: "+variable+"\n";
        }
        DtoSimplex resultado = new DtoSimplex(mensaje, arbol.toStringRepeat(0), factible, finalizado);
        return resultado;
    }

    @Override
    public String[] calcularRadio(DtoSimplex dto, int columna) {
        return super.calcularRadio(dto, columna);
    }

    @Override
    public DtoSimplex completarProblema(DtoSimplex dto) {
        return super.completarProblema(dto);
    }

    @Override
    public DtoSimplex siguientesOperaciones(DtoSimplex dto) {
        return super.siguientesOperaciones(dto);
    }

    /**
     * Revisa si no exite una solución no entera mayor, a la mejor solución
     * entera que se ha desarrollado. Si no existe una solución el indice es -1
     * y termina la ejecución de la función.
     *
     * @param tipoProblema Boolean que indica si es problema de maximización o
     * no.
     */
    private void acotarNodos(boolean tipoProblema) {
        ArrayList<NodoBranchAndBound> nodosNoAcotados = buscarNodos(false);
        for (int contador = 0; contador < nodosNoAcotados.size(); contador++) {
            NodoBranchAndBound nodoNoAcotado = nodosNoAcotados.get(contador);
            for (int contadorHoja = 0; contadorHoja < hojas.size(); contadorHoja++) {
                NodoBranchAndBound hoja = hojas.get(contadorHoja);
                if (hoja.esFactible() && !hoja.esAcotado() && hoja.esSolucionEntera()) {
                    if (tipoProblema) {
                        if (hoja.getValorZ().mayorIgualQue(nodoNoAcotado.getValorZ())) {
                            nodoNoAcotado.setAcotado(true);
                        }
                    } else {
                        AbstractFraccion valorZ1 = hoja.getValorZ().clonar();
                        valorZ1.hacerNegativa();
                        AbstractFraccion valorZ2 = nodoNoAcotado.getValorZ().clonar();
                        valorZ2.hacerNegativa();
                        if (valorZ1.menorIgualQue(valorZ2)) {
                            nodoNoAcotado.setAcotado(true);
                        }
                    }
                }
            }
        }
    }

    /**
     * Busca dentro del arreglo de nodos hojas, los nodos que sean enteros o no,
     * dependiendo del parámetro indicado. Además deben ser Factibles y No
     * acotado.
     *
     * @param esEntera Indica si el nodo contiene una sollucón entera o no.
     * @return AArreglo con los nodos encontrados.
     */
    private ArrayList<NodoBranchAndBound> buscarNodos(boolean esEntera) {
        ArrayList<NodoBranchAndBound> resultado = new ArrayList<>();
        for (int contador = 0; contador < hojas.size(); contador++) {
            NodoBranchAndBound hoja = hojas.get(contador);
            if (hoja.esFactible() && !hoja.esAcotado() && hoja.esSolucionEntera() == esEntera) {
                resultado.add(hoja);
            }
        }
        return resultado;
    }

    /**
     * Genera un nodo del arbol asignando el valor del padre y el los datos del
     * problema generado. Asigna el valor de la función Z y el de las variables
     * objetivo.
     *
     * @param padre Referencia al nodo padre de donde proviene, si es nulo
     * significa que es la raíz del arbol.
     * @param problema Objeto de tranferencia con los datos del problema, con
     * las restricciones agregadas hasta el momento.
     * @param indiceProblema Identificador del problema dentro del arbol.
     * @return Nuevo nodo del árbol con los datos inicializados
     */
    private NodoBranchAndBound generarNuevoNodo(NodoBranchAndBound padre, DtoSimplex problema,
            DtoSimplex solucion, String indiceProblema) {
        NodoBranchAndBound resultado = new NodoBranchAndBound(problema, padre);
        AbstractFraccion valorZ = obtenerValorZ(solucion.getMatriz());
        AbstractFraccion[] valorVariables = obtenerValorVariables(solucion);
        resultado.setIndiceProblema(indiceProblema);
        resultado.setValorZ(valorZ);
        resultado.setValorVariables(valorVariables);
        resultado.setFactible(solucion.esFactible() && solucion.esAcotado());
        return resultado;
    }

    /**
     * Retorna el valor de la solución de la función Z del problema de
     * programación lineal.
     *
     * @param matriz Matriz con los valores de la tabla final del problema de
     * programación lineal.
     * @return Fraccion con el dato de la solución del problema.
     */
    private AbstractFraccion obtenerValorZ(AbstractFraccion[][] matriz) {
        AbstractFraccion resultado = matriz[0][matriz[0].length - 1];
        return resultado;
    }

    /**
     * Obtiene el valor de la solución de las variables básicas del problema. El
     * arreglo solución devuelve el valor de cada variable según estan ordenadas
     * en el nombre de las columnas
     *
     * @param problema Objeto de transferencia de datos con los datos del
     * problema.
     * @return Arreglo con los valores de cada variable basica
     */
    private AbstractFraccion[] obtenerValorVariables(DtoSimplex problema) {
        int cantidadVariablesBasicas = problema.getVariablesBasicas() - 1;
        AbstractFraccion[] resultado = new AbstractFraccion[cantidadVariablesBasicas];
        String[] nombresColumnas = problema.getNombreColumnas();
        String[] nombresFilas = problema.getNombreFilas();
        for (int contador = 0; contador < cantidadVariablesBasicas; contador++) {
            String columna = nombresColumnas[contador];
            int indice = super.buscarIndice(nombresFilas, columna);
            if (indice == -1) {
                resultado[contador] = new Fraccion();
            } else {
                resultado[contador] = problema.getMatriz()[indice][problema.getMatriz()[0].length - 1];
            }
        }
        return resultado;
    }

    /**
     * Veifica si en los nodos de las hojas del árbol dan la caracteristica de
     * que el problema sea factible.
     *
     * @return Valor Booleano que indica que el problema es factible.
     */
    private boolean haySolucionFactible() {
        for (int contador = 0; contador < hojas.size(); contador++) {
            NodoBranchAndBound nodo = hojas.get(contador);
            if (nodo.esFactible()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Analiza si en las hojas del árbol generado por el algoritmo de branch and
     * bound, existe la posibilidad de continuar iterando sobre el problema. Se
     * verifica que entre los nodos no exista uno que se factible, no este
     * acotado y que aún no se haya generado una solución entera.
     *
     * @return Valor booleano que indica que el problema ha sido terminado.
     */
    private boolean validarBranchAndBoundTerminado() {
        for (int contador = 0; contador < hojas.size(); contador++) {
            NodoBranchAndBound hoja = hojas.get(contador);
            if (hoja.esFactible() && !hoja.esAcotado()) {
                if (!hoja.esSolucionEntera()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Busca dentro del arreglo de hojas, el índice del siguiente nodo con
     * solución de z mejor entera o no. Dicho nodo dependerá del tipo de
     * problema que se indique, se retorna el nodo con mayor valor z si el
     * problema es de maximización y el menor si el problema es de minimización.
     *
     * @param tipoProblema Boolean que indica el tipo de problema, true si es de
     * maximización de lo contrario sería de minimización.
     * @param esSolucionEntera Indica si el nodo debe contener una solucion
     * entera.
     * @return Índice del siguiente nodo a iterar dependiendo del tipo de
     * problema.
     */
    private int buscarNodo(boolean tipoProblema, boolean esSolucionEntera) {
        if (tipoProblema) {
            return buscarNodoMayorZ(esSolucionEntera);
        } else {
            return buscarNodoMenorZ(esSolucionEntera);
        }
    }

    /**
     * Busca dentro del arreglo de nodos hojas del arbol, el nodo que contenga
     * el valor z menor y retorna el índice donde se encuentra. Dicho valor debe
     * existir porque esta función no se ejecuta si el problema aun no esta
     * terminado.
     *
     * @param esSolucionEntera Indica si el nodo debe contener una solucion
     * entera.
     *
     * @return Valor entero del indice del nodo con el valor z menor.
     */
    private int buscarNodoMenorZ(boolean esSolucionEntera) {
        int resultado = -1;
        for (int contador = 0; contador < hojas.size(); contador++) {
            NodoBranchAndBound hoja = hojas.get(contador);
            if (hoja.esFactible() && !hoja.esAcotado() && hoja.esSolucionEntera() == esSolucionEntera) {
                if (resultado == -1) {
                    resultado = contador;
                } else {
                    AbstractFraccion valorZ1 = hoja.getValorZ().clonar();
                    valorZ1.hacerNegativa();
                    AbstractFraccion valorZ2 = hojas.get(resultado).getValorZ().clonar();
                    valorZ2.hacerNegativa();
                    if (valorZ1.menorIgualQue(valorZ2)) {
                        resultado = contador;
                    }
                }
            }
        }
        return resultado;
    }

    /**
     * Busca dentro del arreglo de nodos hojas del arbol, el nodo que contenga
     * el valor z mayor y retorna el índice donde se encuentra. Dicho valor debe
     * existir porque esta función no se ejecuta si el problema aun no esta
     * terminado.
     *
     * @param esSolucionEntera Indica si el nodo debe contener una solucion
     * entera.
     * @return Valor entero del indice del nodo con el valor z mayor.
     */
    private int buscarNodoMayorZ(boolean esSolucionEntera) {
        int resultado = -1;
        for (int contador = 0; contador < hojas.size(); contador++) {
            NodoBranchAndBound hoja = hojas.get(contador);
            if (hoja.esFactible() && !hoja.esAcotado() && hoja.esSolucionEntera() == esSolucionEntera) {
                if (resultado == -1) {
                    resultado = contador;
                } else if (hoja.getValorZ().mayorQue(hojas.get(resultado).getValorZ())) {
                    resultado = contador;
                }
            }
        }
        return resultado;
    }

    /**
     * Genera los dos nuevos hijos de un nodo dentro del arbol, crea las
     * restricciones, que contendrán y se añaden al problema.
     *
     * @param indiceSiguienteNodo Índice del número entero dentro el arreglo de
     * nodos hojas.
     */
    private void generarNuevosProblemas(int indiceSiguienteNodo) {
        NodoBranchAndBound siguienteNodo = hojas.get(indiceSiguienteNodo);
        AbstractFraccion[] valoresVariables = siguienteNodo.getValorVariables();
        AbstractFraccion[] parteDecimal = obtenerParteDecimal(valoresVariables);
        int indiceVariable = obtenerIndiceDelValorMayor(parteDecimal, 0, 0);
        AbstractFraccion valorSeleccionado = valoresVariables[indiceVariable];
        valorSeleccionado = valorSeleccionado.obtenerParteEntera();
        crearHijos(siguienteNodo, indiceVariable, valorSeleccionado);
    }

    /**
     * Obtiene la parte decimal de los valores de las fracciones ingresadas por
     * parámetro.
     *
     * @param valorVariables Arreglo de fracciones donde se obtendrá la parte
     * decimal.
     * @return Arreglo de fracciones que contiene las partes decimales de los
     * elementos originales respectivamente.
     */
    private AbstractFraccion[] obtenerParteDecimal(AbstractFraccion[] valorVariables) {
        AbstractFraccion[] resultado = new AbstractFraccion[valorVariables.length];
        for (int contador = 0; contador < resultado.length; contador++) {
            resultado[contador] = valorVariables[contador].obtenerParteDecimal();
        }
        return resultado;
    }

    /**
     * Obtiene el índice del valor menor que se encuentre dentro de la lista de
     * fracciones.
     *
     * @param valores Arreglo con las fracciones donde se va a buscar el valor
     * menor.
     * @param indiceInicio Índice donde va a inciar a recorrer el arreglo de
     * valores.
     * @param acotoFinal Índice que representa el último elemento dentro del
     * arreglo que va se comparado.
     * @return Valor entro que representa el índice del valor menor del arreglo.
     */
    private int obtenerIndiceDelValorMayor(AbstractFraccion[] valores, int indiceInicio,
            int acotoFinal) {
        AbstractFraccion valorMenor = valores[indiceInicio];
        AbstractFraccion valor;
        int indice = indiceInicio;
        for (; indiceInicio < valores.length - acotoFinal; indiceInicio++) {
            valor = valores[indiceInicio];
            if (valor.mayorQue(valorMenor)) {
                valorMenor = valor;
                indice = indiceInicio;
            }
        }
        return indice;
    }

    /**
     * Crea los hijos del nodo actual para iterar su solución, luego agrega los
     * hijos al nodo actual y elimina al nodo actual de la lista de hojas,
     * agregando los nuevos hijos.
     *
     * @param siguienteNodo Nodo actual que se encuentra iterando.
     * @param indiceVariable Índice actual de la variable seleccionada por
     * agregar dentro de las restricciones. Indica la columna donde se
     * encuentra.
     * @param valorSeleccionado Valor que se asigna en el lado derecho de la
     * nueva restricción.
     */
    private void crearHijos(NodoBranchAndBound siguienteNodo, int indiceVariable,
            AbstractFraccion valorSeleccionado) {
        DtoSimplex problema1 = siguienteNodo.getProblema();
        DtoSimplex problema2 = siguienteNodo.getProblema();
        AbstractFraccion uno = new Fraccion(1);
        problema1 = agregarRestriccionFinal(problema1, indiceVariable, valorSeleccionado.clonar(), sym.MENORIGUAL);
        valorSeleccionado = valorSeleccionado.sumar(uno);
        problema2 = agregarRestriccionFinal(problema2, indiceVariable, valorSeleccionado, sym.MAYORIGUAL);
        //se solucionan los nuevos problemas
        DtoSimplex solucion1 = super.completarProblema(problema1.clonarSinCompletarProfundo());
        ArrayList<DtoSimplex> iteraciones1 = super.solucionar(solucion1);
        solucion1 = iteraciones1.get(iteraciones1.size() - 1);

        DtoSimplex solucion2 = super.completarProblema(problema2.clonarSinCompletarProfundo());
        ArrayList<DtoSimplex> iteraciones2 = super.solucionar(solucion2);
        solucion2 = iteraciones2.get(iteraciones2.size() - 1);
        //Se generan los nodos
        NodoBranchAndBound nodo1 = generarNuevoNodo(siguienteNodo, problema1, solucion1, siguienteNodo.getIndiceProblema() + ".1");
        NodoBranchAndBound nodo2 = generarNuevoNodo(siguienteNodo, problema2, solucion2, siguienteNodo.getIndiceProblema() + ".2");
        nodo1.setIndiceVariableRestrccion(indiceVariable);
        nodo1.setRestricciion(sym.MENORIGUAL);
        nodo1.setValorRestriccion(valorSeleccionado.restar(uno));
        nodo2.setIndiceVariableRestrccion(indiceVariable);
        nodo2.setRestricciion(sym.MAYORIGUAL);
        nodo2.setValorRestriccion(valorSeleccionado);
        siguienteNodo.setHijoIzquierdo(nodo1);
        siguienteNodo.setHijoDerecho(nodo2);
        hojas.remove(siguienteNodo);
        hojas.add(nodo1);
        hojas.add(nodo2);
    }

    /**
     * Agrega una restricción nueva al final de la matriz del problema de
     * programación lineal.
     *
     * @param problema Objeto de transferencia con los datos del problema de
     * programación lineal.
     * @param indiceVariable Posición dentro de las columnas donde se encuentra
     * la variable seleccionada.
     * @param valorLadoDerecho Fraccion que tomara e papel del lado derecho de
     * la nueva restricción.
     * @param tipoRestriccion Valor numérico que representa a la nueva
     * restriccion.
     * @return Objeto de tranferencia de datos con os datos del nuevo problema.
     */
    private DtoSimplex agregarRestriccionFinal(DtoSimplex problema, int indiceVariable,
            AbstractFraccion valorLadoDerecho, int tipoRestriccion) {
        AbstractFraccion[][] nuevaMatriz = super.agregarFila(problema.getMatriz());
        nuevaMatriz[nuevaMatriz.length - 1][indiceVariable] = new Fraccion(1);
        int tamañoFila = nuevaMatriz[0].length;
        nuevaMatriz[nuevaMatriz.length - 1][tamañoFila - 1] = valorLadoDerecho;
        problema.setListaDesigualdades(agregaDesigualdad(problema.getListaDesigualdades(), tipoRestriccion));
        problema.setMatriz(nuevaMatriz);
        return problema;
    }

    /**
     * Crea y agrega las restricciones dentro de un nuevo arreglo, agregando al
     * final la nueva restricción indicada.
     *
     * @param listaDesigualdades Lista con las desigualdades actuales del
     * problema anterior.
     * @param tipoRestriccion Nueva restricción por agregar.
     * @return Arreglo con las restricciones anteriores y la nueva agregada al
     * final.
     */
    private int[] agregaDesigualdad(int[] listaDesigualdades, int tipoRestriccion) {
        int[] resultado = new int[listaDesigualdades.length + 1];
        for (int contador = 0; contador < listaDesigualdades.length; contador++) {
            resultado[contador] = listaDesigualdades[contador];
        }
        resultado[resultado.length - 1] = tipoRestriccion;
        return resultado;
    }

}

/**
 * Clase privada para trabajar con el arbol generado por el algoritmo de Branch
 * and Bound.
 *
 * @author Yordan Jimenez
 */
class NodoBranchAndBound {

    private DtoSimplex problema;
    private NodoBranchAndBound padre;
    private NodoBranchAndBound hijoIzquierdo;
    private NodoBranchAndBound hijoDerecho;
    private AbstractFraccion[] valorVariables;
    private AbstractFraccion valorZ;
    private boolean acotado;
    private boolean factible;
    private boolean optimo = false;
    private int indiceVariableRestrccion;
    private int restricciion;
    private AbstractFraccion valorRestriccion;
    private String indiceProblema;

    /**
     * Inicializa la instancia con los datos del problema y la referencia al
     * padre.
     *
     * @param problema Objeto de transferencia de datos con los datos del
     * problema a por branch and bound.
     * @param padre Referencia al padre del nodo.
     */
    public NodoBranchAndBound(DtoSimplex problema, NodoBranchAndBound padre) {
        this.problema = problema;
        this.padre = padre;
    }

    /**
     * Valida si los valores de las variables básicas poseen la caracteristica
     * de ser valores enteros.
     *
     * @return Valor boolean que indica si el valor es entero.
     */
    public boolean esSolucionEntera() {
        AbstractFraccion cero = new Fraccion();
        for (int contador = 0; contador < valorVariables.length; contador++) {
            AbstractFraccion valorDecimal = valorVariables[contador].obtenerParteDecimal();
            if (!valorDecimal.iguales(cero)) {
                return false;
            }
        }
        return true;
    }

    public DtoSimplex getProblema() {
        return problema.clonarSinCompletarProfundo();
    }

    public NodoBranchAndBound getPadre() {
        return padre;
    }

    public NodoBranchAndBound getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBranchAndBound hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoBranchAndBound getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoBranchAndBound hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public AbstractFraccion[] getValorVariables() {
        return valorVariables;
    }

    public void setValorVariables(AbstractFraccion[] valorVariables) {
        this.valorVariables = valorVariables;
    }

    public AbstractFraccion getValorZ() {
        return valorZ;
    }

    public void setValorZ(AbstractFraccion ValorZ) {
        this.valorZ = ValorZ;
    }

    public boolean esAcotado() {
        return acotado;
    }

    public void setAcotado(boolean Acotado) {
        this.acotado = Acotado;
    }

    /**
     * Retorna el String que simboliza a la restrcción que añade el nodo Sigue
     * el siguiente Fromato: variable (TipoRestriccion) valor
     *
     * @return String con los datos de retorno.
     */
    public String obtenerRestriccion() {
        String resultado = "";
        if (padre == null) {
            return "No es necesario\n";
        }
        String[] columnas = problema.getNombreColumnas();
        resultado = columnas[indiceVariableRestrccion] + " ";
        switch (restricciion) {
            case 6:
                resultado += "<= ";
                break;
            case 5:
                resultado += ">= ";
                break;
        }
        resultado += valorRestriccion.toString(problema.esFormatoFraccional()) + "\n";
        return resultado;
    }

    /**
     * Retorna un String con el valor que posee las variables objetivo del
     * problema, este resultado poseerá una tabulación indicada.
     *
     * @param espacio String con un espacio para tabular.
     * @return String con el valor de las variables.
     */
    public String valorVariables(String espacio) {
        AbstractFraccion valorZ = this.valorZ.clonar();
        if (!problema.esMaximización()) {
            valorZ.hacerNegativa();
        }
        String resultado = espacio + "z = " + valorZ.toString(problema.esFormatoFraccional()) + '\n';
        String[] nombreColumnas = problema.getNombreColumnas();
        for (int contador = 0; contador < valorVariables.length; contador++) {
            AbstractFraccion valorVariable = valorVariables[contador];
            String nombre = nombreColumnas[contador];
            resultado += espacio + nombre + " = " + valorVariable.toString(problema.esFormatoFraccional()) + '\n';
        }
        return resultado;
    }

    /**
     * Genera el String de los datos del nodo, indica si es acotado, factible o
     * muestra todo el contenido con un espacio de tabulación.
     *
     * @param espacio String con un espacio para tabular.
     * @return String con los datos del nodo.
     */
    private String toString(String espacio) {
        String resultado = "";
        if (esFactible()) {
            resultado = valorVariables(espacio);
            resultado += espacio + "Restricción: " + obtenerRestriccion();
            if (optimo) {
                resultado += espacio + "*Solución Óptima.\n";
            }
            if (acotado) {
                resultado += espacio + "*Problema Acotado\n";
            }
        } else {
            resultado += espacio + "Restricción: " + obtenerRestriccion();
            resultado += espacio + "*Problema No Factible\n";
        }
        return resultado;
    }

    /**
     * Genera el String del arbol que representa el nodo actual. Posee la
     * estructura similar a la de un file system, con los nodos como elementos.
     * Estrucutra: Problema 1 información del nodo Problema 1.1 Información del
     * nodo
     *
     * Problema 1.2 Información del nodo.
     *
     * @param cantidad Espacio tabulador entre los niveles del árbol.
     * @return String con el formato indicado,
     */
    public String toStringRepeat(int cantidad) {
        String espacio = repetirCaracter(cantidad, ' ');
        String resultado = espacio + "Problema " + indiceProblema + "\n";
        resultado += this.toString(espacio + "  ") + "\n";
        if (this.hijoIzquierdo != null) {
            resultado += hijoIzquierdo.toStringRepeat(cantidad + 5);
        }
        if (this.hijoDerecho != null) {
            resultado += hijoDerecho.toStringRepeat(cantidad + 5);
        }
        return resultado;
    }

    /**
     * Crea un nuevo String con el caracter indicado repetido las veces
     * indicadas.
     *
     * @param cantidad Cantidad de veces que se va repetir el caracter.
     * @param caracter Char que constituirá el nuevo String.
     * @return String con los caracteres agregados.
     */
    private String repetirCaracter(int cantidad, char caracter) {
        String resultado = "";
        for (int contador = 0; contador < cantidad; contador++) {
            resultado += caracter;
        }
        return resultado;
    }

    /**
     * Retorna el valor String de las restricciones dentro del arbol hasta
     * llegar a este nodo.
     *
     * @return
     */
    public String obtenerTodasRestricciones() {
        String resultado = obtenerRestriccion();
        NodoBranchAndBound nodoPadre = padre;
        while (nodoPadre != null) {
            if (nodoPadre.getPadre() != null) {
                resultado = nodoPadre.obtenerRestriccion() + resultado;
            }
            nodoPadre = nodoPadre.getPadre();
        }
        return resultado;
    }

    public boolean esFactible() {
        return factible;
    }

    public void setFactible(boolean factible) {
        this.factible = factible;
    }

    public int getIndiceVariableRestrccion() {
        return indiceVariableRestrccion;
    }

    public void setIndiceVariableRestrccion(int indiceVariableRestrccion) {
        this.indiceVariableRestrccion = indiceVariableRestrccion;
    }

    public int getRestricciion() {
        return restricciion;
    }

    public void setRestricciion(int restricciion) {
        this.restricciion = restricciion;
    }

    public AbstractFraccion getValorRestriccion() {
        return valorRestriccion;
    }

    public void setValorRestriccion(AbstractFraccion valorRestriccion) {
        this.valorRestriccion = valorRestriccion;
    }

    public boolean esOptimo() {
        return optimo;
    }

    public void setOptimo(boolean optimo) {
        this.optimo = optimo;
    }

    public String getIndiceProblema() {
        return indiceProblema;
    }

    public void setIndiceProblema(String indiceProblema) {
        this.indiceProblema = indiceProblema;
    }
}
