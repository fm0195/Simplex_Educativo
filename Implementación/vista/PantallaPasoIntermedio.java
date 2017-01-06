/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.AbstractControlador;
import controlador.IVista;
import controlador.SimplexControlador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import dto.DtoSimplex;

/**
 *
 * @author fm010
 */
public class PantallaPasoIntermedio extends javax.swing.JFrame implements IVista {

    /**
     * Creates new form PantallaPasoIntermedio
     */
    AbstractControlador controlador;
    String[][] matrizFracciones;
    JLabel[][] matrizLabels;
    JLabel[] matrizRadios;
    JLabel labelMensaje;
    JTextArea labelOperaciones;
    JTextArea labelResumen;
    JScrollPane scrollResumen;
    JPanel pestanaResumen;
    JPanel pestanaMatriz;
    JPanel panelTabla;
    JPanel panelRadios;
    JPanel panelBotonesMatriz;
    JPanel panelBotonesResumen;
    JButton botonSiguienteMatriz;
    JButton botonAnteriorMatriz;
    JButton botonSiguienteResumen;
    JButton botonAnteriorResumen;
    JButton botonCopiarTodo;
    JButton botonCopiarPaso;
    final Point casillaSeleccionada;
    final int ANCHO_CASILLA = 100;
    final int ALTO_CASILLA = 40;
    final int POSICION_TABLA_X = 20;
    final int POSICION_TABLA_Y = 50;
    int pasoActual;
    int POSICION_RADIOS_X;
    int POSICION_RADIOS_Y;
    KeyListener keyboardListener;
    final StringBuilder keyBuffer;
    final StringBuilder resumenPasoAnterior;
    private int ESPACIO_TABLAS = 10;
    boolean esPrimeraFase = false;
    

    
    public PantallaPasoIntermedio(AbstractControlador controlador) {
        initComponents();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent evt) {
          dispose();
          new PantallaPrincipal("").setVisible(true);
        }
       });
        this.controlador = controlador;
        this.keyBuffer = new StringBuilder();
        this.resumenPasoAnterior = new StringBuilder();
        this.casillaSeleccionada = new Point();
        initVariables();
        agregarActionListeners();
        agregarComponentes();
    }
    
    public void initVariables(){
        panelTabla = new JPanel();
        panelRadios = new JPanel();
        panelBotonesMatriz = new JPanel();
        panelBotonesResumen = new JPanel();
        panelBotonesResumen.setBounds(POSICION_TABLA_X-5, 320, 550, 140);
        pestanaResumen = new JPanel(null);
        pestanaResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        pestanaMatriz = new JPanel(null);
        labelOperaciones = new JTextArea();
        labelOperaciones.setEditable(false);
        labelOperaciones.setLineWrap(true);
        labelOperaciones.setBorder(null);
        labelOperaciones.setFocusable(false);  
        labelOperaciones.setFont(new Font("Courier New", Font.BOLD, 14));
        labelMensaje = new JLabel();
        labelMensaje.setBounds(POSICION_TABLA_X,10,800,40);
        labelMensaje.setFont(new Font("Courier New", Font.BOLD, 14));
        labelResumen = new JTextArea();
        labelResumen.setEditable(false);
        labelResumen.setLineWrap(false);
        labelResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        scrollResumen = new JScrollPane(labelResumen);
        scrollResumen.setBounds(POSICION_TABLA_X, 10, 800, 300);
        scrollResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        botonSiguienteMatriz = new JButton("Siguiente Paso");
        botonSiguienteMatriz.setFont(new Font("Courier New", Font.BOLD, 14));
        botonAnteriorMatriz = new JButton("Paso Anterior");
        botonAnteriorMatriz.setFont(new Font("Courier New", Font.BOLD, 14));
        botonSiguienteResumen = new JButton("Siguiente Paso");
        botonSiguienteResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        botonAnteriorResumen = new JButton("Paso Anterior");
        botonAnteriorResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        botonCopiarPaso = new JButton("Copiar Paso");
        botonCopiarPaso.setFont(new Font("Courier New", Font.BOLD, 14));
        botonCopiarTodo = new JButton("Copiar Todo");
        botonCopiarTodo.setFont(new Font("Courier New", Font.BOLD, 14));
        panelTabla.setBackground(Color.WHITE);
        botonAnteriorMatriz.setFocusable(false);
        botonSiguienteMatriz.setFocusable(false);
    }
    
    public void agregarActionListeners(){
        botonSiguienteMatriz.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.siguientePaso();
            }
        });
        
        botonAnteriorMatriz.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.anteriorPaso();
            }
        });
        
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
        botonCopiarPaso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(resumenPasoAnterior.toString());
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        });
        botonCopiarTodo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(labelResumen.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        });
    }
    
    public void agregarComponentes(){
        panelBotonesMatriz.add(botonAnteriorMatriz);
        panelBotonesMatriz.add(botonSiguienteMatriz);
        panelBotonesResumen.add(botonAnteriorResumen);
        panelBotonesResumen.add(botonSiguienteResumen);
        panelBotonesResumen.add(botonCopiarPaso);
        panelBotonesResumen.add(botonCopiarTodo);
        panelPestana.addTab("Matriz Numérica", pestanaMatriz);
        panelPestana.addTab("Resumen de pasos", pestanaResumen);
        pestanaMatriz.add(labelMensaje);
        pestanaMatriz.add(panelTabla);
        pestanaMatriz.add(panelRadios);
        pestanaMatriz.add(panelBotonesMatriz);
        pestanaMatriz.add(labelOperaciones);
        pestanaResumen.add(scrollResumen);
        pestanaResumen.add(panelBotonesResumen);
    }
    public void siguientePaso() {
        controlador.siguientePaso();
    }
    
    public void mostrarOperacion(String operacion){
        labelOperaciones.setText("Siguientes operaciones:\n"+operacion);
    }
    
    public void mostrarMensaje(String mensaje){
        labelMensaje.setText(mensaje);
    }
    
    public void configurarLayout(int cantFilas, int cantColumnas) {
        panelTabla.setLayout(new GridLayout(cantFilas,cantColumnas));
        panelRadios.setLayout(new GridLayout(cantFilas, 1));
    }
    
    public void colocarComponentes(int cantFilas, int cantColumnas){
        int anchoTablaNumeros = ANCHO_CASILLA * cantColumnas;
        int altoTablaNumeros = ALTO_CASILLA * cantFilas;
        panelTabla.setBounds(POSICION_TABLA_X, POSICION_TABLA_Y, anchoTablaNumeros, altoTablaNumeros);
        int posicionRadiosX1 = POSICION_TABLA_X + anchoTablaNumeros + ESPACIO_TABLAS;
        int posicionRadiosY1 = POSICION_TABLA_Y;
        int anchoTablaRadios = ANCHO_CASILLA;
        int altoTablaRadios = altoTablaNumeros;
        panelRadios.setBounds(posicionRadiosX1, posicionRadiosY1, anchoTablaRadios, altoTablaRadios);
        panelRadios.setBackground(Color.white);
        panelBotonesMatriz.setBounds(POSICION_TABLA_X , POSICION_TABLA_Y + altoTablaRadios, 300,40);
        labelOperaciones.setBounds(POSICION_TABLA_X + 300, POSICION_TABLA_Y + altoTablaRadios + 5, 250,100);
        labelOperaciones.setBackground(new Color(214, 217, 223));
        Dimension dimension = this.getSize();
        this.setSize(Math.max(anchoTablaNumeros + anchoTablaRadios + 200, dimension.width), Math.max(altoTablaNumeros * 2 + 100, dimension.height));
    }
    
    public void mostrarMatriz(DtoSimplex dto){
        this.esPrimeraFase = dto.esDosfases();
        String[][] matriz = dto.getMatrizString();
        String[] nombresColumnas = dto.getNombreColumnas();
        String[] nombresFilas = dto.getNombreFilas();
        Point coordenada = dto.getCoordenadaPivote();
        String[] listaRadios = controlador.generarRadios(coordenada.x);
        resumenPasoAnterior.setLength(0);
        resumenPasoAnterior.append(dto.toString());
        this.matrizFracciones = dto.getMatrizString();
        int cantFilas = matriz.length + 1;
        int cantColumnas = matriz[0].length + 2;
        
        configurarLayout(cantFilas, cantColumnas);
        //inicializa la matriz de labels
        initMatriz(cantFilas, cantColumnas);
        //inicializa la tabla de radios
        initRadios(listaRadios.length);
        //rellena la matriz con los valores
        rellenarMatriz(matriz, nombresColumnas, nombresFilas);
        //pinta casilla seleccionada
        seleccionarCasilla(coordenada);
        //rellena la tabla con los radios
        rellenarRadios(listaRadios);
        //rellena el cuadrod el texto del resumen
        rellenarResumen(controlador.generarResumen());
        //muestra las proximas operaciones basadas en el pivote actual. 
        mostrarMensaje(dto.getMensaje());
        //muestra las proximas operaciones basadas en el pivote actual. 
        mostrarOperacion(dto.getOperaciones());
        //colocar los componentes en sus respectivas posiciones.
        colocarComponentes(cantFilas, cantColumnas);

        panelTabla.validate();
        panelTabla.repaint();
        this.setVisible(true);
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


    private void initMatriz(int cantFilas, int cantColumnas) {
        panelTabla.removeAll();
        this.matrizLabels = new JLabel[cantFilas][cantColumnas];
        //crear matriz de labels
        for (int i = 0; i < cantFilas; i++) {
            for (int j = 0; j < cantColumnas; j++) {
                JLabel label = new JLabel();
                label.setBorder(BorderFactory.createLineBorder(Color.black));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Courier New", Font.BOLD, 14));;
                matrizLabels[i][j] = label;
                panelTabla.add(label);
            }
        }
    }

    private void seleccionarCasilla(Point coordenada) {
        if (coordenada.x < 0 && coordenada.y < 0) {
            coordenada.x = -1;
            coordenada.y = 0;
            //return;
        }
        panelPestana.removeKeyListener(keyboardListener);
        this.casillaSeleccionada.x = coordenada.x;
        this.casillaSeleccionada.y = coordenada.y;
        int columna = coordenada.x + 2;
        int fila = coordenada.y + 1;
        JLabel label;
        final JLabel labelNumero = matrizLabels[fila][columna];
        final String valorOriginal = labelNumero.getText();
        labelNumero.setBackground(Color.RED);
        labelNumero.setOpaque(true);
        label = matrizLabels[0][columna];
        label.setBackground(Color.YELLOW);
        label.setOpaque(true);
        label = matrizLabels[fila][0];
        label.setBackground(Color.YELLOW);
        keyboardListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (panelPestana.getSelectedIndex() == 1) {
                    return;
                }
                char tecla = ke.getKeyChar();
                switch(tecla){
                    case '0':
                        keyBuffer.append('0');
                        break;
                    case '1':
                        keyBuffer.append('1');
                        break;
                    case '2':
                        keyBuffer.append('2');
                        break;
                    case '3':
                        keyBuffer.append('3');
                        break;
                    case '4':
                        keyBuffer.append('4');
                        break;
                    case '5':
                        keyBuffer.append('5');
                        break;
                    case '6':
                        keyBuffer.append('6');
                        break;
                    case '7':
                        keyBuffer.append('7');
                        break;
                    case '8':
                        keyBuffer.append('8');
                        break;
                    case '9':
                        keyBuffer.append('9');
                        break;
                    case '-':
                        if(keyBuffer.length() == 0)
                            keyBuffer.append('-');
                        break;
                    case '\b':
                        if(keyBuffer.length() > 0)
                            keyBuffer.setLength(keyBuffer.length()-1);
                        break;
                    case '.':
                        if(keyBuffer.indexOf(".") == -1) {
                            keyBuffer.append(".");
                        }
                        break;
                    case '/':
                        if(keyBuffer.length() > 0 && keyBuffer.indexOf("/") == -1) {
                            keyBuffer.append("/");
                        }
                        break;
                    case '\n':
                        if(esNumeroEntero(keyBuffer.toString())){
                            matrizFracciones[coordenada.y][coordenada.x] = keyBuffer.toString();
                            controlador.modificarEntradaMatriz(coordenada.y, coordenada.x, keyBuffer.toString());
                            JOptionPane.showMessageDialog(null, "Valor actualizado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            keyBuffer.setLength(0);
                        }else if(esFraccion(keyBuffer.toString())){
                            matrizFracciones[coordenada.y][coordenada.x] = keyBuffer.toString();
                            controlador.modificarEntradaMatriz(coordenada.y, coordenada.x, keyBuffer.toString());
                            JOptionPane.showMessageDialog(null, "Valor actualizado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            keyBuffer.setLength(0);
                        } else if (esDecimal(keyBuffer.toString())){
                            matrizFracciones[coordenada.y][coordenada.x] = keyBuffer.toString();
                            controlador.modificarEntradaMatriz(coordenada.y, coordenada.x, keyBuffer.toString());
                            JOptionPane.showMessageDialog(null, "Valor actualizado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            keyBuffer.setLength(0);
                        } else{
                            JOptionPane.showMessageDialog(null, "El numero ingresado no tiene formato valido.", "Error", JOptionPane.ERROR_MESSAGE);
                            keyBuffer.setLength(0);
                        }
                }
                mostrarOperacion(controlador.siguientesOperaciones(coordenada));
                if(keyBuffer.length() > 0)
                    labelNumero.setText(keyBuffer.toString());
                else
                    labelNumero.setText(matrizFracciones[coordenada.y][coordenada.x].toString());
            }
            @Override
            public void keyPressed(KeyEvent ke) {
            }
            @Override
            public void keyReleased(KeyEvent ke) {
            }

            private boolean esNumeroEntero(String string) {
                return string.matches("-?[0-9]*");
            }
            private boolean esFraccion(String string) {
                return string.matches("-?[1-9][0-9]*/[1-9][0-9]*");
            }
            private boolean esDecimal(String string) {
                return string.matches("-?[0-9]*\\.[0-9]+");
            }
        };
        panelPestana.addKeyListener(keyboardListener);
        label.setOpaque(true);
    }
    
    public void despintarCasilla() {
        Point coordenada = this.casillaSeleccionada;
        int columna = coordenada.x + 2;
        int fila = coordenada.y + 1;
        JLabel label = matrizLabels[fila][columna];
        label.setOpaque(false);
        label = matrizLabels[0][columna];
        label.setOpaque(false);
        label = matrizLabels[fila][0];
        label.setOpaque(false);
        if (coordenada.x == -1) {
            matrizLabels[fila][columna].setText("-w");
        } else {
            matrizLabels[fila][columna].setText(matrizFracciones[coordenada.y][coordenada.x]);
        }
        keyBuffer.setLength(0);
        panelPestana.removeKeyListener(keyboardListener);
        panelPestana.validate();
        panelPestana.repaint();
    }
    
    private void rellenarMatriz(String[][] matriz, String[] nombresColumnas, String[] nombresFilas){
        int i;
        //rellenar la primera fila (nombres de variables)
        for (i = 0; i < nombresColumnas.length ; i++) {
            matrizLabels[0][i+2].setText(nombresColumnas[i]);
        }
        matrizLabels[0][matrizLabels[0].length-1].setText("RHS");
        //rellenar la primera columna (numero de restriccion)
        if (esPrimeraFase) {
            i = 0;
            matrizLabels[1][0].setText("0'");
            for (i=1; i < matriz.length; i++) {
                matrizLabels[i+1][0].setText(String.valueOf(i-1));
            }
        }else{
            for (i=1; i <= matriz.length; i++) {
                matrizLabels[i][0].setText(String.valueOf(i-1));
            }
        }
        
        //rellenar la segunda columna (Variabls basicas)
        for (i = 1; i <= nombresFilas.length ; i++) {
            matrizLabels[i][1].setText(nombresFilas[i-1]);
        }
        //rellenar los valores numericos
        for (i = 1; i < matriz.length + 1; i++) {
            for (int j = 2; j < matriz[0].length + 2; j++) {
                final Point coordenada = new Point(j-2,i-1);
                String fraccion = matriz[i-1][j-2];
                matrizLabels[i][j].setText(fraccion);
                matrizLabels[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent me) {
                        if(me.getButton() == MouseEvent.BUTTON1){
                            me.consume();
                            despintarCasilla();
                            seleccionarCasilla(coordenada);
                            mostrarOperacion(controlador.siguientesOperaciones(coordenada));
                            String[] listaRadios = controlador.generarRadios(coordenada.x);
                            rellenarRadios(listaRadios);
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                    }
                });
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JTabbedPane panelPestana = new javax.swing.JTabbedPane();
    // End of variables declaration//GEN-END:variables

    private void rellenarRadios(String[] listaRadios) {
        for (int i = 0; i < listaRadios.length; i++) {
            int limiteRestricciones = this.esPrimeraFase ? 2 : 1;
            if (i < limiteRestricciones) {
                matrizRadios[i].setText("-");
            } else {
                matrizRadios[i].setText(listaRadios[i]);
            }
        }
    }

    private void initRadios(int cantRestricciones) {
        panelRadios.removeAll();
        matrizRadios = new JLabel[cantRestricciones];
        JLabel label = new JLabel("Radios");
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Courier New", Font.BOLD, 14));
        panelRadios.add(label);
        for (int i = 0; i < cantRestricciones; i++) {
            label = new JLabel();
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Courier New", Font.BOLD, 14));
            matrizRadios[i] = label;
            panelRadios.add(label);
        }
    }
    
    public void mostrarMensajeError(String mensaje, String encabezado){
        JOptionPane.showMessageDialog(null, mensaje, encabezado, JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarMensajeInformacion(String mensaje, String encabezado){
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
