
package controlador;

import modelo.SolucionadorSimplex;

/**
 *
 * @author Yordan Jimenez
 */
public class SimplexControlador extends AbstractSimplexControlador{

    public SimplexControlador() {
        this.solucionador = new SolucionadorSimplex();
    }
    
    
}
