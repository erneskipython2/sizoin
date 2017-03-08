package modeloBO;

import java.sql.Date;

/**
 *
 * @author Erneski Coronado
 */
public class VacunacionCicloBO {
    private Integer id_vacunacion_ciclo;
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Integer id_Sedes_insai;

    public Integer getId_vacunacion_ciclo() {
        return id_vacunacion_ciclo;
    }

    public void setId_vacunacion_ciclo(Integer id_vacunacion_ciclo) {
        this.id_vacunacion_ciclo = id_vacunacion_ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Integer getId_Sedes_insai() {
        return id_Sedes_insai;
    }

    public void setId_Sedes_insai(Integer id_Sedes_insai) {
        this.id_Sedes_insai = id_Sedes_insai;
    }
    
    
}
