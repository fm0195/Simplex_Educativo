package pruebas;

import modelo.AbstractFraccion;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
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
public class VerificarFactibilidadTest {

    AbstractFraccion[] valores;
    boolean resultadoCorrecto;

    public VerificarFactibilidadTest(AbstractFraccion[] valores, boolean resultadoCorrecto) {
        this.valores = valores;
        this.resultadoCorrecto = resultadoCorrecto;
    }

    @Test
    public void test() {
        Object[] argumentos = new Object[]{valores};
        Class<?>[] tipoArgumentos = new Class<?>[]{AbstractFraccion[].class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "verificarFactibilidad";
        Object solucionador = new SolucionadorSimplex();
        boolean resultadoGenerado
                = (boolean) UtilPruebas.ingresarMetodoPrivado(argumentos, tipoArgumentos, clase, nombreMetodo, solucionador);
        assertTrue(resultadoGenerado == resultadoCorrecto);
    }

    @Parameterized.Parameters
    public static Collection parametros() throws IOException {
        AbstractFraccion[] prueba1 = new AbstractFraccion[]{
            new Fraccion(1),
            new Fraccion(),
            new Fraccion(),
            new Fraccion(-9)
        };
        AbstractFraccion[] prueba2 = new AbstractFraccion[]{
            new Fraccion(),
            new Fraccion(3),
            new Fraccion(3),
            new Fraccion()
        };
        AbstractFraccion[] prueba3 = new AbstractFraccion[]{
            new Fraccion(2, 9),
            new Fraccion(),
            new Fraccion(5, 9),
            new Fraccion(-9)
        };
        AbstractFraccion[] prueba4 = new AbstractFraccion[]{
            new Fraccion(-98, 5),
            new Fraccion(5),
            new Fraccion(),
            new Fraccion(-1)
        };
        AbstractFraccion[] prueba5 = new AbstractFraccion[]{
            new Fraccion(),
            new Fraccion(4, 3),
            new Fraccion(3),
            new Fraccion()
        };
        return Arrays.asList(new Object[][]{
            {prueba1, false},
            {prueba2, true},
            {prueba3, false},
            {prueba4, true},
            {prueba5, true}
        });
    }
}
