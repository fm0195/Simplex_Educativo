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
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import modelo.parser.sym;

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
    JTextArea labelMensaje;
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
    JMenuBar barraMenu;
    JMenu menuRestricciones;
    JMenuItem itemMenuMayorIgual;
    JMenuItem itemMenuMenorIgual;
    JMenuItem itemMenuIgual;
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

    /**
     * Pantalla de pasos intermedios utilizada para dibujar la matriz de pasos y
     * capturar los eventos del usuario con el sistema.
     *
     * @param controlador el controlador que se conectará con el modelo.
     */
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

    /**
     * Inicializa todas los componentes y variables necesarios para el
     * despliegue de la pantalla.
     */
    public void initVariables() {
        panelTabla = new JPanel();
        panelRadios = new JPanel();
        panelBotonesMatriz = new JPanel(new GridBagLayout());
        panelBotonesResumen = new JPanel();
        pestanaResumen = new JPanel(new GridBagLayout());
        pestanaResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        pestanaMatriz = new JPanel(new GridBagLayout());
        
        labelOperaciones = new JTextArea();
        labelOperaciones.setEditable(false);
        labelOperaciones.setLineWrap(true);
        labelOperaciones.setBorder(null);
        labelOperaciones.setFocusable(false);
        labelOperaciones.setFont(new Font("Courier New", Font.BOLD, 14));
        labelOperaciones.setBackground(new Color(214, 217, 223));
        labelMensaje = new JTextArea();
        labelMensaje.setEditable(false);
        labelMensaje.setLineWrap(true);
        labelMensaje.setBorder(null);
        labelMensaje.setFocusable(false);
        labelMensaje.setFont(new Font("Courier New", Font.BOLD, 14));
        labelMensaje.setBackground(new Color(214, 217, 223));
        labelMensaje.setPreferredSize(new Dimension(700, 120));
        labelMensaje.setWrapStyleWord(true);
        labelResumen = new JTextArea();
        labelResumen.setEditable(false);
        labelResumen.setLineWrap(false);
        labelResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        scrollResumen = new JScrollPane(labelResumen);
        scrollResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        botonSiguienteMatriz = new JButton("Siguiente Paso");
        botonSiguienteMatriz.setFont(new Font("Courier New", Font.BOLD, 14));
        botonAnteriorMatriz = new JButton("Paso Anterior");
        botonAnteriorMatriz.setFont(new Font("Courier New", Font.BOLD, 14));
        botonSiguienteResumen = new JButton("Siguiente Paso");
        botonSiguienteResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        botonAnteriorResumen = new JButton("Paso Anterior");
        botonAnteriorResumen.setFont(new Font("Courier New", Font.BOLD, 14));
        botonCopiarPaso = new JButton("Copiar Paso Anterior");
        botonCopiarPaso.setFont(new Font("Courier New", Font.BOLD, 14));
        botonCopiarTodo = new JButton("Copiar Todo");
        botonCopiarTodo.setFont(new Font("Courier New", Font.BOLD, 14));
        panelTabla.setBackground(Color.WHITE);
        botonAnteriorMatriz.setFocusable(false);
        botonSiguienteMatriz.setFocusable(false);
        barraMenu = new JMenuBar();
        menuRestricciones = new JMenu("Agregar restricción");
        itemMenuMayorIgual = new JMenuItem(">=");
        itemMenuMenorIgual= new JMenuItem("<=");
        itemMenuIgual= new JMenuItem("=");
    }

    /**
     * Agrega las acciones a los botones de la pantalla
     */
    public void agregarActionListeners() {
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
        itemMenuIgual.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.agregarRestriccion(sym.IGUAL);
            }
        });
        itemMenuMayorIgual.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.agregarRestriccion(sym.MAYORIGUAL);
            }
        });
        itemMenuMenorIgual.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.agregarRestriccion(sym.MENORIGUAL);
            }
        });
    }

    /**
     * Agrega los componentes en su respectivo contenedor, y agrega los
     * componentes a la pantalla principal.
     */
    public void agregarComponentes() {
        GridBagConstraints propiedades = new GridBagConstraints();
        
        propiedades.gridx = 0;
        propiedades.gridy = 0;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0.1;
        propiedades.anchor = GridBagConstraints.FIRST_LINE_START;
        panelBotonesMatriz.add(botonAnteriorMatriz, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 1;
        propiedades.gridy = 0;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0.1;
        propiedades.anchor = GridBagConstraints.FIRST_LINE_START;
        panelBotonesMatriz.add(botonSiguienteMatriz, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 2;
        propiedades.gridy = 0;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 1;
        propiedades.anchor = GridBagConstraints.FIRST_LINE_START;
        propiedades.fill = GridBagConstraints.VERTICAL;
        propiedades.weighty = 1;
        panelBotonesMatriz.add(labelOperaciones, propiedades);
        
        panelBotonesResumen.add(botonAnteriorResumen);
        panelBotonesResumen.add(botonSiguienteResumen);
        panelBotonesResumen.add(botonCopiarPaso);
        panelBotonesResumen.add(botonCopiarTodo);
        panelPestana.addTab("Matriz Numérica", pestanaMatriz);
        panelPestana.addTab("Resumen de pasos", pestanaResumen);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 0;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.fill = GridBagConstraints.BOTH;
        pestanaMatriz.add(labelMensaje, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 1;
        propiedades.gridwidth = 2;
        propiedades.gridheight = 1;
        propiedades.weightx = 0.9;
        propiedades.weighty = 0.9;
        propiedades.fill = GridBagConstraints.BOTH;
        pestanaMatriz.add(panelTabla, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 2;
        propiedades.gridy = 1;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0.1;
        propiedades.fill = GridBagConstraints.BOTH;
        propiedades.insets = new Insets(0, 10, 0, 10);
        pestanaMatriz.add(panelRadios, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 3;
        propiedades.gridwidth = 2;
        propiedades.gridheight = 1;
        propiedades.weightx = 0.1;
        propiedades.weighty = 0.5;
        propiedades.fill = GridBagConstraints.BOTH;
        pestanaMatriz.add(panelBotonesMatriz, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 0;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0.1;
        propiedades.weighty = 1;
        propiedades.fill = GridBagConstraints.BOTH;
        pestanaResumen.add(scrollResumen, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 1;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0.1;
        propiedades.weighty = 0.1;
        pestanaResumen.add(panelBotonesResumen, propiedades);
        
        menuRestricciones.add(itemMenuMenorIgual);
        menuRestricciones.add(itemMenuIgual);
        menuRestricciones.add(itemMenuMayorIgual);
        barraMenu.add(menuRestricciones);
        this.setJMenuBar(barraMenu);
    }

    /**
     * Pide al controlador el siguiente paso del problema.
     */
    public void siguientePaso() {
        controlador.siguientePaso();
    }

    /**
     * Muestra la operacion recibida por parámetro en el área de texto
     * correspondiente
     *
     * @param operacion
     */
    public void mostrarOperacion(String operacion) {
        labelOperaciones.setText("Siguientes operaciones:\n" + operacion);
    }

    /**
     * Muetra el mensaje informativo sobre el paso anterior en el label
     * correspondiente
     *
     * @param mensaje mensaje por mostrar
     */
    private void mostrarMensaje(String mensaje) {
        String informacion = "+ Seleccione una casilla para pivotar haciendo clic sobre ella.\n"+
                             "+ Utilice el teclado para modificar la entrada seleccionada, luego presione Enter\n" +
                             "+ Presione el botón \"Siguiente Paso\" para pivotar en la casilla seleccionada.\n"+ 
                             "+ Agregue una restricción mediante el menú en la parte superior de la pantalla.\n"+ 
                             "+ Cierre esta ventana para volver al menú principal."   ;
        labelMensaje.setText(informacion + "\n\n" + mensaje);
    }

    /**
     * Configura el layout del panel que contiene la matriz a un GridLayout con
     * la cantidad de filas y columnas indicadas.
     *
     * @param cantFilas cantidad de filas del layout
     * @param cantColumnas cantidad de columnas del layout
     */
    public void configurarLayout(int cantFilas, int cantColumnas) {
        panelTabla.setLayout(new GridLayout(cantFilas, cantColumnas));
        panelRadios.setLayout(new GridLayout(cantFilas, 1));
    }

    /**
     * Coloca los componentes en sus posiciones relativas al tamaño de la matriz
     * recibido por parametro,
     *
     * @param cantFilas cantidad de filas de la matriz mostrada
     * @param cantColumnas cantidad de columnas de la matriz mostrada.
     */
    public void colocarComponentes(int cantFilas, int cantColumnas) {
        int anchoTablaNumeros = ANCHO_CASILLA * cantColumnas;
        int altoTablaNumeros = ALTO_CASILLA * cantFilas;
        int posicionRadiosX1 = POSICION_TABLA_X + anchoTablaNumeros + ESPACIO_TABLAS;
        int posicionRadiosY1 = POSICION_TABLA_Y;
        int anchoTablaRadios = ANCHO_CASILLA;
        int altoTablaRadios = altoTablaNumeros;
        panelRadios.setBackground(Color.white);
        labelOperaciones.setPreferredSize(new Dimension(200, 70));
        Dimension dimension = this.getSize();
    }

    public void mostrarMatriz(DtoSimplex dto) {
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

        panelPestana.setAutoscrolls(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPestana, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPestana, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
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
                switch (tecla) {
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
                        if (keyBuffer.length() == 0) {
                            keyBuffer.append('-');
                        }
                        break;
                    case '\b':
                        if (keyBuffer.length() > 0) {
                            keyBuffer.setLength(keyBuffer.length() - 1);
                        }
                        break;
                    case '.':
                        if (keyBuffer.indexOf(".") == -1) {
                            keyBuffer.append(".");
                        }
                        break;
                    case '/':
                        if (keyBuffer.length() > 0 && keyBuffer.indexOf("/") == -1) {
                            keyBuffer.append("/");
                        }
                        break;
                    case '\n':
                        if (keyBuffer.toString().isEmpty()) {
                            return;
                        }
                        if (esNumeroEntero(keyBuffer.toString())) {
                            matrizFracciones[coordenada.y][coordenada.x] = keyBuffer.toString();
                            controlador.modificarEntradaMatriz(coordenada.y, coordenada.x, keyBuffer.toString());
                            JOptionPane.showMessageDialog(null, "Valor actualizado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            keyBuffer.setLength(0);
                        } else {
                            if (esFraccion(keyBuffer.toString())) {
                                matrizFracciones[coordenada.y][coordenada.x] = keyBuffer.toString();
                                controlador.modificarEntradaMatriz(coordenada.y, coordenada.x, keyBuffer.toString());
                                JOptionPane.showMessageDialog(null, "Valor actualizado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                                keyBuffer.setLength(0);
                            } else {
                                if (esDecimal(keyBuffer.toString())) {
                                    matrizFracciones[coordenada.y][coordenada.x] = keyBuffer.toString();
                                    controlador.modificarEntradaMatriz(coordenada.y, coordenada.x, keyBuffer.toString());
                                    JOptionPane.showMessageDialog(null, "Valor actualizado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                                    keyBuffer.setLength(0);
                                } else {
                                    JOptionPane.showMessageDialog(null, "El numero ingresado no tiene formato valido.", "Error", JOptionPane.ERROR_MESSAGE);
                                    keyBuffer.setLength(0);
                                }
                            }
                        }
                }
                mostrarOperacion(controlador.siguientesOperaciones(coordenada));
                if (keyBuffer.length() > 0) {
                    labelNumero.setText(keyBuffer.toString());
                } else {
                    labelNumero.setText(matrizFracciones[coordenada.y][coordenada.x].toString());
                }
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

    /**
     * Despinta la casilla seleccionada junto con las casillas de su fila y
     * columna que habían sido marcadas cuando estuvieron seleccionadas.
     */
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

    private void rellenarMatriz(String[][] matriz, String[] nombresColumnas, String[] nombresFilas) {
        int i;
        //rellenar la primera fila (nombres de variables)
        for (i = 0; i < nombresColumnas.length; i++) {
            matrizLabels[0][i + 2].setText(nombresColumnas[i]);
        }
        matrizLabels[0][matrizLabels[0].length - 1].setText("RHS");
        //rellenar la primera columna (numero de restriccion)
        if (esPrimeraFase) {
            i = 0;
            matrizLabels[1][0].setText("0'");
            for (i = 1; i < matriz.length; i++) {
                matrizLabels[i + 1][0].setText(String.valueOf(i - 1));
            }
        } else {
            for (i = 1; i <= matriz.length; i++) {
                matrizLabels[i][0].setText(String.valueOf(i - 1));
            }
        }

        //rellenar la segunda columna (Variabls basicas)
        for (i = 1; i <= nombresFilas.length; i++) {
            matrizLabels[i][1].setText(nombresFilas[i - 1]);
        }
        //rellenar los valores numericos
        for (i = 1; i < matriz.length + 1; i++) {
            for (int j = 2; j < matriz[0].length + 2; j++) {
                final Point coordenada = new Point(j - 2, i - 1);
                String fraccion = matriz[i - 1][j - 2];
                matrizLabels[i][j].setText(fraccion);
                matrizLabels[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent me) {

                    }

                    @Override
                    public void mousePressed(MouseEvent me) {
                        if (me.getButton() == MouseEvent.BUTTON1) {
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
