package sizin;

import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Esta clase permite pintar de colores los analisis que ya estan listos y los
 * que no los pinta de rojo
 *
 * @author Erneski Coronado
 */
public class FormatoTablaBuscar extends DefaultTableCellRenderer {

    private int columna_patron;
    private int id;
    private int muestra;
    boolean encontrado;
    int columna_analisis;
    String sqlres;

    public FormatoTablaBuscar(int Colpatron, int analisis) {
        this.columna_patron = Colpatron;
        this.columna_analisis = analisis;

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo

        Conexion access = new Conexion();// instanciamos para nueva conexiona BD
        Statement stmt = null; // Creamos statement 
        String sqlregistro;

        //buscamos el id en base al protocolo obtenido de la tabla
        try {

            sqlregistro = "Select id_registro_general FROM registro_general WHERE protocolo = '" + table.getValueAt(row, columna_patron).toString() + "'";

            stmt = access.conn.createStatement();
            ResultSet rsgeneral;
            rsgeneral = stmt.executeQuery(sqlregistro);
            while (rsgeneral.next()) {
                //cargamos el id
                id = rsgeneral.getInt(1);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de carga" + e);
        }

        encontrarmuestra();
        if (table.getValueAt(row, columna_analisis).toString().equals("Anemia Infecciosa")) {
            sqlres = "Select id_resultado_anemia FROM resultado_anemia WHERE id_registro_muestra = ";
        } else if (table.getValueAt(row, columna_analisis).toString().equals("Brucelosis")) {
            sqlres = "Select id_resultado_brucelosis FROM resultado_brucelosis WHERE id_registro_muestra = ";
        } else if (table.getValueAt(row, columna_analisis).toString().equals("Coprología")) {
            sqlres = "Select id_resultado_coprologia FROM resultado_coprologia WHERE id_registro_muestra  = ";
        } else if (table.getValueAt(row, columna_analisis).toString().equals("Hematología")) {
            sqlres = "Select id_resultado_hematologia FROM resultado_hematologia WHERE id_registro_muestra = ";
        } else if (table.getValueAt(row, columna_analisis).toString().equals("Hemoparásitos")) {
            sqlres = "Select id_resultado_hemoparasito FROM resultado_hemoparasito WHERE id_registro_muestra  = ";
        } else {
            sqlres = "Select id_resultado_titulacion FROM resultado_titulacion WHERE id_registro_muestra = ";
        }
        encontrarid(sqlres);

        //if( table.getValueAt(row,columna_patron).toString().equals("Brucelosis (B)") )
        if (encontrado == true) {
            Color Verde = new Color(110, 230, 110);
            setBackground(Verde);
            setToolTipText("Resultado Listo");

        } else {
            Color rojo = new Color(230, 110, 110);
            setBackground(rojo);
            setToolTipText("Resultado en Proceso");
        }
        access.desconectar();
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;

    }

    public void encontrarmuestra() {

        Conexion access2 = new Conexion();// instanciamos para nueva conexiona BD
        Statement stmt2 = null; // Creamos statement 
        String sqlregistro2;

        try {

            sqlregistro2 = "Select id_registro_muestra FROM registro_muestra WHERE id_registro_general = " + id;

            stmt2 = access2.conn.createStatement();
            ResultSet rsgeneral;
            rsgeneral = stmt2.executeQuery(sqlregistro2);

            while (rsgeneral.next()) {
                //cargamos el id
                muestra = rsgeneral.getInt(1);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Registro");
        }
        access2.desconectar();
    }

//buscamos si existe al menos un resultado llenado para la muestra
    public void encontrarid(String sql) {

        Conexion access2 = new Conexion();// instanciamos para nueva conexiona BD
        Statement stmt2 = null; // Creamos statement 
        String sqlregistro2 = sql;
        int encontrar = 0;

        try {

            sqlregistro2 = sqlregistro2 + muestra;

            stmt2 = access2.conn.createStatement();
            ResultSet rsgeneral;
            rsgeneral = stmt2.executeQuery(sqlregistro2);

            while (rsgeneral.next()) {
                //cargamos el id
                encontrar++;

            }
            if (encontrar > 0) {
                encontrado = true;
            } else {
                encontrado = false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Registro");
        }
        access2.desconectar();
    }

}
