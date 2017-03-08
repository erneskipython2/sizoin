package modeloDAO;

import java.awt.Component;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import modeloBO.RegistroGeneralBO;
import sizin.ClaseObjetoParaComboBox;
import sizin.Conexion;
import sizin.MyTableModel;

/**
 * Clase que maneja los resultados para el analisis de brucelosis
 *
 * @author Erneski Coronado
 */
public class HemoparasitoDAO extends JTable {

    Object[][] fila = new Object[][]{{"Nombre Científico", "Nombre Común", "Tipo de Muestra", "Identificación", "Edad", "Sexo", "F. Vacunación", "Observaciones", "ID", "", "Tipo de prueba", "Huevos y Larvas"}};
    ;// se le coloca el numero de columnas en el metodo columnastabla
    
    //String sqltabla = "Select mu.idmuestrasgeneral, mu.Hierro, mu.IdAnimal, mu.IdEspecie, mu.IdRaza, mu.IdRaza, mu.Vacunado, mu.FechaVacuna, mu.Sexo, mu.Edad, mu.Observaciones, ang.Nombre, ane.Nombre, anr.Nombre, angr.Nombre FROM muestrasgeneral mu INNER JOIN animal ang ON mu.IdAnimal=ang.idAnimal INNER JOIN animalespecie ane ON mu.IdEspecie=ane.idanimalespecie INNER JOIN animalraza anr ON mu.IdRaza=anr.idanimalraza INNER JOIN animalgrupo angr ON mu.IdGrupo=angr.idanimalgrupo WHERE mu.idregistrogeneral=5";
    String[] columnNames = new String[]{"Nombre Científico", "Nombre Común", "Tipo de Muestra", "Identificación", "Edad", "Sexo", "F. Vacunación", "Observaciones", "ID", "Taxon. Nº", "Resultado", "Tipo de prueba", "Descripción"};
    //Object [][] data      = new Object[][]{{"Bovino","Toro","Padrote","123","21 años","Macho","Si","12/06/2013","Ninguna","12",true} };
    // MyTableModel modelo = new MyTableModel(columnNames, data);

    public MyTableModel modelo;
    int id = 0;

    public ArrayList buscarAnalistas_Fecha(int id_registro_general) {
        Conexion conex = new Conexion();
        ArrayList analistas = new ArrayList();
        boolean existe = false;
        try {
            //Statement estatuto = conex.getConnection().createStatement();

            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT reg.id_personal, concat_ws(' ',per.cedula,per.nombre,per.apellido) as cedula,  per.num_acreditacion FROM registro_procesado reg INNER join personal per ON reg.id_personal=per.id_personal WHERE reg.id_registro_general= ? ");
            consulta.setInt(1, id_registro_general);
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                ArrayList columnas = new ArrayList();
                columnas.add(res.getInt(1));
                columnas.add(res.getString(2));
                columnas.add(res.getString(3));
                analistas.add(columnas);

            }
            res.close();
            conex.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }

