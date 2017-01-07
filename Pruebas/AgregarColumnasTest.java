package pruebas;

import java.util.Arrays;
import java.util.Collection;
import modelo.AbstractFraccion;
import modelo.Fraccion;
import modelo.SolucionadorSimplex;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yordan
 */
@RunWith(Parameterized.class)
public class AgregarColumnasTest {

    AbstractFraccion[][] matriz;
    int cantidadAgregar;
    int resultadoCorrecto;

    public AgregarColumnasTest(AbstractFraccion[][] matriz, int cantidadAgregar, int resultadoCorrecto) {
        this.matriz = matriz;
        this.cantidadAgregar = cantidadAgregar;
        this.resultadoCorrecto = resultadoCorrecto;
    }

    @Test
    public void test() {
        Object[] argumentos = new Object[]{matriz, cantidadAgregar};
        Class<?>[] tipoArgumentos = new Class<?>[]{AbstractFraccion[][].class, int.class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "agregarColumnas";
        Object solucionador = new SolucionadorSimplex();
        AbstractFraccion[][] resultadoGenerado
                = (AbstractFraccion[][]) UtilPruebas.ingresarMetodoPrivado(argumentos, tipoArgumentos, clase, nombreMetodo, solucionador);
        assertTrue(compararCantidadColumnas(resultadoGenerado));
    }

    private boolean compararCantidadColumnas(AbstractFraccion[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            AbstractFraccion[] fila = matriz[i];
            if (fila.length != resultadoCorrecto) {
                return false;
            }
        }
        return true;
    }

    @Parameterized.Parameters
    public static Collection parametros() {
        AbstractFraccion[][] matriz1 = new AbstractFraccion[][]{
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

        AbstractFraccion[][] matriz2 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),
                new Fraccion(),},
            new AbstractFraccion[]{
                new Fraccion(15, 4),
                new Fraccion(10),
                new Fraccion(-1),
                new Fraccion(-1),},
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(3),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(5),
                new Fraccion(6),
                new Fraccion(),
                new Fraccion()
            }
        };

        AbstractFraccion[][] matriz3 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4)
            },
            new AbstractFraccion[]{
                new Fraccion(1)
            },
            new AbstractFraccion[]{
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(5)
            },
            new AbstractFraccion[]{
                new Fraccion(2, 8)
            }
        };

        AbstractFraccion[][] matriz4 = new AbstractFraccion[][]{
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4),
                new Fraccion(10),
                new Fraccion(-1)
            },
            new AbstractFraccion[]{
                new Fraccion(1),
                new Fraccion(),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(),
                new Fraccion(3),
                new Fraccion()
            },
            new AbstractFraccion[]{
                new Fraccion(5),
                new Fraccion(6),
                new Fraccion()
            }
        };
        return Arrays.asList(new Object[][]{
            {matriz1, 12, 22},
            {matriz2, 5, 9},
            {matriz3, 2, 3},
            {matriz4, 1, 4},
            {matriz4, 5, 8}
        });
    }
}
