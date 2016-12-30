/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import modelo.AbstractFraccion;
import modelo.DtoSimplex;
import modelo.Fraccion;

/**
 *
 * @author fm010
 */
public class PantallaPasoIntermedio extends javax.swing.JFrame {

    /**
     * Creates new form PantallaPasoIntermedio
     */
    AbstractFraccion[][] matrizFracciones;
    AbstractFraccion nuevaFraccion;
    JLabel[][] matrizLabels;
    JLabel[] matrizRadios;
    JTextPane labelResumen;
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
    final int ANCHO_CASILLA = 50;
    final int ALTO_CASILLA = 20;
    final int POSICION_TABLA_X = 20;
    final int POSICION_TABLA_Y = 50;
    int POSICION_RADIOS_X;
    int POSICION_RADIOS_Y;
    KeyListener keyboardListener;
    final StringBuilder keyBuffer;
    private int ESPACIO_TABLAS = 10;
    
    public PantallaPasoIntermedio() {
        initComponents();
        panelTabla = new JPanel();
        panelRadios = new JPanel();
        panelBotonesMatriz = new JPanel();
        panelBotonesResumen = new JPanel();
        pestanaResumen = new JPanel(null);
        pestanaMatriz = new JPanel(null);
        keyBuffer = new StringBuilder();
        labelResumen = new JTextPane();
        scrollResumen = new JScrollPane(labelResumen);
        scrollResumen.setBounds(0, 0, 290, 200);
        scrollResumen.setBackground(Color.red);
        botonSiguienteMatriz = new JButton("Siguiente");
        botonAnteriorMatriz = new JButton("Atras");
        botonSiguienteResumen = new JButton("Siguiente");
        botonAnteriorResumen = new JButton("Atras");
        botonCopiarPaso = new JButton("Copiar Paso");
        botonCopiarTodo = new JButton("Copiar Todo");
        panelTabla.setBackground(Color.WHITE);
        botonAnteriorMatriz.setFocusable(false);
        botonSiguienteMatriz.setFocusable(false);
        
        panelBotonesMatriz.add(botonAnteriorMatriz);
        panelBotonesMatriz.add(botonSiguienteMatriz);
        
        panelBotonesResumen.add(botonSiguienteResumen);
        panelBotonesResumen.add(botonAnteriorResumen);
        panelBotonesResumen.add(botonCopiarPaso);
        panelBotonesResumen.add(botonCopiarTodo);
        panelBotonesResumen.setBounds(300, 20, 100, 140);

        panelPestana.addTab("Matriz", pestanaMatriz);
        panelPestana.addTab("Resumen", pestanaResumen);
        
        pestanaMatriz.add(panelTabla);
        pestanaMatriz.add(panelRadios);
        pestanaMatriz.add(panelBotonesMatriz);
        
        pestanaResumen.add(scrollResumen);
        pestanaResumen.add(panelBotonesResumen);
        
        
        casillaSeleccionada = new Point();
        AbstractFraccion[][] fracciones = 
        {
                {new Fraccion(1),new Fraccion(2),new Fraccion(3),new Fraccion(3)},
                {new Fraccion(4),new Fraccion(5),new Fraccion(6),new Fraccion(3)},
                {new Fraccion(7),new Fraccion(8),new Fraccion(9),new Fraccion(3)},
                {new Fraccion(7),new Fraccion(8),new Fraccion(9),new Fraccion(3)},
        };
        String[] nombreColumnas = {"i", "BVS", "x1","x2", "x3","RHS"};
        String[] nombreFilas = {"z","s1","s2","s3"};
        AbstractFraccion[] listaRadios = 
                {new Fraccion(2),new Fraccion(3), new Fraccion(4)};
        Point p = new Point (0,2);
        DtoSimplex dto = new DtoSimplex(fracciones, nombreColumnas, nombreFilas, p, listaRadios);
        mostrarMatriz(dto);
    }
    
