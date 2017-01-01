package controlador;

import java.util.ArrayList;
import modelo.AbstractFraccion;
import modelo.AbstractSolucionadorSimplex;
import modelo.DtoSimplex;

/**
 *
 * @author Yordan Jiménez
 */
public abstract class AbstractSimplexControlador {

    protected AbstractSolucionadorSimplex solucionador;

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
    public ArrayList<DtoSimplex> solucionarSimplex(DtoSimplex dto) {
        dto = solucionador.completarProblema(dto);
        return solucionador.solucionar(dto);
    }

    /**
     * Indica al solucionador, que genera la siguiente iteración del problema de
     * programación lineal indicado por parámetro.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @return Datos de la siguiente iteración del problema de programación
     * lineal.
     */
    public DtoSimplex pasoSimplex(DtoSimplex dto) {
        return solucionador.siguientePaso(dto);
    }

    /**
     * Indica al solucionador, que genera la primera iteración del problema de
     * programación lineal indicado por parámetro, agrega las variables de
     * holgura y artificiales necesarias.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @return Datos de la siguiente iteración luego de completar el problema de
     * programación lineal.
     */
    public DtoSimplex completarProblema(DtoSimplex dto) {
        return solucionador.completarProblema(dto);
    }

    /**
     * Indica al solucionador, que genera las siguientes operaciones del
     * problema de programación lineal indicado por parámetro, en el punto(fila,
     * columna) que se encuentra dentro del objeto de transferencia.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @return Datos de las siguientes operaciones del problema de programación
     * lineal.
     */
    public DtoSimplex siguientesOperaciones(DtoSimplex dto) {
        return solucionador.siguientesOperaciones(dto);
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
    public AbstractFraccion[] generarRadios(DtoSimplex dto, int columna) {
        return solucionador.calcularRadio(dto, columna);
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
    public AbstractFraccion[] generarRadios(DtoSimplex dto) {
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
     * @return Datos  del problema de programación lineal con la entrada en la matriz
     * intercambiada..
     */
    public DtoSimplex modificarEntradaMatriz(DtoSimplex dto, int fila, int columna,
            AbstractFraccion valor) {
        dto.getMatriz()[fila][columna] = valor;
        return dto;
    }
}
