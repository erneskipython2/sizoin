package sizin;

import javax.swing.JComboBox;

/**
 *
 * @author Erneski Coronado
 */
public class ClaseObjetoParaComboBox {
    
 public String nombre;
  public int id;

    public ClaseObjetoParaComboBox(int id, String nombre)
      {
        this.id=id;
        this.nombre=nombre;
      }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
    @Override
    public String toString()
    {
     return nombre;
    }
   
}
