package dto;

import java.awt.Point;
import modelo.AbstractFraccion;
import modelo.Fraccion;

/**
 *
 * @author Yordan Jiménez
 */
public class DtoSimplex implements Serializable{

    private AbstractFraccion[][] matriz;
    private String nombreColumnas[];
    private String nombreFilas[];
    private String operaciones[] = new String[0];
    private int listaDesigualdades[];
    private boolean maximizacion;
    private boolean factible = true;
    private boolean acotado = true;
    private boolean dosfases = false;
    private boolean finalizado = false;
    private boolean bloqueoDosFases = false;
    private boolean formatoFraccional = true;
    private boolean esMatriz = false;
    private int variablesBasicas;
    private int variablesHolgura;
    private int artificialActual;
    private Point coordenadaPivote;
    private String solucion;
    private String mensaje;
    
    /**
     * Inicializa una instancia de un objeto de tranferencia de datos, con la solucion,
     * mensaje, valor de factibilidad y valor de finalizado indicados.
     * @param solucion String que indica la solucion.
     * @param mensaje String con mensaje a mostrar.
     * @param factible Valor booleano que indica si el problema es factible.
     * @param finalizado  Valor boolean que indica si el problema esta finalizado.
     */
    public DtoSimplex(String solucion,String mensaje, boolean factible, boolean finalizado){
        this.mensaje = mensaje;
        this.solucion = solucion;
        this.factible = factible;
        this.finalizado = finalizado;
    }

    /**
     * Instancia un objeto de transferencia de datos con los atributos no
     * vacíos.
     *
     * @param Fraccion Matriz de fracciones que representa al problema de
     * programación lineal.
     * @param nombreColumnas Etiquetas que identifican las variables en cada
     * columna.
     * @param nombreFilas Etiqueta que identifica las variables seleccionadas
     * por fila que posee el valor de el "lado derecho".
     */
    public DtoSimplex(AbstractFraccion[][] Fraccion, String[] nombreColumnas, String[] nombreFilas) {
        this.matriz = Fraccion;
        this.nombreColumnas = nombreColumnas;
        this.nombreFilas = nombreFilas;
    }

    /**
     * Instancia un objeto de transferencia de datos con los atributos no
     * vacíos.
     *
     * @param matriz Matriz de fracciones que representa al problema de
     * programación lineal.
     * @param nombreColumnas Etiquetas que identifican las variables en cada
     * columna.
     * @param listaDesigualdades identifica numeracamente el tipo de desigualdad
     * de la restriccion, &lt;=, &gt;= o =
     * @param maximizacion indica si el problema es de maximizacion o
     * minimizacion
     */
    public DtoSimplex(AbstractFraccion[][] matriz, String[] nombreColumnas,
            int[] listaDesigualdades, boolean maximizacion) {
        this.matriz = matriz;
        this.nombreColumnas = nombreColumnas;
        this.listaDesigualdades = listaDesigualdades;
        this.maximizacion = maximizacion;
    }

    /**
     * Instancia un objeto de transferencia de datos con todos los atributos no
     * vacíos.
     *
     * @param Fraccion Matriz de fracciones que representa al problema de
     * programación lineal.
     * @param nombreColumnas Etiquetas que identifican las variables en cada
     * columna.
     * @param nombreFilas Etiqueta que identifica las variables seleccionadas
     * @param pivote Coordenada (fila,columna) que representa la casilla donde
     * se realizara la accion de pivoteo. por fila que posee el valor de el
     * "lado derecho".
     */
    public DtoSimplex(AbstractFraccion[][] Fraccion, String[] nombreColumnas, String[] nombreFilas, Point pivote) {
        this.matriz = Fraccion;
        this.nombreColumnas = nombreColumnas;
        this.nombreFilas = nombreFilas;
        this.coordenadaPivote = pivote;
    }

