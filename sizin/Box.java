package sizin;

import javax.swing.DefaultComboBoxModel;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JComboBox;

/**
 *
 * @author Erneski Coronado
 */

//Clase para crear modelo de carga de un JComboBox
/**
 *
 * @author Erneski Coronado
 */
public class Box extends DefaultComboBoxModel {

    public DefaultComboBoxModel modelo = new DefaultComboBoxModel();
    private String sqltabla;//recibe el sql para cargar el Box
    private String columna1; //recibe la columna a guardar
    private String columna2;// recibe la columna a mostrar

    public DefaultComboBoxModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultComboBoxModel modelo) {
        this.modelo = modelo;
    }

    //para llenar con 2 columnas el JComboBox columna 1 es el id y columna 2 el texto a mostrar
    public void consultabox2(String sql, String col, String col2) {
        sqltabla = sql;
        columna1 = col;
        columna2 = col2;
    }

//este metodo trabaja en conjunto con la claseObjetoParacomboBox. toma el id y nombre pero solo muestra el nombre en el jcombobox
    public void LLenarBoxConID() {
        modelo.removeAllElements();
        try {
            Conexion conectar = new Conexion();
            Statement s = conectar.conn.createStatement();
            ResultSet rs = s.executeQuery(sqltabla);

            //if(vuelta==0){
            modelo.addElement(new ClaseObjetoParaComboBox(0, "--Seleccione--"));
            // }
            // vuelta++;

            while (rs.next()) {
                modelo.addElement(new ClaseObjetoParaComboBox(rs.getInt(columna1), rs.getString(columna2)));

            }
            s.close();
            conectar.desconectar();

        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.print(e.getMessage());
        }

    }
    
    public void LLenarBoxTablaConID(JComboBox com) {

        com.removeAllItems();

        try {

            Conexion conectar = new Conexion();
            Statement s = conectar.conn.createStatement();
            ResultSet rs = s.executeQuery(sqltabla);

            com.addItem(new ClaseObjetoParaComboBox(0, "--Seleccione--"));

            while (rs.next()) {
                com.addItem(new ClaseObjetoParaComboBox(rs.getInt(columna1), rs.getString(columna2)));
            }
            s.close();
            conectar.desconectar();

        } catch (SQLException e) {

            System.out.print(e.getMessage());
        }

    }

    public void modelovacio() {
        modelo.addElement("--Seleccione--");
    }

    public int getComboBoxIndex(JComboBox combo, String name) {
        int index = -1;
        for (int i = 0; i < combo.getItemCount(); i++) {
            combo.setSelectedIndex(i);
            String item = combo.getSelectedItem().toString();
            if (item.equals(name) == true) {
                index = i;
            }
        }
        return index;
    }

    public void setjComboBox(JComboBox combo, String dir) {
        int index = getComboBoxIndex(combo, dir);
        // Si ha encontrado un Item con nombre "name" lo seleccionamos
        if (index != -1) {
            combo.setSelectedIndex(index);

        }
    }

}
