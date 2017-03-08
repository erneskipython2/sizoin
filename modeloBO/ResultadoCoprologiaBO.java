package modeloBO;

/**
 *
 * @author Erneski Coronado
 */
public class ResultadoCoprologiaBO {
    private Integer id_resultado_coprologia;
    private Integer id_registro_muestra;
    private Integer id_analisis_prueba;
    private String observacion;

    public Integer getId_resultado_coprologia() {
        return id_resultado_coprologia;
    }

    public void setId_resultado_coprologia(Integer id_resultado_coprologia) {
        this.id_resultado_coprologia = id_resultado_coprologia;
    }

    public Integer getId_registro_muestra() {
        return id_registro_muestra;
    }

    public void setId_registro_muestra(Integer id_registro_muestra) {
        this.id_registro_muestra = id_registro_muestra;
    }

    public Integer getId_analisis_prueba() {
        return id_analisis_prueba;
    }

    public void setId_analisis_prueba(Integer id_analisis_prueba) {
        this.id_analisis_prueba = id_analisis_prueba;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
}
