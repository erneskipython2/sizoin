package controlador;

import java.util.ArrayList;
import logica.LogicaPredio;
import modeloBO.PredioBO;
import modeloDAO.PredioDAO;
import vista.RegistroGeneral;

/**
 *
 * @author Erneski Coronado
 */
public class CoordinadorPredio {
    private LogicaPredio miLogica;
    private RegistroGeneral miVentanaRegistro;
    private PredioDAO miPredioDAO = new PredioDAO();

    public RegistroGeneral getMiVentanaRegistro() {
        return miVentanaRegistro;
    }

    public void setMiVentanaRegistro(RegistroGeneral miVentanaRegistro) {
        this.miVentanaRegistro = miVentanaRegistro;
    }
    
    public LogicaPredio getMiLogica() {
        return miLogica;
    }

    public void setMiLogica(LogicaPredio miLogica) {
        this.miLogica = miLogica;
    }

    
    
    ////////////////////////////////////////////////////////////////////
        
        public boolean registrarPredio(PredioBO miPredio, ArrayList n) {
		boolean realizado=false;
            if(miLogica.validarRegistro(miPredio,n)==true)realizado=true;return realizado;
	}
        
        public boolean modificarPredio(PredioBO miPredio, ArrayList n) {
		boolean realizado=false;
            if(miLogica.validarModificacion(miPredio,n)==true)realizado=true;return realizado;
	}
        
        public boolean eliminarPredio(Integer id_predio) {
		boolean realizado=false;
            if(miLogica.validarEliminacion(id_predio)==true)realizado=true;return realizado;
	}
        
        public ArrayList cargarFinalidadPredio(Integer id_predio) {
		ArrayList n= new ArrayList();
            n=miPredioDAO.buscarFinalidadPredio(id_predio);
        return n;
	}
        
        
        
        
        
        
    
}