    /**
     * Constructor privado utilizado para reaalizar un clonado profundo de la
     * instancia.
     *
     * @param matriz Matriz de fracciones que representa al problema de
     * programación lineal.
     * @param nombreColumnas Etiquetas que identifican las variables en cada
     * columna.
     * @param listaDesigualdades identifica numeracamente el tipo de desigualdad
     * de la restriccion, >=, <= o =
     * @param maximizacion Indica el tipo de problema, si es o no de
     * maximizacion.
     * @param variablesBasicas Cantidad de variables basicas.
     * @param variablesHolgura Cantidad de variables de holgura.
     * @param dosFases Representa el tipo de metodo de simplex que se va
     * realizar.
     * @param acotado Indica si el problema esta acotado.
     * @param factible Indica si el problema es factible.
     * @param finalizado Indica si el problema esta finalizado.
     * @param bloqueoDosFases Indica si el problema se encuentra en la primera
     * iteración del metodo simplex de dos fases.
     * @param formatoFraccional Indica el formato de salida de los datos.
     */
    private DtoSimplex(AbstractFraccion[][] matriz, String[] nombreColumnas,
            int[] listaDesigualdades, boolean maximizacion,
            int variablesBasicas, int variablesHolgura, boolean dosFases,
            boolean acotado, boolean factible, boolean finalizado,
            boolean bloqueoDosFases, boolean formatoFraccional, 
            int artificialActual) {
        this.matriz = matriz;
        this.nombreColumnas = nombreColumnas;
        this.listaDesigualdades = listaDesigualdades;
        this.maximizacion = maximizacion;
        this.variablesBasicas = variablesBasicas;
        this.variablesHolgura = variablesHolgura;
        this.bloqueoDosFases = bloqueoDosFases;
        this.acotado = acotado;
        this.dosfases = dosFases;
        this.factible = factible;
        this.finalizado = finalizado;
        this.formatoFraccional = formatoFraccional;
        this.artificialActual = artificialActual;
    }
    
    /**
     * Constructor privado utilizado para reaalizar un clonado profundo de la
     * instancia.
     *
     * @param matriz Matriz de fracciones que representa al problema de
     * programación lineal.
     * @param nombreColumnas Etiquetas que identifican las variables en cada
     * columna.
     * @param nombreFilas Etiquetas que identifican las variables en cada fila.
     * @param listaDesigualdades identifica numeracamente el tipo de desigualdad
     * de la restriccion, >=, <= o =
     * @param maximizacion Indica el tipo de problema, si es o no de
     * maximizacion.
     * @param variablesBasicas Cantidad de variables basicas.
     * @param variablesHolgura Cantidad de variables de holgura.
     * @param dosFases Representa el tipo de metodo de simplex que se va
     * realizar.
     * @param acotado Indica si el problema esta acotado.
     * @param factible Indica si el problema es factible.
     * @param finalizado Indica si el problema esta finalizado.
     * @param bloqueoDosFases Indica si el problema se encuentra en la primera
     * iteración del metodo simplex de dos fases.
     * @param formatoFraccional Indica el formato de salida de los datos.
     * @param coordenadaPivote Punto de la siguiente fila y columna a realizar
     * operación.
     */
    private DtoSimplex(AbstractFraccion[][] matriz, String[] nombreColumnas,
            String[] nombreFilas, int[] listaDesigualdades, boolean maximizacion,
            int variablesBasicas, int variablesHolgura, boolean dosFases,
            boolean acotado, boolean factible, boolean finalizado,
            boolean bloqueoDosFases, boolean formatoFraccional, Point coordenadaPivote,
            int artificialActual) {
        this.matriz = matriz;
        this.nombreColumnas = nombreColumnas;
        this.nombreFilas = nombreFilas;
        this.listaDesigualdades = listaDesigualdades;
        this.maximizacion = maximizacion;
        this.variablesBasicas = variablesBasicas;
        this.variablesHolgura = variablesHolgura;
        this.bloqueoDosFases = bloqueoDosFases;
        this.acotado = acotado;
        this.dosfases = dosFases;
        this.factible = factible;
        this.finalizado = finalizado;
        this.formatoFraccional = formatoFraccional;
        this.coordenadaPivote = new Point(coordenadaPivote.x, coordenadaPivote.y);
        this.artificialActual = artificialActual;
    }

    public AbstractFraccion[][] getMatriz() {
        return matriz;
    }

    public String[] getNombreColumnas() {
        return nombreColumnas;
    }

    public String[] getNombreFilas() {
        return nombreFilas;
    }

    public boolean esMaximización() {
        return maximizacion;
    }

    public boolean esAcotado() {
        return acotado;
    }

    public boolean esFactible() {
        return factible;
    }

    public boolean isMaximizacion() {
        return maximizacion;
    }

    public void setMaximizacion(boolean maximizacion) {
        this.maximizacion = maximizacion;
    }

    public boolean esMatriz() {
        return esMatriz;
    }

