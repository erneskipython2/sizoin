package modeloDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//import controlador.Coordinador;

import sizin.Conexion;
import modeloBO.PropietarioBO;
import sizin.ConfiguracionGlobal;

/**
 *
 * @author Erneski Coronado
 */
public class PropietarioDAO {
    
    public boolean registrarPropietario(PropietarioBO miPropietario)
	{
		Conexion conex = new Conexion();	
                boolean realizado = false;
		try {
			PreparedStatement estatuto=null;
                        String sql = "INSERT into propietario " +
                        "(id_propietario,rif,nombre,apellido,telefono)" +
                        " values(null,?,?,?,?)";
                        estatuto = conex.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                        estatuto.setString(1, miPropietario.getRif());
                        estatuto.setString(2, miPropietario.getNombre());
                        estatuto.setString(3, miPropietario.getApellido());
                        estatuto.setString(4, miPropietario.getTelefono());

                        if(estatuto.executeUpdate()==1)realizado=true;
 			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}return realizado;
	}


	public boolean modificarPropietario(PropietarioBO miPropietario) {
		
		Conexion conex= new Conexion();
                boolean realizado=false;
		try{
                        
			String consulta="UPDATE propietario SET rif=?, nombre=?, apellido=?, telefono=? WHERE id_propietario=?";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1, miPropietario.getRif());
                        estatuto.setString(2, miPropietario.getNombre());
                        estatuto.setString(3, miPropietario.getApellido());
                        estatuto.setString(4, miPropietario.getTelefono());
                        estatuto.setInt(5, miPropietario.getId_propietario());
                        
                        estatuto.executeUpdate();
                        if(estatuto.executeUpdate()==1)realizado=true;

                        JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ","Confirmacion",JOptionPane.INFORMATION_MESSAGE);
         

        }catch(SQLException	 e){

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar","Error",JOptionPane.ERROR_MESSAGE);
        }return realizado;
        
	}

	public boolean eliminarPropietario(Integer id_propietario)
	{
		Conexion conex= new Conexion();
                boolean realizado=false;
		try {
			Statement estatuto = conex.getConnection().createStatement();
			if(estatuto.executeUpdate("DELETE FROM propietario WHERE id_propietario='"+id_propietario+"'")==1)realizado=true;
                        JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Elimino");
		}return realizado;
	}
        
        public boolean buscarPropietario(PropietarioBO miPropietario)
	{
		Conexion conex= new Conexion();
                boolean realizado=false;
		try {
			Statement estatuto = conex.getConnection().createStatement();
                         ResultSet rs1;
                         rs1 = estatuto.executeQuery("SELECT * FROM propietario WHERE rif='"+miPropietario.getRif()+"'");
                         if(rs1.next()){
                             realizado=true;
                            JOptionPane.showMessageDialog(null, "Busqueda Exitosa, Propietario: "+rs1.getString(3)+" "+rs1.getString(4)+", Telf.: "+rs1.getString(5)); // mostramos el propietario encontrado
                         }else{
                            JOptionPane.showMessageDialog(null, "Propietario no encontrado!, Intente de nuevo");
                         }
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Realizo la busqueda");
		}return realizado;
	}
        
        	

    
}
