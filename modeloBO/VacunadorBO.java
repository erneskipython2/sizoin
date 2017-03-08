package modeloBO;

/**
 *
 * @author Erneski Coronado
 */
public class VacunadorBO {
    private Integer id_vacunador;
    private Integer id_sede;
    private String nombre;
    private String apellido;
    private String cedula;
    private String telefono;

    public Integer getId_vacunador() {
        return id_vacunador;
    }

    public void setId_vacunador(Integer id_vacunador) {
        this.id_vacunador = id_vacunador;
    }

    public Integer getId_sede() {
        return id_sede;
    }

    public void setId_sede(Integer id_sede) {
        this.id_sede = id_sede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
