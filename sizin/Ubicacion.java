package sizin;

/**
 *
 * @author Erneski Coronado
 */
public class Ubicacion {
    
    private static double latitud=0.0;
    private static double longitud=0.0;
    private static String direccion;

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setLatitud(double aLatitud) {
        latitud = aLatitud;
    }

    public void setLongitud(double aLongitud) {
        longitud = aLongitud;
    }

    public void setDireccion(String aDireccion) {
        direccion = aDireccion;
    }
    
}
