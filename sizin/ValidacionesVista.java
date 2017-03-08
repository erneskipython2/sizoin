package sizin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que contine validaciones de vista para email, rif, cedula y fecha
 *
 * @author Erneski Coronado
 */
public class ValidacionesVista {

    /**
     *
     */
    public ValidacionesVista() {

    }

    /**
     * Metodo para validar un correo electronico
     *
     * @param correo String que recibe el correo a evaluar
     * @return true si el correo es válido, false si es inválido
     */
    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            //System.out.println("[" + mat.group() + "]");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para validar una cedula en formato V0000000
     *
     * @param ced String que recibe la cedula a evaluar
     * @return true si la cedula es válida, false si es inválida
     */
    public boolean isCed(String ced) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([VE]([0-9]){0,})$");
        mat = pat.matcher(ced);
        if (mat.find()) {
            //System.out.println("[" + mat.group() + "]");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para validar una fecha en formato yyyy-mm-dd
     *
     * @param fecha String que recibe la fecha a evaluar
     * @return true si la fecha es válida, false si es inválida
     */
    public boolean isFecha(String fecha) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^(19|20)\\d\\d[-.](0[1-9]|1[012])[-.](0[1-9]|[12][0-9]|3[01])$");
        mat = pat.matcher(fecha);
        if (mat.find()) {
            //System.out.println("[" + mat.group() + "]");
            return true;
        } else {
            return false;
        }
    }
}
