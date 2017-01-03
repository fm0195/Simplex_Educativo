package controlador;

import java.awt.Point;
import java.util.ArrayList;
import modelo.AbstractFraccion;
import modelo.AbstractSolucionadorSimplex;
import modelo.DtoSimplex;
import modelo.Fraccion;
import modelo.SolucionadorSimplex;
import modelo.parser.IParser;
import vista.PantallaPasoIntermedio;

/**
 *
 * @author Yordan Jiménez
 */
public abstract class AbstractSimplexControlador {

    protected AbstractSolucionadorSimplex solucionador;
    protected IParser parser;
    protected int pasoActual = 0;
    protected ArrayList<DtoSimplex> listaPasos;
    protected PantallaPasoIntermedio vista;

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
    public ArrayList<DtoSimplex> solucionarSimplex(String problema) {
        DtoSimplex dto = parser.parse(problema);
        dto = solucionador.completarProblema(dto);
        listaPasos = solucionador.solucionar(dto);
        pasoActual = listaPasos.size()-1;
        vista.mostrarMatriz(listaPasos.get(pasoActual));
        return listaPasos;
    }

    /**
     * Indica al solucionador, que genera la siguiente iteración del problema de
     * programación lineal cargado.
     * @param problema el string del problema por resolver
     */
    public void siguientePasoSimplex(String problema) {
        listaPasos = solucionarSimplex(problema);
        pasoActual = 0;
        vista.mostrarMatriz(listaPasos.get(pasoActual));
    }
    
    /**
     * Indica al solucionador, que genera la siguiente iteración del problema de
     * programación lineal cargado.
     *
     */
    public void siguientePasoSimplex() {
        if(pasoActual == listaPasos.size()-1)
            listaPasos.add(solucionador.siguientePaso(listaPasos.get(pasoActual)));
        vista.mostrarMatriz(listaPasos.get(++pasoActual));
    }
    
    /**
     * Indica al solucionador, que genera la siguiente iteración del problema de
     * programación lineal indicado por parámetro.
     *
     * @return Datos de la siguiente iteración del problema de programación
     * lineal.
     */
    public void anteriorPasoSimplex() {
        if (pasoActual < listaPasos.size() && pasoActual > 0) {
            vista.mostrarMatriz(listaPasos.get(--pasoActual));
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
        listaPasos.get(pasoActual).setCoordenadaPivote(coordenadas);
        return solucionador.siguientesOperaciones(listaPasos.get(pasoActual)).getOperaciones();
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
        String[] resultado = solucionador.calcularRadio(listaPasos.get(pasoActual), columna);
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
        AbstractFraccion nuevaFraccion;
        if (valor.contains("/")) {
            String[] split = valor.split("/");
            nuevaFraccion = new Fraccion(Double.valueOf(split[0]), Double.valueOf(split[1]));
        }else {
            nuevaFraccion = new Fraccion(Double.valueOf(valor));
        }
        listaPasos.get(pasoActual).getMatriz()[fila][columna] = nuevaFraccion;
    }

    public void setVista(PantallaPasoIntermedio vista) {
        this.vista = vista;
    }
    
    public String generarResumen() {
        String resultado = "";
        for (int i = 0; i <= pasoActual; i++) {
            resultado += listaPasos.get(i).toString();
        }
        return resultado;
    }
    
}
