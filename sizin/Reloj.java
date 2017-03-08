package sizin;

import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JLabel;

/**
 *
 * @author Erneski Coronado
 */
public class Reloj {
    
    public String Hora(){
        GregorianCalendar dd= new GregorianCalendar();
        int diasemana;
        String dialetras;
        String mesletras;
        int day;
        int month;
        int year;
        String fecha;
        
        diasemana=dd.get(GregorianCalendar.DAY_OF_WEEK);
        if(diasemana==1){
            dialetras="Domingo";            
        }else if(diasemana==2){
            dialetras="Lunes";
        }else if(diasemana==3){
            dialetras="Martes";
        }else if(diasemana==4){
            dialetras="Miércoles";
        }else if(diasemana==5){
            dialetras="Jueves";
        }else if(diasemana==6){
            dialetras="Viernes";
        }else{
            dialetras="Sabado";
        }
        
        day=dd.get(GregorianCalendar.DATE);
        month= dd.get(GregorianCalendar.MONTH)+1;
        if(month==1){
            mesletras="Enero";            
        }else if(month==2){
            mesletras="Febrero";
        }else if(month==3){
            mesletras="Marzo";
        }else if(month==4){
            mesletras="Abril";
        }else if(month==5){
            mesletras="Mayo";
        }else if(month==6){
            mesletras="Junio";
        }else if(month==7){
            mesletras="Julio";
        }else if(month==8){
            mesletras="Agosto";
        }
        else if(month==9){
            mesletras="Septiembre";
        }
        else if(month==10){
            mesletras="Octubre";
        }
        else if(month==11){
            mesletras="Noviembre";
        }
        else {
            mesletras="Diciembre";
        }
        
        year=dd.get(GregorianCalendar.YEAR);
        
        fecha= dialetras+", "+day+" de "+mesletras+" de "+year;

       return fecha; 
      
    }
    
    
}
