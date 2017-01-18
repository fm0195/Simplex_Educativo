/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dto.DtoSimplex;
import java.util.ArrayList;
import modelo.SolucionadorGomory;
import modelo.parser.SimplexParser;

/**
 *
 * @author fm010
 */
public class GomoryControlador extends AbstractControlador {

    /**
     * Instancia el controlador que resuelve problemas lineales por medio del
     * algoritmo Cortes de Gomory
     */
    public GomoryControlador() {
        this.solucionador = new SolucionadorGomory();
        this.parser = new SimplexParser();
    }
}
