package principal;

import java.util.ArrayList;

import E_S.Salida;

import comandos.GestorComandos;
import contador.RelojSimple;

import ciclista.Ciclista;
import factoresexternos.MapaCurvas;
import factoresexternos.MapaPendiente;
import factoresexternos.MapaViento;


/**
 * Clase que contiene referencias a todos los objetos "importantes" de la aplicación.
 * Una vez inicializada la clase no de pueden modificar las referencias, ya que no posee métodos "set"
 * Esta clase hace que no sea necesario pasar tantos parámetros al resto de clases que utilicen objetos comunes.
 * 
 * Emplea el patrón Singleton modificado, pues al patrón singleton no suelen pasarse atributos,
 * Lo que se hace es inicializarlo con setInstance, y luego lo recogemos con getInstance().
 *
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class Presentador {

	private static Presentador instancia = null;
	
	private boolean finaliza; //indica si la ejecución debe finalizar o no
	private int numero_iteracion; //numero de iteraciones del bucle principal
	private String log; //String que contiene el log producido durante la ejecución
	private ArrayList<Ciclista> listaciclistas;
	private MapaPendiente pendiente;
	private MapaViento viento;
	private MapaCurvas curvas;
	private GestorComandos gestorcomandos;
	private RelojSimple tiempo;
	
	
	private Presentador(boolean finaliza, int numero_iteracion, String log, ArrayList<Ciclista> listaciclistas, MapaPendiente pendiente, MapaViento viento, MapaCurvas curvas, GestorComandos gestorcomandos, RelojSimple tiempo)
	{
		this.finaliza = finaliza;
		this.numero_iteracion = numero_iteracion;
		this.log = log;
		this.listaciclistas = listaciclistas;
		this.pendiente = pendiente;
		this.viento = viento;
		this.curvas = curvas;
		this.gestorcomandos = gestorcomandos;
		this.tiempo = tiempo;
	}
	
	/**
	 * Método de inicialización e instanciación de la clase Presentador.
	 * Sólo se admite una única llamada a este método (para el buen funcionamiento de la aplicación).
	 * 
	 * Si se realiza más de una llamada a este método, produce una excepción "IllegalStateException"
	 */
	public static void setInstance(boolean finaliza, int numero_iteracion, String log, ArrayList<Ciclista> listaciclistas, MapaPendiente pendiente, MapaViento viento, MapaCurvas curvas, GestorComandos gestorcomandos, RelojSimple tiempo) {
		if (instancia == null)
			instancia = new Presentador(finaliza, numero_iteracion, log, listaciclistas, pendiente, viento, curvas, gestorcomandos, tiempo);
		else
			throw new IllegalStateException("Clase Presentador ya instanciada." + " No se pueden modificar las referencias una vez instanciada la clase.");
	}	
	
	/**
	 * Devuelve una instancia única de la clase Presentador
	 * @return Instancia de la clase
	 */
	public static Presentador getInstance()
	{
		return instancia;
	}
	
	//---------------------getters and setters--------------------------------
	
	/**
	 * @return the finaliza
	 */
	public boolean isFinaliza() {
		return finaliza;
	}
	
	/**
	 * @param finaliza the finaliza to set
	 */
	public void setFinaliza(boolean finaliza)
	{
		this.finaliza = finaliza;
	}
	
	/**
	 * @return the numero_iteracion
	 */
	public int getNumero_iteracion() {
		return numero_iteracion;
	}
	
	/**
	 * Aumenta el número de iteración en 1
	 */
	public void aumentaNumero_iteracion() {
		this.numero_iteracion++;
	}
	
	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}
	
	/**
	 * Añade una nueva línea al log
	 * @param cadena String a concatenar
	 */
	public void nuevoMensajeLog(String cadena) {
		this.log = log.concat(cadena + "\n");
		new Salida().escribirEnFichero(Globales.getInstance().getRUTA_LOG() , cadena, false); //false = sin sobreescribir
	}
	
	/**
	 * @return the listaciclistas
	 */
	public ArrayList<Ciclista> getListaciclistas()
	{
		return listaciclistas;
	}

	/**
	 * @return the pendiente
	 */
	public MapaPendiente getPendiente()
	{
		return pendiente;
	}

	/**
	 * @return the viento
	 */
	public MapaViento getViento()
	{
		return viento;
	}

	/**
	 * @return the curvas
	 */
	public MapaCurvas getCurvas()
	{
		return curvas;
	}

	/**
	 * @return the gestorcomandos
	 */
	public GestorComandos getGestorcomandos()
	{
		return gestorcomandos;
	}

	/**
	 * @return the tiempo
	 */
	public RelojSimple getTiempo() {
		return tiempo;
	}

}
