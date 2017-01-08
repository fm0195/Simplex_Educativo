package modelo;

import dto.DtoSimplex;
import java.awt.Point;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fm010
 */
public class SolucionadorGomory extends SolucionadorSimplex {
    
    private ArrayList<DtoSimplex> listaPasos; 
    
    public SolucionadorGomory() {
        super();
        listaPasos = new ArrayList<>();
    }
    
    @Override
    public ArrayList<DtoSimplex> solucionar(DtoSimplex dto) {
        ArrayList<DtoSimplex> solucion = super.solucionar(dto);
        listaPasos.addAll(solucion);
        DtoSimplex ultimaSolucion = solucion.get(solucion.size()-1).clonarProfundo();
        while(! esSolucionEntera(ultimaSolucion)){
            int indiceRestriccionCorte = obtenerIndiceRestriccionCorte(ultimaSolucion);
            AbstractFraccion[] nuevoCorte = realizarCorte(ultimaSolucion, indiceRestriccionCorte);
            AbstractFraccion[][] nuevaMatriz = agregarFila(ultimaSolucion.getMatriz());
            for (int i = 0; i < nuevaMatriz[0].length; i++) {
                nuevaMatriz[nuevaMatriz.length-1][i] = nuevoCorte[i];
            }
            nuevaMatriz = agregarColumnas(nuevaMatriz, 2);
            ultimaSolucion.setMatriz(nuevaMatriz);
            ultimaSolucion = completarDto(ultimaSolucion);
            solucion = super.solucionar(ultimaSolucion);
            solucion.get(solucion.size()-1).setMensaje("Solucion óptima no entera. Corte agregado.");
            listaPasos.addAll(solucion);
            ultimaSolucion = solucion.get(solucion.size()-1);
        }
        return listaPasos;
    }

    @Override
    public DtoSimplex siguientePaso(DtoSimplex dto) {
        DtoSimplex siguiente = super.siguientePaso(dto);
        if (dto.esFinalizado()) {
            if(! esSolucionEntera(dto)){
                int indiceRestriccionCorte = obtenerIndiceRestriccionCorte(siguiente);
                AbstractFraccion[] nuevoCorte = realizarCorte(siguiente, indiceRestriccionCorte);
                AbstractFraccion[][] nuevaMatriz = agregarFila(siguiente.getMatriz());
                for (int i = 0; i < nuevaMatriz[0].length; i++) {
                    nuevaMatriz[nuevaMatriz.length-1][i] = nuevoCorte[i];
                }
                nuevaMatriz = agregarColumnas(nuevaMatriz, 2);
                siguiente.setMatriz(nuevaMatriz);
                siguiente = completarDto(siguiente);
                siguiente.setMensaje("Solucion óptima no entera. Corte agregado a la fila "+indiceRestriccionCorte);
            } else
                return siguiente;
        }
        return siguiente;
    }

    @Override
    public String[] calcularRadio(DtoSimplex dto, int columna) {
        return super.calcularRadio(dto, columna);
    }

    public DtoSimplex completarDto(DtoSimplex dto) {
        DtoSimplex dtoRes = dto.clonarProfundo();
        AbstractFraccion[][] matriz = dtoRes.clonarProfundo().getMatriz();
        matriz = super.convertirDosFases(matriz, matriz[0].length-1);
        matriz[matriz.length-1][matriz[0].length-3] = new Fraccion(-1);
        matriz[matriz.length-1][matriz[0].length-2] = new Fraccion(1);
        dtoRes.setMatriz(matriz);
        dtoRes.setVariablesHolgura(dtoRes.getVariablesHolgura()+1);
        dtoRes.setArtificialActual(dtoRes.getVariablesHolgura()
                    + dtoRes.getVariablesBasicas() - 1);
        dtoRes.setFinalizado(false);
        dtoRes.setBloqueoDosFases(true);
        dtoRes.setDosfases(true);
        dtoRes.setBloqueoDosFases(true);
        dtoRes.setNombreColumnas(agregarNombresColumnas(dtoRes.getNombreColumnas()));
        dtoRes.setNombreFilas(agregarNombresFilas(matriz, dtoRes.getNombreFilas(), dtoRes.getNombreColumnas()));
        dtoRes.setCoordenadaPivote(new Point(matriz.length, matriz[0].length-3));
        dtoRes.setOperaciones(siguientesOperacionesInicioDosfases(matriz[0].length-1));
        return dtoRes;
    }

