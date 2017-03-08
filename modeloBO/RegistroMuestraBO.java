package modeloBO;

import java.sql.Date;

/**
 *
 * @author Erneski Coronado
 */
public class RegistroMuestraBO {
    private Integer id_registro_muestra;
    private Integer id_registro_general;
    private Integer id_tsn_especie;
    private String nombre_completo;
    private Integer id_muestra_tipo;
    private String muestra_tipo;
    private String hierro;
    private Date fecha_vacunacion;
    private String sexo;
    private Integer edad_meses;
    private String observacion;

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getMuestra_tipo() {
        return muestra_tipo;
    }

    public void setMuestra_tipo(String muestra_tipo) {
        this.muestra_tipo = muestra_tipo;
    }
    
    

    public Integer getId_registro_muestra() {
        return id_registro_muestra;
    }

    public void setId_registro_muestra(Integer id_registro_muestra) {
        this.id_registro_muestra = id_registro_muestra;
    }

    public Integer getId_registro_general() {
        return id_registro_general;
    }

    public void setId_registro_general(Integer id_registro_general) {
        this.id_registro_general = id_registro_general;
    }

    public Integer getId_tsn_especie() {
        return id_tsn_especie;
    }

    public void setId_tsn_especie(Integer id_tsn_especie) {
        this.id_tsn_especie = id_tsn_especie;
    }


    public Integer getId_muestra_tipo() {
        return id_muestra_tipo;
    }

    public void setId_muestra_tipo(Integer id_muestra_tipo) {
        this.id_muestra_tipo = id_muestra_tipo;
    }

    public String getHierro() {
        return hierro;
    }

    public void setHierro(String hierro) {
        this.hierro = hierro;
    }

    public Date getFecha_vacunacion() {
        return fecha_vacunacion;
    }

    public void setFecha_vacunacion(Date fecha_vacunacion) {
        this.fecha_vacunacion = fecha_vacunacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad_meses() {
        return edad_meses;
    }

    public void setEdad_meses(Integer edad_meses) {
        this.edad_meses = edad_meses;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
    
    
}
