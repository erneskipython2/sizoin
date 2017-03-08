package controlador;

import logica.LogicaPropietario;
import modeloBO.PropietarioBO;
import modeloDAO.PropietarioDAO;
import vista.RegistroGeneral;

/**
 *
 * @author Erneski Coronado
 */
public class CoordinadorPropietario {
    private LogicaPropietario miLogica;
    private RegistroGeneral miVentanaRegistro;

    public RegistroGeneral getMiVentanaRegistro() {
        return miVentanaRegistro;
    }

    public void setMiVentanaRegistro(RegistroGeneral miVentanaRegistro) {
        this.miVentanaRegistro = miVentanaRegistro;
    }
    
    public LogicaPropietario getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(LogicaPropietario miLogica) {
        this.miLogica = miLogica;
    }

    
    
    ////////////////////////////////////////////////////////////////////
        
        public boolean registrarPropietario(PropietarioBO miPropietario) {
		boolean realizado=false;
            if(miLogica.validarRegistro(miPropietario)==true)realizado=true;return realizado;
	}
        
        public boolean modificarPropietario(PropietarioBO miPropietario) {
		boolean realizado=false;
            if(miLogica.validarModificacion(miPropietario)==true)realizado=true;return realizado;
	}
        
        public boolean eliminarPropietario(Integer id_propietario) {
		boolean realizado=false;
            if(miLogica.validarEliminacion(id_propietario)==true)realizado=true;return realizado;
	}
        
        public boolean buscarPropietario(PropietarioBO miPropietario) {
		boolean realizado=false;
            if(miLogica.validarBusqueda(miPropietario)==true)realizado=true;return realizado;
	}
        
       
    
}
