package modeloBO;

/**
 *
 * @author Erneski Coronado
 */
public class ResultadoTitulacionBO {
    private Integer id_resultado_titulacion;
    private Integer id_registro_muestra;
    private Integer id_analisis_prueba;
    private Integer id_antigeno_lote;
    private String resultado;

    public Integer getId_resultado_titulacion() {
        return id_resultado_titulacion;
    }

    public void setId_resultado_titulacion(Integer id_resultado_titulacion) {
        this.id_resultado_titulacion = id_resultado_titulacion;
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

    public Integer getId_antigeno_lote() {
        return id_antigeno_lote;
    }

    public void setId_antigeno_lote(Integer id_antigeno_lote) {
        this.id_antigeno_lote = id_antigeno_lote;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
}
