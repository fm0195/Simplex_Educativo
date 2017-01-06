package controlador;

import java.awt.Point;
import java.util.ArrayList;
import modelo.AbstractSolucionadorSimplex;
import dto.DtoSimplex;
import modelo.parser.IParser;

/**
 *
 * @author Yordan Jiménez
 */
public abstract class AbstractControlador {

    protected AbstractSolucionadorSimplex solucionador;
    protected IParser parser;
    protected int pasoActual = 0;
    protected ArrayList<DtoSimplex> listaPasos;
    protected IVista vista;
    protected String problemaOriginal;

    /**
     * Indica al solucionador de problemas Simplex, que realice todas las
     * iteraciones para solucionar el problema de programación lineal indicado
     * por parámetro.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
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
        pasoActual = listaPasos.size()-1;
        vista.mostrarMatriz(listaPasos.get(pasoActual));
        if (!listaPasos.get(pasoActual).esFactible()) {
            vista.mostrarMensajeError("El problema no es factible. ", "Infactibilidad");
        }
        else{
            if (!listaPasos.get(pasoActual).esAcotado()) {
                vista.mostrarMensajeError("El problema no esta acotado. ", "No acotado");
            }else{
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
     * @param problema el string del problema por resolver
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
        if(pasoActual == listaPasos.size()-1){
            listaPasos.add(siguientePaso);
        }
        else{
            listaPasos.set(pasoActual+1, siguientePaso);
        }
        pasoActual++;
        vista.mostrarMatriz(listaPasos.get(pasoActual));
        
        if (!listaPasos.get(pasoActual).esFactible()) {
            vista.mostrarMensajeError("El problema no es factible. ", "Infactibilidad");
        }
        else{
            if (!listaPasos.get(pasoActual).esAcotado()) {
                vista.mostrarMensajeError("El problema no esta acotado. ", "No acotado");
            }else{
                if (listaPasos.get(pasoActual).esFinalizado()) {
                    vista.mostrarMensajeInformacion("Problema finalizado. \n" + listaPasos.get(pasoActual).getSolucion(), "Finalizado");
                }
            }
        }
    }
    
    /**
     * Indica al solucionador, que genera la siguiente iteración del problema de
     * programación lineal indicado por parámetro.
     *
     * @return Datos de la siguiente iteración del problema de programación
     * lineal.
     */
    public void anteriorPaso() {
        if (pasoActual < listaPasos.size() && pasoActual > 0) {
            DtoSimplex anterior = listaPasos.get(pasoActual-1);
            DtoSimplex actual = listaPasos.get(pasoActual);
            DtoSimplex siguiente = pasoActual+1 < listaPasos.size() ? listaPasos.get(pasoActual+1) : null;
            DtoSimplex rem = listaPasos.remove(pasoActual--);
            vista.mostrarMatriz(listaPasos.get(pasoActual));
        } else {
            vista.menu(null, problemaOriginal);
        }
    }

    /**
     * Indica al solucionador, que genera las siguientes operaciones del
     * problema de programación lineal indicado por parámetro, en el punto(fila,
     * columna) que se encuentra dentro del objeto de transferencia.
     *
     * @param coordenadas la coordenada de la entrada seleccionada
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @return Datos de las siguientes operaciones del problema de programación
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
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @param columna Indice de la columna a generar el radio.
     * @return Arreglo con el valor de los radios.
     */
    public String[] generarRadios(int columna) {   
        String[] resultado;
        if(columna == -1){
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
            String maxDouble = String.format("%.2f",Double.MAX_VALUE);
            String actual = resultado[i];
            if (resultado[i].compareTo(String.valueOf(Integer.MAX_VALUE)) == 0 || resultado[i].compareTo(String.format("%.2f",Double.MAX_VALUE)) == 0) {
                resultado[i] = "oo";
            }
        }
        return resultado;
    }

    /**
     * Genera el valor de los radios entre la columna indicada dentro del punto
     * dentro del objeto y el "lado derecho" del problema de programación lineal
     * dentro del objeto de transferencia, se obtiene llamando al método
     * calcular radio del solucionador.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @return Arreglo con el valor de los radios.
     *
     */
    public String[] generarRadios() {
        DtoSimplex dto = listaPasos.get(pasoActual);
        return solucionador.calcularRadio(dto, dto.getCoordenadaPivote().y);
    }

    /**
     * Cambia el valor de una entrada en la matriz de coeficientes, por un nuevo
     * valor en la posición fila y columna indicado.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @param fila Indice de la fila dentro de la matriz.
     * @param columna Indice de la columna dentro de la matriz.
     * @param valor Valor del coeficiente a intercambiar.
     */
    public void modificarEntradaMatriz(int fila, int columna,
            String valor) {
        double numerador;
        double denominador = 1;
        if (valor.contains("/")) {
            String[] split = valor.split("/");
            numerador = Double.valueOf(split[0]);
            denominador = Double.valueOf(split[1]);
        }else {
            numerador = Double.valueOf(valor);
        }
        listaPasos.get(pasoActual).setEntradaMatriz(fila, columna, numerador, denominador);
    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }
    
    public String generarResumen() {
        String resultado = "";
        for (int i = 0; i <= pasoActual; i++) {
            resultado += listaPasos.get(i).toString() + "\n";
        }
        return resultado;
    }
}
