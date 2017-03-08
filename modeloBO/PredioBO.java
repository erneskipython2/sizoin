
package modeloBO;

import java.awt.Point;

/**
 *
 * @author Erneski Coronado
 */
public class PredioBO {
    private Integer id_predio;
    private Integer id_propietario;
    private String direccion;
    private Integer id_parroquia;
    private Integer id_municipio;
    private Integer id_estado;
    private String superficie;
    private String predio;
    private Float utm_este;
    private Float utm_norte;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public Integer getId_predio() {
        return id_predio;
    }

    public void setId_predio(Integer id_predio) {
        this.id_predio = id_predio;
    }

    public Integer getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(Integer id_propietario) {
        this.id_propietario = id_propietario;
    }



    public Integer getId_parroquia() {
        return id_parroquia;
    }

    public void setId_parroquia(Integer id_parroquia) {
        this.id_parroquia = id_parroquia;
    }

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public Float getUtm_este() {
        return utm_este;
    }

    public void setUtm_este(Float utm_este) {
        this.utm_este = utm_este;
    }

    public Float getUtm_norte() {
        return utm_norte;
    }

    public void setUtm_norte(Float utm_norte) {
        this.utm_norte = utm_norte;
    }



}
