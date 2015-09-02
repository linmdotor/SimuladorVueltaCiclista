/**
 * File: Bicicleta.java
 * -------------------
 * Este fichero define la clase bicicleta de nuestro programa.
 */
package bicicleta;

/*Se tienen que importar las interfaces que utilizan la clase*/
import principal.Globales;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class Bicicleta
{
	/*Constantes de la clase Bicicleta*/
	static final int[] DIENTESPLATOS = {50,54,56}; //vectores que contienen los piñones y platos de una bicicleta por defecto
	static final int[] DIENTESPINONES = {25,28,30,33,35};
	
	/*Variables privadas*/
	private int masa; /*El valor de la masa de la bicicleta, en kilogramos*/
	private double radiorueda; /*El valor del diametro de la rueda de la bicicleta, en m*/
	private double aceleracionexterna; /*Aceleración calculada como la suma de las aceleraciones de los factores externos*/
	private double velocidadactual; /* El valor de la velocidad que tiene la bicicleta, en m/s*/
	private int[] dientesplatos; /*Vector que contiene los dientes de cada plato de la bicicleta*/
	private int[] dientespinones; /*Vector que contiene los dientes de cada piñón de la bicicleta*/
	private double cuentametros = 0; //Lleva la cuenta de la distancia al origen a la que se encuentra la bicicleta (metros)
	
	private int platoseleccionado; /*Número del plato seleccionado*/
	private int pinonseleccionado; /*Número del piñón seleccionado*/
	
	private Globales globales = Globales.getInstance();
	
	//TODO  Tener cuidado, porque no reconoce cuando el avance es tan grande que cambia en medio de una pendiente (tanto ene esto como en el dibujo)
	//Si le pilla un cambio de pendiente enmedio del avance, hace todo el tramo como si fuera la pendiente de la que parte.!!!!!!!!!!!
	
	/**
	 * Crea una nueva Bicicleta con valores por defecto.
	 */
	public Bicicleta()
	{
		this(15, 1/Globales.getInstance().getPI(), 0, DIENTESPLATOS, DIENTESPINONES);		
	}
	
	/**
	 * Crea una nueva bicicleta inicializada con ciertos valores.
	 * @param masa Valor de la masa de la bicicleta, en gramos.
	 * @param radiorueda Valor del diametro de la rueda de la bicicleta, en pulgadas.
	 * @param velocidadactual Valor de la velocidad que tiene la bicicleta, en Km/h.
	 * @param dientesplatos Vector que contiene los dientes de cada plato de la bicicleta.
	 * @param dientespinones Vector que contiene los dientes de cada piñón de la bicicleta.
	 */
	public Bicicleta(int masa, double radiorueda, int velocidadactual, int[] dientesplatos, int[] dientespinones) 
	{
		this.masa = masa;
		this.radiorueda = radiorueda;
		this.velocidadactual = velocidadactual;
		this.dientesplatos = dientesplatos.clone();
		this.dientespinones = dientespinones.clone();
		platoseleccionado = 0;
		pinonseleccionado = 0;
	}

	/**
	 * Modifica el valor de la masa de la bicicleta.
	 * @param masa Valor de la masa de la bicicleta.
	 */
	public void setMasa(int masa) {
		this.masa = masa;
	}
	
	/**
	 * Devuelve el valor de la masa de la bicicleta.
	 * @return la masa de la bicicleta.
	 */
	public int getMasa() {
		return masa;
	}
	
	/**
	 * Devuelve el valor del peso de la bicicleta.
	 * @return el peso de la bicicleta.
	 */
	public double getPeso() {
		return (double)this.masa * globales.getGRAVEDAD();
	}

	/**
	 * Modifica el valor del radio de la rueda de la bicicleta.
	 * @param radiorueda Valor del radio de las ruedas de la bicicleta.
	 */
	public void setRadioRueda(double radiorueda) {
		this.radiorueda = radiorueda;
	}
	
	/**
	 * Devuelve el valor del radio de la bicicleta.
	 * @return radiorueda el radio de las ruedas de la bicicleta.
	 */
	public double getRadioRueda() {
		return radiorueda;
	}
	
	/**
	 * @return the aceleracionexterna
	 */
	public double getAceleracionexterna() {
		return aceleracionexterna;
	}

	/**
	 * @param aceleracionexterna the aceleracionexterna to set
	 */
	public void setAceleracionexterna(double aceleracionexterna) {
		this.aceleracionexterna = aceleracionexterna;
	}
	
	/**
	 * Modifica el valor de la velocidad de la bicicleta.
	 * @param velocidadactual Valor de la velocidad de la bicicleta.
	 */
	public void setVelocidadActual(double velocidadactual) {
		if (getCuentametros() > 0)
			this.velocidadactual = velocidadactual;
		else //están en el punto de salida
			this.velocidadactual = 0;
	}
	
	/**
	 * Devuelve el valor de la velocidad actual.
	 * @return velocidad de la bicicleta.
	 */
	public double getVelocidadActual() {
		return velocidadactual;
	}	
	
	/**
	 * Modifica el valor de los dientes de un plato.
	 * @param plato Valor del plato de la bicicleta.
	 * @param numerodientes Número dientes que tiene cada piñón de la bicicleta.
	 */
	public void setDientesPlatos(int plato, int numerodientes) 
	{
		dientesplatos[plato] = numerodientes;
	}	
	
	/**
	 * Devuelve un vector con los dientes de cada plato.
	 * @return Vector de dientes de los platos
	 */
	public int[] getDientesPlatos()
	{
		return dientesplatos;
	}
	
	/**
	 * Devuelve el valor de los dientes de un plato.
	 * @return dientesplatos Vector que contiene los dientes de cada plato de la bicicleta
	 */
	public int getDientesPlatos(int plato) 
	{
		return dientesplatos[plato];
	}
	
	/**
	 * Modifica el valor de los dientes de un plato.
	 * @param pinon Valor del piñones de la bicicleta.
	 * @param numerodientes Número dientes que tiene cada piñón de la bicicleta.
	 */
	public void setDientesPinones(int pinon, int numerodientes) 
	{
		dientespinones[pinon] = numerodientes;
	}
	
	/**
	 * Devuelve un vector con los dientes de cada piñon.
	 * @return Vector de dientes de los piñones
	 */
	public int[] getDientesPinones()
	{
		return dientespinones;
	}
	
	/**
	 * Devuelve el valor de los piñones de un plato.
	 * @param pinon Valor del piñones de la bicicleta.
	 * @return dientespinones Vector que contiene los dientes de cada plato de la bicicleta
	 */
	public int getDientesPinones(int pinon) 
	{
		return dientespinones[pinon];
	}

	/**
	 * Devuelve el valor del plato seleccionado en la bicicleta.
	 * @return platoseleccionado Número del plato seleccionado
	 */
	public int getPlatoSeleccionado() 
	{
		return platoseleccionado;
	}

	/**
	 * Modifica el valor del plato seleccionado en la bicicleta.
	 * @param plato Número del plato seleccionado
	 */
	public void setPlatoSeleccionado(int plato) 
	{
		if(plato < 0)
			platoseleccionado = 0;
		else if(plato > dientesplatos.length-1)
			platoseleccionado = dientesplatos.length-1;	
		else 
			platoseleccionado = plato;	
	}

	/**
	 * Devuelve el valor del piñón seleccionado en la bicicleta.
	 * @return pinoneleccionado Número del piñon seleccionado
	 */
	public int getPinonSeleccionado() 
	{
		return pinonseleccionado;
	}

	/**
	 * Modifica el valor del piñón seleccionado en la bicicleta.
	 * @param pinon Número del piñón seleccionado
	 */
	public void setPinonSeleccionado(int pinon) 
	{
		if(pinon < 0)
			pinonseleccionado = 0;
		else if(pinon > dientespinones.length-1)
			pinonseleccionado = dientespinones.length-1;	
		else 
			pinonseleccionado = pinon;
	}
	
	/**
	 * Devuelve la distancia al origen de la bicicleta en metros
	 * @return distancia al origen de la bicicleta en metros
	 */
	public double getCuentametros()
	{
		return this.cuentametros;
	}
	
	/**
	 * Sitúa al ciclista en una posición del inicio de la carrera
	 * @param metros metros a los que se encuentra del origen
	 */
	public void setCuentametros(double metros)
	{
		if (metros > 0)
			this.cuentametros = metros;
		else
			this.cuentametros = 0;
	}
	
	/**
	 * Calcula el tiempo que tarda en dar una pedalada, en función de una cadencia.
	 * @param cadencia Valor del número de pedaladas por minuto (RPM).
	 * @return Devuelve el valor del tiempo de pedalada (expresado segundos).
	 */
	public double calcularTiempoCadencia(int cadencia)
	{		
		if (cadencia > 0)
			return globales.getMINUTOS_A_SEGUNDOS()/(double)cadencia;
		else
			return 0;
	}
	
	/*
	 * Se tiene en cuenta que el recorrido lineal es 2*PI*radio, porque se tomará como pedalada una vuelta entera, no media vuelta
	 */
	public double calcularRecorridoLinealRueda()
	{
		return (double)2 * globales.getPI() * getRadioRueda();
	}
	
	public double calcularRelacionTransmision()
	{
		return (double)dientesplatos[platoseleccionado] / (double)dientespinones[pinonseleccionado];	
	}
	
	
	/**
	 * Calcula la distancia que da en una pedalada en función del plato y piñón seleccionado.
	 * @return Devuelve el valor de la distancia recorrida en una pedalada (expresado en m).
	 */
	public double calcularDistanciaPedalada() //EspacioPorCadaPedalada
	{
		return (calcularRecorridoLinealRueda() * calcularRelacionTransmision());
	}
	
	/**
	 * Calcula la velocidad que adquiriria la bicicleta en función de un tiempo de pedalada.
	 * @param tiempopedalada Tiempo que tarda en dar 1 pedalada (segundos)
	 * @return Devuelve la velocidad (expresado en m/s)
	 */
	public double calcularVelocidadTeorica(double tiempopedalada)
	{
		if (tiempopedalada > 0)
			return calcularDistanciaPedalada() / tiempopedalada;
		else
			return 0;
	}
	
	/**
	 * Calcula la aceleración que adquiriria la bicicleta en un instante "tiempodepedalada".
	 * @param tiempopedalada Tiempo que tarda en dar 1 pedalada (segundos)
	 * @return Devuelve la aceleracion (expresado en m/s^2)
	 */
	public double calcularAceleracionTeorica(double tiempopedalada)
	{
		if (tiempopedalada > 0)
			return calcularVelocidadTeorica(tiempopedalada) / tiempopedalada;
		else
			return 0;
	}
	
	
	/*
	 * Este es el método que tiene que decidir cuál es la aceleración de ciclista que emplearemos,
	 * dependiendo de si está pedaleando o no, y cómo sea la aceleración externa
	 * a la hora de calcular velocidades y aceleraciones se tiene en cuenta... :
	 * 
	 */
	public double calcularAceleracionAplicadaBicicleta(double tiempopedalada)
	{
		double aceleracionteoricapedalada = calcularAceleracionTeorica(tiempopedalada);
		double aceleracionciclista = 0;
		
		if (aceleracionteoricapedalada == 0 || aceleracionteoricapedalada <= this.aceleracionexterna) //no está pedaleando, porque o bien tiempopedaalda = 0, o bien los factores externos le dan una aceleración mayor de la que pudiera alcanzar pedaleando (pedalada en vacío)																									//por tanto puede avanzar o retroceder en función de los factores externos, y la fuerza gastada será nula
		{
			aceleracionciclista = this.aceleracionexterna;
		}
		else //no hay opción de que sea <0, pues eso lo controla calcularAceleracionAplicadaBicicleta()
			//(aceleracionteoricapedalada > this.aceleracionexterna)  //la aceleracion que conseguiría pedaleando es mayor que la de los factores externos, por tanto sólo gasto la fuerza necesaria para alcanzar esa aceleración, aprovechando la aceleración que me dan los factores externos
		{
			aceleracionciclista = aceleracionteoricapedalada;
		}
		
		return aceleracionciclista;
	}
	
	/*
	 * Este es el método que tiene que decidir cuál es la aceleración resultante del ciclista, que se emplea para calcular la fuerza ejercida
	 * dependiendo de si está pedaleando o no, y cómo sea la aceleración externa
	 * a la hora de calcular velocidades y aceleraciones se tiene en cuenta... :
	 * 
	 */
	public double calcularAceleracionResultanteEsfuerzo(double tiempopedalada)
	{
		double aceleracionteoricapedalada = calcularAceleracionTeorica(tiempopedalada);
		double aceleracionresultanteesfuerzo = 0;
		
		if (aceleracionteoricapedalada == 0 || aceleracionteoricapedalada <= this.aceleracionexterna) //no está pedaleando, porque o bien tiempopedaalda = 0, o bien los factores externos le dan una aceleración mayor de la que pudiera alcanzar pedaleando (pedalada en vacío)
			//por tanto puede avanzar o retroceder en función de los factores externos, y la fuerza gastada será nula
		{
			aceleracionresultanteesfuerzo = 0;
		}
		else //no hay opción de que sea <0, pues eso lo controla calcularAceleracionAplicadaBicicleta()
		//(aceleracionteoricapedalada > this.aceleracionexterna)  //la aceleracion que conseguiría pedaleando es mayor que la de los factores externos, por tanto sólo gasto la fuerza necesaria para alcanzar esa aceleración, aprovechando la aceleración que me dan los factores externos
		{
			aceleracionresultanteesfuerzo = aceleracionteoricapedalada - this.aceleracionexterna;
		}
		
		return aceleracionresultanteesfuerzo;
	}

		
	/**
	 * Realiza todas las operaciones necesarias para que la bicicleta avance, y se actualicen sus datos:
	 * La aceleración que le aplica el ciclista (o motor).
	 * La aceleración de los factores externos (que ya tienen que haber sido calculados).
	 * Devuelve la aceleración que se debe emplear en el cálculo del esfuerzo de pedalada
	 * 
	 * @param tiempopedalada Tiempo que tarda el ciclista en dar la pedalada (seg)
	 * @return Aceleración resultante al dar la pedalada(m/s^2) (se emplea para calcular la fuerza aplicada).
	 */
	public double avanzayActualiza(double tiempopedalada)
	{
		double aceleracionesfuerzo = calcularAceleracionResultanteEsfuerzo(tiempopedalada);
		if(tiempopedalada > 0 && getVelocidadActual() < 0) //si ha comenzado a pedalear, tras tener pedalada 0 y estar retrocediendo
			setVelocidadActual(0); //es incompatible que retroceda si tiene timpo de pedalada, por lo tanto, se pone velocidad 0 para que comience a avanzar.
		recalcularPosicion(tiempopedalada);
		recalcularVelocidad(tiempopedalada);
		setAceleracionexterna(0); //reinicia la aceleración externa para la siguiente vuelta del bucle
			
		return aceleracionesfuerzo/globales.getFRECUENCIA_EJECUCION(); //devolver la aceleracion resultante
	}
	
	/*
	 * Método que calcula la velocidad en función de la velocidad actual y la aceleración (V = V0 + a*t)
	 */
	public void recalcularVelocidad(double tiempopedalada)
	{
		setVelocidadActual(getVelocidadActual() + calcularAceleracionAplicadaBicicleta(tiempopedalada)/globales.getFRECUENCIA_EJECUCION());	
	}
	
	/*
	 * Métdo que modifica la posicion actual del ciclista, avanzando
	 */
	public void recalcularPosicion(double tiempopedalada)
	{
		setCuentametros(calcularSiguientePosicion(tiempopedalada));
	}
	
	/*
	 * Método que calcula la posicion en función de la velocidad actual y la aceleración (X = (a*t^2)/2 + V0*t + X0)
	 * Tener en cuenta que se debe ejecutar este antes del del recalcularVelocidad, pues si no tomaría un valor de la velocidad no válido
	 */
	
	public double calcularSiguientePosicion(double tiempopedalada)
	{
		return ((calcularAceleracionAplicadaBicicleta(tiempopedalada)/Math.pow(globales.getFRECUENCIA_EJECUCION(),2))/2 + 
				getVelocidadActual()/globales.getFRECUENCIA_EJECUCION() + 
				getCuentametros());
	}

}
