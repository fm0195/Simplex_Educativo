package pruebas;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
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
public class CrearNombreFilaTest {

    String nombre;
    int cantidad;

    public CrearNombreFilaTest(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Test
    public void test() {
        Object[] argumentos = new Object[]{cantidad, nombre};
        Class<?>[] tipoArgumentos = new Class<?>[]{int.class, String.class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "crearNombreFila";
        Object solucionador = new SolucionadorSimplex();
        String[] resultadoGenerado
                = (String[]) UtilPruebas.ingresarMetodoPrivado(argumentos, tipoArgumentos, clase, nombreMetodo, solucionador);
        assertTrue(resultadoGenerado[0].equals(nombre)
                && resultadoGenerado.length == cantidad);
    }

    @Parameterized.Parameters
    public static Collection parametros() throws IOException {
        return Arrays.asList(new Object[][]{
            {"x1", 3},
            {"s3", 2},
            {"-w", 1},
            {"x1", 5},
            {"s4", 4}
        });
    }
}
