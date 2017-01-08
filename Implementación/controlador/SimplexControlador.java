package controlador;

import modelo.SolucionadorSimplex;
import modelo.parser.SimplexParser;

/**
 *
 * @author Yordan Jimenez
 */
public class SimplexControlador extends AbstractControlador {

    public SimplexControlador() {
        this.solucionador = new SolucionadorSimplex();
        this.parser = new SimplexParser();
    }
}
