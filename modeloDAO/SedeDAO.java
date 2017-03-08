package modeloDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//import controlador.Coordinador;

import sizin.Conexion;
import modeloBO.SedeBO;
import sizin.ConfiguracionGlobal;

/**
 *
 * @author Erneski Coronado
 */
public class SedeDAO {
    
    public boolean registrarSede(SedeBO miPersona)
	{
		Conexion conex = new Conexion();	
                boolean realizado = false;
		try {
			PreparedStatement estatuto=null;
                        String sql = "INSERT into sedes_insai " +
                        "(id_sedes_insai,sede,num_sede,telefono,email,parroquia_id,direccion)" +
                        " values(null,?,?,?,?,?,?)";
                        estatuto = conex.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                        estatuto.setString(1, miPersona.getSede());
                        estatuto.setString(2, miPersona.getNum_sede());
                        estatuto.setString(3, miPersona.getTelefono());
                        estatuto.setString(4, miPersona.getEmail());
                        estatuto.setInt(5, miPersona.getParroquia_id());
                        estatuto.setString(6, miPersona.getDireccion());

                        if(estatuto.executeUpdate()==1)realizado=true;
 			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}return realizado;
	}


	public boolean modificarSede(SedeBO miPersona) {
		
		Conexion conex= new Conexion();
                boolean realizado=false;
		try{
                        
			String consulta="UPDATE sedes_insai SET sede=?, num_sede=?, telefono=?, email=?, parroquia_id=?, direccion=?, id_responsable=? WHERE id_sedes_insai=?";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
                        estatuto.setString(1, miPersona.getSede());
                        estatuto.setString(2, miPersona.getNum_sede());
                        estatuto.setString(3, miPersona.getTelefono());
                        estatuto.setString(4, miPersona.getEmail());
                        estatuto.setInt(5, miPersona.getParroquia_id());
                        estatuto.setString(6, miPersona.getDireccion());
                        estatuto.setInt(7, miPersona.getResponsable());
                        estatuto.setInt(8, miPersona.getId_sedes_insai());
                        
                        estatuto.executeUpdate();
                        if(estatuto.executeUpdate()==1)realizado=true;

                        JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ","Confirmacion",JOptionPane.INFORMATION_MESSAGE);
         

        }catch(SQLException	 e){

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar","Error",JOptionPane.ERROR_MESSAGE);
        }return realizado;
        
	}

	public boolean eliminarSede(Integer id_sede)
	{
		Conexion conex= new Conexion();
                boolean realizado=false;
		try {
			Statement estatuto = conex.getConnection().createStatement();
			if(estatuto.executeUpdate("DELETE FROM sedes_insai WHERE id_sedes_insai='"+id_sede+"'")==1)realizado=true;
                        JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Elimino");
		}return realizado;
	}
        
        	

    
}
