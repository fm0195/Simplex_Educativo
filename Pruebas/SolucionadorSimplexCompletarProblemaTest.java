package pruebas;

import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import modelo.AbstractFraccion;
import dto.DtoSimplex;
import modelo.Fraccion;
import modelo.SolucionadorSimplex;
import modelo.parser.IParser;
import modelo.parser.SimplexParser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yordan Jiménez
 */
@RunWith(Parameterized.class)
public class SolucionadorSimplexCompletarProblemaTest {

    DtoSimplex problema;
    DtoSimplex resultadoCorrecto;

    public SolucionadorSimplexCompletarProblemaTest(DtoSimplex problema, DtoSimplex resultadoCorrecto) {
        this.problema = problema;
        this.resultadoCorrecto = resultadoCorrecto;
    }

    @Test
    public void testSolucionar() {
        SolucionadorSimplex s = new SolucionadorSimplex();
        problema = s.completarProblema(problema);
        assertTrue(compararDtoSimplex(problema, resultadoCorrecto));
    }

    private boolean compararDtoSimplex(DtoSimplex dto1, DtoSimplex dto2) {
        boolean resultado = dto1.esAcotado() == dto2.esAcotado();
        resultado &= dto1.esBloqueoDosFases() == dto2.esBloqueoDosFases();
        resultado &= dto1.esDosfases() == dto2.esDosfases();
        resultado &= dto1.esFactible() == dto2.esFactible();
        resultado &= dto1.esFinalizado() == dto2.esFinalizado();
        resultado &= dto1.getCoordenadaPivote().equals(dto2.getCoordenadaPivote());
        resultado &= UtilPruebas.arreglosIguales(dto1.getNombreColumnas(), dto2.getNombreColumnas());
        resultado &= UtilPruebas.arreglosIguales(dto1.getNombreFilas(), dto2.getNombreFilas());
        resultado &= dto1.getOperaciones().equals(dto2.getOperaciones());
        resultado &= dto1.getVariablesBasicas() == dto2.getVariablesBasicas();
        resultado &= dto1.getVariablesHolgura() == dto2.getVariablesHolgura();
        resultado &= dto1.getArtificialActual() == dto2.getArtificialActual();
        resultado &= UtilPruebas.matricesIguales(dto1.getMatriz(), dto2.getMatriz());
        return resultado;
    }

