package sizin;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Erneski Coronado
 */
public class Conexion {
     Connection conn = null;
   
     public Conexion() {
      try{
          
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost/sizoin", "root", "");
         if (conn!=null){
            System.out.println("Conexión BD listo");
         }
      }catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }
   }
      
      
   public Connection getConnection(){
      return conn;
   }

   public void desconectar(){
        try {
            conn.close();
            conn = null;
          System.out.println("La conexion a la  base de datos a terminado");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
    
}
