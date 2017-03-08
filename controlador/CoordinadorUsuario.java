package controlador;

import logica.LogicaUsuario;
import modeloBO.UsuarioBO;
import modeloDAO.UsuarioDAO;
import vista.Configuracion;
import vista.LoginUsuario;

/**
 *
 * @author Erneski Coronado
 */
public class CoordinadorUsuario {
    private LogicaUsuario miLogica;
    private LoginUsuario miLogin_usuario;
    private Configuracion miVentanaConfiguracion;

    public Configuracion getMiVentanaConfiguracion() {
        return miVentanaConfiguracion;
    }

    public void setMiVentanaConfiguracion(Configuracion miVentanaConfiguracion) {
        this.miVentanaConfiguracion = miVentanaConfiguracion;
    }
    
    public LogicaUsuario getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(LogicaUsuario miLogica) {
        this.miLogica = miLogica;
    }

    public LoginUsuario getMiLogin_usuario() {
        return miLogin_usuario;
    }

    public void setMiLogin_usuario(LoginUsuario miLogin_usuario) {
        this.miLogin_usuario = miLogin_usuario;
    }

    
    
    ////////////////////////////////////////////////////////////////////
    
    	public UsuarioBO loginUsuario(String usuario, String clave) {
		return miLogica.validarUsuario(usuario, clave);
	}
        
        public boolean registrarUsuario(UsuarioBO miPersona) {
		boolean realizado=false;
            if(miLogica.validarRegistro(miPersona)==true)realizado=true;return realizado;
	}
        
        public boolean modificarUsuario(UsuarioBO miPersona) {
		boolean realizado=false;
            if(miLogica.validarModificacion(miPersona)==true)realizado=true;return realizado;
	}
        
        public boolean eliminarUsuario(Integer id_usuario) {
		boolean realizado=false;
            if(miLogica.validarEliminacion(id_usuario)==true)realizado=true;return realizado;
	}
        
        
    
}
