
package modeloBO;

/**
 *
 * @author Erneski Coronado
 */
public class ResultadoBrucelosisBO {
    private Integer id_resultado_brucelosis;
    private Integer id_registro_muestra;
    private Integer id_analisis_prueba;
    private Integer id_antigeno_lote;
    private Byte resultado;

    public Integer getId_resultado_brucelosis() {
        return id_resultado_brucelosis;
    }

    public void setId_resultado_brucelosis(Integer id_resultado_brucelosis) {
        this.id_resultado_brucelosis = id_resultado_brucelosis;
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

    public Byte getResultado() {
        return resultado;
    }

    public void setResultado(Byte resultado) {
        this.resultado = resultado;
    }
    
    
}
