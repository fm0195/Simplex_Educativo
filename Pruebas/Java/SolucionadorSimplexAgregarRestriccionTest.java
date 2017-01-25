/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import modelo.AbstractFraccion;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import dto.DtoSimplex;
import java.awt.Point;
import modelo.Fraccion;
import modelo.SolucionadorSimplex;
import modelo.parser.sym;
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
public class SolucionadorSimplexAgregarRestriccionTest {

    DtoSimplex problema;
    DtoSimplex resultadoCorrecto;
    int tipoRestriccion;

    public SolucionadorSimplexAgregarRestriccionTest(DtoSimplex problema, DtoSimplex resultadoCorrecto, int tipoRestriccion) {
        this.problema = problema;
        this.resultadoCorrecto = resultadoCorrecto;
        this.tipoRestriccion = tipoRestriccion;
    }

    @Test
    public void testSolucionar() {
        SolucionadorSimplex s = new SolucionadorSimplex();
        DtoSimplex solucionGenerada = s.agregarRestriccion(problema, tipoRestriccion);
        imprimir(solucionGenerada);
        boolean resultado = compararDtoSimplex(solucionGenerada, resultadoCorrecto);
        assertTrue(resultado);
    }
    
    static void imprimir(DtoSimplex dto) {
        AbstractFraccion[][] m = dto.getMatriz();
        String c = "";
        String[] f = dto.getNombreFilas();
        String[] col = dto.getNombreColumnas();
        String s = "";
        String of = dto.getOperaciones();
        s += of;
        s += '\n';
        c += s;
        c += " Acotado: " + dto.esAcotado() + "\n";
        c += " Factible: " + dto.esFactible() + "\n";
        c += " Terminado: " + dto.esFinalizado() + "\n";
        c += " Dos fases: " + dto.esDosfases() + "\n";
        c += " Bloqueo: " + dto.esBloqueoDosFases() + "\n";
        c += " punto: " + dto.getCoordenadaPivote() + "\n";
        c += "art : " + dto.getArtificialActual() + '\n';
        c += "basicas : " + dto.getVariablesBasicas() + '\n';
        c += "holgura : " + dto.getVariablesHolgura() + '\n';
        c += dto.getMensaje() + "\n";
        s = "     ";
        for (int contador = 0; contador < col.length; contador++) {
            s += col[contador];
            s += "     ";
        }
        s += "\n";
        c += s;
        for (int contadorFila = 0; contadorFila < m.length; contadorFila++) {
            s = "";
            s += f[contadorFila];
            s += "     ";
            for (int contadorColumna = 0; contadorColumna < m[0].length; contadorColumna++) {
                s += m[contadorFila][contadorColumna].toString();
                s += "     ";
            }
            s += "\n";
            c += s;
        }
        System.out.println(c);
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
                new Fraccion(),
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
                new Fraccion(),
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
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(7)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
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
                new String[]{"x1", "x2", "x4", "x5", "x6", "s6", "s7","s10", "a8", "a9"},
                new String[]{"-w", "z", "a8", "s6", "a9","s10"});
        dtoRes12.setAcotado(true);
        dtoRes12.setBloqueoDosFases(true);
        dtoRes12.setFactible(true);
        dtoRes12.setDosfases(true);
        dtoRes12.setFinalizado(false);
        dtoRes12.setCoordenadaPivote(new Point(8, 2));
        dtoRes12.setArtificialActual(9);
        dtoRes12.setVariablesBasicas(6);
        dtoRes12.setVariablesHolgura(3);
        dtoRes12.setMensaje("Restricción agregada.");

