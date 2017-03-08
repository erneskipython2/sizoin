package sizin;
import modeloBO.UsuarioBO;

/**
 * Consultas de vista
 * @author ERNESKI CORONADO
 */
public class ConfiguracionGlobal {
    
 
    public static Integer sede_seleccionada;//elige la sede de insai predefida para filtrar dato
    public static Integer id_usuario;

    public  Integer getId_usuario() {
        return id_usuario;
    }

    public  void setId_usuario(Integer id_usuario) {
        ConfiguracionGlobal.id_usuario = id_usuario;
    }
    public static Integer privilegio;
    public static String usuario;
    public String box_consulta_tipo_usuario="SELECT id_usuario_tipo, usuario_tipo FROM usuario_tipo";//consulta de tipo de usuario
    public String box_consulta_sede="SELECT id_sedes_insai, sede FROM sedes_insai";
    public String box_consulta_estado="SELECT * FROM estado ORDER BY estado";
    public String box_consulta_reino="SELECT * FROM reino";
    public String box_consulta_analisis_tipo="SELECT * FROM analisis_tipo ORDER BY analisis_tipo";
    public String box_consulta_muestra="SELECT * FROM muestra_tipo ORDER BY muestra_tipo";
    public String box_consulta_grupo="SELECT * FROM taxonomia_rapida";
    public String box_tipo_prueba="Select * FROM analisis_prueba where id_analisis_tipo=2 ORDER BY analisis_prueba";
    public String box_lote="Select id_antigeno_lote, concat_ws(' ',an.antigeno,lot.lote,lot.fecha_vencimiento) as lote FROM antigeno_lote lot inner join antigeno an on lot.id_antigeno=an.id_antigeno";    public String tabla_consulta_propietario="SELECT * FROM propietario ORDER BY nombre";
    public String box_buscar_todo="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado";
    public String lista_taxon_inicial="SELECT Distinct(tax.tsn), tax.nombre_completo, tax.rango_id, ran.rango FROM taxonomia tax LEFT JOIN rango_taxonomico ran ON tax.rango_id=ran.rango_id WHERE tax.rango_id=10";
    public String lista_finalidad="SELECT * FROM finalidad";
    public String tabla_cuarentenario="select cua.id_tsn,tax.nombre_completo, cua.nombre_comun, cua.descripcion from organismo_cuarentenario cua join taxonomia tax on cua.id_tsn=tax.tsn";
    public String tabla_consulta_sede="select \n" +
"    sed.id_sedes_insai,\n" +
"    sed.sede,\n" +
"    sed.num_sede,\n" +
"    sed.telefono,\n" +
"    sed.email,\n" +
"    sed.direccion,\n" +
"    par.parroquia,\n" +
"    mun.municipio,\n" +
"    est.estado,\n" +
"    concat_ws(' ',per.cedula,per.nombre,per.apellido) as cedula\n"+
"from\n" +
"    sedes_insai sed\n" +
"        inner join\n" +
"    parroquia par ON par.id_parroquia = sed.parroquia_id\n" +
"        inner join\n" +
"    municipio mun ON par.id_municipio = mun.id_municipio\n" +
"        inner join\n" +
"    estado est ON mun.id_estado = est.id_estado inner join personal per on sed.id_responsable=per.id_personal";
    
    public Integer getSede_seleccionada() {
        return sede_seleccionada;
    }

