package modelo;

import dto.DtoSimplex;
import java.util.ArrayList;

/**
 *
 * @author Yordan Jiménez
 */
public abstract class AbstractSolucionadorSimplex {

    /**
     * Constructor vacío, no modifica ningún elemento.
     */
    public AbstractSolucionadorSimplex() {
    }

    /**
     * Obtiene la solución directa por medio del método simplex, de un problema
     * de programación lineal.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * solucionar.
     * @return Arreglo de objetos de tranferencia con los datos de todas las
     * iteraciones utilizadas de la solución del problema de programación lineal
     * indicado.
     */
    public abstract ArrayList<DtoSimplex> solucionar(DtoSimplex dto);

    /**
     * Obtiene la siguiente iteración de un problema de programación lineal
     * indicado.
     *
     * @param dto Contiene los datos del problema de programación lineal a
     * generar la siguiente iteracción.
     * @return Objeto de transferencia de datos con los datos de la siguiente
     * iteración.
     */
    public abstract DtoSimplex siguientePaso(DtoSimplex dto);

    /**
     * Calcula el radio de la división del lado derecho de un problema de
     * programación lineal y una columna del mismo.
     *
     * @param dto el objeto de transferencia con la matriz actual
     * @param columna Valor entero que indica la columna por generar el radio
     * @return Arreglo de fracciones que representan los radios por cada fila
     * del problema de programación lineal.
     */
    public abstract String[] calcularRadio(DtoSimplex dto, int columna);

    /**
     * Agrega las variables artificiales y de holgura a la matriz de
     * coeficientes, además cambia el signo del "lado derecho" y los
     * coeficientes de una fila si el valor del "lado derecho" es negativo.
     *
     * @param dto Objeto de tranferencia de datos con los datos del problema de
     * programación lineal.
     * @return Objeto de tranferencia de datos con las variables de holgura y
     * artificiales agregadas.
     */
    public abstract DtoSimplex completarProblema(DtoSimplex dto);

    /**
     * Genera un string almacenado dentro del objeto de tranferencia de datos,
     * que indica cuales son las siguientes operaciones filas a realizar.
     *
     * @param dto Objeto de tranferencia de datos con los datos del problema de
     * programación lineal.
     * @return Objeto de tranferencia de datos con las operaciones siguientes.
     */
    public abstract DtoSimplex siguientesOperaciones(DtoSimplex dto);

    /**
     * Agrega una restricción &gt;=, &lt;= ó = al problema pasado por parametro.
     *
     * @param dto el objeto de transferencia de datos con el problema
     * @param tipo valor numérico para identificar el tipo de restricción
     * agregada
     * @return dto con el problema actualizado
     */
    public abstract DtoSimplex agregarRestriccion(DtoSimplex dto, int tipo);

    /**
     * Agrega un fila al Dto pasado por parámetro en la posición también
     * indicada por parametro.
     *
     * @param dto el DTO que será modificado.
     * @param posicion la posición en la matriz donde se ingresará la nueva fila
     * @return el nuevo DTO con la fila agregada.
     */
    public abstract DtoSimplex agregarFila(DtoSimplex dto, int posicion);

    /**
     * Agrega una columna al Dto pasado por parámetro en la posición también
     * indicada por parametro.
     *
     * @param dto el DTO que será modificado.
     * @param posicion la posición en la matriz donde se ingresará la nueva
     * columna
     * @return el nuevo DTO con la columna agregada.
     */
    public abstract DtoSimplex agregarColumna(DtoSimplex dto, int posicion);

}
