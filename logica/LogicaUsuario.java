package logica;

import controlador.CoordinadorUsuario;
import javax.swing.JOptionPane;
import modeloDAO.UsuarioDAO;
import modeloBO.UsuarioBO;

/**
 *
 * @author Erneski Coronado
 */
public class LogicaUsuario {
    public static boolean loginUsuario=false;
    private CoordinadorUsuario miCoordinador;
    
    	public void setCoordinador(CoordinadorUsuario miCoordinador) {
		this.miCoordinador=miCoordinador;
		
	}

    public UsuarioBO validarUsuario(String usuario, String clave) {
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
    }
    
    	public boolean validarRegistro(UsuarioBO miPersona) {
		UsuarioDAO miUsuarioDao;
                boolean realizado=false;
		if (miPersona.getId_personal()<1) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el tipo de usuario","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if (miPersona.getId_personal()<1){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar la persona asignada al usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(miPersona.getUsuario().length()<3||miPersona.getUsuario().length()>15){
                        JOptionPane.showMessageDialog(null, "Debe ingresar un Usuario de 3 a 15 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPersona.getClave().length()<3||miPersona.getClave().length()>15){
                        JOptionPane.showMessageDialog(null, "Debe ingresar una Clave de 3 a 15 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miUsuarioDao = new UsuarioDAO();
                        
			if(miUsuarioDao.registrarUsuario(miPersona)==true) realizado=true;
		}return realizado;
		
	}
        
    	public boolean validarModificacion(UsuarioBO miPersona) {
		UsuarioDAO miUsuarioDao;
                boolean realizado=false;
		if (miPersona.getId_usuario()==null) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el usuario a modificar en la tabla","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if(miPersona.getId_personal()<1) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el tipo de usuario","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if (miPersona.getId_personal()<1){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar la persona asignada al usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(miPersona.getUsuario().length()<3||miPersona.getUsuario().length()>15){
                        JOptionPane.showMessageDialog(null, "Debe ingresar un Usuario de 3 a 15 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPersona.getClave().length()<3||miPersona.getClave().length()>15){
                        JOptionPane.showMessageDialog(null, "Debe ingresar una Clave de 3 a 15 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miUsuarioDao = new UsuarioDAO();
                        
			if(miUsuarioDao.modificarUsuario(miPersona)==true) realizado=true;
		}return realizado;
		
	}
        
        public boolean validarEliminacion(Integer id_usuario) {
		UsuarioDAO miUsuarioDao=new UsuarioDAO();
                boolean realizado=false;
		if(miUsuarioDao.eliminarUsuario(id_usuario)==true) realizado=true;
		return realizado;
	}
        
    
}
