package controlador;

import modelo.SolucionadorSimplex;
import modelo.parser.SimplexParser;

/**
 *
 * @author Yordan Jimenez
 */
public class SimplexControlador extends AbstractControlador {

    /**
     * Instancia el controlador que resuelve problemas lineales por medio del
     * algoritmo Simplex.
     */
    public SimplexControlador() {
        this.solucionador = new SolucionadorSimplex();
        this.parser = new SimplexParser();
    }
}
