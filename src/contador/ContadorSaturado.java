/**
 * File: ContadorSaturado.java
 * -------------------
 * Este fichero define la clase contador saturado de nuestro programa.
 */
package contador;

/**
 * La clase ContadorSaturado supone una mejora en la clase Cuenta, añadiendo un límite.
 * De manera que no se puede superar un valor mayor que el límite indicado. 
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class ContadorSaturado extends Contador 
{
	/*Variables privadas*/
	protected int limitecontador = 0;
	
	/**
	 * Crea un nuevo ContadorSaturado inicializado a 0 con límite 99.	
	 */
	public ContadorSaturado()
	{
		this(99);
	}
	
	/**
	 * Crea un nuevo ContadorSaturado con un límite específico.	
	 * @param limite Límite máximo del contador
	 */
	public ContadorSaturado(int limite)
	{
		super(); //Crea un contador con valor 0
		limitecontador = limite;
	}
	
	/**
	 * Crea un nuevo ContadorSaturado inicializado a cierto vaor y con un límite específico.	
	 * @param valorinicio Valor de inicio.
	 * @param limite Límite máximo del contador
	 */
	public ContadorSaturado(int valorinicio, int limite)
	{
		super(valorinicio);
		limitecontador = limite;
	}
	
	/**
	 * Modifica el valor del límite.
	 * @param valor Valor del límite
	 */
	public void setLimite(int valor) 
	{
		limitecontador = valor;
	}
	
	
	/**
	 * Devuelve el valor del límite.
	 * @return limitecontador Valor del límite.
	 */
	public int getLimite() 
	{
		return limitecontador;
	}
	
	/**
	 * Incrementa el valor del contador en cierto valor sin salirse del límite.
	 * @param incremento Valor a incrementar
	 */
	public void incrementa(int incremento) 
	{
		if ((getCuenta()+ incremento) < limitecontador)
			setCuenta(getCuenta() + incremento) ;
		else
			setCuenta (limitecontador-1);
	}
	
	/**
	 * Modifica el valor del contador sin salirse del límite.
	 * @param valor Valor del contador
	 */
	public void setCuenta(int valor) 
	{
		if (valor < limitecontador)
			super.setCuenta(valor);
		else
			super.setCuenta(limitecontador-1);
	}
	
}