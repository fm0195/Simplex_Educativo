package pruebas;

import dto.DtoSimplex;
import pruebas.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import modelo.AbstractFraccion;
import modelo.Fraccion;
import modelo.parser.Parser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yordan Jiménez
 */
@RunWith(Parameterized.class)
public class NodoBranchAndBoundTest {

    Object nodo;
    String restriccion;
    String todasRestricciones;
    String espacio;
    String valorVariables;
    int cantidad;
    String repetido;
    Class<?> NodoBranchAndBound;

    public NodoBranchAndBoundTest(Object nodo, String restriccion, String todasRestricciones, String espacio, String valorVariables, int cantidad, String repetido) throws ClassNotFoundException {
        this.nodo = nodo;
        this.restriccion = restriccion;
        this.todasRestricciones = todasRestricciones;
        this.espacio = espacio;
        this.valorVariables = valorVariables;
        this.cantidad = cantidad;
        this.repetido = repetido;
        this.NodoBranchAndBound = Class.forName("modelo.NodoBranchAndBound");
    }

    @Test
    public void obtenerRestricciontest() {
        String resultadoObtenido
                = (String) UtilPruebas.ingresarMetodoPrivado(new Object[]{}, new Class<?>[]{},
                NodoBranchAndBound, "obtenerRestriccion", nodo);
        assertTrue(resultadoObtenido.equals(restriccion));
    }

    @Test
    public void obtenerTodasRestriccionestest() {
        String resultadoObtenido
                = (String) UtilPruebas.ingresarMetodoPrivado(new Object[]{}, new Class<?>[]{},
                NodoBranchAndBound, "obtenerTodasRestricciones", nodo);
        assertTrue(resultadoObtenido.equals(todasRestricciones));
    }

    @Test
    public void valorVariablestest() {
        String resultadoObtenido
                = (String) UtilPruebas.ingresarMetodoPrivado(new Object[]{espacio}, new Class<?>[]{String.class},
                NodoBranchAndBound, "valorVariables", nodo);
        assertTrue(resultadoObtenido.equals(valorVariables));
    }

    @Test
    public void toStringRepeattest() {
        String resultadoObtenido
                = (String) UtilPruebas.ingresarMetodoPrivado(new Object[]{cantidad}, new Class<?>[]{int.class},
                NodoBranchAndBound, "toStringRepeat", nodo);
        assertTrue(resultadoObtenido.equals(repetido));
    }

