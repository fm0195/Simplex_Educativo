package pruebas;

import dto.DtoSimplex;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import modelo.SolucionadorBranchAndBound;
import modelo.parser.Parser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yordan Jiménez
 */
@RunWith(Parameterized.class)
public class SolucionarBranchAndBoundTest {

    DtoSimplex problema;
    String arbol;
    String mensaje;

    public SolucionarBranchAndBoundTest(DtoSimplex problema, String arbol, String mensaje) {
        this.problema = problema;
        this.arbol = arbol;
        this.mensaje = mensaje;
    }

    @Test
    public void test() {
        SolucionadorBranchAndBound solucionador = new SolucionadorBranchAndBound();
        ArrayList<DtoSimplex> pasos = solucionador.solucionar(problema);
        DtoSimplex solucion = pasos.get(pasos.size() - 1);
        boolean correcto = solucion.getMensaje().equals(arbol);
        correcto &= solucion.getSolucion().equals(mensaje);
        assertTrue(correcto);
    }

    @Parameterized.Parameters
    public static Collection parametros() throws IOException {
        Parser parser = new Parser();
        DtoSimplex dto1 = parser.parse("max z = 3 x1 + 4 x2\n"
                + "2 x1 +   x2 <= 6\n"
                + "2 x1 + 3 x2 <= 9");
        DtoSimplex dto2 = parser.parse("min z = x1 + x2\n"
                + " 2 x1 + 2 x2 >= 5\n"
                + "12 x1 + 5 x2 <= 30");
        DtoSimplex dto3 = parser.parse("max z = -1 x1 - 2 x2\n"
                + "3 x1 + 4 x2 <= 20\n"
                + "2 x1 - 1 x2 >= 2");
        DtoSimplex dto4 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1          <= 2\n"
                + "(2)                  1 x2   = 3\n"
                + "(3)         -1 x1 +  1 x2  <= 0");
        DtoSimplex dto5 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1          <= 2\n"
                + "(2)          1 x1 -  1 x2  <= 0");
        String arbol1 = "Problema 1\n"
                + "  z = 51/4\n"
                + "  x1 = 9/4\n"
                + "  x2 = 3/2\n"
                + "  Restricción: No es necesario.\n"
                + "\n"
                + "     Problema 1.1\n"
                + "       z = 23/2\n"
                + "       x1 = 5/2\n"
                + "       x2 = 1\n"
                + "       Restricción: x2 <= 1\n"
                + "       *Problema Acotado\n"
                + "\n"
                + "     Problema 1.2\n"
                + "       z = 25/2\n"
                + "       x1 = 3/2\n"
                + "       x2 = 2\n"
                + "       Restricción: x2 >= 2\n"
                + "\n"
                + "          Problema 1.2.1\n"
                + "            z = 37/3\n"
                + "            x1 = 1\n"
                + "            x2 = 7/3\n"
                + "            Restricción: x1 <= 1\n"
                + "\n"
                + "               Problema 1.2.1.1\n"
                + "                 z = 11\n"
                + "                 x1 = 1\n"
                + "                 x2 = 2\n"
                + "                 Restricción: x2 <= 2\n"
                + "\n"
                + "               Problema 1.2.1.2\n"
                + "                 z = 12\n"
                + "                 x1 = 0\n"
                + "                 x2 = 3\n"
                + "                 Restricción: x2 >= 3\n"
                + "                 *Solución Óptima.\n"
                + "\n"
                + "          Problema 1.2.2\n"
                + "            Restricción: x1 >= 2\n"
                + "            *Problema No Factible\n"
                + "\n"
                + "";
        String mensaje1 = "x2 >= 2\n"
                + "x1 <= 1\n"
                + "x2 >= 3\n"
                + "Valor de las variables:\n"
                + "z = 12\n"
                + "x1 = 0\n"
                + "x2 = 3\n"
                + "";
        String arbol2 = "Problema 1\n"
                + "  z = 5/2\n"
                + "  x1 = 5/2\n"
                + "  x2 = 0\n"
                + "  Restricción: No es necesario.\n"
                + "\n"
                + "     Problema 1.1\n"
                + "       z = 5/2\n"
                + "       x1 = 2\n"
                + "       x2 = 1/2\n"
                + "       Restricción: x1 <= 2\n"
                + "\n"
                + "          Problema 1.1.1\n"
                + "            Restricción: x2 <= 0\n"
                + "            *Problema No Factible\n"
                + "\n"
                + "          Problema 1.1.2\n"
                + "            z = 5/2\n"
                + "            x1 = 3/2\n"
                + "            x2 = 1\n"
                + "            Restricción: x2 >= 1\n"
                + "\n"
                + "               Problema 1.1.2.1\n"
                + "                 z = 5/2\n"
                + "                 x1 = 1\n"
                + "                 x2 = 3/2\n"
                + "                 Restricción: x1 <= 1\n"
                + "\n"
                + "                    Problema 1.1.2.1.1\n"
                + "                      Restricción: x2 <= 1\n"
                + "                      *Problema No Factible\n"
                + "\n"
                + "                    Problema 1.1.2.1.2\n"
                + "                      z = 5/2\n"
                + "                      x1 = 1/2\n"
                + "                      x2 = 2\n"
                + "                      Restricción: x2 >= 2\n"
                + "\n"
                + "                         Problema 1.1.2.1.2.1\n"
                + "                           z = 5/2\n"
                + "                           x1 = 0\n"
                + "                           x2 = 5/2\n"
                + "                           Restricción: x1 <= 0\n"
                + "\n"
                + "                              Problema 1.1.2.1.2.1.1\n"
                + "                                Restricción: x2 <= 2\n"
                + "                                *Problema No Factible\n"
                + "\n"
                + "                              Problema 1.1.2.1.2.1.2\n"
                + "                                z = 3\n"
                + "                                x1 = 0\n"
                + "                                x2 = 3\n"
                + "                                Restricción: x2 >= 3\n"
                + "                                *Solución Óptima.\n"
                + "\n"
                + "                         Problema 1.1.2.1.2.2\n"
                + "                           z = 3\n"
                + "                           x1 = 1\n"
                + "                           x2 = 2\n"
                + "                           Restricción: x1 >= 1\n"
                + "\n"
                + "               Problema 1.1.2.2\n"
                + "                 z = 3\n"
                + "                 x1 = 2\n"
                + "                 x2 = 1\n"
                + "                 Restricción: x1 >= 2\n"
                + "\n"
                + "     Problema 1.2\n"
                + "       Restricción: x1 >= 3\n"
                + "       *Problema No Factible\n"
                + "\n"
                + "";
        String mensaje2 = "x1 <= 2\n"
                + "x2 >= 1\n"
                + "x1 <= 1\n"
                + "x2 >= 2\n"
                + "x1 <= 0\n"
                + "x2 >= 3\n"
                + "Valor de las variables:\n"
                + "z = 3\n"
                + "x1 = 0\n"
                + "x2 = 3\n"
                + "";
        String arbol3 = "Problema 1\n"
                + "  z = -1\n"
                + "  x1 = 1\n"
                + "  x2 = 0\n"
                + "  Restricción: No es necesario.\n"
                + "  *Solución Óptima.\n"
                + "\n"
                + "";
        String mensaje3 = "No es necesario.\n"
                + "Valor de las variables:\n"
                + "z = -1\n"
                + "x1 = 1\n"
                + "x2 = 0\n"
                + "";
        String arbol4 = "Problema 1\n"
                + "  Restricción: No es necesario.\n"
                + "  *Problema No Factible\n"
                + "\n"
                + "";
        String mensaje4 = "El problema no posee una solución entera óptima factible.";
        String arbol5 = "Problema 1\n"
                + "  Restricción: No es necesario.\n"
                + "  *Problema No Factible\n"
                + "\n"
                + "";
        String mensaje5 = "El problema no posee una solución entera óptima factible.";
        return Arrays.asList(new Object[][]{
            {dto1, arbol1, mensaje1},
            {dto2, arbol2, mensaje2},
            {dto3, arbol3, mensaje3},
            {dto4, arbol4, mensaje4},
            {dto5, arbol5, mensaje5}
        });
    }
}
