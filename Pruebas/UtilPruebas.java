package pruebas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.AbstractFraccion;
import static org.junit.Assert.fail;

/**
 *
 * @author Yordan Jimenez
 */
public class UtilPruebas {

    public static boolean matricesIguales(AbstractFraccion[][] matriz1, AbstractFraccion[][] matriz2) {
        if (matriz1.length != matriz2.length) {
            return false;
        }
        for (int contadorFila = 0; contadorFila < matriz1.length; contadorFila++) {
            if (matriz1[contadorFila].length != matriz2[contadorFila].length) {
                return false;
            }
            for (int contadorColumna = 0; contadorColumna < matriz2[0].length; contadorColumna++) {
                if (!matriz1[contadorFila][contadorColumna].iguales(matriz2[contadorFila][contadorColumna])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean arreglosIguales(String[] arreglo1, String[] arreglo2) {
        if (arreglo1.length != arreglo2.length) {
            return false;
        }
        for (int contador = 0; contador < arreglo2.length; contador++) {
            if (!arreglo2[contador].equals(arreglo1[contador])) {
                return false;
            };
        }
        return true;
    }

    public static boolean arreglosIguales(AbstractFraccion[] arreglo1, AbstractFraccion[] arreglo2) {
        if (arreglo1.length != arreglo2.length) {
            return false;
        }
        for (int contador = 0; contador < arreglo1.length; contador++) {
            if (!arreglo1[contador].iguales(arreglo2[contador])) {
                return false;
            }
        }
        return true;
    }
    public static Object ingresarMetodoPrivado(Object[] argumentos, Class<?>[] tipoArgumentos,
            Class<?> clase,String nombreMetodo, Object instancia){
        try {
            Method metodo = clase.getDeclaredMethod(
                    nombreMetodo, tipoArgumentos);
            metodo.setAccessible(true);
            return metodo.invoke(instancia, argumentos);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(FraccionTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Error al acceder a un método privado "+nombreMetodo);
        }
        return null;
    }
    public static AbstractFraccion[][] clonarMatriz(AbstractFraccion[][] matriz) {
        AbstractFraccion[][] resultado = new AbstractFraccion[matriz.length][matriz[0].length];
        for (int contadorFila = 0; contadorFila < resultado.length; contadorFila++) {
            for (int contadorColumna = 0; contadorColumna < resultado[0].length; contadorColumna++) {
                resultado[contadorFila][contadorColumna] = matriz[contadorFila][contadorColumna].clonar();
            }
        }
        return resultado;
    }
}
