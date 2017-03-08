package logica;

import controlador.CoordinadorPredio;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modeloDAO.PredioDAO;
import modeloBO.PredioBO;

/**
 *
 * @author Erneski Coronado
 */
public class LogicaPredio {
    public static boolean loginUsuario=false;
    private CoordinadorPredio miCoordinador;
    
    	public void setCoordinador(CoordinadorPredio miCoordinador) {
		this.miCoordinador=miCoordinador;
		
	}

    
    	public boolean validarRegistro(PredioBO miPredio, ArrayList n) {
		PredioDAO miPredioDao;
                boolean realizado=false;
                if(miPredio.getPredio()==null||miPredio.getPredio().equals("")||miPredio.getPredio().length()>100){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del predio","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPredio.getId_estado()==null||miPredio.getId_estado()==0){
                        JOptionPane.showMessageDialog(null, "Debe seleccionar el estado de procedencia","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(miPredio.getId_municipio()==null||miPredio.getId_municipio()==0){
                        JOptionPane.showMessageDialog(null, "Debe seleccionar el municipio de procedencia","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(miPredio.getId_parroquia()==null||miPredio.getId_parroquia()==0){
                        JOptionPane.showMessageDialog(null, "Debe seleccionar la parroquia de procedencia","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miPredioDao = new PredioDAO();
                        
			if(miPredioDao.registrarPredio(miPredio, n)==true) realizado=true;
		}return realizado;
		
	}
        
    	public boolean validarModificacion(PredioBO miPredio, ArrayList n) {
		PredioDAO miPredioDao;
                boolean realizado=false;
                if(miPredio.getPredio()==null||miPredio.getPredio().equals("")||miPredio.getPredio().length()>100){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del predio","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPredio.getId_estado()==null||miPredio.getId_estado()==0){
                        JOptionPane.showMessageDialog(null, "Debe seleccionar el estado de procedencia","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(miPredio.getId_municipio()==null||miPredio.getId_municipio()==0){
                        JOptionPane.showMessageDialog(null, "Debe seleccionar el municipio de procedencia","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(miPredio.getId_parroquia()==null||miPredio.getId_parroquia()==0){
                        JOptionPane.showMessageDialog(null, "Debe seleccionar la parroquia de procedencia","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miPredioDao = new PredioDAO();
                        
			if(miPredioDao.modificarPredio(miPredio,n)==true) realizado=true;
		}return realizado;
		
	}
        
        public boolean validarEliminacion(Integer id_predio) {
           boolean realizado=false; 
           if(id_predio!=null){
		PredioDAO miPredioDao=new PredioDAO();
                
		if(miPredioDao.eliminarPredio(id_predio)==true){ realizado=true;}
           }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el predio a eliminar","Advertencia",JOptionPane.WARNING_MESSAGE);
           }
		return realizado;
	}
        
    
}
