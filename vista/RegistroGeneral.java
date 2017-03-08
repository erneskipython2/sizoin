package vista;

import controlador.CoordinadorResultado;
import controlador.CoordinadorParroquia;
import controlador.CoordinadorPredio;
import datechooser.beans.DateChooserCombo;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import modeloBO.PropietarioBO;
import sizin.JFrameConFondo;
import sizin.Box;
import sizin.Tabla;
import sizin.Lista;
import sizin.JLabelImagenAjusta;
import sizin.Reloj;
import sizin.ConfiguracionGlobal;
import sizin.ClaseObjetoParaComboBox;
import sizin.MyRenderer;
import sizin.JTextFieldLimit;
import controlador.CoordinadorPropietario;
import controlador.CoordinadorRegistroGeneral;
import java.awt.HeadlessException;
import java.awt.geom.Point2D;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.ParseException;
import logica.LogicaResultado;
import logica.LogicaParroquia;
import logica.LogicaPredio;
import logica.LogicaPropietario;
import logica.LogicaRegistroGeneral;
import modeloBO.HemoparasitoDetalleBO;
import modeloBO.ParroquiaBO;
import modeloBO.PredioBO;
import modeloBO.RegistroGeneralBO;
import modeloBO.RegistroMuestraBO;
import modeloBO.ResultadoHemoparasitoBO;
import modeloDAO.AnemiaDAO;
import modeloDAO.BrucelosisDAO;
import modeloDAO.HematologiaDAO;
import modeloDAO.HemoparasitoDAO;
import sizin.ClaseObjetoParaLista;
import sizin.FormatoTablaBuscar;
import sizin.URLabel;
import sizin.ValidacionesVista;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import maps.java.Geocoding;
import maps.java.MapsJava;
import modeloDAO.CoprologiaDAO;
import sizin.EjecutarReporte;
import sizin.Ubicacion;

/**
 * Vishemo mas relevante del sistema, contiene registro, modificacion y
 eliminacion de muestras y sus resulhemodos
 *
 * @author Erneski Coronado
 */
public class RegistroGeneral extends JFrameConFondo {// extiende de JFrameConFondo para colocar imagen de fondo en JFrame metodo setImagen

    private Box comboestado;// modelo para jComboBox Estado
    private Box combomunicipio; //modelo para jComboBox municipio
    private Box comboparroquia; //modelo para jComboBox parroquia  
    private Box comboestadobuscar;// modelo para jComboBox Estado en buscar
    private Box combomunicipiobuscar; //modelo para jComboBox municipio en buscar
    private Box comboparroquiabuscar; //modelo para jComboBox parroquia en buscar
    private Box comboreino;//modelo para el jComboBox genero
    private Box combogrupo;//modelo para el jComboBox del grupo etareo
    private Box combotipoanalisis; //modelo para el jComboBox tipoanalisis
    private Box combopersonal; //modelo para el jComboBox del personal analista 
    private Box combotipoanalisisbuscar;//modelo para el jComboBox tipo de analisis buscar
    private Box combomuestra;
    private Tabla muestras;//modelo para la tabla de agregar muestras del formulario de Agregar Registro
    private Tabla muestrasb; //modelo para la tabla de resultados de brucelosis, solo para pruebas, puede ser eliminado
    private Tabla buscar;//modelo paa la tabla para buscar registros y muestras 
    private Tabla mod; //modelo para tabla propietarios   
    private Tabla detalle; //modelo para tabla propietarios 
    private Tabla pre = new Tabla(); //modelo para la tabla predios, para seleccionar los predios asociados a un productor
    private int filatabla;
    private int filatabladet;
    private int n_muestras;//almacena la cantidad de muestras por registro
    private int filatablabuscar; //guarda la fila seleccionada en la tabla de buscar registros y muestras
    private Integer idregistrogeneralmodificar; //almacena el id del registro general que se desea modificar
    private ArrayList filasid;//guarda los id  de genero, especie y raza de las muestras agregadas en la tabla, esto es porque en la tabla solo se cargan los string del genero, especie y raza, esto puede ser arreglado pasando un object con ClaseObjetoParaComboBox a la tabla en vez de un string   
    private String ModiTip; //Obtiene el telf de Propietario en la tabla jTablePropietarios en evento click
    private String ModiId; //Obtiene el id del Propietario en la tabla jTablePropietarios en evento click
    private String Moditiprif;//obtiene el tipo de rif separado
    private String rifextra; //obtiene el rif extraido de la BD sin el tipo.
    private String Protocoloid; //obtiene el protocolo para modificar, eliminar e ingresar resultados en  buscar registros
    private String tipoanalisisbuscar;//Obtiene el valor para buscar por tipo de analisis
    private String analisiselec;//guardar el tipo de analisis de la fila seleccionada
    private int idusact; //almacena el id del propietario seleccionado actualmente, compara si hay uno seleccionado, modificar y eliminar 
    private int idpreact; //almacena el id del predio asociado al productor para lanzar en el formulario de agregar registro
    private String predio_actual;
    private int indexresultadobru = 0;//almacena el index del resultado para la tabla de resultados de brucelosis - resultadobru ya no es util
    private Lista tax= new Lista(); //modelo para la lista JListTipoPrueba muestra los tipos de prueba en base al tipo de analisis
    private Lista per;//modelo para la lista JListAnalistas muestra los analistas para un resultado
    private Lista fin;//modelo para la lista jListFinalidad de la finalidades del predio
    private JComboBox jComboBoxEstadobuscar;// para seleccionar filtro por estado en buscar registro
    private JComboBox jComboBoxMunicipiobuscar;// para seleccionar filtro por municipio en buscar registro
    private JComboBox jComboBoxParroquiabuscar;// para seleccionar filtro por parroquia en buscar registro
    private ConfiguracionGlobal config = new ConfiguracionGlobal();
    private RegistroGeneral miVentanaRegistro;
    private CoordinadorPropietario miCoordinador;
    private CoordinadorPredio miCoordinadorPredio;
    private CoordinadorParroquia miCoordinadorParroquia;
    private CoordinadorResultado miCoordinadorResultado;
    private CoordinadorRegistroGeneral miCoordinadorRegistroGeneral;
    private LogicaRegistroGeneral miLogicaRegistroGeneral;
    private LogicaPropietario miLogica;
    private LogicaPredio miLogicaPredio;
    private LogicaParroquia miLogicaParroquia;
    private LogicaResultado miLogicaResultado;
    private URLabel label;
    private ClaseObjetoParaLista taxo = null;
    ResultadoHemoparasitoBO resultHPBo;
    private Ubicacion ObjUbicacion=new Ubicacion();

