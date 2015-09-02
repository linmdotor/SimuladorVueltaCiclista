package fecha;

import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;

/**
 * La Clase Fecha se emplea para tener un acceso fácil a la fecha del sistema, 
 * imprimiendo la misma con el formato que se desee.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class Fecha {

	Calendar calendario = new GregorianCalendar();
	
	
	/**
	 * Devuelve un String con la hora actual con el formato: "01:12:51"
	 * @return
	 */
	@SuppressWarnings("resource")
	public String getHoraActual()
	{
		String hora, minutos, segundos;
		String tiempo_sis;
		
		hora = new Formatter().format("%02d", calendario.get(Calendar.HOUR_OF_DAY)).toString();
		minutos = new Formatter().format("%02d", calendario.get(Calendar.MINUTE)).toString();
		segundos = new Formatter().format("%02d", calendario.get(Calendar.SECOND)).toString();
		
		tiempo_sis = hora + ":" + minutos + ":" + segundos;
		
		return tiempo_sis;
	}

}
