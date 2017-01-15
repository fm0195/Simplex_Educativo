package pruebas;


import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jjime.simplex.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vista.PaginaInicial;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;

/**
 * Created by fm010 on 14/01/2017.
 */
@RunWith(AndroidJUnit4.class)
public class TestMovil {
    @Rule public final ActivityTestRule<PaginaInicial> main = new ActivityTestRule<PaginaInicial>(PaginaInicial.class);

    //Problema directo, prueba solucion entera
    @Test
    public void testProblemaDirecto1(){
        onView(ViewMatchers.withId(R.id.areaProblema)).perform(typeText(
                "max z = 15 x1 + 10 x2\n" +
                        "x1 <= 2\n" +
                        "x2 >= 3\n" +
                        "x1 + x2 = 4"));//agregar problema
        onView(withId(R.id.areaProblema)).perform(closeSoftKeyboard());
        onView(withId(R.id.radio_directa)).perform(click());//seleccionar solución directa
        onView(withId(R.id.radio_Fraccional)).perform(click());//seleccionar formato de fraccion
        onView(withId(R.id.solucionar)).perform(click());//presionar boton de solucionar
        onView(withText("Problema finalizado. \n" +
                "z = 45\n" +
                "x1 = 1\n" +
                "x2 = 3\n")).check(matches(isDisplayed()));
    }

    //Problema directo, solucion no entera, formato fraccionario
    @Test
    public void testProblemaDirecto2(){
        onView(withId(R.id.areaProblema)).perform(typeText(
                "min z = 20 x1 + 22 x2 + 18 x3\n" +
                        "4 x1 + 6 x2 + 1 x3 >= 54\n" +
                        "4 x1 + 4 x2 + 6 x3 >= 65\n" +
                        "1 x1<=7\n" +
                        "1 x2<=7\n" +
                        "1 x3<=7"));//agregar problema
        onView(withId(R.id.areaProblema)).perform(closeSoftKeyboard());
        onView(withId(R.id.radio_directa)).perform(click());//seleccionar solución directa
        onView(withId(R.id.radio_Fraccional)).perform(click());//seleccionar formato de fraccion
        onView(withId(R.id.solucionar)).perform(click());//presionar boton de solucionar
        onView(withText("Problema finalizado. \n" +
                "z = 279\n" +
                "x1 = 7/4\n" +
                "x2 = 7\n"+
                "x3 = 5\n")).check(matches(isDisplayed()));
    }

    //Problema directo, solucion no entera, formato decimal
    @Test
    public void testProblemaDirecto3(){
        onView(withId(R.id.areaProblema)).perform(typeText(
                "min z = 20 x1 + 22 x2 + 18 x3\n" +
                        "4 x1 + 6 x2 + 1 x3 >= 54\n" +
                        "4 x1 + 4 x2 + 6 x3 >= 65\n" +
                        "1 x1<=7\n" +
                        "1 x2<=7\n" +
                        "1 x3<=7"));//agregar problema
        onView(withId(R.id.areaProblema)).perform(closeSoftKeyboard());
        onView(withId(R.id.radio_directa)).perform(click());//seleccionar solución directa
        onView(withId(R.id.radio_Decimal)).perform(click());//seleccionar formato de fraccion
        onView(withId(R.id.solucionar)).perform(click());//presionar boton de solucionar
        onView(withText("Problema finalizado. \n" +
                "z = 279\n" +
                "x1 = 1,75\n" +
                "x2 = 7\n"+
                "x3 = 5\n")).check(matches(isDisplayed()));
    }

    //Solucion no factible
    @Test
    public void testProblemaInfactible(){
        onView(withId(R.id.areaProblema)).perform(typeText(
                "min z = x1 + x2\n" +
                        "x1 >= 6\n" +
                        "x2 >= 6\n" +
                        "x1 + x2 <= 11"));//agregar problema
        onView(withId(R.id.areaProblema)).perform(closeSoftKeyboard());
        onView(withId(R.id.radio_directa)).perform(click());//seleccionar solución directa
        onView(withId(R.id.radio_Decimal)).perform(click());//seleccionar formato de fraccion
        onView(withId(R.id.solucionar)).perform(click());//presionar boton de solucionar
        onView(withText("Infactibilidad"))
                .check(matches(isDisplayed()));
    }