    public void mostrarMatriz(DtoSimplex dto){
        AbstractFraccion[][] matriz = dto.getMatriz();
        String[] nombresColumnas = dto.getNombreColumnas();
        String[] nombresFilas = dto.getNombreFilas();
        Point coordenada = dto.getCoordenadaPivote();
        AbstractFraccion[] listaRadios = dto.getListaRadios();
        this.matrizFracciones = dto.getMatriz();
        int cantFilas = matriz.length + 1;
        int cantColumnas = matriz[0].length + 2;
        
        panelTabla.setLayout(new GridLayout(cantFilas,cantColumnas));
        panelRadios.setLayout(new GridLayout(cantFilas, 1));
        
        this.setSize(450, 300);//debe ser dinamico
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
        int anchoTablaNumeros = ANCHO_CASILLA * cantColumnas;
        int altoTablaNumeros = ALTO_CASILLA * cantFilas;
        panelTabla.setBounds(POSICION_TABLA_X, POSICION_TABLA_Y, anchoTablaNumeros, altoTablaNumeros);
        int posicionRadiosX1 = POSICION_TABLA_X + anchoTablaNumeros + ESPACIO_TABLAS;
        int posicionRadiosY1 = POSICION_TABLA_Y;
        int anchoTablaRadios = ANCHO_CASILLA;
        int altoTablaRadios = altoTablaNumeros;
        panelRadios.setBounds(posicionRadiosX1, posicionRadiosY1, anchoTablaRadios, altoTablaRadios);
        panelRadios.setBackground(Color.white);
        panelBotonesMatriz.setBounds(POSICION_TABLA_X - 32, POSICION_TABLA_Y + altoTablaRadios, 200,40);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPestana, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPestana, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelPestana.getAccessibleContext().setAccessibleName("");
        panelPestana.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(PantallaPasoIntermedio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPasoIntermedio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPasoIntermedio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPasoIntermedio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PantallaPasoIntermedio frame = new PantallaPasoIntermedio();
                frame.setVisible(true);
            }
        });
    }

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
                matrizLabels[i][j] = label;
                panelTabla.add(label);
            }
        }
    }

    private void seleccionarCasilla(Point coordenada) {
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
                            matrizFracciones[coordenada.y][coordenada.x] = new Fraccion(Double.parseDouble(keyBuffer.toString()));
                            JOptionPane.showMessageDialog(null, "Valor actualizado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            keyBuffer.setLength(0);
                        }else if(esFraccion(keyBuffer.toString())){
                            String[] split = keyBuffer.toString().split("/");
                            matrizFracciones[coordenada.y][coordenada.x] = new Fraccion(Double.parseDouble(split[0]),Double.parseDouble(split[1]));
                            JOptionPane.showMessageDialog(null, "Valor actualizado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            keyBuffer.setLength(0);
                        } else if (esDecimal(keyBuffer.toString())){
                            matrizFracciones[coordenada.y][coordenada.x] = new Fraccion(Double.parseDouble(keyBuffer.toString()));
                            JOptionPane.showMessageDialog(null, "Valor actualizado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            keyBuffer.setLength(0);
                        } else{
                            JOptionPane.showMessageDialog(null, "El numero ingresado no tiene formato valido.", "Error", JOptionPane.ERROR_MESSAGE);
                            keyBuffer.setLength(0);
                        }
                } 
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
                return string.matches("-?[1-9][0-9]*");
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
        matrizLabels[fila][columna].setText(matrizFracciones[coordenada.y][coordenada.x].toString());
        keyBuffer.setLength(0);
        panelPestana.removeKeyListener(keyboardListener);
        panelPestana.validate();
        panelPestana.repaint();
    }
    
    private void rellenarMatriz(AbstractFraccion[][] matriz, String[] nombresColumnas, String[] nombresFilas){
        //rellenar la primera fila (nombres de variables)
        for (int i = 0; i < nombresColumnas.length ; i++) {
            matrizLabels[0][i].setText(nombresColumnas[i]);
        }
        //rellenar la primera columna (numero de restriccion)
        for (int i = 1; i < matriz.length+1; i++) {
            matrizLabels[i][0].setText(String.valueOf(i-1));
        }
        //rellenar la segunda columna (Variabls basicas)
        for (int i = 1; i <= nombresFilas.length ; i++) {
            matrizLabels[i][1].setText(nombresFilas[i-1]);
        }
        //rellenar los valores numericos
        for (int i = 1; i < matriz.length + 1; i++) {
            for (int j = 2; j < matriz[0].length + 2; j++) {
                final Point coordenada = new Point(j-2,i-1);
                AbstractFraccion fraccion = matriz[i-1][j-2];
                matrizLabels[i][j].setText(fraccion.toString());
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

    private void rellenarRadios(AbstractFraccion[] listaRadios) {
        for (int i = 0; i < listaRadios.length; i++) {
            matrizRadios[i].setText(listaRadios[i].toString());
        }
    }

    private void initRadios(int cantRestricciones) {
        panelRadios.removeAll();
        matrizRadios = new JLabel[cantRestricciones];
        JLabel label = new JLabel("Radios");
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        panelRadios.add(label);
        label = new JLabel("-");
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        panelRadios.add(label);
        for (int i = 0; i < cantRestricciones; i++) {
            label = new JLabel();
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            matrizRadios[i] = label;
            panelRadios.add(label);
        }
    }
}
