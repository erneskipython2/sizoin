/*
 * @author Erneski Coronado
 */


package sizin;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.*;

//clase para limitar la cantidad de caracteres de un JtextField
//extiende de PLainDocument
//En postdeclaration code de las propiedades de un Jtextfield. Ejemplo: textField.setDocument(new JTextFieldLimit(15));
 
public class JTextFieldLimit extends PlainDocument {
private int limit;
// optional uppercase conversion
private boolean toUppercase = false;
 
//Pasamos el limite de caracteres al constructor
JTextFieldLimit(int limit) {
super();
this.limit = limit;
}
 
public JTextFieldLimit(int limit, boolean upper) {
super();
this.limit = limit;
toUppercase = upper;
}


 
@Override
public void insertString
(int offset, String  str, AttributeSet attr)
throws BadLocationException {
if (str == null) {
        return;
    }
 
if ((getLength() + str.length()) <= limit) {
if (toUppercase) {
        str = str.toUpperCase();
    }
super.insertString(offset, str, attr);
}
}
}
