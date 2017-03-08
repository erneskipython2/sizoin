package modeloBO;

import java.sql.Date;

/**
 *
 * @author Erneski Coronado
 */
public class AntigenoLoteBO {
    private Integer antigeno_lote;
    private Integer id_antigeno;
    private String lote;
    private Date fecha_vencimiento;

    public Integer getAntigeno_lote() {
        return antigeno_lote;
    }

    public void setAntigeno_lote(Integer antigeno_lote) {
        this.antigeno_lote = antigeno_lote;
    }

    public Integer getId_antigeno() {
        return id_antigeno;
    }

    public void setId_antigeno(Integer id_antigeno) {
        this.id_antigeno = id_antigeno;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
    
    
    
}
