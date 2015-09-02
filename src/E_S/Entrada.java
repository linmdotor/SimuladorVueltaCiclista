/**
 * File: Entrada.java
 * -------------------
 * Este fichero define la clase de entrada de datos de nuestro programa.
 */
package E_S;

import java.io.*;
import java.util.Scanner;

/**
 * La clase Entrada contiene los métodos para la entrada de datos;
 * tanto entrada de texto por consola o lectura de ficheros.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class Entrada {
	
	
	/*
	 * MÁS INFO:
	 * Para la lectura, otra opción con el imput Stream
	 * // Se podría hacer más genérico con public String leerDeTeclado(InputStream entradateclado) y Scanner sc = new Scanner(entradateclado);
	 * y así valdrá también para Sockets
	 * http://stackoverflow.com/questions/4886293/socket-input-stream-hangs-on-final-read-best-way-to-handle-this
	 */	
	/**
	 * Lee palabras que se introducen por el teclado
	 * @return cadena Devuelve un String que contiene la información leida por el teclado
	 */
	public String leerDeTeclado()
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String cadena = scanner.nextLine();
		
		return cadena;
	}

	/**
	 * Lee un fichero de TEXTO y devuelve un String correspondiente a TODo su contenido
	 * Devuelve null si se produjo una excepción en la lectura
	 * @param rutafichero Ruta (relativa o absoluta) del fichero
	 * @return String con el contenido del fichero
	 */
	public String leerDeFichero (String rutafichero)
	{
		String directorioactual = System.getProperty("user.dir");
		File ficherorutacompleta = new File(directorioactual + rutafichero);
	    FileReader fichero = null;
        BufferedReader reader = null;
        
        StringBuffer texto = new StringBuffer();
        String linea;
        
    	String cadenafinal = null;
        
	    try
	    {
	        fichero = new FileReader(ficherorutacompleta);
	    	reader = new BufferedReader(fichero);
	    	
	    	// Lectura del fichero
	        while((linea = reader.readLine())!=null)
	        	texto.append(linea + "\n");
	    } 
	    catch (Exception e) 
	    {
	    	texto = null;
	    	System.out.println("ERROR CONTROLADO al Leer de Fichero en : ");
	    	e.printStackTrace();	        
	    } 
	    finally 
	    {
	    	cerrarFicheroLectura (fichero, reader);
	    	if (texto != null)
	    		cadenafinal = texto.toString();  	
	    }	  
	    return cadenafinal;
	}
	
	
	/**
	 * Cierra el fichero y el BufferReader
	 * @param fichero
	 * @param reader
	 */
	private void cerrarFicheroLectura (FileReader fichero, BufferedReader reader)
	{
		try 
	    {
	       if (fichero != null)
	       {
	    	   reader.close();
	           fichero.close();
	       }		       
	    } 
	    catch (Exception e2) 
	    {
	    	System.out.println("ERROR CONTROLADO al Cerrar Fichero de Lectura en : ");
	       e2.printStackTrace();
	    }
	}
} 