package controlador;

import logica.LogicaSede;
import modeloBO.SedeBO;
import modeloDAO.SedeDAO;
import vista.Configuracion;

/**
 *
 * @author Erneski Coronado
 */
public class CoordinadorSede {
    private LogicaSede miLogica;
    private Configuracion miVentanaConfiguracion;

    public Configuracion getMiVentanaConfiguracion() {
        return miVentanaConfiguracion;
    }

    public void setMiVentanaConfiguracion(Configuracion miVentanaConfiguracion) {
        this.miVentanaConfiguracion = miVentanaConfiguracion;
    }
    
    public LogicaSede getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(LogicaSede miLogica) {
        this.miLogica = miLogica;
    }

    
    
    ////////////////////////////////////////////////////////////////////
        
        public boolean registrarSede(SedeBO miSede) {
		boolean realizado=false;
            if(miLogica.validarRegistro(miSede)==true)realizado=true;return realizado;
	}
        
        public boolean modificarSede(SedeBO miSede) {
		boolean realizado=false;
            if(miLogica.validarModificacion(miSede)==true)realizado=true;return realizado;
	}
        
        public boolean eliminarSede(Integer id_sede) {
		boolean realizado=false;
            if(miLogica.validarEliminacion(id_sede)==true)realizado=true;return realizado;
	}
        
        
    
}
