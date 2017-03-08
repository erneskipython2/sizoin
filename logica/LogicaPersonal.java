package logica;

import controlador.CoordinadorPersonal;
import javax.swing.JOptionPane;
import modeloDAO.PersonalDAO;
import modeloBO.PersonalBO;
import sizin.ValidacionesVista;

/**
 *
 * @author Erneski Coronado
 */
public class LogicaPersonal {
    public static boolean loginUsuario=false;
    private CoordinadorPersonal miCoordinador;
    ValidacionesVista em= new ValidacionesVista();
    	public void setCoordinador(CoordinadorPersonal miCoordinador) {
		this.miCoordinador=miCoordinador;
		
	}

    
    	public boolean validarRegistro(PersonalBO miPersona) {
		PersonalDAO miPersonaDao;
                boolean realizado=false;
                
                
                if(miPersona.getId_sede()<1) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar la sede del personal","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if(miPersona.getNombres()==null||miPersona.getNombres().equals("")||miPersona.getNombres().length()>45){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la persona","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPersona.getApellidos()==null||miPersona.getApellidos().equals("")||miPersona.getApellidos().length()>45){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la persona","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPersona.getCedula()==null||miPersona.getCedula().equals("")||miPersona.getCedula().length()<2||miPersona.getCedula().length()>11){
                        JOptionPane.showMessageDialog(null, "Debe ingresar la cedula del personal","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(em.isCed(miPersona.getCedula())==false){
                        JOptionPane.showMessageDialog(null, "Debe ingresar la cedula con el Formato V-número ó E-número","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miPersonaDao = new PersonalDAO();
                        
			if(miPersonaDao.registrarPersonal(miPersona)==true) realizado=true;
		}return realizado;
		
	}
        
    	public boolean validarModificacion(PersonalBO miPersona) {
		PersonalDAO miPersonaDao;
                boolean realizado=false;
		if (miPersona.getId_personal()==null) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el personal a modificar en la tabla","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if(miPersona.getId_sede()<1) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar la sede del personal","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if(miPersona.getNombres()==null||miPersona.getNombres().equals("")||miPersona.getNombres().length()>45){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre completo de la persona","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPersona.getApellidos()==null||miPersona.getApellidos().equals("")||miPersona.getApellidos().length()>45){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la persona","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPersona.getCedula()==null||miPersona.getCedula().equals("")||miPersona.getCedula().length()<2||miPersona.getCedula().length()>11){
                        JOptionPane.showMessageDialog(null, "Debe ingresar la cedula del personal","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(em.isCed(miPersona.getCedula())==false){
                        JOptionPane.showMessageDialog(null, "Debe ingresar la cedula con el Formato V-número ó E-número","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miPersonaDao = new PersonalDAO();
                        
			if(miPersonaDao.modificarPersonal(miPersona)==true) realizado=true;
		}return realizado;
		
	}
        
        public boolean validarEliminacion(Integer id_personal) {
		PersonalDAO miPersonalDao=new PersonalDAO();
                boolean realizado=false;
		if(miPersonalDao.eliminarPersonal(id_personal)==true) realizado=true;
		return realizado;
	}
        
    
}
