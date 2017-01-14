package pruebas;

import modelo.AbstractFraccion;
import dto.DtoSimplex;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
public class SolucionadorSimplexSolucionarTest {

    DtoSimplex problema;
    DtoSimplex[] solucion;

    public SolucionadorSimplexSolucionarTest(DtoSimplex problema, DtoSimplex[] resultado) {
        this.problema = problema;
        this.solucion = resultado;
    }

    @Test
    public void testSolucionar() {
        SolucionadorSimplex s = new SolucionadorSimplex();
        problema = s.completarProblema(problema);
        ArrayList<DtoSimplex> resultado = s.solucionar(problema);
        if (solucion.length != resultado.size()) {
            fail("El solucionador no ha brindado un solución con la cantidad correcta"
                    + "de iteraciones");
        }
        boolean igualSolucion = true;
        for (int i = 0; i < solucion.length; i++) {
            DtoSimplex solucionCorrecta = solucion[i];
            DtoSimplex solucionObtenida = resultado.get(i);
            if (!compararDtoSimplex(solucionCorrecta, solucionObtenida)) {
                igualSolucion = false;
                break;
            }
        }
        assertTrue(igualSolucion);
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

        AbstractFraccion[][] sol23 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(5, 2),
                new Fraccion(),
                new Fraccion(1, 2),
                new Fraccion(-1, 2),
                new Fraccion(-1)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(11, 2),
                new Fraccion(1),
                new Fraccion(3, 2),
                new Fraccion(-3, 2),
                new Fraccion(17)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(-1, 2),
                new Fraccion(),
                new Fraccion(-1, 2),
                new Fraccion(1, 2),
                new Fraccion(1)
            }
        };
        AbstractFraccion[][] sol24 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(5, 2),
                new Fraccion(),
                new Fraccion(1, 2),
                new Fraccion(-1)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(11, 2),
                new Fraccion(1),
                new Fraccion(3, 2),
                new Fraccion(17)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(-1, 2),
                new Fraccion(),
                new Fraccion(-1, 2),
                new Fraccion(1)
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

        DtoSimplex dtoRes22 = new DtoSimplex(sol22,
                new String[]{"x1", "x2", "s3", "s4", "a5"},
                new String[]{"-w", "z", "s3", "a5"});
        dtoRes22.setAcotado(true);
        dtoRes22.setBloqueoDosFases(false);
        dtoRes22.setFactible(true);
        dtoRes22.setDosfases(true);
        dtoRes22.setFinalizado(false);
        dtoRes22.setCoordenadaPivote(new Point(0, 3));
        dtoRes22.setOperaciones(new String[]{"1/2 * F2 -> F2", "2 * F2 + F0' -> F0'",
            "-1 * F2 + F0 -> F0", "-3 * F2 + F1 -> F1"});
        dtoRes22.setArtificialActual(5);
        dtoRes22.setVariablesBasicas(3);
        dtoRes22.setVariablesHolgura(2);
        dtoRes22.setMensaje("Primera etapa de las dos fases, se eliminan los 1's de las variables artificiales");

        DtoSimplex dtoRes24 = new DtoSimplex(sol24,
                new String[]{"x1", "x2", "s3", "s4"},
                new String[]{"z", "s3", "x1"});
        dtoRes24.setAcotado(true);
        dtoRes24.setBloqueoDosFases(false);
        dtoRes24.setFactible(true);
        dtoRes24.setDosfases(false);
        dtoRes24.setFinalizado(true);
        dtoRes24.setCoordenadaPivote(new Point(0, 2));
        dtoRes24.setOperaciones(new String[]{"1 * F2 -> F2", "0 * F2 + F0 -> F0",
            "0 * F2 + F1 -> F1"});
        dtoRes24.setArtificialActual(5);
        dtoRes24.setVariablesBasicas(3);
        dtoRes24.setVariablesHolgura(2);
        dtoRes24.setMensaje("Primera fase finalizada, eliminadas variables artificiales y fila w. Estado óptimo.");

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

        AbstractFraccion[][] sol32 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-3)
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

        AbstractFraccion[][] sol34 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(-1)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(25),
                new Fraccion(10),
                new Fraccion(),
                new Fraccion(50)
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
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(1)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(2)
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

        DtoSimplex dtoRes32 = new DtoSimplex(sol32,
                new String[]{"x1", "x2", "s3", "s4", "a5"},
                new String[]{"-w", "z", "s3", "a5", "s4"});
        dtoRes32.setAcotado(true);
        dtoRes32.setBloqueoDosFases(false);
        dtoRes32.setFactible(true);
        dtoRes32.setDosfases(true);
        dtoRes32.setFinalizado(false);
        dtoRes32.setCoordenadaPivote(new Point(1, 4));
        dtoRes32.setOperaciones(new String[]{"1 * F3 -> F3", "1 * F3 + F0' -> F0'",
            "10 * F3 + F0 -> F0", "0 * F3 + F1 -> F1", "-1 * F3 + F2 -> F2"});
        dtoRes32.setArtificialActual(5);
        dtoRes32.setVariablesBasicas(3);
        dtoRes32.setVariablesHolgura(2);
        dtoRes32.setMensaje("Primera etapa de las dos fases, se eliminan los 1's de las variables artificiales");

        DtoSimplex dtoRes33 = new DtoSimplex(sol33,
                new String[]{"x1", "x2", "s3", "s4", "a5"},
                new String[]{"-w", "z", "s3", "a5", "x2"});
        dtoRes33.setAcotado(true);
        dtoRes33.setBloqueoDosFases(false);
        dtoRes33.setFactible(true);
        dtoRes33.setDosfases(true);
        dtoRes33.setFinalizado(false);
        dtoRes33.setCoordenadaPivote(new Point(0, 2));
        dtoRes33.setOperaciones(new String[]{"1 * F1 -> F1", "1 * F1 + F0' -> F0'",
            "25 * F1 + F0 -> F0", "-1 * F1 + F2 -> F2", "1 * F1 + F3 -> F3"});
        dtoRes33.setArtificialActual(5);
        dtoRes33.setVariablesBasicas(3);
        dtoRes33.setVariablesHolgura(2);
        dtoRes33.setMensaje("Se realizaron las operaciones fila indicadas en el paso anterior.");

        DtoSimplex dtoRes34 = new DtoSimplex(sol34,
                new String[]{"x1", "x2", "s3", "s4", "a5"},
                new String[]{"-w", "z", "x1", "a5", "x2"});
        dtoRes34.setAcotado(true);
        dtoRes34.setBloqueoDosFases(false);
        dtoRes34.setFactible(false);
        dtoRes34.setDosfases(true);
        dtoRes34.setFinalizado(false);
        dtoRes34.setCoordenadaPivote(new Point(0, 2));
        dtoRes34.setArtificialActual(5);
        dtoRes34.setVariablesBasicas(3);
        dtoRes34.setVariablesHolgura(2);
        dtoRes34.setMensaje("El problema no posee solución óptima factible.");

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

        AbstractFraccion[][] sol43 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(5),
                new Fraccion(),
                new Fraccion(10),
                new Fraccion(50)
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
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(1)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(2)
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

        DtoSimplex dtoRes42 = new DtoSimplex(sol42,
                new String[]{"x1", "x2", "s3", "s4", "s5"},
                new String[]{"z", "x1", "s4", "s5"});
        dtoRes42.setAcotado(true);
        dtoRes42.setBloqueoDosFases(false);
        dtoRes42.setFactible(true);
        dtoRes42.setDosfases(false);
        dtoRes42.setFinalizado(false);
        dtoRes42.setCoordenadaPivote(new Point(1, 3));
        dtoRes42.setOperaciones(new String[]{"1 * F3 -> F3", "10 * F3 + F0 -> F0",
            "0 * F3 + F1 -> F1", "-1 * F3 + F2 -> F2"});
        dtoRes42.setArtificialActual(0);
        dtoRes42.setVariablesBasicas(3);
        dtoRes42.setVariablesHolgura(3);
        dtoRes42.setMensaje("Operaciones fila realizadas.");

        DtoSimplex dtoRes43 = new DtoSimplex(sol43,
                new String[]{"x1", "x2", "s3", "s4", "s5"},
                new String[]{"z", "x1", "s4", "x2"});
        dtoRes43.setAcotado(true);
        dtoRes43.setBloqueoDosFases(false);
        dtoRes43.setFactible(true);
        dtoRes43.setDosfases(false);
        dtoRes43.setFinalizado(true);
        dtoRes43.setCoordenadaPivote(new Point(0, 1));
        dtoRes43.setArtificialActual(0);
        dtoRes43.setVariablesBasicas(3);
        dtoRes43.setVariablesHolgura(3);
        dtoRes43.setMensaje("Estado óptimo.");
        dtoRes43.setMensaje("z = 50.0\nx1 = 2.0\nx2 = 2.0");
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

        AbstractFraccion[][] sol54 = new AbstractFraccion[][]{
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

        DtoSimplex dtoRes52 = new DtoSimplex(sol52,
                new String[]{"x1", "x2", "s3", "s4"},
                new String[]{"z", "s3", "x1"});
        dtoRes52.setAcotado(true);
        dtoRes52.setBloqueoDosFases(false);
        dtoRes52.setFactible(true);
        dtoRes52.setDosfases(false);
        dtoRes52.setFinalizado(false);
        dtoRes52.setCoordenadaPivote(new Point(1, 1));
        dtoRes52.setOperaciones(new String[]{"1 * F1 -> F1", "25 * F1 + F0 -> F0",
            "1 * F1 + F2 -> F2"});
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

        DtoSimplex dtoRes54 = new DtoSimplex(sol54,
                new String[]{"x1", "x2", "s3", "s4"},
                new String[]{"z", "x2", "x1"});
        dtoRes54.setAcotado(false);
        dtoRes54.setBloqueoDosFases(false);
        dtoRes54.setFactible(true);
        dtoRes54.setDosfases(false);
        dtoRes54.setFinalizado(false);
        dtoRes54.setCoordenadaPivote(new Point(3, 1));
        dtoRes54.setArtificialActual(0);
        dtoRes54.setVariablesBasicas(3);
        dtoRes54.setVariablesHolgura(2);
        dtoRes54.setMensaje("El problema no posee una solución acotada. Revise las restricciones");
        return Arrays.asList(new Object[][]{
            {dto1, new DtoSimplex[]{dtoRes11, dtoRes12, dtoRes13, dtoRes14}},
            {dto2, new DtoSimplex[]{dtoRes21, dtoRes22, dtoRes24}},
            {dto3, new DtoSimplex[]{dtoRes31, dtoRes32, dtoRes33, dtoRes34}},
            {dto4, new DtoSimplex[]{dtoRes41, dtoRes42, dtoRes43}},
            {dto5, new DtoSimplex[]{dtoRes51, dtoRes52, dtoRes53, dtoRes54}}
        });
    }

}
