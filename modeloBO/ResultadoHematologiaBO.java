package modeloBO;

/**
 *
 * @author Erneski Coronado
 */
public class ResultadoHematologiaBO {
    private Integer id_resultado_hematologia;
    private Float hematocrito;
    private Float hemoglobina;
    private Integer rojas;
    private Integer blancas;
    private Integer id_registro_muestra;

    public Integer getId_resultado_hematologia() {
        return id_resultado_hematologia;
    }

    public void setId_resultado_hematologia(Integer id_resultado_hematologia) {
        this.id_resultado_hematologia = id_resultado_hematologia;
    }

    public Float getHematocrito() {
        return hematocrito;
    }

    public void setHematocrito(Float hematocrito) {
        this.hematocrito = hematocrito;
    }

    public Float getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(Float hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public Integer getRojas() {
        return rojas;
    }

    public void setRojas(Integer rojas) {
        this.rojas = rojas;
    }

    public Integer getBlancas() {
        return blancas;
    }

    public void setBlancas(Integer blancas) {
        this.blancas = blancas;
    }

    public Integer getId_registro_muestra() {
        return id_registro_muestra;
    }

    public void setId_registro_muestra(Integer id_registro_muestra) {
        this.id_registro_muestra = id_registro_muestra;
    }
    
    
        
}
