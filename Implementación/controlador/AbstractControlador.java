package controlador;

import java.awt.Point;
import java.util.ArrayList;
import modelo.AbstractSolucionadorSimplex;
import dto.DtoSimplex;
import modelo.parser.IParser;
import modelo.parser.sym;

/**
 *
 * @author Yordan Jiménez
 */
public abstract class AbstractControlador {

    /**
     * El solucionador de problemas con el algoritmo simplex.
     */
    protected AbstractSolucionadorSimplex solucionador;
    /**
     * Parser para analizar una cadena de texto que represente un problema
     * lineal y retornar un Dto con sus datos
     */
    protected IParser parser;
    /**
     * Indice del paso actual para acceder a la lista de pasos
     */
    protected int pasoActual = 0;
    /**
     * Lista con todos los pasos resueltos hasta el momento.
     */
    protected ArrayList<DtoSimplex> listaPasos;
    /**
     * La vista donde se mostrara la matriz del paso actual
     */
    protected IVista vista;
    /**
     * La cadena de texto del problema original.
     */
    protected String problemaOriginal;

    /**
     * Indica al solucionador de problemas Simplex, que realice todas las
     * iteraciones para solucionar el problema de programación lineal indicado
     * por parámetro.
     *
     * @param problema Representacion textual del problema de programacion
     * lineal.
     * @param fraccional representacion fraccionaria o decimal.
     * @return Arreglo que contiene todas las iteraciones del problema de
     * programación lineal.
     */
    public ArrayList<DtoSimplex> solucionar(String problema, boolean fraccional) {
        DtoSimplex dto = null;
        this.problemaOriginal = problema;
        try {
            dto = parser.parse(problema);
        } catch (Exception ex) {
            vista.menu("Error en el formato de entrada.", problemaOriginal);
            return null;
        }
        dto.setFormatoFraccional(fraccional);
        dto = solucionador.completarProblema(dto);
        listaPasos = solucionador.solucionar(dto);
        pasoActual = listaPasos.size() - 1;
        vista.mostrarMatriz(listaPasos.get(pasoActual));
        if (!listaPasos.get(pasoActual).esFactible()) {
            vista.mostrarMensajeError("El problema no es factible. ", "Infactibilidad");
        } else {
            if (!listaPasos.get(pasoActual).esAcotado()) {
                vista.mostrarMensajeError("El problema no esta acotado. ", "No acotado");
            } else {
                if (listaPasos.get(pasoActual).esFinalizado()) {
                    vista.mostrarMensajeInformacion("Problema finalizado. \n" + listaPasos.get(pasoActual).getSolucion(), "Finalizado");
                }
            }
        }
        return listaPasos;
    }

    /**
     * Indica al solucionador, que genera la siguiente iteración del problema de
     * programación lineal cargado.
     *
     * @param problema el string del problema por resolver
     * @param fraccional formato de salida fraccional o decimal
     */
    public void siguientePaso(String problema, boolean fraccional) {
        DtoSimplex dto = null;
        this.problemaOriginal = problema;
        try {
            dto = parser.parse(problema);
        } catch (Exception ex) {
            vista.menu("Error en el formato de entrada.", problemaOriginal);
            return;
        }
        dto = solucionador.completarProblema(dto);
        dto.setFormatoFraccional(fraccional);
        pasoActual = 0;
        listaPasos = new ArrayList<>();
        listaPasos.add(dto);
        vista.mostrarMatriz(listaPasos.get(pasoActual));
    }

    /**
     * Indica al solucionador, que genera la siguiente iteración del problema de
     * programación lineal cargado.
     *
     */
    public void siguientePaso() {
        DtoSimplex problemaActual = listaPasos.get(pasoActual).clonarProfundo();
        DtoSimplex siguientePaso = solucionador.siguientePaso(problemaActual);
        if (pasoActual == listaPasos.size() - 1) {
            listaPasos.add(siguientePaso);
        } else {
            listaPasos.set(pasoActual + 1, siguientePaso);
        }
        pasoActual++;
        vista.mostrarMatriz(listaPasos.get(pasoActual));

        if (!listaPasos.get(pasoActual).esFactible()) {
            vista.mostrarMensajeError("El problema no es factible. ", "Infactibilidad");
        } else {
            if (!listaPasos.get(pasoActual).esAcotado()) {
                vista.mostrarMensajeError("El problema no esta acotado. ", "No acotado");
            } else {
                if (listaPasos.get(pasoActual).esFinalizado()) {
                    vista.mostrarMensajeInformacion("Problema finalizado. \n" + listaPasos.get(pasoActual).getSolucion(), "Finalizado");
                }
            }
        }
    }

