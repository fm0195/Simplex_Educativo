package pruebas;

import modelo.AbstractFraccion;
import modelo.DtoSimplex;
import modelo.parser.IParser;
import modelo.parser.Parser;
import modelo.parser.sym;
import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.junit.Before;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParserTest {
    IParser parser;
    String problema;
    double[][] resultadoEsperado;
    int[] desigualdadesEsperadas;
    
    @Before
    public void initialize() {
       this.parser = new Parser();
    }

    public ParserTest(String problema, double[][] resultadoEsperado, int[] desigualdadesEsperadas) {
        this.problema = problema;
        this.resultadoEsperado = resultadoEsperado;
        this.desigualdadesEsperadas = desigualdadesEsperadas;
    }
    
    
    @Parameterized.Parameters
    public static Collection parametros(){
        String[] problemas = 
        {  
            "max z = 1 x1 + 2 x2\n" +
            " 1 x1 + 1 x2 >= 1\n" +
            "-1 x1 + 1 x2 >= 3\n" +
            "        1 x2 <= 5"
            , 
            
            "max z = 2 x1 - 1 x2 + 1 x3\n" +
            "1 x1 + 1 x2 - 2 x3 <= 8\n" +
            "4 x1 - 1 x2 + 1 x3 = 2\n" +
            "2 x1 + 3 x2 - 1 x3 >= 4"
            , 
            
            "min z = -2 x1 + 2 x2 + 1 x3 + 1 x4\n" +
            "1 x1 + 2 x2 + 1 x3 + 1 x4 <= 2\n" +
            "1 x1 - 1 x2 + 1 x3 + 5 x4 >= 4\n" +
            "2 x1 - 1 x2 + 1 x3        >= 2"
            ,
            
            "min z = 1 x3 + 1 x4 + 1 x5\n" +
            "1 x1 - 1 x3 + 1 x4 - 1 x5 = -2\n" +
            "1 x2 - 1 x3 - 1 x4 + 1 x5 =  1"
            ,
            "max z = 3 x1 + 2 x2\n" +
            "0.25 x1 + 0.30 x2 <= 30\n" +
            "0.50 x1 + 0.20 x2 <= 40\n" +
            "0.10 x1 + 0.20 x2 <= 20"
            ,
            "max z = 3/2 x1 + 2/7 x2\n" +
            "25/8 x1 + 32/51 x2 >= 45/2\n" +
            "8/5 x1 + 1/5 x2 = 40/87 \n" +
            "10/3 x1 + 7/8 x2 <= 4/3"
            ,
            "max z = 3/2 x1 + 2/7 x2\n" +
            "25/8 x3 + 32/51 x4 >= 45/2\n" +
            "8/5 x2 + 1/5 x5 = 40/87 \n" +
            "10/3 x1 + 7/8 x5 <= 4/3"
        };
        double[][][] resultadosEsperados =
        {
            {
                {1, 2, 0},
                {1, 1, 1},
                {-1, 1, 3},
                {0, 1, 5}
            }
            ,
            {
                {2, -1, 1, 0},
                {1, 1, -2, 8},
                {4, -1, 1, 2},
                {2, 3, -1, 4}
            }
            ,
            {
                {-2, 2, 1, 1, 0},
                {1, 2, 1, 1, 2},
                {1, -1, 1, 5, 4},
                {2, -1, 1, 0, 2}
            }
            ,
            {
                {0, 0, 1, 1, 1, 0},
                {1, 0, -1, 1, -1, -2},
                {0, 1, -1, -1, 1, 1},
            }
            ,
            {
                {3, 2, 0},
                {0.25, 0.30, 30},
                {0.5, 0.2, 40},
                {0.10, 0.20, 20}
            }
            ,
            {
                {3/2, 2/7, 0},
                {25/8, 32/51, 45/2},
                {8/5, 1/5, 40/87},
                {10/3, 7/8, 4/3}
            }
            ,
            {
                {3/2, 2/7, 0, 0, 0, 0},
                {0, 0, 25/8, 32/51, 0, 45/2},
                {0, 8/5, 0, 0, 1/5, 40/87},
                {10/3, 0, 0, 0, 7/8, 4/3}
            }
        };
        int[][] desigualdadesEsperadas = {
            {sym.MAYORIGUAL, sym.MAYORIGUAL, sym.MENORIGUAL},
            {sym.MENORIGUAL, sym.IGUAL, sym.MAYORIGUAL},
            {sym.MENORIGUAL, sym.MAYORIGUAL, sym.MAYORIGUAL},
            {sym.IGUAL, sym.IGUAL},
            {sym.MENORIGUAL,sym.MENORIGUAL, sym.MENORIGUAL},
            {sym.MAYORIGUAL,sym.IGUAL, sym.MENORIGUAL},
            {sym.MAYORIGUAL,sym.IGUAL, sym.MENORIGUAL}
        };
        return Arrays.asList(new Object[][] {
         { problemas[0], resultadosEsperados[0], desigualdadesEsperadas[0] },
         { problemas[1], resultadosEsperados[1], desigualdadesEsperadas[1] },
         { problemas[2], resultadosEsperados[2], desigualdadesEsperadas[2] },
         { problemas[3], resultadosEsperados[3], desigualdadesEsperadas[3] },
         { problemas[4], resultadosEsperados[4], desigualdadesEsperadas[4] }
        });
    }
    
    @Test
    public void test() {
        DtoSimplex resultado = parser.parse(problema);
        assert(compararResultados(resultado, resultadoEsperado, desigualdadesEsperadas));
    }

    private boolean compararResultados(DtoSimplex resultado, double[][] resultadosEsperados, int[] desigualdadesEsperadas) {
        for (int i = 0; i < resultadosEsperados.length; i++) {
            for (int j = 0; j < resultadosEsperados[i].length; j++) {
                AbstractFraccion fraccion = resultado.getMatriz()[i][j];
                double resultadoObtenido = valorFraccion(fraccion);
                double resultadoEsperado = resultadosEsperados[i][j];
                double diferencia = Math.abs(resultadoEsperado - resultadoObtenido);
                if (diferencia > 0.00001) {
                    return false;
                }
            }
        }
        for (int i = 0; i < desigualdadesEsperadas.length; i++) {
            int desigualdadEsperada = desigualdadesEsperadas[i];
            int desigualdadObtenida = resultado.getListaDesigualdades()[i];
            if(desigualdadEsperada != desigualdadObtenida)
                return false;
        }
        return true;
    }
        
    private double valorFraccion(AbstractFraccion fraccion){
        return fraccion.getNumerador() / fraccion.getDenominador();
    }
    
    
}

