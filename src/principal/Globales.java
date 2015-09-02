package principal;

/**
 * Clase que contiene las variables globales que se inicializan en el arranque de la aplicación.
 * Estas variables son utilizadas por gran parte de las clases de la aplicación, pues contienen datos clave en la misma.
 * 
 * Para realizar la clase se ha empleado el patrón Singleton, de manera que sólo podrá existir una única instancia de la clase.
 * Esto hace que los datos que contiene la misma, sean iguales para todas las clases que la empleen.
 * BIBL: http://migranitodejava.blogspot.com.es/2011/05/singleton.html
 * 
 * La ventaja de esta clase es que se tienen reunidos todas las varibles que permiten la modificación de la práctica a ciertos parámetros.
 * El uso de este tipo de variables globales puede considerarse mala práctica si no se hace con cuidado.
 * Para ello el usuario tendrá que tener en cuenta que cualquier cambio que se haga en la misma repercute en el resto de clases.
 * Por lo que estas variables NO se pueden modificar en cualquier momento.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class Globales {

	private static Globales instancia = new Globales();
		
		//Variables de la aplicación principal
	private int FRECUENCIA_EJECUCION = 100; //1-> 1 vuelta del bucle/1 ejecución (muy rapido), 1000 vueltas de bucle/1 ejecución (muy lento)	
	private int FRECUENCIA_MAX = 1000;
	private int FRECUENCIA_MIN = 1;
	private int MAX_NUM_CICLISTAS = 20;
	private int NUM_CICLISTAS = 4;
	private double MAX_FUERZA_CICLISTA = 1.5E5;
	private int KILOMETROS_CARRERA = 120;
	private int CADENCIA_MAX = 120;
	
	
		//Variables de Interfaz Gráfica
	private int GR_RESOLUCION_X = 800;
	private int GR_RESOLUCION_Y = 600;
	private int GR_RESOLUCION_MIN_X = 355;
	private int GR_RESOLUCION_MIN_Y = 540;
	private double GR_TAM_DIBUJO_CARRERA_X = 780;
	private double GR_TAM_DIBUJO_CARRERA_Y = 150;
	private double GR_POS_Y_COMIENZO_CARRERA = GR_TAM_DIBUJO_CARRERA_Y/2;
	private double GR_TAM_IMG_CICLISTA_X = 100;
	private double GR_TAM_IMG_CICLISTA_Y = 50;
	private int GR_REDIMENSION_IMG_CICLISTA = 15;
	private int GR_SEPARACION_MARCA_KILOMETROS = 10;
	
		//Variables RUTA de los ficheros externos
	private String RUTA_ENTRADA_DATOS = "/ficheros/entrada_datos_iniciales.txt";
	private String RUTA_COMANDOS = "/ficheros/entrada_comandos.txt";
	private String RUTA_LOG = "/ficheros/salida_log.txt";
	private String RUTA_SALIDA = "/ficheros/salida_auxiliar.txt";
	private String RUTA_IMAGEN_CICLISTA = "ficheros/ciclista.gif";	//la imagen no debe tener el "/" antes de la ruta	
	
		//CONSTANTES matemáticas
	private final double PI = Math.PI;
	private final double GRAVEDAD = 9.8;  //m/s^2
	
		//CONSTANTES multiplicativas de conversión de unidades
	private final double CNV_M_S_A_KM_H = 3.6; //equivalencia m/s y km/h  (1m/s = 3.6km/h)
	private final double CNV_METROS_A_KILOMETROS = 0.001; //equivalencia m/km (km = m*0.001)	
	private final int CNV_DIAS_A_HORAS = 24; //equivalencia h/min (h = dias*24)
	private final int CNV_HORAS_A_MINUTOS = 60; //equivalencia h/min (min = h*60)
	private final int CNV_MINUTOS_A_SEGUNDOS = 60; //equivalencia min/seg (seg = min*60)
	private final int CNV_SEGUNDOS_A_MILISEGUNDOS = 1000; //equivalencia seg/mili (mili = seg*1000)


	/**
	 * Constructor de la case privado, para evitar que se creen nuevas instancias
	 */
	private Globales() {		
	}
	
	/**
	 * Devuelve una instancia única de la clase Globales
	 * @return Instancia de la clase
	 */
	public static Globales getInstance() {
	    return instancia;    
	}

	
	
	//---------------------getters and setters--------------------------------
		
	/**
	 * @return the fRECUENCIA_EJECUCION
	 */
	public int getFRECUENCIA_EJECUCION() {
		return FRECUENCIA_EJECUCION;
	}

	/**
	 * @param fRECUENCIA_EJECUCION the fRECUENCIA_EJECUCION to set
	 */
	public void setFRECUENCIA_EJECUCION(int fRECUENCIA_EJECUCION) {
		if(fRECUENCIA_EJECUCION < FRECUENCIA_MIN)
			FRECUENCIA_EJECUCION = FRECUENCIA_MIN;
		else if(fRECUENCIA_EJECUCION > FRECUENCIA_MAX)
			FRECUENCIA_EJECUCION = FRECUENCIA_MAX;
		else
			FRECUENCIA_EJECUCION = fRECUENCIA_EJECUCION;
	}

	/**
	 * @return the mAX_NUM_CICLISTAS
	 */
	public int getMAX_NUM_CICLISTAS() {
		return MAX_NUM_CICLISTAS;
	}
	
	/**
	 * @return the nUM_CICLISTAS
	 */
	public int getNUM_CICLISTAS() {
		return NUM_CICLISTAS;
	}

	/**
	 * @param nUM_CICLISTAS the nUM_CICLISTAS to set
	 */
	public void setNUM_CICLISTAS(int nUM_CICLISTAS) {
		NUM_CICLISTAS = nUM_CICLISTAS;
	}

	/**
	 * @return the mAX_FUERZA_CICLISTA
	 */
	public double getMAX_FUERZA_CICLISTA() {
		return MAX_FUERZA_CICLISTA;
	}

	/**
	 * @return the kILOMETROS_CARRERA
	 */
	public int getKILOMETROS_CARRERA() {
		return KILOMETROS_CARRERA;
	}

	/**
	 * @return the cADENCIA_MAX
	 */
	public int getCADENCIA_MAX() {
		return CADENCIA_MAX;
	}

	/**
	 * @return the rESOLUCION_X
	 */
	public int getRESOLUCION_X() {
		return GR_RESOLUCION_X;
	}

	/**
	 * @return the rESOLUCION_Y
	 */
	public int getRESOLUCION_Y() {
		return GR_RESOLUCION_Y;
	}

	/**
	 * @return the rESOLUCION_MIN_X
	 */
	public int getRESOLUCION_MIN_X() {
		return GR_RESOLUCION_MIN_X;
	}

	/**
	 * @return the rESOLUCION_MIN_Y
	 */
	public int getRESOLUCION_MIN_Y() {
		return GR_RESOLUCION_MIN_Y;
	}

	/**
	 * @return the tAM_DIBUJO_CARRERA_X
	 */
	public double getTAM_DIBUJO_CARRERA_X() {
		return GR_TAM_DIBUJO_CARRERA_X;
	}

	/**
	 * @return the tAM_DIBUJO_CARRERA_Y
	 */
	public double getTAM_DIBUJO_CARRERA_Y() {
		return GR_TAM_DIBUJO_CARRERA_Y;
	}

	/**
	 * @return the pOS_Y_COMIENZO_CARRERA
	 */
	public double getPOS_Y_COMIENZO_CARRERA() {
		return GR_POS_Y_COMIENZO_CARRERA;
	}

	/**
	 * @return the tAM_IMG_CICLISTA_X
	 */
	public double getTAM_IMG_CICLISTA_X() {
		return GR_TAM_IMG_CICLISTA_X;
	}

	/**
	 * @return the tAM_IMG_CICLISTA_Y
	 */
	public double getTAM_IMG_CICLISTA_Y() {
		return GR_TAM_IMG_CICLISTA_Y;
	}

	/**
	 * @return the rEDIMENSION_IMG_CICLISTA
	 */
	public int getREDIMENSION_IMG_CICLISTA() {
		return GR_REDIMENSION_IMG_CICLISTA;
	}

	/**
	 * @return the sEPARACION_MARCA_KILOMETROS
	 */
	public int getSEPARACION_MARCA_KILOMETROS() {
		return GR_SEPARACION_MARCA_KILOMETROS;
	}

	/**
	 * @return the pI
	 */
	public double getPI() {
		return PI;
	}

	/**
	 * @return the gRAVEDAD
	 */
	public double getGRAVEDAD() {
		return GRAVEDAD;
	}

	/**
	 * @return the m_S_A_KM_H
	 */
	public double getM_S_A_KM_H() {
		return CNV_M_S_A_KM_H;
	}

	/**
	 * @return the mETROS_A_KILOMETROS
	 */
	public double getMETROS_A_KILOMETROS() {
		return CNV_METROS_A_KILOMETROS;
	}

	/**
	 * @return the dIAS_A_HORAS
	 */
	public int getDIAS_A_HORAS() {
		return CNV_DIAS_A_HORAS;
	}

	/**
	 * @return the hORAS_A_MINUTOS
	 */
	public int getHORAS_A_MINUTOS() {
		return CNV_HORAS_A_MINUTOS;
	}

	/**
	 * @return the mINUTOS_A_SEGUNDOS
	 */
	public int getMINUTOS_A_SEGUNDOS() {
		return CNV_MINUTOS_A_SEGUNDOS;
	}

	/**
	 * @return the sEGUNDOS_A_MILISEGUNDOS
	 */
	public int getSEGUNDOS_A_MILISEGUNDOS() {
		return CNV_SEGUNDOS_A_MILISEGUNDOS;
	}

	/**
	 * @return the rUTA_COMANDOS
	 */
	public String getRUTA_COMANDOS() {
		return RUTA_COMANDOS;
	}
	
	/**
	 * @return the rUTA_IMAGEN_CICLISTA
	 */
	public String getRUTA_IMAGEN_CICLISTA() {
		return RUTA_IMAGEN_CICLISTA;
	}
	
	/**
	 * @return the rUTA_SALIDA
	 */
	public String getRUTA_SALIDA() {
		return RUTA_SALIDA;
	}
	
	/**
	 * @return the rUTA_ENTRADA_DATOS
	 */
	public String getRUTA_ENTRADA_DATOS() {
		return RUTA_ENTRADA_DATOS;
	}
	
	/**
	 * @return the rUTA_LOG
	 */
	public String getRUTA_LOG() {
		return RUTA_LOG;
	}
	
}