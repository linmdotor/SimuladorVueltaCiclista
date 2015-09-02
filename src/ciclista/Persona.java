/**
 * File: Persona.java
 * -------------------
 * Este fichero define la clase persona de nuestro programa.
 */
package ciclista;

import principal.Globales;

/**
 * Clase Persona, que contiene el nombre y masa de la misma
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public abstract class Persona {

	//Variables privadas de la clase
	private String nombre = "";
	private double masa; // kilogramos de la persona
	
	
	//---------------------getters and setters--------------------------------
	
	
	/**
	 * @return Devuelve el nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param nombre El nombre asignado a la persona
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @return Devuelve la masa de la persona
	 */
	public double getMasa() {
		return masa;
	}
	
	/**
	 * @param masa La masa asignada a la persona
	 */
	public void setMasa(double masa) {
		this.masa = masa;
	}
	
	
	public double getPeso()
	{
		return this.masa * Globales.getInstance().getGRAVEDAD();
	}
}
