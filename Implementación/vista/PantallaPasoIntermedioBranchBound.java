/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.AbstractControlador;
import controlador.IVista;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import dto.DtoSimplex;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author fm010
 */
public class PantallaPasoIntermedioBranchBound extends javax.swing.JFrame implements IVista {

    /**
     * Creates new form PantallaPasoIntermedio
     */
    AbstractControlador controlador;
    JTextArea labelMensaje;
    JTextArea labelResumen;
    JScrollPane scrollResumen;
    JScrollPane scrollMensaje;
    JPanel pestanaResumen;
    JPanel panelBotonesResumen;
    JButton botonSiguienteResumen;
    JButton botonAnteriorResumen;
    JButton botonCopiarArbol;    
    JButton botonCopiarMensaje;
    JLabel etiquetaArbol;
    JLabel etiquetaMensaje;
    int pasoActual;
    final StringBuilder resumenPasoAnterior;
    boolean esPrimeraFase = false;
    final int POSICION_X = 20;
    final int POSICION_Y = 50;
    JMenuBar barraMenu;
    JMenuItem itemMenuFAQ;
    JMenu menuAyuda;

    /**
     * Pantalla de pasos intermedios utilizada para dibujar la matriz de pasos y
     * capturar los eventos del usuario con el sistema.
     *
     * @param controlador el controlador que se conectará con el modelo.
     */
    public PantallaPasoIntermedioBranchBound(AbstractControlador controlador) {
        initComponents();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                dispose();
                new PantallaPrincipal("").setVisible(true);
            }
        });
        this.controlador = controlador;
        this.resumenPasoAnterior = new StringBuilder();
        initVariables();
        agregarActionListeners();
        agregarComponentes();
    }

    /**
     * Inicializa todas los componentes y variables necesarios para el
     * despliegue de la pantalla.
     */
    public void initVariables() {
        panelBotonesResumen = new JPanel();
        panelBotonesResumen.setBounds(POSICION_X - 5, 320, 550, 140);
        pestanaResumen = new JPanel(null);
        pestanaResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        etiquetaArbol = new JLabel("Arbol generado.");
        etiquetaArbol.setFont(new Font("Courier New", Font.BOLD, 14));
        etiquetaArbol.setBounds(10, 10, 200, 15);
        etiquetaMensaje = new JLabel("Mensaje.");
        etiquetaMensaje.setFont(new Font("Courier New", Font.BOLD, 14));
        etiquetaMensaje.setBounds(625, 10, 100, 15);
        labelMensaje = new JTextArea();
        labelMensaje.setEditable(false);
        labelMensaje.setLineWrap(false);
        labelMensaje.setFont(new Font("Courier New", Font.BOLD, 14));
        scrollMensaje = new JScrollPane(labelMensaje);
        scrollMensaje.setBounds(625, 40, 320, 300);
        scrollMensaje.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollMensaje.setFont(new Font("Courier New", Font.BOLD, 14));
        labelResumen = new JTextArea();
        labelResumen.setEditable(false);
        labelResumen.setLineWrap(false);
        labelResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        scrollResumen = new JScrollPane(labelResumen);
        scrollResumen.setBounds(POSICION_X, 40, 600, 280);
        scrollResumen.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        botonSiguienteResumen = new JButton("Siguiente Paso");
        botonSiguienteResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        botonAnteriorResumen = new JButton("Paso Anterior");
        botonAnteriorResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        botonCopiarArbol = new JButton("Copiar Arbol");
        botonCopiarArbol.setFont(new Font("Courier New", Font.BOLD, 14));
        botonCopiarMensaje = new JButton("Copiar Mensaje");
        botonCopiarMensaje.setFont(new Font("Courier New", Font.BOLD, 14));
        barraMenu = new JMenuBar();
        menuAyuda = new JMenu("Ayuda");
        itemMenuFAQ = new JMenuItem("FAQ");
        super.setSize(1000, this.getHeight()+30);
    }

    /**
     * Agrega las acciones a los botones de la pantalla
     */
    public void agregarActionListeners() {
        botonSiguienteResumen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.siguientePaso();
            }
        });

        botonAnteriorResumen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.anteriorPaso();
            }
        });
        botonCopiarArbol.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(labelResumen.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        });
        
        botonCopiarMensaje.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(labelMensaje.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        });
        
        itemMenuFAQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JScrollPane scrollpane = new JScrollPane(); 
                String informacion = "¿Cómo vuelvo al menú principal?\n" +
                                    "\n" +
                                    "  + Cierre la ventana actual para volver. \n" +
                                    "\n" +
                                    "¿Qué estoy viendo en la pantalla actual?\n" +
                                    "\n" +
                                    "+  En la pantalla actual se muestra la representación de un arbol en cadena de texto,\n"
                                    + " donde se agrega las restricciones que el algoritmo de Branch And Bound genera para obtener la \n"
                                    + " solución óptima entera. Además cada nodo del arbol, representado por Problema x.x.., muestra las\n"
                                    + " restricciones agregadas por el algoritmo. La sección de mensaje se podrá informar de las acciones\n"
                                    + " que ha tomado el algoritmo." +
                                    "\n" +
                                    "¿Cómo sé cuándo el algoritmo ha terminado?\n" +
                                    "  + El sistema alertará mediante un mensaje que se ha llegado a un estado óptimo. "
                                    + "\n"+
                                    "";
                JTextArea areaTexto = new JTextArea();
                areaTexto.setText(informacion);
                areaTexto.setWrapStyleWord(true);
                scrollpane.add(areaTexto);
                scrollpane.getViewport().add(areaTexto);
                JOptionPane.showMessageDialog(null, scrollpane, "FAQ",  
                                              JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    /**
     * Agrega los componentes en su respectivo contenedor, y agrega los
     * componentes a la pantalla principal.
     */
    public void agregarComponentes() {
        panelBotonesResumen.add(botonAnteriorResumen);
        panelBotonesResumen.add(botonSiguienteResumen);
        panelBotonesResumen.add(botonCopiarArbol);
        panelBotonesResumen.add(botonCopiarMensaje);
        panelPestana.addTab("Branch and Bound", pestanaResumen);
        pestanaResumen.add(scrollResumen);
        pestanaResumen.add(scrollMensaje);
        pestanaResumen.add(etiquetaArbol);
        pestanaResumen.add(etiquetaMensaje);
        pestanaResumen.add(panelBotonesResumen);
         menuAyuda.add(itemMenuFAQ);
        barraMenu.add(menuAyuda);
        this.setJMenuBar(barraMenu);
    }

    /**
     * Pide al controlador el siguiente paso del problema.
     */
    public void siguientePaso() {
        controlador.siguientePaso();
    }

    /**
     * Muetra el mensaje informativo sobre el paso anterior en el label
     * correspondiente
     *
     * @param mensaje mensaje por mostrar
     */
    public void mostrarMensaje(String mensaje) {
        labelMensaje.setText(mensaje);
    }

    public void mostrarMatriz(DtoSimplex dto) {
        mostrarMensaje(dto.getSolucion());
        rellenarResumen(dto.getMensaje());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Simplex Educativo");
        setSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPestana, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPestana, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelPestana.getAccessibleContext().setAccessibleName("");
        panelPestana.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JTabbedPane panelPestana = new javax.swing.JTabbedPane();
    // End of variables declaration//GEN-END:variables

    public void mostrarMensajeError(String mensaje, String encabezado) {
        JOptionPane.showMessageDialog(null, mensaje, encabezado, JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensajeInformacion(String mensaje, String encabezado) {
        JOptionPane.showMessageDialog(null, mensaje, encabezado, JOptionPane.INFORMATION_MESSAGE);
    }

    private void rellenarResumen(String resumen) {
        labelResumen.setText(resumen);
    }

    @Override
    public void menu(String mensajeError, String problemaOriginal) {
        this.setVisible(false);
        if (mensajeError != null) {
            this.mostrarMensajeError(mensajeError, "Error");
        }
        this.dispose();
        new PantallaPrincipal(problemaOriginal).setVisible(true);
    }
}
