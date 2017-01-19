package controlador;

import dto.DtoSimplex;
import java.util.ArrayList;
import modelo.SolucionadorBranchAndBound;
import modelo.parser.SimplexParser;

/**
 *
 * @author Yordan Jiménez
 */
public class BranchAndBoundControlador extends AbstractControlador {

    /**
     * Instancia el controlador que resuelve problemas lineales por medio del
     * algoritmo Branch and Bound
     */
    public BranchAndBoundControlador() {
        this.solucionador = new SolucionadorBranchAndBound();
        this.parser = new SimplexParser();
    }

    @Override
    public ArrayList<DtoSimplex> solucionar(String problema, boolean fraccional) {
        DtoSimplex dto = null;
        this.problemaOriginal = problema;
        try {
            dto = parser.parse(problema);
        } catch (Exception ex) {
            vista.menu("Error en el formato de entrada.", problemaOriginal);
            return null;
        }
        dto.setFormatoFraccional(fraccional);
        listaPasos = solucionador.solucionar(dto);
        pasoActual = listaPasos.size() - 1;
        vista.mostrarMatriz(listaPasos.get(pasoActual));
        if (!listaPasos.get(pasoActual).esFactible()) {
            vista.mostrarMensajeError("\"El problema no es factible.\n La función -w no posee el valor 0 en el RHS al terminar la primera fase.", "Infactibilidad");
        } else {
            if (listaPasos.get(pasoActual).esFinalizado()) {
                vista.mostrarMensajeInformacion("Se ha logrado llegar a un óptimo.", "Finalizado");
                DtoSimplex solucion = listaPasos.get(pasoActual);
                String optima = problemaOriginal + "\nRestricciones agregadas:\n" + solucion.getSolucion();
                optima = "Solución óptima.\n" + optima;
                solucion = new DtoSimplex(optima, solucion.getMensaje(), solucion.esFactible(), solucion.esFinalizado());
                vista.mostrarMatriz(solucion);
            }
        }
        return listaPasos;
    }

    @Override
    public void siguientePaso(String problema, boolean fraccional) {
        DtoSimplex dto = null;
        this.problemaOriginal = problema;
        try {
            dto = parser.parse(problema);
        } catch (Exception ex) {
            vista.menu("Error en el formato de entrada.", problemaOriginal);
            return;
        }
        dto.setFormatoFraccional(fraccional);
        listaPasos = solucionador.solucionar(dto);
        pasoActual = 0;
        vista.mostrarMatriz(listaPasos.get(pasoActual));
    }

    @Override
    public void siguientePaso() {
        if (pasoActual + 1 < listaPasos.size()) {
            pasoActual++;
        }
        vista.mostrarMatriz(listaPasos.get(pasoActual));

        if (!listaPasos.get(pasoActual).esFactible()) {
            vista.mostrarMensajeError("El problema no es factible.\n La función -w no posee el valor 0 en el RHS al terminar la primera fase.", "Infactibilidad");
        } else {
            if (listaPasos.get(pasoActual).esFinalizado()) {
                vista.mostrarMensajeInformacion("Se ha logrado llegar a un óptimo.", "Finalizado");
                DtoSimplex solucion = listaPasos.get(pasoActual);
                String optima = problemaOriginal + "\nRestricciones agregadas:\n" + solucion.getSolucion();
                optima = "Solución óptima.\n" + optima;
                solucion = new DtoSimplex(optima, solucion.getMensaje(), solucion.esFactible(), solucion.esFinalizado());
                vista.mostrarMatriz(solucion);
            }
        }
    }

    @Override
    public void anteriorPaso() {
        if (pasoActual > 0) {
            pasoActual--;
        }
        vista.mostrarMatriz(listaPasos.get(pasoActual));
    }
}
