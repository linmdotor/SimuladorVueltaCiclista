/**
 * File: Ciclista.java
 * -------------------
 * Este fichero define la clase ciclista de nuestro programa.
 */
package ciclista;

import principal.Globales;
import principal.Presentador;
import principal.VentanasEmergentes;
import interfaces.InterfaceObjetoEjecutable;
import interfaces.InterfaceObjetoMostrable;
import bicicleta.Bicicleta;
import contador.RelojSimple;
import E_S.*;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */

/*
 * PONER EN LA DOC http://es.wikipedia.org/wiki/Movimiento_rectil%C3%ADneo_uniformemente_acelerado
 */
public class Ciclista extends Persona implements InterfaceObjetoEjecutable, InterfaceObjetoMostrable
{

	//Variables privadas de la clase
	private Bicicleta bici; //bicicleta del ciclista
	private RelojSimple reloj; //reloj contador del tiempo del ciclista
	private double fuerzamaxima; //fuerza maxima que puede ejercer el ciclista
	private double fuerzaactual; //fuerza que le queda al ciclista
	private int cadencia; //frecuencia de pedaleo actual (RPM)
	private double tiempopedalada; //tiempo tarda en dar una pedalada (segundos)
	private int numerociclista; //numero de dorsal del ciclista
	private boolean vivo; //variable que indica si el ciclista se ejecuta o no
	private int cantidadfrenada; //Indica la proporción total de frenada (1/n). siendo la cantidad 0 (frenado en seco), y 1/n la reduccion de velocidad conseguida.
	private int numeroiteracionesfrenada; //Indica el número de iteraciones del buce que tiene que continuar frenando el ciclista
	private double cantidadfrenadaporiteracion; //cantidad que tiene que frenar a cada vuelta del bucle
	private int cadenciaantesdefrenar; //cadencia que llevaba el ciclista antes de frenar
	private double tiempopedaladaantesdefrenar; //tiempodepedalada que llevaba el ciclista antes de frenar
	
	private Globales globales = Globales.getInstance();
	
	/**
	 * Crea un nuevo ciclista por defecto (con una bici por defecto)
	 * @param numerociclista Numero del ciclista (conviene no repetir numeros)
	 */
	public Ciclista(int numerociclista)
	{
		this(numerociclista, new Bicicleta());
	}

	/**
	 * Crea un nuevo Ciclista con una bici determinada por parámetro
	 * @param numerociclista Numero del ciclista (conviene no repetir numeros)
	 * @param bicicleta La Bicicleta asignada al Ciclista
	 */
	public Ciclista (int numerociclista, Bicicleta bicicleta)
	{
		this(numerociclista, 65, Globales.getInstance().getMAX_FUERZA_CICLISTA(), bicicleta);
	}

	/**
	 * Crea un nuevo Ciclista con los valores pasados como parámetros y una bici por defecto
	 * @param numerociclista Numero del ciclista (conviene no repetir numeros)
	 * @param masa Masa del ciclista (en kilogramos)
	 * @param fuerza Fuerza máxima del ciclista
	 */
	public Ciclista (int numerociclista, double masa, double fuerza)
	{
		this(numerociclista, masa, fuerza, new Bicicleta());
	}

	/**
	 * Crea un nuevo Ciclista con los valores pasados como parámetros
	 * @param numerociclista Numero del ciclista (conviene no repetir numeros)
	 * @param masa Masa del ciclista (en kilogramos)
	 * @param fuerza Fuerza máxima del ciclista
	 * @param bicicleta Bicicleta del ciclista
	 */
	public Ciclista (int numerociclista, double masa, double fuerza, Bicicleta bicicleta)
	{
		this.setNumeroCiclista(numerociclista);
		setNombre("Indurain");
		setMasa(masa);
		setFuerzamaxima(fuerza);
		fuerzaactual = fuerza;
		bici = bicicleta;
		reloj = new RelojSimple();
		vivo = true;
		numeroiteracionesfrenada = -1;
		cantidadfrenadaporiteracion = 0;
	}


	/**
	 * @return Devuelve la Fuerza máxima que puede hacer el ciclista
	 */
	public double getFuerzamaxima() {
		return fuerzamaxima;
	}

