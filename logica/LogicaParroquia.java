package logica;

import controlador.CoordinadorParroquia;
import javax.swing.JOptionPane;
import modeloDAO.ParroquiaDAO;
import modeloBO.ParroquiaBO;

/**
 *
 * @author Erneski Coronado
 */
public class LogicaParroquia {
    public static boolean loginUsuario=false;
    private CoordinadorParroquia miCoordinador;
    
    	public void setCoordinador(CoordinadorParroquia miCoordinador) {
		this.miCoordinador=miCoordinador;
		
	}

    
    	public boolean validarRegistro(ParroquiaBO miParroquia) {
		ParroquiaDAO miParroquiaDao;
                boolean realizado=false;
                if(miParroquia.getParroquia()==null||miParroquia.getParroquia().equals("")||miParroquia.getParroquia().length()>80){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la parroquia","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miParroquia.getId_municipio()==null||miParroquia.getId_municipio()==0){
                        JOptionPane.showMessageDialog(null, "Debe seleccionar el municipio primero","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miParroquiaDao = new ParroquiaDAO();
                        
			if(miParroquiaDao.registrarParroquia(miParroquia)==true) realizado=true;
		}return realizado;
		
	}
        
    	
        
    
}
