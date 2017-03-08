package logica;

import controlador.CoordinadorResultado;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modeloDAO.BrucelosisDAO;
import modeloBO.ResultadoBrucelosisBO;
import java.util.Date;
import modeloDAO.AnemiaDAO;
import modeloDAO.CoprologiaDAO;
import modeloDAO.HematologiaDAO;
import modeloDAO.HemoparasitoDAO;

/**
 *
 * @author Erneski Coronado
 */
public class LogicaResultado {

    public static boolean loginUsuario = false;
    private CoordinadorResultado miCoordinador;

    public void setCoordinador(CoordinadorResultado miCoordinador) {
        this.miCoordinador = miCoordinador;

    }

    public boolean validarResultado(ArrayList i, String procesado, ArrayList n) throws ParseException {
        BrucelosisDAO miBrucelosisDao;
        boolean realizado = false;
        miBrucelosisDao = new BrucelosisDAO();
        if (n.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un analista para los resultados", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (miBrucelosisDao.registrarResultado(i, procesado, n) == true) {
                realizado = true;
            }
        }
        return realizado;
    }

    public boolean validarResultadoAnemia(ArrayList i, String procesado, ArrayList n) throws ParseException {
        AnemiaDAO miAnemiaDao;
        boolean realizado = false;
        miAnemiaDao = new AnemiaDAO();
        if (n.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un analista para los resultados", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (miAnemiaDao.registrarResultado(i, procesado, n) == true) {
                realizado = true;
            }
        }
        return realizado;
    }

    public boolean validarResultadoHematologia(ArrayList i, String procesado, ArrayList n) throws ParseException {
        HematologiaDAO miHematologiaDao;
        boolean realizado = false;
        miHematologiaDao = new HematologiaDAO();
        if (n.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un analista para los resultados", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (miHematologiaDao.registrarResultado(i, procesado, n) == true) {
                realizado = true;
            }
        }
        return realizado;
    }

    public boolean validarResultadoHemoparasito(ArrayList i, String procesado, ArrayList n) throws ParseException {
        HemoparasitoDAO miHemoparasitoDao;
        boolean realizado = false;
        miHemoparasitoDao = new HemoparasitoDAO();
        if (n.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un analista para los resultados", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (miHemoparasitoDao.registrarResultado(i, procesado, n) == true) {
                realizado = true;
            }
        }
        return realizado;
    }
    
    public boolean validarResultadoCoprologia(ArrayList i, String procesado, ArrayList n) throws ParseException {
        CoprologiaDAO miCoprologiaDao;
        boolean realizado = false;
        miCoprologiaDao = new CoprologiaDAO();
        if (n.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un analista para los resultados", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (miCoprologiaDao.registrarResultado(i, procesado, n) == true) {
                realizado = true;
            }
        }
        return realizado;
    }

}
