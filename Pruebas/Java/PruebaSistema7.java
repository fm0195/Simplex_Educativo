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
import org.junit.Before;
import org.junit.Test;
import vista.PantallaPasoIntermedio;
import vista.PantallaPrincipal;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PruebaSistema7 {

    private PantallaPrincipal pantallaPrincipal = null;

    public PruebaSistema7() {
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
        if (area == null) {
            fail("Error no se encontro el área para ingreso del texto.");
            return;
        }
        area.setText("max z = 3 x1 + 4 x2\n"
                + "2 x1 +   x2 <= 6\n"
                + "2 x1 + 3 x2 <= 9");
        Thread.sleep(1000);

        field = pantallaPrincipal.getClass().getDeclaredField("radioFraccion");
        field.setAccessible(true);
        JRadioButton radioFraccion = (JRadioButton) field.get(pantallaPrincipal);
        if (radioFraccion == null) {
            fail("Error no se encontro el radio para tipo de salida.");
            return;
        }
        radioFraccion.doClick();
        Thread.sleep(1000);

        field = pantallaPrincipal.getClass().getDeclaredField("radioGomory");
        field.setAccessible(true);
        JRadioButton radioGomory = (JRadioButton) field.get(pantallaPrincipal);
        if (radioGomory == null) {
            fail("Error no se encontro el radio para solucion Gomory.");
            return;
        }
        radioGomory.doClick();
        Thread.sleep(1000);

        field = pantallaPrincipal.getClass().getDeclaredField("radioSolucionDirecta");
        field.setAccessible(true);
        JRadioButton radioSolucionDirecta = (JRadioButton) field.get(pantallaPrincipal);
        if (radioSolucionDirecta == null) {
            fail("Error no se encontro el radio para solucion directa.");
            return;
        }
        radioSolucionDirecta.doClick();
        Thread.sleep(1000);

        field = pantallaPrincipal.getClass().getDeclaredField("botonSimplex");
        field.setAccessible(true);
        JButton solucionar = (JButton) field.get(pantallaPrincipal);
        if (solucionar == null) {
            fail("Error no se encontro el boton para solucionar.");
            return;
        }
        Thread.sleep(1000);
        Robot bot = new Robot();
        bot.mouseMove(solucionar.getLocationOnScreen().x, solucionar.getLocationOnScreen().y);
        bot.mousePress(InputEvent.BUTTON1_MASK); //press the left mouse button
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(1000);

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

        field = pantallaSiguiente.getClass().getDeclaredField("labelResumen");
        field.setAccessible(true);
        JTextArea resumen = (JTextArea) field.get(pantallaSiguiente);
        if (resumen == null) {
            fail("Error no se encontro el texto de resumen.");
            return;
        }
        String resultadoObtenido = resumen.getText();
        String resultadoCorrecto = "------------------------------------\n"
                + " BVS     x1    x2    s3    s4   RHS  \n"
                + "------------------------------------\n"
                + "   z     -3    -4    0     0     0   \n"
                + "   s3    2     1     1     0     6   \n"
                + "   s4    2     3     0     1     9   \n"
                + "------------------------------------\n"
                + "------------------------------------------------\n"
                + "  BVS       x1      x2      s3      s4     RHS   \n"
                + "------------------------------------------------\n"
                + "    z      -1/3     0       0      4/3      12   \n"
                + "    s3     4/3      0       1      -1/3     3    \n"
                + "    x2     2/3      1       0      1/3      3    \n"
                + "------------------------------------------------\n"
                + "------------------------------------------------\n"
                + "  BVS       x1      x2      s3      s4     RHS   \n"
                + "------------------------------------------------\n"
                + "    z       0       0      1/4     5/4     51/4  \n"
                + "    x1      1       0      3/4     -1/4    9/4   \n"
                + "    x2      0       1      -1/2    1/2     3/2   \n"
                + "------------------------------------------------\n"
                + "----------------------------------------------------------------\n"
                + "  BVS       x1      x2      s3      s4      s5      a6     RHS   \n"
                + "----------------------------------------------------------------\n"
                + "    -w      0       0       0       0       0       1       0    \n"
                + "    z       0       0      1/4     5/4      0       0      51/4  \n"
                + "    x1      1       0      3/4     -1/4     0       0      9/4   \n"
                + "    x2      0       1      -1/2    1/2      0       0      3/2   \n"
                + "    a6      0       0      1/2     1/2      -1      1      1/2   \n"
                + "----------------------------------------------------------------\n"
                + "----------------------------------------------------------------\n"
                + "  BVS       x1      x2      s3      s4      s5      a6     RHS   \n"
                + "----------------------------------------------------------------\n"
                + "    -w      0       0      -1/2    -1/2     1       0      -1/2  \n"
                + "    z       0       0      1/4     5/4      0       0      51/4  \n"
                + "    x1      1       0      3/4     -1/4     0       0      9/4   \n"
                + "    x2      0       1      -1/2    1/2      0       0      3/2   \n"
                + "    a6      0       0      1/2     1/2      -1      1      1/2   \n"
                + "----------------------------------------------------------------\n"
                + "------------------------------------------------------------------------\n"
                + "  BVS       x1      x2      s3      s4      s5      s6      a7     RHS   \n"
                + "------------------------------------------------------------------------\n"
                + "    -w      0       0       0       0       0       0       1       0    \n"
                + "    z       0       0       0       1      1/2      0       0      25/2  \n"
                + "    x1      1       0       0       -1     3/2      0       0      3/2   \n"
                + "    x2      0       1       0       1       -1      0       0       2    \n"
                + "    s3      0       0       1       1       -2      0       0       1    \n"
                + "    a7      0       0       0       0      1/2      -1      1      1/2   \n"
                + "------------------------------------------------------------------------\n"
                + "------------------------------------------------------------------------\n"
                + "  BVS       x1      x2      s3      s4      s5      s6      a7     RHS   \n"
                + "------------------------------------------------------------------------\n"
                + "    -w      0       0       0       0      -1/2     1       0      -1/2  \n"
                + "    z       0       0       0       1      1/2      0       0      25/2  \n"
                + "    x1      1       0       0       -1     3/2      0       0      3/2   \n"
                + "    x2      0       1       0       1       -1      0       0       2    \n"
                + "    s3      0       0       1       1       -2      0       0       1    \n"
                + "    a7      0       0       0       0      1/2      -1      1      1/2   \n"
                + "------------------------------------------------------------------------\n"
                + "------------------------------------------------\n"
                + " BVS     x1    x2    s3    s4    s5    s6   RHS  \n"
                + "------------------------------------------------\n"
                + "   z     0     0     0     1     0     1     12  \n"
                + "   x1    1     0     0     -1    0     3     0   \n"
                + "   x2    0     1     0     1     0     -2    3   \n"
                + "   s3    0     0     1     1     0     -4    3   \n"
                + "   s5    0     0     0     0     1     -2    1   \n"
                + "------------------------------------------------\n"
                + "";
        assertTrue(resultadoObtenido.equals(resultadoCorrecto));

    }
}