    public void setSede_seleccionada(Integer sede_seleccionada) {
        this.sede_seleccionada = sede_seleccionada;
    }
    public Integer getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(Integer privilegio) {
        this.privilegio = privilegio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    

    
   public String box_consulta_municipio(int id_estado){
      String box_consulta="SELECT * FROM municipio WHERE id_estado='"+id_estado+"' ORDER BY municipio";
      return box_consulta;
     
   }
   
   public String box_consulta_parroquia(int id_municipio){
      String box_consulta="SELECT * FROM parroquia WHERE id_municipio='"+id_municipio+"' ORDER BY parroquia";
      return box_consulta;     
   }
   
   public String tabla_consulta_incidencias(String estado){
      String box_consulta="SELECT `consulta_epidemiologica`.`id_epidemiologia`,\n" +
"    `consulta_epidemiologica`.`organismo_cuarentenario`,\n" +
"    `consulta_epidemiologica`.`nombre_comun`,\n" +
"    `consulta_epidemiologica`.`descripcion`,\n" +
"	`consulta_epidemiologica`.`protocolo`,\n" +
"    `consulta_epidemiologica`.`fecha_alerta`,\n" +
"    `consulta_epidemiologica`.`animal_infectado`,\n" +
"    `consulta_epidemiologica`.`predio_origen`,\n" +
"    `consulta_epidemiologica`.`finalidad`,\n" +
"    `consulta_epidemiologica`.`estado`,\n" +
"    `consulta_epidemiologica`.`municipio`,\n" +
"    `consulta_epidemiologica`.`parroquia`,\n" +
"    `consulta_epidemiologica`.`direccion`,\n" +
"    `consulta_epidemiologica`.`utm_este`,\n" +
"    `consulta_epidemiologica`.`utm_norte`,\n" +
"    `consulta_epidemiologica`.`superficie`,    \n" +
"	`consulta_epidemiologica`.`propietario`,\n" +
"    `consulta_epidemiologica`.`rif`,\n" +
"    `consulta_epidemiologica`.`telefono`\n" +
"FROM `sizoin`.`consulta_epidemiologica` WHERE estado='"+estado+"' ORDER BY fecha_alerta";
      return box_consulta;     
   }
   
      /*public String box_consulta_sector(int id_parroquia){
      String box_consulta="SELECT * FROM sector WHERE id_parroquia='"+id_parroquia+"' ORDER BY sector";
      return box_consulta;     
   }*/
   
      public String box_consulta_personal_cedula(){
      String box_consulta="SELECT id_personal, concat_ws(' ',cedula,nombre,apellido) as cedula FROM personal WHERE id_sede ='"+sede_seleccionada+"' ";
      return box_consulta;     
   }
      
      public String box_consulta_analisis_prueba(int tipo){
      String box_consulta="SELECT * FROM analisis_prueba WHERE id_analisis_tipo ='"+tipo+"' ORDER BY analisis_prueba";
      return box_consulta;     
   }
      
      public String tabla_consulta_usuarios(){
      String box_consulta="SELECT \n" +
"    us.id_usuario,\n" +
"    us.usuario,\n" +
"    us.clave,\n" +
"    tip.usuario_tipo,\n" +
"    per.nombre,\n" +
"    concat_ws(' ',per.cedula,per.nombre,per.apellido) as cedula\n" +
"FROM\n" +
"    usuario us\n" +
"        INNER JOIN\n" +
"    usuario_tipo tip ON us.id_usuario_tipo = tip.id_usuario_tipo\n" +
"        INNER JOIN\n" +
"    personal per ON us.id_personal = per.id_personal\n" +
"WHERE per.id_sede='"+this.getSede_seleccionada()+"' ";
      return box_consulta;     
   }
      
      public String tabla_consulta_personal(){
      String box_consulta="SELECT \n" +
"    per.id_personal,\n" +
"    per.cedula,\n" +
"    per.nombre,\n" +
"    per.apellido,\n" +
"    per.telefono,\n" +
"    sed.sede,\n" +
"    per.num_acreditacion,\n" +
"    per.carnet_mvc\n" +
"FROM\n" +
"    personal per\n" +
"        INNER JOIN\n" +
"    sedes_insai sed ON per.id_sede = sed.id_sedes_insai\n" +
"WHERE\n" +
"    per.id_sede ='"+this.getSede_seleccionada()+"' ";
      return box_consulta;     
   }
      
      public String tabla_consulta_propietario_buscar(String rif){
      String box_consulta="SELECT * FROM propietario WHERE rif='"+rif+"' ";
      return box_consulta;     
   }
      
      public String tabla_consulta_predios(int id_propietario){
      String box_consulta="Select * from predio_completo WHERE id_propietario="+id_propietario;
      return box_consulta;     
   }
      
      public String usuario_login(){
      String box_consulta="Bienvenido Sr(a) :"+usuario+" ";
      return box_consulta;     
   }
      
      public String taxonomia(int id_tsn){
          String box_consulta="SELECT Distinct(tax.tsn), tax.nombre_completo, tax.rango_id, ran.rango FROM taxonomia tax LEFT JOIN rango_taxonomico ran ON tax.rango_id=ran.rango_id WHERE tax.padre_tsn="+id_tsn;
          return box_consulta;
      }
      
       public String taxonomia_rapida(int id_tsn){
          String box_consulta="SELECT Distinct(tax.tsn), tax.nombre_completo, tax.rango_id, ran.rango FROM taxonomia tax LEFT JOIN rango_taxonomico ran ON tax.rango_id=ran.rango_id WHERE tsn="+id_tsn;
          return box_consulta;
      }
       
      public String lista_consulta_personal_cedula(){
      String box_consulta="SELECT id_personal, concat_ws(' ',cedula,nombre,apellido) as cedula,  num_acreditacion FROM personal WHERE id_sede ='"+sede_seleccionada+"' ";
      return box_consulta;     
   }
      
      public String box_buscar_direccion_p(int parroquiabus, int tipoanalisisbuscar){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE pre.id_parroquia=" + parroquiabus + " AND reg.id_analisis_tipo= '" + tipoanalisisbuscar + "'";
      return box_consulta;    
   }
      
      public String box_buscar_direccion_p(int parroquiabus){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE pre.id_parroquia=" + parroquiabus;
      return box_consulta;   
   }
      
      public String box_buscar_direccion_m(int municipiobus, int tipoanalisisbuscar){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE par.id_municipio=" + municipiobus + " AND reg.id_analisis_tipo= '" + tipoanalisisbuscar + "'";
      return box_consulta;    
   }
      
      public String box_buscar_direccion_m(int municipiobus){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE par.id_municipio=" + municipiobus;
      return box_consulta;    
   }
      
     public String box_buscar_direccion_e(int estadobus, int tipoanalisisbuscar){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE mun.id_estado=" + estadobus + " AND reg.id_analisis_tipo= '" + tipoanalisisbuscar + "'";
      return box_consulta;    
   }
     
     public String box_buscar_direccion_e(int estadobus){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE mun.id_estado=" + estadobus;
      return box_consulta;    
   }
     
    public String box_buscar_fecha(String fecha_inicial,String fecha_final, int tipoanalisisbuscar){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE reg.fecha_recepcion BETWEEN  '" + fecha_inicial + "' AND '" + fecha_final + "' AND reg.id_analisis_tipo= '" + tipoanalisisbuscar + "'";
      return box_consulta;    
   }
    
    public String box_buscar_fecha(String fecha_inicial,String fecha_final){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE reg.fecha_recepcion BETWEEN  '" + fecha_inicial + "' AND '" + fecha_final;
      return box_consulta;    
   }
    
   public String box_buscar_todo(int tipoanalisisbuscar){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE reg.id_analisis_tipo= '" + tipoanalisisbuscar + "'";
      return box_consulta;    
   }
   
   public String box_buscar_remitente(String remitente, int tipoanalisisbuscar){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE reg.remitente like  '%"+ remitente + "%' AND reg.id_analisis_tipo= '" + tipoanalisisbuscar + "'";
      return box_consulta;    
   }

   public String box_buscar_remitente(String remitente){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE reg.remitente like  '%"+ remitente+ "%'";
      return box_consulta;    
   }
    
   public String box_buscar_predio(String predio, int tipoanalisisbuscar){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE pre.predio like  '%" + predio + "%' AND reg.id_analisis_tipo= '" + tipoanalisisbuscar + "'";
      return box_consulta;    
   }
   
   public String box_buscar_predio(String predio){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE pre.predio like  '%" + predio + "%'";
      return box_consulta;    
   }

   public String box_buscar_productor(String productor, int tipoanalisisbuscar){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE pro.rif = '"+ productor + "'AND reg.id_analisis_tipo= '" + tipoanalisisbuscar + "'";
      return box_consulta;    
   }
  
   public String box_buscar_productor(String productor){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local,reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado WHERE pro.rif = '"+ productor+ "'";
      return box_consulta;    
   }
   
   public String box_buscar_protocolo(String protocolo){
      String box_consulta="Select reg.protocolo,reg.fecha_recepcion,tip.analisis_tipo,pro.nombre,pro.apellido,pre.predio,reg.remitente,est.estado,mun.municipio,par.parroquia,pre.direccion_local, reg.id_registro_general FROM registro_general reg INNER JOIN analisis_tipo tip ON tip.id_analisis_tipo = reg.id_analisis_tipo INNER JOIN predio pre ON pre.id_predio = reg.id_predio INNER JOIN propietario pro ON pro.id_propietario = pre.id_propietario  INNER JOIN  parroquia par ON par.id_parroquia = pre.id_parroquia INNER JOIN  municipio mun ON mun.id_municipio = par.id_municipio INNER JOIN estado est ON est.id_estado = mun.id_estado  WHERE reg.protocolo = '"+ protocolo + "'";
      return box_consulta;    
   }

   public String box_tipo_prueba(String tipo){
      String box_consulta="select * from analisis_prueba where id_analisis_tipo= (select id_analisis_tipo from analisis_tipo where analisis_tipo ='"+tipo+"')";
      return box_consulta;    
   }
}
