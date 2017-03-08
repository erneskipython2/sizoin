package modeloBO;

import java.util.List;

/**
 *
 * @author Erneski Coronado
 */
public class ResultadoHemoparasitoBO {
    private Integer id_resultado_hemoparasito;
    private Integer id_registro_muestra;
    private Integer id_analisis_prueba;
    private String observacion;
    private List<HemoparasitoDetalleBO> hemoparasitoDetalleBo;

    public Integer getId_resultado_hemopoarasito() {
        return id_resultado_hemoparasito;
    }

    public void setId_resultado_hemoparasito(Integer id_resultado_hemoparasito) {
        this.id_resultado_hemoparasito = id_resultado_hemoparasito;
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

    public List<HemoparasitoDetalleBO> getHemoparasitoDetalleBo() {
        return hemoparasitoDetalleBo;
    }

    public void setHemoparasitoDetalleBo(List<HemoparasitoDetalleBO> hemoparasitoDetalleBo) {
        this.hemoparasitoDetalleBo = hemoparasitoDetalleBo;
    }

}
