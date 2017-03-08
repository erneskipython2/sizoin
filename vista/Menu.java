package vista;

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import sizin.Box;
import sizin.ClaseObjetoParaComboBox;
import sizin.ConfiguracionGlobal;
import sizin.EjecutarReporte;
import sizin.JFrameConFondo;
import sizin.JLabelImagenAjusta;
import sizin.Reloj;
import sizin.ValidacionesVista;

/**
 *
 * @author Erneski Coronado
 */
public class Menu extends JFrameConFondo {

    private JLabelImagenAjusta logo;
    //private JLabelImagenAjusta imagen;
    private ConfiguracionGlobal config;
    private Reloj r;
    private JComboBox jComboBoxEstadobuscar;
    private Box comboestadobuscar;

    /**
     * Creates new form Menu
     */
    public Menu() {

        initComponents();
        jInternalFrameReportes.setVisible(false);
        setImagen("imagenes/fondo_con_fotos.jpg");//se pasa la imagen de fondo del formulario
        logo = new JLabelImagenAjusta();
        //imagen = new JLabelImagenAjusta();
        logo.ImagenJLabel(jLabelLogo, "imagenes/logo_sistema.png");
        //imagen.ImagenJLabel(jLabelImagen, "imagenes/imagen_menu.png");
        config = new ConfiguracionGlobal();
        jLabelUsuario.setText(config.usuario_login());
        r = new Reloj();
        jLabelHora.setText(r.Hora());
        jLabelLogo_des.setText(JLabelImagenAjusta.convertToMultiline("SISTEMA DE INFORMACIÓN PARA EL REGISTRO Y ANALISIS\n"
                + "DE MUESTRAS ZOOPATÓGENAS DE LA RED DE \n"
                + "LABORATORIOS DE DIAGNÓSTICO ZOOSANITARIO DEL INSAI\n"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPanelMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButtonMuestras = new javax.swing.JButton();
        jButtonEpidemiologia = new javax.swing.JButton();
        jButtonInformes = new javax.swing.JButton();
        jButtonConfiguracion = new javax.swing.JButton();
        jButtonAyuda = new javax.swing.JButton();
        jPanelMembrete = new javax.swing.JPanel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelLogo_des = new javax.swing.JLabel();
        jLabelHora = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLayeredPaneMenu = new javax.swing.JLayeredPane();
        jInternalFrameReportes = new javax.swing.JInternalFrame();
        jPanelOpcionMenu = new javax.swing.JPanel();
        jButtonInforme = new javax.swing.JButton();
        jButtonInformeEpi = new javax.swing.JButton();
        jButtonInformePredios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIZOIN - Menu Principal");
        setBackground(new java.awt.Color(255, 255, 255));
        setExtendedState(6);

        jPanelMenu.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMenu.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setOpaque(false);

        jButtonMuestras.setBackground(new java.awt.Color(255, 255, 255));
        jButtonMuestras.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonMuestras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/microscopio.png"))); // NOI18N
        jButtonMuestras.setText("Registro y Analisis de Muestras");
        jButtonMuestras.setToolTipText("Registro y Análisis de Muestras Zoopatógenas");
        jButtonMuestras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonMuestras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMuestrasActionPerformed(evt);
            }
        });

