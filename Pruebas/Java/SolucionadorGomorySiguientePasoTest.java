package pruebas;

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
public class SolucionadorGomorySiguientePasoTest {

    Object pasoAnterior;
    Object solucion;

    public SolucionadorGomorySiguientePasoTest(Object pasoAnterior, Object solucion) {
        this.pasoAnterior = pasoAnterior;
        this.solucion = solucion;
    } 

    @Test
    public void testSolucionar() {
        SolucionadorGomory s = new SolucionadorGomory();
        DtoSimplex resultadoObtenido = s.siguientePaso((DtoSimplex)pasoAnterior);
        boolean igualSolucion = compararDtoSimplex(resultadoObtenido, (DtoSimplex)solucion);
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
        Object[] pasos1 = obtenerPasos("test\\pruebas\\gomory1.objt");
        Object[] pasos2 = obtenerPasos("test\\pruebas\\gomory2.objt");
        Object[] pasos3 = obtenerPasos("test\\pruebas\\gomory3.objt");
        Object[] pasos4 = obtenerPasos("test\\pruebas\\gomory4.objt");
        Object[] pasos5 = obtenerPasos("test\\pruebas\\gomory5.objt");
        return Arrays.asList(new Object[][]{
            {pasos1[6], pasos1[7]},
            {pasos2[1], pasos2[2]},
            {pasos3[1], pasos3[2]},
            {pasos4[0], pasos4[1]},
            {pasos5[1], pasos5[2]}
        });
    }

}
