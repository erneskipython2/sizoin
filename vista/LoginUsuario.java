package vista;

import controlador.CoordinadorUsuario;
import java.awt.Color;
import javax.swing.JOptionPane;
import logica.LogicaUsuario;
import modeloBO.UsuarioBO;
import sizin.JFrameConFondo;
import sizin.Sizin;
import sizin.JLabelImagenAjusta;
import sizin.JTextFieldLimit;

/**
 *
 * @author Erneski Coronado
 */
public class LoginUsuario extends JFrameConFondo {

    private CoordinadorUsuario miCoordinador;
    private LogicaUsuario miLogica;
    private LoginUsuario miVentana_login;
    private JLabelImagenAjusta logo;

    /**
     * Creates new form Login_usuario
     */
    @SuppressWarnings("static-access")
    public LoginUsuario() {
        initComponents();
        logo = new JLabelImagenAjusta();
        logo.ImagenJLabel(lblLogo, "imagenes/logo_sistema.png");
        jLabelLogo_des.setText(logo.convertToMultiline("SISTEMA DE INFORMACIÓN PARA EL REGISTRO Y ANALISIS\n"
                + "DE MUESTRAS ZOOPATÓGENAS DE LA RED DE \n"
                + "LABORATORIOS DE DIAGNÓSTICO ZOOSANITARIO DEL INSAI\n"));
        txtusuario.setDocument(new JTextFieldLimit(15, false));
        txtclave.setDocument(new JTextFieldLimit(15, false));

    }

    private void iniciar() {
        /*Se instancian las clases*/
        miVentana_login = new LoginUsuario();

        miLogica = new LogicaUsuario();
        miCoordinador = new CoordinadorUsuario();

        /*Se establecen las relaciones entre clases*/
        miVentana_login.setCoordinador(miCoordinador);
        miLogica.setCoordinador(miCoordinador);

        /*Se establecen relaciones con la clase coordinador*/
        miCoordinador.setMiLogin_usuario(miVentana_login);
        miCoordinador.setMiLogica(miLogica);
        miVentana_login.setLocationRelativeTo(null);
        miVentana_login.setVisible(true);
    }

    public void setCoordinador(CoordinadorUsuario miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLogin = new javax.swing.JPanel();
        jPanelIngresar = new javax.swing.JPanel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        txtusuario = new javax.swing.JTextField();
        txtclave = new javax.swing.JPasswordField();
        lblusuario = new javax.swing.JLabel();
        lblpassword = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jLabelLogo_des = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIZOIN - Ingreso al Sistema");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanelLogin.setBackground(new java.awt.Color(255, 255, 255));

        jPanelIngresar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso al Sistema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 12))); // NOI18N
        jPanelIngresar.setOpaque(false);

        btnaceptar.setBackground(new java.awt.Color(204, 255, 255));
        btnaceptar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aceptar32.png"))); // NOI18N
        btnaceptar.setText("Aceptar");
        btnaceptar.setToolTipText("Validar usuario y clave");
        btnaceptar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnaceptar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        btncancelar.setBackground(new java.awt.Color(255, 204, 204));
        btncancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar32.png"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setToolTipText("Limpiar usuario y clave");
        btncancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btncancelar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        lblusuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblusuario.setText("Usuario:");

        lblpassword.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblpassword.setText("Clave:");

        javax.swing.GroupLayout jPanelIngresarLayout = new javax.swing.GroupLayout(jPanelIngresar);
        jPanelIngresar.setLayout(jPanelIngresarLayout);
        jPanelIngresarLayout.setHorizontalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblusuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(lblpassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtusuario)
                            .addComponent(txtclave)))
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addComponent(btnaceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(btncancelar)))
                .addContainerGap())
        );
        jPanelIngresarLayout.setVerticalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblusuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblpassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnaceptar)
                    .addComponent(btncancelar))
                .addContainerGap())
        );

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelLogo_des.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        jLabelLogo_des.setForeground(new java.awt.Color(255, 102, 102));
        jLabelLogo_des.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLogo_des, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                    .addComponent(jPanelIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLogo_des, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanelIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        UsuarioBO miUsuario = miCoordinador.loginUsuario(txtusuario.getText(), txtclave.getText());
        if (miUsuario != null) {
            this.setVisible(false);
            Menu ventana = new Menu();
            ventana.setVisible(true);

            //llamar la ventana de menu desde el coordinador
            JOptionPane.showMessageDialog(null, "Bienvenido Usuario: " + miUsuario.getUsuario(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        //Limpiar los textbox
        txtusuario.setText(null);
        txtclave.setText(null);

    }//GEN-LAST:event_btncancelarActionPerformed

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
            java.util.logging.Logger.getLogger(LoginUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        LoginUsuario miPrincipal = new LoginUsuario();
        miPrincipal.iniciar();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JLabel jLabelLogo_des;
    private javax.swing.JPanel jPanelIngresar;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JPasswordField txtclave;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
