
import javax.swing.*;

public class menu extends javax.swing.JFrame {

    ArbolBinario objArbol;

    public menu() {
        initComponents();
        objArbol = new ArbolBinario();
        consultar.setEnabled(false);
        buscar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        insertar = new javax.swing.JButton();
        consultar = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setText("Arboles");

        insertar.setText("Insertar");
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });

        consultar.setText("consultar");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });

        buscar.setText("buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(title))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(consultar)
                            .addComponent(insertar)
                            .addComponent(buscar)
                            .addComponent(limpiar))))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(insertar)
                .addGap(18, 18, 18)
                .addComponent(consultar)
                .addGap(18, 18, 18)
                .addComponent(buscar)
                .addGap(18, 18, 18)
                .addComponent(limpiar)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActionPerformed
         try {
        String input = JOptionPane.showInputDialog(null, "Ingrese el nuevo dato");
        if (input == null || input.equals("")) {
           JOptionPane.showMessageDialog(null, "No ingresaste nada");
           return;
        }
        
         int dato = Integer.parseInt(input);
            objArbol.agregarDato(dato);
            objArbol.actualizarVista();
            objArbol.mostrarGrafico();

            consultar.setEnabled(true);
            buscar.setEnabled(true);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número entero válido.");
    }
    }//GEN-LAST:event_insertarActionPerformed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Maneras de recorrer\n"
                + "1.INORDER\n" + "2.POSORDEN\n" + "3.PREORDEN"));
        switch (opcion) {
            case 1:
                objArbol.consultarInorden(objArbol.raiz);
                break;
            case 2:
                objArbol.consultarPosorden(objArbol.raiz);
                break;
            case 3:
                objArbol.consultarPreorden(objArbol.raiz);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Seleccionaste una opción no válida");
                break;
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        int buscar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el dato a buscar"));
        if (objArbol.buscarDato(buscar) == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el dato");
        } else {
            JOptionPane.showMessageDialog(null, "El dato " + objArbol.buscarDato(buscar).getDato() + " ha sido encontrado");
            if (objArbol.buscarDato(buscar).getHijoIzquierdo() == null && objArbol.buscarDato(buscar).getHijoDerecho() == null) {
                JOptionPane.showMessageDialog(null, "Este nodo no tiene hijos");
            } else {
                JOptionPane.showMessageDialog(null, "Este nodo sí tiene hijos");
            }
            JOptionPane.showMessageDialog(null, "El nodo se encuentra en el nivel: " + objArbol.obtenerNivelDeUnNodo(objArbol.raiz, buscar, 0));

            objArbol.suPadre(objArbol.raiz, buscar);
            if (objArbol.suPadre(objArbol.raiz, buscar) != null) {
                JOptionPane.showMessageDialog(null, "El dato padre del nodo es: " + objArbol.suPadre(objArbol.raiz, buscar).getDato());
            } else {
                JOptionPane.showMessageDialog(null, "No tiene padre");
            }
        }
    }//GEN-LAST:event_buscarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
           limpiarYCerrarVentana();


    }//GEN-LAST:event_limpiarActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }
    
       private void limpiarYCerrarVentana() {
        objArbol.cerrarVentana();
        objArbol = new ArbolBinario();
        consultar.setEnabled(false);
        buscar.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JButton consultar;
    private javax.swing.JButton insertar;
    private javax.swing.JButton limpiar;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
