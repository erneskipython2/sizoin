package modeloBO;

/**
 *
 * @author Erneski Coronado
 */
public class UsuarioBO {
    private Integer id_usuario;
    private Integer id_personal;
    private Integer id_usuario_tipo;
    private String usuario;
    private String clave;
    private Integer id_sede;

    public Integer getId_sede() {
        return id_sede;
    }

    public void setId_sede(Integer id_sede) {
        this.id_sede = id_sede;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_personal() {
        return id_personal;
    }

    public void setId_personal(Integer id_personal) {
        this.id_personal = id_personal;
    }

    public Integer getId_usuario_tipo() {
        return id_usuario_tipo;
    }

    public void setId_usuario_tipo(Integer id_usuario_tipo) {
        this.id_usuario_tipo = id_usuario_tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
}
