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
import modeloBO.PredioBO;
import sizin.ConfiguracionGlobal;

/**
 *
 * @author Erneski Coronado
 */
public class PredioDAO {
    
    public boolean registrarPredio(PredioBO miPredio, ArrayList n)
	{
		Conexion conex = new Conexion();	
                boolean realizado = false;
                int id_predio=0;
		try {

                        conex.getConnection().setAutoCommit(false);
			PreparedStatement estatuto=null;
                        String sql = "INSERT into predio " +
                        "(id_predio,id_propietario,direccion_local,id_parroquia,superficie,predio,utm_este,utm_norte)" +
                        " values(null,?,?,?,?,?,?,?)";
                        estatuto = conex.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                        //estatuto.setInt(1, miPredio.getId_predio());
                        estatuto.setInt(1, miPredio.getId_propietario());
                        estatuto.setString(2, miPredio.getDireccion());
                        estatuto.setInt(3, miPredio.getId_parroquia());
                        estatuto.setString(4, miPredio.getSuperficie());
                        estatuto.setString(5, miPredio.getPredio());
                        estatuto.setFloat(6, miPredio.getUtm_este());
                        estatuto.setFloat(7, miPredio.getUtm_norte());

                        
                        
                    if (estatuto.executeUpdate() == 1) {
                        ResultSet keyResultSet = estatuto.getGeneratedKeys();
                        if (keyResultSet.next()) {
                            id_predio = keyResultSet.getInt(1);
                        }
                        //eliminar la finalidad del predio
                        CallableStatement cst3 = conex.getConnection().prepareCall("{call eliminar_finalidad_predio (?)}");
                        cst3.setInt(1, id_predio);
                        cst3.execute();
                        cst3.close();
                        
                        //guardar la finalidad del predio
                        int cantidad_ana = n.size();
                        for (int i = 0; i < cantidad_ana; i++) {
                            CallableStatement cst2 = conex.getConnection().prepareCall("{call registro_finalidad_predio (?,?)}");
                            ArrayList muestra = new ArrayList();
                            muestra = (ArrayList) n.get(i);
                            cst2.setInt(1, id_predio);
                            cst2.setInt(2, Integer.parseInt(muestra.get(0).toString()));
                            cst2.execute();
                            cst2.close();
                        }
                        conex.getConnection().commit();
                        realizado = true;
                    }
                    JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
             
                        
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro");
		}return realizado;
	}


	public boolean modificarPredio(PredioBO miPredio, ArrayList n) {
		
		Conexion conex= new Conexion();
                boolean realizado=false;
		try{
                    conex.getConnection().setAutoCommit(false);
                        
			String consulta="UPDATE predio SET id_propietario=?, direccion_local=?, id_parroquia=?, superficie=?, predio=?, utm_este=?, utm_norte=? WHERE id_predio=?";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
                        estatuto.setInt(1, miPredio.getId_propietario());
                        estatuto.setString(2, miPredio.getDireccion());
                        estatuto.setInt(3, miPredio.getId_parroquia());
                        estatuto.setString(4, miPredio.getSuperficie());
                        estatuto.setString(5, miPredio.getPredio());
                        estatuto.setFloat(6, miPredio.getUtm_este());
                        estatuto.setFloat(7, miPredio.getUtm_norte());
                        estatuto.setInt(8, miPredio.getId_predio());
                        
                        estatuto.executeUpdate();
                        if(estatuto.executeUpdate()==1){

                        //eliminar la finalidad del predio
                        CallableStatement cst3 = conex.getConnection().prepareCall("{call eliminar_finalidad_predio (?)}");
                        cst3.setInt(1, miPredio.getId_predio());
                        cst3.execute();
                        cst3.close();
                        
                        //guardar la finalidad del predio
                        int cantidad_ana = n.size();
                        for (int i = 0; i < cantidad_ana; i++) {
                            CallableStatement cst2 = conex.getConnection().prepareCall("{call registro_finalidad_predio (?,?)}");
                            ArrayList muestra = new ArrayList();
                            muestra = (ArrayList) n.get(i);
                            cst2.setInt(1, miPredio.getId_predio());
                            cst2.setInt(2, Integer.parseInt(muestra.get(0).toString()));
                            cst2.execute();
                            cst2.close();
                        }
                        conex.getConnection().commit();
                            realizado=true;
                        }

                        JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ","Confirmacion",JOptionPane.INFORMATION_MESSAGE);
         

        }catch(SQLException	 e){

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar","Error",JOptionPane.ERROR_MESSAGE);
        }return realizado;
        
	}

	public boolean eliminarPredio(Integer id_predio)
	{
		Conexion conex= new Conexion();
                boolean realizado=false;
		try {
			Statement estatuto = conex.getConnection().createStatement();
			if(estatuto.executeUpdate("DELETE FROM predio WHERE id_predio='"+id_predio+"'")==1)realizado=true;
                        JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
            System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Elimino");
		}return realizado;
	}
        
       
        
        public ArrayList buscarFinalidadPredio(int id_predio) {
        Conexion conex = new Conexion();
        ArrayList analistas= new ArrayList();     
        try {
            //Statement estatuto = conex.getConnection().createStatement();
            
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT pre.id_finalidad, fin.finalidad  FROM predio_finalidad pre INNER join finalidad fin ON pre.id_finalidad=fin.id_finalidad WHERE pre.id_predio= ? ");
            consulta.setInt(1, id_predio);
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                ArrayList columnas=new ArrayList();
                columnas.add(res.getInt(1));
                columnas.add(res.getString(2));
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
        
        
        
        
        
        
        
        	

    
}
