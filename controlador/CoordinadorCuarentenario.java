package controlador;

import logica.LogicaCuarentenario;
import modeloBO.CuarentenarioBO;
import modeloDAO.CuarentenarioDAO;
import vista.Epidemiologia;

/**
 *
 * @author Erneski Coronado
 */
public class CoordinadorCuarentenario {
    private LogicaCuarentenario miLogica;
    private Epidemiologia miVentanaConfiguracion;

    public Epidemiologia getMiVentanaConfiguracion() {
        return miVentanaConfiguracion;
    }

    public void setMiVentanaConfiguracion(Epidemiologia miVentanaConfiguracion) {
        this.miVentanaConfiguracion = miVentanaConfiguracion;
    }
    
    public LogicaCuarentenario getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(LogicaCuarentenario miLogica) {
        this.miLogica = miLogica;
    }

    
    
    ////////////////////////////////////////////////////////////////////
        
        public boolean registrarOrganismo(CuarentenarioBO miSede) {
		boolean realizado=false;
            if(miLogica.validarRegistro(miSede)==true)realizado=true;return realizado;
	}
        
        
        public boolean eliminarOrganismo(Integer id_sede) {
		boolean realizado=false;
            if(miLogica.validarEliminacion(id_sede)==true)realizado=true;return realizado;
	}
        
        
    
}
