/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.AbstractControlador;
import controlador.BranchAndBoundControlador;
import controlador.GomoryControlador;
import controlador.SimplexControlador;
import controlador.MatrizControlador;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.Fraccion;

/**
 *
 * @author fm010
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    private boolean fraccionario;
    private boolean gomory;
    private boolean branchAndBound;
    private boolean solucionDirecta;
    private boolean matrizNumerica;
    private boolean solucionSimplex;
    AbstractControlador controlador;

    public PantallaPrincipal(String problema) {
        this.solucionSimplex = true;
        this.fraccionario = true;
        this.solucionDirecta = false;
        this.gomory = false;
        initComponents();
        this.panelFormatoNumerico.setVisible(false);
        this.panelMetodoSolucion.setVisible(false);
        this.panelPasosIntermedios.setVisible(false);
        this.botonSimplex.setVisible(false);
        this.botonBorrar.setVisible(false);
        this.areaTexto.setText(problema);
        this.areaTexto.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                panelFormatoNumerico.setVisible(true);
                botonBorrar.setVisible(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                insertUpdate(e);
            }
        });
        setLayout(new GridBagLayout());
        
        GridBagConstraints propiedades = new GridBagConstraints();
        
        propiedades = new GridBagConstraints();
        propiedades.gridx=0;
        propiedades.gridy=0;
        propiedades.gridwidth=1;
        propiedades.ipadx = 20;
        propiedades.ipady = 20;
        propiedades.weightx = 0.1;
        propiedades.weighty = 0.1;
        propiedades.anchor = GridBagConstraints.FIRST_LINE_START;
        propiedades.fill = GridBagConstraints.BOTH;
        propiedades.insets = new Insets(10, 10, 10, 10);
        add(jPanel1, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx=1;
        propiedades.gridy=0;
        propiedades.gridwidth=1;
        propiedades.ipadx = 20;
        propiedades.ipady = 20;
        propiedades.weightx = 0.000000000000001;
        propiedades.weighty = 0.000000000000001;
        propiedades.anchor = GridBagConstraints.LAST_LINE_START;
        propiedades.fill = GridBagConstraints.BOTH;
        add(jPanel3, propiedades);
        
        
        /************************************************************************************/
        
        jPanel1.setLayout(new GridBagLayout());
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 0;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0;
        propiedades.anchor = GridBagConstraints.FIRST_LINE_START;
        propiedades.fill = GridBagConstraints.VERTICAL;
        propiedades.weighty = 0;        
        jPanel1.add(labelFormato1, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 1;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0;
        propiedades.anchor = GridBagConstraints.FIRST_LINE_START;
        propiedades.fill = GridBagConstraints.VERTICAL;
        propiedades.weighty = 0;
        jPanel1.add(labelFormato2, propiedades);
                
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 2;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0.1;
        propiedades.anchor = GridBagConstraints.FIRST_LINE_START;
        propiedades.fill = GridBagConstraints.BOTH;
        propiedades.weighty = 0.1;
        
        JScrollPane scrolltxt = new JScrollPane(areaTexto);
        scrolltxt.setWheelScrollingEnabled(true);
        
        jPanel1.add(scrolltxt, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 3;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0;
        propiedades.anchor = GridBagConstraints.FIRST_LINE_START;
        propiedades.fill = GridBagConstraints.VERTICAL;
        propiedades.weighty = 0;
        jPanel1.add(jTextArea1, propiedades);
        
        propiedades = new GridBagConstraints();
        propiedades.gridx = 0;
        propiedades.gridy = 4;
        propiedades.gridwidth = 1;
        propiedades.gridheight = 1;
        propiedades.weightx = 0;
        propiedades.anchor = GridBagConstraints.FIRST_LINE_START;
        propiedades.fill = GridBagConstraints.VERTICAL;
        propiedades.weighty = 0;
        jPanel1.add(panelSpinners, propiedades);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        grupoFormato = new javax.swing.ButtonGroup();
        grupoSolucion = new javax.swing.ButtonGroup();
        grupoPasos = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaTexto = new javax.swing.JTextArea();
        labelFormato1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        labelFormato2 = new javax.swing.JLabel();
        panelSpinners = new javax.swing.JPanel();
        spinnerFilas = new javax.swing.JSpinner();
        spinnerColumnas = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botonGenerar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panelFormatoNumerico = new javax.swing.JPanel();
        radioDecimal = new javax.swing.JRadioButton();
        radioFraccion = new javax.swing.JRadioButton();
        labelFormato = new javax.swing.JLabel();
        panelMetodoSolucion = new javax.swing.JPanel();
        radioBB = new javax.swing.JRadioButton();
        radioGomory = new javax.swing.JRadioButton();
        radioMatriz = new javax.swing.JRadioButton();
        radioSimplex = new javax.swing.JRadioButton();
        labelEntero = new javax.swing.JLabel();
        panelPasosIntermedios = new javax.swing.JPanel();
        radioSolucionDirecta = new javax.swing.JRadioButton();
        radioMostrarPasos = new javax.swing.JRadioButton();
        labelPasosIntermedios = new javax.swing.JLabel();
        botonSimplex = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simplex Educativo. ");

        areaTexto.setColumns(12);
        areaTexto.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        areaTexto.setRows(5);
        areaTexto.setTabSize(4);
        areaTexto.setToolTipText("");
        areaTexto.setWrapStyleWord(true);
        areaTexto.setInheritsPopupMenu(true);
        jScrollPane1.setViewportView(areaTexto);

        labelFormato1.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        labelFormato1.setText("Simplex Educativo");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Ejemplo de Simplex:\n max z = 15 x1 + 10 x2\n            x1          <= 2\n                    x2  >= 3\n            x1 +    x2   = 4\n\nEjemplo de Matriz:\n1   2   3\n4   5   6");
        jTextArea1.setBorder(null);
        jTextArea1.setCaretColor(new java.awt.Color(214, 217, 223));
        jScrollPane2.setViewportView(jTextArea1);

        labelFormato2.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        labelFormato2.setText("Por favor ingrese un problema:");

        spinnerFilas.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        spinnerFilas.setModel(new javax.swing.SpinnerNumberModel(2, 2, null, 1));
        spinnerFilas.setOpaque(false);
        spinnerFilas.setValue(2);

        spinnerColumnas.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        spinnerColumnas.setModel(new javax.swing.SpinnerNumberModel(2, 2, null, 1));
        spinnerColumnas.setValue(2);

        jLabel1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel1.setText("Filas");

        jLabel2.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel2.setText("Columnas");

        botonGenerar.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        botonGenerar.setText("Generar Matriz");
        botonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSpinnersLayout = new javax.swing.GroupLayout(panelSpinners);
        panelSpinners.setLayout(panelSpinnersLayout);
        panelSpinnersLayout.setHorizontalGroup(
            panelSpinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSpinnersLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelSpinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSpinnersLayout.createSequentialGroup()
                        .addGroup(panelSpinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSpinnersLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSpinnersLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(25, 25, 25)))
                        .addGroup(panelSpinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spinnerFilas, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(spinnerColumnas)))
                    .addComponent(botonGenerar))
                .addGap(19, 19, 19))
        );
        panelSpinnersLayout.setVerticalGroup(
            panelSpinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSpinnersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSpinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSpinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonGenerar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelFormato1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(labelFormato2)
                    .addComponent(panelSpinners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFormato1)
                .addGap(8, 8, 8)
                .addComponent(labelFormato2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSpinners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grupoFormato.add(radioDecimal);
        radioDecimal.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioDecimal.setText("Decimal");
        radioDecimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDecimalActionPerformed(evt);
            }
        });

        grupoFormato.add(radioFraccion);
        radioFraccion.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioFraccion.setText("Fracción");
        radioFraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFraccionActionPerformed(evt);
            }
        });

        labelFormato.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        labelFormato.setText("Escoja el formato numérico");

        javax.swing.GroupLayout panelFormatoNumericoLayout = new javax.swing.GroupLayout(panelFormatoNumerico);
        panelFormatoNumerico.setLayout(panelFormatoNumericoLayout);
        panelFormatoNumericoLayout.setHorizontalGroup(
            panelFormatoNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormatoNumericoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFormatoNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFormato)
                    .addGroup(panelFormatoNumericoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelFormatoNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioFraccion)
                            .addComponent(radioDecimal))))
                .addGap(48, 48, 48))
        );
        panelFormatoNumericoLayout.setVerticalGroup(
            panelFormatoNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormatoNumericoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFormato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioFraccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioDecimal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grupoSolucion.add(radioBB);
        radioBB.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioBB.setText("Branch and Bound");
        radioBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBBActionPerformed(evt);
            }
        });

        grupoSolucion.add(radioGomory);
        radioGomory.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioGomory.setText("Cortes de Gomory");
        radioGomory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioGomoryActionPerformed(evt);
            }
        });

        grupoSolucion.add(radioMatriz);
        radioMatriz.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioMatriz.setText("Matriz Numérica");
        radioMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMatrizActionPerformed(evt);
            }
        });

        grupoSolucion.add(radioSimplex);
        radioSimplex.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioSimplex.setText("Simplex de Dos Fases");
        radioSimplex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSimplexActionPerformed(evt);
            }
        });

        labelEntero.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        labelEntero.setText("Escoja el metodo de solucion");

        javax.swing.GroupLayout panelMetodoSolucionLayout = new javax.swing.GroupLayout(panelMetodoSolucion);
        panelMetodoSolucion.setLayout(panelMetodoSolucionLayout);
        panelMetodoSolucionLayout.setHorizontalGroup(
            panelMetodoSolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMetodoSolucionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMetodoSolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEntero)
                    .addGroup(panelMetodoSolucionLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelMetodoSolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioBB)
                            .addComponent(radioSimplex)
                            .addComponent(radioGomory)
                            .addComponent(radioMatriz))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelMetodoSolucionLayout.setVerticalGroup(
            panelMetodoSolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMetodoSolucionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelEntero)
                .addGap(7, 7, 7)
                .addComponent(radioMatriz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSimplex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioGomory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioBB))
        );

        grupoPasos.add(radioSolucionDirecta);
        radioSolucionDirecta.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioSolucionDirecta.setText("Solucion Directa");
        radioSolucionDirecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSolucionDirectaActionPerformed(evt);
            }
        });

        grupoPasos.add(radioMostrarPasos);
        radioMostrarPasos.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioMostrarPasos.setText("Mostrar Pasos");
        radioMostrarPasos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMostrarPasosActionPerformed(evt);
            }
        });

        labelPasosIntermedios.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        labelPasosIntermedios.setText("¿Desea mostrar los pasos intermedios?");

        javax.swing.GroupLayout panelPasosIntermediosLayout = new javax.swing.GroupLayout(panelPasosIntermedios);
        panelPasosIntermedios.setLayout(panelPasosIntermediosLayout);
        panelPasosIntermediosLayout.setHorizontalGroup(
            panelPasosIntermediosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPasosIntermediosLayout.createSequentialGroup()
                .addGroup(panelPasosIntermediosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPasosIntermediosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelPasosIntermediosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioSolucionDirecta)
                            .addComponent(radioMostrarPasos)))
                    .addGroup(panelPasosIntermediosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelPasosIntermedios)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelPasosIntermediosLayout.setVerticalGroup(
            panelPasosIntermediosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPasosIntermediosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelPasosIntermedios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioMostrarPasos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSolucionDirecta)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        botonSimplex.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        botonSimplex.setText("Solucionar");
        botonSimplex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSimplexActionPerformed(evt);
            }
        });

        botonBorrar.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        botonBorrar.setLabel("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(panelFormatoNumerico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMetodoSolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPasosIntermedios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonSimplex)
                            .addComponent(botonBorrar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFormatoNumerico, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelMetodoSolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPasosIntermedios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonSimplex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonBorrar)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioFraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFraccionActionPerformed
        fraccionario = true;
        panelMetodoSolucion.setVisible(true);
    }//GEN-LAST:event_radioFraccionActionPerformed

    private void radioDecimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDecimalActionPerformed
        fraccionario = false;
        panelMetodoSolucion.setVisible(true);
    }//GEN-LAST:event_radioDecimalActionPerformed

    private void radioGomoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioGomoryActionPerformed
        gomory = true;
        branchAndBound = false;
        solucionSimplex = false;
        matrizNumerica = false;
        panelPasosIntermedios.setVisible(true);
        botonSimplex.setVisible(false);
    }//GEN-LAST:event_radioGomoryActionPerformed

    private void radioBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBBActionPerformed
        gomory = false;
        branchAndBound = true;
        solucionSimplex = false;
        matrizNumerica = false;
        panelPasosIntermedios.setVisible(true);
        botonSimplex.setVisible(false);
    }//GEN-LAST:event_radioBBActionPerformed

    private void radioMostrarPasosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMostrarPasosActionPerformed
        solucionDirecta = false;
        botonSimplex.setVisible(true);
    }//GEN-LAST:event_radioMostrarPasosActionPerformed

    private void radioSolucionDirectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSolucionDirectaActionPerformed
        solucionDirecta = true;
        botonSimplex.setVisible(true);
    }//GEN-LAST:event_radioSolucionDirectaActionPerformed

    private void botonSimplexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSimplexActionPerformed
        String texto;
        if (solucionSimplex) {
            controlador = new SimplexControlador();
            if (solucionDirecta) {
                controlador.setVista(new PantallaPasoIntermedio(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "").trim();
                controlador.solucionar(texto, fraccionario);
                this.dispose();
            } else {
                controlador.setVista(new PantallaPasoIntermedio(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "").trim();
                controlador.siguientePaso(texto, fraccionario);
                this.dispose();
            }
        }
        if (matrizNumerica) {
            controlador = new MatrizControlador();
            controlador.setVista(new PantallaPasoIntermedio(controlador));
            controlador.siguientePaso(areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "").trim(), fraccionario);
            this.dispose();
        }
        if (gomory) {
            controlador = new GomoryControlador();
            if (solucionDirecta) {
                controlador.setVista(new PantallaPasoIntermedio(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "").trim();
                controlador.solucionar(texto, fraccionario);
                this.dispose();
            } else {
                controlador.setVista(new PantallaPasoIntermedio(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "").trim();
                controlador.siguientePaso(texto, fraccionario);
                this.dispose();
            }
        }
        if (branchAndBound) {
            controlador = new BranchAndBoundControlador();
            if (solucionDirecta) {
                controlador.setVista(new PantallaPasoIntermedioBranchBound(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "").trim();
                controlador.solucionar(texto, fraccionario);
                this.dispose();
            } else {
                controlador.setVista(new PantallaPasoIntermedioBranchBound(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "").trim();
                controlador.siguientePaso(texto, fraccionario);
                this.dispose();
            }
        }
    }//GEN-LAST:event_botonSimplexActionPerformed

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
        areaTexto.setText("");
        panelFormatoNumerico.setVisible(false);
        panelMetodoSolucion.setVisible(false);
        panelPasosIntermedios.setVisible(false);
        botonBorrar.setVisible(false);
        botonSimplex.setVisible(false);
    }//GEN-LAST:event_botonBorrarActionPerformed

    private void radioSimplexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSimplexActionPerformed
        solucionSimplex = true;
        matrizNumerica = false;
        branchAndBound = false;
        gomory = false;
        panelPasosIntermedios.setVisible(true);
        botonSimplex.setVisible(false);
    }//GEN-LAST:event_radioSimplexActionPerformed

    private void radioMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMatrizActionPerformed
        solucionSimplex = false;
        matrizNumerica = true;
        branchAndBound = false;
        panelPasosIntermedios.setVisible(false);
        botonSimplex.setVisible(true);
    }//GEN-LAST:event_radioMatrizActionPerformed

    private void botonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGenerarActionPerformed
        String resultado = "";
        int filas = (int) spinnerFilas.getValue();
        int columnas = (int) spinnerColumnas.getValue();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado += (int) Math.floor(Math.random() * 101) + "\t";
            }
            resultado += "\n";
        }
        areaTexto.setText(resultado);
    }//GEN-LAST:event_botonGenerarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Fraccion f = new Fraccion(0.30);
                new PantallaPrincipal("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonGenerar;
    private javax.swing.JButton botonSimplex;
    private javax.swing.ButtonGroup grupoFormato;
    private javax.swing.ButtonGroup grupoPasos;
    private javax.swing.ButtonGroup grupoSolucion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelEntero;
    private javax.swing.JLabel labelFormato;
    private javax.swing.JLabel labelFormato1;
    private javax.swing.JLabel labelFormato2;
    private javax.swing.JLabel labelPasosIntermedios;
    private javax.swing.JPanel panelFormatoNumerico;
    private javax.swing.JPanel panelMetodoSolucion;
    private javax.swing.JPanel panelPasosIntermedios;
    private javax.swing.JPanel panelSpinners;
    private javax.swing.JRadioButton radioBB;
    private javax.swing.JRadioButton radioDecimal;
    private javax.swing.JRadioButton radioFraccion;
    private javax.swing.JRadioButton radioGomory;
    private javax.swing.JRadioButton radioMatriz;
    private javax.swing.JRadioButton radioMostrarPasos;
    private javax.swing.JRadioButton radioSimplex;
    private javax.swing.JRadioButton radioSolucionDirecta;
    private javax.swing.JSpinner spinnerColumnas;
    private javax.swing.JSpinner spinnerFilas;
    // End of variables declaration//GEN-END:variables
}