        jButtonEpidemiologia.setBackground(new java.awt.Color(255, 255, 255));
        jButtonEpidemiologia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonEpidemiologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/epide.png"))); // NOI18N
        jButtonEpidemiologia.setText("Epidemiología");
        jButtonEpidemiologia.setToolTipText("Alertas Epidemiológicas Detectadas y Creadas");
        jButtonEpidemiologia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonEpidemiologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEpidemiologiaActionPerformed(evt);
            }
        });

        jButtonInformes.setBackground(new java.awt.Color(255, 255, 255));
        jButtonInformes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/archivo_lleno32.png"))); // NOI18N
        jButtonInformes.setText("Informes y Reportes");
        jButtonInformes.setToolTipText("Informe mensual, semanal, reportes generales");
        jButtonInformes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInformesActionPerformed(evt);
            }
        });

        jButtonConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
        jButtonConfiguracion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/config32.png"))); // NOI18N
        jButtonConfiguracion.setText("Configuración del Sistema");
        jButtonConfiguracion.setToolTipText("Usuarios, Personal y Sedes");
        jButtonConfiguracion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfiguracionActionPerformed(evt);
            }
        });

        jButtonAyuda.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAyuda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/comentario32.png"))); // NOI18N
        jButtonAyuda.setText("Ayuda y Soporte al Usuario");
        jButtonAyuda.setToolTipText("Acerca de, Soporte, Contenido de Ayuda");
        jButtonAyuda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConfiguracion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonInformes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEpidemiologia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMuestras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEpidemiologia, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInformes, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelMembrete.setBackground(new java.awt.Color(204, 255, 255));
        jPanelMembrete.setOpaque(false);

        jLabelLogo.setText("jLabel1");

        jLabelUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabelLogo_des.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabelLogo_des.setForeground(new java.awt.Color(255, 102, 102));
        jLabelLogo_des.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelHora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanelMembreteLayout = new javax.swing.GroupLayout(jPanelMembrete);
        jPanelMembrete.setLayout(jPanelMembreteLayout);
        jPanelMembreteLayout.setHorizontalGroup(
            jPanelMembreteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMembreteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelLogo_des, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                .addGroup(jPanelMembreteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelMembreteLayout.setVerticalGroup(
            jPanelMembreteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMembreteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMembreteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMembreteLayout.createSequentialGroup()
                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMembreteLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabelLogo_des, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jInternalFrameReportes.setBackground(new java.awt.Color(255, 255, 255));
        jInternalFrameReportes.setClosable(true);
        jInternalFrameReportes.setTitle("Reportes Globales");
        jInternalFrameReportes.setToolTipText("");
        jInternalFrameReportes.setMaximumSize(new java.awt.Dimension(315, 319));
        jInternalFrameReportes.setMinimumSize(new java.awt.Dimension(315, 319));
        jInternalFrameReportes.setVisible(true);

        jPanelOpcionMenu.setBackground(new java.awt.Color(238, 242, 254));
        jPanelOpcionMenu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione una opción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 51, 255)));

        jButtonInforme.setBackground(new java.awt.Color(255, 255, 255));
        jButtonInforme.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButtonInforme.setForeground(new java.awt.Color(153, 153, 255));
        jButtonInforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/archivo_lleno32.png"))); // NOI18N
        jButtonInforme.setText("Informe Mensual");
        jButtonInforme.setToolTipText("Reporte Global de Solicitudes y Resultados por Sede");
        jButtonInforme.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInformeActionPerformed(evt);
            }
        });

        jButtonInformeEpi.setBackground(new java.awt.Color(255, 255, 255));
        jButtonInformeEpi.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButtonInformeEpi.setForeground(new java.awt.Color(255, 153, 153));
        jButtonInformeEpi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/archivo_lleno32.png"))); // NOI18N
        jButtonInformeEpi.setText("Reporte Epidemiológico");
        jButtonInformeEpi.setToolTipText("Reporte de Análisis Positivos para Patógenos");
        jButtonInformeEpi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonInformeEpi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInformeEpiActionPerformed(evt);
            }
        });

        jButtonInformePredios.setBackground(new java.awt.Color(255, 255, 255));
        jButtonInformePredios.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButtonInformePredios.setForeground(new java.awt.Color(204, 153, 0));
        jButtonInformePredios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/archivo_lleno32.png"))); // NOI18N
        jButtonInformePredios.setText("Predios y Propietarios");
        jButtonInformePredios.setToolTipText("Reporte de Predios y Propietarios Registrados");
        jButtonInformePredios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonInformePredios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInformePrediosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOpcionMenuLayout = new javax.swing.GroupLayout(jPanelOpcionMenu);
        jPanelOpcionMenu.setLayout(jPanelOpcionMenuLayout);
        jPanelOpcionMenuLayout.setHorizontalGroup(
            jPanelOpcionMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpcionMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOpcionMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonInformeEpi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonInformePredios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOpcionMenuLayout.setVerticalGroup(
            jPanelOpcionMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpcionMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInformeEpi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInformePredios, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jInternalFrameReportesLayout = new javax.swing.GroupLayout(jInternalFrameReportes.getContentPane());
        jInternalFrameReportes.getContentPane().setLayout(jInternalFrameReportesLayout);
        jInternalFrameReportesLayout.setHorizontalGroup(
            jInternalFrameReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelOpcionMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameReportesLayout.setVerticalGroup(
            jInternalFrameReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelOpcionMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jLayeredPaneMenuLayout = new javax.swing.GroupLayout(jLayeredPaneMenu);
        jLayeredPaneMenu.setLayout(jLayeredPaneMenuLayout);
        jLayeredPaneMenuLayout.setHorizontalGroup(
            jLayeredPaneMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrameReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPaneMenuLayout.setVerticalGroup(
            jLayeredPaneMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneMenuLayout.createSequentialGroup()
                .addComponent(jInternalFrameReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 156, Short.MAX_VALUE))
        );
        jLayeredPaneMenu.setLayer(jInternalFrameReportes, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMembrete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPaneMenu)
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addComponent(jPanelMembrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPaneMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEpidemiologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEpidemiologiaActionPerformed
        if ((config.getPrivilegio() == 1)|| (config.getPrivilegio() == 2)) {
            Epidemiologia ventana = new Epidemiologia();
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jButtonEpidemiologiaActionPerformed

    private void jButtonConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfiguracionActionPerformed
        if (config.getPrivilegio() == 1) {
            Configuracion ventana = new Configuracion();
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jButtonConfiguracionActionPerformed

    private void jButtonMuestrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMuestrasActionPerformed

        RegistroGeneral ventana = new RegistroGeneral();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

    }//GEN-LAST:event_jButtonMuestrasActionPerformed

    private void jButtonInformeEpiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInformeEpiActionPerformed
        this.jComboBoxEstadobuscar = new JComboBox();
        this.comboestadobuscar = new Box();
        this.jComboBoxEstadobuscar.setModel(comboestadobuscar.modelo);
        comboestadobuscar.consultabox2(config.box_consulta_estado, "id_estado", "estado");
        comboestadobuscar.LLenarBoxConID();

        JOptionPane.showMessageDialog(null, jComboBoxEstadobuscar, "Seleccione el Estado", JOptionPane.QUESTION_MESSAGE);
        ClaseObjetoParaComboBox EstadoBuscar = (ClaseObjetoParaComboBox) jComboBoxEstadobuscar.getSelectedItem();//variable para extraer el id del estado seleccionado
        String estadobus = EstadoBuscar.getNombre();
        if (estadobus.isEmpty() == false) {
            EjecutarReporte pre = new EjecutarReporte();
            pre.startReportString("estado", estadobus, "informe_epidemiologico");
        } else {
            JOptionPane.showMessageDialog(jLayeredPaneMenu, "Debe seleccionar un estado primero");
        }
    }//GEN-LAST:event_jButtonInformeEpiActionPerformed

    private void jButtonInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInformesActionPerformed
        jInternalFrameReportes.setVisible(true);
        jInternalFrameReportes.toFront();
    }//GEN-LAST:event_jButtonInformesActionPerformed

    private void jButtonInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInformeActionPerformed
        String fecha_inicial = JOptionPane.showInputDialog(jLayeredPaneMenu, "Fecha Inicial:", "Ingrese la fecha en formato aaaa-mm-dd", JOptionPane.QUESTION_MESSAGE);
        String fecha_final = JOptionPane.showInputDialog(jLayeredPaneMenu, "Fecha Final:", "Ingrese la fecha en formato aaaa-mm-dd", JOptionPane.QUESTION_MESSAGE);
        ValidacionesVista em = new ValidacionesVista();
        if (em.isFecha(fecha_inicial) && em.isFecha(fecha_final)) {
            EjecutarReporte inf = new EjecutarReporte();
            inf.startReportMensual("sede", config.getSede_seleccionada(), "fecha_inicio", fecha_inicial, "fecha_fin", fecha_final, "informe_mensual");
        } else {
            JOptionPane.showMessageDialog(jLayeredPaneMenu, "Ingrese las fechas con el formato indicado");
        }


    }//GEN-LAST:event_jButtonInformeActionPerformed

    private void jButtonInformePrediosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInformePrediosActionPerformed
        this.jComboBoxEstadobuscar = new JComboBox();
        this.comboestadobuscar = new Box();
        this.jComboBoxEstadobuscar.setModel(comboestadobuscar.modelo);
        comboestadobuscar.consultabox2(config.box_consulta_estado, "id_estado", "estado");
        comboestadobuscar.LLenarBoxConID();

        JOptionPane.showMessageDialog(null, jComboBoxEstadobuscar, "Seleccione el Estado", JOptionPane.QUESTION_MESSAGE);
        ClaseObjetoParaComboBox EstadoBuscar = (ClaseObjetoParaComboBox) jComboBoxEstadobuscar.getSelectedItem();//variable para extraer el id del estado seleccionado
        String estadobus = EstadoBuscar.getNombre();
        if (estadobus.isEmpty() == false) {
            EjecutarReporte pre = new EjecutarReporte();
            pre.startReportString("estado", estadobus, "predios");
        } else {
            JOptionPane.showMessageDialog(jLayeredPaneMenu, "Debe seleccionar un estado primero");
        }

    }//GEN-LAST:event_jButtonInformePrediosActionPerformed

    private void jButtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAyudaActionPerformed
        Ayuda ventana= new Ayuda();
        ventana.setVisible(true);
        ventana.toFront();
    }//GEN-LAST:event_jButtonAyudaActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAyuda;
    private javax.swing.JButton jButtonConfiguracion;
    private javax.swing.JButton jButtonEpidemiologia;
    private javax.swing.JButton jButtonInforme;
    private javax.swing.JButton jButtonInformeEpi;
    private javax.swing.JButton jButtonInformePredios;
    private javax.swing.JButton jButtonInformes;
    private javax.swing.JButton jButtonMuestras;
    private javax.swing.JInternalFrame jInternalFrameReportes;
    private javax.swing.JLabel jLabelHora;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelLogo_des;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JLayeredPane jLayeredPaneMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMembrete;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelOpcionMenu;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
