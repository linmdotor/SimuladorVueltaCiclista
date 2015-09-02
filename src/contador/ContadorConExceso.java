/**
 * File: ContadorConExceso.java
 * -------------------
 * Este fichero define la clase contador con exceso de nuestro programa.
 * Notas: No se recomienda utilizar el método setLimite una vez se ha empezado a contar.
 */
package contador;

/**
 * La clase ContadorConExceso supone una mejora en la clase Cuenta, excediendo a un segundo contador.
 * De manera que si se alcanza el límite indicado, el Contador principal se reinicia 
 * y se incrementa en 1 e segundo contador (el del exceso)
 * . 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class ContadorConExceso extends ContadorSaturado 
{

	
	/*Variables privadas*/
	protected Contador contadorsuperior; //¿Por qué tiene que ser static? 

	/**
	 * Crea un nuevo ContadorConExceso inicializado a 0 con límite 99.	
	 */
	/*public ContadorConExceso()
	{
		this(contadorsuperior);
	}
	*/
	
	/**
	 * Crea un nuevo ContadorConExceso con un límite específico.	
	 * @param contador Contador al que incrementará 1 cuando llegue al límite
	 */
	public ContadorConExceso(Contador contador)
	{
		super();
		contadorsuperior = contador;
	}
	
	/**
	 * Crea un nuevo ContadorConExceso inicializado a 0 con un límite específico.	
	 * @param limite Límite máximo del contador
	 * @param contador Contador al que incrementará 1 cuando llegue al límite
	 */
	public ContadorConExceso(int limite, Contador contador)
	{
		super(limite);
		contadorsuperior = contador;
	}
	
	/**
	 * Modifica el contador superior que incrementará cuando llegue al límite.
	 * @param contador Contador al que incrementará 1 cuando llegue al límite
	 */
	public void setContadorSuperior(Contador contador) 
	{
		contadorsuperior = contador;
	}
	
	
	/**
	 * Devuelve el contador superior que incrementará cuando llegue al límite.
	 * @return contadorsuperior Contador al que incrementará 1 cuando llegue al límite
	 */
	public Contador getContadorSuperior() 
	{
		return contadorsuperior;
	}
	
	/**
	 * Incrementa el valor del contador en 1 y si llega al límite incrementa el contador superior y reinicia a 0.
	 */
	public void	incrementa()
	{
		incrementa(1);
	}
	
	/**
	 * Incrementa el valor del contador en cierto valor y si llega al límite incrementa el contador superior y reinicia a 0.
	 * @param incremento Valor a incrementar
	 */
	public void incrementa(int incremento) 
	{
		if ((getCuenta()+ incremento) < limitecontador)
		{	
			setCuenta(getCuenta() + incremento) ;
		}
		else
		{
			contadorsuperior.incrementa((getCuenta()+ incremento)/limitecontador);
			setCuenta ((getCuenta()+ incremento)%limitecontador);
		}
	}
}
