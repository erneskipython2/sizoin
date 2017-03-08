package sizin;



/**
 *
 * @author Erneski Coronado
 */
public class ClaseObjetoParaLista {
    
 public String nombre;
  public int id_tsn;
  public int id_rango;
  public String rango;

    public ClaseObjetoParaLista(int id_tsn, String nombre, int id_rango, String rango)
      {
        this.id_tsn=id_tsn;
        this.nombre=nombre;
        this.id_rango=id_rango;
        this.rango=rango;
      }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public int getId_rango() {
        return id_rango;
    }

    public void setId_rango(int id_rango) {
        this.id_rango = id_rango;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id_tsn;
    }

    public void setId(int id_tsn) {
        this.id_tsn = id_tsn;
    }
    
    
    
    
    
    @Override
    public String toString()
    {
     return nombre;
    }
   
}
