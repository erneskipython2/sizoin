package controlador;

import java.text.ParseException;
import java.util.ArrayList;
import logica.LogicaResultado;
import vista.RegistroGeneral;

/**
 *
 * @author Erneski Coronado
 */
public class CoordinadorResultado {
    private LogicaResultado miLogica;
    private RegistroGeneral miVentanaRegistro;

    public RegistroGeneral getMiVentanaRegistro() {
        return miVentanaRegistro;
    }

    public void setMiVentanaRegistro(RegistroGeneral miVentanaRegistro) {
        this.miVentanaRegistro = miVentanaRegistro;
    }
    
    public LogicaResultado getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(LogicaResultado miLogica) {
        this.miLogica = miLogica;
    }

    
    
    ////////////////////////////////////////////////////////////////////
        
        public boolean registrarResultado(ArrayList i, String procesado, ArrayList n) throws ParseException {
		boolean realizado=false;
            if(miLogica.validarResultado(i, procesado, n)==true)realizado=true;return realizado;
	}
        
                
        public boolean registrarResultadoAnemia(ArrayList i, String procesado, ArrayList n) throws ParseException {
		boolean realizado=false;
            if(miLogica.validarResultadoAnemia(i, procesado, n)==true)realizado=true;return realizado;
	}
        
        public boolean registrarResultadoHematologia(ArrayList i, String procesado, ArrayList n) throws ParseException {
		boolean realizado=false;
            if(miLogica.validarResultadoHematologia(i, procesado, n)==true)realizado=true;return realizado;
	}
        
        public boolean registrarResultadoHemoparasito(ArrayList i, String procesado, ArrayList n) throws ParseException {
		boolean realizado=false;
            if(miLogica.validarResultadoHemoparasito(i, procesado, n)==true)realizado=true;return realizado;
	}
        
        public boolean registrarResultadoCoprologia(ArrayList i, String procesado, ArrayList n) throws ParseException {
		boolean realizado=false;
            if(miLogica.validarResultadoCoprologia(i, procesado, n)==true)realizado=true;return realizado;
	}
               
    
}
