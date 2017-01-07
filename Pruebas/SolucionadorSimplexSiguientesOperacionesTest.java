/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import modelo.AbstractFraccion;
import dto.DtoSimplex;
import modelo.Fraccion;
import modelo.SolucionadorSimplex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yordan Jiménez
 */
@RunWith(Parameterized.class)
public class SolucionadorSimplexSiguientesOperacionesTest {

    DtoSimplex problema;
    String operaciones;

    public SolucionadorSimplexSiguientesOperacionesTest(DtoSimplex problema, String resultado) {
        this.problema = problema;
        this.operaciones = resultado;
    }

    @Test
    public void testSolucionar() {
        SolucionadorSimplex s = new SolucionadorSimplex();
        DtoSimplex solucionGenerada = s.siguientesOperaciones(problema);
        assertTrue(solucionGenerada.getOperaciones().equals(operaciones));
    }

    @Parameterized.Parameters
    public static Collection parametros() throws IOException {

        AbstractFraccion[][] sol22 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(-2),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(-2)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(2),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(3),
                new Fraccion(4),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(20)
            },
            new AbstractFraccion[]{
                new Fraccion(2),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(2)
            }
        };

        DtoSimplex dtoRes22 = new DtoSimplex(sol22,
                new String[]{"x1", "x2", "s3", "s4", "a5"},
                new String[]{"-w", "z", "s3", "a5"});
        dtoRes22.setAcotado(true);
        dtoRes22.setBloqueoDosFases(false);
        dtoRes22.setFactible(true);
        dtoRes22.setDosfases(true);
        dtoRes22.setFinalizado(false);
        dtoRes22.setCoordenadaPivote(new Point(0, 3));
        //dtoRes22.setOperaciones(new String[]{"1/2 * F2 -> F2", "2 * F2 + F0' -> F0'",
        //    "-1 * F2 + F0 -> F0", "-3 * F2 + F1 -> F1"});
        dtoRes22.setArtificialActual(5);
        dtoRes22.setVariablesBasicas(3);
        dtoRes22.setVariablesHolgura(2);
        dtoRes22.setMensaje("Primera etapa de las dos fases, se eliminan los 1's de las variables artificiales");

        /*tercer problema*/
        AbstractFraccion[][] sol33 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(-3)
            },
            new AbstractFraccion[]{
                new Fraccion(-25),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(10),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(3)
            },
            new AbstractFraccion[]{
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion()
            },};

        DtoSimplex dtoRes33 = new DtoSimplex(sol33,
                new String[]{"x1", "x2", "s3", "s4", "a5"},
                new String[]{"-w", "z", "s3", "a5", "x2"});
        dtoRes33.setAcotado(true);
        dtoRes33.setBloqueoDosFases(false);
        dtoRes33.setFactible(true);
        dtoRes33.setDosfases(true);
        dtoRes33.setFinalizado(false);
        dtoRes33.setCoordenadaPivote(new Point(0, 2));
        //dtoRes33.setOperaciones(new String[]{"1 * F1 -> F1", "1 * F1 + F0' -> F0'",
        //    "25 * F1 + F0 -> F0", "-1 * F1 + F2 -> F2", "1 * F1 + F3 -> F3"});
        dtoRes33.setArtificialActual(5);
        dtoRes33.setVariablesBasicas(3);
        dtoRes33.setVariablesHolgura(2);
        dtoRes33.setMensaje("Se realizaron las operaciones fila indicadas en el paso anterior.");

        AbstractFraccion[][] sol42 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(0),
                new Fraccion(-10),
                new Fraccion(15),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(30)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(3)
            },
            new AbstractFraccion[]{
                new Fraccion(0),
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(2)
            }
        };
        DtoSimplex dtoRes42 = new DtoSimplex(sol42,
                new String[]{"x1", "x2", "s3", "s4", "s5"},
                new String[]{"z", "x1", "s4", "s5"});
        dtoRes42.setAcotado(true);
        dtoRes42.setBloqueoDosFases(false);
        dtoRes42.setFactible(true);
        dtoRes42.setDosfases(false);
        dtoRes42.setFinalizado(false);
        dtoRes42.setCoordenadaPivote(new Point(1, 3));
        //dtoRes42.setOperaciones(new String[]{"1 * F3 -> F3", "10 * F3 + F0 -> F0",
        //    "0 * F3 + F1 -> F1", "-1 * F3 + F2 -> F2"});
        dtoRes42.setArtificialActual(0);
        dtoRes42.setVariablesBasicas(3);
        dtoRes42.setVariablesHolgura(3);
        dtoRes42.setMensaje("Operaciones fila realizadas.");

        AbstractFraccion[][] sol52 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(-25),
                new Fraccion(),
                new Fraccion(15),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion()
            },};

        AbstractFraccion[][] sol53 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(25),
                new Fraccion(-10),
                new Fraccion(50)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(2)
            },};

        DtoSimplex dtoRes52 = new DtoSimplex(sol52,
                new String[]{"x1", "x2", "s3", "s4"},
                new String[]{"z", "s3", "x1"});
        dtoRes52.setAcotado(true);
        dtoRes52.setBloqueoDosFases(false);
        dtoRes52.setFactible(true);
        dtoRes52.setDosfases(false);
        dtoRes52.setFinalizado(false);
        dtoRes52.setCoordenadaPivote(new Point(1, 1));
        //dtoRes52.setOperaciones(new String[]{"1 * F1 -> F1", "25 * F1 + F0 -> F0",
        //   "1 * F1 + F2 -> F2"});
        dtoRes52.setArtificialActual(0);
        dtoRes52.setVariablesBasicas(3);
        dtoRes52.setVariablesHolgura(2);
        dtoRes52.setMensaje("Operaciones fila realizadas.");

        DtoSimplex dtoRes53 = new DtoSimplex(sol53,
                new String[]{"x1", "x2", "s3", "s4"},
                new String[]{"z", "x2", "x1"});
        dtoRes53.setAcotado(true);
        dtoRes53.setBloqueoDosFases(false);
        dtoRes53.setFactible(true);
        dtoRes53.setDosfases(false);
        dtoRes53.setFinalizado(false);
        dtoRes53.setCoordenadaPivote(new Point(3, 1));
        dtoRes53.setOperaciones(new String[]{"-1 * F1 -> F1", "10 * F1 + F0 -> F0",
            "0 * F1 + F2 -> F2"});
        dtoRes53.setArtificialActual(0);
        dtoRes53.setVariablesBasicas(3);
        dtoRes53.setVariablesHolgura(2);
        dtoRes53.setMensaje("Operaciones fila realizadas.");
        return Arrays.asList(new Object[][]{
            {dtoRes22, "1/2 * F2 -> F2\n2 * F2 + F0' -> F0'\n-1 * F2 + F0 -> F0\n-3 * F2 + F1 -> F1\n"},
            {dtoRes33, "1 * F1 -> F1\n1 * F1 + F0' -> F0'\n25 * F1 + F0 -> F0\n-1 * F1 + F2 -> F2\n1 * F1 + F3 -> F3\n"},
            {dtoRes42, "1 * F3 -> F3\n10 * F3 + F0 -> F0\n0 * F3 + F1 -> F1\n-1 * F3 + F2 -> F2\n"},
            {dtoRes52, "1 * F1 -> F1\n25 * F1 + F0 -> F0\n1 * F1 + F2 -> F2\n"},
            {dtoRes53, "-1 * F1 -> F1\n10 * F1 + F0 -> F0\n0 * F1 + F2 -> F2\n"}
        });
    }

}
