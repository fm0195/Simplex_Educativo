package pruebas;

import controlador.AbstractControlador;
import controlador.IVista;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import vista.PantallaPasoIntermedio;
import vista.PantallaPrincipal;

public class PruebaSistema1 {

    private PantallaPrincipal pantallaPrincipal = null;

    public PruebaSistema1() {
    }

    @Before
    public void setUp() throws Exception {
        pantallaPrincipal = new PantallaPrincipal("");
        pantallaPrincipal.setVisible(true);
    }

    @Test
    public void test() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, AWTException, InterruptedException {
        JDialog dialogo;

        Field field = pantallaPrincipal.getClass().getDeclaredField("areaTexto");
        field.setAccessible(true);
        JTextArea area = (JTextArea) field.get(pantallaPrincipal);
        area.setText("(0) max z = -15/4 x1 + -10 x2 + x4 + x5 + x6\n"
                + "(1) -1 x1        <= -2\n"
                + "(2) -3     x2    >= -4\n"
                + "(3) -5 x1 + -6 x2 = -7");
        Thread.sleep(1000);
        if (area == null) {
            fail("Error no se encontro el área para ingreso del texto.");
            return;
        }

        field = pantallaPrincipal.getClass().getDeclaredField("radioFraccion");
        field.setAccessible(true);
        JRadioButton radioFraccion = (JRadioButton) field.get(pantallaPrincipal);
        if (radioFraccion == null) {
            fail("Error no se encontro el radio para tipo de salida.");
            return;
        }
        radioFraccion.doClick();
        Thread.sleep(1000);

        field = pantallaPrincipal.getClass().getDeclaredField("radioSimplex");
        field.setAccessible(true);
        JRadioButton radioSimplex = (JRadioButton) field.get(pantallaPrincipal);
        if (radioSimplex == null) {
            fail("Error no se encontro el radio para solucion Simplex.");
            return;
        }
        radioSimplex.doClick();
        Thread.sleep(1000);

        field = pantallaPrincipal.getClass().getDeclaredField("radioMostrarPasos");
        field.setAccessible(true);
        JRadioButton radioMostrarPasos = (JRadioButton) field.get(pantallaPrincipal);
        if (radioMostrarPasos == null) {
            fail("Error no se encontro el radio para solucion directa.");
            return;
        }

        field = pantallaPrincipal.getClass().getDeclaredField("botonSimplex");
        field.setAccessible(true);
        JButton solucionar = (JButton) field.get(pantallaPrincipal);
        solucionar.doClick();
        Thread.sleep(1000);
        if (solucionar == null) {
            fail("Error no se encontro el boton para solucionar.");
            return;
        }

        field = pantallaPrincipal.getClass().getDeclaredField("controlador");
        field.setAccessible(true);
        AbstractControlador controlador = (AbstractControlador) field.get(pantallaPrincipal);
        if (controlador == null) {
            fail("Error no se encontro el controlador.");
            return;
        }

        field = AbstractControlador.class.getDeclaredField("vista");
        field.setAccessible(true);
        PantallaPasoIntermedio pantallaSiguiente = (PantallaPasoIntermedio) (IVista) field.get(controlador);
        if (pantallaSiguiente == null) {
            fail("Error no se encontro la vista de paso a paso.");
            return;
        }

        field = pantallaSiguiente.getClass().getDeclaredField("botonSiguienteMatriz");
        field.setAccessible(true);
        JButton sgtePaso = (JButton) field.get(pantallaSiguiente);
        if (sgtePaso == null) {
            fail("Error no se encontro el boton para el siguiente paso.");
            return;
        }
        sgtePaso.doClick();
        Thread.sleep(1000);
        sgtePaso.doClick();
        Thread.sleep(1000);
        Robot bot = new Robot();
        bot.mouseMove(sgtePaso.getLocationOnScreen().x, sgtePaso.getLocationOnScreen().y);
        bot.mousePress(InputEvent.BUTTON1_MASK); //press the left mouse button
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(1000);

        field = pantallaSiguiente.getClass().getDeclaredField("labelResumen");
        field.setAccessible(true);
        JTextArea resumen = (JTextArea) field.get(pantallaSiguiente);
        if (solucionar == null) {
            fail("Error no se encontro el texto de resumen.");
            return;
        }
        String resultadoObtenido = resumen.getText();
        String resultadoCorrecto = "----------------------------------------------------------------------------------------\n" +
"  BVS       x1      x2      x4      x5      x6      s6      s7      a8      a9     RHS   \n" +
"----------------------------------------------------------------------------------------\n" +
"    -w      0       0       0       0       0       0       0       1       1       0    \n" +
"    z      15/4     10      -1      -1      -1      0       0       0       0       0    \n" +
"    a8      1       0       0       0       0       0       -1      1       0       2    \n" +
"    s6      0       3       0       0       0       1       0       0       0       4    \n" +
"    a9      5       6       0       0       0       0       0       0       1       7    \n" +
"----------------------------------------------------------------------------------------\n" +
"----------------------------------------------------------------------------------------\n" +
"  BVS       x1      x2      x4      x5      x6      s6      s7      a8      a9     RHS   \n" +
"----------------------------------------------------------------------------------------\n" +
"    -w      -1      0       0       0       0       0       1       0       1       -2   \n" +
"    z      15/4     10      -1      -1      -1      0       0       0       0       0    \n" +
"    a8      1       0       0       0       0       0       -1      1       0       2    \n" +
"    s6      0       3       0       0       0       1       0       0       0       4    \n" +
"    a9      5       6       0       0       0       0       0       0       1       7    \n" +
"----------------------------------------------------------------------------------------\n" +
"----------------------------------------------------------------------------------------\n" +
"  BVS       x1      x2      x4      x5      x6      s6      s7      a8      a9     RHS   \n" +
"----------------------------------------------------------------------------------------\n" +
"    -w      -6      -6      0       0       0       0       1       0       0       -9   \n" +
"    z      15/4     10      -1      -1      -1      0       0       0       0       0    \n" +
"    a8      1       0       0       0       0       0       -1      1       0       2    \n" +
"    s6      0       3       0       0       0       1       0       0       0       4    \n" +
"    a9      5       6       0       0       0       0       0       0       1       7    \n" +
"----------------------------------------------------------------------------------------\n" +
"---------------------------------------------------------------------------------------------------\n" +
"   BVS       x1       x2       x4       x5       x6       s6       s7       a8       a9       RHS   \n" +
"---------------------------------------------------------------------------------------------------\n" +
"    -w        0       6/5       0        0        0        0        1        0       6/5     -3/5   \n" +
"     z        0      11/2      -1       -1       -1        0        0        0      -3/4     -21/4  \n" +
"    a8        0      -6/5       0        0        0        0       -1        1      -1/5      3/5   \n" +
"    s6        0        3        0        0        0        1        0        0        0        4    \n" +
"    x1        1       6/5       0        0        0        0        0        0       1/5      7/5   \n" +
"---------------------------------------------------------------------------------------------------\n"
                + "";
        assertTrue(resultadoObtenido.equals(resultadoCorrecto));

    }
}
