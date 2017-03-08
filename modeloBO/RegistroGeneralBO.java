package modeloBO;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Erneski Coronado
 */
public class RegistroGeneralBO {
    private Integer id_registro_general;
    private Integer id_sede;
    private Integer id_analisis_tipo;
    private String analisis_tipo;//solo para la carga de datos para modificar
    private Integer id_propietario;
    private String propietario;//solo para la carga de datos para modificar
    private Integer id_predio;
    private String predio; //solo para la carga de datos para modificar
    private String remitente;
    private Date fecha_recepcion;
    private Date fecha_procesado;
    private Date fecha_entregado;
    private String protocolo;
    private ArrayList muestras=new ArrayList();

    public Date getFecha_procesado() {
        return fecha_procesado;
    }

    public void setFecha_procesado(Date fecha_procesado) {
        this.fecha_procesado = fecha_procesado;
    }

    public Date getFecha_entregado() {
        return fecha_entregado;
    }

    public void setFecha_entregado(Date fecha_entregado) {
        this.fecha_entregado = fecha_entregado;
    }
    
    

    public ArrayList getMuestras() {
        return muestras;
    }

    public void setMuestras(ArrayList muestras) {
        this.muestras = muestras;
    }

    public String getAnalisis_tipo() {
        return analisis_tipo;
    }

    public void setAnalisis_tipo(String analisis_tipo) {
        this.analisis_tipo = analisis_tipo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }
    

    public Integer getId_registro_general() {
        return id_registro_general;
    }

    public void setId_registro_general(Integer id_registro_general) {
        this.id_registro_general = id_registro_general;
    }

    public Integer getId_sede() {
        return id_sede;
    }

    public void setId_sede(Integer id_sede) {
        this.id_sede = id_sede;
    }

    public Integer getId_analisis_tipo() {
        return id_analisis_tipo;
    }

    public void setId_analisis_tipo(Integer id_analisis_tipo) {
        this.id_analisis_tipo = id_analisis_tipo;
    }

    public Integer getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(Integer id_propietario) {
        this.id_propietario = id_propietario;
    }

    public Integer getId_predio() {
        return id_predio;
    }

    public void setId_predio(Integer id_predio) {
        this.id_predio = id_predio;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
    
    
}
