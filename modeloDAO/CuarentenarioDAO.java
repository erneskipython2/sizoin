package modeloDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import sizin.Conexion;
import modeloBO.CuarentenarioBO;


/**
 *
 * @author Erneski Coronado
 */
public class CuarentenarioDAO {
    
    public boolean registrarSede(CuarentenarioBO miPersona)
	{
		Conexion conex = new Conexion();	
                boolean realizado = false;
		try {
			PreparedStatement estatuto=null;
                        String sql = "INSERT into organismo_cuarentenario " +
                        "(id_tsn,nombre_comun,descripcion)" +
                        " values(?,?,?)";
                        estatuto = conex.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                        estatuto.setInt(1, miPersona.getId_tsn());
                        estatuto.setString(2, miPersona.getNombre_comun());
                        estatuto.setString(3, miPersona.getDescripcion());

                        if(estatuto.executeUpdate()==1)realizado=true;
 			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}return realizado;
	}



	public boolean eliminarSede(Integer id_sede)
	{
		Conexion conex= new Conexion();
                boolean realizado=false;
		try {
			Statement estatuto = conex.getConnection().createStatement();
			if(estatuto.executeUpdate("DELETE FROM organismo_cuarentenario WHERE id_tsn='"+id_sede+"'")==1)realizado=true;
                        JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Elimino");
		}return realizado;
	}
        
        	

    
}
