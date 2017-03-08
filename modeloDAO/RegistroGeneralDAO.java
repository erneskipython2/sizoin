package modeloDAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import sizin.Conexion;
import modeloBO.RegistroGeneralBO;
import modeloBO.RegistroMuestraBO;
import sizin.ConfiguracionGlobal;

/**
 * Clase para gestionar las solicitudes para análisis de muestras
 *
 * @see RegistroGeneralBO, RegistroMuestraBO, ConfiguracionGlobal, Conexion
 * @author Erneski Coronado
 */
public class RegistroGeneralDAO {

    /**
     * Permite consultar el usuario logueado para ser auditadas sus acciones
     */
    ConfiguracionGlobal config = new ConfiguracionGlobal();

    /**
     * Metodo para registrar una solicitud para analisis de muestras
     *
     * @param miRegistro Contiene los datos generales cargados desde la vista
     * para registrar una solicitud
     * @param m Contiene las muestras cargadas para la solicitud
     * @param conex gestiona la conexion a BD
     * @param realizado Almacena si se realizo la insercion en BD o no
     * @param newid_registro Almacena el id autoincrementado generado durante la inserción
     * @param estatuto Permite gestionar el envío de datos a la base de datos
     * @param sql Almacena la cadena con a instruccion de insercion sql
     * @param cst Permite gestionar el uso del proedimiento almacenado para generar el nº de protocolo
     * @param cst2 Permite gestionar el uso del procedimiento almacenado para el registro de muestras
     * @param audit Permite gestionar el uso del procedimiento almacenado oara la auditoria
     * @return realizado 
     *
     */
    public boolean registrarRegistro(RegistroGeneralBO miRegistro, ArrayList m) throws ParseException {
        Conexion conex = new Conexion();
        boolean realizado = false;
        int newid_registro = 0;
        try {
            conex.getConnection().setAutoCommit(false);
            PreparedStatement estatuto = null;
            String sql = "INSERT into registro_general "
                    + "(id_registro_general,id_sede,id_analisis_tipo,id_predio,remitente,fecha_recepcion,protocolo)"
                    + " values(null,?,?,?,?,?,?)";
            estatuto = conex.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            estatuto.setInt(1, miRegistro.getId_sede());
            estatuto.setInt(2, miRegistro.getId_analisis_tipo());
            estatuto.setInt(3, miRegistro.getId_predio());
            estatuto.setString(4, miRegistro.getRemitente());
            estatuto.setDate(5, miRegistro.getFecha_recepcion());
            estatuto.setString(6, miRegistro.getProtocolo());
            if (estatuto.executeUpdate() == 1) {
                realizado = true;
            }
            //obener el ultimo id generado
            ResultSet keyResultSet = estatuto.getGeneratedKeys();
            //int newCustomerId = 0;
            if (keyResultSet.next()) {
                newid_registro = (int) keyResultSet.getInt(1);
                CallableStatement cst = conex.getConnection().prepareCall("{call protocolo (?,?,?,?)}");//metodo que genera el numero de protocolo desde mysql
                cst.setInt(1, newid_registro);
                cst.setInt(2, miRegistro.getId_analisis_tipo());
                cst.setInt(3, miRegistro.getId_sede());
                cst.setString(4, miRegistro.getFecha_recepcion().toString());
                cst.execute();
                cst.close();
            }
            //pasar las muestras
            int cantidad = m.size();
            for (int i = 0; i < cantidad; i++) {
                CallableStatement cst2 = conex.getConnection().prepareCall("{call registrar_muestra (?,?,?,?,?,?,?,?,?)}");
                ArrayList muestra = new ArrayList();
                muestra = (ArrayList) m.get(i);
                cst2.setInt(1, newid_registro);
                cst2.setInt(2, newid_registro);
                cst2.setInt(3, Integer.parseInt(muestra.get(0).toString()));
                cst2.setInt(4, Integer.parseInt(muestra.get(1).toString()));
                cst2.setString(5, muestra.get(2).toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = sdf.parse(muestra.get(5).toString());
                cst2.setDate(6, new java.sql.Date(fecha.getTime()));
                cst2.setString(7, muestra.get(4).toString());
                cst2.setInt(8, Integer.parseInt(muestra.get(3).toString()));
                cst2.setString(9, muestra.get(6).toString());
                cst2.execute();
            }
            JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            CallableStatement audit = conex.getConnection().prepareCall("{call auditoria (?,?,?)}");
            audit.setString(1, "registro_general");
            audit.setString(2, "i");
            audit.setInt(3, config.getId_usuario());
            audit.execute();

            conex.getConnection().commit();
            conex.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Registro");
        }
        return realizado;
    }

    public boolean buscarRegistro(String sql) {
        Conexion conex = new Conexion();
        boolean realizado = false;
        try {
            Statement stmt = null;
            stmt = conex.getConnection().createStatement();
            ResultSet rsgeneral;

            rsgeneral = stmt.executeQuery(sql);

            while (rsgeneral.next()) {
                realizado = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se realizo la busqueda");
        }
        return realizado;

    }

    public boolean modificarRegistro(RegistroGeneralBO miUsuario, ArrayList m) throws ParseException {

        Conexion conex = new Conexion();
        boolean realizado = false;
        try {
            conex.getConnection().setAutoCommit(false);
            String consulta = "UPDATE registro_general SET id_sede=?,id_analisis_tipo=?,id_predio=?,remitente=?,fecha_recepcion=? WHERE id_registro_general=?";
            PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
            estatuto.setInt(1, miUsuario.getId_sede());
            estatuto.setInt(2, miUsuario.getId_analisis_tipo());
            estatuto.setInt(3, miUsuario.getId_predio());
            estatuto.setString(4, miUsuario.getRemitente());
            estatuto.setDate(5, miUsuario.getFecha_recepcion());
            estatuto.setInt(6, miUsuario.getId_registro_general());
            estatuto.executeUpdate();
            if (estatuto.executeUpdate() == 1) {
                realizado = true;
            }

            //eliminar muestras anteriores
            CallableStatement cst3 = conex.getConnection().prepareCall("{call eliminar_muestra (?)}");
            cst3.setInt(1, miUsuario.getId_registro_general());
            cst3.execute();
            cst3.close();

            //insertar nuevos muestras
            int cantidad = m.size();
            for (int i = 0; i < cantidad; i++) {
                CallableStatement cst2 = conex.getConnection().prepareCall("{call registrar_muestra (?,?,?,?,?,?,?,?,?)}");
                ArrayList muestra = new ArrayList();
                muestra = (ArrayList) m.get(i);
                cst2.setInt(1, miUsuario.getId_registro_general());
                cst2.setInt(2, miUsuario.getId_registro_general());
                cst2.setInt(3, Integer.parseInt(muestra.get(0).toString()));
                cst2.setInt(4, Integer.parseInt(muestra.get(1).toString()));
                cst2.setString(5, muestra.get(2).toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = sdf.parse(muestra.get(5).toString());
                cst2.setDate(6, new java.sql.Date(fecha.getTime()));
                cst2.setString(7, muestra.get(4).toString());
                cst2.setInt(8, Integer.parseInt(muestra.get(3).toString()));
                cst2.setString(9, muestra.get(6).toString());
                cst2.execute();
                cst2.close();
            }
            estatuto.close();
            CallableStatement audit = conex.getConnection().prepareCall("{call auditoria (?,?,?)}");
            audit.setString(1, "registro_general");
            audit.setString(2, "m");
            audit.setInt(3, config.getId_usuario());
            audit.execute();

            conex.getConnection().commit();
            conex.desconectar();
            JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return realizado;
    }

    public boolean eliminarRegistro(String protocolo) {
        Conexion conex = new Conexion();
        boolean realizado = false;
        try {
            conex.getConnection().setAutoCommit(false);
            Statement estatuto = conex.getConnection().createStatement();
            if (estatuto.executeUpdate("DELETE FROM registro_general WHERE protocolo='" + protocolo + "'") == 1) {
                realizado = true;
            }
            JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            CallableStatement audit = conex.getConnection().prepareCall("{call auditoria (?,?,?)}");
            audit.setString(1, "registro_general");
            audit.setString(2, "e");
            audit.setInt(3, config.getId_usuario());
            audit.execute();
            conex.getConnection().commit();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Elimino");
        }
        return realizado;
    }

    public RegistroGeneralBO cargarRegistro(String protocoloid) throws ParseException {
        String sql = "Select reg.id_registro_general, reg.protocolo, reg.fecha_recepcion, tip.analisis_tipo, reg.id_analisis_tipo, pro.nombre, pre.id_propietario, pre.predio, reg.id_predio, reg.remitente, reg.id_sede FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario WHERE reg.protocolo = '" + protocoloid + "'";
        RegistroGeneralBO miRegistro = new RegistroGeneralBO();

        ArrayList muestras = new ArrayList();
        Conexion conex = new Conexion();
        try {
            Statement stmt = null;
            stmt = conex.getConnection().createStatement();
            ResultSet rsgeneral;

            rsgeneral = stmt.executeQuery(sql);

            while (rsgeneral.next()) {
                miRegistro.setId_registro_general(rsgeneral.getInt(1));
                miRegistro.setProtocolo(rsgeneral.getString(2));
                miRegistro.setFecha_recepcion(rsgeneral.getDate(3));
                miRegistro.setAnalisis_tipo(rsgeneral.getString(4));
                miRegistro.setId_analisis_tipo(rsgeneral.getInt(5));
                miRegistro.setPropietario(rsgeneral.getString(6));
                miRegistro.setId_propietario(rsgeneral.getInt(7));
                miRegistro.setPredio(rsgeneral.getString(8));
                miRegistro.setId_predio(rsgeneral.getInt(9));
                miRegistro.setRemitente(rsgeneral.getString(10));
                miRegistro.setId_sede(rsgeneral.getInt(11));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se cargo el registro");
        }

            //cargar las muestras
        try {
            sql = "select mu.id_registro_muestra,mu.id_tsn_especie,tax.nombre_completo, mu.id_muestra_tipo, tip.muestra_tipo, mu.identificacion, mu.fecha_vacunacion, mu.sexo, mu.edad_mes, mu.observacion from registro_muestra mu inner join taxonomia tax on mu.id_tsn_especie= tax.tsn inner join muestra_tipo tip on mu.id_muestra_tipo= tip.id_muestra_tipo WHERE mu.id_registro_general = " + miRegistro.getId_registro_general();

            Statement stmt = null;
            stmt = conex.getConnection().createStatement();
            ResultSet rsgeneral;

            rsgeneral = stmt.executeQuery(sql);

            while (rsgeneral.next()) {
                RegistroMuestraBO miMuestra = new RegistroMuestraBO();
                miMuestra.setId_registro_muestra(rsgeneral.getInt(1));
                miMuestra.setId_tsn_especie(rsgeneral.getInt(2));
                miMuestra.setNombre_completo(rsgeneral.getString(3));
                miMuestra.setId_muestra_tipo(rsgeneral.getInt(4));
                miMuestra.setMuestra_tipo(rsgeneral.getString(5));
                miMuestra.setHierro(rsgeneral.getString(6));
                miMuestra.setFecha_vacunacion(rsgeneral.getDate(7));
                miMuestra.setSexo(rsgeneral.getString(8));
                miMuestra.setEdad_meses(rsgeneral.getInt(9));
                miMuestra.setObservacion(rsgeneral.getString(10));
                muestras.add(miMuestra);
            }
            miRegistro.setMuestras(muestras);
            stmt.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se cargaron las muestras");
        }

        return miRegistro;

    }

}
