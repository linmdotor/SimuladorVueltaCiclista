/**
 * File: Contador.java
 * -------------------
 * Este fichero define la clase contador de nuestro programa.
 */
package contador;

/**
 * La clase contador proporciona una variable cuenta y métodos para avanzar la misma.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class Contador
{
	
	/*Variables privadas*/
	protected int cuenta = 0; /*el valor del contador*/
	
	/**
	 * Crea un nuevo Contador inicializado a 0.	
	 */
	public Contador()
	{
		this(0);
	}
	
	/**
	 * Crea un nuevo Contador inicializado a cierto valor
	 * @param valor Valor de inicio.
	 */
	public Contador(int valor)
	{
		cuenta = valor;
	}
	
	
	/**
	 * Incrementa en 1 el valor del contador.
	 */
	public void incrementa() 
	{
		this.incrementa(1);
	}
	
	/**
	 * Incrementa el valor del contador en cierto valor.
	 * @param incremento Valor a incrementar
	 */
	public void incrementa(int incremento) 
	{
		cuenta = cuenta + incremento;
	}
	
	
	/**
	 * Modifica el valor del contador.
	 * @param valor Valor del contador
	 */
	public void setCuenta(int valor) 
	{
		cuenta = valor;
	}
	
	
	/**
	 * Devuelve el valor del contador.
	 * @return cuenta Valor del contador.
	 */
	public int getCuenta() 
	{
		return cuenta;
	}
		
}