        /*Segunda prueba*/
        AbstractFraccion[][] sol23 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(-2),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-2)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(2),
                new Fraccion(),
                new Fraccion(),
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
                new Fraccion(),
                new Fraccion(),
                new Fraccion(20)
            },
            new AbstractFraccion[]{
                new Fraccion(2),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion()
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

        DtoSimplex dtoRes23 = new DtoSimplex(sol23,
                new String[]{"x1", "x2", "s3", "s4", "s6", "a5", "a7"},
                new String[]{"-w", "z", "s3", "a5","a7"});
        dtoRes23.setAcotado(true);
        dtoRes23.setBloqueoDosFases(false);
        dtoRes23.setFactible(true);
        dtoRes23.setDosfases(true);
        dtoRes23.setFinalizado(false);
        dtoRes23.setCoordenadaPivote(new Point(1, 3));
        //dtoRes22.setOperaciones(new String[]{"1/2 * F2 -> F2", "2 * F2 + F0' -> F0'",
        //    "-1 * F2 + F0 -> F0", "-3 * F2 + F1 -> F1"});
        dtoRes23.setArtificialActual(6);
        dtoRes23.setVariablesBasicas(3);
        dtoRes23.setVariablesHolgura(3);
        dtoRes23.setMensaje("Restricción agregada.");

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
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-3)
            },
            new AbstractFraccion[]{
                new Fraccion(-25),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(10),
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
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(3)
            },
            new AbstractFraccion[]{
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
        new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion()
            }
        };
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
                new String[]{"x1", "x2", "s3", "s4", "a5","a6"},
                new String[]{"-w", "z", "s3", "a5", "x2","a6"});
        dtoRes34.setAcotado(true);
        dtoRes34.setBloqueoDosFases(false);
        dtoRes34.setFactible(true);
        dtoRes34.setDosfases(true);
        dtoRes34.setFinalizado(false);
        dtoRes34.setCoordenadaPivote(new Point(0, 2));
        //dtoRes33.setOperaciones(new String[]{"1 * F1 -> F1", "1 * F1 + F0' -> F0'",
        //    "25 * F1 + F0 -> F0", "-1 * F1 + F2 -> F2", "1 * F1 + F3 -> F3"});
        dtoRes34.setArtificialActual(6);
        dtoRes34.setVariablesBasicas(3);
        dtoRes34.setVariablesHolgura(3);
        dtoRes34.setMensaje("Restricción agregada.");

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
                new Fraccion(0),
                new Fraccion(-10),
                new Fraccion(15),
                new Fraccion(),
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
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(3)
            },
            new AbstractFraccion[]{
                new Fraccion(0),
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion()
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
                new String[]{"x1", "x2", "s3", "s4", "s5","s6"},
                new String[]{"z", "x1", "s4", "s5","s6"});
        dtoRes43.setAcotado(true);
        dtoRes43.setBloqueoDosFases(false);
        dtoRes43.setFactible(true);
        dtoRes43.setDosfases(false);
        dtoRes43.setFinalizado(false);
        dtoRes43.setCoordenadaPivote(new Point(1, 3));
        //dtoRes42.setOperaciones(new String[]{"1 * F3 -> F3", "10 * F3 + F0 -> F0",
        //    "0 * F3 + F1 -> F1", "-1 * F3 + F2 -> F2"});
        dtoRes43.setArtificialActual(7);
        dtoRes43.setVariablesBasicas(3);
        dtoRes43.setVariablesHolgura(4);
        dtoRes43.setMensaje("Restricción agregada.");
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
                new Fraccion(-25),
                new Fraccion(),
                new Fraccion(15),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(2)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(-1),
                new Fraccion(),
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(-1),
                new Fraccion(1),
                new Fraccion()
            }
        };

        DtoSimplex dtoRes52 = new DtoSimplex(sol52,
                new String[]{"x1", "x2", "s3", "s4"},
                new String[]{"z", "s3", "x1"});
        dtoRes52.setAcotado(true);
        dtoRes52.setBloqueoDosFases(true);
        dtoRes52.setFactible(true);
        dtoRes52.setDosfases(true);
        dtoRes52.setFinalizado(false);
        dtoRes52.setCoordenadaPivote(new Point(1, 1));
        //dtoRes52.setOperaciones(new String[]{"1 * F1 -> F1", "25 * F1 + F0 -> F0",
        //   "1 * F1 + F2 -> F2"});
        dtoRes52.setArtificialActual(0);
        dtoRes52.setVariablesBasicas(3);
        dtoRes52.setVariablesHolgura(2);
        dtoRes52.setMensaje("Operaciones fila realizadas.");

        DtoSimplex dtoRes53 = new DtoSimplex(sol53,
                new String[]{"x1", "x2", "s3", "s4","s5","a6"},
                new String[]{"z", "s3", "x1","a6"});
        dtoRes53.setAcotado(true);
        dtoRes53.setBloqueoDosFases(true);
        dtoRes53.setFactible(true);
        dtoRes53.setDosfases(true);
        dtoRes53.setFinalizado(false);
        dtoRes53.setCoordenadaPivote(new Point(2, 1));
        //dtoRes52.setOperaciones(new String[]{"1 * F1 -> F1", "25 * F1 + F0 -> F0",
        //   "1 * F1 + F2 -> F2"});
        dtoRes53.setArtificialActual(6);
        dtoRes53.setVariablesBasicas(3);
        dtoRes53.setVariablesHolgura(3);
        dtoRes53.setMensaje("Restricción agregada.");
        return Arrays.asList(new Object[][]{
            {dtoRes11, dtoRes12,sym.MENORIGUAL},
            {dtoRes22, dtoRes23,sym.MAYORIGUAL},
            {dtoRes33, dtoRes34,sym.IGUAL},
            {dtoRes42, dtoRes43,sym.MENORIGUAL},
            {dtoRes52, dtoRes53,sym.MAYORIGUAL}
        });
    }

}
