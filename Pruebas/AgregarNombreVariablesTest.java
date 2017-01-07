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
public class AgregarNombreVariablesTest {
    String[] nombres;
    int cantidad;
    String prefijo;
    String[] resultadoCorrecto;

    public AgregarNombreVariablesTest(String[] nombres, int cantidad, String prefijo, String[] resultadoCorrecto) {
        this.nombres = nombres;
        this.cantidad = cantidad;
        this.prefijo = prefijo;
        this.resultadoCorrecto = resultadoCorrecto;
    }
     
    @Test
    public void test() {
        Object[] argumentos = new Object[]{nombres,cantidad,prefijo};
        Class<?>[] tipoArgumentos = new Class<?>[]{String[].class,int.class, String.class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "agregarNombreVariables";
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
        return Arrays.asList(new Object[][]{
            {nombres1,2,"s",new String[]{"x1", "x2","s3","s4"}},
            {nombres1,2,"a",new String[]{"x1", "x2","a3","a4"}},
            {nombres2,2,"a",new String[]{"x1", "x2","s3","s4","a5","a6"}},
            {nombres2,3,"s",new String[]{"x1", "x2","s3","s4","s5","s6","s7"}},
            {nombres3,1,"a",new String[]{"x1", "x2","x3","s3","s4","a6"}}
        });
    }
}
