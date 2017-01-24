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
import vista.PantallaPrincipal;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import vista.PantallaPasoIntermedioBranchBound;

public class PruebaSistema9 {

    private PantallaPrincipal pantallaPrincipal = null;

    public PruebaSistema9() {
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

        field = pantallaPrincipal.getClass().getDeclaredField("radioBB");
        field.setAccessible(true);
        JRadioButton radioBB = (JRadioButton) field.get(pantallaPrincipal);
        if (radioBB == null) {
            fail("Error no se encontro el radio para solucion Gomory.");
            return;
        }
        radioBB.doClick();
        Thread.sleep(1000);

        field = pantallaPrincipal.getClass().getDeclaredField("radioSolucionDirecta");
        field.setAccessible(true);
        JRadioButton radioMostrarPasos = (JRadioButton) field.get(pantallaPrincipal);
        if (radioMostrarPasos == null) {
            fail("Error no se encontro el radio para solucion directa.");
            return;
        }
        radioMostrarPasos.doClick();
        Thread.sleep(1000);

        field = pantallaPrincipal.getClass().getDeclaredField("botonSimplex");
        field.setAccessible(true);
        JButton solucionar = (JButton) field.get(pantallaPrincipal);
        if (solucionar == null) {
            fail("Error no se encontro el boton para solucionar.");
            return;
        }
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
        PantallaPasoIntermedioBranchBound pantallaSiguiente = (PantallaPasoIntermedioBranchBound) (IVista) field.get(controlador);
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
        String resultadoCorrecto = "Problema 1\n"
                + "  z = 51/4\n"
                + "  x1 = 9/4\n"
                + "  x2 = 3/2\n"
                + "  Restricción: No es necesario\n"
                + "\n"
                + "     Problema 1.1\n"
                + "       z = 23/2\n"
                + "       x1 = 5/2\n"
                + "       x2 = 1\n"
                + "       Restricción: x2 <= 1\n"
                + "       *Problema Acotado\n"
                + "\n"
                + "     Problema 1.2\n"
                + "       z = 25/2\n"
                + "       x1 = 3/2\n"
                + "       x2 = 2\n"
                + "       Restricción: x2 >= 2\n"
                + "\n"
                + "          Problema 1.2.1\n"
                + "            z = 37/3\n"
                + "            x1 = 1\n"
                + "            x2 = 7/3\n"
                + "            Restricción: x1 <= 1\n"
                + "\n"
                + "               Problema 1.2.1.1\n"
                + "                 z = 11\n"
                + "                 x1 = 1\n"
                + "                 x2 = 2\n"
                + "                 Restricción: x2 <= 2\n"
                + "\n"
                + "               Problema 1.2.1.2\n"
                + "                 z = 12\n"
                + "                 x1 = 0\n"
                + "                 x2 = 3\n"
                + "                 Restricción: x2 >= 3\n"
                + "                 *Solución Óptima.\n"
                + "\n"
                + "          Problema 1.2.2\n"
                + "            Restricción: x1 >= 2\n"
                + "            *Problema No Factible\n"
                + "\n"
                + "";
        assertTrue(resultadoObtenido.equals(resultadoCorrecto));

    }
}
