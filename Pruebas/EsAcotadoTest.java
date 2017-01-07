
package pruebas;

import java.io.IOException;
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
 * @author Yordan Jiménez
 */
@RunWith(Parameterized.class)
public class EsAcotadoTest {
    AbstractFraccion[] valores;
    boolean tipo;
    boolean resultadoCorrecto;

    public EsAcotadoTest(AbstractFraccion[] valores, boolean tipo, boolean resultadoCorrecto) {
        this.valores = valores;
        this.tipo = tipo;
        this.resultadoCorrecto = resultadoCorrecto;
    }
    
    @Test
    public void test() {
        Object[] argumentos = new Object[]{valores,tipo};
        Class<?>[] tipoArgumentos = new Class<?>[]{AbstractFraccion[].class, boolean.class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "esAcotado";
        Object solucionador = new SolucionadorSimplex();
        boolean resultadoGenerado = 
                (boolean)UtilPruebas.ingresarMetodoPrivado(argumentos,tipoArgumentos,clase,nombreMetodo,solucionador);
        assertTrue(resultadoGenerado == resultadoCorrecto);
    }
    
    @Parameterized.Parameters
    public static Collection parametros() throws IOException {
        AbstractFraccion [] prueba1 = new AbstractFraccion[]{
            new Fraccion(1),
            new Fraccion(Double.MAX_VALUE),
            new Fraccion(Double.MAX_VALUE),
            new Fraccion(Double.MAX_VALUE)
        };
        AbstractFraccion [] prueba2 = new AbstractFraccion[]{
            new Fraccion(),
            new Fraccion(3),
            new Fraccion(3),
            new Fraccion(3)
        };
        AbstractFraccion [] prueba3 = new AbstractFraccion[]{
            new Fraccion(2,9),
            new Fraccion(Double.MAX_VALUE),
            new Fraccion(Double.MAX_VALUE),
            new Fraccion(Double.MAX_VALUE)
        };
        AbstractFraccion [] prueba4 = new AbstractFraccion[]{
            new Fraccion(-98,5),
            new Fraccion(Double.MAX_VALUE),
            new Fraccion(Double.MAX_VALUE),
            new Fraccion(Double.MAX_VALUE)
        };
        AbstractFraccion [] prueba5 = new AbstractFraccion[]{
            new Fraccion(),
            new Fraccion(4,3),
            new Fraccion(3),
            new Fraccion()
        };
        return Arrays.asList(new Object[][]{
            {prueba1,true,false},
            {prueba2,false,true},
            {prueba3,true,false},
            {prueba4,false,false},
            {prueba5,false,true}
        });
    }
}
