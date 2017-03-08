/**
 *
 * @author Erneski coronado
 */
package sizin;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.view.save.JRPdfSaveContributor.*;
import net.sf.jasperreports.view.JRViewer.*;
import net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor.*;

public class EjecutarReporte {
    private Conexion con = new Conexion();  


    public void startReport(String parametro,int id, String archivo){

        try{
            javax.swing.JOptionPane.showMessageDialog(null,"Reporte Generado!");
            //ubicar la url relativa del reporte
            String template= "C:"+System.getProperty("file.separator")+"sizin_reportes"+System.getProperty("file.separator")+
 System.getProperty("file.separator")+archivo+".jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            Map param=new HashMap();
            param.put(parametro, id);
             JasperPrint jasperprint= null;
            if(id>0){
            jasperprint= JasperFillManager.fillReport(reporte,param,con.getConnection());    
            }else{
            jasperprint= JasperFillManager.fillReport(reporte,new HashMap(),con.getConnection());    
            }            
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("SIZOIN Reportes");
            visor.setVisible(true);
            con.desconectar();

        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
    
        public void startReportString(String parametro,String id, String archivo){

        try{
            javax.swing.JOptionPane.showMessageDialog(null,"Reporte Generado!");
            //ubicar la url relativa del reporte
            String template= "C:"+System.getProperty("file.separator")+"sizin_reportes"+System.getProperty("file.separator")+
            System.getProperty("file.separator")+archivo+".jasper";
            
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            Map param=new HashMap();
            param.put(parametro, id);
             JasperPrint jasperprint= null;
            if(id.isEmpty()==false){
            jasperprint= JasperFillManager.fillReport(reporte,param,con.getConnection());    
            }else{
            jasperprint= JasperFillManager.fillReport(reporte,new HashMap(),con.getConnection());    
            }            
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("SIZOIN Reportes");
            visor.setVisible(true);
            con.desconectar();

        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
        
        public void startReportMensual(String parametro_sede,Integer id_sede,String fecha_ini,String fecha_ini_id, String fecha_fin,String fecha_fin_id, String archivo){

        try{
            javax.swing.JOptionPane.showMessageDialog(null,"Reporte Generado!");
            //ubicar la url relativa del reporte
            String template= "C:"+System.getProperty("file.separator")+"sizin_reportes"+System.getProperty("file.separator")+
            System.getProperty("file.separator")+archivo+".jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            Map param=new HashMap();
            param.put(parametro_sede, id_sede);
            param.put(fecha_ini, fecha_ini_id);
            param.put(fecha_fin, fecha_fin_id);
             JasperPrint jasperprint= null;
            if(id_sede>0){
            jasperprint= JasperFillManager.fillReport(reporte,param,con.getConnection());    
            }else{
            jasperprint= JasperFillManager.fillReport(reporte,new HashMap(),con.getConnection());    
            }            
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("SIZOIN Reportes");
            visor.setVisible(true);
            con.desconectar();

        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }



}
