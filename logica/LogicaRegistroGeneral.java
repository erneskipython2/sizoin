package logica;

import controlador.CoordinadorRegistroGeneral;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloDAO.RegistroGeneralDAO;
import modeloBO.RegistroGeneralBO;

/**
 *
 * @author Erneski Coronado
 */
public class LogicaRegistroGeneral {
    public static boolean loginUsuario=false;
    private CoordinadorRegistroGeneral miCoordinador;
    
    	public void setCoordinador(CoordinadorRegistroGeneral miCoordinador) {
		this.miCoordinador=miCoordinador;
		
	}

   /* public UsuarioBO validarUsuario(String usuario, String clave) {
        		UsuarioDAO miUsuarioDao;
		
		try {
                    if(usuario.length()==0){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el Usuario","Informacion",JOptionPane.INFORMATION_MESSAGE);	
                    }else if(clave.length()==0){
                        JOptionPane.showMessageDialog(null, "Debe ingresar la Clave","Informacion",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        
                        miUsuarioDao = new UsuarioDAO();
			loginUsuario=true;
			return miUsuarioDao.loginUsuario(usuario, clave);
                    }
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Se ha presentado un Error","Error",JOptionPane.ERROR_MESSAGE);
			loginUsuario=false;
		}
					
		return null;
    }*/
    
    	public boolean validarRegistro(RegistroGeneralBO miPersona, ArrayList m) throws ParseException {
		RegistroGeneralDAO miRegistroDao;
                boolean realizado=false;
		if (miPersona.getId_analisis_tipo()<1) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el tipo de analisis","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if (miPersona.getId_propietario()<1){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el propietario de la muestra","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if (miPersona.getId_predio()<1){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el predio de origen","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miRegistroDao = new RegistroGeneralDAO();
                        
			if(miRegistroDao.registrarRegistro(miPersona,m)==true) realizado=true;
		}return realizado;
		
	}
        
public boolean validarModificacion(RegistroGeneralBO miPersona, ArrayList m) throws ParseException {
		RegistroGeneralDAO miRegistroDao;
                boolean realizado=false;
		if (miPersona.getId_analisis_tipo()<1) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el tipo de analisis","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if (miPersona.getId_propietario()<1){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el propietario de la muestra","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if (miPersona.getId_predio()<1){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el predio de origen","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miRegistroDao = new RegistroGeneralDAO();
                        
			if(miRegistroDao.modificarRegistro(miPersona,m)==true) realizado=true;
		}return realizado;
		
	}


                public boolean validarBusqueda(String sql) {
		RegistroGeneralDAO miUsuarioDao=new RegistroGeneralDAO();
                boolean realizado=false;
		if(miUsuarioDao.buscarRegistro(sql)==true) realizado=true;
		return realizado;
	}
                
                public RegistroGeneralBO validarCarga(String protocoloid) {
                RegistroGeneralBO miRegistro= new RegistroGeneralBO();   
		RegistroGeneralDAO miUsuarioDao=new RegistroGeneralDAO();
                boolean realizado=false;
        try {
            miRegistro=miUsuarioDao.cargarRegistro(protocoloid);
        } catch (ParseException ex) {
            Logger.getLogger(LogicaRegistroGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }
                return miRegistro;
		
	}
        
        public boolean validarEliminacion(String protocolo) {
		RegistroGeneralDAO miUsuarioDao=new RegistroGeneralDAO();
                boolean realizado=false;
		if(miUsuarioDao.eliminarRegistro(protocolo)==true) realizado=true;
		return realizado;
	}
        
    
}