        return analistas;

    }

    public RegistroGeneralBO buscarFecha_Procesado_Fecha(int id_registro_general) {
        Conexion conex = new Conexion();
        RegistroGeneralBO miRegistro = new RegistroGeneralBO();

        try {
            //Statement estatuto = conex.getConnection().createStatement();

            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT fecha_procesado FROM registro_general WHERE id_registro_general= ? ");
            consulta.setInt(1, id_registro_general);
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                miRegistro.setFecha_procesado(res.getDate(1));

            }

            res.close();
            conex.desconectar();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }

        return miRegistro;

    }

    // esta funcion carga los datos de las muestras para llenar su resultado de brucelosis y carga los resultados si ya existen
    public void llenar(int id_registro_general) {
        Conexion conex = new Conexion();
        Statement stmt3;
        int numfilas = 0;
        Vector idmuestrasgeneral = new Vector();
        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement("select tax.nombre_completo, taxr.nombre_comun, tip.muestra_tipo, mu.identificacion, mu.edad_mes, mu.sexo, mu.fecha_vacunacion, mu.observacion, mu.id_registro_muestra from registro_muestra mu inner join taxonomia tax on mu.id_tsn_especie=tax.tsn left join taxonomia_rapida taxr on mu.id_tsn_especie=taxr.tsn_rapido inner join muestra_tipo tip on mu.id_muestra_tipo=tip.id_muestra_tipo where mu.id_registro_general = ? ");
            consulta.setInt(1, id_registro_general);
            try (ResultSet res = consulta.executeQuery()) {
                while (res.next()) {
                    numfilas++;
                    idmuestrasgeneral.add("id_registro_muestra= " + res.getInt(9));
                }
                res.beforeFirst();//devuelvo el result a su estado original
                //res.close();
                //conex.desconectar();

                //Cargamos los datos en el object para luego agregarlos al modelo de la tabla
                Object[][] fila_muestras = new Object[numfilas][13];//guarda los valores para agregarlos a la tabla
                int x = -1;

                //comentarios
                while (res.next()) {
                    x++;
                    //asignamos los valores para la fila a agregar en la tabla
                    fila_muestras[x][0] = res.getString(1);
                    fila_muestras[x][1] = res.getString(2);
                    fila_muestras[x][2] = res.getString(3);
                    fila_muestras[x][3] = res.getString(4);
                    fila_muestras[x][4] = res.getInt(5);
                    fila_muestras[x][5] = res.getString(6);
                    fila_muestras[x][6] = res.getDate(7);
                    fila_muestras[x][7] = res.getString(8);
                    fila_muestras[x][8] = res.getInt(9);
                    ArrayList a = new ArrayList();
                    a.add(0);
                    fila_muestras[x][9] = a;//id del taxonomia identificada
                    fila_muestras[x][10] = "[Sin organismos identificados]";//resultado
                    fila_muestras[x][11] = "";//tipo de prueba
                    fila_muestras[x][12] = "";//Descripcion del resultado
                }

                //cargamos los datos al modelo
                modelo = new MyTableModel(columnNames, fila_muestras);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se cargaron los datos de las muestras");
            System.out.println(e);
        }

        //llenamos los resultados si existen de las muestras si ya existen o fueron llenados
        try {

            //Creamos el string para la consulta
            String sqlmuestrasbru = "select hemo.id_resultado_hemoparasito, hemo.id_registro_muestra, hemo.id_analisis_prueba, hemo.observacion, pru.analisis_prueba from resultado_hemoparasito hemo inner join analisis_prueba pru on hemo.id_analisis_prueba=pru.id_analisis_prueba WHERE ";

            for (int i = 0; i < numfilas; i++) {
                //creo el string de la consulta
                if (i < numfilas - 1) {
                    sqlmuestrasbru += idmuestrasgeneral.get(i).toString() + " or ";
                } else {
                    sqlmuestrasbru += idmuestrasgeneral.get(i).toString();
                }
            }
         //sqlmuestrasbru="Select * FROM resultadobrucelosis WHERE idmuestrasgeneral= 24 or idmuestrasgeneral= 25 or idmuestrasgeneral= 26 or idmuestrasgeneral= 27"; quedara algo asi

            //Cargo los resultados
            stmt3 = conex.getConnection().createStatement();
            ResultSet rsmuestra;
            rsmuestra = stmt3.executeQuery(sqlmuestrasbru);
            int medir = 0;//compara con el numero de filas para cargar los resultados
            while (rsmuestra.next()) {
                if (medir < numfilas) {
                    //cargar los detalles
                    Statement stmt4 = conex.getConnection().createStatement();
                    ResultSet rsdetalle;
                    ArrayList tsn = new ArrayList();
                    ArrayList resultado = new ArrayList();
                    rsdetalle = stmt4.executeQuery("select det.id_tsn_especie, tax.nombre_completo from hemoparasito_detalle det inner join taxonomia tax on tax.tsn=det.id_tsn_especie where id_resultado_hemoparasito=" + rsmuestra.getInt(1));
                    while (rsdetalle.next()) {
                        tsn.add(rsdetalle.getInt(1));
                        resultado.add(rsdetalle.getString(2));
                    }

                    modelo.setValueAt(new ClaseObjetoParaComboBox(rsmuestra.getInt(3), rsmuestra.getString(5)), medir, 11);//cargar el tipo de prueba seleccionado
                    modelo.setValueAt(rsmuestra.getString(4), medir, 12);//cargar la descripcion del resultado
                    if (tsn.isEmpty()) {
                        ArrayList a = new ArrayList();
                        a.add(0);
                        modelo.setValueAt(a, medir, 9);
                    } else {
                        modelo.setValueAt(tsn, medir, 9);
                    }
                    if (resultado.isEmpty()) {
                        ArrayList b = new ArrayList();

                        b.add("Sin organismos identificados");
                        ;
                        modelo.setValueAt(b, medir, 10);
                    } else {
                        modelo.setValueAt(resultado, medir, 10);
                    }

                }
                medir++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se cargaron los datos de las muestras");
            System.out.println(e);
        }
        conex.desconectar();
    }

    public boolean registrarResultado(ArrayList m, String procesado, ArrayList n) throws ParseException {
        Conexion conex = new Conexion();
        boolean realizado = false;
        int id_registro = 0;

        try {
            conex.getConnection().setAutoCommit(false);

            //eliminar los resultados     
            int cantidad_e = m.size();
            for (int i = 0; i < cantidad_e; i++) {
                CallableStatement cst = conex.getConnection().prepareCall("{call eliminar_hemoparasito (?)}");
                ArrayList muestra = new ArrayList();
                muestra = (ArrayList) m.get(i);
                cst.setInt(1, Integer.parseInt(muestra.get(0).toString()));
                cst.execute();

            }

            //guardar los resultados
            int cantidad = m.size();
            //int id_resultado = 0;

            for (int i = 0; i < cantidad; i++) {
                int id_resultado = 0;
                PreparedStatement estatuto = null;
                String sql = "INSERT into resultado_hemoparasito "
                        + "(id_resultado_hemoparasito,id_registro_muestra,id_analisis_prueba,observacion)"
                        + " values(null,?,?,?)";
                estatuto = conex.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                ArrayList muestra = new ArrayList();
                muestra = (ArrayList) m.get(i);
                estatuto.setInt(1, Integer.parseInt(muestra.get(0).toString()));
                estatuto.setInt(2, Integer.parseInt(muestra.get(1).toString()));
                estatuto.setString(3, muestra.get(2).toString());

                //cst2.execute();                 
                if (estatuto.executeUpdate() == 1) {
                    ResultSet keyResultSet = estatuto.getGeneratedKeys();
                    if (keyResultSet.next()) {
                        id_resultado = keyResultSet.getInt(1);
                    }

                    //guardar los detalles
                    ArrayList r = new ArrayList();
                    r = (ArrayList) muestra.get(3);
                    int cantidad_ana = r.size();
                    for (int j = 0; j < cantidad_ana; j++) {
                        if (Integer.parseInt(r.get(j).toString()) > 0) {
                            CallableStatement cst3 = conex.getConnection().prepareCall("{call registro_hemoparasito_detalle (?,?)}");
                            cst3.setInt(1, id_resultado);
                            cst3.setInt(2, Integer.parseInt(r.get(j).toString()));
                            cst3.execute();
                        }

                    }

                }

                estatuto.close();
            }

            //eliminar los analistas
            int cantidad_ana_e = n.size();
            for (int i = 0; i < cantidad_ana_e; i++) {
                CallableStatement cst2 = conex.getConnection().prepareCall("{call eliminar_analista_resultado (?)}");
                ArrayList muestra = new ArrayList();
                muestra = (ArrayList) n.get(i);
                cst2.setInt(1, Integer.parseInt(muestra.get(0).toString()));
                cst2.execute();

            }

            //guardar los analistas
            int cantidad_ana = n.size();
            for (int i = 0; i < cantidad_ana; i++) {
                CallableStatement cst2 = conex.getConnection().prepareCall("{call registro_analista_resultado (?,?)}");
                ArrayList muestra = new ArrayList();
                muestra = (ArrayList) n.get(i);
                id_registro = Integer.parseInt(muestra.get(0).toString());
                cst2.setInt(1, Integer.parseInt(muestra.get(0).toString()));
                cst2.setInt(2, Integer.parseInt(muestra.get(1).toString()));
                cst2.execute();

            }
            CallableStatement cst3 = conex.getConnection().prepareCall("{call actualizar_fecha_procesado (?,?)}");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = sdf.parse(procesado);
            cst3.setDate(1, new java.sql.Date(fecha.getTime()));
            cst3.setInt(2, id_registro);
            cst3.execute();

            JOptionPane.showMessageDialog(null, "Se ha guardado Exitosamente los resultados", "Informacion", JOptionPane.INFORMATION_MESSAGE);

            conex.getConnection().commit();
            conex.desconectar();
            realizado = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Registro");
        }

        return realizado;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    //metodo para centrar los valores de una tabla
    public void centrar_datos(JTable jT, int col) {
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jT.getColumnModel().getColumn(col).setCellRenderer(modelocentrar);
    }

    //metodo para centrar contenido de una jtable
    public TableCellRenderer render = new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            l.setHorizontalAlignment(SwingConstants.CENTER);
            return l;
        }

        //metodo para hacer una celda no editable
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

}
