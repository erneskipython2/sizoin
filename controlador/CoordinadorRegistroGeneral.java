package controlador;

import java.text.ParseException;
import java.util.ArrayList;
import logica.LogicaRegistroGeneral;
import modeloBO.RegistroGeneralBO;
import modeloDAO.RegistroGeneralDAO;
import vista.RegistroGeneral;

/**
 *
 * @author Erneski Coronado
 */
public class CoordinadorRegistroGeneral {
    private LogicaRegistroGeneral miLogica;
    private RegistroGeneral miVentanaRegistro;

    public LogicaRegistroGeneral getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(LogicaRegistroGeneral miLogica) {
        this.miLogica = miLogica;
    }

    public RegistroGeneral getMiVentanaRegistro() {
        return miVentanaRegistro;
    }

    public void setMiVentanaRegistro(RegistroGeneral miVentanaRegistro) {
        this.miVentanaRegistro = miVentanaRegistro;
    }
    
    ////////////////////////////////////////////////////////////////////

        public boolean registrarRegistro(RegistroGeneralBO miPersona, ArrayList m) throws ParseException {
		boolean realizado=false;
            if(miLogica.validarRegistro(miPersona, m)==true)realizado=true;return realizado;
	}
        
        public boolean modificarRegistro(RegistroGeneralBO miPersona, ArrayList m) throws ParseException {
		boolean realizado=false;
            if(miLogica.validarModificacion(miPersona,m)==true)realizado=true;return realizado;
	}
        
        public boolean buscarRegistro(String sql) {
		boolean realizado=false;
            if(miLogica.validarBusqueda(sql)==true)realizado=true;return realizado;
	}
        
       public RegistroGeneralBO cargarRegistro(String protocoloid) {	
           RegistroGeneralBO miRegistro= new RegistroGeneralBO(); 
            miRegistro=miLogica.validarCarga(protocoloid);
            return miRegistro;
	}
        
        public boolean eliminarRegistro(String protocolo) {
		boolean realizado=false;
            if(miLogica.validarEliminacion(protocolo)==true)realizado=true;return realizado;
	}
        
        
    
}
