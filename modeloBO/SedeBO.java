package modeloBO;

/**
 *
 * @author Erneski Coronado
 */
public class SedeBO {
    private Integer id_sedes_insai;
    private String sede;
    private String num_sede;
    private String telefono;
    private String email;
    private Integer parroquia_id;
    private String direccion;
    private Integer responsable;

    public Integer getResponsable() {
        return responsable;
    }

    public void setResponsable(Integer responsable) {
        this.responsable = responsable;
    }
    
    

    public Integer getId_sedes_insai() {
        return id_sedes_insai;
    }

    public void setId_sedes_insai(Integer id_sedes_insai) {
        this.id_sedes_insai = id_sedes_insai;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getNum_sede() {
        return num_sede;
    }

    public void setNum_sede(String num_sede) {
        this.num_sede = num_sede;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getParroquia_id() {
        return parroquia_id;
    }

    public void setParroquia_id(Integer parroquia_id) {
        this.parroquia_id = parroquia_id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
