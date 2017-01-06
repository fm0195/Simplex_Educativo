/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dto.DtoSimplex;

/**
 *
 * @author jfmolina
 */
public interface IVista {

    public void mostrarMensajeError(String mensaje, String encabezado);

    public void mostrarMatriz(DtoSimplex dto);

    public void mostrarMensajeInformacion(String string, String finalizado);
    
    public void menu(String mensaje, String problemaOriginal);
    
}