    @Parameterized.Parameters
    public static Collection parametros() throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Parser parser = new Parser();
        DtoSimplex dto1 = parser.parse("max z = 3 x1 + 4 x2\n"
                + "2 x1 +   x2 <= 6\n"
                + "2 x1 + 3 x2 <= 9");
        AbstractFraccion[] valores1 = new AbstractFraccion[]{
            new Fraccion(1),
            new Fraccion(2, 5)
        };
        AbstractFraccion valorZ1 = new Fraccion(21, 5);
        int restriccion1 = 5;
        String indiceProblema1 = "8.2";
        AbstractFraccion valorRestriccion1 = new Fraccion(5, 58);
        int variable1 = 0;
        DtoSimplex dto2 = parser.parse("min z = x1 + x2\n"
                + " 2 x1 + 2 x2 >= 5\n"
                + "12 x1 + 5 x2 <= 30");
        AbstractFraccion[] valores2 = new AbstractFraccion[]{
            new Fraccion(7),
            new Fraccion(6, 5)
        };
        AbstractFraccion valorZ2 = new Fraccion(5);
        int restriccion2 = 6;
        String indiceProblema2 = "2.2";
        int variable2 = 0;
        AbstractFraccion valorRestriccion2 = new Fraccion(58);
        DtoSimplex dto3 = parser.parse("max z = -1 x1 - 2 x2\n"
                + "3 x1 + 4 x2 <= 20\n"
                + "2 x1 - 1 x2 >= 2");
        AbstractFraccion[] valores3 = new AbstractFraccion[]{
            new Fraccion(4, 5),
            new Fraccion(5)
        };
        AbstractFraccion valorZ3 = new Fraccion(85);
        int restriccion3 = 5;
        String indiceProblema3 = "1.2";
        int variable3 = 1;
        AbstractFraccion valorRestriccion3 = new Fraccion(5, 8);
        DtoSimplex dto4 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1          <= 2\n"
                + "(2)                  1 x2   = 3\n"
                + "(3)         -1 x1 +  1 x2  <= 0");
        AbstractFraccion[] valores4 = new AbstractFraccion[]{
            new Fraccion(7),
            new Fraccion(2, 6)
        };
        AbstractFraccion valorZ4 = new Fraccion(215);
        int restriccion4 = 6;
        String indiceProblema4 = "2.1.1";
        int variable4 = 0;
        AbstractFraccion valorRestriccion4 = new Fraccion(97);
        DtoSimplex dto5 = parser.parse("(0) max z = 15 x1 + 10 x2\n"
                + "(1)          1 x1          <= 2\n"
                + "(2)          1 x1 -  1 x2  <= 0");
        AbstractFraccion[] valores5 = new AbstractFraccion[]{
            new Fraccion(1),
            new Fraccion(1)
        };
        AbstractFraccion valorZ5 = new Fraccion(1);
        int restriccion5 = 5;
        String indiceProblema5 = "1.2";
        int variable5 = 0;
        AbstractFraccion valorRestriccion5 = new Fraccion(8);

        Class<?> claseNodo = Class.forName("modelo.NodoBranchAndBound");
        Class<?>[] parametrosConstructor = new Class<?>[]{DtoSimplex.class, claseNodo};
        Constructor constructorNodo = claseNodo.getConstructor(parametrosConstructor);
        constructorNodo.setAccessible(true);
        Object[] parametros = new Object[]{dto1, null};
        Object nodo1 = constructorNodo.newInstance(parametros);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{true},
                new Class<?>[]{boolean.class},
                claseNodo,
                "setFactible",
                nodo1);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{true},
                new Class<?>[]{boolean.class},
                claseNodo,
                "setAcotado",
                nodo1);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema1},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo1);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{variable1},
                new Class<?>[]{int.class},
                claseNodo,
                "setIndiceVariableRestrccion",
                nodo1);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema1},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo1);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{restriccion1},
                new Class<?>[]{int.class},
                claseNodo,
                "setRestricciion",
                nodo1);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valores1},
                new Class<?>[]{AbstractFraccion[].class},
                claseNodo,
                "setValorVariables",
                nodo1);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorZ1},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorZ",
                nodo1);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorRestriccion1},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorRestriccion",
                nodo1);

        parametros = new Object[]{dto2, null};
        Object nodo2 = constructorNodo.newInstance(parametros);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema2},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo2);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{variable2},
                new Class<?>[]{int.class},
                claseNodo,
                "setIndiceVariableRestrccion",
                nodo2);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema2},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo2);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{restriccion2},
                new Class<?>[]{int.class},
                claseNodo,
                "setRestricciion",
                nodo2);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valores2},
                new Class<?>[]{AbstractFraccion[].class},
                claseNodo,
                "setValorVariables",
                nodo2);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorZ2},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorZ",
                nodo2);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorRestriccion2},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorRestriccion",
                nodo2);

        parametros = new Object[]{dto3, null};
        Object nodo3 = constructorNodo.newInstance(parametros);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{true},
                new Class<?>[]{boolean.class},
                claseNodo,
                "setFactible",
                nodo3);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema3},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo3);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{variable3},
                new Class<?>[]{int.class},
                claseNodo,
                "setIndiceVariableRestrccion",
                nodo3);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema3},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo3);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{restriccion3},
                new Class<?>[]{int.class},
                claseNodo,
                "setRestricciion",
                nodo3);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valores3},
                new Class<?>[]{AbstractFraccion[].class},
                claseNodo,
                "setValorVariables",
                nodo3);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorZ3},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorZ",
                nodo3);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorRestriccion3},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorRestriccion",
                nodo3);

        parametros = new Object[]{dto4, nodo3};
        Object nodo4 = constructorNodo.newInstance(parametros);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{true},
                new Class<?>[]{boolean.class},
                claseNodo,
                "setFactible",
                nodo4);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema4},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo4);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{variable4},
                new Class<?>[]{int.class},
                claseNodo,
                "setIndiceVariableRestrccion",
                nodo4);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema4},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo4);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{restriccion4},
                new Class<?>[]{int.class},
                claseNodo,
                "setRestricciion",
                nodo4);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valores4},
                new Class<?>[]{AbstractFraccion[].class},
                claseNodo,
                "setValorVariables",
                nodo4);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorZ4},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorZ",
                nodo4);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorRestriccion4},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorRestriccion",
                nodo4);

        parametros = new Object[]{dto5, nodo4};
        Object nodo5 = constructorNodo.newInstance(parametros);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{true},
                new Class<?>[]{boolean.class},
                claseNodo,
                "setFactible",
                nodo5);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema5},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo5);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{variable5},
                new Class<?>[]{int.class},
                claseNodo,
                "setIndiceVariableRestrccion",
                nodo5);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{indiceProblema3},
                new Class<?>[]{String.class},
                claseNodo,
                "setIndiceProblema",
                nodo5);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{restriccion5},
                new Class<?>[]{int.class},
                claseNodo,
                "setRestricciion",
                nodo5);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valores5},
                new Class<?>[]{AbstractFraccion[].class},
                claseNodo,
                "setValorVariables",
                nodo5);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorZ5},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorZ",
                nodo5);
        UtilPruebas.ingresarMetodoPrivado(new Object[]{valorRestriccion5},
                new Class<?>[]{AbstractFraccion.class},
                claseNodo,
                "setValorRestriccion",
                nodo5);

        String s = (String) UtilPruebas.ingresarMetodoPrivado(new Object[]{""},
                new Class<?>[]{String.class},
                claseNodo,
                "valorVariables",
                nodo2);
        System.out.println(s);

        return Arrays.asList(new Object[][]{
            {nodo1, "No es necesario.\n", "No es necesario.\n", " ",
                " z = 21/5\n"
                + " x1 = 1\n"
                + " x2 = 2/5\n"
                + "",
                5,
                "     Problema 8.2\n"
                + "       z = 21/5\n"
                + "       x1 = 1\n"
                + "       x2 = 2/5\n"
                + "       Restricción: No es necesario.\n"
                + "       *Problema Acotado\n"
                + "\n"
                + ""},
            {nodo2, "No es necesario.\n", "No es necesario.\n", "",
                "z = -5\n"
                + "x1 = 7\n"
                + "x2 = 6/5\n"
                + "",
                4,
                "    Problema 2.2\n"
                + "      Restricción: No es necesario.\n"
                + "      *Problema No Factible\n"
                + "\n"
                + ""},
            {nodo3, "No es necesario.\n", "No es necesario.\n", "   ",
                "   z = 85\n"
                + "   x1 = 4/5\n"
                + "   x2 = 5\n"
                + "",
                3,
                "   Problema 1.2\n"
                + "     z = 85\n"
                + "     x1 = 4/5\n"
                + "     x2 = 5\n"
                + "     Restricción: No es necesario.\n"
                + "\n"
                + ""},
            {nodo4, "x1 <= 97\n", "x1 <= 97\n", "    ",
                "    z = 215\n"
                + "    x1 = 7\n"
                + "    x2 = 1/3\n"
                + "",
                2,
                "  Problema 2.1.1\n"
                + "    z = 215\n"
                + "    x1 = 7\n"
                + "    x2 = 1/3\n"
                + "    Restricción: x1 <= 97\n"
                + "\n"},
            {nodo5, "x1 >= 8\n", "x1 <= 97\n"
                + "x1 >= 8\n", " ",
                " z = 1\n"
                + " x1 = 1\n"
                + " x2 = 1\n"
                + "", 1,
                " Problema 1.2\n"
                + "   z = 1\n"
                + "   x1 = 1\n"
                + "   x2 = 1\n"
                + "   Restricción: x1 >= 8\n"
                + "\n"}
        });
    }
}
