package sizin;

import controlador.CoordinadorUsuario;
import logica.LogicaUsuario;
import vista.LoginUsuario;


/**
 *
 * @author ERNESKI CORONADO
 */


public class Sizin {
    LogicaUsuario miLogica;
    CoordinadorUsuario miCoordinador;
    LoginUsuario miVentana_login;

    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		Sizin miPrincipal=new Sizin();
		miPrincipal.iniciar();
    }
    	/**
	 * Permite instanciar todas las clases con las que trabaja
	 * el sistema
	 */
    	private void iniciar() {
		/*Se instancian las clases*/
		miVentana_login=new LoginUsuario();

		miLogica=new LogicaUsuario();
		miCoordinador= new CoordinadorUsuario();
		
		/*Se establecen las relaciones entre clases*/
		miVentana_login.setCoordinador(miCoordinador);
		miLogica.setCoordinador(miCoordinador);
		
		/*Se establecen relaciones con la clase coordinador*/
		miCoordinador.setMiLogin_usuario(miVentana_login);
		miCoordinador.setMiLogica(miLogica);
		miVentana_login.setLocationRelativeTo(null);
		miVentana_login.setVisible(true);
	}
    
}
