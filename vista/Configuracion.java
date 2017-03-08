package vista;

import controlador.CoordinadorPersonal;
import controlador.CoordinadorSede;
import controlador.CoordinadorUsuario;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import logica.LogicaPersonal;
import logica.LogicaSede;
import logica.LogicaUsuario;
import modeloBO.PersonalBO;
import modeloBO.SedeBO;
import modeloBO.UsuarioBO;
import sizin.ClaseObjetoParaComboBox;
import sizin.Box;
import sizin.ConfiguracionGlobal;
import sizin.EjecutarReporte;
import sizin.FondoMenuBar;
import sizin.JFrameConFondo;
import sizin.JTextFieldLimit;
import sizin.Tabla;
import sizin.ValidacionesVista;

/**
 *
 * @author Erneski Coronado
 */
public class Configuracion extends JFrameConFondo {

    Box box_tipo_us = new Box();
    Box box_asignado = new Box();
    Box box_responsable = new Box();
    Box box_sede = new Box();
    Box box_estado = new Box();
    Box box_parroquia = new Box();
    Box box_municipio = new Box();
    Tabla tab_usuarios = new Tabla();
    Tabla tab_personal = new Tabla();
    Tabla tab_sede = new Tabla();
    ValidacionesVista em = new ValidacionesVista();
    ConfiguracionGlobal config = new ConfiguracionGlobal();
    private CoordinadorUsuario miCoordinador;
    private CoordinadorPersonal miCoordinadorPer;
    private CoordinadorSede miCoordinadorSed;
    private LogicaUsuario miLogica;
    private LogicaPersonal miLogicaPer;
    private LogicaSede miLogicaSed;
    private Configuracion miVentanaConfiguracion;
    private Integer id_usuario_modificar = null;//almacena el usuario seleccionado para modificar o eliminar
    private Integer id_personal_modificar = null;
    private Integer id_sede_modificar = null;