	/**
	 * @param fuerza Asigna una fuerza máxima al Ciclista
	 */
	public void setFuerzamaxima(double fuerza) {
		fuerzamaxima = fuerza;
	}

	/**
	 * @return the fuerzaactual
	 */
	public double getFuerzaactual() {
		return fuerzaactual;
	}

	/**
	 * @param fuerza the fuerzaactual to set
	 */
	public void setFuerzaactual(double fuerza) {
		if (fuerza >= 0 && fuerza <= fuerzamaxima)
			fuerzaactual = fuerza;
		else if (fuerza < 0)
			fuerzaactual = 0;
		else
			fuerzaactual = fuerzamaxima;
	}
	
	/**
	 * @return Devuelve la cadencia que lleva el ciclista (RPM)
	 */
	public int getCadencia() {
		return cadencia;
	}

	/**
	 * Asigna una cadencia al cilista, si se le pasa una cadencia negativa, la cadenciaactual se actualizará a 0, o si se le pasa una cadencia mayor que la que determina la global.
	 * Además actualiza el valor del TiempoPedalada
	 * @param cadencia Asigna una cadencia al ciclista (RPM)
	 */
	public void setCadencia(int cadencia) {
		
		if (cadencia <= 0)
			this.cadencia = 0;
		else if (cadencia > globales.getCADENCIA_MAX())
			this.cadencia = globales.getCADENCIA_MAX();
		else
			this.cadencia = cadencia;
		
		 //se actualiza el tiempo de pedalada, ya que la cadencia determina el tiempo de pedalada, y no al reves
		setTiempoPedalada(getPeriodoCadencia());
	}
	
	/**
	 * @return Devuelve el periodo de la cadencia que lleva el ciclista (segundos)
	 */
	public double getPeriodoCadencia() {
		if (cadencia > 0)
			return (double)globales.getMINUTOS_A_SEGUNDOS()/(double)cadencia;
		else
			return 0;
	}
	
	/**
	 * @return the tiempopedalada
	 */
	public double getTiempoPedalada() {
		return tiempopedalada;
	}

	/**
	 * @param tiempopedalada the tiempopedalada to set (SEGUNDOS)
	 */
	public void setTiempoPedalada(double tiempopedalada) { 
		if (tiempopedalada>getPeriodoCadencia())
			this.tiempopedalada = getPeriodoCadencia();
		else if (tiempopedalada<0)
			this.tiempopedalada = 0;
		else
			this.tiempopedalada = tiempopedalada;
	}
	
	public int getNumeroCiclista() {
		return numerociclista;
	}

	public void setNumeroCiclista(int numerociclista) {
		this.numerociclista = numerociclista;
	}
	
	/**
	 * @return Devuelve la bici del ciclista
	 */
	public Bicicleta getBici() {
		return bici;
	}	
	
	/**
	 * @return Devuelve el reloj del ciclista
	 */
	public RelojSimple getReloj() {
		return reloj;
	}

	/**
	 * @param reloj the reloj to set
	 */
	public void setReloj(RelojSimple reloj) {
		this.reloj = reloj;
	}
	
	/**
	 * @return the cantidadfrenada
	 */
	public int getCantidadfrenada() {
		return cantidadfrenada;
	}

	/**
	 * @param cantidadfrenada the cantidadfrenada to set
	 */
	public void setCantidadfrenada(int cantidadfrenada) {		
		if (cantidadfrenada < 0) //no se puede tener cantidad de frenada < 0
			this.cantidadfrenada = 0;
		else
			this.cantidadfrenada = cantidadfrenada;
	}

	/**
	 * @return the vivo
	 */
	public boolean isVivo() {
		return vivo;
	}
	
	/**
	 * Aumenta la cadencia del ciclista
	 * @param cadencia Valor del número de pedaladas por minuto (RPM)
	 */
	public void aumentaCadencia(int cadencia)
	{
		setCadencia(getCadencia() + cadencia);
	}

	/**
	 * Disminuye la cadencia del ciclista
	 * @param cadencia Valor del número de pedaladas por minuto (RPM)
	 */
	public void disminuyeCadencia(int cadencia)
	{
		setCadencia(getCadencia() - cadencia);
	}
	
