package pruebas;

import controlador.AbstractControlador;
import controlador.IVista;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import vista.PantallaPasoIntermedio;
import vista.PantallaPrincipal;

public class PruebaSistema2 {

    private PantallaPrincipal pantallaPrincipal = null;

    public PruebaSistema2() {
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
        area.setText("max z = -1 x1 - 2 x2\n"
                + "3 x1 + 4 x2 <= 20\n"
                + "2 x1 - 1 x2 >= 2");
        Thread.sleep(1000);
        if (area == null) {
            fail("Error no se encontro el área para ingreso del texto.");
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
        String resultadoCorrecto = "------------------------------------------------------------------------------\n"
                + "|  BVS   x1          x2          s3          s4          a5          RHS     |\n"
                + "------------------------------------------------------------------------------\n"
                + "|  -w    0           0           0           0           1           0       |\n"
                + "|  z    1           2           0           0           0           0       |\n"
                + "|  s3    3           4           1           0           0           20      |\n"
                + "|  a5    2           -1          0           -1          1           2       |\n"
                + "------------------------------------------------------------------------------\n"
                + "------------------------------------------------------------------------------\n"
                + "|  BVS   x1          x2          s3          s4          a5          RHS     |\n"
                + "------------------------------------------------------------------------------\n"
                + "|  -w    -2          1           0           1           0           -2      |\n"
                + "|  z    1           2           0           0           0           0       |\n"
                + "|  s3    3           4           1           0           0           20      |\n"
                + "|  a5    2           -1          0           -1          1           2       |\n"
                + "------------------------------------------------------------------------------\n"
                + "------------------------------------------------------------------------------\n"
                + "|  BVS   x1          x2          s3          s4          a5          RHS     |\n"
                + "------------------------------------------------------------------------------\n"
                + "|  -w    0           0           0           0           1           0       |\n"
                + "|  z    0           5/2         0           1/2         -1/2        -1      |\n"
                + "|  s3    0           11/2        1           3/2         -3/2        17      |\n"
                + "|  x1    1           -1/2        0           -1/2        1/2         1       |\n"
                + "------------------------------------------------------------------------------\n"
                + "------------------------------------------------------------------\n"
                + "|  BVS   x1          x2          s3          s4          RHS     |\n"
                + "------------------------------------------------------------------\n"
                + "|  z    0           5/2         0           1/2         -1      |\n"
                + "|  s3    0           11/2        1           3/2         17      |\n"
                + "|  x1    1           -1/2        0           -1/2        1       |\n"
                + "------------------------------------------------------------------\n";
        assertTrue(resultadoObtenido.equals(resultadoCorrecto));

    }
}