    public void setEsMatriz(boolean esMatriz) {
        this.esMatriz = esMatriz;
    }

    public void setAcotado(boolean acotado) {
        this.acotado = acotado;
    }

    public void setFactible(boolean factible) {
        this.factible = factible;
    }

    public void setMatriz(AbstractFraccion[][] matriz) {
        this.matriz = matriz;
    }

    public int[] getListaDesigualdades() {
        return listaDesigualdades;
    }

    public int getVariablesBasicas() {
        return variablesBasicas;
    }

    public void setVariablesBasicas() {
        this.variablesBasicas = matriz[0].length;
    }

    public void setVariablesBasicas(int num) {
        this.variablesBasicas = num;
    }

    public int getVariablesHolgura() {
        return variablesHolgura;
    }

    public void setVariablesHolgura(int variablesHolgura) {
        this.variablesHolgura = variablesHolgura;
    }

    public boolean esDosfases() {
        return dosfases;
    }

    public void setDosfases(boolean dosfases) {
        this.dosfases = dosfases;
    }

    public Point getCoordenadaPivote() {
        return coordenadaPivote;
    }

    public void setCoordenadaPivote(Point coordenadaPivote) {
        this.coordenadaPivote = coordenadaPivote;
    }

    public boolean esFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public void setNombreColumnas(String[] nombreColumnas) {
        this.nombreColumnas = nombreColumnas;
    }

    public void setNombreFilas(String[] nombreFilas) {
        this.nombreFilas = nombreFilas;
    }

    public String getNombreColumna(int indice) {
        return nombreColumnas[indice];
    }

    public void setNombreFila(int indice, String nombreVariable) {
        nombreFilas[indice] = nombreVariable;
    }

    public String getOperaciones() {
        String resultado = "";
        for (int i = 0; i < operaciones.length; i++) {
            resultado += operaciones[i] + "\n";
        }
        return resultado;
    }

    public void setOperaciones(String[] operaciones) {
        this.operaciones = operaciones;
    }

    public boolean esBloqueoDosFases() {
        return bloqueoDosFases;
    }

    public void setBloqueoDosFases(boolean bloqueoDosFases) {
        this.bloqueoDosFases = bloqueoDosFases;
    }

    public boolean esFormatoFraccional() {
        return formatoFraccional;
    }

    public void setFormatoFraccional(boolean formatoFraccional) {
        this.formatoFraccional = formatoFraccional;
    }

    /**
     * Obtiene un objeto identico pero con distintas referencia.
     *
     * @return Nueva referencia del objeto.
     */
    public DtoSimplex clonarProfundo() {
        DtoSimplex resultado = new DtoSimplex(clonarMatriz(), nombreColumnas.clone(), nombreFilas.clone(), listaDesigualdades,
                maximizacion, variablesBasicas, variablesHolgura, dosfases, acotado,
                factible, finalizado, bloqueoDosFases, formatoFraccional, (Point) coordenadaPivote.clone(),
                artificialActual);
        resultado.setEsMatriz(esMatriz);
        return resultado;
    }
    
    /**
     * Obtiene un objeto identico pero con distintas referencia, inerta nulo el
     * valor de coordenada y nombre de filas..
     *
     * @return Nueva referencia del objeto.
     */
    public DtoSimplex clonarSinCompletarProfundo() {
        DtoSimplex resultado = new DtoSimplex(clonarMatriz(), nombreColumnas.clone(),listaDesigualdades,
                maximizacion, variablesBasicas, variablesHolgura, dosfases, acotado,
                factible, finalizado, bloqueoDosFases, formatoFraccional,
                artificialActual);
        resultado.setEsMatriz(esMatriz);
        return resultado;
    }

    /**
     * Clona todos los elementos que pertenecen a la matriz, devolviendo una
     * matriz nueva identica a la anterior, pero con referencia distintas.
     *
     * @return Nueva matriz con distintas referencias.
     */
    private AbstractFraccion[][] clonarMatriz() {
        AbstractFraccion[][] resultado = new AbstractFraccion[matriz.length][matriz[0].length];
        for (int contadorFila = 0; contadorFila < resultado.length; contadorFila++) {
            for (int contadorColumna = 0; contadorColumna < resultado[0].length; contadorColumna++) {
                resultado[contadorFila][contadorColumna] = matriz[contadorFila][contadorColumna].clonar();
            }
        }
        return resultado;
    }

