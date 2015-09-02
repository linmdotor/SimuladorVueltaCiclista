/**
 * File: GestorComandos.java
 * -------------------
 * Este fichero define la entrada de comandos en el sistema, y cómo se ejecutan.
 * En cada método se define el comportamiento de cada uno.
 *
 * Para ello los comandos han tenido que ser analizados previamente por el Analizador (parser) propio de cada uno
 * y han tenido que ser "redirigidos" a esta clase que los reune todos en una lista y los ejecuta (previamente configurado el contexto)
 * 
 * PARA AGREGAR UN NUEVO COMANDO --------------------------------------------------------------
 * 
 * 1º Se agrega una nueva clase CMD_nombrecomnado al paquete, que implemente la InterfaceComando
 * 2º Se desarrolla la clase (parse, contexto, ejecuta, ayuda), en función de cómo queramos que sea el comado.
 * 		Se pueden ver como ejemplo los comandos ya desarrolados.
 * 3º Se agrega en la función almacenarComandosValidos() de esta clase, dentro de un if else (mirar la función)
 * 4º Se agrega en la función Ayuda() de esta clase (mirar la función)
 * 
 * importar además la clase si fuera necesario
 * --------------------------------------------------------------------------------------------
 */
package comandos;

import interfaces.InterfaceComando;
import interfaces.InterfaceObjetoEjecutable;
import principal.Globales;
import principal.Presentador;

import java.util.*;

import E_S.*;


/**
 * La clase GestorComandos posee una lista de todos los comandos a ejeutar y contiene 
 * los métodos para ejecutar comandos e introducir comandos desde fichero o Swing.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */

public class GestorComandos  implements InterfaceObjetoEjecutable{
	
	private ArrayList <InterfaceComando> colacomandos = new ArrayList <InterfaceComando>();
		
	/**
	 * Método que introduce los comandos en el sistema.
	 * Se encarga de:
	 * Leer los comandos almacenados en un fichero.
	 * Pasarlo a la lista de comandos.
	 *
	 * Pueden generarse las Excepciones del método LeerDeFichero (ver documentación respectiva)
	 */
	public void introducirComandosFichero(String ruta)
	{
		String comandos = new Entrada().leerDeFichero(ruta);
		if (comandos != null && !comandos.isEmpty())
		{
			almacenarComandosValidos(comandos);
			new Salida().escribirEnFichero(ruta, null, true); //sobreescribe el fichero, eliminando su contenido (vaciándolo) 
		}
	}
	
	/**
	 * Método que introduce los comandos en el sistema.
	 * Se encarga de:
	 * Leer de SWING comandos.
	 * Pasarlo a la lista de comandos. 
	 */
	public void introducirComandosSwing(String comando)
	{
		if(comando != null && !comando.isEmpty())
		{
			almacenarComandosValidos(comando);	
		}
	}
	
	/**
	 * Almacena en la cola todos los comandos VÁLIDOS NO NULOS que encuentre en el String que se le pasa por parámetro
	 * @param stringcomandosanalizar String que se desea analizar y almacenar
	 */
	private void almacenarComandosValidos(String stringcomandosanalizar)
	{
		String[] secuenciadelineas = stringcomandosanalizar.split("\n");
		for (int i=0; i<secuenciadelineas.length; i++)
		{
			InterfaceComando comando;
			//se comprueba uno a uno si cada string es alguno de los comandos conocidos
			if (new CMD_Cadencia().parse(secuenciadelineas[i]) != null)
				comando = new CMD_Cadencia().parse(secuenciadelineas[i]);
			
			else if(new CMD_Frena().parse(secuenciadelineas[i]) != null)
				comando = new CMD_Frena().parse(secuenciadelineas[i]);
			
			else if(new CMD_Viento().parse(secuenciadelineas[i]) != null)
				comando = new CMD_Viento().parse(secuenciadelineas[i]);
			
			else if(new CMD_Pendiente().parse(secuenciadelineas[i]) != null && Presentador.getInstance().getNumero_iteracion() == 1)
				comando = new CMD_Pendiente().parse(secuenciadelineas[i]);
			
			else if(new CMD_Curva().parse(secuenciadelineas[i]) != null)
				comando = new CMD_Curva().parse(secuenciadelineas[i]);
			
			else if(new CMD_Pinon().parse(secuenciadelineas[i]) != null)
				comando = new CMD_Pinon().parse(secuenciadelineas[i]);
			
			else if(new CMD_Plato().parse(secuenciadelineas[i]) != null)
				comando = new CMD_Plato().parse(secuenciadelineas[i]);
			
			else if (new CMD_Help().parse(secuenciadelineas[i]) != null)
				comando = new CMD_Help().parse(secuenciadelineas[i]);
			
			else
			{
				comando = null;
				Presentador.getInstance().nuevoMensajeLog("Comando no válido en: " + Presentador.getInstance().getTiempo().getTiempo());
			}					
			
			if (comando != null) //Si el comando es CONOCIDO
			{
				colacomandos.add(comando);
				Presentador.getInstance().nuevoMensajeLog("Comando válido \"" + comando.help() + "\" en: " + Presentador.getInstance().getTiempo().getTiempo());
			}
		}
	}	
	
	/**
	 * Método de consulta de la ayuda de todos los comandos exsitentes.
	 * @return devuelve un texto con la ayuda de todos los comandos
	 */
	public String ayuda()
	{
		String ayuda = new String("Son válidos los siguientes comandos: \n");
		ayuda = ayuda.concat("\n" + new CMD_Help().help() );
		ayuda = ayuda.concat(",\n" + new CMD_Cadencia().help() );
		ayuda = ayuda.concat(",\n" + new CMD_Frena().help() );
		ayuda = ayuda.concat(",\n" + new CMD_Viento().help() );
		ayuda = ayuda.concat(",\n" + new CMD_Pendiente().help() );
		ayuda = ayuda.concat(",\n" + new CMD_Curva().help() );
		ayuda = ayuda.concat(",\n" + new CMD_Pinon().help() );
		ayuda = ayuda.concat(",\n" + new CMD_Plato().help() );
		
		return ayuda;
	}
	
	/**
	 * Método que ejecuta TODOS los comandos de la cola.
	 * Se encarga de:
	 * Ejecutar uno a uno los comandos de la cola
	 * Limpiar la lista.
	 */
	public void ejecutarComandos()
	{
		if (!colacomandos.isEmpty())
		{
			for (InterfaceComando c: colacomandos)
			{
				c.configuraContexto();
				c.ejecuta();
			}
			colacomandos.clear();
		}
	}
	
	/**
	 * Método ejecuta que introduce comandos por fichero y ejecuta los de la lista
	 */
	public void ejecuta()
	{
		introducirComandosFichero(Globales.getInstance().getRUTA_COMANDOS());
		ejecutarComandos();
	}
	
}