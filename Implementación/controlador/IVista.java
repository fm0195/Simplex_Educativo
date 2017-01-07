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

    /**
     * Muestra un mensaje de error en la pantalla.
     *
     * @param mensaje El mensaje por mostrar
     * @param encabezado El encabezado del mensaje
     */
    public void mostrarMensajeError(String mensaje, String encabezado);

    /**
     * Muestra los datos del objeto dto en la vista.
     *
     * @param dto el dto que representa el paso por mostrar.
     */
    public void mostrarMatriz(DtoSimplex dto);

    /**
     * Muestra un mensaje de error en la pantalla.
     *
     * @param mensaje El mensaje por mostrar
     * @param encabezado El encabezado del mensaje
     */
    public void mostrarMensajeInformacion(String mensaje, String encabezado);

    /**
     * Muestra un mensaje de error en la pantalla para luego volver al menu
     * principal.
     *
     * @param mensaje El mensaje por mostrar
     * @param problemaOriginal El texto del problema original para volver a
     * mostrarlo en la pantalla del menu.
     */
    public void menu(String mensaje, String problemaOriginal);

}
