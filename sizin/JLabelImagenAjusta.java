package sizin;

import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Erneski Coronado
 */
public class JLabelImagenAjusta {
    
    
    public void ImagenJLabel(JLabel j, String path ){ 
     
        URL url = this.getClass().getResource(path);          
        ImageIcon icon = new ImageIcon(url); 
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(j.getWidth(), j.getHeight(), Image.SCALE_DEFAULT));
        j.setIcon(icono);  
        j.repaint();
        
    }
    
        public void LimpiarImagenJLabel(JLabel j){ 
        j.setIcon(null);  
        j.repaint();
        
    }
        
public static String convertToMultiline(String orig)
{
    return "<html><P ALIGN=center>" + orig.replaceAll("\n", "<br>");
}


    
    
    
}
