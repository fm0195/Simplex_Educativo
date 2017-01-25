/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.parser;

import java.awt.Point;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import modelo.AbstractFraccion;
import dto.DtoSimplex;

/**
 *
 * @author fm010
 */
public class MatrizParser implements IParser {

    @Override
    public DtoSimplex parse(String value) throws IOException {
        ArrayList<ArrayList<AbstractFraccion>> matriz = new ArrayList<>();
        matriz.add(new ArrayList<AbstractFraccion>());
        Scanner scanner = new Scanner(new StringReader(value));
        Symbol token = scanner.next_token();
        boolean esNegativo = false;
        int cantElementosFila = 0;
        int filaActual = 0;
        while (token.sym != sym.EOF) {
            if (token.sym == sym.MENOS) {
                if (esNegativo) {
                    throw new IllegalArgumentException("Hay dos simbolos negativos seguidos");
                } else {
                    esNegativo = true;
                }
            }
            if (token.sym == sym.COEFICIENTE) {
                AbstractFraccion fraccion = (AbstractFraccion) token.value;
                if (esNegativo) {
                    fraccion.hacerNegativa();
                    esNegativo = false;
                }
                matriz.get(filaActual).add(fraccion);
            }
            if (token.sym == sym.CAMBIOLINEA) {
                if (matriz.size() == 1) {
                    cantElementosFila = matriz.get(0).size();
                } else {
                    if (cantElementosFila != matriz.get(filaActual).size()) {
                        throw new IllegalArgumentException("Diferente numero de elementos en las filas.");
                    }
                }
                matriz.add(new ArrayList<AbstractFraccion>());
                filaActual++;
            }
            token = scanner.next_token();
        }
        if (matriz.get(filaActual).isEmpty()) {
            matriz.remove(filaActual);
        }
        String[] nombreFilas = new String[matriz.size()];
        String[] nombreColumnas = new String[matriz.get(0).size()];
        AbstractFraccion[][] matrizFracciones = new AbstractFraccion[matriz.size()][matriz.get(0).size()];
        for (int i = 0; i < matriz.size(); i++) {
            nombreFilas[i] = "-";
            for (int j = 0; j < matriz.get(0).size(); j++) {
                nombreColumnas[j] = j == matriz.get(0).size() - 1 ? "RHS" : "-";
                matrizFracciones[i][j] = matriz.get(i).get(j);
            }
        }
        DtoSimplex resultado = new DtoSimplex(matrizFracciones, nombreColumnas, nombreFilas, new Point(0, 0));
        resultado.setEsMatriz(true);
        return resultado;
    }

}