    @Parameterized.Parameters
    public static Collection parametros() throws IOException {
        IParser parser = new SimplexParser();
        DtoSimplex dto1 = parser.parse("(0) max z = -15/4 x1 + -10 x2 + x4 + x5 + x6\n"
                + "(1) -1 x1        <= -2\n"
                + "(2) -3     x2    >= -4\n"
                + "(3) -5 x1 + -6 x2 = -7");
        dto1.setVariablesBasicas();
        DtoSimplex dto2 = parser.parse("max z = -1 x1 - 2 x2\n"
                + "3 x1 + 4 x2 <= 20\n"
                + "2 x1 - 1 x2 >= 2");
        dto2.setVariablesBasicas();
        DtoSimplex dto3 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1          <= 2\n"
                + "(2)                  1 x2   = 3\n"
                + "(3)         -1 x1 +  1 x2  <= 0");
        dto3.setVariablesBasicas();
        DtoSimplex dto4 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1         <= 2\n"
                + "(2)                  1 x2 <= 3\n"
                + "(3)          1 x1 +  1 x2 <= 4");
        dto4.setVariablesBasicas();
        DtoSimplex dto5 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1          <= 2\n"
                + "(2)          1 x1 -  1 x2  <= 0");
        dto5.setVariablesBasicas();
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

        /*Segunda prueba*/
        AbstractFraccion[][] sol21 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion()
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

        DtoSimplex dtoRes21 = new DtoSimplex(sol21,
                new String[]{"x1", "x2", "s3", "s4", "a5"},
                new String[]{"-w", "z", "s3", "a5"});
        dtoRes21.setAcotado(true);
        dtoRes21.setBloqueoDosFases(true);
        dtoRes21.setFactible(true);
        dtoRes21.setDosfases(true);
        dtoRes21.setFinalizado(false);
        dtoRes21.setCoordenadaPivote(new Point(4, 3));
        dtoRes21.setOperaciones(new String[]{"-1 F2 + F0' -> F0'"});
        dtoRes21.setArtificialActual(4);
        dtoRes21.setVariablesBasicas(3);
        dtoRes21.setVariablesHolgura(2);
        dtoRes21.setMensaje("Agregada fila -w, holguras 's' y artificiales 'a'.");

        /*tercer problema*/
        AbstractFraccion[][] sol31 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(-15),
                new Fraccion(-10),
                new Fraccion(),
                new Fraccion(),
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
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
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

        DtoSimplex dtoRes31 = new DtoSimplex(sol31,
                new String[]{"x1", "x2", "s3", "s4", "a5"},
                new String[]{"-w", "z", "s3", "a5", "s4"});
        dtoRes31.setAcotado(true);
        dtoRes31.setBloqueoDosFases(true);
        dtoRes31.setFactible(true);
        dtoRes31.setDosfases(true);
        dtoRes31.setFinalizado(false);
        dtoRes31.setCoordenadaPivote(new Point(4, 3));
        dtoRes31.setOperaciones(new String[]{"-1 F2 + F0' -> F0'"});
        dtoRes31.setArtificialActual(4);
        dtoRes31.setVariablesBasicas(3);
        dtoRes31.setVariablesHolgura(2);
        dtoRes31.setMensaje("Agregada fila -w, holguras 's' y artificiales 'a'.");


        /*
         problema 4
         */
        AbstractFraccion[][] sol41 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(-15),
                new Fraccion(-10),
                new Fraccion(),
                new Fraccion(),
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
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(3)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(4)
            }
        };

        DtoSimplex dtoRes41 = new DtoSimplex(sol41,
                new String[]{"x1", "x2", "s3", "s4", "s5"},
                new String[]{"z", "s3", "s4", "s5"});
        dtoRes41.setAcotado(true);
        dtoRes41.setBloqueoDosFases(false);
        dtoRes41.setFactible(true);
        dtoRes41.setDosfases(false);
        dtoRes41.setFinalizado(false);
        dtoRes41.setCoordenadaPivote(new Point(0, 1));
        dtoRes41.setOperaciones(new String[]{"1 * F1 -> F1", "15 * F1 + F0 -> F0",
            "0 * F1 + F2 -> F2", "-1 * F1 + F3 -> F3"});
        dtoRes41.setArtificialActual(0);
        dtoRes41.setVariablesBasicas(3);
        dtoRes41.setVariablesHolgura(3);
        dtoRes41.setMensaje("Agregadas variables de holgura 's' al problema original.");

        /*
         Problema 5
         */
        AbstractFraccion[][] sol51 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(-15),
                new Fraccion(-10),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion()
            },};

        DtoSimplex dtoRes51 = new DtoSimplex(sol51,
                new String[]{"x1", "x2", "s3", "s4"},
                new String[]{"z", "s3", "s4"});
        dtoRes51.setAcotado(true);
        dtoRes51.setBloqueoDosFases(false);
        dtoRes51.setFactible(true);
        dtoRes51.setDosfases(false);
        dtoRes51.setFinalizado(false);
        dtoRes51.setCoordenadaPivote(new Point(0, 2));
        dtoRes51.setOperaciones(new String[]{"1 * F2 -> F2", "15 * F2 + F0 -> F0",
            "-1 * F2 + F1 -> F1"});
        dtoRes51.setArtificialActual(0);
        dtoRes51.setVariablesBasicas(3);
        dtoRes51.setVariablesHolgura(2);
        dtoRes51.setMensaje("Agregadas variables de holgura 's' al problema original.");

        return Arrays.asList(new Object[][]{
            {dto1, dtoRes11},
            {dto2, dtoRes21},
            {dto3, dtoRes31},
            {dto4, dtoRes41},
            {dto5, dtoRes51}
        });
    }
}
