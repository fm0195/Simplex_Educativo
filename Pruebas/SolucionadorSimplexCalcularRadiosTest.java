/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import modelo.AbstractFraccion;
import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import dto.DtoSimplex;
import modelo.Fraccion;
import modelo.SolucionadorSimplex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author Yordan Jiménez
 */
@RunWith(Parameterized.class)
public class SolucionadorSimplexCalcularRadiosTest {

    DtoSimplex problema;
    String[] resultado;
    int columna;

    public SolucionadorSimplexCalcularRadiosTest(DtoSimplex problema, String[] resultado, int columna) {
        this.problema = problema;
        this.resultado = resultado;
        this.columna = columna;
    }

    @Test
    public void testSolucionar() {
        SolucionadorSimplex s = new SolucionadorSimplex();
        String[] solucionGenerada = s.calcularRadio(problema, columna);
        if (solucionGenerada.length != resultado.length) {
            fail("El solucionador no ha brindado un solución con la cantidad correcta"
                    + "de elementos");
        }
        boolean res = UtilPruebas.arreglosIguales(solucionGenerada, resultado);
        if(!res)
            res = false;
        assertTrue(UtilPruebas.arreglosIguales(solucionGenerada, resultado));
    }

    @Parameterized.Parameters
    public static Collection parametros() throws IOException {
        AbstractFraccion[][] dtoSol11 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4),
                new Fraccion(10),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(3),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(4)
            },
            new AbstractFraccion[]{
                new Fraccion(5),
                new Fraccion(6),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(7)
            }
        };

        AbstractFraccion[][] dtoSol12 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(-2)
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4),
                new Fraccion(10),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(3),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(4)
            },
            new AbstractFraccion[]{
                new Fraccion(5),
                new Fraccion(6),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(7)
            }
        };

        AbstractFraccion[][] dtoSol13 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(-6),
                new Fraccion(-6),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-9)
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4),
                new Fraccion(10),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(3),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(4)
            },
            new AbstractFraccion[]{
                new Fraccion(5),
                new Fraccion(6),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(7)
            }
        };

        AbstractFraccion[][] dtoSol14 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(6, 5),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(6, 5),
                new Fraccion(-3, 5)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(11, 2),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-3, 4),
                new Fraccion(-21, 4)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(-6, 5),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(-1, 5),
                new Fraccion(3, 5)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(3),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(4)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(6, 5),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1, 5),
                new Fraccion(7, 5)
            }
        };
        DtoSimplex dtoRes11 = new DtoSimplex(dtoSol11,
                new String[]{"x1", "x2", "x4", "x5", "x6", "s6", "s7", "a8", "a9"},
                new String[]{"-w", "z", "a8", "s6", "a9"});
        dtoRes11.setAcotado(true);
        dtoRes11.setBloqueoDosFases(true);
        dtoRes11.setFactible(true);
        dtoRes11.setDosfases(true);
        dtoRes11.setFinalizado(false);
        dtoRes11.setCoordenadaPivote(new Point(7, 2));
        dtoRes11.setOperaciones(new String[]{"-1 F1 + F0' -> F0'"});
        dtoRes11.setArtificialActual(7);
        dtoRes11.setVariablesBasicas(6);
        dtoRes11.setVariablesHolgura(2);
        dtoRes11.setMensaje("Agregada fila -w, holguras 's' y artificiales 'a'.");

        DtoSimplex dtoRes12 = new DtoSimplex(dtoSol12,
                new String[]{"x1", "x2", "x4", "x5", "x6", "s6", "s7", "a8", "a9"},
                new String[]{"-w", "z", "a8", "s6", "a9"});
        dtoRes12.setAcotado(true);
        dtoRes12.setBloqueoDosFases(true);
        dtoRes12.setFactible(true);
        dtoRes12.setDosfases(true);
        dtoRes12.setFinalizado(false);
        dtoRes12.setCoordenadaPivote(new Point(8, 4));
        dtoRes12.setOperaciones(new String[]{"-1 F3 + F0' -> F0'"});
        dtoRes12.setArtificialActual(8);
        dtoRes12.setVariablesBasicas(6);
        dtoRes12.setVariablesHolgura(2);
        dtoRes12.setMensaje("Primera etapa de las dos fases, se eliminan los 1's de las variables artificiales");

        DtoSimplex dtoRes13 = new DtoSimplex(dtoSol13,
                new String[]{"x1", "x2", "x4", "x5", "x6", "s6", "s7", "a8", "a9"},
                new String[]{"-w", "z", "a8", "s6", "a9"});
        dtoRes13.setAcotado(true);
        dtoRes13.setBloqueoDosFases(false);
        dtoRes13.setFactible(true);
        dtoRes13.setDosfases(true);
        dtoRes13.setFinalizado(false);
        dtoRes13.setCoordenadaPivote(new Point(0, 4));
        dtoRes13.setOperaciones(new String[]{"1/5 * F3 -> F3", "6 * F3 + F0' -> F0'",
            "-15/4 * F3 + F0 -> F0", "-1 * F3 + F1 -> F1", "0 * F3 + F2 -> F2"});
        dtoRes13.setArtificialActual(9);
        dtoRes13.setVariablesBasicas(6);
        dtoRes13.setVariablesHolgura(2);
        dtoRes13.setMensaje("Primera etapa de las dos fases, se eliminan los 1's de las variables artificiales");

        DtoSimplex dtoRes14 = new DtoSimplex(dtoSol14,
                new String[]{"x1", "x2", "x4", "x5", "x6", "s6", "s7", "a8", "a9"},
                new String[]{"-w", "z", "a8", "s6", "x1"});
        dtoRes14.setAcotado(true);
        dtoRes14.setBloqueoDosFases(false);
        dtoRes14.setFactible(false);
        dtoRes14.setDosfases(true);
        dtoRes14.setFinalizado(false);
        dtoRes14.setCoordenadaPivote(new Point(0, 4));
        dtoRes14.setArtificialActual(9);
        dtoRes14.setVariablesBasicas(6);
        dtoRes14.setVariablesHolgura(2);
        dtoRes14.setMensaje("El problema no posee solución óptima factible.");
        return Arrays.asList(new Object[][]{
            {dtoRes11, new String[]{
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(0).toString(true),
                new Fraccion(2).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(7, 5).toString(true)
            }, 0},
            {dtoRes12, new String[]{
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion().toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(4, 3).toString(true),
                new Fraccion(7, 6).toString(true)
            }, 1},
            {dtoRes13, new String[]{
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),}, 2},
            {dtoRes14, new String[]{
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(4, 3).toString(true),
                new Fraccion(7, 6).toString(true)
            }, 1},
            {dtoRes14, new String[]{
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(Double.MAX_VALUE).toString(true),
                new Fraccion(7, 5).toString(true)
            }, 0},});
    }

}
