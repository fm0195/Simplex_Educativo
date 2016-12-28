package Modelo;

/**
 *
 * @author Yordan Jiménez
 */
public class DtoSimplex {

    private AbstractFraccion Fraccion[][];
    private String nombreColumnas[];
    private String nombreFilas[];

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
        this.Fraccion = Fraccion;
        this.nombreColumnas = nombreColumnas;
        this.nombreFilas = nombreFilas;
    }

    public AbstractFraccion[][] getFraccion() {
        return Fraccion;
    }

    public String[] getNombreColumnas() {
        return nombreColumnas;
    }

    public String[] getNombreFilas() {
        return nombreFilas;
    }
}
