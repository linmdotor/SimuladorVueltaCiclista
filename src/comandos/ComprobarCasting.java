/**
 * 
 */
package comandos;

import java.text.ParseException;

import contador.RelojSimple;

/**
 * La Clase ComprobarCasting contiene los métodos que sirven para comprobar si es posible
 * realizar el csating desde String a distintas clases, controlando las posibles excepciones.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class ComprobarCasting {

	/**
	 * Intenta realizar un casting de String a Int
	 * @param cadena Cadena a convertir
	 * @return boolean devuelve TRUE, si es posible el casting, y FALSE si es imposible
	 */
	public static boolean isInt(String cadena)
	{				
		try
		{			
			Integer.parseInt(cadena);
			return true;		
		}
		catch (NumberFormatException e)
		{			
			return false;		
		}		
	}
	
	/**
	 * Intenta realizar un casting de String a Double
	 * @param cadena Cadena a convertir
	 * @return boolean devuelve TRUE, si es posible el casting, y FALSE si es imposible
	 */
	public static boolean isDouble(String cadena)
	{		
		try
		{
			Double.parseDouble(cadena);
			return true;		
		} 
		catch (NumberFormatException e)
		{			
			return false;		
		}		
	}

	/**
	 * Intenta realizar un casting de String a RelojSimple
	 * @param cadena Cadena a convertir
	 * @return boolean devuelve TRUE, si es posible el casting, y FALSE si es imposible
	 */
	public static boolean isTiempo(String cadena)
	{
		try
		{
			RelojSimple.parseRelojSimple(cadena);
			return true;		
		} 
		catch (ParseException e)
		{			
			return false;		
		}
	}

}
