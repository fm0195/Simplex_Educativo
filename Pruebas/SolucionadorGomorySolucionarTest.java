package pruebas;

import pruebas.*;
import dto.DtoSimplex;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import modelo.AbstractSolucionadorSimplex;
import modelo.SolucionadorGomory;
import modelo.SolucionadorSimplex;
import modelo.parser.IParser;
import modelo.parser.SimplexParser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yordan Jiménez
 */
@RunWith(Parameterized.class)
public class SolucionadorGomorySolucionarTest {

    DtoSimplex problema;
    Object[] solucion;

    public SolucionadorGomorySolucionarTest(DtoSimplex problema, Object[] resultado) {
        this.problema = problema;
        this.solucion = resultado;
    }

    @Test
    public void testSolucionar() {
        AbstractSolucionadorSimplex s = new SolucionadorGomory();
        problema = s.completarProblema(problema);
        ArrayList<DtoSimplex> resultado = s.solucionar(problema);
        if (solucion.length != resultado.size()) {
            fail("El solucionador no ha brindado un solución con la cantidad correcta"
                    + "de iteraciones");
        }
        boolean igualSolucion = true;
        for (int i = 0; i < solucion.length; i++) {
            DtoSimplex solucionCorrecta = (DtoSimplex)solucion[i];
            DtoSimplex solucionObtenida = resultado.get(i);
            if (!compararDtoSimplex(solucionCorrecta, solucionObtenida)) {
                igualSolucion = false;
                break;
            }
        }
        assertTrue(igualSolucion);
    }

    private boolean compararDtoSimplex(DtoSimplex dto1, DtoSimplex dto2) {
        boolean resultado = dto1.esAcotado() == dto2.esAcotado();
        resultado &= dto1.esBloqueoDosFases() == dto2.esBloqueoDosFases();
        resultado &= dto1.esDosfases() == dto2.esDosfases();
        resultado &= dto1.esFactible() == dto2.esFactible();
        resultado &= dto1.esFinalizado() == dto2.esFinalizado();
        resultado &= dto1.getCoordenadaPivote().equals(dto2.getCoordenadaPivote());
        resultado &= UtilPruebas.arreglosIguales(dto1.getNombreColumnas(), dto2.getNombreColumnas());
        resultado &= UtilPruebas.arreglosIguales(dto1.getNombreFilas(), dto2.getNombreFilas());
        resultado &= dto1.getOperaciones().equals(dto2.getOperaciones());
        resultado &= dto1.getVariablesBasicas() == dto2.getVariablesBasicas();
        resultado &= dto1.getVariablesHolgura() == dto2.getVariablesHolgura();
        resultado &= dto1.getArtificialActual() == dto2.getArtificialActual();
        resultado &= UtilPruebas.matricesIguales(dto1.getMatriz(), dto2.getMatriz());
        return resultado;
    }
    
    private static Object[] obtenerPasos(String nombre) throws IOException, ClassNotFoundException{
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombre));
        ArrayList<DtoSimplex> resultado = (ArrayList<DtoSimplex>) entrada.readObject();
        entrada.close();
        return resultado.toArray();
    }

    @Parameterized.Parameters
    public static Collection parametros() throws IOException, ClassNotFoundException {
        IParser parser = new SimplexParser();
        DtoSimplex dto1 = parser.parse("max z = 3 x1 + 4 x2\n"
                + "2 x1 +   x2 <= 6\n"
                + "2 x1 + 3 x2 <= 9");
        dto1.setVariablesBasicas();
        DtoSimplex dto2 = parser.parse("max z = -1 x1 - 2 x2\n"
                + "3 x1 + 4 x2 <= 20\n"
                + "2 x1 - 1 x2 >= 2");
        dto2.setVariablesBasicas();
        DtoSimplex dto3 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1          <= 2\n"
                + "(2)                  1 x2   = 3\n"
                + "(3)         -1 x1 +  1 x2  <= 0");
        dto3.setVariablesBasicas();
        DtoSimplex dto4 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1         <= 2\n"
                + "(2)                  1 x2 <= 3\n"
                + "(3)          1 x1 +  1 x2 <= 4");
        dto4.setVariablesBasicas();
        DtoSimplex dto5 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1          <= 2\n"
                + "(2)          1 x1 -  1 x2  <= 0");
        dto5.setVariablesBasicas();
        Object[] pasos1 = obtenerPasos("test\\pruebas\\gomory1.objt");
        Object[] pasos2 = obtenerPasos("test\\pruebas\\gomory2.objt");
        Object[] pasos3 = obtenerPasos("test\\pruebas\\gomory3.objt");
        Object[] pasos4 = obtenerPasos("test\\pruebas\\gomory4.objt");
        Object[] pasos5 = obtenerPasos("test\\pruebas\\gomory5.objt");
        return Arrays.asList(new Object[][]{
            {dto1, pasos1},
            {dto2, pasos2},
            {dto3, pasos3},
            {dto4, pasos4},
            {dto5, pasos5}
        });
    }

}
