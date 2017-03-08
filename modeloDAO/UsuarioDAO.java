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
import modeloBO.UsuarioBO;
import sizin.ConfiguracionGlobal;

/**
 *
 * @author Erneski Coronado
 */
public class UsuarioDAO {
    ConfiguracionGlobal config= new ConfiguracionGlobal();
    
    public boolean registrarUsuario(UsuarioBO miUsuario)
	{
		Conexion conex = new Conexion();	
                boolean realizado = false;
		try {
                        conex.getConnection().setAutoCommit(false);
			PreparedStatement estatuto=null;
                        String sql = "INSERT into usuario " +
                        "(id_usuario,id_personal,id_usuario_tipo,usuario,clave)" +
                        " values(null,?,?,?,?)";
                        estatuto = conex.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                        estatuto.setInt(1, miUsuario.getId_personal());
                        estatuto.setInt(2, miUsuario.getId_usuario_tipo());
                        estatuto.setString(3, miUsuario.getUsuario());
                        estatuto.setString(4, miUsuario.getClave());
                        if(estatuto.executeUpdate()==1)realizado=true;
                       
                        //como obener el ultimo id generado
                        /*ResultSet keyResultSet = estatuto.getGeneratedKeys();
                        int newCustomerId = 0;
                        if (keyResultSet.next()) {
                        int newid_usuario = (int) keyResultSet.getInt(1);
                        }*/
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
                        
                        CallableStatement audit = conex.getConnection().prepareCall("{call auditoria (?,?,?)}");
                        audit.setString(1, "usuario");
                        audit.setString(2, "i");
                        audit.setInt(3, config.getId_usuario());
                        audit.execute();
                        conex.getConnection().commit();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}return realizado;
	}

	public UsuarioBO buscarUsuario(int id_personal) 
	{
		Conexion conex= new Conexion();
		UsuarioBO usuario= new UsuarioBO();
		boolean existe=false;
		try {
			//Statement estatuto = conex.getConnection().createStatement();
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuario where id_personal = ? ");
			consulta.setInt(1, id_personal);
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				existe=true;
				usuario.setId_usuario(res.getInt("id_usuario"));
				usuario.setId_usuario_tipo(res.getInt("id_usuario_tipo"));
				usuario.setUsuario(res.getString("usuario"));
				usuario.setClave(res.getString("clave"));
			 }
			res.close();
			conex.desconectar();
					
					
			} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error, no se conecto");
					System.out.println(e);
			}
		
			if (existe) {
				return usuario;
			}
			else return null;				
	}

	public boolean modificarUsuario(UsuarioBO miUsuario) {
		
		Conexion conex= new Conexion();
                boolean realizado=false;
		try{
                        conex.getConnection().setAutoCommit(false);
			String consulta="UPDATE usuario SET id_personal=?, id_usuario_tipo=?, usuario=?, clave=? WHERE id_usuario=?";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setInt(1, miUsuario.getId_personal());
                        estatuto.setInt(2, miUsuario.getId_usuario_tipo());
                        estatuto.setString(3, miUsuario.getUsuario());
                        estatuto.setString(4, miUsuario.getClave());
                        estatuto.setInt(5, miUsuario.getId_usuario());
                        estatuto.executeUpdate();
                        if(estatuto.executeUpdate()==1)realizado=true;
                        CallableStatement audit = conex.getConnection().prepareCall("{call auditoria (?,?,?)}");
                        audit.setString(1, "usuario");
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

	public boolean eliminarUsuario(Integer id_usuario)
	{
		Conexion conex= new Conexion();
                boolean realizado=false;
		try {
                        conex.getConnection().setAutoCommit(false);
			Statement estatuto = conex.getConnection().createStatement();
			if(estatuto.executeUpdate("DELETE FROM usuario WHERE id_usuario='"+id_usuario+"'")==1)realizado=true;
                        JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
                        CallableStatement audit = conex.getConnection().prepareCall("{call auditoria (?,?,?)}");
                        audit.setString(1, "usuario");
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
        
        	public UsuarioBO loginUsuario(String login, String clave) 
	{
		Conexion conex= new Conexion();
		UsuarioBO usuario= new UsuarioBO();
                ConfiguracionGlobal config = new ConfiguracionGlobal();
		boolean existe=false;
		try {
			//Statement estatuto = conex.getConnection().createStatement();
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT us.id_usuario, us.id_personal, us.id_usuario_tipo, us.usuario, us.clave, per.id_sede, concat_ws(' ',per.nombre,per.apellido) as nombre FROM usuario us INNER JOIN personal per ON us.id_personal=per.id_personal  where us.usuario = ? AND us.clave = ?");
			consulta.setString(1, login);
                        consulta.setString(2, clave);
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				existe=true;
				usuario.setId_usuario(res.getInt("us.id_usuario"));
                                usuario.setId_personal(res.getInt("us.id_personal"));
				usuario.setId_usuario_tipo(res.getInt("us.id_usuario_tipo"));
				usuario.setUsuario(res.getString("us.usuario"));
				usuario.setClave(res.getString("us.clave"));
                                usuario.setId_sede(res.getInt("per.id_sede"));
                                config.setSede_seleccionada(res.getInt("per.id_sede"));
                                config.setId_usuario(res.getInt("us.id_usuario"));
                                config.privilegio=res.getInt("us.id_usuario_tipo");
                                config.usuario=res.getString("nombre");
                                
                        
                                  
                                
			 }  

			conex.desconectar();
					
					
			} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error, no se conecto");
					System.out.println(e);
			}
		
			if (existe) {
                            return usuario;
			}
                        
                        else{
                            JOptionPane.showMessageDialog(null, "Usuario o clave incorrectos","Informacion",JOptionPane.INFORMATION_MESSAGE);
                            return null;
                        }				
	}

    
}
