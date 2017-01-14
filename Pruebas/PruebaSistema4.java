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

public class PruebaSistema4 {

    private PantallaPrincipal pantallaPrincipal = null;

    public PruebaSistema4() {
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
        area.setText("(0) max z = 15 x1 + 10 x2\n"
                + "(1)            x1          <= 2\n"
                + "(2)                    x2  >= 3\n"
                + "(3)            x1 +    x2   = 4");
        Thread.sleep(1000);

        field = pantallaPrincipal.getClass().getDeclaredField("botonSimplex");
        field.setAccessible(true);
        JButton solucionar = (JButton) field.get(pantallaPrincipal);
        if (solucionar == null) {
            fail("Error no se encontro el boton para solucionar.");
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

        field = pantallaPrincipal.getClass().getDeclaredField("radioSolucionDirecta");
        field.setAccessible(true);
        JRadioButton solucionDirecta = (JRadioButton) field.get(pantallaPrincipal);
        if (solucionDirecta == null) {
            fail("Error no se encontro el radio para solucion directa.");
            return;
        }
        solucionDirecta.doClick();
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
        Thread.sleep(1000);

        field = pantallaSiguiente.getClass().getDeclaredField("labelResumen");
        field.setAccessible(true);
        JTextArea resumen = (JTextArea) field.get(pantallaSiguiente);
        if (resumen == null) {
            fail("Error no se encontro el texto de resumen.");
            return;
        }
        String resultadoObtenido = resumen.getText();
        String resultadoCorrecto = "------------------------------------------------------------------------------------------\n"
                + "|  BVS   x1          x2          s3          s4          a5          a6          RHS     |\n"
                + "------------------------------------------------------------------------------------------\n"
                + "|  -w    0           0           0           0           1           1           0       |\n"
                + "|  z    -15         -10         0           0           0           0           0       |\n"
                + "|  s3    1           0           1           0           0           0           2       |\n"
                + "|  a5    0           1           0           -1          1           0           3       |\n"
                + "|  a6    1           1           0           0           0           1           4       |\n"
                + "------------------------------------------------------------------------------------------\n"
                + "------------------------------------------------------------------------------------------\n"
                + "|  BVS   x1          x2          s3          s4          a5          a6          RHS     |\n"
                + "------------------------------------------------------------------------------------------\n"
                + "|  -w    0           -1          0           1           0           1           -3      |\n"
                + "|  z    -15         -10         0           0           0           0           0       |\n"
                + "|  s3    1           0           1           0           0           0           2       |\n"
                + "|  a5    0           1           0           -1          1           0           3       |\n"
                + "|  a6    1           1           0           0           0           1           4       |\n"
                + "------------------------------------------------------------------------------------------\n"
                + "------------------------------------------------------------------------------------------\n"
                + "|  BVS   x1          x2          s3          s4          a5          a6          RHS     |\n"
                + "------------------------------------------------------------------------------------------\n"
                + "|  -w    -1          -2          0           1           0           0           -7      |\n"
                + "|  z    -15         -10         0           0           0           0           0       |\n"
                + "|  s3    1           0           1           0           0           0           2       |\n"
                + "|  a5    0           1           0           -1          1           0           3       |\n"
                + "|  a6    1           1           0           0           0           1           4       |\n"
                + "------------------------------------------------------------------------------------------\n"
                + "------------------------------------------------------------------------------------------\n"
                + "|  BVS   x1          x2          s3          s4          a5          a6          RHS     |\n"
                + "------------------------------------------------------------------------------------------\n"
                + "|  -w    -1          0           0           -1          2           0           -1      |\n"
                + "|  z    -15         0           0           -10         10          0           30      |\n"
                + "|  s3    1           0           1           0           0           0           2       |\n"
                + "|  x2    0           1           0           -1          1           0           3       |\n"
                + "|  a6    1           0           0           1           -1          1           1       |\n"
                + "------------------------------------------------------------------------------------------\n"
                + "------------------------------------------------------------------\n"
                + "|  BVS   x1          x2          s3          s4          RHS     |\n"
                + "------------------------------------------------------------------\n"
                + "|  z    0           0           0           5           45      |\n"
                + "|  s3    0           0           1           -1          1       |\n"
                + "|  x2    0           1           0           -1          3       |\n"
                + "|  x1    1           0           0           1           1       |\n"
                + "------------------------------------------------------------------\n";
        assertTrue(resultadoObtenido.equals(resultadoCorrecto));

    }
}
