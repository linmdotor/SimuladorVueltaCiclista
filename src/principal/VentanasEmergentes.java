/**
 * 
 */
package principal;

import javax.swing.JOptionPane;

/**
 * Clase que contiene métodos para mostrar ventanas emergentes en la aplicación.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class VentanasEmergentes {

	
	/**
	 * Obtiene un valor entero entre 2 límites, máximo y mínimo, incluidos ambos
	 * 
	 * @param nombredato Nombre del dato que se está intentando obtener (saldrá en el mensaje emergente)
	 * @param min mínimo número admitido
	 * @param max máximo número admitido
	 * @return Número válido introducido
	 */
	public static int getInt(String nombredato, int min, int max)
	{
		int numero = 0;
		
		boolean formatocorrecto = false;
		
		while(!formatocorrecto)
		{	
			String numero_string = JOptionPane.showInputDialog("Ingrese un numero correcto para " + nombredato + ", \n entre " + min + " y " + max + ": ");
			
			try //se intenta convertir a Int
	        {
				numero = Integer.parseInt(numero_string);
				
	            if(numero >= min && numero <= max) //se comprueba que está entre los límites
	            {
	            	formatocorrecto = true;
	            }
	            else
	            {
	            	formatocorrecto = false;
	            }            	
	        }
	        catch ( NumberFormatException e )
	        {
	        	formatocorrecto = false;
	        }				
		}
		
		return numero;
	}

	/**
	 * Obtiene un valor entero entre el máximo y mímino valores de Int, incluidos ambos
	 * Sobrecarga, haciendo uso de el método getInt(String nombredato, int min, int max)
	 * 
	 * @param nombredato Nombre del dato que se está intentando obtener (saldrá en el mensaje emergente)
	 * @return Número válido introducido
	 */
	public static int getInt(String nombredato)
	{
		return getInt(nombredato, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * Muestra un mensaje al inicio de la aplicación
	 */
	public static void inicio()
	{
		JOptionPane.showMessageDialog(null, "Para modificar los factores externos, Puede introducir comandos antes de comenzar.\n" +
				"Cuando este preparado pulse \"Start\".");
	}
	
	/**
	 * Imprime un mensaje para cuando un ciclista llega a meta
	 * @param nombreciclista Nombre del ciclista
	 * @param numerociclista Número del ciclista
	 */
	public static void ciclistaGanador(String nombreciclista, int numerociclista)
	{
		JOptionPane.showMessageDialog(null, "El ciclista número " + Integer.toString(numerociclista) + ", " + nombreciclista + ", ha llegado a la META." );
	}
}
