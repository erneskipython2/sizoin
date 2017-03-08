package controlador;

import logica.LogicaParroquia;
import modeloBO.ParroquiaBO;
import modeloDAO.ParroquiaDAO;
import vista.RegistroGeneral;

/**
 *
 * @author Erneski Coronado
 */
public class CoordinadorParroquia {
    private LogicaParroquia miLogica;
    private RegistroGeneral miVentanaRegistro;

    public RegistroGeneral getMiVentanaRegistro() {
        return miVentanaRegistro;
    }

    public void setMiVentanaRegistro(RegistroGeneral miVentanaRegistro) {
        this.miVentanaRegistro = miVentanaRegistro;
    }
    
    public LogicaParroquia getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(LogicaParroquia miLogica) {
        this.miLogica = miLogica;
    }

    
    
    ////////////////////////////////////////////////////////////////////
        
        public boolean registrarParroquia(ParroquiaBO miParroquia) {
		boolean realizado=false;
            if(miLogica.validarRegistro(miParroquia)==true)realizado=true;return realizado;
	}
               
    
}