    /**
     * Creates new form Configuracion
     */
    public Configuracion() {
        initComponents();

        setImagen("imagenes/fondo_difuminado.jpg"); //lanza la imagen de fondo
        jFrameUs.dispose();
        jFramePer.dispose();
        jFrameSede.dispose();
        //declaracion y usos de modelos para jComboBox y jTable
        box_tipo_us.consultabox2(config.box_consulta_tipo_usuario, "id_usuario_tipo", "usuario_tipo");
        box_tipo_us.LLenarBoxConID();
        box_tipo_mod.setModel(box_tipo_us.getModelo());
        box_asignado.consultabox2(config.box_consulta_personal_cedula(), "id_personal", "cedula");
        box_asignado.LLenarBoxConID();
        box_responsable.consultabox2(config.box_consulta_personal_cedula(), "id_personal", "cedula");
        box_responsable.LLenarBoxConID();
        box_asignado_mod.setModel(box_asignado.getModelo());
        box_asignado_mod.setToolTipText("Asignar una persona registrada al usuario");
        box_responsable_mod.setModel(box_responsable.getModelo());
        box_responsable_mod.setToolTipText("Asignar el responsable de la sede");
        box_sede.consultabox2(config.box_consulta_sede, "id_sedes_insai", "sede");
        box_sede.LLenarBoxConID();
        box_estado.consultabox2(config.box_consulta_estado, "id_estado", "estado");
        box_estado.LLenarBoxConID();
        jComboBoxEstado.setModel(box_estado.getModelo());
        jComboBoxMunicipio.setModel(box_municipio.getModelo());
        jComboBoxParroquia.setModel(box_parroquia.getModelo());
        JComboBoxSede.setModel(box_sede.getModelo());
        tab_usuarios.columnastabla(6, config.tabla_consulta_usuarios());
        tab_usuarios.crearmodelousuario();
        tab_personal.columnastabla(8, config.tabla_consulta_personal());
        tab_personal.crearmodelopersonal();
        tab_sede.columnastabla(10, config.tabla_consulta_sede);
        tab_sede.crearmodelosede();
        jTableUsuarios.setModel(tab_usuarios.getModelo());
        jTablePersonal.setModel(tab_personal.getModelo());
        jTableSede.setModel(tab_sede.getModelo());
        jTableUsuarios.setToolTipText("Seleccione un usuario para modificar o eliminar");
        jTablePersonal.setToolTipText("Seleccione un personal para modificar o eliminar");
        jTableSede.setToolTipText("Seleccione una sede para modificar o eliminar");
        //validaciones de jTextField
        txt_usuario_mod.setDocument(new JTextFieldLimit(15, false));
        txt_clave_mod.setDocument(new JTextFieldLimit(15, false));
        JTextFieldCedula.setDocument(new JTextFieldLimit(11, false));
        JTextFieldNombres.setDocument(new JTextFieldLimit(80, false));

        JTextFieldTelefono.setDocument(new JTextFieldLimit(11, false));
        JTextFieldAcreditacion.setDocument(new JTextFieldLimit(15, false));
        JTextFieldCarnet.setDocument(new JTextFieldLimit(15, false));
        JTextFieldSede.setDocument(new JTextFieldLimit(100, false));
        JTextFieldNum.setDocument(new JTextFieldLimit(5, false));
        JTextFieldTelefonoSede.setDocument(new JTextFieldLimit(11, false));
        JTextFieldEmail.setDocument(new JTextFieldLimit(35, false));
        JTextFieldDireccion.setDocument(new JTextFieldLimit(250, false));

        //evento para la carga dinamica de los jComboBox de municipio y parroquia
        jComboBoxEstado.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    ClaseObjetoParaComboBox sede = (ClaseObjetoParaComboBox) item;
                    int id_estado = sede.getId();
                    box_municipio.consultabox2(config.box_consulta_municipio(id_estado), "id_municipio", "municipio");//cargamos la consulta para cargar el municipio
                    box_municipio.LLenarBoxConID();//llenamos el combo de municipio                
                }
            }
        });

        jComboBoxMunicipio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    ClaseObjetoParaComboBox sede = (ClaseObjetoParaComboBox) item;
                    int id_municipio = sede.getId();
                    box_parroquia.consultabox2(config.box_consulta_parroquia(id_municipio), "id_parroquia", "parroquia");//cargamos la consulta para cargar la parroquia
                    box_parroquia.LLenarBoxConID();//llenamos el combo de municipio                
                }
            }
        });

    }

    //metodo para iniciar la ventana
    private void iniciar() {
        /*Se instancian las clases*/
        miVentanaConfiguracion = new Configuracion();
        miVentanaConfiguracion.setVisible(true);
    }

    //metodo para llamar al coordinador de usuario
    private void iniciarUsuario() {
        /*Se instancian las clases*/
        miLogica = new LogicaUsuario();
        miCoordinador = new CoordinadorUsuario();
        /*Se establecen las relaciones entre clases*/
        this.setCoordinadorUsuario(miCoordinador);
        miLogica.setCoordinador(miCoordinador);
        /*Se establecen relaciones con la clase coordinador*/
        miCoordinador.setMiVentanaConfiguracion(miVentanaConfiguracion);
        miCoordinador.setMiLogica(miLogica);
    }

    //metodo para llamar al coordinador de personal
    private void iniciarPersonal() {
        /*Se instancian las clases*/
        miLogicaPer = new LogicaPersonal();
        miCoordinadorPer = new CoordinadorPersonal();
        /*Se establecen las relaciones entre clases*/
        this.setCoordinadorPersonal(miCoordinadorPer);
        miLogicaPer.setCoordinador(miCoordinadorPer);
        miCoordinadorPer.setMiVentanaConfiguracion(miVentanaConfiguracion);
        miCoordinadorPer.setMiLogica(miLogicaPer);
    }

    //metodo para llamar al coordinador de sede
    private void iniciarSede() {
        /*Se instancian las clases*/
        miLogicaSed = new LogicaSede();
        miCoordinadorSed = new CoordinadorSede();
        /*Se establecen las relaciones entre clases*/
        this.setCoordinadorSede(miCoordinadorSed);
        miLogicaSed.setCoordinador(miCoordinadorSed);
        miCoordinadorSed.setMiVentanaConfiguracion(miVentanaConfiguracion);
        miCoordinadorSed.setMiLogica(miLogicaSed);
    }

    //limpiar campos de usuario    
    public void limpiar_usuario() {
        txt_usuario_mod.setText(null);
        txt_clave_mod.setText(null);
        box_tipo_us.setjComboBox(box_tipo_mod, "--Seleccione--");
        box_asignado.setjComboBox(box_asignado_mod, "--Seleccione--");
        id_usuario_modificar = null;
    }

    //limpiar campos del personal
    public void limpiar_personal() {
        JTextFieldCedula.setText(null);
        JTextFieldNombres.setText(null);
        JTextFieldTelefono.setText(null);
        JTextFieldAcreditacion.setText(null);
        JTextFieldCarnet.setText(null);
        box_sede.setjComboBox(JComboBoxSede, "--Seleccione--");
        id_personal_modificar = null;
    }

    //limpiar campos de sede
    public void limpiar_sede() {
        JTextFieldSede.setText(null);
        JTextFieldNum.setText(null);
        JTextFieldTelefonoSede.setText(null);
        JTextFieldEmail.setText(null);
        JTextFieldDireccion.setText(null);
        box_estado.setjComboBox(jComboBoxEstado, "--Seleccione--");
        id_sede_modificar = null;
    }

    public void setCoordinadorSede(CoordinadorSede miCoordinador) {
        this.miCoordinadorSed = miCoordinador;
    }

    public void setCoordinadorPersonal(CoordinadorPersonal miCoordinador) {
        this.miCoordinadorPer = miCoordinador;
    }

    public void setCoordinadorUsuario(CoordinadorUsuario miCoordinador) {
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

        jPanelConfiguracion = new javax.swing.JPanel();
        jFrameUs = new javax.swing.JInternalFrame();
        jPanelus = new javax.swing.JPanel();
        jPanelus1 = new javax.swing.JPanel();
        btn_cancelar_us = new javax.swing.JButton();
        txt_clave_mod = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        box_asignado_mod = new javax.swing.JComboBox();
        btn_agregar_us = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_usuario_mod = new javax.swing.JTextField();
        btn_eliminar_us = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        box_tipo_mod = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        btn_modificar_us = new javax.swing.JButton();
        jPanelus3 = new javax.swing.JPanel();
        jPanelus2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jFramePer = new javax.swing.JInternalFrame();
        jPanelPer = new javax.swing.JPanel();
        jPanelPer2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePersonal = new javax.swing.JTable();
        jPanelPer1 = new javax.swing.JPanel();
        JButtonCancelarPer = new javax.swing.JButton();
        JTextFieldNombres = new javax.swing.JTextField();
        JButtonAgregarPer = new javax.swing.JButton();
        jLabelNombres = new javax.swing.JLabel();
        JTextFieldCedula = new javax.swing.JTextField();
        JButtonEliminarPer = new javax.swing.JButton();
        jLabelCedula = new javax.swing.JLabel();
        JComboBoxSede = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        JButtonModificarPer = new javax.swing.JButton();
        JTextFieldTelefono = new javax.swing.JTextField();
        jLabelTelefono = new javax.swing.JLabel();
        jLabelAcreditacion = new javax.swing.JLabel();
        JTextFieldAcreditacion = new javax.swing.JTextField();
        JTextFieldCarnet = new javax.swing.JTextField();
        jLabelCarnet = new javax.swing.JLabel();
        JTextFieldApellidos = new javax.swing.JTextField();
        jLabelNombres1 = new javax.swing.JLabel();
        jFrameSede = new javax.swing.JInternalFrame();
        jPanelSede2 = new javax.swing.JPanel();
        jPanelSede4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableSede = new javax.swing.JTable();
        jPanelSede3 = new javax.swing.JPanel();
        JButtonCancelarSede = new javax.swing.JButton();
        JTextFieldNum = new javax.swing.JTextField();
        JButtonAgregarSede = new javax.swing.JButton();
        jLabelNum = new javax.swing.JLabel();
        JTextFieldSede = new javax.swing.JTextField();
        JButtonEliminarSede = new javax.swing.JButton();
        jLabelSede = new javax.swing.JLabel();
        JButtonModificarSede = new javax.swing.JButton();
        JTextFieldTelefonoSede = new javax.swing.JTextField();
        jLabelTelefonoSede = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        JTextFieldEmail = new javax.swing.JTextField();
        jLabelEstado = new javax.swing.JLabel();
        jLabelMunicipio = new javax.swing.JLabel();
        jLabelParroquia = new javax.swing.JLabel();
        jLabelParroquia1 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox();
        jComboBoxMunicipio = new javax.swing.JComboBox();
        jComboBoxParroquia = new javax.swing.JComboBox();
        JTextFieldDireccion = new javax.swing.JTextField();
        box_responsable_mod = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jMenuBarConfig = new javax.swing.JMenuBar();
        jMenuUsuario = new javax.swing.JMenu();
        jMenuAdministrarUs = new javax.swing.JMenu();
        jMenuItemAgregarUs = new javax.swing.JMenuItem();
        jMenuItemModificarUs = new javax.swing.JMenuItem();
        jMenuItemImprimirUs = new javax.swing.JMenuItem();
        jMenuPersonal = new javax.swing.JMenu();
        jMenuAdministrarPer = new javax.swing.JMenu();
        jMenuItemAgregarPer = new javax.swing.JMenuItem();
        jMenuItemModificarPer = new javax.swing.JMenuItem();
        jMenuItemImprimirPer = new javax.swing.JMenuItem();
        jMenuSede = new javax.swing.JMenu();
        jMenuAdministrarSede = new javax.swing.JMenu();
        jMenuItemAgregarSede = new javax.swing.JMenuItem();
        jMenuItemModificarSede = new javax.swing.JMenuItem();
        jMenuItemImprimirSede = new javax.swing.JMenuItem();
        jMenuAuditoria = new javax.swing.JMenu();
        jMenuItemImprimirSes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SIZOIN - Configuracion");
        setResizable(false);

        jPanelConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
        jPanelConfiguracion.setOpaque(false);

        jFrameUs.setBackground(new java.awt.Color(255, 255, 255));
        jFrameUs.setClosable(true);
        jFrameUs.setTitle("Administrar Usuarios");
        jFrameUs.setInheritsPopupMenu(true);
        jFrameUs.setMaximumSize(new java.awt.Dimension(336, 335));
        jFrameUs.setVisible(true);

        jPanelus.setBackground(new java.awt.Color(255, 255, 255));

        jPanelus1.setBackground(new java.awt.Color(255, 255, 255));

        btn_cancelar_us.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_us.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_cancelar_us.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar16.png"))); // NOI18N
        btn_cancelar_us.setToolTipText("Cancelar operación, limpiar los campos");
        btn_cancelar_us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_usActionPerformed(evt);
            }
        });

        txt_clave_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 255));
        jLabel28.setText("Asignar:");

        box_asignado_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        box_asignado_mod.setToolTipText("");

        btn_agregar_us.setBackground(new java.awt.Color(255, 255, 255));
        btn_agregar_us.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_agregar_us.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aceptar16.png"))); // NOI18N
        btn_agregar_us.setToolTipText("Agregar registro");
        btn_agregar_us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_usActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 255));
        jLabel9.setText("Clave:");

        txt_usuario_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_usuario_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuario_modActionPerformed(evt);
            }
        });

        btn_eliminar_us.setBackground(new java.awt.Color(255, 255, 255));
        btn_eliminar_us.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_eliminar_us.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/papelera16.png"))); // NOI18N
        btn_eliminar_us.setToolTipText("Eliminar el registro seleccionado");
        btn_eliminar_us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_usActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("Usuario:");

        box_tipo_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 255));
        jLabel10.setText("Tipo:");

        btn_modificar_us.setBackground(new java.awt.Color(255, 255, 255));
        btn_modificar_us.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_modificar_us.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar16.png"))); // NOI18N
        btn_modificar_us.setToolTipText("Modificar el registro seleccionado");
        btn_modificar_us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificar_usActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelus1Layout = new javax.swing.GroupLayout(jPanelus1);
        jPanelus1.setLayout(jPanelus1Layout);
        jPanelus1Layout.setHorizontalGroup(
            jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelus1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_clave_mod)
                    .addComponent(txt_usuario_mod, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(box_asignado_mod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(box_tipo_mod, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_cancelar_us, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_eliminar_us, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_agregar_us))
                    .addComponent(btn_modificar_us, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanelus1Layout.setVerticalGroup(
            jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelus1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelus1Layout.createSequentialGroup()
                        .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_usuario_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanelus1Layout.createSequentialGroup()
                        .addComponent(btn_agregar_us)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelus1Layout.createSequentialGroup()
                        .addComponent(btn_modificar_us)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar_us)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cancelar_us))
                    .addGroup(jPanelus1Layout.createSequentialGroup()
                        .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_clave_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(box_tipo_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanelus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(box_asignado_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanelus3.setBackground(new java.awt.Color(255, 255, 255));

        jPanelus2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios Existentes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanelus2.setOpaque(false);

        jTableUsuarios.setToolTipText("");
        jTableUsuarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsuarios);

        javax.swing.GroupLayout jPanelus2Layout = new javax.swing.GroupLayout(jPanelus2);
        jPanelus2.setLayout(jPanelus2Layout);
        jPanelus2Layout.setHorizontalGroup(
            jPanelus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelus2Layout.setVerticalGroup(
            jPanelus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelus2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelus3Layout = new javax.swing.GroupLayout(jPanelus3);
        jPanelus3.setLayout(jPanelus3Layout);
        jPanelus3Layout.setHorizontalGroup(
            jPanelus3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelus3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelus2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelus3Layout.setVerticalGroup(
            jPanelus3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelus3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelus2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelusLayout = new javax.swing.GroupLayout(jPanelus);
        jPanelus.setLayout(jPanelusLayout);
        jPanelusLayout.setHorizontalGroup(
            jPanelusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelus3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelusLayout.setVerticalGroup(
            jPanelusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelusLayout.createSequentialGroup()
                .addComponent(jPanelus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelus3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jFrameUsLayout = new javax.swing.GroupLayout(jFrameUs.getContentPane());
        jFrameUs.getContentPane().setLayout(jFrameUsLayout);
        jFrameUsLayout.setHorizontalGroup(
            jFrameUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrameUsLayout.setVerticalGroup(
            jFrameUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameUsLayout.createSequentialGroup()
                .addComponent(jPanelus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jFramePer.setBackground(new java.awt.Color(255, 255, 255));
        jFramePer.setClosable(true);
        jFramePer.setTitle("Administrar Personal");
        jFramePer.setInheritsPopupMenu(true);
        jFramePer.setMaximumSize(new java.awt.Dimension(336, 335));
        jFramePer.setVisible(true);

        jPanelPer.setBackground(new java.awt.Color(255, 255, 255));

        jPanelPer2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Existente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanelPer2.setOpaque(false);

        jTablePersonal.setToolTipText("");
        jTablePersonal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTablePersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePersonalMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTablePersonal);

        javax.swing.GroupLayout jPanelPer2Layout = new javax.swing.GroupLayout(jPanelPer2);
        jPanelPer2.setLayout(jPanelPer2Layout);
        jPanelPer2Layout.setHorizontalGroup(
            jPanelPer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelPer2Layout.setVerticalGroup(
            jPanelPer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
        );

        jPanelPer1.setBackground(new java.awt.Color(255, 255, 255));

        JButtonCancelarPer.setBackground(new java.awt.Color(255, 255, 255));
        JButtonCancelarPer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JButtonCancelarPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar16.png"))); // NOI18N
        JButtonCancelarPer.setToolTipText("Cancelar operación, limpiar los campos");
        JButtonCancelarPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonCancelarPerActionPerformed(evt);
            }
        });

        JTextFieldNombres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        JButtonAgregarPer.setBackground(new java.awt.Color(255, 255, 255));
        JButtonAgregarPer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JButtonAgregarPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aceptar16.png"))); // NOI18N
        JButtonAgregarPer.setToolTipText("Agregar registro");
        JButtonAgregarPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonAgregarPerActionPerformed(evt);
            }
        });

        jLabelNombres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNombres.setForeground(new java.awt.Color(51, 51, 255));
        jLabelNombres.setText("Nombres:");

        JTextFieldCedula.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JTextFieldCedula.setToolTipText("Formato de Cedula V-número ó E-número");
        JTextFieldCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTextFieldCedulaFocusLost(evt);
            }
        });

        JButtonEliminarPer.setBackground(new java.awt.Color(255, 255, 255));
        JButtonEliminarPer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JButtonEliminarPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/papelera16.png"))); // NOI18N
        JButtonEliminarPer.setToolTipText("Eliminar el registro seleccionado");
        JButtonEliminarPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonEliminarPerActionPerformed(evt);
            }
        });

        jLabelCedula.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCedula.setForeground(new java.awt.Color(51, 51, 255));
        jLabelCedula.setText("Cedula:");

        JComboBoxSede.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 255));
        jLabel21.setText("Sede:");

        JButtonModificarPer.setBackground(new java.awt.Color(255, 255, 255));
        JButtonModificarPer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JButtonModificarPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar16.png"))); // NOI18N
        JButtonModificarPer.setToolTipText("Modificar el registro seleccionado");
        JButtonModificarPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonModificarPerActionPerformed(evt);
            }
        });

        JTextFieldTelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JTextFieldTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTextFieldTelefonoKeyTyped(evt);
            }
        });

        jLabelTelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelTelefono.setForeground(new java.awt.Color(51, 51, 255));
        jLabelTelefono.setText("Telefono:");

        jLabelAcreditacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelAcreditacion.setForeground(new java.awt.Color(51, 51, 255));
        jLabelAcreditacion.setText("Nº Acred.:");

        JTextFieldAcreditacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        JTextFieldCarnet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabelCarnet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCarnet.setForeground(new java.awt.Color(51, 51, 255));
        jLabelCarnet.setText("Carnet MVC:");

        JTextFieldApellidos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabelNombres1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNombres1.setForeground(new java.awt.Color(51, 51, 255));
        jLabelNombres1.setText("Apellidos:");

        javax.swing.GroupLayout jPanelPer1Layout = new javax.swing.GroupLayout(jPanelPer1);
        jPanelPer1.setLayout(jPanelPer1Layout);
        jPanelPer1Layout.setHorizontalGroup(
            jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPer1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelAcreditacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCarnet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNombres1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JComboBoxSede, javax.swing.GroupLayout.Alignment.TRAILING, 0, 191, Short.MAX_VALUE)
                    .addComponent(JTextFieldNombres, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JTextFieldCedula, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JTextFieldTelefono, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JTextFieldAcreditacion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JTextFieldCarnet, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JTextFieldApellidos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(JButtonCancelarPer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JButtonEliminarPer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JButtonAgregarPer))
                    .addComponent(JButtonModificarPer, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanelPer1Layout.setVerticalGroup(
            jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPer1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPer1Layout.createSequentialGroup()
                        .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanelPer1Layout.createSequentialGroup()
                        .addComponent(JButtonAgregarPer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPer1Layout.createSequentialGroup()
                        .addComponent(JButtonModificarPer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JButtonEliminarPer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JButtonCancelarPer))
                    .addGroup(jPanelPer1Layout.createSequentialGroup()
                        .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNombres1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAcreditacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldAcreditacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JComboBoxSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelPerLayout = new javax.swing.GroupLayout(jPanelPer);
        jPanelPer.setLayout(jPanelPerLayout);
        jPanelPerLayout.setHorizontalGroup(
            jPanelPerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelPer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelPerLayout.setVerticalGroup(
            jPanelPerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPerLayout.createSequentialGroup()
                .addComponent(jPanelPer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelPer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jFramePerLayout = new javax.swing.GroupLayout(jFramePer.getContentPane());
        jFramePer.getContentPane().setLayout(jFramePerLayout);
        jFramePerLayout.setHorizontalGroup(
            jFramePerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFramePerLayout.setVerticalGroup(
            jFramePerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jFrameSede.setBackground(new java.awt.Color(255, 255, 255));
        jFrameSede.setClosable(true);
        jFrameSede.setTitle("Administrar Sedes");
        jFrameSede.setInheritsPopupMenu(true);
        jFrameSede.setMaximumSize(new java.awt.Dimension(458, 454));
        jFrameSede.setVisible(true);

        jPanelSede2.setBackground(new java.awt.Color(255, 255, 255));

        jPanelSede4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sedes Existentes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanelSede4.setOpaque(false);

        jTableSede.setToolTipText("");
        jTableSede.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableSede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSedeMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableSede);

        javax.swing.GroupLayout jPanelSede4Layout = new javax.swing.GroupLayout(jPanelSede4);
        jPanelSede4.setLayout(jPanelSede4Layout);
        jPanelSede4Layout.setHorizontalGroup(
            jPanelSede4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelSede4Layout.setVerticalGroup(
            jPanelSede4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
        );

        jPanelSede3.setBackground(new java.awt.Color(255, 255, 255));

        JButtonCancelarSede.setBackground(new java.awt.Color(255, 255, 255));
        JButtonCancelarSede.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JButtonCancelarSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar16.png"))); // NOI18N
        JButtonCancelarSede.setToolTipText("Cancelar operación, limpiar los campos");
        JButtonCancelarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonCancelarSedeActionPerformed(evt);
            }
        });

        JTextFieldNum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        JButtonAgregarSede.setBackground(new java.awt.Color(255, 255, 255));
        JButtonAgregarSede.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JButtonAgregarSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aceptar16.png"))); // NOI18N
        JButtonAgregarSede.setToolTipText("Agregar registro");
        JButtonAgregarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonAgregarSedeActionPerformed(evt);
            }
        });

        jLabelNum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNum.setForeground(new java.awt.Color(51, 51, 255));
        jLabelNum.setText("Nº Sede:");

        JTextFieldSede.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JTextFieldSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextFieldSedeActionPerformed(evt);
            }
        });

        JButtonEliminarSede.setBackground(new java.awt.Color(255, 255, 255));
        JButtonEliminarSede.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JButtonEliminarSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/papelera16.png"))); // NOI18N
        JButtonEliminarSede.setToolTipText("Eliminar el registro seleccionado");
        JButtonEliminarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonEliminarSedeActionPerformed(evt);
            }
        });

        jLabelSede.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelSede.setForeground(new java.awt.Color(51, 51, 255));
        jLabelSede.setText("Sede:");

        JButtonModificarSede.setBackground(new java.awt.Color(255, 255, 255));
        JButtonModificarSede.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JButtonModificarSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar16.png"))); // NOI18N
        JButtonModificarSede.setToolTipText("Modificar el registro seleccionado");
        JButtonModificarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonModificarSedeActionPerformed(evt);
            }
        });

        JTextFieldTelefonoSede.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JTextFieldTelefonoSede.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTextFieldTelefonoSedeKeyTyped(evt);
            }
        });

        jLabelTelefonoSede.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelTelefonoSede.setForeground(new java.awt.Color(51, 51, 255));
        jLabelTelefonoSede.setText("Telefono:");

        jLabelEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEmail.setForeground(new java.awt.Color(51, 51, 255));
        jLabelEmail.setText("e-mail:");

        JTextFieldEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JTextFieldEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTextFieldEmailKeyTyped(evt);
            }
        });

        jLabelEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEstado.setForeground(new java.awt.Color(51, 51, 255));
        jLabelEstado.setText("Estado:");

        jLabelMunicipio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelMunicipio.setForeground(new java.awt.Color(51, 51, 255));
        jLabelMunicipio.setText("Municipio:");

        jLabelParroquia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelParroquia.setForeground(new java.awt.Color(51, 51, 255));
        jLabelParroquia.setText("Parroquia:");

        jLabelParroquia1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelParroquia1.setForeground(new java.awt.Color(51, 51, 255));
        jLabelParroquia1.setText("Direccion:");

        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxMunicipio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxParroquia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        box_responsable_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        box_responsable_mod.setToolTipText("");
        box_responsable_mod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box_responsable_modMouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 255));
        jLabel29.setText("Responsable:");

        javax.swing.GroupLayout jPanelSede3Layout = new javax.swing.GroupLayout(jPanelSede3);
        jPanelSede3.setLayout(jPanelSede3Layout);
        jPanelSede3Layout.setHorizontalGroup(
            jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSede3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSede3Layout.createSequentialGroup()
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelSede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTelefonoSede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelParroquia1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTextFieldNum, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JTextFieldTelefonoSede, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JTextFieldEmail)
                            .addComponent(jComboBoxEstado, 0, 235, Short.MAX_VALUE)
                            .addComponent(jComboBoxMunicipio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxParroquia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JTextFieldDireccion)
                            .addComponent(JTextFieldSede, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanelSede3Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(box_responsable_mod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JButtonCancelarSede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JButtonEliminarSede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(JButtonModificarSede, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(JButtonAgregarSede, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanelSede3Layout.setVerticalGroup(
            jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSede3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSede, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTextFieldSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JButtonAgregarSede))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSede3Layout.createSequentialGroup()
                        .addComponent(JButtonModificarSede)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JButtonEliminarSede)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JButtonCancelarSede))
                    .addGroup(jPanelSede3Layout.createSequentialGroup()
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNum, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTelefonoSede, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldTelefonoSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelParroquia1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSede3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(box_responsable_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelSede2Layout = new javax.swing.GroupLayout(jPanelSede2);
        jPanelSede2.setLayout(jPanelSede2Layout);
        jPanelSede2Layout.setHorizontalGroup(
            jPanelSede2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSede3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelSede2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSede4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelSede2Layout.setVerticalGroup(
            jPanelSede2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSede2Layout.createSequentialGroup()
                .addComponent(jPanelSede3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelSede4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jFrameSedeLayout = new javax.swing.GroupLayout(jFrameSede.getContentPane());
        jFrameSede.getContentPane().setLayout(jFrameSedeLayout);
        jFrameSedeLayout.setHorizontalGroup(
            jFrameSedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSede2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrameSedeLayout.setVerticalGroup(
            jFrameSedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameSedeLayout.createSequentialGroup()
                .addComponent(jPanelSede2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelConfiguracionLayout = new javax.swing.GroupLayout(jPanelConfiguracion);
        jPanelConfiguracion.setLayout(jPanelConfiguracionLayout);
        jPanelConfiguracionLayout.setHorizontalGroup(
            jPanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfiguracionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFrameUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFramePer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFrameSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelConfiguracionLayout.setVerticalGroup(
            jPanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfiguracionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFrameUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelConfiguracionLayout.createSequentialGroup()
                        .addGroup(jPanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFrameSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFramePer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        try {
            jFrameUs.setIcon(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        try {
            jFramePer.setIcon(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        try {
            jFrameSede.setIcon(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jMenuBarConfig.setBackground(new java.awt.Color(204, 255, 255));
        jMenuBarConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBarConfig.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jMenuBarConfig.setOpaque(false);
        jMenuBarConfig.setPreferredSize(new java.awt.Dimension(223, 41));

        jMenuUsuario.setBackground(new java.awt.Color(255, 255, 255));
        jMenuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/usuario16.png"))); // NOI18N
        jMenuUsuario.setText("Usuarios");
        jMenuUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuAdministrarUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/config16.png"))); // NOI18N
        jMenuAdministrarUs.setText("Administrar");

        jMenuItemAgregarUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar16.png"))); // NOI18N
        jMenuItemAgregarUs.setText("Agregar");
        jMenuItemAgregarUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregarUsActionPerformed(evt);
            }
        });
        jMenuAdministrarUs.add(jMenuItemAgregarUs);

        jMenuItemModificarUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar16.png"))); // NOI18N
        jMenuItemModificarUs.setText("Modificar o Eliminar");
        jMenuItemModificarUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificarUsActionPerformed(evt);
            }
        });
        jMenuAdministrarUs.add(jMenuItemModificarUs);

        jMenuUsuario.add(jMenuAdministrarUs);

        jMenuItemImprimirUs.setBackground(new java.awt.Color(239, 239, 252));
        jMenuItemImprimirUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/imprimir16.png"))); // NOI18N
        jMenuItemImprimirUs.setText("Imprimir");
        jMenuItemImprimirUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImprimirUsActionPerformed(evt);
            }
        });
        jMenuUsuario.add(jMenuItemImprimirUs);

        jMenuBarConfig.add(jMenuUsuario);

        jMenuPersonal.setBackground(new java.awt.Color(255, 255, 255));
        jMenuPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/personal.png"))); // NOI18N
        jMenuPersonal.setText("Personal");
        jMenuPersonal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuAdministrarPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/config16.png"))); // NOI18N
        jMenuAdministrarPer.setText("Administrar");

        jMenuItemAgregarPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar16.png"))); // NOI18N
        jMenuItemAgregarPer.setText("Agregar");
        jMenuItemAgregarPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregarPerActionPerformed(evt);
            }
        });
        jMenuAdministrarPer.add(jMenuItemAgregarPer);

        jMenuItemModificarPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar16.png"))); // NOI18N
        jMenuItemModificarPer.setText("Modificar o Eliminar");
        jMenuItemModificarPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificarPerActionPerformed(evt);
            }
        });
        jMenuAdministrarPer.add(jMenuItemModificarPer);

        jMenuPersonal.add(jMenuAdministrarPer);

        jMenuItemImprimirPer.setBackground(new java.awt.Color(239, 239, 252));
        jMenuItemImprimirPer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/imprimir16.png"))); // NOI18N
        jMenuItemImprimirPer.setText("Imprimir");
        jMenuItemImprimirPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImprimirPerActionPerformed(evt);
            }
        });
        jMenuPersonal.add(jMenuItemImprimirPer);

        jMenuBarConfig.add(jMenuPersonal);

        jMenuSede.setBackground(new java.awt.Color(255, 255, 255));
        jMenuSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sede.png"))); // NOI18N
        jMenuSede.setText("Sedes");
        jMenuSede.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuAdministrarSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/config16.png"))); // NOI18N
        jMenuAdministrarSede.setText("Administrar");

        jMenuItemAgregarSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar16.png"))); // NOI18N
        jMenuItemAgregarSede.setText("Agregar");
        jMenuItemAgregarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregarSedeActionPerformed(evt);
            }
        });
        jMenuAdministrarSede.add(jMenuItemAgregarSede);

        jMenuItemModificarSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar16.png"))); // NOI18N
        jMenuItemModificarSede.setText("Modificar o Eliminar");
        jMenuItemModificarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificarSedeActionPerformed(evt);
            }
        });
        jMenuAdministrarSede.add(jMenuItemModificarSede);

        jMenuSede.add(jMenuAdministrarSede);

        jMenuItemImprimirSede.setBackground(new java.awt.Color(239, 239, 252));
        jMenuItemImprimirSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/imprimir16.png"))); // NOI18N
        jMenuItemImprimirSede.setText("Imprimir");
        jMenuItemImprimirSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImprimirSedeActionPerformed(evt);
            }
        });
        jMenuSede.add(jMenuItemImprimirSede);

        jMenuBarConfig.add(jMenuSede);

        jMenuAuditoria.setBackground(new java.awt.Color(255, 255, 255));
        jMenuAuditoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/llave16.png"))); // NOI18N
        jMenuAuditoria.setText("Auditoria");
        jMenuAuditoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItemImprimirSes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/imprimir16.png"))); // NOI18N
        jMenuItemImprimirSes.setText("Imprimir Bitácora");
        jMenuItemImprimirSes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImprimirSesActionPerformed(evt);
            }
        });
        jMenuAuditoria.add(jMenuItemImprimirSes);

        jMenuBarConfig.add(jMenuAuditoria);

        setJMenuBar(jMenuBarConfig);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConfiguracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsuariosMouseClicked
        if (jTableUsuarios.isEnabled()) {
            int fil = jTableUsuarios.getSelectedRow();//obtiene la fila seleccionada
            id_usuario_modificar = Integer.parseInt(jTableUsuarios.getModel().getValueAt(fil, 0).toString());
            txt_usuario_mod.setText(jTableUsuarios.getModel().getValueAt(fil, 1).toString());
            txt_clave_mod.setText(jTableUsuarios.getModel().getValueAt(fil, 2).toString());
            box_tipo_us.setjComboBox(box_tipo_mod, jTableUsuarios.getModel().getValueAt(fil, 3).toString());
            box_asignado.setjComboBox(box_asignado_mod, jTableUsuarios.getModel().getValueAt(fil, 5).toString());
        }
    }//GEN-LAST:event_jTableUsuariosMouseClicked

    private void jMenuItemImprimirUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImprimirUsActionPerformed
        EjecutarReporte r=new EjecutarReporte();
        r.startReportString("Sede", config.getSede_seleccionada().toString(), "usuarios");

    }//GEN-LAST:event_jMenuItemImprimirUsActionPerformed

    private void btn_eliminar_usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_usActionPerformed
        if (id_usuario_modificar != null) {
            this.iniciarUsuario();
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "Desea eliminar el usuario seleccionado?", "Confirmacion",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_NO_OPTION) {
                if (miCoordinador.eliminarUsuario(id_usuario_modificar) == true) {
                    limpiar_usuario();
                    tab_usuarios.LlenarTabla();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione en la tabla el usuario a eliminar", "Informacion", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_eliminar_usActionPerformed

    private void btn_cancelar_usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_usActionPerformed
        limpiar_usuario();
    }//GEN-LAST:event_btn_cancelar_usActionPerformed

    private void btn_agregar_usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_usActionPerformed
        try {
            this.iniciarUsuario();
            UsuarioBO miPersona = new UsuarioBO();
            ClaseObjetoParaComboBox box_tipo = (ClaseObjetoParaComboBox) box_tipo_mod.getSelectedItem();
            miPersona.setId_usuario_tipo(box_tipo.getId());
            ClaseObjetoParaComboBox box_asignado = (ClaseObjetoParaComboBox) box_asignado_mod.getSelectedItem();
            miPersona.setId_personal(box_asignado.getId());
            miPersona.setUsuario(txt_usuario_mod.getText());
            miPersona.setClave(txt_clave_mod.getText());
            if (miCoordinador.registrarUsuario(miPersona) == true) {
                limpiar_usuario();
                tab_usuarios.LlenarTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_agregar_usActionPerformed

    private void txt_usuario_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuario_modActionPerformed

    }//GEN-LAST:event_txt_usuario_modActionPerformed

    private void btn_modificar_usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificar_usActionPerformed
        try {
            this.iniciarUsuario();
            UsuarioBO miPersona = new UsuarioBO();
            miPersona.setId_usuario(id_usuario_modificar);
            ClaseObjetoParaComboBox box_tipo = (ClaseObjetoParaComboBox) box_tipo_mod.getSelectedItem();
            miPersona.setId_usuario_tipo(box_tipo.getId());
            ClaseObjetoParaComboBox box_asignado = (ClaseObjetoParaComboBox) box_asignado_mod.getSelectedItem();
            miPersona.setId_personal(box_asignado.getId());
            miPersona.setUsuario(txt_usuario_mod.getText());
            miPersona.setClave(txt_clave_mod.getText());
            if (miCoordinador.modificarUsuario(miPersona) == true) {
                limpiar_usuario();
                tab_usuarios.LlenarTabla();
            }
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_modificar_usActionPerformed

    private void jMenuItemAgregarUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregarUsActionPerformed
        btn_modificar_us.setVisible(false);
        btn_eliminar_us.setVisible(false);
        btn_agregar_us.setVisible(true);
        jTableUsuarios.setEnabled(false);
        limpiar_usuario();
        jFrameUs.setVisible(true);
    }//GEN-LAST:event_jMenuItemAgregarUsActionPerformed

    private void jMenuItemModificarUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarUsActionPerformed
        btn_modificar_us.setVisible(true);
        btn_eliminar_us.setVisible(true);
        btn_agregar_us.setVisible(false);
        jTableUsuarios.setEnabled(true);
        limpiar_usuario();
        jFrameUs.setVisible(true);
    }//GEN-LAST:event_jMenuItemModificarUsActionPerformed

    private void jTablePersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePersonalMouseClicked
        if (jTablePersonal.isEnabled()) {
            int fil = jTablePersonal.getSelectedRow();//obtiene la fila seleccionada
            id_personal_modificar = Integer.parseInt(jTablePersonal.getModel().getValueAt(fil, 0).toString());
            JTextFieldCedula.setText(jTablePersonal.getModel().getValueAt(fil, 1).toString());
            JTextFieldNombres.setText(jTablePersonal.getModel().getValueAt(fil, 2).toString());
            JTextFieldApellidos.setText(jTablePersonal.getModel().getValueAt(fil, 3).toString());
            JTextFieldTelefono.setText(jTablePersonal.getModel().getValueAt(fil, 4).toString());
            JTextFieldAcreditacion.setText(jTablePersonal.getModel().getValueAt(fil, 6).toString());
            JTextFieldCarnet.setText(jTablePersonal.getModel().getValueAt(fil, 7).toString());
            box_sede.setjComboBox(JComboBoxSede, jTablePersonal.getModel().getValueAt(fil, 5).toString());
        }
    }//GEN-LAST:event_jTablePersonalMouseClicked

    private void JButtonCancelarPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonCancelarPerActionPerformed
        limpiar_personal();
    }//GEN-LAST:event_JButtonCancelarPerActionPerformed

    private void JButtonAgregarPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonAgregarPerActionPerformed
        try {
            this.iniciarPersonal();
            PersonalBO miPersona = new PersonalBO();
            ClaseObjetoParaComboBox box_sede = (ClaseObjetoParaComboBox) JComboBoxSede.getSelectedItem();
            miPersona.setId_sede(box_sede.getId());
            miPersona.setNombres(JTextFieldNombres.getText());
            miPersona.setApellidos(JTextFieldApellidos.getText());
            miPersona.setCedula(JTextFieldCedula.getText());
            miPersona.setTelefono(JTextFieldTelefono.getText());
            miPersona.setAcreditacion(JTextFieldAcreditacion.getText());
            miPersona.setCarnet(JTextFieldCarnet.getText());
            //miCoordinadorPer.registrarPersonal(miPersona);
            if (miCoordinadorPer.registrarPersonal(miPersona) == true) {
                limpiar_personal();
                tab_personal.LlenarTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JButtonAgregarPerActionPerformed

    private void JButtonEliminarPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonEliminarPerActionPerformed
        if (id_personal_modificar != null) {
            this.iniciarPersonal();
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "Desea eliminar el personal seleccionado?", "Confirmacion",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_NO_OPTION) {
                if (miCoordinadorPer.eliminarPersonal(id_personal_modificar) == true) {
                    limpiar_personal();
                    tab_personal.LlenarTabla();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione en la tabla el personal a eliminar", "Informacion", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_JButtonEliminarPerActionPerformed

    private void JButtonModificarPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonModificarPerActionPerformed
        try {
            this.iniciarPersonal();
            PersonalBO miPersona = new PersonalBO();
            miPersona.setId_personal(id_personal_modificar);
            ClaseObjetoParaComboBox box_tipo = (ClaseObjetoParaComboBox) JComboBoxSede.getSelectedItem();
            miPersona.setId_sede(box_tipo.getId());
            miPersona.setNombres(JTextFieldNombres.getText());
            miPersona.setApellidos(JTextFieldApellidos.getText());
            miPersona.setCedula(JTextFieldCedula.getText());
            miPersona.setTelefono(JTextFieldTelefono.getText());
            miPersona.setAcreditacion(JTextFieldAcreditacion.getText());
            miPersona.setCarnet(JTextFieldCarnet.getText());
            if (miCoordinadorPer.modificarPersonal(miPersona) == true) {
                limpiar_personal();
                tab_personal.LlenarTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JButtonModificarPerActionPerformed

    private void jMenuItemAgregarPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregarPerActionPerformed
        JButtonModificarPer.setVisible(false);
        JButtonEliminarPer.setVisible(false);
        JButtonAgregarPer.setVisible(true);
        jTablePersonal.setEnabled(false);
        limpiar_personal();
        jFramePer.setVisible(true);
    }//GEN-LAST:event_jMenuItemAgregarPerActionPerformed

    private void jMenuItemModificarPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarPerActionPerformed
        JButtonModificarPer.setVisible(true);
        JButtonEliminarPer.setVisible(true);
        JButtonAgregarPer.setVisible(false);
        jTablePersonal.setEnabled(true);
        limpiar_personal();
        jFramePer.setVisible(true);
    }//GEN-LAST:event_jMenuItemModificarPerActionPerformed

    private void jMenuItemImprimirPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImprimirPerActionPerformed
        EjecutarReporte r=new EjecutarReporte();
        r.startReportString("Sede", config.getSede_seleccionada().toString(), "personal");
    }//GEN-LAST:event_jMenuItemImprimirPerActionPerformed

    private void jTableSedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSedeMouseClicked
        if (jTableSede.isEnabled()) {
            int fil = jTableSede.getSelectedRow();//obtiene la fila seleccionada
            id_sede_modificar = Integer.parseInt(jTableSede.getModel().getValueAt(fil, 0).toString());
            JTextFieldSede.setText(jTableSede.getModel().getValueAt(fil, 1).toString());
            JTextFieldNum.setText(jTableSede.getModel().getValueAt(fil, 2).toString());
            JTextFieldTelefonoSede.setText(jTableSede.getModel().getValueAt(fil, 3).toString());
            JTextFieldEmail.setText(jTableSede.getModel().getValueAt(fil, 4).toString());
            JTextFieldDireccion.setText(jTableSede.getModel().getValueAt(fil, 5).toString());
            box_estado.setjComboBox(jComboBoxEstado, jTableSede.getModel().getValueAt(fil, 8).toString());
            box_municipio.setjComboBox(jComboBoxMunicipio, jTableSede.getModel().getValueAt(fil, 7).toString());
            box_parroquia.setjComboBox(jComboBoxParroquia, jTableSede.getModel().getValueAt(fil, 6).toString());
            if (box_responsable_mod.getItemCount() > 2) {
                box_responsable.setjComboBox(box_responsable_mod, jTableSede.getModel().getValueAt(fil, 9).toString());
            }
        }
    }//GEN-LAST:event_jTableSedeMouseClicked

    private void JButtonCancelarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonCancelarSedeActionPerformed
        limpiar_sede();
    }//GEN-LAST:event_JButtonCancelarSedeActionPerformed

    private void JButtonAgregarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonAgregarSedeActionPerformed
        try {
            this.iniciarSede();
            SedeBO miSede = new SedeBO();
            ClaseObjetoParaComboBox box_parroquiab = (ClaseObjetoParaComboBox) jComboBoxParroquia.getSelectedItem();
            miSede.setParroquia_id(box_parroquiab.getId());
            miSede.setSede(JTextFieldSede.getText());
            miSede.setNum_sede(JTextFieldNum.getText());
            miSede.setTelefono(JTextFieldTelefonoSede.getText());
            miSede.setEmail(JTextFieldEmail.getText());
            miSede.setDireccion(JTextFieldDireccion.getText());
            if (miCoordinadorSed.registrarSede(miSede) == true) {
                limpiar_sede();
                tab_sede.LlenarTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JButtonAgregarSedeActionPerformed

    private void JTextFieldSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextFieldSedeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextFieldSedeActionPerformed

    private void JButtonEliminarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonEliminarSedeActionPerformed
        if (id_sede_modificar != null) {
            this.iniciarSede();
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "Desea eliminar la sede seleccionada?", "Confirmacion",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_NO_OPTION) {
                if (miCoordinadorSed.eliminarSede(id_sede_modificar) == true) {
                    limpiar_sede();
                    tab_sede.LlenarTabla();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione en la tabla la sede a eliminar", "Informacion", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_JButtonEliminarSedeActionPerformed

    private void JButtonModificarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonModificarSedeActionPerformed
        try {
            this.iniciarSede();
            SedeBO miSede = new SedeBO();
            ClaseObjetoParaComboBox box_parroquiab = (ClaseObjetoParaComboBox) jComboBoxParroquia.getSelectedItem();
            miSede.setParroquia_id(box_parroquiab.getId());
            miSede.setSede(JTextFieldSede.getText());
            miSede.setNum_sede(JTextFieldNum.getText());
            miSede.setTelefono(JTextFieldTelefonoSede.getText());
            miSede.setEmail(JTextFieldEmail.getText());
            miSede.setDireccion(JTextFieldDireccion.getText());
            miSede.setId_sedes_insai(id_sede_modificar);
            ClaseObjetoParaComboBox box_responsable = (ClaseObjetoParaComboBox) box_responsable_mod.getSelectedItem();
            miSede.setResponsable(box_responsable.getId());
            if (miCoordinadorSed.modificarSede(miSede) == true) {
                limpiar_sede();
                tab_sede.LlenarTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JButtonModificarSedeActionPerformed

    private void jMenuItemAgregarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregarSedeActionPerformed
        JButtonModificarSede.setVisible(false);
        JButtonEliminarSede.setVisible(false);
        JButtonAgregarSede.setVisible(true);
        //box_responsable_mod.setVisible(false);
        //jLabel29.setVisible(false);
        jTableSede.setEnabled(false);
        //limpiar_sede();
        jFrameSede.setVisible(true);
    }//GEN-LAST:event_jMenuItemAgregarSedeActionPerformed

    private void jMenuItemModificarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarSedeActionPerformed
        JButtonModificarSede.setVisible(true);
        JButtonEliminarSede.setVisible(true);
        JButtonAgregarSede.setVisible(false);
        //box_responsable_mod.setVisible(true);
        //jLabel29.setVisible(true);
        jTableSede.setEnabled(true);
        //limpiar_sede();
        jFrameSede.setVisible(true);
    }//GEN-LAST:event_jMenuItemModificarSedeActionPerformed

    private void jMenuItemImprimirSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImprimirSedeActionPerformed
            EjecutarReporte r = new EjecutarReporte();
            r.startReport("id", 0, "sedes");
    }//GEN-LAST:event_jMenuItemImprimirSedeActionPerformed

    private void box_responsable_modMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box_responsable_modMouseClicked
        if (box_responsable_mod.getItemCount() < 2) {
            JOptionPane.showMessageDialog(null, "Ingrese primero el personal de la sede y despues actualize", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_box_responsable_modMouseClicked

    private void JTextFieldTelefonoSedeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextFieldTelefonoSedeKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTextFieldTelefonoSedeKeyTyped

    private void JTextFieldTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextFieldTelefonoKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTextFieldTelefonoKeyTyped

    private void JTextFieldEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextFieldEmailKeyTyped

        String correo = JTextFieldEmail.getText();
        if (em.isEmail(correo)) {
            JTextFieldEmail.setForeground(Color.black);
            JTextFieldEmail.setToolTipText("Email Correcto");
        } else {
            JTextFieldEmail.setForeground(Color.red);
            JTextFieldEmail.setToolTipText("Email Incorrecto, modifique o borre");
        }
    }//GEN-LAST:event_JTextFieldEmailKeyTyped

    private void JTextFieldCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTextFieldCedulaFocusLost
        String correo = JTextFieldCedula.getText();
        if (em.isCed(correo)) {
            JTextFieldCedula.setForeground(Color.black);
            JTextFieldCedula.setToolTipText("Cedula Correcta");
        } else {
            JTextFieldCedula.setForeground(Color.red);
            JTextFieldCedula.setToolTipText("Cedula Incorrecta - Formato de Cedula V-número ó E-número");
        }
    }//GEN-LAST:event_JTextFieldCedulaFocusLost

    private void jMenuItemImprimirSesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImprimirSesActionPerformed
        String fecha_inicial = JOptionPane.showInputDialog(null, "Fecha Inicial:", "Ingrese la fecha en formato aaaa-mm-dd", JOptionPane.QUESTION_MESSAGE);
        String fecha_final = JOptionPane.showInputDialog(null, "Fecha Final:", "Ingrese la fecha en formato aaaa-mm-dd", JOptionPane.QUESTION_MESSAGE);
        ValidacionesVista em = new ValidacionesVista();
        if (em.isFecha(fecha_inicial) && em.isFecha(fecha_final)) {
        EjecutarReporte r = new EjecutarReporte();
        r.startReportMensual("sede", config.getSede_seleccionada(), "fecha_inicio", fecha_inicial, "fecha_fin", fecha_final, "auditoria");
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese las fechas con el formato indicado");
        }
    }//GEN-LAST:event_jMenuItemImprimirSesActionPerformed

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
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        JFrameConFondo miPrincipal = new Configuracion();
        miPrincipal.setLocationRelativeTo(null);
        miPrincipal.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonAgregarPer;
    private javax.swing.JButton JButtonAgregarSede;
    private javax.swing.JButton JButtonCancelarPer;
    private javax.swing.JButton JButtonCancelarSede;
    private javax.swing.JButton JButtonEliminarPer;
    private javax.swing.JButton JButtonEliminarSede;
    private javax.swing.JButton JButtonModificarPer;
    private javax.swing.JButton JButtonModificarSede;
    private javax.swing.JComboBox JComboBoxSede;
    private javax.swing.JTextField JTextFieldAcreditacion;
    private javax.swing.JTextField JTextFieldApellidos;
    private javax.swing.JTextField JTextFieldCarnet;
    private javax.swing.JTextField JTextFieldCedula;
    private javax.swing.JTextField JTextFieldDireccion;
    private javax.swing.JTextField JTextFieldEmail;
    private javax.swing.JTextField JTextFieldNombres;
    private javax.swing.JTextField JTextFieldNum;
    private javax.swing.JTextField JTextFieldSede;
    private javax.swing.JTextField JTextFieldTelefono;
    private javax.swing.JTextField JTextFieldTelefonoSede;
    private javax.swing.JComboBox box_asignado_mod;
    private javax.swing.JComboBox box_responsable_mod;
    private javax.swing.JComboBox box_tipo_mod;
    private javax.swing.JButton btn_agregar_us;
    private javax.swing.JButton btn_cancelar_us;
    private javax.swing.JButton btn_eliminar_us;
    private javax.swing.JButton btn_modificar_us;
    private javax.swing.JComboBox jComboBoxEstado;
    private javax.swing.JComboBox jComboBoxMunicipio;
    private javax.swing.JComboBox jComboBoxParroquia;
    private javax.swing.JInternalFrame jFramePer;
    private javax.swing.JInternalFrame jFrameSede;
    private javax.swing.JInternalFrame jFrameUs;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAcreditacion;
    private javax.swing.JLabel jLabelCarnet;
    private javax.swing.JLabel jLabelCedula;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelMunicipio;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JLabel jLabelNombres1;
    private javax.swing.JLabel jLabelNum;
    private javax.swing.JLabel jLabelParroquia;
    private javax.swing.JLabel jLabelParroquia1;
    private javax.swing.JLabel jLabelSede;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelTelefonoSede;
    private javax.swing.JMenu jMenuAdministrarPer;
    private javax.swing.JMenu jMenuAdministrarSede;
    private javax.swing.JMenu jMenuAdministrarUs;
    private javax.swing.JMenu jMenuAuditoria;
    private javax.swing.JMenuBar jMenuBarConfig;
    private javax.swing.JMenuItem jMenuItemAgregarPer;
    private javax.swing.JMenuItem jMenuItemAgregarSede;
    private javax.swing.JMenuItem jMenuItemAgregarUs;
    private javax.swing.JMenuItem jMenuItemImprimirPer;
    private javax.swing.JMenuItem jMenuItemImprimirSede;
    private javax.swing.JMenuItem jMenuItemImprimirSes;
    private javax.swing.JMenuItem jMenuItemImprimirUs;
    private javax.swing.JMenuItem jMenuItemModificarPer;
    private javax.swing.JMenuItem jMenuItemModificarSede;
    private javax.swing.JMenuItem jMenuItemModificarUs;
    private javax.swing.JMenu jMenuPersonal;
    private javax.swing.JMenu jMenuSede;
    private javax.swing.JMenu jMenuUsuario;
    private javax.swing.JPanel jPanelConfiguracion;
    private javax.swing.JPanel jPanelPer;
    private javax.swing.JPanel jPanelPer1;
    private javax.swing.JPanel jPanelPer2;
    private javax.swing.JPanel jPanelSede2;
    private javax.swing.JPanel jPanelSede3;
    private javax.swing.JPanel jPanelSede4;
    private javax.swing.JPanel jPanelus;
    private javax.swing.JPanel jPanelus1;
    private javax.swing.JPanel jPanelus2;
    private javax.swing.JPanel jPanelus3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTablePersonal;
    private javax.swing.JTable jTableSede;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JTextField txt_clave_mod;
    private javax.swing.JTextField txt_usuario_mod;
    // End of variables declaration//GEN-END:variables
}
