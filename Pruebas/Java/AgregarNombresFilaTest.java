package pruebas;

import java.io.IOException;
import java.util.ArrayList;
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
public class AgregarNombresFilaTest {

    String[] nombres;
    ArrayList<Integer> indices;
    String prefijo;
    int indiceInicio;
    String[] resultadoCorrecto;

    public AgregarNombresFilaTest(String[] nombres, ArrayList<Integer> indices,
            String prefijo, int indiceInicio, String[] resultadoCorrecto) {
        this.nombres = nombres;
        this.indices = indices;
        this.prefijo = prefijo;
        this.indiceInicio = indiceInicio;
        this.resultadoCorrecto = resultadoCorrecto;
    }

    @Test
    public void test() {
        Object[] argumentos = new Object[]{nombres, indices, prefijo, indiceInicio};
        Class<?>[] tipoArgumentos = new Class<?>[]{String[].class, ArrayList.class, String.class, int.class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "agregarNombresFila";
        Object solucionador = new SolucionadorSimplex();
        String[] resultadoGenerado
                = (String[]) UtilPruebas.ingresarMetodoPrivado(argumentos, tipoArgumentos, clase, nombreMetodo, solucionador);
        assertTrue(UtilPruebas.arreglosIguales(resultadoGenerado, resultadoCorrecto));
    }

    @Parameterized.Parameters
    public static Collection parametros() throws IOException {
        String[] nombres1 = new String[]{"x1", "x2"};
        String[] nombres2 = new String[]{"x1", "x2", "s3", "s4"};
        String[] nombres3 = new String[]{"x1", "x2", "x3", "s3", "s4"};
        return Arrays.asList(new Object[][]{
            {nombres1.clone(), new ArrayList<>(Arrays.asList(1)),
                "a", 2, new String[]{"x1", "a2"}},
            {nombres2.clone(), new ArrayList<>(Arrays.asList(0, 3)),
                "a", 10, new String[]{"a10", "x2", "s3", "a11"}},
            {nombres2.clone(), new ArrayList<>(Arrays.asList(1)),
                "a", 1, new String[]{"x1", "a1", "s3", "s4"}},
            {nombres3.clone(), new ArrayList<>(Arrays.asList(1, 2, 3)),
                "a", 3, new String[]{"x1", "a3", "a4", "a5", "s4"}},
            {nombres3.clone(), new ArrayList<>(Arrays.asList(0, 4)),
                "a", 9, new String[]{"a9", "x2", "x3", "s3", "a10"}}
        });
    }

}
