package sizin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Erneski Coronado*/
    public class Lista {
        public DefaultListModel modelo = new DefaultListModel();
        
        String sqlconsulta;
        String columna1;
        String columna2;
        String columna3;
        String columna4;
        
        public void consultalist(String sql, String col, String col2, String col3, String col4){
        sqlconsulta=sql;
        columna1=col;
        columna2=col2;
        columna3=col3;
        columna4=col4;
    }
        
        
        public void LLenarLista(){
        Conexion conectar= new Conexion();          
            modelo.removeAllElements();
            
            try{
            
                Statement s=conectar.conn.createStatement();
                ResultSet rs = s.executeQuery(sqlconsulta);
                while (rs.next())
                {

                    modelo.addElement(new ClaseObjetoParaLista(rs.getInt(columna1),rs.getString(columna2),rs.getInt(columna3),rs.getString(columna4)));
                    

                }
                s.close();
                conectar.desconectar();
        
        
            }catch(SQLException e){
                System.out.print(e.getMessage());
            }
            
}
        
                public void setjList(JList combo, String dir){
        int index=getjListIndex(combo, dir);
        // Si ha encontrado un Item con nombre "name" lo seleccionamos
        if(index != -1) {
            combo.setSelectedIndex(index);
            
           
        }        
    }
                
                         //metodo para hacer seleccion en jList que trabaja con id y string
        public int getjListIndex(JList combo, String name){
        int index=-1;
        for(int i=0; i<=combo.getLastVisibleIndex(); i++){
            combo.setSelectedIndex(i);
            String item=combo.getModel().getElementAt(i).toString();
            if(item.equals(name)==true){
                index=i;
            }
          
        }
        return index;
    }

        
}
