
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
public class BuscarIndiceTest {
    String[] nombres;
    String nombre;
    int resultadoCorrecto;

    public BuscarIndiceTest(String[] nombres, String nombre, int resultadoCorrecto) {
        this.nombres = nombres;
        this.nombre = nombre;
        this.resultadoCorrecto = resultadoCorrecto;
    }
        
    @Test
    public void test() {
        Object[] argumentos = new Object[]{nombres,nombre};
        Class<?>[] tipoArgumentos = new Class<?>[]{String[].class,String.class};
        Class<?> clase = SolucionadorSimplex.class;
        String nombreMetodo = "buscarIndice";
        Object solucionador = new SolucionadorSimplex();
        int resultadoGenerado = 
                (int)UtilPruebas.ingresarMetodoPrivado(argumentos,tipoArgumentos,clase,nombreMetodo,solucionador);
        assertTrue(resultadoGenerado == resultadoCorrecto);
    }
    
    @Parameterized.Parameters
    public static Collection parametros() throws IOException {
        String[] nombres1 = new String[]{"x1", "x2"};
        String[] nombres2 = new String[]{"x1", "x2","s3","s4"};
        String[] nombres3 = new String[]{"x1", "x2","x3","s3","s4"};
        String[] nombres4 = new String[]{"x1"};
        String[] nombres5 = new String[]{"x1","s4"};
        return Arrays.asList(new Object[][]{
            {nombres1,"x1",0},
            {nombres2,"s3",2},
            {nombres3,"-w",-1},
            {nombres4,"x1",0},
            {nombres5,"s4",1}
        });
    }
}
