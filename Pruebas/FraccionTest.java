package pruebas;

import modelo.Fraccion;
import modelo.AbstractFraccion;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yordan Jimenez
 */
@RunWith(Parameterized.class)
public class FraccionTest {

    static AbstractFraccion fraccion;
    static AbstractFraccion fraccion2;
    static AbstractFraccion fraccion3;
    static AbstractFraccion fraccion4;
    static AbstractFraccion fraccion5;

    static AbstractFraccion fraccion6;
    static AbstractFraccion fraccion7;
    static AbstractFraccion fraccion8;
    static AbstractFraccion fraccion9;
    static AbstractFraccion fraccion10;

    static AbstractFraccion[] fraccionRes1;
    static AbstractFraccion[] fraccionRes2;
    static AbstractFraccion[] fraccionRes3;
    static AbstractFraccion[] fraccionRes4;
    static AbstractFraccion[] fraccionRes5;

    AbstractFraccion fraccionOperando1;
    AbstractFraccion fraccionOperando2;
    AbstractFraccion[] resultadoOperando;
    double[] resDivisorComun;
    boolean esCero;

    public FraccionTest(AbstractFraccion fraccion1, AbstractFraccion fraccion2,
            double resDivisorComun[], AbstractFraccion[] resultado, boolean esCero) {
        this.fraccionOperando1 = fraccion1;
        this.fraccionOperando2 = fraccion2;
        this.resultadoOperando = resultado;
        this.resDivisorComun = resDivisorComun;
        this.esCero = esCero;
    }

    @Test
    public void test() {
        testSumar(fraccionOperando1, fraccionOperando2, resultadoOperando[0]);
        testRestar(fraccionOperando1, fraccionOperando2, resultadoOperando[1]);
        testMultiplicar(fraccionOperando1, fraccionOperando2, resultadoOperando[2]);
        testDividir(fraccionOperando1, fraccionOperando2, resultadoOperando[3]);
        testClonar(fraccionOperando2);
        testObtenerInverso(fraccionOperando2,resultadoOperando[4]);
        testHacerNegativa(fraccionOperando1.clonar(), resultadoOperando[5]);
        testObtenerMayorDivisorComun(resDivisorComun[0], resDivisorComun[1], (int) resDivisorComun[2]);
        testCero(fraccionOperando2, esCero);
    }

    @Parameterized.Parameters
    public static Collection parametros() {
        fraccion = new Fraccion(15, 60);
        fraccion2 = new Fraccion(150, 60);
        fraccion3 = new Fraccion(120, 25);
        fraccion4 = new Fraccion(9, 78);
        fraccion5 = new Fraccion(12, 3);

        fraccion6 = new Fraccion(5, 7);
        fraccion7 = new Fraccion(100, 61);
        fraccion8 = new Fraccion(14, 5);
        fraccion9 = new Fraccion(56, 8);
        fraccion10 = new Fraccion(12, 3);

        fraccionRes1 = new AbstractFraccion[]{
            new Fraccion(27, 28),
            new Fraccion(-13, 28),
            new Fraccion(5, 28),
            new Fraccion(7, 20),
            new Fraccion(7,5),
            new Fraccion(-15, 60)
        };
        fraccionRes2 = new AbstractFraccion[]{
            new Fraccion(505, 122),
            new Fraccion(105, 122),
            new Fraccion(250, 61),
            new Fraccion(61, 40),
            new Fraccion(61, 100),
            new Fraccion(-150, 60)
        };

        fraccionRes3 = new AbstractFraccion[]{
            new Fraccion(38, 5),
            new Fraccion(2),
            new Fraccion(336, 25),
            new Fraccion(12, 7),
            new Fraccion(5, 14),
            new Fraccion(-120, 25)
        };

        fraccionRes4 = new AbstractFraccion[]{
            new Fraccion(185, 26),
            new Fraccion(-179, 26),
            new Fraccion(21, 26),
            new Fraccion(3, 182),
            new Fraccion(8, 56),
            new Fraccion(-9, 78)
        };
        fraccionRes5 = new AbstractFraccion[]{
            new Fraccion(8),
            new Fraccion(),
            new Fraccion(16),
            new Fraccion(1),
            new Fraccion(3, 12),
            new Fraccion(-12, 3)
        };

        return Arrays.asList(new Object[][]{
            {fraccion, fraccion6, new double[]{15, 60, 15}, fraccionRes1, false},
            {fraccion2, fraccion7, new double[]{150, 60, 30}, fraccionRes2, false},
            {fraccion3, fraccion8, new double[]{120, 25, 5}, fraccionRes3, false},
            {fraccion4, fraccion9, new double[]{9, 78, 3}, fraccionRes4, false},
            {fraccion5, fraccion10, new double[]{12, 3, 3}, fraccionRes5, false}
        });
    }

