/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.AbstractControlador;
import controlador.GomoryControlador;
import controlador.SimplexControlador;
import controlador.MatrizControlador;
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
        this.areaTexto.setText(problema);
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
        labelFormato = new javax.swing.JLabel();
        radioFraccion = new javax.swing.JRadioButton();
        radioDecimal = new javax.swing.JRadioButton();
        labelEntero = new javax.swing.JLabel();
        radioGomory = new javax.swing.JRadioButton();
        radioBB = new javax.swing.JRadioButton();
        labelPasosIntermedios = new javax.swing.JLabel();
        radioMostrarPasos = new javax.swing.JRadioButton();
        radioSolucionDirecta = new javax.swing.JRadioButton();
        botonBorrar = new javax.swing.JButton();
        botonSimplex = new javax.swing.JButton();
        radioSimplex = new javax.swing.JRadioButton();
        radioMatriz = new javax.swing.JRadioButton();

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
        jTextArea1.setText("Ejemplo de Simplex:\n(0) max z = 15 x1 + 10 x2\n(1)            x1          <= 2\n(2)                    x2  >= 3\n(3)            x1 +    x2   = 4\n\nEjemplo de Matriz:\n1   2   3\n4   5   6");
        jTextArea1.setBorder(null);
        jTextArea1.setCaretColor(new java.awt.Color(214, 217, 223));
        jScrollPane2.setViewportView(jTextArea1);

        labelFormato2.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        labelFormato2.setText("Por favor ingrese un problema:");

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        labelFormato.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        labelFormato.setText("Escoja el formato numérico");

        grupoFormato.add(radioFraccion);
        radioFraccion.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioFraccion.setSelected(true);
        radioFraccion.setText("Fracción");
        radioFraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFraccionActionPerformed(evt);
            }
        });

        grupoFormato.add(radioDecimal);
        radioDecimal.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioDecimal.setText("Decimal");
        radioDecimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDecimalActionPerformed(evt);
            }
        });

        labelEntero.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        labelEntero.setText("Escoja el metodo de solucion");

        grupoSolucion.add(radioGomory);
        radioGomory.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioGomory.setText("Cortes de Gomory");
        radioGomory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioGomoryActionPerformed(evt);
            }
        });

        grupoSolucion.add(radioBB);
        radioBB.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioBB.setText("Branch and Bound");
        radioBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBBActionPerformed(evt);
            }
        });

        labelPasosIntermedios.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        labelPasosIntermedios.setText("¿Desea mostrar los pasos intermedios?");

        grupoPasos.add(radioMostrarPasos);
        radioMostrarPasos.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioMostrarPasos.setSelected(true);
        radioMostrarPasos.setText("Mostrar Pasos");
        radioMostrarPasos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMostrarPasosActionPerformed(evt);
            }
        });

        grupoPasos.add(radioSolucionDirecta);
        radioSolucionDirecta.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioSolucionDirecta.setText("Solucion Directa");
        radioSolucionDirecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSolucionDirectaActionPerformed(evt);
            }
        });

        botonBorrar.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        botonBorrar.setLabel("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        botonSimplex.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        botonSimplex.setText("Solucionar");
        botonSimplex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSimplexActionPerformed(evt);
            }
        });

        grupoSolucion.add(radioSimplex);
        radioSimplex.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        radioSimplex.setSelected(true);
        radioSimplex.setText("Simplex de Dos Fases");
        radioSimplex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSimplexActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFormato)
                            .addComponent(labelEntero)
                            .addComponent(botonSimplex)
                            .addComponent(botonBorrar)
                            .addComponent(labelPasosIntermedios)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioFraccion)
                                    .addComponent(radioDecimal)
                                    .addComponent(radioMostrarPasos)
                                    .addComponent(radioSolucionDirecta)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioMatriz)
                            .addComponent(radioBB)
                            .addComponent(radioGomory)
                            .addComponent(radioSimplex))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelFormato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioFraccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioDecimal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEntero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioSimplex)
                .addGap(5, 5, 5)
                .addComponent(radioMatriz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioGomory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioBB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPasosIntermedios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioMostrarPasos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSolucionDirecta)
                .addGap(18, 18, 18)
                .addComponent(botonSimplex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonBorrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioFraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFraccionActionPerformed
        fraccionario = true;
    }//GEN-LAST:event_radioFraccionActionPerformed

    private void radioDecimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDecimalActionPerformed
        fraccionario = false;
    }//GEN-LAST:event_radioDecimalActionPerformed

    private void radioGomoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioGomoryActionPerformed
        gomory = true;
        branchAndBound = false;
        solucionSimplex = false;
        matrizNumerica = false;
        labelPasosIntermedios.setVisible(true);
        radioMostrarPasos.setVisible(true);
        radioSolucionDirecta.setVisible(true);
    }//GEN-LAST:event_radioGomoryActionPerformed

    private void radioBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBBActionPerformed
        gomory = false;
        branchAndBound = true;
        solucionSimplex = false;
        matrizNumerica = false;
        labelPasosIntermedios.setVisible(true);
        radioMostrarPasos.setVisible(true);
        radioSolucionDirecta.setVisible(true);
    }//GEN-LAST:event_radioBBActionPerformed

    private void radioMostrarPasosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMostrarPasosActionPerformed
        solucionDirecta = false;
    }//GEN-LAST:event_radioMostrarPasosActionPerformed

    private void radioSolucionDirectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSolucionDirectaActionPerformed
        solucionDirecta = true;
    }//GEN-LAST:event_radioSolucionDirectaActionPerformed

    private void botonSimplexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSimplexActionPerformed
        String texto;
        if (solucionSimplex) {
            controlador = new SimplexControlador();
            if (solucionDirecta) {
                controlador.setVista(new PantallaPasoIntermedio(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "");
                controlador.solucionar(texto, fraccionario);
                this.dispose();
            } else {
                controlador.setVista(new PantallaPasoIntermedio(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "");
                controlador.siguientePaso(texto, fraccionario);
                this.dispose();
            }
        }
        if (matrizNumerica) {
            controlador = new MatrizControlador();
            controlador.setVista(new PantallaPasoIntermedio(controlador));
            controlador.siguientePaso(areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", ""), fraccionario);
            this.dispose();
        }
        if(gomory){
            controlador = new GomoryControlador();
            if (solucionDirecta) {
                controlador.setVista(new PantallaPasoIntermedio(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "");
                controlador.solucionar(texto, fraccionario);
                this.dispose();
            } else {
                controlador.setVista(new PantallaPasoIntermedio(controlador));
                texto = areaTexto.getText().replaceAll("(?m)^[ \t]*\r?\n", "");
                controlador.siguientePaso(texto, fraccionario);
                this.dispose();
            }
        }
            
    }//GEN-LAST:event_botonSimplexActionPerformed

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
        areaTexto.setText("");
    }//GEN-LAST:event_botonBorrarActionPerformed

    private void radioSimplexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSimplexActionPerformed
        solucionSimplex = true;
        matrizNumerica = false;
        labelPasosIntermedios.setVisible(true);
        radioMostrarPasos.setVisible(true);
        radioSolucionDirecta.setVisible(true);
    }//GEN-LAST:event_radioSimplexActionPerformed

    private void radioMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMatrizActionPerformed
        solucionSimplex = false;
        matrizNumerica = true;
        labelPasosIntermedios.setVisible(false);
        radioMostrarPasos.setVisible(false);
        radioSolucionDirecta.setVisible(false);
    }//GEN-LAST:event_radioMatrizActionPerformed

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
    private javax.swing.JButton botonSimplex;
    private javax.swing.ButtonGroup grupoFormato;
    private javax.swing.ButtonGroup grupoPasos;
    private javax.swing.ButtonGroup grupoSolucion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelEntero;
    private javax.swing.JLabel labelFormato;
    private javax.swing.JLabel labelFormato1;
    private javax.swing.JLabel labelFormato2;
    private javax.swing.JLabel labelPasosIntermedios;
    private javax.swing.JRadioButton radioBB;
    private javax.swing.JRadioButton radioDecimal;
    private javax.swing.JRadioButton radioFraccion;
    private javax.swing.JRadioButton radioGomory;
    private javax.swing.JRadioButton radioMatriz;
    private javax.swing.JRadioButton radioMostrarPasos;
    private javax.swing.JRadioButton radioSimplex;
    private javax.swing.JRadioButton radioSolucionDirecta;
    // End of variables declaration//GEN-END:variables
}
