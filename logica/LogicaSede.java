package logica;

import controlador.CoordinadorSede;
import javax.swing.JOptionPane;
import modeloDAO.SedeDAO;
import modeloBO.SedeBO;

/**
 *
 * @author Erneski Coronado
 */
public class LogicaSede {
    private CoordinadorSede miCoordinador;
    
    	public void setCoordinador(CoordinadorSede miCoordinador) {
		this.miCoordinador=miCoordinador;
		
	}

    
    	public boolean validarRegistro(SedeBO miSede) {
		SedeDAO miSedeDao;
                boolean realizado=false;
                if(miSede.getParroquia_id()<1) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar la parroquia de la sede","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if(miSede.getSede()==null||miSede.getSede().equals("")||miSede.getSede().length()>100){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la sede o laboratorio","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else {
                        miSedeDao = new SedeDAO();
                        
			if(miSedeDao.registrarSede(miSede)==true) realizado=true;
		}return realizado;
		
	}
        
    	public boolean validarModificacion(SedeBO miSede) {
		SedeDAO miSedeDao;
                boolean realizado=false;
                if(miSede.getParroquia_id()<1) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar la parroquia de la sede","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if(miSede.getSede()==null||miSede.getSede().equals("")||miSede.getSede().length()>100){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la sede o laboratorio","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miSede.getResponsable()<1){
                        miSede.setResponsable(null);
                }else {
                        miSedeDao = new SedeDAO();
                        
			if(miSedeDao.modificarSede(miSede)==true) realizado=true;
		}return realizado;
		
	}
        
        public boolean validarEliminacion(Integer id_sede) {
		SedeDAO miSedeDao=new SedeDAO();
                boolean realizado=false;
		if(miSedeDao.eliminarSede(id_sede)==true) realizado=true;
		return realizado;
	}
        
    
}