	/*
	 * Cuando el numero de iteraciones frenada sea 0, se deja de frenar y se recuperan las cadencia y tiempo de frenada anteriores
	 * y se pone el numeroiteraciones a -1, para marcar que no existe frenada
	 */
	private void frenar()
	{
		if (numeroiteracionesfrenada > 0)
		{
			bici.setVelocidadActual(bici.getVelocidadActual() - cantidadfrenadaporiteracion);
			setCadencia(0);
			numeroiteracionesfrenada--;
		}
		else if (numeroiteracionesfrenada == 0)
		{
			cadencia = cadenciaantesdefrenar;
			tiempopedalada = tiempopedaladaantesdefrenar;
			numeroiteracionesfrenada = -1;
		}
	}
	
	/**
	 * 
	 * @param frenada
	 * @param tiempo
	 */
	public void frenar(double frenada, int tiempo)
	{
		if (frenada != 0)
		{
			numeroiteracionesfrenada = tiempo*globales.getFRECUENCIA_EJECUCION(); // el numero de veces que se repita la accion de frenar, en iteraciones de bucle
			cantidadfrenadaporiteracion = bici.getVelocidadActual()/(frenada*tiempo*globales.getFRECUENCIA_EJECUCION());
			cadenciaantesdefrenar = cadencia;
			tiempopedaladaantesdefrenar = tiempopedalada;
		}
		else //si la frenada es 0, es frenado en seco
		{
			numeroiteracionesfrenada = 1;
			cantidadfrenadaporiteracion = bici.getVelocidadActual();
			cadenciaantesdefrenar = 0;
			tiempopedaladaantesdefrenar = 0;
		}
	}
	
	/*
	 * Método que "mata" al ciclista
	 * Pone sus propiedades a 0, y lo coloca en la posición en la que haya muerto
	 */
	public void Muere(double metros)
	{
		//setCadencia(0);
		//setTiempoPedalada(0);
		getBici().setVelocidadActual(0);
		getBici().setCuentametros(metros);
		vivo = false;
	}

	public void ejecuta() {

		if (vivo)
		{
			frenar();
			double aceleracionempleada = bici.avanzayActualiza(getTiempoPedalada());
			setFuerzaactual(getFuerzaactual() - (getMasa()+bici.getMasa())*aceleracionempleada);
				//comprueba si se ha quedado sin fuerza
			if(getFuerzaactual() <= 0)
			{
				Muere(getBici().getCuentametros());
				Presentador.getInstance().nuevoMensajeLog("El ciclista " + getNumeroCiclista() + " ha MUERTO al quedarse sin fuerza en el: " + Presentador.getInstance().getTiempo().getTiempo());
			}
				//comprueba si ha llegado a meta
			if(bici.getCuentametros() >= globales.getKILOMETROS_CARRERA()/globales.getMETROS_A_KILOMETROS())
			{
				Muere(globales.getKILOMETROS_CARRERA()/globales.getMETROS_A_KILOMETROS());
				VentanasEmergentes.ciclistaGanador(getNombre(), getNumeroCiclista());
			}
		}
	}
	
	public void muestra(){
		System.out.println("CICLISTA " + this.numerociclista + " * " + bici.getCuentametros() + " (m) * " + bici.getVelocidadActual() + " (m/s) * " + getCadencia() + " (ped/min)" );
		new Salida().escribirEnFichero(Globales.getInstance().getRUTA_SALIDA() , "CICLISTA " + this.numerociclista + " * " + bici.getCuentametros() + " (m) * " + bici.getVelocidadActual() + " (m/s) * " + getCadencia() + " (ped/min)" , false);
			//System.out.println("BICI - " + "acel.teorica: " + bici.calcularAceleracionTeorica(tiempopedalada) + "    acel.extern: " + bici.getAceleracionexterna() + "    acel.aplicad: " + bici.calcularAceleracionAplicadaBicicleta(tiempopedalada));
			//en verdad para poder mostrar las velocidades externas, tendríamos que hacer este syso en recalcularVelocidad(double tiempopedalada), ya que los valores se pierden
	}

}
