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
 * @author jjime
 */
@RunWith(Parameterized.class)
public class AgregarUnoMatrizTest {
    AbstractFraccion[][] matriz;
    int fila;
    int columna;
    boolean positivo;
    AbstractFraccion valorCorrecto;

    public AgregarUnoMatrizTest(AbstractFraccion[][] matriz, int fila, int columna, boolean positivo, AbstractFraccion valorCorrecto) {
        this.matriz = matriz;
        this.fila = fila;
        this.columna = columna;
        this.positivo = positivo;
        this.valorCorrecto = valorCorrecto;
    }
    
    @Test
    public void test() {
        Object[] argumentos = new Object[]{UtilPruebas.clonarMatriz(matriz),fila,columna,positivo};
        Class<?>[] tipoArgumentos = new Class<?>[]{AbstractFraccion[][].class,int.class,int.class,boolean.class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "agregarUnoMatriz";
        Object solucionador = new SolucionadorSimplex();
        AbstractFraccion[][] resultadoGenerado = 
                (AbstractFraccion[][])UtilPruebas.ingresarMetodoPrivado(argumentos,tipoArgumentos,clase,nombreMetodo,solucionador);
        assertTrue(resultadoGenerado[fila][columna].iguales(valorCorrecto));
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
                new Fraccion(),
            },
            new AbstractFraccion[]{
                new Fraccion(15, 4),
                new Fraccion(10),
                new Fraccion(-1),
                new Fraccion(-1),
            },
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
                new Fraccion(2,8)
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
            {matriz1, 0, 0, true, new Fraccion(1)},
            {matriz2, 3, 2, false, new Fraccion(-1)},
            {matriz3, 1, 0, true, new Fraccion(1)},
            {matriz4, 1, 1, false, new Fraccion(-1)},
            {matriz4, 0, 2, true, new Fraccion(1)}
        });
    }
}