    @Override
    public DtoSimplex siguientesOperaciones(DtoSimplex dto) {
        return super.siguientesOperaciones(dto);
    }

    private boolean esSolucionEntera(DtoSimplex dto) {
        String solucion = obtenerSolucion(dto);
        return !solucion.contains("/") && !solucion.contains(".");
    }

    private int obtenerIndiceRestriccionCorte(DtoSimplex dto) {
        int indice = 0;
        double mayorNumero = 0;
        AbstractFraccion[][] matriz = dto.getMatriz();
        for(int i = 1; i < matriz.length ; i++){
            AbstractFraccion fraccion = matriz[i][matriz[0].length-1];
            AbstractFraccion parteDecimal = fraccion.obtenerParteDecimal();
            double parteDecimalActual = (double) parteDecimal.getNumerador() / (double) parteDecimal.getDenominador();
            if (parteDecimalActual > mayorNumero) {
                mayorNumero = parteDecimalActual;
                indice = i;
            }
        }
        return indice;
    }

    private AbstractFraccion[] realizarCorte(DtoSimplex dto, int indiceRestriccion) {
        AbstractFraccion[][] matriz = dto.clonarProfundo().getMatriz();
        AbstractFraccion[] restriccion = matriz[indiceRestriccion];
        AbstractFraccion[] cortes = new AbstractFraccion[restriccion.length];
        for (int i = 0; i < restriccion.length; i++) {
            AbstractFraccion fraccion = restriccion[i];
            cortes[i] = obtenerCorte(fraccion);
        }
        return cortes;
    }
    
    private AbstractFraccion obtenerCorte(AbstractFraccion coeficiente) {
        if(coeficiente.getDenominador() == 1)//es un numero entero
            return new Fraccion(0);
        if(coeficiente.getNumerador() < 0) {//coeficiente negativo
            coeficiente.hacerNegativa();
            return new Fraccion(1).restar(coeficiente.obtenerParteDecimal());
        }
        else {
            return coeficiente.obtenerParteDecimal();
        }
    }
    
    private void imprimirMatriz(AbstractFraccion[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j].toString(true) + "  ");
            }
            System.out.println();
        }
        System.out.println("\n----------------------\n");
    }
    
    private String[] agregarNombresColumnas(String[] nombres){
        String[] resultado = new String[nombres.length + 2];
        int i;
        for (i = 0; i < nombres.length; i++) {
            resultado[i] = nombres[i];
        }
        resultado[i] = "s"+(i+1);
        i++;
        resultado[i] = "a"+(i+1);
        return resultado;
    }

    private String[] agregarNombresFilas(AbstractFraccion[][] matriz, String[] nombreFilas, String[] nombreColumnas) {
        String[] resultado = new String[nombreFilas.length + 2];
        AbstractFraccion uno = new Fraccion(1);
        AbstractFraccion cero = new Fraccion(0);
        boolean todosUno;
        imprimirMatriz(matriz);
        resultado[0] = "-w";
        resultado[1] = nombreFilas[0];
        for (int i = 2; i < resultado.length; i++) {
            for (int j = 0; j < matriz[0].length-1; j++) {
                todosUno = true;
                if(matriz[i][j].iguales(uno)) {
                    for (int k = 1; k < matriz.length; k++) {
                        if (!matriz[k][j].iguales(cero) && i != k) {
                            todosUno = false;
                        } 
                    }
                    if (todosUno) {
                        resultado[i] = nombreColumnas[j];
                        break;
                    }
                }
            }
        }
        return resultado;
    }
}
