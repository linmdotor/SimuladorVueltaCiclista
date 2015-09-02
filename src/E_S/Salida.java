/**
 * File: Salida.java
 * -------------------
 * Este fichero define la clase de salida de datos de nuestro programa.
 */
package E_S;

import java.io.*;

/**
 * La clase Salida contiene los métodos para la salida de datos;
 * Esta salida se produce mediante escritura en fichero.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class Salida {

	
	/**
	 * Escribe en un fichero de TEXTO el contenido de un String.
	 * Tiene la posibilidad de reemplazar el fichero original (si existiera) o agregar el String al final del mismo.
	 * 
	 * La ruta de fichero que se le pase tiene que ser una ruta relativa respecto al
	 * directorio del proyecto. Se debe escribir de la siguiente manera:
	 * Windows: "\\carpeta\\carpeta\\fichero.extension"
	 * Unix: "/carpeta/carpeta/fichero.extension"
	 * (en función de la versión, es posible que la ruta en Windows se deba escribir como en Unix)
	 * 
	 * Se puede producir Exception por 2 motivos:
	 * - No se ha podido realizar la escritura del fichero.
	 * - No se ha podido cerrar el fichero tras la escritura.
	 * @param rutafichero Ruta (relativa o absoluta) del fichero
	 * @param contenido String con el contenido del fichero
	 * @param eliminarcontenidooriginal Veradero (True) si se quiere reemplazar el fichero en caso de que exista.
	 * False en caso de que se quiera agregar el contenido al final del fichero.
	 * @return True en caso de que se haya realizado la operación con éxito, False en caso contrario
	 */
	public boolean escribirEnFichero (String rutafichero, String contenido, boolean eliminarcontenidooriginal)
	{
		boolean resultado = true;

		String directorioactual = System.getProperty("user.dir");
		File ficherorutacompleta = new File(directorioactual + rutafichero);
	    FileWriter fichero = null;
        BufferedWriter writer = null;        
	    
	    try
	    {
	    	if (!ficherorutacompleta.exists())
	    	{
	    		ficherorutacompleta.getParentFile().mkdirs(); // esto crea la carpeta donde se aloja el fichero, 
	    		//independientemente que exista el path completo, si no existe crea toda la ruta necesaria
	    	}
	    	fichero = new FileWriter(ficherorutacompleta, !eliminarcontenidooriginal);
	    	writer = new BufferedWriter(fichero);
	        writer.write(contenido, 0, contenido.length());
	        writer.newLine();
	    } 
	    catch (Exception e) 
	    {
	    	resultado = false;
	    	System.out.println("ERROR CONTROLADO al Escribir en Fichero en: ");
	    	e.printStackTrace(); 
	    } 
	    finally 
	    {
	    	cerrarFicheroEscritura(fichero, writer);
	    }
	    return resultado;
	}
	
	/**
	 * Cierra el fichero y el BufferWriter
	 * @param fichero
	 * @param writer
	 */
	private void cerrarFicheroEscritura (FileWriter fichero, BufferedWriter writer)
	{
		try 
       {
	       if (fichero != null)
	       {
	    	   writer.close();
	           fichero.close();
	       }		       
       } 
       catch (Exception e2) 
       {
    	  System.out.println("ERROR CONTROLADO al Cerrar Fichero de Escritura en: ");
          e2.printStackTrace();
       }
	}
} 