    public RegistroGeneral() {
        MapsJava.setKey("AIzaSyCzWaJYw_MW87ganzyaVlxB9igfGMTTrW8");
        this.label = new URLabel();
        this.jComboBoxParroquiabuscar = new JComboBox();
        this.jComboBoxMunicipiobuscar = new JComboBox();
        this.jComboBoxEstadobuscar = new JComboBox();
        this.fin = new Lista();
        this.per = new Lista();       
        this.filasid = new ArrayList();
        this.detalle = new Tabla();
        this.mod = new Tabla();
        this.buscar = new Tabla();
        this.muestrasb = new Tabla();
        this.muestras = new Tabla();
        this.combomuestra = new Box();
        this.combogrupo = new Box();
        this.combotipoanalisisbuscar = new Box();
        this.combopersonal = new Box();
        this.combotipoanalisis = new Box();
        this.comboreino = new Box();
        this.comboparroquiabuscar = new Box();
        this.combomunicipiobuscar = new Box();
        this.comboestadobuscar = new Box();
        this.comboparroquia = new Box();
        this.combomunicipio = new Box();
        this.comboestado = new Box();
        initComponents();
        setImagen("imagenes/fondo_difuminado.jpg");//se pasa la imagen de fondo del formulario
        JLabelImagenAjusta logo = new JLabelImagenAjusta();
        logo.ImagenJLabel(jLabelLogo, "imagenes/logo_sistema.png");
                jLabelLogo_des.setText(JLabelImagenAjusta.convertToMultiline("SISTEMA DE INFORMACIÓN PARA EL REGISTRO Y ANALISIS\n"
                + "DE MUESTRAS ZOOPATÓGENAS DE LA RED DE \n"
                + "LABORATORIOS DE DIAGNÓSTICO ZOOSANITARIO DEL INSAI\n"));
        Reloj r = new Reloj();
        jLabelFechaRG.setText(r.Hora());

        //jLabelImagenAjusta aju=new jLabelImagenAjusta();//creo objeto para jlabel con fondo del frame interno 
        //aju.ImagenJLabel(jLabelFondoPropietario, "iconos/propietarios.jpg");// asigno la JLabel y la foto
        comboestado.consultabox2(config.box_consulta_estado, "id_estado", "estado");//cargamos la consulta para cargar el estado de agregar registro       
        //jComboBoxEstado.setModel(comboestado);
        comboestado.LLenarBoxConID();//llenamos el combo del estado de agregar registro
        combomuestra.consultabox2(config.box_consulta_muestra, "id_muestra_tipo", "muestra_tipo");
        combomuestra.LLenarBoxConID();
        combogrupo.consultabox2(config.box_consulta_grupo, "tsn_rapido", "nombre_comun");
        combogrupo.LLenarBoxConID();
        combopersonal.consultabox2(config.box_consulta_personal_cedula(), "id_personal", "cedula");//cargamos la consulta para cargar el personal analista de la muestra       
        //jComboBoxEstado.setModel(comboestado);
        combopersonal.LLenarBoxConID();//llenamos el combo del estado de agregar registro
        tax.consultalist(config.lista_taxon_inicial, "tax.tsn", "tax.nombre_completo", "tax.rango_id", "ran.rango");//cargamos la consulta para cargar la especie
        tax.LLenarLista();//llenamos el combo de la especie
        per.consultalist(config.lista_consulta_personal_cedula(), "id_personal", "cedula", "id_personal", "num_acreditacion");
        per.LLenarLista();
        fin.consultalist(config.lista_finalidad, "id_finalidad", "finalidad", "id_finalidad", "finalidad");
        fin.LLenarLista();
        detalle.crearmodeloresultado();
        //listeners para llenar combos abuelo, padre, nieto y amigo de estado, municipio, parroquia y sector
        jComboBoxEstadoAgregar.addItemListener(new ItemListener() {

            //eventos para carga dinamica de JComboBox
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // los nuevos registros son agregados al MODEL del JCombo HIJO
                    Object item = e.getItem();
                    ClaseObjetoParaComboBox persona = (ClaseObjetoParaComboBox) item;
                    int idgen = persona.getId();
                    combomunicipio.consultabox2(config.box_consulta_municipio(idgen), "id_municipio", "municipio");//cargamos la consulta para cargar el municipio
                    combomunicipio.LLenarBoxConID();//llenamos el combo de municipio
                }
            }
        });
        jComboBoxMunicipioAgregar.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // los nuevos registros son agregados al MODEL del JCombo HIJO
                    Object item = e.getItem();
                    ClaseObjetoParaComboBox persona = (ClaseObjetoParaComboBox) item;
                    int idgen = persona.getId();
                    comboparroquia.consultabox2(config.box_consulta_parroquia(idgen), "id_parroquia", "parroquia");//cargamos la consulta para cargar la parroquia
                    comboparroquia.LLenarBoxConID();//llenamos el combo de la parroquia 
                }
            }
        });

        comboreino.consultabox2(config.box_consulta_reino, "reino_id", "reino"); //cargamos la consulta para el combo de genero del animal de agregar registro
        comboreino.LLenarBoxConID();//llenamos el combo del genero del animal de agregar registro     
        combotipoanalisis.consultabox2(config.box_consulta_analisis_tipo, "id_analisis_tipo", "analisis_tipo");//cargamos la consulta para el combo del tipo de analisis de agregar registro
        combotipoanalisis.LLenarBoxConID();//llenamos el combo del tipo de analisis de agregar registro
        combotipoanalisisbuscar.consultabox2(config.box_consulta_analisis_tipo, "id_analisis_tipo", "analisis_tipo"); //cargamos la consulta para el combo de tipo de analisis para buscar un registro
        combotipoanalisisbuscar.LLenarBoxConID();//llenamos el combo del tipo de analisis para buscar un registro
        combotipoanalisisbuscar.modelo.addElement("Todos");// agregar la opcion para buscar todos los analisis en buscar un registro
        dateChooserComboVacunacion.setEnabled(false);//se habilita cuando selecionamos el jCheckBoxVacunado
        muestras.crearmodelomuestras();//creamos el modelo de la tabla para las muestras 
        muestrasb.crearmodelomuestrasbru();//creamos el modelo de la tabla para el resultado de brucelosis, ya no se usa
        buscar.crearmodelobuscar();//creamos el modelo para la tabla buscar
        mod.columnastabla(5, config.tabla_consulta_propietario);// al abrir pasamos los parametros de la tabla  propietarios
        mod.crearmodelopropietario();//al abrir creamos la tabla  propietarios
        jTextFieldRifModificar.setEnabled(false);
        jTextFieldNombreModificar.setEnabled(false);
        jTextFieldApellidoModificar.setEnabled(false);
        jTextFieldTelefonoModificar.setEnabled(false);
        jComboBoxRifModificar.setEnabled(false);
        jComboBoxEstadobuscar.setModel(comboestadobuscar.modelo);//asignamos el modelo para el jcombobox de buscar por estado
        jComboBoxMunicipiobuscar.setModel(combomunicipiobuscar.modelo);//asignamos el modelo para el jcombobox de buscar por municipio
        jComboBoxParroquiabuscar.setModel(comboparroquiabuscar.modelo);//asignamos el modelo para el jcombobox de buscar por parroquia
        Protocoloid = "";//definimos el protocolo vacio
        pre.crearmodelopredios();//creo el modelo para la seleccion de predio en agregar registros 

        //limitar cantidad de caracteres
        jTextFieldRifModificar.setDocument(new JTextFieldLimit(11, false));
        jTextFieldNombreModificar.setDocument(new JTextFieldLimit(45, false));
        jTextFieldApellidoModificar.setDocument(new JTextFieldLimit(45, false));
        jTextFieldTelefonoModificar.setDocument(new JTextFieldLimit(11, false));
        jTextFieldRifAgregar.setDocument(new JTextFieldLimit(11, false));
        jTextFieldNombreAgregar.setDocument(new JTextFieldLimit(45, false));
        jTextFieldApellidoAgregar.setDocument(new JTextFieldLimit(45, false));
        jTextFieldTelefonoAgregar.setDocument(new JTextFieldLimit(11, false));
        jTextFieldPredioAgregar.setDocument(new JTextFieldLimit(80, false));
        jTextFieldDireccionAgregar.setDocument(new JTextFieldLimit(250, false));
        jTextFieldSuperficieAgregar.setDocument(new JTextFieldLimit(15, false));
        jTextFieldUtmNorteAgregar.setDocument(new JTextFieldLimit(11, false));
        jTextFieldUtmEsteAgregar.setDocument(new JTextFieldLimit(11, false));
        jTextFieldRemitente.setDocument(new JTextFieldLimit(100, false));
        jTextFieldHierro.setDocument(new JTextFieldLimit(25, false));
        jTextFieldObservaciones.setDocument(new JTextFieldLimit(100, false));

        //Colocar color y tamaño a las cabeceras de las tablas      
        Integer tam_buscar[] = {120, 130, 130, 160, 160, 160, 100, 120, 120, 160, 160, 70};
        for (int i = 0; i < 12; i++) {
            jTableBuscar.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
            jTableBuscar.getColumnModel().getColumn(i).setPreferredWidth(tam_buscar[i]);
        }
        Integer tam_muestras[] = {80, 180, 200, 80, 120, 50, 50, 275};
        for (int i = 0; i < 8; i++) {
            jTableMuestras.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
            jTableMuestras.getColumnModel().getColumn(i).setPreferredWidth(tam_muestras[i]);//autoajustar celdas al tamaño del contenido                
        }
        Integer tam_detalle[] = {80, 160, 80};
        for (int i = 0; i < 3; i++) {
            jTableDetalleResultado.getColumnModel().getColumn(i).setPreferredWidth(tam_detalle[i]);
            jTableDetalleResultado.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
        }
        for (int i = 0; i < 5; i++) {
            jTablePropietarios.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotonesSolicitud = new javax.swing.JPanel();
        jButtonAbrirAgregarRegistro = new javax.swing.JButton();
        jButtonAbrirBuscarRegistro = new javax.swing.JButton();
        jLayeredPaneSolicitudes = new javax.swing.JLayeredPane();
        jInternalFramePropietario = new javax.swing.JInternalFrame();
        jPanel9 = new javax.swing.JPanel();
        jPanelPropietariosTabla = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePropietarios = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jTabbedPanePropietarios = new javax.swing.JTabbedPane();
        jPanelBuscar = new javax.swing.JPanel();
        jLabelRif = new javax.swing.JLabel();
        jComboBoxRif = new javax.swing.JComboBox();
        jTextFieldRif = new javax.swing.JTextField();
        jCheckBoxFiltro = new javax.swing.JCheckBox();
        jButtonBuscar = new javax.swing.JButton();
        jSeparatorBuscar1 = new javax.swing.JSeparator();
        jSeparatorBuscar2 = new javax.swing.JSeparator();
        jPanelAgregar = new javax.swing.JPanel();
        jPanelBotonesAgregar = new javax.swing.JPanel();
        jButtonAgregar = new javax.swing.JButton();
        jLabelRifAgregar = new javax.swing.JLabel();
        jComboBoxRifAgregar = new javax.swing.JComboBox();
        jTextFieldRifAgregar = new javax.swing.JTextField();
        jLabelNombreAgregar = new javax.swing.JLabel();
        jTextFieldNombreAgregar = new javax.swing.JTextField();
        jLabelTelefonoAgregar = new javax.swing.JLabel();
        jTextFieldTelefonoAgregar = new javax.swing.JTextField();
        jLabelNombreAgregar1 = new javax.swing.JLabel();
        jTextFieldApellidoAgregar = new javax.swing.JTextField();
        jPanelModificar = new javax.swing.JPanel();
        jPanelBotonesModificar = new javax.swing.JPanel();
        jButtonEliminar = new javax.swing.JButton();
        jButtonLimpiarPropietario = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jLabelRifModificar = new javax.swing.JLabel();
        jLabelNombreModificar = new javax.swing.JLabel();
        jLabelTelefonoModificar = new javax.swing.JLabel();
        jComboBoxRifModificar = new javax.swing.JComboBox();
        jTextFieldRifModificar = new javax.swing.JTextField();
        jTextFieldNombreModificar = new javax.swing.JTextField();
        jTextFieldTelefonoModificar = new javax.swing.JTextField();
        jTextFieldApellidoModificar = new javax.swing.JTextField();
        jLabelNombreModificar1 = new javax.swing.JLabel();
        jInternalFrameRegistroGeneral = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jComboBoxEdadMeses = new javax.swing.JComboBox();
        jLabelGenero3 = new javax.swing.JLabel();
        jComboBoxSexo = new javax.swing.JComboBox();
        jLabelVacunacion = new javax.swing.JLabel();
        jLabelGenero1 = new javax.swing.JLabel();
        jLabelEdad = new javax.swing.JLabel();
        jCheckBoxVacunado = new javax.swing.JCheckBox();
        jComboBoxMuestra = new javax.swing.JComboBox();
        dateChooserComboVacunacion = new datechooser.beans.DateChooserCombo();
        jLabelObservaciones = new javax.swing.JLabel();
        jTextFieldHierro = new javax.swing.JTextField();
        jTextFieldObservaciones = new javax.swing.JTextField();
        jLabelIdentificacion = new javax.swing.JLabel();
        jComboBoxEdadYear = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabelGenero2 = new javax.swing.JLabel();
        jPanelUrl = new javax.swing.JPanel();
        jComboBoxGrupo = new javax.swing.JComboBox();
        jButtonTax = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListTaxon = new javax.swing.JList();
        jButtonTaxReset = new javax.swing.JButton();
        jButtonBusquedaRapida = new javax.swing.JButton();
        jLabelGenero = new javax.swing.JLabel();
        jButtonAgregarMuestra = new javax.swing.JButton();
        jButtonAgregarRegistro = new javax.swing.JButton();
        jPanelDatosGeneralesRG = new javax.swing.JPanel();
        jButtonPropietario = new javax.swing.JButton();
        jLabelRemitente = new javax.swing.JLabel();
        jLabelPredio = new javax.swing.JLabel();
        jLabelTipoAnalisis = new javax.swing.JLabel();
        jTextFieldPredio = new javax.swing.JTextField();
        jButtonPredios = new javax.swing.JButton();
        jTextFieldRemitente = new javax.swing.JTextField();
        jLabelPropietario = new javax.swing.JLabel();
        dateChooserComboRecepcion = new datechooser.beans.DateChooserCombo();
        jLabelFechaRecepcion = new javax.swing.JLabel();
        jTextFieldPropietario = new javax.swing.JTextField();
        jComboBoxTipoAnalisis = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableMuestras = new javax.swing.JTable();
        jLabelNMuestras = new javax.swing.JLabel();
        jButtonQuitarMuestra = new javax.swing.JButton();
        jInternalFrameAgregarParroquia = new javax.swing.JInternalFrame();
        jPanel8 = new javax.swing.JPanel();
        jLabelEstadoAgregarParroquia = new javax.swing.JLabel();
        jTextFieldEstadoAgregarParroquia = new javax.swing.JTextField();
        jLabelMunicipioAgregarParroquia = new javax.swing.JLabel();
        jTextFieldMunicipioAgregarParroquia = new javax.swing.JTextField();
        jLabelParroquiaAgregarParroquia = new javax.swing.JLabel();
        jTextFieldParroquiaAgregarParroquia = new javax.swing.JTextField();
        jButtonAgregarParroquia = new javax.swing.JButton();
        jInternalFrameBuscar = new javax.swing.JInternalFrame();
        jPanelBucar = new javax.swing.JPanel();
        jPanelOpcBus = new javax.swing.JPanel();
        jComboBoxTipoAnalisisBuscar = new javax.swing.JComboBox();
        jLabelAnalisisBuscar = new javax.swing.JLabel();
        jLabelAnalisisBuscarPor = new javax.swing.JLabel();
        jComboBoxBuscarPor = new javax.swing.JComboBox();
        jButtonBuscarPor = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableBuscar = new javax.swing.JTable();
        jButtonEliminarRegistro = new javax.swing.JButton();
        jButtonModificarRegistro = new javax.swing.JButton();
        jButtonResultado = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jInternalFramePredios = new javax.swing.JInternalFrame();
        jPanel11 = new javax.swing.JPanel();
        jButtonAbrirAgregarPredios = new javax.swing.JButton();
        jButtonAbrirModificarPredios = new javax.swing.JButton();
        jButtonPredio = new javax.swing.JButton();
        jPanelPrediosTabla = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTablePredios = new javax.swing.JTable();
        jInternalFrameBrucelosisR = new javax.swing.JInternalFrame();
        jPanelBrucelosisR = new javax.swing.JPanel();
        jLabelResultadoBrucelosis = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonResultadoBru = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableMuestrasbru = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jListAnalistas = new javax.swing.JList();
        dateChooserComboProcesamiento = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        jButtonCargar = new javax.swing.JButton();
        jInternalFramePrediosAsociados = new javax.swing.JInternalFrame();
        jPanel21 = new javax.swing.JPanel();
        jLabelPredio3 = new javax.swing.JLabel();
        jTextFieldPredioAgregar = new javax.swing.JTextField();
        jLabelFinalidad3 = new javax.swing.JLabel();
        jLabelEstado3 = new javax.swing.JLabel();
        jComboBoxEstadoAgregar = new javax.swing.JComboBox();
        jLabelMunicipio3 = new javax.swing.JLabel();
        jComboBoxMunicipioAgregar = new javax.swing.JComboBox();
        jLabelParroquia3 = new javax.swing.JLabel();
        jComboBoxParroquiaAgregar = new javax.swing.JComboBox();
        jLabelSector3 = new javax.swing.JLabel();
        jButtonAbrirParroquiaAgregar = new javax.swing.JButton();
        jLabelUTM3 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabelUtmNorte3 = new javax.swing.JLabel();
        jTextFieldUtmNorteAgregar = new javax.swing.JTextField();
        jTextFieldUtmEsteAgregar = new javax.swing.JTextField();
        jLabelUtmEste3 = new javax.swing.JLabel();
        jTextFieldSuperficieAgregar = new javax.swing.JTextField();
        jLabelSuperficie3 = new javax.swing.JLabel();
        jButtonAgregarPredio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldProSelec = new javax.swing.JTextField();
        jTextFieldDireccionAgregar = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        jListFinalidad = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jInternalFrameDetalleResultado = new javax.swing.JInternalFrame();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanelUrl1 = new javax.swing.JPanel();
        jButtonTaxOrganismos = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        jListTaxonOrganismos = new javax.swing.JList();
        jButtonTaxReset1 = new javax.swing.JButton();
        jLabelGenero7 = new javax.swing.JLabel();
        jButtonAgregarOrganismo = new javax.swing.JButton();
        jButtonTerminarCarga = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTableDetalleResultado = new javax.swing.JTable();
        jButtonQuitarMuestra1 = new javax.swing.JButton();
        jLabelLogo = new javax.swing.JLabel();
        jSeparatorSolicitudes = new javax.swing.JSeparator();
        jLabelFechaRG = new javax.swing.JLabel();
        jLabelLogo_des = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SIZOIN - Registro General");
        setExtendedState(6);

        jPanelBotonesSolicitud.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBotonesSolicitud.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonAbrirAgregarRegistro.setBackground(new java.awt.Color(204, 255, 204));
        jButtonAbrirAgregarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar32.png"))); // NOI18N
        jButtonAbrirAgregarRegistro.setToolTipText("Agregar un registro de muestras");
        jButtonAbrirAgregarRegistro.setBorderPainted(false);
        jButtonAbrirAgregarRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAbrirAgregarRegistroMouseClicked(evt);
            }
        });

        jButtonAbrirBuscarRegistro.setBackground(new java.awt.Color(204, 204, 255));
        jButtonAbrirBuscarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar32.png"))); // NOI18N
        jButtonAbrirBuscarRegistro.setToolTipText("Buscar, Eliminar y Modificar Registros; Resultados de Muestras");
        jButtonAbrirBuscarRegistro.setBorderPainted(false);
        jButtonAbrirBuscarRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAbrirBuscarRegistroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonAbrirBuscarRegistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonAbrirBuscarRegistroMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesSolicitudLayout = new javax.swing.GroupLayout(jPanelBotonesSolicitud);
        jPanelBotonesSolicitud.setLayout(jPanelBotonesSolicitudLayout);
        jPanelBotonesSolicitudLayout.setHorizontalGroup(
            jPanelBotonesSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesSolicitudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBotonesSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAbrirBuscarRegistro)
                    .addComponent(jButtonAbrirAgregarRegistro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBotonesSolicitudLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAbrirAgregarRegistro, jButtonAbrirBuscarRegistro});

        jPanelBotonesSolicitudLayout.setVerticalGroup(
            jPanelBotonesSolicitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesSolicitudLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jButtonAbrirAgregarRegistro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAbrirBuscarRegistro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPaneSolicitudes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLayeredPaneSolicitudes.setForeground(new java.awt.Color(0, 51, 204));

        jInternalFramePropietario.setClosable(true);
        jInternalFramePropietario.setTitle("Propietarios");
        jInternalFramePropietario.setVisible(false);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanelPropietariosTabla.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPropietariosTabla.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propietarios Registrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        jTablePropietarios.setModel(mod.modelo);
        //jTablePropietarios.getColumnModel().getColumn(0).setPreferredWidth(30);
        //jTablePropietarios.getColumnModel().getColumn(1).setPreferredWidth(100);
        //jTablePropietarios.getColumnModel().getColumn(2).setPreferredWidth(180);
        //jTablePropietarios.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTablePropietarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePropietariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTablePropietarios);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/seleccionar16.png"))); // NOI18N
        jButton2.setToolTipText("Seleccionar Propietario");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelPropietariosTablaLayout = new javax.swing.GroupLayout(jPanelPropietariosTabla);
        jPanelPropietariosTabla.setLayout(jPanelPropietariosTablaLayout);
        jPanelPropietariosTablaLayout.setHorizontalGroup(
            jPanelPropietariosTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPropietariosTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPropietariosTablaLayout.setVerticalGroup(
            jPanelPropietariosTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPropietariosTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPropietariosTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPanePropietarios.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanePropietarios.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPanelBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBuscar.setForeground(new java.awt.Color(51, 51, 255));

        jLabelRif.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelRif.setForeground(new java.awt.Color(0, 51, 255));
        jLabelRif.setText("Rif:");

        jComboBoxRif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "J", "G", "E" }));

        jCheckBoxFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxFiltro.setForeground(new java.awt.Color(0, 51, 255));
        jCheckBoxFiltro.setText("Filtrar");
        jCheckBoxFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxFiltroItemStateChanged(evt);
            }
        });

        jButtonBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar16.png"))); // NOI18N
        jButtonBuscar.setToolTipText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jSeparatorBuscar1.setForeground(new java.awt.Color(0, 0, 255));

        jSeparatorBuscar2.setForeground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout jPanelBuscarLayout = new javax.swing.GroupLayout(jPanelBuscar);
        jPanelBuscar.setLayout(jPanelBuscarLayout);
        jPanelBuscarLayout.setHorizontalGroup(
            jPanelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBuscarLayout.createSequentialGroup()
                        .addComponent(jLabelRif, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxRif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRif, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonBuscar))
                    .addComponent(jCheckBoxFiltro))
                .addContainerGap(111, Short.MAX_VALUE))
            .addComponent(jSeparatorBuscar1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparatorBuscar2)
        );
        jPanelBuscarLayout.setVerticalGroup(
            jPanelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRif)
                    .addComponent(jComboBoxRif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addGap(22, 22, 22)
                .addComponent(jSeparatorBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxFiltro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jSeparatorBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPanePropietarios.addTab("Buscar", jPanelBuscar);

        jPanelAgregar.setBackground(new java.awt.Color(255, 255, 255));

        jPanelBotonesAgregar.setBackground(new java.awt.Color(235, 255, 255));
        jPanelBotonesAgregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonAgregar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAgregar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aceptar16.png"))); // NOI18N
        jButtonAgregar.setToolTipText("Agregar Propietario");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesAgregarLayout = new javax.swing.GroupLayout(jPanelBotonesAgregar);
        jPanelBotonesAgregar.setLayout(jPanelBotonesAgregarLayout);
        jPanelBotonesAgregarLayout.setHorizontalGroup(
            jPanelBotonesAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAgregar)
                .addContainerGap())
        );
        jPanelBotonesAgregarLayout.setVerticalGroup(
            jPanelBotonesAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAgregar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelRifAgregar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelRifAgregar.setForeground(new java.awt.Color(0, 51, 255));
        jLabelRifAgregar.setText("Rif:");

        jComboBoxRifAgregar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "J", "G", "E" }));

        jTextFieldRifAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldRifAgregarKeyTyped(evt);
            }
        });

        jLabelNombreAgregar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNombreAgregar.setForeground(new java.awt.Color(0, 51, 255));
        jLabelNombreAgregar.setText("Nombre:");

        jLabelTelefonoAgregar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelTelefonoAgregar.setForeground(new java.awt.Color(0, 51, 255));
        jLabelTelefonoAgregar.setText("Telefono:");

        jTextFieldTelefonoAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoAgregarKeyTyped(evt);
            }
        });

        jLabelNombreAgregar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNombreAgregar1.setForeground(new java.awt.Color(0, 51, 255));
        jLabelNombreAgregar1.setText("Apellido:");

        javax.swing.GroupLayout jPanelAgregarLayout = new javax.swing.GroupLayout(jPanelAgregar);
        jPanelAgregar.setLayout(jPanelAgregarLayout);
        jPanelAgregarLayout.setHorizontalGroup(
            jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarLayout.createSequentialGroup()
                .addGroup(jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelAgregarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombreAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTelefonoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelAgregarLayout.createSequentialGroup()
                                .addComponent(jLabelRifAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxRifAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldRifAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelNombreAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelAgregarLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jTextFieldNombreAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAgregarLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jTextFieldTelefonoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAgregarLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jTextFieldApellidoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jPanelBotonesAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanelAgregarLayout.setVerticalGroup(
            jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelBotonesAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelAgregarLayout.createSequentialGroup()
                        .addGroup(jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelRifAgregar)
                            .addComponent(jComboBoxRifAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRifAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNombreAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNombreAgregar))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldApellidoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNombreAgregar1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTelefonoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTelefonoAgregar))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPanePropietarios.addTab("Agregar", jPanelAgregar);

        jPanelModificar.setBackground(new java.awt.Color(255, 255, 255));

        jPanelBotonesModificar.setBackground(new java.awt.Color(235, 255, 255));
        jPanelBotonesModificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/papelera16.png"))); // NOI18N
        jButtonEliminar.setToolTipText("Eliminar Propietario");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonLimpiarPropietario.setBackground(new java.awt.Color(255, 255, 255));
        jButtonLimpiarPropietario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonLimpiarPropietario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar16.png"))); // NOI18N
        jButtonLimpiarPropietario.setToolTipText("Cancelar, Limpiar campos");
        jButtonLimpiarPropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarPropietarioActionPerformed(evt);
            }
        });

        jButtonModificar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar16.png"))); // NOI18N
        jButtonModificar.setToolTipText("Modificar Propietario");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotonesModificarLayout = new javax.swing.GroupLayout(jPanelBotonesModificar);
        jPanelBotonesModificar.setLayout(jPanelBotonesModificarLayout);
        jPanelBotonesModificarLayout.setHorizontalGroup(
            jPanelBotonesModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBotonesModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButtonModificar)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonLimpiarPropietario))
                .addContainerGap())
        );
        jPanelBotonesModificarLayout.setVerticalGroup(
            jPanelBotonesModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLimpiarPropietario)
                .addContainerGap())
        );

        jLabelRifModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelRifModificar.setForeground(new java.awt.Color(0, 51, 255));
        jLabelRifModificar.setText("Rif:");

        jLabelNombreModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNombreModificar.setForeground(new java.awt.Color(0, 51, 255));
        jLabelNombreModificar.setText("Nombre:");

        jLabelTelefonoModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelTelefonoModificar.setForeground(new java.awt.Color(0, 51, 255));
        jLabelTelefonoModificar.setText("Telefono:");

        jComboBoxRifModificar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "J", "G", "E" }));

        jTextFieldRifModificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldRifModificarKeyTyped(evt);
            }
        });

        jTextFieldTelefonoModificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoModificarKeyTyped(evt);
            }
        });

        jLabelNombreModificar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNombreModificar1.setForeground(new java.awt.Color(0, 51, 255));
        jLabelNombreModificar1.setText("Apellido:");

        javax.swing.GroupLayout jPanelModificarLayout = new javax.swing.GroupLayout(jPanelModificar);
        jPanelModificar.setLayout(jPanelModificarLayout);
        jPanelModificarLayout.setHorizontalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelRifModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombreModificar)
                    .addComponent(jLabelTelefonoModificar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelNombreModificar1))
                .addGap(4, 4, 4)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNombreModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefonoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelModificarLayout.createSequentialGroup()
                        .addComponent(jComboBoxRifModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRifModificar))
                    .addComponent(jTextFieldApellidoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBotonesModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanelModificarLayout.setVerticalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldRifModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxRifModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelRifModificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreModificar)
                    .addComponent(jTextFieldNombreModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreModificar1)
                    .addComponent(jTextFieldApellidoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefonoModificar)
                    .addComponent(jTextFieldTelefonoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addComponent(jPanelBotonesModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        jTabbedPanePropietarios.addTab("Modificar", jPanelModificar);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPropietariosTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTabbedPanePropietarios, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPanePropietarios, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelPropietariosTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jInternalFramePropietarioLayout = new javax.swing.GroupLayout(jInternalFramePropietario.getContentPane());
        jInternalFramePropietario.getContentPane().setLayout(jInternalFramePropietarioLayout);
        jInternalFramePropietarioLayout.setHorizontalGroup(
            jInternalFramePropietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFramePropietarioLayout.setVerticalGroup(
            jInternalFramePropietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLayeredPaneSolicitudes.add(jInternalFramePropietario);
        jInternalFramePropietario.setBounds(130, 10, 570, 410);

        jInternalFrameRegistroGeneral.setClosable(true);
        jInternalFrameRegistroGeneral.setTitle("Agregar Registro");
        jInternalFrameRegistroGeneral.setVisible(false);
        jInternalFrameRegistroGeneral.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                jInternalFrameRegistroGeneralInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Muestra"));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jComboBoxEdadMeses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Meses--", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabelGenero3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelGenero3.setText("Tipo de Muestra:");

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "H" }));

        jLabelVacunacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelVacunacion.setText("Fecha:");

        jLabelGenero1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelGenero1.setText("Sexo:");

        jLabelEdad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEdad.setText("Edad:");

        jCheckBoxVacunado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBoxVacunado.setText("Vacunado");
        jCheckBoxVacunado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxVacunadoItemStateChanged(evt);
            }
        });

        jComboBoxMuestra.setModel(combomuestra.modelo);
        jComboBoxMuestra.setToolTipText("Tipo de muestra");

        dateChooserComboVacunacion.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dateChooserComboVacunacion.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    dateChooserComboVacunacion.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

    jLabelObservaciones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelObservaciones.setText("Observaciónes:");

    jLabelIdentificacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelIdentificacion.setText("Identificación:");

    jComboBoxEdadYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Años--", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", " " }));
    jComboBoxEdadYear.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBoxEdadYearActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabelObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldObservaciones))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabelGenero3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabelIdentificacion)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldHierro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabelEdad)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxEdadYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxEdadMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabelGenero1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jCheckBoxVacunado)
                    .addGap(18, 18, 18)
                    .addComponent(jLabelVacunacion)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(dateChooserComboVacunacion, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(62, Short.MAX_VALUE))
    );

    jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelGenero3, jLabelObservaciones});

    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGenero3)
                    .addComponent(jComboBoxMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIdentificacion)
                    .addComponent(jTextFieldHierro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEdad)
                    .addComponent(jComboBoxEdadYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxEdadMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGenero1)
                    .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jCheckBoxVacunado)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelVacunacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooserComboVacunacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelObservaciones)
                .addComponent(jTextFieldObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelGenero3, jLabelIdentificacion});

    jPanel5.setBackground(new java.awt.Color(255, 255, 255));

    jLabelGenero2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelGenero2.setText("Busqueda Rápida:");

    jPanelUrl.setBackground(new java.awt.Color(255, 255, 255));
    jPanelUrl.setBorder(javax.swing.BorderFactory.createTitledBorder("Ayuda Taxonómica"));

    javax.swing.GroupLayout jPanelUrlLayout = new javax.swing.GroupLayout(jPanelUrl);
    jPanelUrl.setLayout(jPanelUrlLayout);
    jPanelUrlLayout.setHorizontalGroup(
        jPanelUrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 372, Short.MAX_VALUE)
    );
    jPanelUrlLayout.setVerticalGroup(
        jPanelUrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );

    jComboBoxGrupo.setModel(combogrupo.modelo);
    jComboBoxGrupo.setToolTipText("Grupo etareo del animal");

    jButtonTax.setBackground(new java.awt.Color(255, 255, 255));
    jButtonTax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
    jButtonTax.setToolTipText("Buscar Hijos del Taxon");
    jButtonTax.setPreferredSize(new java.awt.Dimension(32, 32));
    jButtonTax.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonTaxActionPerformed(evt);
        }
    });

    jListTaxon.setModel(tax.modelo);
    jListTaxon.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            jListTaxonValueChanged(evt);
        }
    });
    jScrollPane4.setViewportView(jListTaxon);

    jButtonTaxReset.setBackground(new java.awt.Color(255, 255, 255));
    jButtonTaxReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refrescar.png"))); // NOI18N
    jButtonTaxReset.setToolTipText("Resetear Taxonomía");
    jButtonTaxReset.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonTaxResetActionPerformed(evt);
        }
    });

    jButtonBusquedaRapida.setBackground(new java.awt.Color(255, 255, 255));
    jButtonBusquedaRapida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/encontrar.png"))); // NOI18N
    jButtonBusquedaRapida.setToolTipText("Busqueda Rápida de la Taxonomía");
    jButtonBusquedaRapida.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonBusquedaRapidaActionPerformed(evt);
        }
    });

    jLabelGenero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelGenero.setText("Taxonomia:");

    jButtonAgregarMuestra.setBackground(new java.awt.Color(255, 255, 255));
    jButtonAgregarMuestra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar.png"))); // NOI18N
    jButtonAgregarMuestra.setToolTipText("Agregar Muestra");
    jButtonAgregarMuestra.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonAgregarMuestraActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabelGenero2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jComboBoxGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jButtonBusquedaRapida, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabelGenero)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButtonTaxReset, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButtonTax, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
            .addComponent(jPanelUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButtonAgregarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonBusquedaRapida, jButtonTax, jButtonTaxReset});

    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButtonAgregarMuestra)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelGenero)
                            .addComponent(jComboBoxGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelGenero2)
                            .addComponent(jButtonBusquedaRapida))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jButtonTax, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonTaxReset)))
                    .addComponent(jPanelUrl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap())
    );

    jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonBusquedaRapida, jButtonTax, jButtonTaxReset});

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(33, Short.MAX_VALUE))
    );

    jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel4, jPanel5});

    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addGap(11, 11, 11)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jButtonAgregarRegistro.setBackground(new java.awt.Color(204, 204, 255));
    jButtonAgregarRegistro.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
    jButtonAgregarRegistro.setForeground(new java.awt.Color(0, 51, 255));
    jButtonAgregarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar32.png"))); // NOI18N
    jButtonAgregarRegistro.setText("Guardar");
    jButtonAgregarRegistro.setToolTipText("Guardar el registro");
    jButtonAgregarRegistro.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonAgregarRegistroActionPerformed(evt);
        }
    });

    jPanelDatosGeneralesRG.setBackground(new java.awt.Color(255, 255, 255));
    jPanelDatosGeneralesRG.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));
    jPanelDatosGeneralesRG.setForeground(new java.awt.Color(0, 102, 255));
    jPanelDatosGeneralesRG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

    jButtonPropietario.setBackground(new java.awt.Color(255, 255, 255));
    jButtonPropietario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar16.png"))); // NOI18N
    jButtonPropietario.setToolTipText("Seleccionar propietario guardado");
    jButtonPropietario.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonPropietarioActionPerformed(evt);
        }
    });

    jLabelRemitente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabelRemitente.setText("Remitente:");

    jLabelPredio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabelPredio.setText("Predio:");

    jLabelTipoAnalisis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabelTipoAnalisis.setText("Tipo de Análisis:");

    jTextFieldPredio.setEditable(false);
    jTextFieldPredio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    jButtonPredios.setBackground(new java.awt.Color(255, 255, 255));
    jButtonPredios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar16.png"))); // NOI18N
    jButtonPredios.setToolTipText("Seleccionar predio guardado");
    jButtonPredios.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonPrediosActionPerformed(evt);
        }
    });

    jTextFieldRemitente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    jLabelPropietario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabelPropietario.setText("Propietario:");

    dateChooserComboRecepcion.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    dateChooserComboRecepcion.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

    jLabelFechaRecepcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabelFechaRecepcion.setText("F. Recepción:");
    jLabelFechaRecepcion.setToolTipText("Fecha de Recepción de Muestras");

    jTextFieldPropietario.setEditable(false);
    jTextFieldPropietario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    jComboBoxTipoAnalisis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jComboBoxTipoAnalisis.setModel(combotipoanalisis.modelo);
    jComboBoxTipoAnalisis.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBoxTipoAnalisisActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanelDatosGeneralesRGLayout = new javax.swing.GroupLayout(jPanelDatosGeneralesRG);
    jPanelDatosGeneralesRG.setLayout(jPanelDatosGeneralesRGLayout);
    jPanelDatosGeneralesRGLayout.setHorizontalGroup(
        jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelDatosGeneralesRGLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanelDatosGeneralesRGLayout.createSequentialGroup()
                    .addComponent(jLabelFechaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(dateChooserComboRecepcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanelDatosGeneralesRGLayout.createSequentialGroup()
                    .addComponent(jLabelTipoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxTipoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addGroup(jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabelRemitente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPropietario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanelDatosGeneralesRGLayout.createSequentialGroup()
                    .addComponent(jTextFieldPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonPropietario))
                .addComponent(jTextFieldRemitente))
            .addGap(18, 18, 18)
            .addComponent(jLabelPredio)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jTextFieldPredio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButtonPredios)
            .addContainerGap(222, Short.MAX_VALUE))
    );

    jPanelDatosGeneralesRGLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldPredio, jTextFieldPropietario});

    jPanelDatosGeneralesRGLayout.setVerticalGroup(
        jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelDatosGeneralesRGLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDatosGeneralesRGLayout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPredio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelPredio)))
                .addGroup(jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipoAnalisis)
                    .addComponent(jComboBoxTipoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPropietario)
                    .addComponent(jTextFieldPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jButtonPropietario)
                .addComponent(jButtonPredios))
            .addGap(18, 18, 18)
            .addGroup(jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelFechaRecepcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooserComboRecepcion, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGroup(jPanelDatosGeneralesRGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRemitente)
                    .addComponent(jTextFieldRemitente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );

    jPanelDatosGeneralesRGLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBoxTipoAnalisis, jLabelTipoAnalisis});

    jPanelDatosGeneralesRGLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelPredio, jLabelPropietario, jLabelRemitente, jTextFieldPredio, jTextFieldPropietario, jTextFieldRemitente});

    jPanel6.setBackground(new java.awt.Color(255, 255, 255));
    jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Muestras del Registro"));

    jTableMuestras.setModel(muestras.modelo);
    jTableMuestras.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jTableMuestras.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTableMuestrasMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(jTableMuestras);

    jLabelNMuestras.setForeground(new java.awt.Color(255, 0, 0));
    jLabelNMuestras.setText("Nº Muestras: 0");

    jButtonQuitarMuestra.setBackground(new java.awt.Color(255, 255, 255));
    jButtonQuitarMuestra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar32.png"))); // NOI18N
    jButtonQuitarMuestra.setToolTipText("Quitar Muestra");
    jButtonQuitarMuestra.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonQuitarMuestraActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonQuitarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabelNMuestras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addComponent(jLabelNMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButtonQuitarMuestra))
            .addContainerGap())
    );

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(275, 275, 275)
                    .addComponent(jButtonAgregarRegistro))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelDatosGeneralesRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(39, 39, Short.MAX_VALUE))
    );

    jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel6, jPanel7, jPanelDatosGeneralesRG});

    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanelDatosGeneralesRG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButtonAgregarRegistro)
            .addGap(20, 20, 20))
    );

    jScrollPane1.setViewportView(jPanel3);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 330, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jInternalFrameRegistroGeneralLayout = new javax.swing.GroupLayout(jInternalFrameRegistroGeneral.getContentPane());
    jInternalFrameRegistroGeneral.getContentPane().setLayout(jInternalFrameRegistroGeneralLayout);
    jInternalFrameRegistroGeneralLayout.setHorizontalGroup(
        jInternalFrameRegistroGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jInternalFrameRegistroGeneralLayout.setVerticalGroup(
        jInternalFrameRegistroGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jInternalFrameRegistroGeneralLayout.createSequentialGroup()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    jLayeredPaneSolicitudes.add(jInternalFrameRegistroGeneral);
    jInternalFrameRegistroGeneral.setBounds(10, 10, 1210, 490);

    jInternalFrameAgregarParroquia.setClosable(true);
    jInternalFrameAgregarParroquia.setTitle("Agregar Parroquia");
    jInternalFrameAgregarParroquia.setVisible(false);

    jPanel8.setBackground(new java.awt.Color(255, 255, 255));

    jLabelEstadoAgregarParroquia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabelEstadoAgregarParroquia.setText("Estado:");

    jTextFieldEstadoAgregarParroquia.setEditable(false);

    jLabelMunicipioAgregarParroquia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabelMunicipioAgregarParroquia.setText("Municipio:");

    jTextFieldMunicipioAgregarParroquia.setEditable(false);

    jLabelParroquiaAgregarParroquia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabelParroquiaAgregarParroquia.setText("Parroquia:");

    jButtonAgregarParroquia.setBackground(new java.awt.Color(204, 204, 255));
    jButtonAgregarParroquia.setText("Agregar");
    jButtonAgregarParroquia.setToolTipText("Agregar la parroquia");
    jButtonAgregarParroquia.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonAgregarParroquiaActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addComponent(jLabelEstadoAgregarParroquia)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldEstadoAgregarParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addComponent(jLabelMunicipioAgregarParroquia)
                    .addGap(18, 18, 18)
                    .addComponent(jTextFieldMunicipioAgregarParroquia))
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addComponent(jLabelParroquiaAgregarParroquia)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonAgregarParroquia)
                        .addComponent(jTextFieldParroquiaAgregarParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(34, Short.MAX_VALUE))
    );

    jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelEstadoAgregarParroquia, jLabelMunicipioAgregarParroquia, jLabelParroquiaAgregarParroquia});

    jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldEstadoAgregarParroquia, jTextFieldMunicipioAgregarParroquia, jTextFieldParroquiaAgregarParroquia});

    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelEstadoAgregarParroquia)
                .addComponent(jTextFieldEstadoAgregarParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelMunicipioAgregarParroquia)
                .addComponent(jTextFieldMunicipioAgregarParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelParroquiaAgregarParroquia)
                .addComponent(jTextFieldParroquiaAgregarParroquia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(jButtonAgregarParroquia)
            .addContainerGap(44, Short.MAX_VALUE))
    );

    jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelEstadoAgregarParroquia, jTextFieldEstadoAgregarParroquia});

    jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelMunicipioAgregarParroquia, jLabelParroquiaAgregarParroquia, jTextFieldMunicipioAgregarParroquia, jTextFieldParroquiaAgregarParroquia});

    javax.swing.GroupLayout jInternalFrameAgregarParroquiaLayout = new javax.swing.GroupLayout(jInternalFrameAgregarParroquia.getContentPane());
    jInternalFrameAgregarParroquia.getContentPane().setLayout(jInternalFrameAgregarParroquiaLayout);
    jInternalFrameAgregarParroquiaLayout.setHorizontalGroup(
        jInternalFrameAgregarParroquiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jInternalFrameAgregarParroquiaLayout.setVerticalGroup(
        jInternalFrameAgregarParroquiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    jLayeredPaneSolicitudes.add(jInternalFrameAgregarParroquia);
    jInternalFrameAgregarParroquia.setBounds(370, 210, 270, 200);

    jInternalFrameBuscar.setClosable(true);
    jInternalFrameBuscar.setTitle("Buscar Registro");
    jInternalFrameBuscar.setVisible(true);

    jPanelBucar.setBackground(new java.awt.Color(255, 255, 255));

    jPanelOpcBus.setBackground(new java.awt.Color(255, 255, 255));
    jPanelOpcBus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones de Búsqueda", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 255))); // NOI18N

    jComboBoxTipoAnalisisBuscar.setModel(combotipoanalisisbuscar.modelo);
    jComboBoxTipoAnalisisBuscar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBoxTipoAnalisisBuscarActionPerformed(evt);
        }
    });

    jLabelAnalisisBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabelAnalisisBuscar.setText("Tipo de Análisis:");

    jLabelAnalisisBuscarPor.setText("Buscar por:");

    jComboBoxBuscarPor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--", "Nº Protocolo", "Productor", "Predio", "Remitente", "Dirección", "Fecha", "Todos" }));
    jComboBoxBuscarPor.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBoxBuscarPorActionPerformed(evt);
        }
    });

    jButtonBuscarPor.setBackground(new java.awt.Color(204, 255, 255));
    jButtonBuscarPor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar32.png"))); // NOI18N
    jButtonBuscarPor.setToolTipText("Buscar");
    jButtonBuscarPor.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonBuscarPorActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanelOpcBusLayout = new javax.swing.GroupLayout(jPanelOpcBus);
    jPanelOpcBus.setLayout(jPanelOpcBusLayout);
    jPanelOpcBusLayout.setHorizontalGroup(
        jPanelOpcBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelOpcBusLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabelAnalisisBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jComboBoxTipoAnalisisBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(52, 52, 52)
            .addComponent(jLabelAnalisisBuscarPor)
            .addGap(18, 18, 18)
            .addComponent(jComboBoxBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButtonBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(19, Short.MAX_VALUE))
    );
    jPanelOpcBusLayout.setVerticalGroup(
        jPanelOpcBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelOpcBusLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelOpcBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButtonBuscarPor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelOpcBusLayout.createSequentialGroup()
                    .addGroup(jPanelOpcBusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelAnalisisBuscarPor)
                        .addComponent(jComboBoxTipoAnalisisBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelAnalisisBuscar))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );

    jTableBuscar.setModel(buscar.modelo);
    jTableBuscar.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jTableBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTableBuscarMouseClicked(evt);
        }
    });
    jScrollPane5.setViewportView(jTableBuscar);

    jButtonEliminarRegistro.setBackground(new java.awt.Color(255, 153, 153));
    jButtonEliminarRegistro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jButtonEliminarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/papelera32.png"))); // NOI18N
    jButtonEliminarRegistro.setToolTipText("Borrar registro seleccionado");
    jButtonEliminarRegistro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jButtonEliminarRegistro.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonEliminarRegistroActionPerformed(evt);
        }
    });

    jButtonModificarRegistro.setBackground(new java.awt.Color(204, 255, 204));
    jButtonModificarRegistro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jButtonModificarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar32.png"))); // NOI18N
    jButtonModificarRegistro.setToolTipText("Modificar el registro seleccionado");
    jButtonModificarRegistro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jButtonModificarRegistro.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonModificarRegistroActionPerformed(evt);
        }
    });

    jButtonResultado.setBackground(new java.awt.Color(153, 255, 255));
    jButtonResultado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jButtonResultado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/archivo_lleno32.png"))); // NOI18N
    jButtonResultado.setToolTipText("Resultados de las muestras");
    jButtonResultado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jButtonResultado.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonResultadoActionPerformed(evt);
        }
    });

    jButtonImprimir.setBackground(new java.awt.Color(153, 255, 255));
    jButtonImprimir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/imprimir32.png"))); // NOI18N
    jButtonImprimir.setToolTipText("Resultados de las muestras");
    jButtonImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonImprimirActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanelBucarLayout = new javax.swing.GroupLayout(jPanelBucar);
    jPanelBucar.setLayout(jPanelBucarLayout);
    jPanelBucarLayout.setHorizontalGroup(
        jPanelBucarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelBucarLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelBucarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBucarLayout.createSequentialGroup()
                    .addComponent(jPanelOpcBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 507, Short.MAX_VALUE))
                .addGroup(jPanelBucarLayout.createSequentialGroup()
                    .addComponent(jScrollPane5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanelBucarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonEliminarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonModificarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonImprimir))))
            .addContainerGap())
    );

    jPanelBucarLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonEliminarRegistro, jButtonImprimir, jButtonModificarRegistro, jButtonResultado});

    jPanelBucarLayout.setVerticalGroup(
        jPanelBucarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelBucarLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanelOpcBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelBucarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelBucarLayout.createSequentialGroup()
                    .addComponent(jButtonEliminarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonModificarRegistro)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonResultado)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonImprimir)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanelBucarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonEliminarRegistro, jButtonModificarRegistro, jButtonResultado});

    javax.swing.GroupLayout jInternalFrameBuscarLayout = new javax.swing.GroupLayout(jInternalFrameBuscar.getContentPane());
    jInternalFrameBuscar.getContentPane().setLayout(jInternalFrameBuscarLayout);
    jInternalFrameBuscarLayout.setHorizontalGroup(
        jInternalFrameBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jInternalFrameBuscarLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanelBucar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    jInternalFrameBuscarLayout.setVerticalGroup(
        jInternalFrameBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameBuscarLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanelBucar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    jLayeredPaneSolicitudes.add(jInternalFrameBuscar);
    jInternalFrameBuscar.setBounds(10, 10, 1190, 490);

    jInternalFramePredios.setClosable(true);
    jInternalFramePredios.setTitle("Predios");
    jInternalFramePredios.setVisible(false);

    jPanel11.setBackground(new java.awt.Color(204, 255, 255));

    jButtonAbrirAgregarPredios.setBackground(new java.awt.Color(255, 255, 255));
    jButtonAbrirAgregarPredios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar16.png"))); // NOI18N
    jButtonAbrirAgregarPredios.setToolTipText("Agregar un predio");
    jButtonAbrirAgregarPredios.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonAbrirAgregarPrediosActionPerformed(evt);
        }
    });

    jButtonAbrirModificarPredios.setBackground(new java.awt.Color(255, 255, 255));
    jButtonAbrirModificarPredios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar16.png"))); // NOI18N
    jButtonAbrirModificarPredios.setToolTipText("Modificar el predio seleccionado");
    jButtonAbrirModificarPredios.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonAbrirModificarPrediosActionPerformed(evt);
        }
    });

    jButtonPredio.setBackground(new java.awt.Color(255, 255, 255));
    jButtonPredio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/seleccionar16.png"))); // NOI18N
    jButtonPredio.setToolTipText("Seleccionar este predio para el registro");
    jButtonPredio.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonPredioMouseClicked(evt);
        }
    });

    jPanelPrediosTabla.setBackground(new java.awt.Color(255, 255, 255));
    jPanelPrediosTabla.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Predios Registrados por productor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 255))); // NOI18N

    jTablePredios.setModel(pre.modelo);
    jTablePredios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jTablePredios.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTablePrediosMouseClicked(evt);
        }
    });
    jScrollPane6.setViewportView(jTablePredios);

    javax.swing.GroupLayout jPanelPrediosTablaLayout = new javax.swing.GroupLayout(jPanelPrediosTabla);
    jPanelPrediosTabla.setLayout(jPanelPrediosTablaLayout);
    jPanelPrediosTablaLayout.setHorizontalGroup(
        jPanelPrediosTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelPrediosTablaLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanelPrediosTablaLayout.setVerticalGroup(
        jPanelPrediosTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelPrediosTablaLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanelPrediosTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButtonPredio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAbrirAgregarPredios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAbrirModificarPredios))
            .addContainerGap())
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addComponent(jButtonPredio)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButtonAbrirAgregarPredios)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButtonAbrirModificarPredios))
                .addComponent(jPanelPrediosTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    javax.swing.GroupLayout jInternalFramePrediosLayout = new javax.swing.GroupLayout(jInternalFramePredios.getContentPane());
    jInternalFramePredios.getContentPane().setLayout(jInternalFramePrediosLayout);
    jInternalFramePrediosLayout.setHorizontalGroup(
        jInternalFramePrediosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jInternalFramePrediosLayout.setVerticalGroup(
        jInternalFramePrediosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jInternalFramePrediosLayout.createSequentialGroup()
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    jLayeredPaneSolicitudes.add(jInternalFramePredios);
    jInternalFramePredios.setBounds(20, 110, 730, 200);

    jInternalFrameBrucelosisR.setClosable(true);
    jInternalFrameBrucelosisR.setTitle("Ingresar Resultados - Análisis de Brucelosis");
    jInternalFrameBrucelosisR.setVisible(false);

    jPanelBrucelosisR.setBackground(new java.awt.Color(255, 255, 255));

    jLabelResultadoBrucelosis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabelResultadoBrucelosis.setForeground(new java.awt.Color(51, 51, 255));
    jLabelResultadoBrucelosis.setText("Resultado");

    jButtonResultadoBru.setBackground(new java.awt.Color(153, 255, 255));
    jButtonResultadoBru.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonResultadoBru.setText("Guardar");
    jButtonResultadoBru.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonResultadoBruMouseClicked(evt);
        }
    });

    jTableMuestrasbru.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jTableMuestrasbru.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTableMuestrasbruMouseClicked(evt);
        }
    });
    jScrollPane7.setViewportView(jTableMuestrasbru);

    jLabel2.setText("Analistas:");

    jListAnalistas.setModel(per.modelo);
    jListAnalistas.setToolTipText("Selecciones varios analistas pisando la tecla Ctrl");
    jListAnalistas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            jListAnalistasValueChanged(evt);
        }
    });
    jScrollPane13.setViewportView(jListAnalistas);

    dateChooserComboProcesamiento.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    dateChooserComboProcesamiento.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

    jLabel3.setText("F. Procesamiento:");

    jButtonCargar.setBackground(new java.awt.Color(255, 255, 255));
    jButtonCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
    jButtonCargar.setToolTipText("Cargar Organismos identificados");
    jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonCargarActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanelBrucelosisRLayout = new javax.swing.GroupLayout(jPanelBrucelosisR);
    jPanelBrucelosisR.setLayout(jPanelBrucelosisRLayout);
    jPanelBrucelosisRLayout.setHorizontalGroup(
        jPanelBrucelosisRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator2)
        .addGroup(jPanelBrucelosisRLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelBrucelosisRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBrucelosisRLayout.createSequentialGroup()
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(dateChooserComboProcesamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonResultadoBru))
                .addGroup(jPanelBrucelosisRLayout.createSequentialGroup()
                    .addComponent(jLabelResultadoBrucelosis)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBrucelosisRLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(44, 44, 44))
    );
    jPanelBrucelosisRLayout.setVerticalGroup(
        jPanelBrucelosisRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelBrucelosisRLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabelResultadoBrucelosis)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelBrucelosisRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3)
                .addComponent(jButtonResultadoBru)
                .addComponent(dateChooserComboProcesamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPanelBrucelosisRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGroup(jPanelBrucelosisRLayout.createSequentialGroup()
                    .addComponent(jButtonCargar)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );

    javax.swing.GroupLayout jInternalFrameBrucelosisRLayout = new javax.swing.GroupLayout(jInternalFrameBrucelosisR.getContentPane());
    jInternalFrameBrucelosisR.getContentPane().setLayout(jInternalFrameBrucelosisRLayout);
    jInternalFrameBrucelosisRLayout.setHorizontalGroup(
        jInternalFrameBrucelosisRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanelBrucelosisR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jInternalFrameBrucelosisRLayout.setVerticalGroup(
        jInternalFrameBrucelosisRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jInternalFrameBrucelosisRLayout.createSequentialGroup()
            .addComponent(jPanelBrucelosisR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 10, Short.MAX_VALUE))
    );

    jLayeredPaneSolicitudes.add(jInternalFrameBrucelosisR);
    jInternalFrameBrucelosisR.setBounds(20, 40, 970, 380);

    jInternalFramePrediosAsociados.setClosable(true);
    jInternalFramePrediosAsociados.setTitle("Predios Asociados al Productor");
    jInternalFramePrediosAsociados.setVisible(false);
    jInternalFramePrediosAsociados.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
        public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
        }
        public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            jInternalFramePrediosAsociadosInternalFrameClosed(evt);
        }
        public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
        }
        public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
        }
        public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
        }
        public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
        }
        public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
        }
    });

    jPanel21.setBackground(new java.awt.Color(204, 255, 255));

    jLabelPredio3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelPredio3.setText("Predio:");

    jLabelFinalidad3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelFinalidad3.setText("Finalidad:");

    jLabelEstado3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelEstado3.setText("Estado:");
    jLabelEstado3.setPreferredSize(new java.awt.Dimension(6, 20));

    jComboBoxEstadoAgregar.setModel(comboestado.modelo);
    jComboBoxEstadoAgregar.setPreferredSize(new java.awt.Dimension(6, 20));

    jLabelMunicipio3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelMunicipio3.setText("Municipio:");
    jLabelMunicipio3.setPreferredSize(new java.awt.Dimension(6, 20));

    jComboBoxMunicipioAgregar.setModel(combomunicipio.modelo);
    jComboBoxMunicipioAgregar.setPreferredSize(new java.awt.Dimension(6, 20));

    jLabelParroquia3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelParroquia3.setText("Parroquia:");
    jLabelParroquia3.setPreferredSize(new java.awt.Dimension(6, 20));

    jComboBoxParroquiaAgregar.setModel(comboparroquia.modelo);
    jComboBoxParroquiaAgregar.setPreferredSize(new java.awt.Dimension(6, 20));

    jLabelSector3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelSector3.setText("Dirección:");
    jLabelSector3.setPreferredSize(new java.awt.Dimension(6, 20));

    jButtonAbrirParroquiaAgregar.setBackground(new java.awt.Color(255, 255, 255));
    jButtonAbrirParroquiaAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar16.png"))); // NOI18N
    jButtonAbrirParroquiaAgregar.setToolTipText("Agregar Parroquia");
    jButtonAbrirParroquiaAgregar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonAbrirParroquiaAgregarActionPerformed(evt);
        }
    });

    jLabelUTM3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelUTM3.setText("Coordenadas UTM REGVEN 19:");

    jPanel23.setBackground(new java.awt.Color(255, 255, 255));
    jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jLabelUtmNorte3.setText("N:");

    jLabelUtmEste3.setText("E:");

    javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
    jPanel23.setLayout(jPanel23Layout);
    jPanel23Layout.setHorizontalGroup(
        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel23Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addComponent(jLabelUtmNorte3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldUtmNorteAgregar))
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addComponent(jLabelUtmEste3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldUtmEsteAgregar)))
            .addContainerGap())
    );
    jPanel23Layout.setVerticalGroup(
        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel23Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelUtmNorte3)
                .addComponent(jTextFieldUtmNorteAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelUtmEste3)
                .addComponent(jTextFieldUtmEsteAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabelSuperficie3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelSuperficie3.setText("Superficie:");

    jButtonAgregarPredio.setBackground(new java.awt.Color(255, 255, 255));
    jButtonAgregarPredio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aceptar16.png"))); // NOI18N
    jButtonAgregarPredio.setText("Agregar");
    jButtonAgregarPredio.setToolTipText("Guardar datos de predio");
    jButtonAgregarPredio.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonAgregarPredioActionPerformed(evt);
        }
    });

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel1.setText("Propietario:");

    jTextFieldProSelec.setEditable(false);
    jTextFieldProSelec.setForeground(new java.awt.Color(51, 51, 255));

    jTextFieldDireccionAgregar.setToolTipText("calle, sector, punto de referencia");

    jListFinalidad.setModel(fin.modelo);
    jScrollPane12.setViewportView(jListFinalidad);

    jButton1.setBackground(new java.awt.Color(255, 255, 255));
    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/encontrar.png"))); // NOI18N
    jButton1.setToolTipText("Busca automaticamente las coordenadas de la dirección ingresada");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
    jPanel21.setLayout(jPanel21Layout);
    jPanel21Layout.setHorizontalGroup(
        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel21Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelFinalidad3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelPredio3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldPredioAgregar)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelUTM3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton1)))
            .addGap(18, 18, 18)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelSector3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelParroquia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelMunicipio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelEstado3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxParroquiaAgregar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldDireccionAgregar))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonAbrirParroquiaAgregar)
                            .addGap(38, 38, 38))
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxMunicipioAgregar, javax.swing.GroupLayout.Alignment.LEADING, 0, 132, Short.MAX_VALUE)
                                .addComponent(jComboBoxEstadoAgregar, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAgregarPredio))))
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldProSelec, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabelSuperficie3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSuperficieAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 99, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel21Layout.setVerticalGroup(
        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel21Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelEstado3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxEstadoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAgregarPredio))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMunicipio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxMunicipioAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonAbrirParroquiaAgregar)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelParroquia3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxParroquiaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSector3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldDireccionAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSuperficie3)
                        .addComponent(jTextFieldSuperficieAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldProSelec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelPredio3)
                        .addComponent(jTextFieldPredioAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelFinalidad3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addComponent(jLabelUTM3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(15, 15, 15)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(0, 11, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jInternalFramePrediosAsociadosLayout = new javax.swing.GroupLayout(jInternalFramePrediosAsociados.getContentPane());
    jInternalFramePrediosAsociados.getContentPane().setLayout(jInternalFramePrediosAsociadosLayout);
    jInternalFramePrediosAsociadosLayout.setHorizontalGroup(
        jInternalFramePrediosAsociadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jInternalFramePrediosAsociadosLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jInternalFramePrediosAsociadosLayout.setVerticalGroup(
        jInternalFramePrediosAsociadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jInternalFramePrediosAsociadosLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(15, Short.MAX_VALUE))
    );

    jLayeredPaneSolicitudes.add(jInternalFramePrediosAsociados);
    jInternalFramePrediosAsociados.setBounds(10, 10, 640, 340);

    jInternalFrameDetalleResultado.setClosable(true);
    jInternalFrameDetalleResultado.setTitle("Detalles del Resultado");
    jInternalFrameDetalleResultado.setVisible(false);

    jPanel10.setBackground(new java.awt.Color(255, 255, 255));

    jPanel12.setBackground(new java.awt.Color(255, 255, 255));
    jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Organismo Identificado"));

    jPanel14.setBackground(new java.awt.Color(255, 255, 255));

    jPanelUrl1.setBackground(new java.awt.Color(255, 255, 255));
    jPanelUrl1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ayuda Taxonómica"));

    javax.swing.GroupLayout jPanelUrl1Layout = new javax.swing.GroupLayout(jPanelUrl1);
    jPanelUrl1.setLayout(jPanelUrl1Layout);
    jPanelUrl1Layout.setHorizontalGroup(
        jPanelUrl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 372, Short.MAX_VALUE)
    );
    jPanelUrl1Layout.setVerticalGroup(
        jPanelUrl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 69, Short.MAX_VALUE)
    );

    jButtonTaxOrganismos.setBackground(new java.awt.Color(255, 255, 255));
    jButtonTaxOrganismos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
    jButtonTaxOrganismos.setToolTipText("Buscar Hijos del Taxon");
    jButtonTaxOrganismos.setPreferredSize(new java.awt.Dimension(32, 32));
    jButtonTaxOrganismos.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonTaxOrganismosActionPerformed(evt);
        }
    });

    jListTaxonOrganismos.setModel(tax.modelo);
    jListTaxonOrganismos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            jListTaxonOrganismosValueChanged(evt);
        }
    });
    jScrollPane14.setViewportView(jListTaxonOrganismos);

    jButtonTaxReset1.setBackground(new java.awt.Color(255, 255, 255));
    jButtonTaxReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refrescar.png"))); // NOI18N
    jButtonTaxReset1.setToolTipText("Resetear Taxonomía");
    jButtonTaxReset1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonTaxReset1ActionPerformed(evt);
        }
    });

    jLabelGenero7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabelGenero7.setText("Taxonomia:");

    jButtonAgregarOrganismo.setBackground(new java.awt.Color(255, 255, 255));
    jButtonAgregarOrganismo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar.png"))); // NOI18N
    jButtonAgregarOrganismo.setToolTipText("Agregar Muestra");
    jButtonAgregarOrganismo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonAgregarOrganismoActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
    jPanel14.setLayout(jPanel14Layout);
    jPanel14Layout.setHorizontalGroup(
        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel14Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabelGenero7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButtonTaxReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButtonTaxOrganismos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelUrl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButtonAgregarOrganismo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(332, 332, 332))
    );
    jPanel14Layout.setVerticalGroup(
        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel14Layout.createSequentialGroup()
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButtonAgregarOrganismo)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelGenero7)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jButtonTaxOrganismos, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonTaxReset1)))
                    .addComponent(jPanelUrl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 22, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
    jPanel12.setLayout(jPanel12Layout);
    jPanel12Layout.setHorizontalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addContainerGap(12, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanel12Layout.setVerticalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addGap(11, 11, 11)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jButtonTerminarCarga.setBackground(new java.awt.Color(204, 204, 255));
    jButtonTerminarCarga.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonTerminarCarga.setForeground(new java.awt.Color(0, 51, 255));
    jButtonTerminarCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/seleccionar.png"))); // NOI18N
    jButtonTerminarCarga.setText("Listo");
    jButtonTerminarCarga.setToolTipText("Terminar Carga de los organismos");
    jButtonTerminarCarga.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonTerminarCargaActionPerformed(evt);
        }
    });

    jPanel15.setBackground(new java.awt.Color(255, 255, 255));
    jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles del Resultado - Organismos Identificados"));

    jTableDetalleResultado.setModel(detalle.modelo);
    jTableDetalleResultado.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jTableDetalleResultado.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTableDetalleResultadoMouseClicked(evt);
        }
    });
    jScrollPane15.setViewportView(jTableDetalleResultado);

    jButtonQuitarMuestra1.setBackground(new java.awt.Color(255, 255, 255));
    jButtonQuitarMuestra1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar32.png"))); // NOI18N
    jButtonQuitarMuestra1.setToolTipText("Quitar Muestra");
    jButtonQuitarMuestra1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonQuitarMuestra1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
    jPanel15.setLayout(jPanel15Layout);
    jPanel15Layout.setHorizontalGroup(
        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel15Layout.createSequentialGroup()
            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButtonQuitarMuestra1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    jPanel15Layout.setVerticalGroup(
        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel15Layout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButtonQuitarMuestra1))
            .addContainerGap())
    );

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTerminarCarga)))
            .addContainerGap(42, Short.MAX_VALUE))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButtonTerminarCarga)))
            .addContainerGap())
    );

    javax.swing.GroupLayout jInternalFrameDetalleResultadoLayout = new javax.swing.GroupLayout(jInternalFrameDetalleResultado.getContentPane());
    jInternalFrameDetalleResultado.getContentPane().setLayout(jInternalFrameDetalleResultadoLayout);
    jInternalFrameDetalleResultadoLayout.setHorizontalGroup(
        jInternalFrameDetalleResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
        .addGroup(jInternalFrameDetalleResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameDetalleResultadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );
    jInternalFrameDetalleResultadoLayout.setVerticalGroup(
        jInternalFrameDetalleResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
        .addGroup(jInternalFrameDetalleResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameDetalleResultadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );

    jLayeredPaneSolicitudes.add(jInternalFrameDetalleResultado);
    jInternalFrameDetalleResultado.setBounds(10, 10, 830, 470);

    jLabelFechaRG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFechaRG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFechaRG.setText("jLabel1");

    jLabelLogo_des.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
    jLabelLogo_des.setForeground(new java.awt.Color(255, 102, 102));
    jLabelLogo_des.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanelBotonesSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                    .addComponent(jLayeredPaneSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 1227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelFechaRG, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabelLogo_des, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        .addComponent(jSeparatorSolicitudes)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabelFechaRG, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabelLogo_des, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparatorSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLayeredPaneSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(jPanelBotonesSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents
    private void iniciar() {
        /*Se instancian las clases*/
        miVentanaRegistro = new RegistroGeneral();
        miVentanaRegistro.setVisible(true);
    }

    private void iniciarPropietario() {
        /*Se instancian las clases*/
        miLogica = new LogicaPropietario();
        miCoordinador = new CoordinadorPropietario();
        /*Se establecen las relaciones entre clases*/
        this.setCoordinadorRegistro(miCoordinador);
        miLogica.setCoordinador(miCoordinador);
        /*Se establecen relaciones con la clase coordinador*/
        miCoordinador.setMiVentanaRegistro(miVentanaRegistro);
        miCoordinador.setMiLogica(miLogica);
    }

    private void iniciarResultado() {
        /*Se instancian las clases*/
        miLogicaResultado = new LogicaResultado();
        miCoordinadorResultado = new CoordinadorResultado();
        /*Se establecen las relaciones entre clases*/
        this.setCoordinadorBrucelosis(miCoordinadorResultado);
        miLogicaResultado.setCoordinador(miCoordinadorResultado);
        /*Se establecen relaciones con la clase coordinador*/
        miCoordinadorResultado.setMiVentanaRegistro(miVentanaRegistro);
        miCoordinadorResultado.setMiLogica(miLogicaResultado);
    }

    private void iniciarParroquia() {
        /*Se instancian las clases*/
        miLogicaParroquia = new LogicaParroquia();
        miCoordinadorParroquia = new CoordinadorParroquia();
        /*Se establecen las relaciones entre clases*/
        this.setCoordinadorParroquia(miCoordinadorParroquia);
        miLogicaParroquia.setCoordinador(miCoordinadorParroquia);
        /*Se establecen relaciones con la clase coordinador*/
        miCoordinadorParroquia.setMiVentanaRegistro(miVentanaRegistro);
        miCoordinadorParroquia.setMiLogica(miLogicaParroquia);
    }

    private void iniciarPredio() {
        /*Se instancian las clases*/
        miLogicaPredio = new LogicaPredio();
        miCoordinadorPredio = new CoordinadorPredio();
        /*Se establecen las relaciones entre clases*/
        this.setCoordinadorPredio(miCoordinadorPredio);
        miLogicaPredio.setCoordinador(miCoordinadorPredio);
        /*Se establecen relaciones con la clase coordinador*/
        miCoordinadorPredio.setMiVentanaRegistro(miVentanaRegistro);
        miCoordinadorPredio.setMiLogica(miLogicaPredio);
    }

    private void iniciarRegistro() {
        /*Se instancian las clases*/
        miLogicaRegistroGeneral = new LogicaRegistroGeneral();
        miCoordinadorRegistroGeneral = new CoordinadorRegistroGeneral();
        /*Se establecen las relaciones entre clases*/
        this.setCoordinadorRegistroGeneral(miCoordinadorRegistroGeneral);
        miLogicaRegistroGeneral.setCoordinador(miCoordinadorRegistroGeneral);
        /*Se establecen relaciones con la clase coordinador*/
        miCoordinadorRegistroGeneral.setMiVentanaRegistro(miVentanaRegistro);
        miCoordinadorRegistroGeneral.setMiLogica(miLogicaRegistroGeneral);
    }

    public void setCoordinadorRegistro(CoordinadorPropietario miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public void setCoordinadorRegistroGeneral(CoordinadorRegistroGeneral miCoordinador) {
        this.miCoordinadorRegistroGeneral = miCoordinador;
    }

    public void setCoordinadorPredio(CoordinadorPredio miCoordinadorPredio) {
        this.miCoordinadorPredio = miCoordinadorPredio;
    }

    public void setCoordinadorParroquia(CoordinadorParroquia miCoordinadorParroquia) {
        this.miCoordinadorParroquia = miCoordinadorParroquia;
    }

    public void setCoordinadorBrucelosis(CoordinadorResultado miCoordinadorBrucelosis) {
        this.miCoordinadorResultado = miCoordinadorBrucelosis;
    }

    public void limpiar_propietario() {
        jComboBoxRifModificar.setSelectedItem("V");
        jTextFieldRifAgregar.setText(null);
        jTextFieldNombreAgregar.setText(null);
        jTextFieldApellidoAgregar.setText(null);
        jTextFieldTelefonoAgregar.setText(null);
        jTextFieldRifModificar.setEnabled(false);
        jTextFieldNombreModificar.setEnabled(false);
        jTextFieldApellidoModificar.setEnabled(false);
        jTextFieldTelefonoModificar.setEnabled(false);
        jComboBoxRifModificar.setEnabled(false);
        idusact = 0;
    }

    public void limpiar_parroquia() {
        jTextFieldParroquiaAgregarParroquia.setText(null);
    }

    public void limpiar_predio() {
        jTextFieldPredioAgregar.setText(null);
        jTextFieldSuperficieAgregar.setText(null);
        jTextFieldUtmNorteAgregar.setText(null);
        jTextFieldUtmEsteAgregar.setText(null);
        //limpiamos los jComboBox
        jComboBoxParroquiaAgregar.setSelectedIndex(0);
        jComboBoxMunicipioAgregar.setSelectedIndex(0);
        jComboBoxEstadoAgregar.setSelectedIndex(0);
        //limpio los jCheckBox
    }

    public void limpiar_registro() {
        jTextFieldPropietario.setText(null);
        jTextFieldRemitente.setText(null);
        jTextFieldPredio.setText(null);
        jTextFieldHierro.setText(null);
        jTextFieldObservaciones.setText(null);
        jComboBoxTipoAnalisis.setSelectedIndex(0);
        jComboBoxGrupo.setSelectedIndex(0);
        jComboBoxMuestra.setSelectedIndex(0);
        jComboBoxSexo.setSelectedIndex(0);
        jComboBoxEdadYear.setSelectedIndex(0);
        jComboBoxEdadMeses.setSelectedIndex(0);
        jCheckBoxVacunado.setSelected(false);
        int tabla = jTableMuestras.getRowCount();
        for (int j = 0; j < tabla; j++) {
            muestras.modelo.removeRow(0);
        }
        n_muestras = 0;
        jLabelNMuestras.setText("Nº Muestras: 0");
        tax.consultalist(config.lista_taxon_inicial, "tax.tsn", "tax.nombre_completo", "tax.rango_id", "ran.rango");//cargamos la consulta para cargar la especie
        tax.LLenarLista();
        label.setURL("");
        label.setText("");
    }


    private void jTablePropietariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePropietariosMouseClicked
        int fil = jTablePropietarios.getSelectedRow();//obtiene la fila seleccionada
        ModiId = jTablePropietarios.getModel().getValueAt(fil, 0).toString();//obtiene el id del rif en la fila seleccionada
        ModiTip = jTablePropietarios.getModel().getValueAt(fil, 1).toString();//obtiene el tipo de rif y rif de la fila seleccionada
        Moditiprif = ModiTip.substring(0, 1); //extraigo el tipo de rif del string
        int sizestring = ModiTip.length();// obtenemos el tamaño del string para porder extraer el rif
        rifextra = ModiTip.substring(1, sizestring);//extraigo el rif del string
        System.out.println(sizestring); //muestra para prueba el tamaño del string rif
        jTextFieldRifModificar.setText(rifextra);//lanza a Jtextfield el rif seleccionado
        jTextFieldNombreModificar.setText(jTablePropietarios.getModel().getValueAt(fil, 2).toString());
        jTextFieldApellidoModificar.setText(jTablePropietarios.getModel().getValueAt(fil, 3).toString());//lanza a Jtextfield la clave del rif seleccionad
        jTextFieldTelefonoModificar.setText(jTablePropietarios.getModel().getValueAt(fil, 4).toString());
        jComboBoxRifModificar.setSelectedItem(Moditiprif);//Seleccionar en combo el telf de rif del rif seleccionado
        idusact = Integer.parseInt(ModiId); //asigna el id del rif seleccionado
        jTextFieldRifModificar.setEnabled(true); // habilita el Jtextfield del rif
        jTextFieldNombreModificar.setEnabled(true); //habilita el Jtextfield de la clave del rif
        jTextFieldApellidoModificar.setEnabled(true); //habilita el Jtextfield de la clave del rif
        jTextFieldTelefonoModificar.setEnabled(true); //habilita el Jtextfield de la clave del rif
        jComboBoxRifModificar.setEnabled(true); //habilita el box del telf de rif                
    }//GEN-LAST:event_jTablePropietariosMouseClicked

    private void jCheckBoxFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxFiltroItemStateChanged
        boolean b = jCheckBoxFiltro.isSelected();// chekeamos si se va a filtrar en la tabla
        if (b == false) {
            mod.columnastabla(4, config.tabla_consulta_propietario);
            mod.LlenarTabla();
        }
    }//GEN-LAST:event_jCheckBoxFiltroItemStateChanged

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            this.iniciarPropietario();
            PropietarioBO miPropietario = new PropietarioBO();
            miPropietario.setRif(jComboBoxRif.getSelectedItem().toString() + jTextFieldRif.getText());
            if (miCoordinador.buscarPropietario(miPropietario) == true) {
                limpiar_propietario();
                boolean b = jCheckBoxFiltro.isSelected();// chekeamos si se va a filtrar en la tabla
                if (b == true) {
                    mod.columnastabla(4, config.tabla_consulta_propietario_buscar(miPropietario.getRif()));
                    mod.LlenarTabla();
                }
            } else {
                mod.columnastabla(4, config.tabla_consulta_propietario);
                mod.LlenarTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en la busqueda", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        try {
            //this.iniciar();
            this.iniciarPropietario();
            PropietarioBO miPropietario = new PropietarioBO();
            miPropietario.setRif(jComboBoxRifAgregar.getSelectedItem().toString() + jTextFieldRifAgregar.getText());
            miPropietario.setNombre(jTextFieldNombreAgregar.getText());
            miPropietario.setApellido(jTextFieldApellidoAgregar.getText());
            miPropietario.setTelefono(jTextFieldTelefonoAgregar.getText());
            if (miCoordinador.registrarPropietario(miPropietario) == true) {
                limpiar_propietario();
                mod.LlenarTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        if (idusact > 0) {
            this.iniciarPropietario();
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "Desea eliminar el propietario seleccionado?", "Confirmacion",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_NO_OPTION) {
                if (miCoordinador.eliminarPropietario(idusact) == true) {
                    limpiar_propietario();
                    mod.LlenarTabla();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione en la tabla el propietario a eliminar", "Informacion", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonLimpiarPropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarPropietarioActionPerformed
        limpiar_propietario();
    }//GEN-LAST:event_jButtonLimpiarPropietarioActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        try {
            //this.iniciar();
            this.iniciarPropietario();
            PropietarioBO miPropietario = new PropietarioBO();
            miPropietario.setRif(jComboBoxRifModificar.getSelectedItem().toString() + jTextFieldRifModificar.getText());
            miPropietario.setNombre(jTextFieldNombreModificar.getText());
            miPropietario.setApellido(jTextFieldApellidoModificar.getText());
            miPropietario.setTelefono(jTextFieldTelefonoModificar.getText());
            miPropietario.setId_propietario(idusact);
            if (miCoordinador.modificarPropietario(miPropietario) == true) {
                limpiar_propietario();
                mod.LlenarTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        jTextFieldPropietario.setText(jTextFieldNombreModificar.getText());
        jTextFieldProSelec.setText(jTextFieldNombreModificar.getText());
        jInternalFramePropietario.setVisible(false);
    }//GEN-LAST:event_jButton2MouseClicked

    public static Date Fecha(DateChooserCombo jDatefecha) {
        Date date = jDatefecha.getCurrent().getTime();
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        return fecha;
    }


    private void jComboBoxTipoAnalisisBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoAnalisisBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoAnalisisBuscarActionPerformed

    private void jComboBoxBuscarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBuscarPorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBuscarPorActionPerformed

    private void jButtonBuscarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPorActionPerformed
        this.iniciarRegistro();
        if (jComboBoxTipoAnalisisBuscar.getSelectedIndex() != 0) {//validaciones de seleccion de opciones
            if (jComboBoxBuscarPor.getSelectedIndex() != 0) {//validaciones de seleccion de opciones

                //Opcion 1: buscar por protocolo
                if (jComboBoxBuscarPor.getSelectedIndex() == 1) {
                    String protocolo = JOptionPane.showInputDialog(jPanelOpcBus, "Nº de Protocolo", "Ingrese el dato a buscar", JOptionPane.QUESTION_MESSAGE);
                    String sql = config.box_buscar_protocolo(protocolo);
                    if (miCoordinadorRegistroGeneral.buscarRegistro(sql) == true) {
                        buscar.columnastabla(12, sql);
                        buscar.LlenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(jPanelOpcBus, "Registro no encontrado");
                    }
                }

                //Opcion 2 : buscar por productor                    
                if (jComboBoxBuscarPor.getSelectedIndex() == 2) {
                    String productor = JOptionPane.showInputDialog(jPanelOpcBus, "Rif Nº:", "Ingrese el dato a buscar", JOptionPane.QUESTION_MESSAGE);
                    String sql;
                    if (jComboBoxTipoAnalisisBuscar.getSelectedIndex() != 7) {//busqueda con filtro de tipo de analisis
                        ClaseObjetoParaComboBox Analisis = (ClaseObjetoParaComboBox) jComboBoxTipoAnalisisBuscar.getSelectedItem();//variable para extraer el id del tipo de analisis
                        int tipoanalisisbuscar = Analisis.getId();
                        sql = config.box_buscar_productor(productor, tipoanalisisbuscar);
                    } else {//busqueda sin filtro - todos
                        sql = config.box_buscar_productor(productor);
                    }
                    if (miCoordinadorRegistroGeneral.buscarRegistro(sql) == true) {
                        buscar.columnastabla(12, sql);
                        buscar.LlenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(jPanelOpcBus, "Registro no encontrado");
                    }
                }

                //Opcion 3 : buscar por predio
                if (jComboBoxBuscarPor.getSelectedIndex() == 3) {
                    String predio = JOptionPane.showInputDialog(jPanelOpcBus, "Predio/Parcela:", "Ingrese el dato a buscar", JOptionPane.QUESTION_MESSAGE);  // sin icono
                    System.out.println("El usuario pide el predio: " + predio);
                    String sql;
                    if (jComboBoxTipoAnalisisBuscar.getSelectedIndex() != 7) {//busqueda con filtro de tipo de analisis
                        ClaseObjetoParaComboBox Analisis = (ClaseObjetoParaComboBox) jComboBoxTipoAnalisisBuscar.getSelectedItem();//variable para extraer el id del tipo de analisis
                        int tipoanalisisbuscar = Analisis.getId();
                        sql = config.box_buscar_predio(predio, tipoanalisisbuscar);
                    } else {
                        sql = config.box_buscar_predio(predio);
                    }
                    if (miCoordinadorRegistroGeneral.buscarRegistro(sql) == true) {
                        buscar.columnastabla(12, sql);
                        buscar.LlenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(jPanelOpcBus, "Registro no encontrado");
                    }

                }

                //Opcion 4 : buscar por remitente
                if (jComboBoxBuscarPor.getSelectedIndex() == 4) {
                    String remitente = JOptionPane.showInputDialog(jPanelOpcBus, "Remitente:", "Ingrese el dato a buscar", JOptionPane.QUESTION_MESSAGE);  // sin icono
                    System.out.println("El usuario pide el remitente: " + remitente);
                    String sql;
                    if (jComboBoxTipoAnalisisBuscar.getSelectedIndex() != 7) {//busqueda con filtro de tipo de analisis
                        ClaseObjetoParaComboBox Analisis = (ClaseObjetoParaComboBox) jComboBoxTipoAnalisisBuscar.getSelectedItem();//variable para extraer el id del tipo de analisis
                        int tipoanalisisbuscar = Analisis.getId();
                        sql = config.box_buscar_remitente(remitente, tipoanalisisbuscar);
                    } else {
                        sql = config.box_buscar_remitente(remitente);
                    }
                    if (miCoordinadorRegistroGeneral.buscarRegistro(sql) == true) {
                        buscar.columnastabla(12, sql);
                        buscar.LlenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(jPanelOpcBus, "Registro no encontrado");
                    }
                }

                //Opcion 5 : buscar por direccion
                if (jComboBoxBuscarPor.getSelectedIndex() == 5) {

                    comboestadobuscar.consultabox2(config.box_consulta_estado, "id_estado", "estado");
                    comboestadobuscar.LLenarBoxConID();
                    //jComboBoxEstadobuscar.setModel(comboestadobuscar.modelo);

                    JOptionPane.showMessageDialog(null, jComboBoxEstadobuscar, "Seleccione el Estado", JOptionPane.QUESTION_MESSAGE);
                    ClaseObjetoParaComboBox EstadoBuscar = (ClaseObjetoParaComboBox) jComboBoxEstadobuscar.getSelectedItem();//variable para extraer el id del estado seleccionado
                    int estadobus = EstadoBuscar.getId();
                    String sql;
                    int confirmado = JOptionPane.showConfirmDialog(jPanelOpcBus, "¿Filtrar hasta municipio?");
                    if (JOptionPane.OK_OPTION == confirmado) {
                        combomunicipiobuscar.consultabox2(config.box_consulta_municipio(estadobus), "id_municipio", "municipio");//cargamos la consulta para cargar el municipio
                        combomunicipiobuscar.LLenarBoxConID();//llenamos el combo del municipio      
                        JOptionPane.showMessageDialog(null, jComboBoxMunicipiobuscar, "Seleccione el Municipio", JOptionPane.QUESTION_MESSAGE);
                        ClaseObjetoParaComboBox MunicipioBuscar = (ClaseObjetoParaComboBox) jComboBoxMunicipiobuscar.getSelectedItem();//variable para extraer el id del estado seleccionado
                        int municipiobus = MunicipioBuscar.getId();
                        int confirmado2 = JOptionPane.showConfirmDialog(jPanelOpcBus, "¿Filtrar hasta parroquia?");

                        if (JOptionPane.OK_OPTION == confirmado2) {
                            comboparroquiabuscar.consultabox2(config.box_consulta_parroquia(municipiobus), "id_parroquia", "parroquia");//cargamos la consulta para cargar la parroquia
                            comboparroquiabuscar.LLenarBoxConID();//llenamos el combo del municipio      
                            JOptionPane.showMessageDialog(null, jComboBoxParroquiabuscar, "Seleccione la Parroquia", JOptionPane.QUESTION_MESSAGE);
                            ClaseObjetoParaComboBox ParroquiaBuscar = (ClaseObjetoParaComboBox) jComboBoxParroquiabuscar.getSelectedItem();//variable para extraer el id de la parroquia seleccionado
                            int parroquiabus = ParroquiaBuscar.getId();
                            //int confirmado3 = JOptionPane.showConfirmDialog(jPanelOpcBus,"¿Filtrar hasta direccion?");    

                            if (jComboBoxTipoAnalisisBuscar.getSelectedIndex() != 7) {//busqueda con filtro de tipo de analisis
                                ClaseObjetoParaComboBox Analisis = (ClaseObjetoParaComboBox) jComboBoxTipoAnalisisBuscar.getSelectedItem();//variable para extraer el id del tipo de analisis
                                int tipoanalisisbuscar = Analisis.getId();
                                sql = config.box_buscar_direccion_p(parroquiabus, tipoanalisisbuscar);
                            } else {
                                sql = config.box_buscar_direccion_p(parroquiabus);
                            }

                        } else {
                            if (jComboBoxTipoAnalisisBuscar.getSelectedIndex() != 7) {//busqueda con filtro de tipo de analisis
                                ClaseObjetoParaComboBox Analisis = (ClaseObjetoParaComboBox) jComboBoxTipoAnalisisBuscar.getSelectedItem();//variable para extraer el id del tipo de analisis
                                int tipoanalisisbuscar = Analisis.getId();
                                sql = config.box_buscar_direccion_m(municipiobus, tipoanalisisbuscar);
                            } else {
                                sql = config.box_buscar_direccion_m(municipiobus);
                            }

                        }

                    } else {

                        if (jComboBoxTipoAnalisisBuscar.getSelectedIndex() != 7) {//busqueda con filtro de tipo de analisis
                            ClaseObjetoParaComboBox Analisis = (ClaseObjetoParaComboBox) jComboBoxTipoAnalisisBuscar.getSelectedItem();//variable para extraer el id del tipo de analisis
                            int tipoanalisisbuscar = Analisis.getId();
                            sql = config.box_buscar_direccion_e(estadobus, tipoanalisisbuscar);
                        } else {
                            sql = config.box_buscar_direccion_e(estadobus);
                        }
                    }
                    if (miCoordinadorRegistroGeneral.buscarRegistro(sql) == true) {
                        buscar.columnastabla(12, sql);
                        buscar.LlenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(jPanelOpcBus, "Registro no encontrado");
                    }

                }

                //Opcion 6 : buscar por fecha
                if (jComboBoxBuscarPor.getSelectedIndex() == 6) {
                    String fecha_inicial = JOptionPane.showInputDialog(jPanelOpcBus, "Fecha Inicial:", "Ingrese la fecha en formato aaaa-mm-dd", JOptionPane.QUESTION_MESSAGE);
                    String fecha_final = JOptionPane.showInputDialog(jPanelOpcBus, "Fecha Final:", "Ingrese la fecha en formato aaaa-mm-dd", JOptionPane.QUESTION_MESSAGE);
                    ValidacionesVista em = new ValidacionesVista();
                    if (em.isFecha(fecha_inicial) && em.isFecha(fecha_final)) {
                        String sql;
                        if (jComboBoxTipoAnalisisBuscar.getSelectedIndex() != 7) {//busqueda con filtro de tipo de analisis
                            ClaseObjetoParaComboBox Analisis = (ClaseObjetoParaComboBox) jComboBoxTipoAnalisisBuscar.getSelectedItem();//variable para extraer el id del tipo de analisis
                            int tipoanalisisbuscar = Analisis.getId();
                            sql = config.box_buscar_fecha(fecha_inicial, fecha_final, tipoanalisisbuscar);
                        } else {
                            sql = config.box_buscar_fecha(fecha_inicial, fecha_final);
                        }
                        if (miCoordinadorRegistroGeneral.buscarRegistro(sql) == true) {
                            buscar.columnastabla(12, sql);
                            buscar.LlenarTabla();
                        } else {
                            JOptionPane.showMessageDialog(jPanelOpcBus, "Registro no encontrado");
                        }

                    } else {
                        JOptionPane.showMessageDialog(jPanelOpcBus, "Ingrese las fechas con el formato indicado");
                    }

                }

                //Opcion 7 : buscar por todos los registros
                if (jComboBoxBuscarPor.getSelectedIndex() == 7) {
                    String sql;

                    if (jComboBoxTipoAnalisisBuscar.getSelectedIndex() != 7) {//busqueda con filtro de tipo de analisis
                        ClaseObjetoParaComboBox Analisis = (ClaseObjetoParaComboBox) jComboBoxTipoAnalisisBuscar.getSelectedItem();//variable para extraer el id del tipo de analisis
                        int tipoanalisisbuscar = Analisis.getId();
                        sql = config.box_buscar_todo(tipoanalisisbuscar);
                    } else {
                        sql = config.box_buscar_todo;
                    }
                    if (miCoordinadorRegistroGeneral.buscarRegistro(sql) == true) {
                        buscar.columnastabla(12, sql);
                        buscar.LlenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(jPanelOpcBus, "Registro no encontrado");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Seleccione el tipo de búsqueda");
            }

        } else {
            if (jComboBoxBuscarPor.getSelectedIndex() != 1) {
                JOptionPane.showMessageDialog(rootPane, "Seleccione el tipo de Análisis");
            }

        }

        for (int i = 1; i < 12; i++) {
            jTableBuscar.getColumnModel().getColumn(1).setCellRenderer(pre.render);
        }

        FormatoTablaBuscar ft = new FormatoTablaBuscar(0, 2);
        jTableBuscar.setDefaultRenderer(Object.class, ft);

    }//GEN-LAST:event_jButtonBuscarPorActionPerformed

    private void jTableBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBuscarMouseClicked
        filatablabuscar = jTableBuscar.getSelectedRow();//obtiene la fila seleccionada        
        Protocoloid = jTableBuscar.getModel().getValueAt(filatablabuscar, 0).toString();//obtiene el protocolo de la muestra seleccionada
        tipoanalisisbuscar = jTableBuscar.getModel().getValueAt(filatablabuscar, 2).toString();
        idregistrogeneralmodificar = Integer.parseInt(jTableBuscar.getModel().getValueAt(filatablabuscar, 11).toString());

    }//GEN-LAST:event_jTableBuscarMouseClicked

    private void jButtonEliminarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarRegistroActionPerformed
        if (Protocoloid.equals("") == false) {
            //mensaje para comprobar si realmente desean eliminar un rif
            Object[] options = {"Sí", "No"};
            int q = JOptionPane.showOptionDialog(null, "Desea ELIMINAR el registro seleccionado?", "Advertencia",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            System.out.println(q);

            if (q == 0) {// ejecutar la eliminacion del registro si responden si en el cuadro de mensaje
                if (miCoordinadorRegistroGeneral.eliminarRegistro(Protocoloid) == true) {
                    buscar.LlenarTabla();
                    Protocoloid = "";
                }

                buscar.LlenarTabla();
                Protocoloid = "";

            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un registro para eliminar");
            }
        }


    }//GEN-LAST:event_jButtonEliminarRegistroActionPerformed

    private void jButtonAbrirBuscarRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAbrirBuscarRegistroMouseClicked
        jInternalFrameBuscar.setVisible(true);
    }//GEN-LAST:event_jButtonAbrirBuscarRegistroMouseClicked

    private void jButtonModificarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarRegistroActionPerformed
        if (jTableBuscar.getSelectedRow() >= 0) {
//filasid.clear();
            n_muestras = 0;
            int tabla;
            //limpio la tabla muestras      
            tabla = jTableMuestras.getRowCount();
            if (tabla > 0) {
                for (int j = 0; j < tabla; j++) {
                    muestras.modelo.removeRow(0);
                }
            }
            RegistroGeneralBO miRegistro;
            miRegistro = miCoordinadorRegistroGeneral.cargarRegistro(Protocoloid);

            //cargamos el id
            idregistrogeneralmodificar = miRegistro.getId_registro_general();
            //cargamos el tipo de analisis
            combotipoanalisis.setjComboBox(jComboBoxTipoAnalisis, miRegistro.getAnalisis_tipo());
            //cargamos la fecha de recepcion haciendo las conversiones necesarias
            Calendar myGDate = new GregorianCalendar();
            myGDate.setTime(miRegistro.getFecha_recepcion());
            dateChooserComboRecepcion.setSelectedDate(myGDate);

            //Cargamos el propietario
            jTextFieldPropietario.setText(miRegistro.getPropietario());
            idusact = miRegistro.getId_propietario();
            //Cargamos el predio
            jTextFieldPredio.setText(miRegistro.getPredio());
            idpreact = miRegistro.getId_predio();
            //Cargamos el remitente
            jTextFieldRemitente.setText(miRegistro.getRemitente());

            //Cargar las muestras
            int cant = miRegistro.getMuestras().size();

            for (int i = 0; i < cant; i++) {
                Object[] fila;
                RegistroMuestraBO miRegistro2;
                miRegistro2 = (RegistroMuestraBO) miRegistro.getMuestras().get(i);
                //columnasid.clear();
                ArrayList columnasid = new ArrayList();
                fila = new Object[8];
                fila[0] = miRegistro2.getId_tsn_especie();
                columnasid.add(miRegistro2.getId_tsn_especie());
                fila[1] = miRegistro2.getNombre_completo();
                fila[2] = miRegistro2.getMuestra_tipo();
                columnasid.add(miRegistro2.getId_muestra_tipo());
                fila[3] = miRegistro2.getHierro();
                columnasid.add(miRegistro2.getHierro());
                fila[6] = miRegistro2.getEdad_meses();
                columnasid.add(miRegistro2.getEdad_meses());
                fila[5] = miRegistro2.getSexo();
                columnasid.add(miRegistro2.getSexo());
                if (miRegistro2.getFecha_vacunacion() != null) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(miRegistro2.getFecha_vacunacion());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    fila[4] = sdf.format(calendar.getTime());
                    columnasid.add(sdf.format(calendar.getTime()));
                } else {
                    fila[4] = null;
                    columnasid.add(null);
                }
                fila[7] = miRegistro2.getObservacion();
                columnasid.add(miRegistro2.getObservacion());
                filasid.add(columnasid);
                muestras.modelo.addRow(fila);
                n_muestras += 1;
            }
            jInternalFrameRegistroGeneral.setVisible(true);
            jLabelNMuestras.setText("Nº Muestras: " + n_muestras);
            jButtonAgregarRegistro.setText("Modificar");
            jButtonAgregarRegistro.setToolTipText("Modificar el registro completo");
            jInternalFrameRegistroGeneral.setTitle("Modificar Registro");
            jInternalFrameRegistroGeneral.toFront();

        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un registro primero");
        }

    }//GEN-LAST:event_jButtonModificarRegistroActionPerformed

    private void jInternalFrameRegistroGeneralInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_jInternalFrameRegistroGeneralInternalFrameClosed
        jButtonAgregarRegistro.setText("Agregar");
        jButtonAgregarRegistro.setToolTipText("Agregar registro completo");
        jInternalFrameRegistroGeneral.setTitle("Agregar Registro");

        //11.5.-Limpiamos las variables y objetos utilizados
        //limpiamos el array de los id de genero, especie y raza
        filasid.clear();
        //limpiamos los cuadros de texto
        jTextFieldPropietario.setText(null);
        jTextFieldRemitente.setText(null);
        jTextFieldPredio.setText(null);

        jTextFieldHierro.setText(null);
        jTextFieldObservaciones.setText(null);
        //limpiamos los jComboBox
        jComboBoxTipoAnalisis.setSelectedIndex(0);

        /* 
         if((jComboBoxRaza.isEnabled()==true) && (jComboBoxRaza.getSelectedIndex()!=-1)) {
         jComboBoxRaza.setSelectedIndex(0);
         }
         if((jComboBoxEspecie.isEnabled()==true)&& (jComboBoxEspecie.getSelectedIndex()!=-1)) {
         jComboBoxEspecie.setSelectedIndex(0);
         }
         if((jComboBoxGenero.isEnabled()==true)&& (jComboBoxGenero.getSelectedIndex()!=-1)) {
         jComboBoxGenero.setSelectedIndex(0);
         }*/
        jComboBoxSexo.setSelectedIndex(0);
        jComboBoxEdadYear.setSelectedIndex(0);
        jComboBoxEdadMeses.setSelectedIndex(0);
        //limpio la lista

        jCheckBoxVacunado.setSelected(false);
        //limpio la tabla muestras              
        int tabla = jTableMuestras.getRowCount();
        for (int j = 0; j < tabla; j++) {
            muestras.modelo.removeRow(0);
        }
        n_muestras = 0;

    }//GEN-LAST:event_jInternalFrameRegistroGeneralInternalFrameClosed

    private void jTablePrediosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePrediosMouseClicked
        int fil = jTablePredios.getSelectedRow();//obtiene la fila seleccionada        
        idpreact = Integer.parseInt(jTablePredios.getModel().getValueAt(fil, 8).toString()); //asigna el id del predio seleccionado
        predio_actual = jTablePredios.getModel().getValueAt(fil, 0).toString();

    }//GEN-LAST:event_jTablePrediosMouseClicked

    private void jButtonPredioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonPredioMouseClicked
        jTextFieldPredio.setText(null);
        jTextFieldPredio.setText(predio_actual);
        jInternalFramePredios.setVisible(false);

    }//GEN-LAST:event_jButtonPredioMouseClicked

    private void jButtonResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResultadoActionPerformed
        if (jTableBuscar.getSelectedRow()>=0) {
 
                   switch (tipoanalisisbuscar) {
            case "Brucelosis": {
                BrucelosisDAO bru = new BrucelosisDAO();
                //ta.encontrar(Protocoloid);
                bru.llenar(idregistrogeneralmodificar);
                //cargar los valores seleccionados para jListAnalistas
                ArrayList analistas;
                ArrayList lista = new ArrayList();
                analistas = bru.buscarAnalistas_Fecha(idregistrogeneralmodificar);
                //cargar los analistas
                int cantidad_e = analistas.size();
                Lista l = new Lista();
                for (int i = 0; i < cantidad_e; i++) {
                    ArrayList muestra;
                    muestra = (ArrayList) analistas.get(i);
                    String cedula = muestra.get(1).toString();
                    l.setjList(jListAnalistas, cedula);
                    lista.add(jListAnalistas.getSelectedIndex());
                }
                int[] indice = new int[cantidad_e];// creamos array del tamaño de los valorers seleccionados
                for (int i = 0; i < cantidad_e; i++) {
                    indice[i] = (Integer) lista.get(i); //Asignamos al array los valores de los index guardados en la lista
                }
                jListAnalistas.setSelectedIndices(indice);// seleccionamos los multiples valores en la lista
                //cargar fecha de procesado
                RegistroGeneralBO miRegistro;
                miRegistro = bru.buscarFecha_Procesado_Fecha(idregistrogeneralmodificar);
                Calendar myGDate = new GregorianCalendar();
                if (miRegistro.getFecha_procesado() == null) {
                    myGDate = Calendar.getInstance();
                } else {
                    myGDate.setTime(miRegistro.getFecha_procesado());
                }
                dateChooserComboProcesamiento.setSelectedDate(myGDate);
                //cargar los valores de la tabla
                jTableMuestrasbru.setModel(bru.modelo);
                //cargamos el tipo de prueba
                final JComboBox comboBoxTipoPrueba = new JComboBox();
                Box comboTipoPrueba = new Box();
                comboTipoPrueba.consultabox2(config.box_tipo_prueba(tipoanalisisbuscar), "id_analisis_prueba", "analisis_prueba");
                comboTipoPrueba.LLenarBoxTablaConID(comboBoxTipoPrueba);
                TableColumn PruebaColumn = jTableMuestrasbru.getColumnModel().getColumn(10);
                PruebaColumn.setCellEditor(new DefaultCellEditor(comboBoxTipoPrueba));
                //cargamos el lote de antigeno
                final JComboBox comboBoxLote = new JComboBox();
                Box comboLote = new Box();
                comboLote.consultabox2(config.box_lote, "id_antigeno_lote", "lote");
                comboLote.LLenarBoxTablaConID(comboBoxLote);
                TableColumn LoteColumn = jTableMuestrasbru.getColumnModel().getColumn(11);
                LoteColumn.setCellEditor(new DefaultCellEditor(comboBoxLote));
                //asigno el tamaño de las columnas para la tabla muestras
                for (int i = 0; i < 12; i++) {
                    jTableMuestrasbru.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
                    int tam = jTableMuestrasbru.getColumnModel().getColumn(i).getHeaderValue().toString().length();//obtener tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setPreferredWidth(tam * 11);//autoajustar celdas al tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setCellRenderer(bru.render);
                    if (i == 9) {
                        jTableMuestrasbru.getColumnModel().getColumn(i).setCellRenderer(null);
                    }//no renderizar columna 10 porque lleva un checkbox
                }
                jInternalFrameBrucelosisR.setTitle("Ingresar Resultados - Análisis de Brucelosis");
                jInternalFrameBrucelosisR.setVisible(true);
                jInternalFrameBrucelosisR.toFront();
                break;
            }
            case "Hematología": {
                HematologiaDAO hema = new HematologiaDAO();
                //ta.encontrar(Protocoloid);
                hema.llenar(idregistrogeneralmodificar);
                //cargar los valores seleccionados para jListAnalistas
                ArrayList analistas;
                ArrayList lista = new ArrayList();
                analistas = hema.buscarAnalistas_Fecha(idregistrogeneralmodificar);
                //cargar los analistas
                int cantidad_e = analistas.size();
                Lista l = new Lista();
                for (int i = 0; i < cantidad_e; i++) {
                    ArrayList muestra;
                    muestra = (ArrayList) analistas.get(i);
                    String cedula = muestra.get(1).toString();
                    l.setjList(jListAnalistas, cedula);
                    lista.add(jListAnalistas.getSelectedIndex());
                }
                int[] indice = new int[cantidad_e];// creamos array del tamaño de los valorers seleccionados
                for (int i = 0; i < cantidad_e; i++) {
                    indice[i] = (Integer) lista.get(i); //Asignamos al array los valores de los index guardados en la lista
                }
                jListAnalistas.setSelectedIndices(indice);// seleccionamos los multiples valores en la lista
                //cargar fecha de procesado
                RegistroGeneralBO miRegistro;
                miRegistro = hema.buscarFecha_Procesado_Fecha(idregistrogeneralmodificar);
                Calendar myGDate = new GregorianCalendar();
                if (miRegistro.getFecha_procesado() == null) {
                    myGDate = Calendar.getInstance();
                } else {
                    myGDate.setTime(miRegistro.getFecha_procesado());
                }
                dateChooserComboProcesamiento.setSelectedDate(myGDate);
                //cargar los valores de la tabla
                jTableMuestrasbru.setModel(hema.modelo);
                //asigno el tamaño de las columnas para la tabla muestras
                for (int i = 0; i < 13; i++) {
                    jTableMuestrasbru.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
                    int tam = jTableMuestrasbru.getColumnModel().getColumn(i).getHeaderValue().toString().length();//obtener tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setPreferredWidth(tam * 11);//autoajustar celdas al tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setCellRenderer(hema.render);

                }
                jButtonCargar.setVisible(false);
                jInternalFrameBrucelosisR.setTitle("Ingresar Resultados - Análisis de Hematologia");
                jInternalFrameBrucelosisR.setVisible(true);
                jInternalFrameBrucelosisR.toFront();
                break;
            }
            case "Anemia Infecciosa": {
                AnemiaDAO ane = new AnemiaDAO();
                //ta.encontrar(Protocoloid);
                ane.llenar(idregistrogeneralmodificar);
                //cargar los valores seleccionados para jListAnalistas
                ArrayList analistas;
                ArrayList lista = new ArrayList();
                analistas = ane.buscarAnalistas_Fecha(idregistrogeneralmodificar);
                //cargar los analistas
                int cantidad_e = analistas.size();
                Lista l = new Lista();
                for (int i = 0; i < cantidad_e; i++) {
                    ArrayList muestra;
                    muestra = (ArrayList) analistas.get(i);
                    String cedula = muestra.get(1).toString();
                    l.setjList(jListAnalistas, cedula);
                    lista.add(jListAnalistas.getSelectedIndex());
                }
                int[] indice = new int[cantidad_e];// creamos array del tamaño de los valorers seleccionados
                for (int i = 0; i < cantidad_e; i++) {
                    indice[i] = (Integer) lista.get(i); //Asignamos al array los valores de los index guardados en la lista
                }
                jListAnalistas.setSelectedIndices(indice);// seleccionamos los multiples valores en la lista
                //cargar fecha de procesado
                RegistroGeneralBO miRegistro;
                miRegistro = ane.buscarFecha_Procesado_Fecha(idregistrogeneralmodificar);
                Calendar myGDate = new GregorianCalendar();
                if (miRegistro.getFecha_procesado() == null) {
                    myGDate = Calendar.getInstance();
                } else {
                    myGDate.setTime(miRegistro.getFecha_procesado());
                }
                dateChooserComboProcesamiento.setSelectedDate(myGDate);
                //cargar los valores de la tabla
                jTableMuestrasbru.setModel(ane.modelo);
                //cargamos el tipo de prueba
                final JComboBox comboBoxTipoPrueba = new JComboBox();
                Box comboTipoPrueba = new Box();
                comboTipoPrueba.consultabox2(config.box_tipo_prueba(tipoanalisisbuscar), "id_analisis_prueba", "analisis_prueba");
                comboTipoPrueba.LLenarBoxTablaConID(comboBoxTipoPrueba);
                TableColumn PruebaColumn = jTableMuestrasbru.getColumnModel().getColumn(10);
                PruebaColumn.setCellEditor(new DefaultCellEditor(comboBoxTipoPrueba));
                //cargamos el lote de antigeno
                final JComboBox comboBoxLote = new JComboBox();
                Box comboLote = new Box();
                comboLote.consultabox2(config.box_lote, "id_antigeno_lote", "lote");
                comboLote.LLenarBoxTablaConID(comboBoxLote);
                TableColumn LoteColumn = jTableMuestrasbru.getColumnModel().getColumn(11);
                LoteColumn.setCellEditor(new DefaultCellEditor(comboBoxLote));
                //asigno el tamaño de las columnas para la tabla muestras
                for (int i = 0; i < 12; i++) {
                    jTableMuestrasbru.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
                    int tam = jTableMuestrasbru.getColumnModel().getColumn(i).getHeaderValue().toString().length();//obtener tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setPreferredWidth(tam * 11);//autoajustar celdas al tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setCellRenderer(ane.render);
                    if (i == 9) {
                        jTableMuestrasbru.getColumnModel().getColumn(i).setCellRenderer(null);
                    }//no renderizar columna 10 porque lleva un checkbox
                }
                jButtonCargar.setVisible(false);
                jInternalFrameBrucelosisR.setTitle("Ingresar Resultados - Análisis de Anemia Infecciosa");
                jInternalFrameBrucelosisR.setVisible(true);
                jInternalFrameBrucelosisR.toFront();
                break;
            }
            case "Hemoparásitos": {
                HemoparasitoDAO hemo = new HemoparasitoDAO();
                //ta.encontrar(Protocoloid);
                hemo.llenar(idregistrogeneralmodificar);
                //cargar los valores seleccionados para jListAnalistas
                ArrayList analistas;
                ArrayList lista = new ArrayList();
                analistas = hemo.buscarAnalistas_Fecha(idregistrogeneralmodificar);
                //cargar los analistas
                int cantidad_e = analistas.size();
                Lista l = new Lista();
                for (int i = 0; i < cantidad_e; i++) {
                    ArrayList muestra;
                    muestra = (ArrayList) analistas.get(i);
                    String cedula = muestra.get(1).toString();
                    l.setjList(jListAnalistas, cedula);
                    lista.add(jListAnalistas.getSelectedIndex());
                }
                int[] indice = new int[cantidad_e];// creamos array del tamaño de los valorers seleccionados
                for (int i = 0; i < cantidad_e; i++) {
                    indice[i] = (Integer) lista.get(i); //Asignamos al array los valores de los index guardados en la lista
                }
                jListAnalistas.setSelectedIndices(indice);// seleccionamos los multiples valores en la lista
                //cargar fecha de procesado
                RegistroGeneralBO miRegistro;
                miRegistro = hemo.buscarFecha_Procesado_Fecha(idregistrogeneralmodificar);
                Calendar myGDate = new GregorianCalendar();
                if (miRegistro.getFecha_procesado() == null) {
                    myGDate = Calendar.getInstance();
                } else {
                    myGDate.setTime(miRegistro.getFecha_procesado());
                }
                dateChooserComboProcesamiento.setSelectedDate(myGDate);
                //cargar los valores de la tabla
                jTableMuestrasbru.setModel(hemo.modelo);
                //cargamos el tipo de prueba
                final JComboBox comboBoxTipoPrueba = new JComboBox();
                Box comboTipoPrueba = new Box();
                comboTipoPrueba.consultabox2(config.box_tipo_prueba(tipoanalisisbuscar), "id_analisis_prueba", "analisis_prueba");
                comboTipoPrueba.LLenarBoxTablaConID(comboBoxTipoPrueba);
                TableColumn PruebaColumn = jTableMuestrasbru.getColumnModel().getColumn(11);
                PruebaColumn.setCellEditor(new DefaultCellEditor(comboBoxTipoPrueba));
                //asigno el tamaño de las columnas para la tabla muestras
                Integer tam_tabla_resultado[] = {120,100,120,100,50,50,100,120,40,80,200,140,220};
                for (int i = 0; i < 13; i++) {
                    
                    jTableMuestrasbru.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
                    int tam = jTableMuestrasbru.getColumnModel().getColumn(i).getHeaderValue().toString().length();//obtener tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setPreferredWidth(tam_tabla_resultado[i]);//autoajustar celdas al tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setCellRenderer(hemo.render);

                }
                jButtonCargar.setVisible(true);
                jInternalFrameBrucelosisR.setTitle("Ingresar Resultados - Análisis de Hemoparásitos");
                jInternalFrameBrucelosisR.setVisible(true);
                jInternalFrameBrucelosisR.toFront();
                break;
            }
            case "Coprología":{
                CoprologiaDAO cop = new CoprologiaDAO();
                //ta.encontrar(Protocoloid);
                cop.llenar(idregistrogeneralmodificar);
                //cargar los valores seleccionados para jListAnalistas
                ArrayList analistas;
                ArrayList lista = new ArrayList();
                analistas = cop.buscarAnalistas_Fecha(idregistrogeneralmodificar);
                //cargar los analistas
                int cantidad_e = analistas.size();
                Lista l = new Lista();
                for (int i = 0; i < cantidad_e; i++) {
                    ArrayList muestra;
                    muestra = (ArrayList) analistas.get(i);
                    String cedula = muestra.get(1).toString();
                    l.setjList(jListAnalistas, cedula);
                    lista.add(jListAnalistas.getSelectedIndex());
                }
                int[] indice = new int[cantidad_e];// creamos array del tamaño de los valorers seleccionados
                for (int i = 0; i < cantidad_e; i++) {
                    indice[i] = (Integer) lista.get(i); //Asignamos al array los valores de los index guardados en la lista
                }
                jListAnalistas.setSelectedIndices(indice);// seleccionamos los multiples valores en la lista
                //cargar fecha de procesado
                RegistroGeneralBO miRegistro;
                miRegistro = cop.buscarFecha_Procesado_Fecha(idregistrogeneralmodificar);
                Calendar myGDate = new GregorianCalendar();
                if (miRegistro.getFecha_procesado() == null) {
                    myGDate = Calendar.getInstance();
                } else {
                    myGDate.setTime(miRegistro.getFecha_procesado());
                }
                dateChooserComboProcesamiento.setSelectedDate(myGDate);
                //cargar los valores de la tabla
                jTableMuestrasbru.setModel(cop.modelo);
                //cargamos el tipo de prueba
                final JComboBox comboBoxTipoPrueba = new JComboBox();
                Box comboTipoPrueba = new Box();
                comboTipoPrueba.consultabox2(config.box_tipo_prueba(tipoanalisisbuscar), "id_analisis_prueba", "analisis_prueba");
                comboTipoPrueba.LLenarBoxTablaConID(comboBoxTipoPrueba);
                TableColumn PruebaColumn = jTableMuestrasbru.getColumnModel().getColumn(11);
                PruebaColumn.setCellEditor(new DefaultCellEditor(comboBoxTipoPrueba));
                //asigno el tamaño de las columnas para la tabla muestras
                Integer tam_tabla_resultado[] = {120,100,120,100,50,50,100,120,40,80,200,140,220};
                for (int i = 0; i < 13; i++) {
                    
                    jTableMuestrasbru.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
                    int tam = jTableMuestrasbru.getColumnModel().getColumn(i).getHeaderValue().toString().length();//obtener tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setPreferredWidth(tam_tabla_resultado[i]);//autoajustar celdas al tamaño del contenido
                    jTableMuestrasbru.getColumnModel().getColumn(i).setCellRenderer(cop.render);

                }
                jButtonCargar.setVisible(true);
                jInternalFrameBrucelosisR.setTitle("Ingresar Resultados - Análisis de Coprologia");
                jInternalFrameBrucelosisR.setVisible(true);
                jInternalFrameBrucelosisR.toFront();                
                
                break;
            }
            default:
                JOptionPane.showMessageDialog(rootPane, "Modulo en desarrollo, contacte al desarrollador");
                break;
        }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un registro primero");
        }
 

    }//GEN-LAST:event_jButtonResultadoActionPerformed

    private void jTableMuestrasbruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMuestrasbruMouseClicked

            indexresultadobru = jTableMuestrasbru.getSelectedRow(); //Obtengo el index de la fila para almacenar el resultado
      
        


    }//GEN-LAST:event_jTableMuestrasbruMouseClicked

    private void jButtonResultadoBruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonResultadoBruMouseClicked
        //Mensaje para confirmar la modificacion
        
        switch (tipoanalisisbuscar) {
            case "Brucelosis": {
                Object[] options = {"Sí", "No"};
                int q = JOptionPane.showOptionDialog(null, "¿Desea Guardar los resultados del Análisis de Brucelosis?", "Advertencia", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (q == 0) { //q recibe 0 si la respuesta es si en el cuadro de mensaje
                    int tabla = jTableMuestrasbru.getRowCount();
                    ArrayList resultados = new ArrayList();
                    Calendar c1 = dateChooserComboProcesamiento.getSelectedDate();
                    String fecha;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    fecha = sdf.format(c1.getTime());//fecha de procesamiento

                    Object[] seleccionados = jListAnalistas.getSelectedValues();
                    int lim = seleccionados.length;
                    ArrayList procesados = new ArrayList();
                    if (lim != 0) {
                        for (int i = 0; i < lim; i++) {
                            ArrayList fila = new ArrayList();
                            ClaseObjetoParaLista banda = (ClaseObjetoParaLista) seleccionados[i];
                            int idprueba = banda.getId();
                            fila.add(idregistrogeneralmodificar);
                            fila.add(idprueba);
                            procesados.add(fila);
                        }
                    }

                    for (int i = 0; i < tabla; i++) {
                        ArrayList fila = new ArrayList();
                        int id_pru=0;
                        int id_lot=0;
                        int id_mu=0;
                        byte resultado=0;
                        try {
                            id_mu = Integer.parseInt(jTableMuestrasbru.getValueAt(i, 8).toString());
                            id_pru = ((ClaseObjetoParaComboBox) jTableMuestrasbru.getValueAt(i, 10)).getId();
                            id_lot = ((ClaseObjetoParaComboBox) jTableMuestrasbru.getValueAt(i, 11)).getId();
                            if (jTableMuestrasbru.getValueAt(i, 9).toString().equals("true")) {
                                resultado = 1;
                            } else {
                                resultado = 0;
                            }
                            fila.add(id_mu);
                            fila.add(id_pru);
                            fila.add(id_lot);
                            fila.add(resultado);
                            resultados.add(fila);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error Leyendo la tabla: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }//cierro el for

                    try {
                        this.iniciarResultado();
                        if (miCoordinadorResultado.registrarResultado(resultados, fecha, procesados) == true) {
                                //limpiar_predio();
                            //pre.LlenarTabla();
                            jInternalFrameBrucelosisR.setVisible(false);
                        }

                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Error en el Ingreso de Resultados: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
            case "Anemia Infecciosa": {
                Object[] options = {"Sí", "No"};
                int q = JOptionPane.showOptionDialog(null, "¿Desea Guardar los resultados del Análisis de Anemia Infecciosa?", "Advertencia", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (q == 0) { //q recibe 0 si la respuesta es si en el cuadro de mensaje
                    int tabla = jTableMuestrasbru.getRowCount();
                    ArrayList resultados = new ArrayList();
                    Calendar c1 = dateChooserComboProcesamiento.getSelectedDate();
                    String fecha;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    fecha = sdf.format(c1.getTime());//fecha de procesamiento

                    Object[] seleccionados = jListAnalistas.getSelectedValues();
                    int lim = seleccionados.length;
                    ArrayList procesados = new ArrayList();
                    if (lim != 0) {
                        for (int i = 0; i < lim; i++) {
                            ArrayList fila = new ArrayList();
                            ClaseObjetoParaLista banda = (ClaseObjetoParaLista) seleccionados[i];
                            int idprueba = banda.getId();
                            fila.add(idregistrogeneralmodificar);
                            fila.add(idprueba);
                            procesados.add(fila);
                        }
                    }

                    for (int i = 0; i < tabla; i++) {
                        ArrayList fila = new ArrayList();
                        int id_pru=0;
                        int id_lot=0;
                        int id_mu=0;
                        byte resultado=0;
                        try {
                            id_mu = Integer.parseInt(jTableMuestrasbru.getValueAt(i, 8).toString());
                            id_pru = ((ClaseObjetoParaComboBox) jTableMuestrasbru.getValueAt(i, 10)).getId();
                            id_lot = ((ClaseObjetoParaComboBox) jTableMuestrasbru.getValueAt(i, 11)).getId();
                            if (jTableMuestrasbru.getValueAt(i, 9).toString().equals("true")) {
                                resultado = 1;
                            } else {
                                resultado = 0;
                            }
                            fila.add(id_mu);
                            fila.add(id_pru);
                            fila.add(id_lot);
                            fila.add(resultado);
                            resultados.add(fila);
                        } catch (NumberFormatException e) {

                        }

                    }//cierro el for

                    try {
                        this.iniciarResultado();
                        if (miCoordinadorResultado.registrarResultadoAnemia(resultados, fecha, procesados) == true) {
                                //limpiar_predio();
                            //pre.LlenarTabla();
                            jInternalFrameBrucelosisR.setVisible(false);
                        }

                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Error en el Ingreso de Resultados: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
            case "Hemoparásitos": {
                Object[] options = {"Sí", "No"};
                int q = JOptionPane.showOptionDialog(null, "¿Desea Guardar los resultados del Análisis de Hemoparasitos?", "Advertencia", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (q == 0) { //q recibe 0 si la respuesta es si en el cuadro de mensaje
                    int tabla = jTableMuestrasbru.getRowCount();
                    ArrayList resultados = new ArrayList();
                    Calendar c1 = dateChooserComboProcesamiento.getSelectedDate();
                    String fecha;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    fecha = sdf.format(c1.getTime());//fecha de procesamiento

                    Object[] seleccionados = jListAnalistas.getSelectedValues();
                    int lim = seleccionados.length;
                    ArrayList procesados = new ArrayList();
                    if (lim != 0) {
                        for (int i = 0; i < lim; i++) {
                            ArrayList fila = new ArrayList();
                            ClaseObjetoParaLista banda = (ClaseObjetoParaLista) seleccionados[i];
                            int idprueba = banda.getId();
                            fila.add(idregistrogeneralmodificar);
                            fila.add(idprueba);
                            procesados.add(fila);
                        }
                    }

                    for (int i = 0; i < tabla; i++) {
                        ArrayList fila = new ArrayList();
                        int id_pru=0;
                        String descrip;
                        int id_mu=0;
                        //Object[] resultado = new Object[]{jTableMuestrasbru.getValueAt(i, 9)};
                        try {
                            id_mu = Integer.parseInt(jTableMuestrasbru.getValueAt(i, 8).toString());
                            id_pru = ((ClaseObjetoParaComboBox) jTableMuestrasbru.getValueAt(i, 11)).getId();
                            descrip = jTableMuestrasbru.getValueAt(i, 12).toString();
                                //resultHPBo.setId_analisis_prueba(id_pru);
                            // resultHPBo.setObservacion(descrip);
                            fila.add(id_mu);
                            fila.add(id_pru);
                            fila.add(descrip);
                            fila.add(jTableMuestrasbru.getValueAt(i, 9));
                            resultados.add(fila);
                            ///resultHPBo.getHemoparasitoDetalleBo().get(i).getId_tsn_especie();
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error leyendo la tabla: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }//cierro el for

                    try {
                        this.iniciarResultado();
                        miCoordinadorResultado.registrarResultadoHemoparasito(resultados, fecha, procesados);
                            //limpiar_predio();
                        //pre.LlenarTabla();
                        //jInternalFrameBrucelosisR.setVisible(false);

                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Error en el Ingreso de Resultados: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }            
            case "Coprología": {
                Object[] options = {"Sí", "No"};
                int q = JOptionPane.showOptionDialog(null, "¿Desea Guardar los resultados del Análisis de Coprología?", "Advertencia", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (q == 0) { //q recibe 0 si la respuesta es si en el cuadro de mensaje
                    int tabla = jTableMuestrasbru.getRowCount();
                    ArrayList resultados = new ArrayList();
                    Calendar c1 = dateChooserComboProcesamiento.getSelectedDate();
                    String fecha;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    fecha = sdf.format(c1.getTime());//fecha de procesamiento

                    Object[] seleccionados = jListAnalistas.getSelectedValues();
                    int lim = seleccionados.length;
                    ArrayList procesados = new ArrayList();
                    if (lim != 0) {
                        for (int i = 0; i < lim; i++) {
                            ArrayList fila = new ArrayList();
                            ClaseObjetoParaLista banda = (ClaseObjetoParaLista) seleccionados[i];
                            int idprueba = banda.getId();
                            fila.add(idregistrogeneralmodificar);
                            fila.add(idprueba);
                            procesados.add(fila);
                        }
                    }

                    for (int i = 0; i < tabla; i++) {
                        ArrayList fila = new ArrayList();
                        int id_pru=0;
                        String descrip;
                        int id_mu=0;
                        //Object[] resultado = new Object[]{jTableMuestrasbru.getValueAt(i, 9)};
                        try {
                            id_mu = Integer.parseInt(jTableMuestrasbru.getValueAt(i, 8).toString());
                            id_pru = ((ClaseObjetoParaComboBox) jTableMuestrasbru.getValueAt(i, 11)).getId();
                            descrip = jTableMuestrasbru.getValueAt(i, 12).toString();
                                //resultHPBo.setId_analisis_prueba(id_pru);
                            // resultHPBo.setObservacion(descrip);
                            fila.add(id_mu);
                            fila.add(id_pru);
                            fila.add(descrip);
                            fila.add(jTableMuestrasbru.getValueAt(i, 9));
                            resultados.add(fila);
                            ///resultHPBo.getHemoparasitoDetalleBo().get(i).getId_tsn_especie();
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error leyendo la tabla: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }//cierro el for

                    try {
                        this.iniciarResultado();
                        miCoordinadorResultado.registrarResultadoCoprologia(resultados, fecha, procesados);
                            //limpiar_predio();
                        //pre.LlenarTabla();
                        //jInternalFrameBrucelosisR.setVisible(false);

                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Error en el Ingreso de Resultados: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
            case "Hematología": {
                Object[] options = {"Sí", "No"};
                int q = JOptionPane.showOptionDialog(null, "¿Desea Guardar los resultados del Análisis de Hematología?", "Advertencia", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (q == 0) { //q recibe 0 si la respuesta es si en el cuadro de mensaje
                    int tabla = jTableMuestrasbru.getRowCount();
                    ArrayList resultados = new ArrayList();
                    Calendar c1 = dateChooserComboProcesamiento.getSelectedDate();
                    String fecha;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    fecha = sdf.format(c1.getTime());//fecha de procesamiento

                    Object[] seleccionados = jListAnalistas.getSelectedValues();
                    int lim = seleccionados.length;
                    ArrayList procesados = new ArrayList();
                    if (lim != 0) {
                        for (int i = 0; i < lim; i++) {
                            ArrayList fila = new ArrayList();
                            ClaseObjetoParaLista banda = (ClaseObjetoParaLista) seleccionados[i];
                            int idprueba = banda.getId();
                            fila.add(idregistrogeneralmodificar);
                            fila.add(idprueba);
                            procesados.add(fila);
                        }
                    }

                    for (int i = 0; i < tabla; i++) {
                        ArrayList fila = new ArrayList();
                        int id_mu=0;
                        int blancas=0;
                        int rojas=0;
                        float hematocrito=0;
                        float hemoglobina=0;

                        try {
                            id_mu = Integer.parseInt(jTableMuestrasbru.getValueAt(i, 8).toString());
                            hematocrito = Float.parseFloat(jTableMuestrasbru.getValueAt(i, 9).toString());
                            hemoglobina = Float.parseFloat(jTableMuestrasbru.getValueAt(i, 10).toString());
                            rojas = Integer.parseInt(jTableMuestrasbru.getValueAt(i, 11).toString());
                            blancas = Integer.parseInt(jTableMuestrasbru.getValueAt(i, 12).toString());
                            fila.add(id_mu);
                            fila.add(hematocrito);
                            fila.add(hemoglobina);
                            fila.add(rojas);
                            fila.add(blancas);

                            resultados.add(fila);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error leyendo la tabla: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }//cierro el for

                    try {
                        this.iniciarResultado();
                        if (miCoordinadorResultado.registrarResultadoHematologia(resultados, fecha, procesados) == true) {
                                //limpiar_predio();
                            //pre.LlenarTabla();
                            jInternalFrameBrucelosisR.setVisible(false);
                        }

                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Error en el Ingreso de Resultados: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
        }

    }//GEN-LAST:event_jButtonResultadoBruMouseClicked

    private void jButtonAbrirBuscarRegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAbrirBuscarRegistroMouseEntered
        /* jLabelImagenAjusta aju=new jLabelImagenAjusta();
         aju.ImagenJLabel(jLabelvisualizar, "iconos/presentabuscarregistro.jpg");*/
    }//GEN-LAST:event_jButtonAbrirBuscarRegistroMouseEntered

    private void jButtonAbrirBuscarRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAbrirBuscarRegistroMouseExited

    }//GEN-LAST:event_jButtonAbrirBuscarRegistroMouseExited

    private void jButtonAbrirParroquiaAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirParroquiaAgregarActionPerformed
        if (jComboBoxEstadoAgregar.getSelectedIndex() != 0 && jComboBoxMunicipioAgregar.getSelectedIndex() != 0) { // comparo si el estado y municipio seleccionado son validos, index!=0
            jInternalFrameAgregarParroquia.setVisible(true);//mostramos la ventana
            jTextFieldEstadoAgregarParroquia.setText(jComboBoxEstadoAgregar.getSelectedItem().toString()); //Asigno en agregar parroquia(jinternalframe) el estado seleccinado
            jTextFieldMunicipioAgregarParroquia.setText(jComboBoxMunicipioAgregar.getSelectedItem().toString());//asigno en agregar parroquia(jinternalframe) el municipio seleccionado
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un Estado y Municipio primero"); // muestro mensaje de requerimiento de datos para agregar una parroquia
        }
    }//GEN-LAST:event_jButtonAbrirParroquiaAgregarActionPerformed

    private void jButtonAgregarPredioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarPredioActionPerformed
        try {

            this.iniciarPredio();
            PredioBO miPredio = new PredioBO();
            //Point posicion = new Point(Integer.parseInt(jTextFieldUtmNorteAgregar.getText()),Integer.parseInt(jTextFieldUtmEsteAgregar.getText())); 
            //Point posicion = new Point(10413412,51241241); 
            ClaseObjetoParaComboBox Estado = (ClaseObjetoParaComboBox) jComboBoxEstadoAgregar.getSelectedItem();
            ClaseObjetoParaComboBox Municipio = (ClaseObjetoParaComboBox) jComboBoxMunicipioAgregar.getSelectedItem();
            ClaseObjetoParaComboBox Parroquia = (ClaseObjetoParaComboBox) jComboBoxParroquiaAgregar.getSelectedItem();

            miPredio.setId_propietario(idusact);
            miPredio.setPredio(jTextFieldPredioAgregar.getText());
            miPredio.setDireccion(jTextFieldDireccionAgregar.getText());
            miPredio.setSuperficie(jTextFieldSuperficieAgregar.getText());
            miPredio.setId_estado(Estado.getId());
            miPredio.setId_municipio(Municipio.getId());
            miPredio.setId_parroquia(Parroquia.getId());
            miPredio.setUtm_este(Float.parseFloat(jTextFieldUtmEsteAgregar.getText()));
            miPredio.setUtm_norte(Float.parseFloat(jTextFieldUtmNorteAgregar.getText()));
            Object[] seleccionados = jListFinalidad.getSelectedValues();
            int lim = seleccionados.length;
            ArrayList n = new ArrayList();
            if (lim != 0) {
                for (int i = 0; i < lim; i++) {
                    ArrayList fila = new ArrayList();
                    ClaseObjetoParaLista banda = (ClaseObjetoParaLista) seleccionados[i];
                    int idprueba = banda.getId();
                    fila.add(idprueba);
                    n.add(fila);
                }
            }

            if (jButtonAgregarPredio.getText().equals("Agregar") == true) {

                if (miCoordinadorPredio.registrarPredio(miPredio, n) == true) {
                    limpiar_predio();
                    pre.LlenarTabla();
                }

            } else if (jButtonAgregarPredio.getText().equals("Modificar") == true) {

                int respuesta = JOptionPane.showConfirmDialog(this,
                        "Desea modificar los datos del predio seleccionado?", "Confirmacion",
                        JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_NO_OPTION) {
                    miPredio.setId_predio(idpreact);
                    if (miCoordinadorPredio.modificarPredio(miPredio, n) == true) {
                        limpiar_predio();
                        pre.LlenarTabla();
                    }
                }
                jInternalFramePrediosAsociados.setVisible(false);

            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonAgregarPredioActionPerformed

    private void jButtonAbrirAgregarPrediosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirAgregarPrediosActionPerformed
        jInternalFramePrediosAsociados.setVisible(true);
        jInternalFramePrediosAsociados.toFront();
    }//GEN-LAST:event_jButtonAbrirAgregarPrediosActionPerformed

    private void jButtonAbrirModificarPrediosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirModificarPrediosActionPerformed
        if (jTablePredios.isEnabled()) {
            int fil = jTablePredios.getSelectedRow();//obtiene la fila seleccionada
            jTextFieldPredioAgregar.setText(jTablePredios.getModel().getValueAt(fil, 0).toString());
            jTextFieldUtmEsteAgregar.setText(jTablePredios.getModel().getValueAt(fil, 5).toString());
            jTextFieldUtmNorteAgregar.setText(jTablePredios.getModel().getValueAt(fil, 6).toString());
            jTextFieldSuperficieAgregar.setText(jTablePredios.getModel().getValueAt(fil, 7).toString());
            jTextFieldDireccionAgregar.setText(jTablePredios.getModel().getValueAt(fil, 4).toString());
            comboestado.setjComboBox(jComboBoxEstadoAgregar, jTablePredios.getModel().getValueAt(fil, 1).toString());
            combomunicipio.setjComboBox(jComboBoxMunicipioAgregar, jTablePredios.getModel().getValueAt(fil, 2).toString());
            comboparroquia.setjComboBox(jComboBoxParroquiaAgregar, jTablePredios.getModel().getValueAt(fil, 3).toString());

            //cargar los valores seleccionados para jListFinalidad
            this.iniciarPredio();
            ArrayList analistas;
            ArrayList lista = new ArrayList();
            analistas = miCoordinadorPredio.cargarFinalidadPredio(idpreact);
            //cargar los analistas
            int cantidad_e = analistas.size();
            Lista l = new Lista();
            for (int i = 0; i < cantidad_e; i++) {
                ArrayList muestra;
                muestra = (ArrayList) analistas.get(i);
                String cedula = muestra.get(1).toString();
                l.setjList(jListFinalidad, cedula);
                lista.add(jListFinalidad.getSelectedIndex());
            }
            int[] indice = new int[cantidad_e];// creamos array del tamaño de los valorers seleccionados
            for (int i = 0; i < cantidad_e; i++) {
                indice[i] = (Integer) lista.get(i); //Asignamos al array los valores de los index guardados en la lista                       
            }
            jListFinalidad.setSelectedIndices(indice);// seleccionamos los multiples valores en la lista
            jButtonAgregarPredio.setText("Modificar");
            jInternalFramePrediosAsociados.setVisible(true);
            jInternalFramePrediosAsociados.toFront();
        }
    }//GEN-LAST:event_jButtonAbrirModificarPrediosActionPerformed

    private void jInternalFramePrediosAsociadosInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_jInternalFramePrediosAsociadosInternalFrameClosed
        jButtonAgregarPredio.setText("Agregar");
        jTextFieldPredioAgregar.setText(null);
        jTextFieldSuperficieAgregar.setText(null);
        jTextFieldUtmNorteAgregar.setText(null);
        jTextFieldUtmEsteAgregar.setText(null);
        jTextFieldDireccionAgregar.setText(null);
        jTextFieldHierro.setText(null);
        if ((jComboBoxParroquiaAgregar.isEnabled() == true) && (jComboBoxParroquiaAgregar.getSelectedIndex() != -1)) {
            jComboBoxParroquiaAgregar.setSelectedIndex(0);
        }
        if ((jComboBoxMunicipioAgregar.isEnabled() == true) && (jComboBoxMunicipioAgregar.getSelectedIndex() != -1)) {
            jComboBoxMunicipioAgregar.setSelectedIndex(0);
        }
        if ((jComboBoxEstadoAgregar.isEnabled() == true) && (jComboBoxEstadoAgregar.getSelectedIndex() != -1)) {
            jComboBoxEstadoAgregar.setSelectedIndex(0);
        }

    }//GEN-LAST:event_jInternalFramePrediosAsociadosInternalFrameClosed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed

        //Seleccionamos el reporte a generar en base al tipo de diagnostico       
        if (tipoanalisisbuscar.equals("Hemoparásitos")) {

            EjecutarReporte r = new EjecutarReporte();
            r.startReport("Protocol", idregistrogeneralmodificar, "diagnostico");

        } else if (tipoanalisisbuscar.equals("Coprología")) {
            EjecutarReporte r = new EjecutarReporte();
            r.startReport("Protocol", idregistrogeneralmodificar, "diagnostico_cop");
        }else if (tipoanalisisbuscar.equals("Hematología")) {
            EjecutarReporte r = new EjecutarReporte();
            r.startReport("Protocol", idregistrogeneralmodificar, "diagnostico_hema");
        }else if (tipoanalisisbuscar.equals("Brucelosis")) {
            EjecutarReporte r = new EjecutarReporte();
            r.startReport("Protocol", idregistrogeneralmodificar, "diagnostico_bru");
        }else if (tipoanalisisbuscar.equals("Anemia Infecciosa")) {
            EjecutarReporte r = new EjecutarReporte();
            r.startReport("Protocol", idregistrogeneralmodificar, "diagnostico_ane");
        } else {
            JOptionPane.showMessageDialog(rootPane, "En Desarrollo, Contacte al Desarrollador de la aplicacion, Mas Informacion 'Ayuda' del Menu Principal");
        }
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonAgregarParroquiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarParroquiaActionPerformed
        try {
            this.iniciarParroquia();
            ParroquiaBO miParroquia = new ParroquiaBO();
            ClaseObjetoParaComboBox mun = (ClaseObjetoParaComboBox) jComboBoxMunicipioAgregar.getSelectedItem();
            miParroquia.setId_municipio(mun.getId());
            miParroquia.setParroquia(jTextFieldParroquiaAgregarParroquia.getText());

            if (miCoordinadorParroquia.registrarParroquia(miParroquia) == true) {
                limpiar_parroquia();
                jInternalFrameAgregarParroquia.setVisible(false);
                comboparroquia.LLenarBoxConID();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAgregarParroquiaActionPerformed

    private void jComboBoxTipoAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoAnalisisActionPerformed

    }//GEN-LAST:event_jComboBoxTipoAnalisisActionPerformed

    private void jButtonPrediosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrediosActionPerformed
        jInternalFramePredios.setVisible(true);
        pre.columnastabla(9, config.tabla_consulta_predios(idusact));
        pre.LlenarTabla();
        Integer tam_predio[] = {140, 100, 100, 100, 150, 70, 70, 70, 70};
        for (int i = 0; i < 9; i++) {
            jTablePredios.getColumnModel().getColumn(i).setPreferredWidth(tam_predio[i]);
            jTablePredios.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.CYAN.brighter(), Color.BLACK.darker()));
            if (i != 0) {
                jTablePredios.getColumnModel().getColumn(i).setCellRenderer(pre.render);
            }
        }
    }//GEN-LAST:event_jButtonPrediosActionPerformed

    private void jButtonPropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPropietarioActionPerformed
        jInternalFramePropietario.setVisible(true);
    }//GEN-LAST:event_jButtonPropietarioActionPerformed

    private void jButtonAgregarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarRegistroActionPerformed
        try {
            if (config.getSede_seleccionada() == null) {
                config.setSede_seleccionada(2);
            }

            this.iniciarRegistro();
            ClaseObjetoParaComboBox Analisis = (ClaseObjetoParaComboBox) jComboBoxTipoAnalisis.getSelectedItem();//variable para extraer el id del tipo de analisis
            int tipo_analisis = Analisis.getId();//almacenamos el valor del tipo de analisis (IdTip)              
            RegistroGeneralBO miRegistro = new RegistroGeneralBO();
            miRegistro.setId_sede(config.getSede_seleccionada());
            miRegistro.setId_analisis_tipo(tipo_analisis);
            miRegistro.setId_propietario(idusact);
            miRegistro.setId_predio(idpreact);
            miRegistro.setRemitente(jTextFieldRemitente.getText());
            Calendar c1 = dateChooserComboRecepcion.getSelectedDate();
            miRegistro.setFecha_recepcion(new java.sql.Date(c1.getTime().getTime()));
            miRegistro.setProtocolo(null);
            if (jButtonAgregarRegistro.getText().equals("Guardar") == true) {

                if (miCoordinadorRegistroGeneral.registrarRegistro(miRegistro, filasid) == true) {
                    limpiar_registro();
                }

            } else if (jButtonAgregarRegistro.getText().equals("Modificar") == true) {

                int respuesta = JOptionPane.showConfirmDialog(this,
                        "Desea modificar los datos del registro?", "Confirmacion",
                        JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_NO_OPTION) {
                    miRegistro.setId_registro_general(idregistrogeneralmodificar);
                    if (miCoordinadorRegistroGeneral.modificarRegistro(miRegistro, filasid) == true) {
                        limpiar_registro();
                    }
                    jInternalFrameRegistroGeneral.setVisible(false);

                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error en el Ingreso de Datos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error en Operacion de conversion de tipos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButtonAgregarRegistroActionPerformed

    private void jButtonQuitarMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitarMuestraActionPerformed
        if (n_muestras > 0) {
            muestras.modelo.removeRow(filatabla);
            filasid.remove(filatabla);
            n_muestras -= 1;
            jLabelNMuestras.setText("Nº Muestras: " + Integer.toString(n_muestras));
        }
    }//GEN-LAST:event_jButtonQuitarMuestraActionPerformed

    private void jTableMuestrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMuestrasMouseClicked
        filatabla = jTableMuestras.getSelectedRow();
    }//GEN-LAST:event_jTableMuestrasMouseClicked

    private void jButtonAgregarMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarMuestraActionPerformed
        Object[] fila;//guarda los valores para agregarlos a la tabla
        //Prueba:  Almaceno los id del genero, especie y raza
        ArrayList columnasid = new ArrayList();
        //validamos que esten selecionados correctamente los jComboBox para los datos a agregar en la tabla
        if (taxo == null) {
            JOptionPane.showMessageDialog(rootPane, "Atención, Debe seleccionar la taxonomía del animal");
        } else if (jComboBoxEdadYear.getSelectedIndex() == 0 || jComboBoxEdadMeses.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Atención, Seleccione la edad en Años y Meses del animal");
        } else if (jComboBoxMuestra.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Atención, Seleccione el tipo de muestra");
        } else {
            ClaseObjetoParaComboBox muestraid = (ClaseObjetoParaComboBox) jComboBoxMuestra.getSelectedItem();//extraemos el id del tipo de muestra

            fila = new Object[8];// contendra los valores para agregar en la tabla
            //asignamos los valores para la fila a agregar en la tabla
            fila[0] = taxo.getId();
            columnasid.add(taxo.getId());
            fila[1] = taxo.getNombre();
            fila[2] = jComboBoxMuestra.getSelectedItem().toString();
            columnasid.add(muestraid.getId());
            fila[3] = jTextFieldHierro.getText();
            columnasid.add(jTextFieldHierro.getText());
            fila[6] = (Integer.parseInt(jComboBoxEdadYear.getSelectedItem().toString()) * 12) + Integer.parseInt(jComboBoxEdadMeses.getSelectedItem().toString());
            columnasid.add((Integer.parseInt(jComboBoxEdadYear.getSelectedItem().toString()) * 12) + Integer.parseInt(jComboBoxEdadMeses.getSelectedItem().toString()));
            fila[5] = jComboBoxSexo.getSelectedItem().toString();
            columnasid.add(jComboBoxSexo.getSelectedItem().toString());
            if (jCheckBoxVacunado.isSelected() == true) {
                Calendar c1 = dateChooserComboVacunacion.getSelectedDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                fila[4] = sdf.format(c1.getTime());
                columnasid.add(sdf.format(c1.getTime()));
            } else {
                fila[4] = null;
                columnasid.add(null);
            }
            fila[7] = jTextFieldObservaciones.getText();
            columnasid.add(jTextFieldObservaciones.getText());
            muestras.modelo.addRow(fila);
        }
        filasid.add(columnasid);//agrego el tipo de muestra
        //asigno el tamaño de las columnas para la tabla muestras
        for (int i = 1; i < 8; i++) {
            jTableMuestras.getColumnModel().getColumn(i).setCellRenderer(muestras.render);
        }
        n_muestras += 1;
        jLabelNMuestras.setText("Nº Muestras: " + Integer.toString(n_muestras));
    }//GEN-LAST:event_jButtonAgregarMuestraActionPerformed

    private void jCheckBoxVacunadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxVacunadoItemStateChanged
        // si el jCheckBox esta selecccionado habilitamos para colocar la fecha de vacunacion
        if (jCheckBoxVacunado.isSelected() == true) {
            dateChooserComboVacunacion.setEnabled(true);
        } else {
            dateChooserComboVacunacion.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxVacunadoItemStateChanged

    private void jListTaxonValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListTaxonValueChanged
        taxo = (ClaseObjetoParaLista) jListTaxon.getSelectedValue(); //creamos objeto para extraer el id           
        @SuppressWarnings("UnusedAssignment")
        String url = null;//hay que limpiarlo para que lo muestre vacio
        jPanelUrl.add(label);
        label.setLocation(10, 30);
        if (taxo != null) {
            url = "http://www.itis.gov/servlet/SingleRpt/SingleRpt?search_topic=TSN&search_value=" + taxo.getId();
            label.setURL(url);
            label.setText("Rango Taxonomico: " + taxo.getRango() + ", Mas sobre " + taxo.getNombre());
            label.setSize(350, 15);
        }
    }//GEN-LAST:event_jListTaxonValueChanged

    private void jButtonTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxActionPerformed
        ClaseObjetoParaLista taxo_1 = (ClaseObjetoParaLista) jListTaxon.getSelectedValue(); //creamos objeto para extraer el id
        Integer tsn = taxo_1.getId();
        tax.consultalist(config.taxonomia(tsn), "tax.tsn", "tax.nombre_completo", "tax.rango_id", "ran.rango");//cargamos la consulta para cargar la especie
        tax.LLenarLista();//llenamos el combo de la especie

    }//GEN-LAST:event_jButtonTaxActionPerformed

    private void jButtonTaxResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxResetActionPerformed
        tax.consultalist(config.lista_taxon_inicial, "tax.tsn", "tax.nombre_completo", "tax.rango_id", "ran.rango");//cargamos la consulta para cargar la especie
        tax.LLenarLista();//llenamos el combo de la especie
        label.setURL("");
        label.setText("");
    }//GEN-LAST:event_jButtonTaxResetActionPerformed

    private void jComboBoxEdadYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEdadYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEdadYearActionPerformed

    private void jButtonBusquedaRapidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBusquedaRapidaActionPerformed
        ClaseObjetoParaComboBox taxo_2 = (ClaseObjetoParaComboBox) jComboBoxGrupo.getSelectedItem(); //creamos objeto para extraer el id
        Integer tsn = taxo_2.getId();
        tax.consultalist(config.taxonomia_rapida(tsn), "tax.tsn", "tax.nombre_completo", "tax.rango_id", "ran.rango");//cargamos la consulta para cargar la especie
        tax.LLenarLista();//llenamos el combo de la especie
    }//GEN-LAST:event_jButtonBusquedaRapidaActionPerformed

    private void jButtonAbrirAgregarRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAbrirAgregarRegistroMouseClicked
        jInternalFrameRegistroGeneral.setVisible(true);
    }//GEN-LAST:event_jButtonAbrirAgregarRegistroMouseClicked

    private void jListAnalistasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListAnalistasValueChanged

    }//GEN-LAST:event_jListAnalistasValueChanged

    private void jTextFieldTelefonoAgregarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoAgregarKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTelefonoAgregarKeyTyped

    private void jTextFieldTelefonoModificarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoModificarKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTelefonoModificarKeyTyped

    private void jTextFieldRifAgregarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRifAgregarKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldRifAgregarKeyTyped

    private void jTextFieldRifModificarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRifModificarKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldRifModificarKeyTyped

    private void jButtonTaxOrganismosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxOrganismosActionPerformed
        ClaseObjetoParaLista taxo_3 = (ClaseObjetoParaLista) jListTaxonOrganismos.getSelectedValue(); //creamos objeto para extraer el id
        Integer tsn = taxo_3.getId();
        tax.consultalist(config.taxonomia(tsn), "tax.tsn", "tax.nombre_completo", "tax.rango_id", "ran.rango");//cargamos la consulta para cargar la especie
        tax.LLenarLista();//llenamos el combo de la especie
    }//GEN-LAST:event_jButtonTaxOrganismosActionPerformed

    private void jListTaxonOrganismosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListTaxonOrganismosValueChanged
        taxo = (ClaseObjetoParaLista) jListTaxonOrganismos.getSelectedValue(); //creamos objeto para extraer el id           
        @SuppressWarnings("UnusedAssignment")
        String url = null;
        jPanelUrl1.add(label);
        label.setLocation(10, 30);
        if (taxo != null) {
            url = "http://www.itis.gov/servlet/SingleRpt/SingleRpt?search_topic=TSN&search_value=" + taxo.getId();
            label.setURL(url);
            label.setText("Rango Taxonomico: " + taxo.getRango() + ", Mas sobre " + taxo.getNombre());
            label.setSize(350, 15);
        }
    }//GEN-LAST:event_jListTaxonOrganismosValueChanged

    private void jButtonTaxReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxReset1ActionPerformed
        tax.consultalist(config.lista_taxon_inicial, "tax.tsn", "tax.nombre_completo", "tax.rango_id", "ran.rango");//cargamos la consulta para cargar la especie
        tax.LLenarLista();//llenamos el combo de la especie
        label.setURL("");
        label.setText("");
    }//GEN-LAST:event_jButtonTaxReset1ActionPerformed

    private void jButtonAgregarOrganismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarOrganismoActionPerformed

        Object[] fila;//guarda los valores para agregarlos a la tabla

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList columnasid = new ArrayList();
        //validamos que esten selecionados correctamente los jComboBox para los datos a agregar en la tabla
        if (taxo == null) {
            JOptionPane.showMessageDialog(rootPane, "Atención, Debe seleccionar la taxonomía del organismo");
        } else {
            
            if (jTableDetalleResultado.getRowCount() > 0) {
                        fila = new Object[3];// contendra los valores para agregar en la tabla
                        //asignamos los valores para la fila a agregar en la tabla
                        fila[0] = taxo.getId();
                        columnasid.add(taxo.getId());
                        fila[1] = taxo.getNombre();
                        fila[2] = jTableMuestrasbru.getValueAt(indexresultadobru, 8);
                        //columnasid.add(idregistrogeneralmodificar+1);
                        boolean coincidencia=false;
                for (int i = 0; i < jTableDetalleResultado.getRowCount(); i++) {

                    if (Integer.parseInt(jTableDetalleResultado.getValueAt(i, 0).toString()) ==  (int)fila[0]) {
                            coincidencia=true;
                           
                        
                    } 

                }
                if(coincidencia==true){
                    JOptionPane.showMessageDialog(rootPane, "Atención, ya se agregó este organismo, seleccione otro");
                }else if(coincidencia==false){
                    detalle.modelo.addRow(fila);
                }
                
                
            } else if(jTableDetalleResultado.getRowCount() ==0) {
                fila = new Object[3];// contendra los valores para agregar en la tabla
                //asignamos los valores para la fila a agregar en la tabla
                fila[0] = taxo.getId();
                columnasid.add(taxo.getId());
                fila[1] = taxo.getNombre();
                fila[2] = jTableMuestrasbru.getValueAt(indexresultadobru, 8);
                //columnasid.add(idregistrogeneralmodificar+1);

                detalle.modelo.addRow(fila);
            }
            
            


        }

        //asigno el tamaño de las columnas para la tabla muestras
        jTableDetalleResultado.getColumnModel().getColumn(0).setCellRenderer(detalle.render);
        jTableDetalleResultado.getColumnModel().getColumn(1).setCellRenderer(detalle.render);
        jTableDetalleResultado.getColumnModel().getColumn(2).setCellRenderer(detalle.render);

    }//GEN-LAST:event_jButtonAgregarOrganismoActionPerformed

    private void jButtonTerminarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminarCargaActionPerformed
        ArrayList pasar = new ArrayList();
        ArrayList pasarString = new ArrayList();
        resultHPBo = new ResultadoHemoparasitoBO();
        List<HemoparasitoDetalleBO> hemoparasitoDetalleBo = new ArrayList<>();
        HemoparasitoDetalleBO hemoPBo;
        resultHPBo.setId_registro_muestra(Integer.parseInt(jTableMuestrasbru.getModel().getValueAt(indexresultadobru, 8).toString()));

        for (int i = 0; i < jTableDetalleResultado.getRowCount(); i++) {
            hemoPBo = new HemoparasitoDetalleBO();
            hemoPBo.setId_tsn_especie(Integer.parseInt(jTableDetalleResultado.getValueAt(i, 0).toString()));
            hemoparasitoDetalleBo.add(hemoPBo);
            pasar.add(jTableDetalleResultado.getValueAt(i, 0));
            pasarString.add(jTableDetalleResultado.getValueAt(i, 1));

        }
        resultHPBo.setHemoparasitoDetalleBo(hemoparasitoDetalleBo);

        //pasar=filasiddet;
        jTableMuestrasbru.setValueAt(pasar, indexresultadobru, 9);
        jTableMuestrasbru.setValueAt(pasarString, indexresultadobru, 10);
        jInternalFrameDetalleResultado.setVisible(false);
    }//GEN-LAST:event_jButtonTerminarCargaActionPerformed

    private void jTableDetalleResultadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDetalleResultadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDetalleResultadoMouseClicked

    private void jButtonQuitarMuestra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitarMuestra1ActionPerformed
        if (jTableDetalleResultado.getRowCount() > 0) {
            detalle.modelo.removeRow(filatabladet);
        }
    }//GEN-LAST:event_jButtonQuitarMuestra1ActionPerformed

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarActionPerformed
        if (jTableMuestrasbru.getSelectedRow()>=0) {
            while (jTableDetalleResultado.getRowCount() > 0) {
                detalle.modelo.removeRow(filatabladet);
            }
            jInternalFrameDetalleResultado.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una muestra para agregar los organismos idenficados");
        }


    }//GEN-LAST:event_jButtonCargarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ClaseObjetoParaComboBox Estado = (ClaseObjetoParaComboBox) jComboBoxEstadoAgregar.getSelectedItem();
        ClaseObjetoParaComboBox Municipio = (ClaseObjetoParaComboBox) jComboBoxMunicipioAgregar.getSelectedItem();
        ClaseObjetoParaComboBox Parroquia = (ClaseObjetoParaComboBox) jComboBoxParroquiaAgregar.getSelectedItem();
        String eval=Parroquia.getNombre().substring(0, 9);
        String par="";
        if(eval.equalsIgnoreCase("No Urbana")){
        par=Parroquia.getNombre().substring(10, Parroquia.getNombre().length());
        }else{
        par= Parroquia.getNombre();   
        }
        //JOptionPane.showMessageDialog(rootPane, "Cadena "+eval);
        //JOptionPane.showMessageDialog(rootPane, "Par "+par);
        String Direccion="";
        Direccion= par+", "+Municipio.getNombre()+", "+Estado.getNombre();

        if(!Direccion.isEmpty()){
            Geocoding ObjGeocoding=new Geocoding();
            Point2D.Double resultado= new Point2D.Double();
            try {
                resultado = ObjGeocoding.getCoordinates(Direccion);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(RegistroGeneral.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(RegistroGeneral.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(resultado.x!=0.0 && resultado.y!=0.0){
                jTextFieldUtmNorteAgregar.setText(String.valueOf(resultado.x));
                jTextFieldUtmEsteAgregar.setText(String.valueOf(resultado.y));
                JOptionPane.showMessageDialog(rootPane, "Se encontró coordenadas para: "+ObjGeocoding.getAddressFound());
                
                ObjUbicacion.setLatitud(resultado.x);
                ObjUbicacion.setLongitud(resultado.y);
                ObjUbicacion.setDireccion(ObjGeocoding.getAddressFound());
            }else{
                JOptionPane.showMessageDialog(rootPane, "No se encontraron coordenadas, comprobar conexión a internet");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrameConFondo reg = new RegistroGeneral();
                reg.setVisible(true);
                reg.setLocationRelativeTo(null);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserComboProcesamiento;
    private datechooser.beans.DateChooserCombo dateChooserComboRecepcion;
    private datechooser.beans.DateChooserCombo dateChooserComboVacunacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAbrirAgregarPredios;
    private javax.swing.JButton jButtonAbrirAgregarRegistro;
    private javax.swing.JButton jButtonAbrirBuscarRegistro;
    private javax.swing.JButton jButtonAbrirModificarPredios;
    private javax.swing.JButton jButtonAbrirParroquiaAgregar;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonAgregarMuestra;
    private javax.swing.JButton jButtonAgregarOrganismo;
    private javax.swing.JButton jButtonAgregarParroquia;
    private javax.swing.JButton jButtonAgregarPredio;
    private javax.swing.JButton jButtonAgregarRegistro;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonBuscarPor;
    private javax.swing.JButton jButtonBusquedaRapida;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEliminarRegistro;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonLimpiarPropietario;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonModificarRegistro;
    private javax.swing.JButton jButtonPredio;
    private javax.swing.JButton jButtonPredios;
    private javax.swing.JButton jButtonPropietario;
    private javax.swing.JButton jButtonQuitarMuestra;
    private javax.swing.JButton jButtonQuitarMuestra1;
    private javax.swing.JButton jButtonResultado;
    private javax.swing.JButton jButtonResultadoBru;
    private javax.swing.JButton jButtonTax;
    private javax.swing.JButton jButtonTaxOrganismos;
    private javax.swing.JButton jButtonTaxReset;
    private javax.swing.JButton jButtonTaxReset1;
    private javax.swing.JButton jButtonTerminarCarga;
    private javax.swing.JCheckBox jCheckBoxFiltro;
    private javax.swing.JCheckBox jCheckBoxVacunado;
    private javax.swing.JComboBox jComboBoxBuscarPor;
    private javax.swing.JComboBox jComboBoxEdadMeses;
    private javax.swing.JComboBox jComboBoxEdadYear;
    private javax.swing.JComboBox jComboBoxEstadoAgregar;
    private javax.swing.JComboBox jComboBoxGrupo;
    private javax.swing.JComboBox jComboBoxMuestra;
    private javax.swing.JComboBox jComboBoxMunicipioAgregar;
    private javax.swing.JComboBox jComboBoxParroquiaAgregar;
    private javax.swing.JComboBox jComboBoxRif;
    private javax.swing.JComboBox jComboBoxRifAgregar;
    private javax.swing.JComboBox jComboBoxRifModificar;
    private javax.swing.JComboBox jComboBoxSexo;
    private javax.swing.JComboBox jComboBoxTipoAnalisis;
    private javax.swing.JComboBox jComboBoxTipoAnalisisBuscar;
    private javax.swing.JInternalFrame jInternalFrameAgregarParroquia;
    private javax.swing.JInternalFrame jInternalFrameBrucelosisR;
    private javax.swing.JInternalFrame jInternalFrameBuscar;
    private javax.swing.JInternalFrame jInternalFrameDetalleResultado;
    private javax.swing.JInternalFrame jInternalFramePredios;
    private javax.swing.JInternalFrame jInternalFramePrediosAsociados;
    private javax.swing.JInternalFrame jInternalFramePropietario;
    private javax.swing.JInternalFrame jInternalFrameRegistroGeneral;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelAnalisisBuscar;
    private javax.swing.JLabel jLabelAnalisisBuscarPor;
    private javax.swing.JLabel jLabelEdad;
    private javax.swing.JLabel jLabelEstado3;
    private javax.swing.JLabel jLabelEstadoAgregarParroquia;
    private javax.swing.JLabel jLabelFechaRG;
    private javax.swing.JLabel jLabelFechaRecepcion;
    private javax.swing.JLabel jLabelFinalidad3;
    private javax.swing.JLabel jLabelGenero;
    private javax.swing.JLabel jLabelGenero1;
    private javax.swing.JLabel jLabelGenero2;
    private javax.swing.JLabel jLabelGenero3;
    private javax.swing.JLabel jLabelGenero7;
    private javax.swing.JLabel jLabelIdentificacion;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelLogo_des;
    private javax.swing.JLabel jLabelMunicipio3;
    private javax.swing.JLabel jLabelMunicipioAgregarParroquia;
    private javax.swing.JLabel jLabelNMuestras;
    private javax.swing.JLabel jLabelNombreAgregar;
    private javax.swing.JLabel jLabelNombreAgregar1;
    private javax.swing.JLabel jLabelNombreModificar;
    private javax.swing.JLabel jLabelNombreModificar1;
    private javax.swing.JLabel jLabelObservaciones;
    private javax.swing.JLabel jLabelParroquia3;
    private javax.swing.JLabel jLabelParroquiaAgregarParroquia;
    private javax.swing.JLabel jLabelPredio;
    private javax.swing.JLabel jLabelPredio3;
    private javax.swing.JLabel jLabelPropietario;
    private javax.swing.JLabel jLabelRemitente;
    private javax.swing.JLabel jLabelResultadoBrucelosis;
    private javax.swing.JLabel jLabelRif;
    private javax.swing.JLabel jLabelRifAgregar;
    private javax.swing.JLabel jLabelRifModificar;
    private javax.swing.JLabel jLabelSector3;
    private javax.swing.JLabel jLabelSuperficie3;
    private javax.swing.JLabel jLabelTelefonoAgregar;
    private javax.swing.JLabel jLabelTelefonoModificar;
    private javax.swing.JLabel jLabelTipoAnalisis;
    private javax.swing.JLabel jLabelUTM3;
    private javax.swing.JLabel jLabelUtmEste3;
    private javax.swing.JLabel jLabelUtmNorte3;
    private javax.swing.JLabel jLabelVacunacion;
    private javax.swing.JLayeredPane jLayeredPaneSolicitudes;
    private javax.swing.JList jListAnalistas;
    private javax.swing.JList jListFinalidad;
    private javax.swing.JList jListTaxon;
    private javax.swing.JList jListTaxonOrganismos;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAgregar;
    private javax.swing.JPanel jPanelBotonesAgregar;
    private javax.swing.JPanel jPanelBotonesModificar;
    private javax.swing.JPanel jPanelBotonesSolicitud;
    private javax.swing.JPanel jPanelBrucelosisR;
    private javax.swing.JPanel jPanelBucar;
    private javax.swing.JPanel jPanelBuscar;
    private javax.swing.JPanel jPanelDatosGeneralesRG;
    private javax.swing.JPanel jPanelModificar;
    private javax.swing.JPanel jPanelOpcBus;
    private javax.swing.JPanel jPanelPrediosTabla;
    private javax.swing.JPanel jPanelPropietariosTabla;
    private javax.swing.JPanel jPanelUrl;
    private javax.swing.JPanel jPanelUrl1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparatorBuscar1;
    private javax.swing.JSeparator jSeparatorBuscar2;
    private javax.swing.JSeparator jSeparatorSolicitudes;
    private javax.swing.JTabbedPane jTabbedPanePropietarios;
    private javax.swing.JTable jTableBuscar;
    private javax.swing.JTable jTableDetalleResultado;
    private javax.swing.JTable jTableMuestras;
    private javax.swing.JTable jTableMuestrasbru;
    private javax.swing.JTable jTablePredios;
    private javax.swing.JTable jTablePropietarios;
    private javax.swing.JTextField jTextFieldApellidoAgregar;
    private javax.swing.JTextField jTextFieldApellidoModificar;
    private javax.swing.JTextField jTextFieldDireccionAgregar;
    private javax.swing.JTextField jTextFieldEstadoAgregarParroquia;
    private javax.swing.JTextField jTextFieldHierro;
    private javax.swing.JTextField jTextFieldMunicipioAgregarParroquia;
    private javax.swing.JTextField jTextFieldNombreAgregar;
    private javax.swing.JTextField jTextFieldNombreModificar;
    private javax.swing.JTextField jTextFieldObservaciones;
    private javax.swing.JTextField jTextFieldParroquiaAgregarParroquia;
    private javax.swing.JTextField jTextFieldPredio;
    private javax.swing.JTextField jTextFieldPredioAgregar;
    private javax.swing.JTextField jTextFieldProSelec;
    private javax.swing.JTextField jTextFieldPropietario;
    private javax.swing.JTextField jTextFieldRemitente;
    private javax.swing.JTextField jTextFieldRif;
    private javax.swing.JTextField jTextFieldRifAgregar;
    private javax.swing.JTextField jTextFieldRifModificar;
    private javax.swing.JTextField jTextFieldSuperficieAgregar;
    private javax.swing.JTextField jTextFieldTelefonoAgregar;
    private javax.swing.JTextField jTextFieldTelefonoModificar;
    private javax.swing.JTextField jTextFieldUtmEsteAgregar;
    private javax.swing.JTextField jTextFieldUtmNorteAgregar;
    // End of variables declaration//GEN-END:variables
}