    public String[][] getMatrizString() {
        String[][] resultado = new String[matriz.length][matriz[0].length];
        for (int contadorFila = 0; contadorFila < resultado.length; contadorFila++) {
            for (int contadorColumna = 0; contadorColumna < resultado[0].length; contadorColumna++) {
                resultado[contadorFila][contadorColumna] = matriz[contadorFila][contadorColumna].toString(formatoFraccional);
            }
        }
        return resultado;
    }

    public String toString() {
        AbstractFraccion[][] matriz = getMatriz();
        String resultado = "";
        String lineaHorizontal="";
        String[] arregloFilas = getNombreFilas();
        String[] arregloColumnas = getNombreColumnas();
        String[][] matrizString = new String[matriz.length][matriz[0].length];
        int anchoColumna = 0;
        int anchoTotal = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                String actual = matriz[i][j].toString(formatoFraccional);
                matrizString[i][j]=actual;
                anchoColumna = anchoColumna < actual.length() ? actual.length() : anchoColumna;
            }
        }
        anchoColumna += 4;
        anchoTotal = anchoColumna * (arregloColumnas.length + 2);
        for (int i = 0; i < anchoTotal; i++) {
            lineaHorizontal+="-";
        }
        int columnaActual = 0;
        int filaActual = 0;
        resultado += lineaHorizontal+"\n|"; 
        for (int i = 0; i <= arregloColumnas.length + 1; i++) {
            String variable = i <= arregloColumnas.length ? arregloColumnas[columnaActual++] : "RHS";
            int espacioIzq = (anchoColumna - variable.length()) / 2;
            int espacioDer = espacioIzq + variable.length();
            if (i == 0) {
                espacioIzq = (anchoColumna - 3) / 2;
                espacioDer = espacioIzq + 3;
                for (int j = 0; j < anchoColumna; j++) {
                    if (j < espacioIzq) {
                        resultado += " ";
                    }
                    else if (j >= espacioIzq && j <= espacioDer){
                        resultado += "BVS";
                        j += 3;
                    }
                    else 
                        resultado += " ";
                }
                resultado += "|";
                columnaActual--;
                continue;
            }
            for (int j = 0; j < anchoColumna; j++) {
                if (j < espacioIzq) {
                    resultado += " ";
                }
                else if (j >= espacioIzq && j <= espacioDer){
                    resultado += variable;
                    j += variable.length();
                }
                else 
                    resultado += " ";
            }
            resultado += "|";
        }
        resultado += "\n" + lineaHorizontal + "\n";
        
        for (int i = 0; i < arregloFilas.length; i++) {
            resultado += "|" ;
            for (int j = 0; j < arregloColumnas.length + 2; j++) {
                String variable;
                int espacioIzq;
                int espacioDer;
                if (j == 0) {
                    variable = arregloFilas[filaActual++];
                    espacioIzq = (anchoColumna - variable.length()) / 2;
                    espacioDer = espacioIzq + variable.length();
                    for (int k = 0; k < anchoColumna; k++) {
                        if (k < espacioIzq) {
                            resultado += " ";
                        }
                        else if (k >= espacioIzq && k <= espacioDer){
                            resultado += variable;
                            k += variable.length();
                        }
                        else 
                            resultado += " ";
                    }
                    resultado += "|";
                    continue;
                }
                
                variable = matrizString[i][j-1];
                espacioIzq = (anchoColumna - variable.length()) / 2;
                espacioDer = espacioIzq + variable.length();
                for (int k = 0; k < anchoColumna; k++) {
                    if (k < espacioIzq) {
                        resultado += " ";
                    }
                    else if (k >= espacioIzq && k <= espacioDer){
                        resultado += variable;
                        k += variable.length();
                    }
                    else 
                        resultado += " ";
                }
                resultado += "|"; 
            }
            resultado += "\n";
        }
        return resultado+lineaHorizontal;
    }

    public void setEntradaMatriz(int fila, int columna, int numerador, int denominador) {
        AbstractFraccion nuevaFraccion;
        nuevaFraccion = new Fraccion(numerador, denominador);
        matriz[fila][columna] = nuevaFraccion;
    }

    public int getArtificialActual() {
        return artificialActual;
    }

    public void setArtificialActual(int artificialActual) {
        this.artificialActual = artificialActual;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = "Información: \n" + mensaje;
    }

    public void setListaDesigualdades(int[] listaDesigualdades) {
        this.listaDesigualdades = listaDesigualdades;
    }
}
