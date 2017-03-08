package controlador;

import logica.LogicaPersonal;
import modeloBO.PersonalBO;
import modeloDAO.PersonalDAO;
import vista.Configuracion;

/**
 *
 * @author Erneski Coronado
 */
public class CoordinadorPersonal {
    private LogicaPersonal miLogica;
    private Configuracion miVentanaConfiguracion;

    public Configuracion getMiVentanaConfiguracion() {
        return miVentanaConfiguracion;
    }

    public void setMiVentanaConfiguracion(Configuracion miVentanaConfiguracion) {
        this.miVentanaConfiguracion = miVentanaConfiguracion;
    }
    
    public LogicaPersonal getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(LogicaPersonal miLogica) {
        this.miLogica = miLogica;
    }

    
    
    ////////////////////////////////////////////////////////////////////
        
        public boolean registrarPersonal(PersonalBO miPersona) {
		boolean realizado=false;
            if(miLogica.validarRegistro(miPersona)==true)realizado=true;return realizado;
	}
        
        public boolean modificarPersonal(PersonalBO miPersona) {
		boolean realizado=false;
            if(miLogica.validarModificacion(miPersona)==true)realizado=true;return realizado;
	}
        
        public boolean eliminarPersonal(Integer id_personal) {
		boolean realizado=false;
            if(miLogica.validarEliminacion(id_personal)==true)realizado=true;return realizado;
	}
        
        
    
}
