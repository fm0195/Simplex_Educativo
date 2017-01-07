package pruebas;

import controlador.AbstractControlador;
import controlador.IVista;
import java.awt.AWTException;
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

public class PruebaSistema3 {

    private PantallaPrincipal pantallaPrincipal = null;

    public PruebaSistema3() {
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
        area.setText("1 2   3   6\n"
                + "4    5   6   6");
        Thread.sleep(1000);
        if (area == null) {
            fail("Error no se encontro el área para ingreso del texto.");
            return;
        }

        field = pantallaPrincipal.getClass().getDeclaredField("radioMatriz");
        field.setAccessible(true);
        JRadioButton radioMatriz = (JRadioButton) field.get(pantallaPrincipal);
        Thread.sleep(1000);
        if (radioMatriz == null) {
            fail("Error no se encontro botón para operar matriz.");
            return;
        }
        radioMatriz.doClick();
        Thread.sleep(1000);

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

        field = pantallaSiguiente.getClass().getDeclaredField("labelResumen");
        field.setAccessible(true);
        JTextArea resumen = (JTextArea) field.get(pantallaSiguiente);
        if (solucionar == null) {
            fail("Error no se encontro el texto de resumen.");
            return;
        }
        String resultadoObtenido = resumen.getText();
        String resultadoCorrecto = "---------------------------------------------------------\n"
                + "|  BVS   -          -          -          RHS           |\n"
                + "---------------------------------------------------------\n"
                + "|  -    1           2           3           6       |\n"
                + "|  -    4           5           6           6       |\n"
                + "---------------------------------------------------------\n"
                + "---------------------------------------------------------\n"
                + "|  BVS   -          -          -          RHS           |\n"
                + "---------------------------------------------------------\n"
                + "|  -    1           2           3           6       |\n"
                + "|  -    0           -3          -6          -18     |\n"
                + "---------------------------------------------------------\n"
                + "---------------------------------------------------------\n"
                + "|  BVS   -          -          -          RHS           |\n"
                + "---------------------------------------------------------\n"
                + "|  -    0           4           8           23      |\n"
                + "|  -    1           -2          -5          -17     |\n"
                + "---------------------------------------------------------\n";
        assertTrue(resultadoObtenido.equals(resultadoCorrecto));

    }
}
