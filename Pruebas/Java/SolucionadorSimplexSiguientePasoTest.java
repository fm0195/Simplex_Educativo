package pruebas;

import modelo.AbstractFraccion;
import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
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
public class SolucionadorSimplexSiguientePasoTest {

    DtoSimplex paso1;
    DtoSimplex paso2;

    public SolucionadorSimplexSiguientePasoTest(DtoSimplex paso1, DtoSimplex paso2) {
        this.paso1 = paso1;
        this.paso2 = paso2;
    }

    @Test
    public void testSolucionar() {
        SolucionadorSimplex s = new SolucionadorSimplex();
        paso1 = s.siguientePaso(paso1);
        boolean igualSolucion = true;
        if (!compararDtoSimplex(paso1, paso2)) {
            igualSolucion = false;
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
        resultado &= UtilPruebas.matricesIguales(dto1.getMatriz(), dto2.getMatriz());
        return resultado;
    }

    @Parameterized.Parameters
    public static Collection parametros() {
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

        DtoSimplex dtoRes11 = new DtoSimplex(dtoSol11,
                new String[]{"x1", "x2", "x4", "x5", "x6", "s6", "s7", "a8", "a9"},
                new String[]{"-w", "z", "a8", "s6", "a9"});
        dtoRes11.setAcotado(true);
        dtoRes11.setBloqueoDosFases(true);
        dtoRes11.setFactible(true);
        dtoRes11.setDosfases(true);
        dtoRes11.setFinalizado(false);
        dtoRes11.setCoordenadaPivote(new Point(7, 2));
        //dtoRes11.setOperaciones(new String[]{"-1 F1 + F0' -> F0'"});
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

        /*Segunda prueba*/
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
            {dtoRes11, dtoRes12},
            {dtoRes22, dtoRes24},
            {dtoRes33, dtoRes34},
            {dtoRes42, dtoRes43},
            {dtoRes52, dtoRes53}
        });
    }

}
