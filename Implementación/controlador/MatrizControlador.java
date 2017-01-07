/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import modelo.AbstractFraccion;
import dto.DtoSimplex;
import modelo.Fraccion;
import modelo.SolucionadorSimplex;
import modelo.parser.MatrizParser;

/**
 *
 * @author fm010
 */
public class MatrizControlador extends AbstractControlador {

    /**
     * Instancia un nuevo controlador
     */
    public MatrizControlador() {
        this.solucionador = new SolucionadorSimplex();
        this.parser = new MatrizParser();
    }

    @Override
    public ArrayList<DtoSimplex> solucionar(String problema, boolean fraccional) {
        this.problemaOriginal = problema;
        return null;
    }

    /**
     * Indica al solucionador que genere la siguiente iteración del problema de
     * programación lineal cargado.
     *
     * @param problema el string del problema por resolver
     * @param fraccionario indica si el formato numerico es fraccionario o
     * decimal
     */
    @Override
    public void siguientePaso(String problema, boolean fraccionario) {
        DtoSimplex dto = null;
        this.problemaOriginal = problema;
        try {
            dto = parser.parse(problema);
        } catch (Exception ex) {
            vista.menu("Error en el formato de entrada", problema);
            return;
        }
        dto.setFormatoFraccional(fraccionario);
        dto.setOperaciones(solucionador.siguientesOperaciones(dto).getOperaciones().split("\n"));
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
    @Override
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
    }

    /**
     * Indica a la vista que muestre el anterior paso resuelto
     *
     */
    @Override
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
     * Indica al solucionador que genere las siguientes operaciones del problema
     * de programación lineal indicado por parámetro en el punto coordenadas que
     * se encuentra dentro del paso actual.
     *
     * @param coordenadas la coordenada de la entrada seleccionada
     * @return Datos de las siguientes operaciones del problema de programación
     * lineal.
     */
    @Override
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
     * derecho" del problema de programación lineal dentro del paso actual. Se
     * obtiene llamando al método calcular radio del solucionador.
     *
     * @param columna Indice de la columna a generar el radio.
     * @return Arreglo con el valor de los radios.
     */
    @Override
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
     * Genera el valor de los radios entre la columna indicada dentro del punto
     * seleccionado actualmente y el "lado derecho" del problema actual, se
     * obtiene llamando al método calcular radio del solucionador.
     *
     * @return Arreglo con el valor de los radios.
     *
     */
    @Override
    public String[] generarRadios() {
        DtoSimplex dto = listaPasos.get(pasoActual);
        return solucionador.calcularRadio(dto, dto.getCoordenadaPivote().y);
    }

    /**
     * Cambia el valor de una entrada en la matriz de coeficientes, por un nuevo
     * valor en la posición fila y columna indicado.
     *
     * @param fila Indice de la fila dentro de la matriz.
     * @param columna Indice de la columna dentro de la matriz.
     * @param valor Valor del coeficiente a intercambiar.
     */
    @Override
    public void modificarEntradaMatriz(int fila, int columna,
            String valor) {
        AbstractFraccion nuevaFraccion;
        if (valor.contains("/")) {
            String[] split = valor.split("/");
            nuevaFraccion = new Fraccion(Double.valueOf(split[0]), Double.valueOf(split[1]));
        } else {
            nuevaFraccion = new Fraccion(Double.valueOf(valor));
        }
        listaPasos.get(pasoActual).getMatriz()[fila][columna] = nuevaFraccion;
    }

    @Override
    public void setVista(IVista vista) {
        this.vista = vista;
    }

    @Override
    public String generarResumen() {
        String resultado = "";
        for (int i = 0; i <= pasoActual; i++) {
            resultado += listaPasos.get(i).toString() + "\n";
        }
        return resultado;
    }
}