    public void testSumar(AbstractFraccion fraccion1, AbstractFraccion fraccion2,
            AbstractFraccion resultado) {
        AbstractFraccion resultadoOperacion = fraccion1.sumar(fraccion2);
        assertTrue(resultado.iguales(resultadoOperacion));
    }

    public void testRestar(AbstractFraccion fraccion1, AbstractFraccion fraccion2,
            AbstractFraccion resultado) {
        AbstractFraccion resultadoOperacion = fraccion1.restar(fraccion2);
        assertTrue(resultado.iguales(resultadoOperacion));
    }

    public void testMultiplicar(AbstractFraccion fraccion1, AbstractFraccion fraccion2,
            AbstractFraccion resultado) {
        AbstractFraccion resultadoOperacion = fraccion1.multiplicar(fraccion2);
        assertTrue(resultado.iguales(resultadoOperacion));
    }

    public void testDividir(AbstractFraccion fraccion1, AbstractFraccion fraccion2,
            AbstractFraccion resultado) {
        AbstractFraccion resultadoOperacion = fraccion1.dividir(fraccion2);
        assertTrue(resultado.iguales(resultadoOperacion));
    }
    
    public void testClonar(AbstractFraccion fraccion) {
        AbstractFraccion resultadoOperacion = fraccion.clonar();
        assertTrue(fraccion.iguales(resultadoOperacion));
    }
    
    public void testObtenerInverso(AbstractFraccion fraccion, AbstractFraccion resultadoCorrecto){
        AbstractFraccion resultadoOperacion = fraccion.obtenerInverso();
        assertTrue(resultadoCorrecto.iguales(resultadoOperacion));
    }

    public void testHacerNegativa(AbstractFraccion fraccion, AbstractFraccion resultadoCorrecto){
        fraccion.hacerNegativa();
        assertTrue(resultadoCorrecto.iguales(fraccion));
    }
    
    public void testObtenerMayorDivisorComun(double numerador, double denominador, int resultadoCorrecto) {
        try {
            Object[] argumentos = new Object[]{numerador, denominador};
            Class<?>[] tipoArgumentos = new Class[]{double.class, double.class};
            Class<?> claseFraccion = Fraccion.class;
            Method metodo = claseFraccion.getDeclaredMethod(
                    "obtenerMayorDivisorComun", tipoArgumentos);
            metodo.setAccessible(true);
            int resultado = (int) metodo.invoke(fraccion, argumentos);
            assertTrue(resultado == resultadoCorrecto);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(FraccionTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Error al acceder a un método privado ObtenerMayorDivisorComun.");
        }
    }

    public void testCero(AbstractFraccion valor, boolean resultadoCorrecto) {
        try {
            Object[] argumentos = new Object[]{valor};
            Class<?>[] tipoArgumentos = new Class[]{AbstractFraccion.class};
            Class<?> claseFraccion = Fraccion.class;
            Method metodo = claseFraccion.getDeclaredMethod(
                    "esCero", tipoArgumentos);
            metodo.setAccessible(true);
            boolean resultado = (boolean) metodo.invoke(fraccion, argumentos);
            assertTrue(resultado == resultadoCorrecto);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(FraccionTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Error al acceder a un método privado esCero.");
        }
    }
}
