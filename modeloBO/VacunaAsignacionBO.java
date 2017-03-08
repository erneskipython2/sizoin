
package modeloBO;

import java.sql.Date;

/**
 *
 * @author Erneski Coronado
 */
public class VacunaAsignacionBO {
    private Integer id_vacuna_asignacion;
    private Integer id_vacuna_registro;
    private Integer id_vacunador;
    private Integer id_personal;
    private Integer id_vacunacion_ciclo;
    private Integer dosis_entregadas;
    private Date fecha_entrega;
    private Integer dosis_devueltas;
    private Date fecha_devolucion;

    public Integer getId_vacuna_asignacion() {
        return id_vacuna_asignacion;
    }

    public void setId_vacuna_asignacion(Integer id_vacuna_asignacion) {
        this.id_vacuna_asignacion = id_vacuna_asignacion;
    }

    public Integer getId_vacuna_registro() {
        return id_vacuna_registro;
    }

    public void setId_vacuna_registro(Integer id_vacuna_registro) {
        this.id_vacuna_registro = id_vacuna_registro;
    }

    public Integer getId_vacunador() {
        return id_vacunador;
    }

    public void setId_vacunador(Integer id_vacunador) {
        this.id_vacunador = id_vacunador;
    }

    public Integer getId_personal() {
        return id_personal;
    }

    public void setId_personal(Integer id_personal) {
        this.id_personal = id_personal;
    }

    public Integer getId_vacunacion_ciclo() {
        return id_vacunacion_ciclo;
    }

    public void setId_vacunacion_ciclo(Integer id_vacunacion_ciclo) {
        this.id_vacunacion_ciclo = id_vacunacion_ciclo;
    }

    public Integer getDosis_entregadas() {
        return dosis_entregadas;
    }

    public void setDosis_entregadas(Integer dosis_entregadas) {
        this.dosis_entregadas = dosis_entregadas;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Integer getDosis_devueltas() {
        return dosis_devueltas;
    }

    public void setDosis_devueltas(Integer dosis_devueltas) {
        this.dosis_devueltas = dosis_devueltas;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }
    
    
}
