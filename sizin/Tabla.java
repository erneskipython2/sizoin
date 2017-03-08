package sizin;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;




/**
 *
 * @author Erneski Coronado
 */
public class Tabla extends DefaultTableModel{
    public DefaultTableModel modelo = new DefaultTableModel();
    int columnas;
    Object [] fila;// se le coloca el numero de columnas en el metodo columnastabla
    String sqltabla;
    
    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }


@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

   

    
    public Tabla(){
        
    }
      
    //metodo para colocar el numero de columnas a la Tabla
    public void columnastabla(int nco, String sql){
        columnas=nco;
        fila=new Object[columnas];
        sqltabla=sql;
       
        
    }
    //metodo para crear el modelo de la Tabla de usuarios
    public void crearmodelocuarentenario(){
      
        modelo.addColumn("Id");
        modelo.addColumn("Nombre Científico");
        modelo.addColumn("Nombre Común ");
        modelo.addColumn("Descripción");
        
    LlenarTabla();

}
    
    public void crearmodeloinciencias(){
      
        modelo.addColumn("Id");
        modelo.addColumn("Organismo Cuarentenario");
        modelo.addColumn("Nombre Común");
        modelo.addColumn("Descripción");
        modelo.addColumn("Nº Protocolo");
        modelo.addColumn("Fecha de Alerta");
        modelo.addColumn("Animal Infectado");
        modelo.addColumn("Predio Origen");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");
        modelo.addColumn("Municipio");
        modelo.addColumn("Parroquia");
        modelo.addColumn("Dirección");
        modelo.addColumn("Coord. UTM E");
        modelo.addColumn("Coord. UTM N");
        modelo.addColumn("Superficie");
        modelo.addColumn("Propietario");
        modelo.addColumn("RIF");
        modelo.addColumn("Teléfono");
    LlenarTabla();

}  
    
    
    //metodo para crear el modelo de la Tabla de usuarios
    public void crearmodelousuario(){
      
        modelo.addColumn("Id");
        modelo.addColumn("Usuario");
        modelo.addColumn("Clave");
        modelo.addColumn("Tipo");
        modelo.addColumn("Asignado a");
        modelo.addColumn("Cedula");
        
    LlenarTabla();

}
    
    //metodo para crear el modelo de la Tabla del personal
     public void crearmodelopersonal(){
      
        modelo.addColumn("Id");
        modelo.addColumn("C.I.");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Telefono");
        modelo.addColumn("Sede");
        modelo.addColumn("Nº Acreditación");
        modelo.addColumn("Nº Carnet");
        

    LlenarTabla();

}
     
         //metodo para crear el modelo de la Tabla del personal
     public void crearmodelosede(){
      
        modelo.addColumn("Id");
        modelo.addColumn("Sede");
        modelo.addColumn("Nº Sede");
        modelo.addColumn("Telefono");
        modelo.addColumn("email");
        modelo.addColumn("Direccion");
        modelo.addColumn("Parroquia");
        modelo.addColumn("Municipio");
        modelo.addColumn("Estado");
        modelo.addColumn("Responsable");

    LlenarTabla();

}
     
     public void crearmodelopropietario(){
      
        modelo.addColumn("Id");
        modelo.addColumn("Rif");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Telefono");
      
    LlenarTabla();

}
     
     public void crearmodelovacuna(){
        modelo.addColumn("Id");
        modelo.addColumn("Vacuna");
        modelo.addColumn("Utilidad");
    
    LlenarTabla();        
     }
     
     public void crearmodeloanimal(){
        modelo.addColumn("Id");
        modelo.addColumn("Género");
 
    LlenarTabla();        
     }
          
     public void crearmodeloanimalespecie(){
        modelo.addColumn("Id");
        modelo.addColumn("Especie");
 
    LlenarTabla();        
     }     
     
     public void crearmodeloanimalraza(){
        modelo.addColumn("Id");
        modelo.addColumn("Raza");
 
    LlenarTabla();        
     }
     
     public void crearmodeloanimalgrupo(){
        modelo.addColumn("Id");
        modelo.addColumn("Grupo");
 
    LlenarTabla();        
     }
     

     public void crearmodelomuestras(){
        modelo.addColumn("Tsn Especie");
        modelo.addColumn("Nombre Científico");
        modelo.addColumn("Tipo Muestra");
        modelo.addColumn("Hierro");
        modelo.addColumn("F. Vacunación");
        modelo.addColumn("Sexo");
        modelo.addColumn("Edad");        
        modelo.addColumn("Observaciones");
     }
     
     public void crearmodeloresultado(){
        modelo.addColumn("Tsn Especie");
        modelo.addColumn("Nombre Científico");
        modelo.addColumn("ID");
     }
     
        public void crearmodelomuestrasbru(){
        modelo.addColumn("Género");
        modelo.addColumn("Especie");
        modelo.addColumn("Raza");
        modelo.addColumn("Grupo");
        modelo.addColumn("Iden");
        modelo.addColumn("Edad");
        modelo.addColumn("Sexo");
        modelo.addColumn("Vacunado");
        modelo.addColumn("F. Vacunación");
        modelo.addColumn("Observaciones");
        modelo.addColumn("ID");
        modelo.addColumn("Reactor");

     }
          
          public void crearmodelobuscar(){
        modelo.addColumn("Protocolo");
        modelo.addColumn("F. Recepción");
        modelo.addColumn("Análisis");
        modelo.addColumn("Nom. Propietario");
        modelo.addColumn("Ape. Propietario");
        modelo.addColumn("Predio");
        modelo.addColumn("Remitente");
        modelo.addColumn("Estado");
        modelo.addColumn("Municipio");
        modelo.addColumn("Parroquia");
        modelo.addColumn("Dirección");
        modelo.addColumn("ID");
  
     }
          
         public void crearmodelopredios(){
             modelo.addColumn("Predio");
             modelo.addColumn("Estado");
             modelo.addColumn("Municipio");
             modelo.addColumn("Parroquia");
             modelo.addColumn("Direccion");
             modelo.addColumn("Coord. UTM ESTE");
             modelo.addColumn("Coord. UTM NORTE");
             modelo.addColumn("Superficie");
             modelo.addColumn("ID");
             
         }
         
         //metodo para centrar los valores de una Tabla
          public void centrar_datos(JTable jT, int col){
 
           DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
            modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
            jT.getColumnModel().getColumn(col).setCellRenderer(modelocentrar);
        }
     
     
     
    //metodo para limpiar las filas de tablas y llenarlo
    public void LlenarTabla() {
        Conexion conectar= new Conexion();
        int filas = modelo.getRowCount();
        if (filas > 0) {
            for (int i = 0; i < filas; i++) {
                modelo.removeRow(0);               
            }
        }        
        try{           
        Statement s=conectar.conn.createStatement();
        ResultSet rs = s.executeQuery(sqltabla);                  
        while (rs.next())
            {
               // Se crea un array que sera una de las filas de la Tabla
               // Se rellena cada posicion de la Tabla con una de las columnas de la Tabla en base de datos.
               for (int i=0;i<columnas;i++) {
                    fila[i] = rs.getObject(i+1);                   
                } // El primer indice en rs es el 1, no el cero, por eso se suma 1.
              // Se anhade al modelo la fila completa.
               modelo.addRow(fila);
            }s.close();//limpio el statement
            conectar.desconectar();//cierro la conexion     
        }catch(SQLException e){  
            System.out.print(e.getMessage());
        }
    }  
    
    //metodo para centrar contenido de una jtable
    public TableCellRenderer render = new DefaultTableCellRenderer() {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
boolean isSelected, boolean hasFocus, int row, int column) {
//aqui obtengo el render de la calse superior
JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//Alineo, puedes alinear cambiar fondo etc, solo seria cuention de validar fia, columna
//si esta seleccionana, depende de tu necesidad
l.setHorizontalAlignment(SwingConstants.CENTER);
//Color Trigoclaro=new Color(245, 222, 179);
//l.setBackground(Color.WHITE);
//l.setForeground(Color.BLUE);

//if(hasFocus) {
       // l.setForeground(Color.BLUE);
   // }
//else {
   //     l.setForeground(Color.BLACK);
   // }
return l;
}
           
   
    //metodo para hacer una celda no editable
    public boolean isCellEditable(int rowIndex, int columnIndex) {
   return false;
} 
    
    
        
}; 
 
    
   
}



    