    //Solucion no factible
    @Test
    public void testProblemaNoAcotado(){
        onView(withId(R.id.areaProblema)).perform(typeText(
                "max z = 4x1 + 6x2\n" +
                        "2x1 - 2x2 <= 6\n" +
                        "4x1 <= 6"));//agregar problema
        onView(withId(R.id.areaProblema)).perform(closeSoftKeyboard());
        onView(withId(R.id.radio_directa)).perform(click());//seleccionar solución directa
        onView(withId(R.id.radio_Decimal)).perform(click());//seleccionar formato de fraccion
        onView(withId(R.id.solucionar)).perform(click());//presionar boton de solucionar
        onView(withText("No acotado"))
                .check(matches(isDisplayed()));
    }

    //Problema directo, prueba solucion entera
    @Test
    public void testProblemaPasos1(){
        onView(withId(R.id.areaProblema)).perform(typeText(
                "max z = 15 x1 + 10 x2\n" +
                        "x1 <= 2\n" +
                        "x2 >= 3\n" +
                        "x1 + x2 = 4"));//agregar problema
        onView(withId(R.id.areaProblema)).perform(closeSoftKeyboard());
        onView(withId(R.id.radio_porPasos)).perform(click());//seleccionar solución directa
        onView(withId(R.id.radio_Fraccional)).perform(click());//seleccionar formato de fraccion
        onView(withId(R.id.solucionar)).perform(click());//presionar boton de solucionar

        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withText("Problema finalizado. \n" +
                "z = 45\n" +
                "x1 = 1\n" +
                "x2 = 3\n")).check(matches(isDisplayed()));
    }

    //Problema directo, solucion no entera, formato fraccionario
    @Test
    public void testProblemaPasos3(){
        onView(withId(R.id.areaProblema)).perform(typeText(
                "min z = 20 x1 + 22 x2 + 18 x3\n" +
                        "4 x1 + 6 x2 + 1 x3 >= 54\n" +
                        "4 x1 + 4 x2 + 6 x3 >= 65\n" +
                        "1 x1<= 7\n" +
                        "1 x2<= 7\n" +
                        "1 x3 <= 7"));//agregar problema
        onView(withId(R.id.areaProblema)).perform(closeSoftKeyboard());
        onView(withId(R.id.radio_porPasos)).perform(click());//seleccionar solución directa
        onView(withId(R.id.radio_Decimal)).perform(click());//seleccionar formato de fraccion
        onView(withId(R.id.solucionar)).perform(click());//presionar boton de solucionar

        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withText("Problema finalizado. \n" +
                "z = 279\n" +
                "x1 = 1,75\n" +
                "x2 = 7\n"+
                "x3 = 5\n")).check(matches(isDisplayed()));
    }

    //Problema directo, solucion no entera, formato fraccionario
    @Test
    public void testProblemaPasos2(){
        onView(withId(R.id.areaProblema)).perform(typeText(
                "min z = 20 x1 + 22 x2 + 18 x3\n" +
                        "4 x1 + 6 x2 + 1 x3 >= 54\n" +
                        "4 x1 + 4 x2 + 6 x3 >= 65\n" +
                        "1 x1<=7\n" +
                        "       1 x2<=7\n" +
                        "              1 x3<=7"));//agregar problema
        onView(withId(R.id.areaProblema)).perform(closeSoftKeyboard());
        onView(withId(R.id.radio_porPasos)).perform(click());//seleccionar solución directa
        onView(withId(R.id.radio_Fraccional)).perform(click());//seleccionar formato de fraccion
        onView(withId(R.id.solucionar)).perform(click());//presionar boton de solucionar

        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withId(R.id.pasoSiguiente)).perform(click());
        onView(withText("Problema finalizado. \n" +
                "z = 279\n" +
                "x1 = 7/4\n" +
                "x2 = 7\n"+
                "x3 = 5\n")).check(matches(isDisplayed()));
    }
}