    /**
     * Indica al controlador que muestre en la vista el paso anterior
     *
     */
    public void anteriorPaso() {
        if (pasoActual < listaPasos.size() && pasoActual > 0) {
            DtoSimplex anterior = listaPasos.get(pasoActual - 1);
            DtoSimplex actual = listaPasos.get(pasoActual);
            DtoSimplex siguiente = pasoActual + 1 < listaPasos.size() ? listaPasos.get(pasoActual + 1) : null;
            DtoSimplex rem = listaPasos.remove(pasoActual--);
            vista.mostrarMatriz(listaPasos.get(pasoActual));
        } else {
            vista.menu(null, problemaOriginal);
        }
    }

    /**
     * Indica al solucionadore que genere las siguientes operaciones del
     * problema de programación lineal actual, en el punto coordenadas
     *
     * @param coordenadas la coordenada de la entrada seleccionada
     * @return Texto de las siguientes operaciones del problema de programación
     * lineal.
     */
    public String siguientesOperaciones(Point coordenadas) {
        String resultado = listaPasos.get(pasoActual).getOperaciones();
        if (!listaPasos.get(pasoActual).esBloqueoDosFases()) {
            listaPasos.get(pasoActual).setCoordenadaPivote(coordenadas);
            resultado = solucionador.siguientesOperaciones(listaPasos.get(pasoActual)).getOperaciones();
        }
        return resultado;
    }

    /**
     * Genera el valor de los radios entre la columna indicada y el "lado
     * derecho" del problema de programación lineal dentro del objeto de
     * transferencia, se obtiene llamando al método calcular radio del
     * solucionador.
     *
     * @param columna Indice de la columna a generar el radio.
     * @return Arreglo con el valor de los radios.
     */
    public String[] generarRadios(int columna) {
        String[] resultado;
        if (columna == -1) {
            int cantRadios = solucionador.calcularRadio(listaPasos.get(pasoActual), 0).length;
            resultado = new String[cantRadios];
            for (int i = 0; i < cantRadios; i++) {
                resultado[i] = "-";
            }
            return resultado;
        }
        resultado = solucionador.calcularRadio(listaPasos.get(pasoActual), columna);

        for (int i = 0; i < resultado.length; i++) {
            if (resultado[i].compareTo(String.valueOf(Integer.MIN_VALUE)) == 0) {
                resultado[i] = "-oo";
            }
            if (resultado[i].compareTo(String.valueOf(Integer.MAX_VALUE)) == 0) {
                resultado[i] = "oo";
            }
        }
        return resultado;
    }

    /**
     * Genera el valor textual de los radios entre la columna indicada del paso
     * actual el "lado derecho" del problema de programación lineal dentro del
     * objeto de transferencia, se obtiene llamando al método calcular radio del
     * solucionador.
     *
     * @return Arreglo con el valor de los radios.
     *
     */
    public String[] generarRadios() {
        DtoSimplex dto = listaPasos.get(pasoActual);
        return solucionador.calcularRadio(dto, dto.getCoordenadaPivote().y);
    }

    /**
     * Cambia el valor de una entrada en la matriz de coeficientes actual, por
     * un nuevo valor en la posición fila y columna indicado.
     *
     * @param fila Indice de la fila dentro de la matriz.
     * @param columna Indice de la columna dentro de la matriz.
     * @param valor Valor del coeficiente a intercambiar.
     */
    public void modificarEntradaMatriz(int fila, int columna,
            String valor) {
        int numerador;
        int denominador = 1;
        if (valor.contains("/")) {
            String[] split = valor.split("/");
            numerador = Integer.valueOf(split[0]);
            denominador = Integer.valueOf(split[1]);
        } else {
            numerador = Integer.valueOf(valor);
        }
        listaPasos.get(pasoActual).setEntradaMatriz(fila, columna, numerador, denominador);
    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }

    /**
     * Genera una cadena de texto con las tablas obtenidas en todos los pasos
     * seguidos para llegar hasta el paso actual.
     *
     * @return representacion en texto de las matrices de los pasos anteriores y
     * el actual
     */
    public String generarResumen() {
        String resultado = "";
        for (int i = 0; i <= pasoActual; i++) {
            resultado += listaPasos.get(i).toString() + "\n";
        }
        return resultado;
    }
    
    /**
     * Agrega una restricción &gt;=, &lt= ó = al problema actual. 
     *
     * @param tipo valor numérico para identificar el tipo de restricción agregada
     */
    public void agregarRestriccion(int tipo){
        DtoSimplex actual = listaPasos.get(pasoActual);
        if (!actual.esDosfases() && (tipo == sym.MAYORIGUAL || tipo == sym.IGUAL)) {
            vista.mostrarMensajeError("No puede agregar esta restricción en un problema de una fase.", "Problema de una fase");
            return;
        }
        if (pasoActual != 0) {
            vista.mostrarMensajeError("Solamente puede agregar restricciones al inicio del algoritmo.", "Problema iniciado");
            return;
        }
        DtoSimplex resultado = solucionador.agregarRestriccion(listaPasos.get(pasoActual), tipo);
        listaPasos.set(pasoActual, resultado);
        vista.mostrarMatriz(resultado);
    }
}
