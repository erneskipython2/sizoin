package logica;

import controlador.CoordinadorPropietario;
import javax.swing.JOptionPane;
import modeloDAO.PropietarioDAO;
import modeloBO.PropietarioBO;

/**
 *
 * @author Erneski Coronado
 */
public class LogicaPropietario {
    public static boolean loginUsuario=false;
    private CoordinadorPropietario miCoordinador;
    
    	public void setCoordinador(CoordinadorPropietario miCoordinador) {
		this.miCoordinador=miCoordinador;
		
	}

    
    	public boolean validarRegistro(PropietarioBO miPropietario) {
		PropietarioDAO miPropietarioDao;
                boolean realizado=false;
                if(miPropietario.getNombre()==null||miPropietario.getNombre().equals("")||miPropietario.getNombre().length()>45){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del propietario","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPropietario.getRif()==null||miPropietario.getRif().equals("")||miPropietario.getRif().length()<2||miPropietario.getRif().length()>11){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el RIF del propietario","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(miPropietario.getTelefono().length()>11){
                        JOptionPane.showMessageDialog(null, "Debe ingresar un telefono valido","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miPropietarioDao = new PropietarioDAO();
                        
			if(miPropietarioDao.registrarPropietario(miPropietario)==true) realizado=true;
		}return realizado;
		
	}
        
    	public boolean validarModificacion(PropietarioBO miPropietario) {
		PropietarioDAO miPropietarioDao;
                boolean realizado=false;
		if (miPropietario.getId_propietario()==null) {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar el propietario a modificar en la tabla","Advertencia",JOptionPane.WARNING_MESSAGE);						
		}else if(miPropietario.getNombre()==null||miPropietario.getNombre().equals("")||miPropietario.getNombre().length()>45){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del propietario","Advertencia",JOptionPane.WARNING_MESSAGE);	
                }else if(miPropietario.getRif()==null||miPropietario.getRif().equals("")||miPropietario.getRif().length()<2||miPropietario.getRif().length()>11){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el RIF del propietario","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else if(miPropietario.getTelefono().length()>11){
                        JOptionPane.showMessageDialog(null, "Debe ingresar un telefono valido","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else {
                        miPropietarioDao = new PropietarioDAO();
                        
			if(miPropietarioDao.modificarPropietario(miPropietario)==true) realizado=true;
		}return realizado;
		
	}
        
        public boolean validarEliminacion(Integer id_propietario) {
           boolean realizado=false; 
           if(id_propietario!=null){
		PropietarioDAO miPropietarioDao=new PropietarioDAO();
                
		if(miPropietarioDao.eliminarPropietario(id_propietario)==true){ realizado=true;}
           }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el propietario a eliminar","Advertencia",JOptionPane.WARNING_MESSAGE);
           }
		return realizado;
	}
        
        public boolean validarBusqueda(PropietarioBO miPropietario) {
           boolean realizado=false;
           if(miPropietario.getRif()==null||miPropietario.getRif().equals("")||miPropietario.getRif().length()<2||miPropietario.getRif().length()>11){
                        JOptionPane.showMessageDialog(null, "Debe ingresar el RIF del propietario a Buscar","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else{
                    PropietarioDAO miPropietarioDao=new PropietarioDAO();
                    if(miPropietarioDao.buscarPropietario(miPropietario)==true){ realizado=true;}            
           }                    
		return realizado;
	}
        
    
}
