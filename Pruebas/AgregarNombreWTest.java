
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
public class AgregarNombreWTest {
    String[] nombres;
    String[] resultadoCorrecto;

    public AgregarNombreWTest(String[] nombres, String[] resultadoCorrecto) {
        this.nombres = nombres;
        this.resultadoCorrecto = resultadoCorrecto;
    }
        
    @Test
    public void test() {
        Object[] argumentos = new Object[]{nombres};
        Class<?>[] tipoArgumentos = new Class<?>[]{String[].class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "agregarNombreW";
        Object solucionador = new SolucionadorSimplex();
        String[]resultadoGenerado = 
                (String[])UtilPruebas.ingresarMetodoPrivado(argumentos,tipoArgumentos,clase,nombreMetodo,solucionador);
        assertTrue(UtilPruebas.arreglosIguales(resultadoGenerado, resultadoCorrecto));
    }
    
    @Parameterized.Parameters
    public static Collection parametros() throws IOException {
        String[] nombres1 = new String[]{"x1", "x2"};
        String[] nombres2 = new String[]{"x1", "x2","s3","s4"};
        String[] nombres3 = new String[]{"x1", "x2","x3","s3","s4"};
        String[] nombres4 = new String[]{"x1"};
        String[] nombres5 = new String[]{"x1","s4"};
        return Arrays.asList(new Object[][]{
            {nombres1,new String[]{"-w","x1", "x2"}},
            {nombres2,new String[]{"-w","x1", "x2","s3","s4"}},
            {nombres3,new String[]{"-w","x1", "x2","x3","s3","s4"}},
            {nombres4,new String[]{"-w","x1"}},
            {nombres5,new String[]{"-w","x1","s4"}}
        });
    }
}
