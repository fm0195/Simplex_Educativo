package modelo;

/**
 *
 * @author Yordan Jiménez
 */
public class DtoSimplex {

    private AbstractFraccion[][] matriz;
    private String nombreColumnas[];
    private String nombreFilas[];
    private int listaDesigualdades[];

    /**
     * Instancia un objeto de transferencia de datos con todos los atributos no
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
     * Instancia un objeto de transferencia de datos con todos los atributos no
     * vacíos.
     *
     * @param matriz Matriz de fracciones que representa al problema de
     * programación lineal.
     * @param nombreColumnas Etiquetas que identifican las variables en cada
     * columna.
     * @param listaDesigualdades identifica numeracamente el tipo de desigualdad de
     * la restriccion, >=, <= o =
     */
    public DtoSimplex(AbstractFraccion[][] matriz, String[] nombreColumnas, int[] listaDesigualdades) {
        this.matriz = matriz;
        this.nombreColumnas = nombreColumnas;
        this.listaDesigualdades = listaDesigualdades;
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
}
