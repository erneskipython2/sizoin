package logica;

import controlador.CoordinadorCuarentenario;
import javax.swing.JOptionPane;
import modeloDAO.CuarentenarioDAO;
import modeloBO.CuarentenarioBO;

/**
 *
 * @author Erneski Coronado
 */
public class LogicaCuarentenario {
    private CoordinadorCuarentenario miCoordinador;
    
    	public void setCoordinador(CoordinadorCuarentenario miCoordinador) {
		this.miCoordinador=miCoordinador;
		
	}

    
    public boolean validarRegistro(CuarentenarioBO miSede) {
        CuarentenarioDAO miSedeDao;
        boolean realizado = false;

        miSedeDao = new CuarentenarioDAO();

        if (miSedeDao.registrarSede(miSede) == true) {
            realizado = true;
        }
        return realizado;

    }

    public boolean validarEliminacion(Integer id_sede) {
        CuarentenarioDAO miSedeDao = new CuarentenarioDAO();
        boolean realizado = false;
        if (miSedeDao.eliminarSede(id_sede) == true) {
            realizado = true;
        }
        return realizado;
    }

    
}
