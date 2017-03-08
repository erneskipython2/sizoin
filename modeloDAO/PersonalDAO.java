package modeloDAO;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//import controlador.Coordinador;

import sizin.Conexion;
import modeloBO.PersonalBO;
import sizin.ConfiguracionGlobal;

/**
 *
 * @author Erneski Coronado
 */
public class PersonalDAO {
    ConfiguracionGlobal config= new ConfiguracionGlobal();
    public boolean registrarPersonal(PersonalBO miPersona)
	{
		Conexion conex = new Conexion();	
                boolean realizado = false;
		try {
                        conex.getConnection().setAutoCommit(false);
			PreparedStatement estatuto=null;
                        String sql = "INSERT into personal " +
                        "(id_personal,id_sede,nombre,apellido,cedula,telefono,num_acreditacion,carnet_mvc)" +
                        " values(null,?,?,?,?,?,?,?)";
                        estatuto = conex.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                        estatuto.setInt(1, miPersona.getId_sede());
                        estatuto.setString(2, miPersona.getNombres());
                        estatuto.setString(3, miPersona.getApellidos());
                        estatuto.setString(4, miPersona.getCedula());
                        estatuto.setString(5, miPersona.getTelefono());
                        estatuto.setString(6, miPersona.getAcreditacion());
                        estatuto.setString(7, miPersona.getCarnet());

                        if(estatuto.executeUpdate()==1)realizado=true;
                        estatuto.close();
                        CallableStatement audit = conex.getConnection().prepareCall("{call auditoria (?,?,?)}");
                        audit.setString(1, "personal");
                        audit.setString(2, "i");
                        audit.setInt(3, config.getId_usuario());
                        audit.execute();
                        
                        conex.getConnection().commit();
 			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}return realizado;
	}


	public boolean modificarPersonal(PersonalBO miPersona) {
		
		Conexion conex= new Conexion();
                boolean realizado=false;
		try{
                        conex.getConnection().setAutoCommit(false);
			String consulta="UPDATE personal SET id_sede=?, nombre=?, apellido=?, cedula=?, telefono=?, num_acreditacion=?, carnet_mvc=? WHERE id_personal=?";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setInt(1, miPersona.getId_sede());
                        estatuto.setString(2, miPersona.getNombres());
                        estatuto.setString(3, miPersona.getApellidos());
                        estatuto.setString(4, miPersona.getCedula());
                        estatuto.setString(5, miPersona.getTelefono());
                        estatuto.setString(6, miPersona.getAcreditacion());
                        estatuto.setString(7, miPersona.getCarnet());
                        estatuto.setInt(8, miPersona.getId_personal());
                        
                        estatuto.executeUpdate();

                        if(estatuto.executeUpdate()==1)realizado=true;
                        CallableStatement audit = conex.getConnection().prepareCall("{call auditoria (?,?,?)}");
                        audit.setString(1, "personal");
                        audit.setString(2, "m");
                        audit.setInt(3, config.getId_usuario());
                        audit.execute();
                        conex.getConnection().commit();
                        conex.desconectar();
                        JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ","Confirmacion",JOptionPane.INFORMATION_MESSAGE);
         

        }catch(SQLException	 e){

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar","Error",JOptionPane.ERROR_MESSAGE);
        }return realizado;
        
	}

	public boolean eliminarPersonal(Integer id_personal)
	{
		Conexion conex= new Conexion();
                boolean realizado=false;
		try {
                        conex.getConnection().setAutoCommit(false);
			Statement estatuto = conex.getConnection().createStatement();
			if(estatuto.executeUpdate("DELETE FROM personal WHERE id_personal='"+id_personal+"'")==1)realizado=true;
                        JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
                        CallableStatement audit = conex.getConnection().prepareCall("{call auditoria (?,?,?)}");
                        audit.setString(1, "personal");
                        audit.setString(2, "e");
                        audit.setInt(3, config.getId_usuario());
                        audit.execute();
                        conex.getConnection().commit();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Elimino");
		}return realizado;
	}
        
        	

    
}
