/*
 * @author  Erneski Coronado
 * Clase para colocar imagen de fondo en jframe, trabaja en union con JPanelConFondo
 */

package sizin;
import java.awt.Image;
import javax.swing.JFrame;

//clase que extiende de JFrame para poder colocar fondo a un JFrame
public class JFrameConFondo extends JFrame {

    private final JPanelConFondo contenedor = new JPanelConFondo();

    public JFrameConFondo() {
        setContentPane(contenedor);
    }

    public void setImagen(String nombreImagen) {
        contenedor.setImagen(nombreImagen);
    }

    public void setImagen(Image nuevaImagen) {
        contenedor.setImagen(nuevaImagen);
    }
    

    
}