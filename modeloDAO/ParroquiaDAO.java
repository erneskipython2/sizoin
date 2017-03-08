package modeloDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import sizin.Conexion;
import modeloBO.ParroquiaBO;

/**
 *
 * @author Erneski Coronado
 */
public class ParroquiaDAO {
    
    public boolean registrarParroquia(ParroquiaBO miParroquia)
	{
		Conexion conex = new Conexion();	
                boolean realizado = false;
		try {
			PreparedStatement estatuto=null;
                        String sql = "INSERT into parroquia " +
                        "(id_parroquia,parroquia,id_municipio)" +
                        " values(null,?,?)";
                        estatuto = conex.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                        estatuto.setString(1, miParroquia.getParroquia());
                        estatuto.setInt(2, miParroquia.getId_municipio());

                        if(estatuto.executeUpdate()==1)realizado=true;
 			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}return realizado;
	}
    
}
