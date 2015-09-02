/**
 * File: RelojSimple.java
 * -------------------
 * Este fichero define la clase reloj simple de nuestro programa.
 */
package contador;

import java.text.ParseException;

import E_S.Salida;
import principal.Globales;
import interfaces.InterfaceObjetoEjecutable;
import interfaces.InterfaceObjetoMostrable;

/**
 * La clase RelojSimple implementa un Contador desde las horas hasta los milisegundos.
 * Además proporciona métodos para el acceso, modificación y visualización del tiempo.
 * 
 * El funcionamiento de la clase son 4 Contadores con exceso, que incrementan al contador superior;
 * milisegundos incrementa a segundos, segundos a minutos, minutos a horas, y horas a un contador nulo.
 * De esta manera cuando cada contador llega a su límite, incrementa a 1 el siguiente y se reinicia.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class RelojSimple implements InterfaceObjetoEjecutable, InterfaceObjetoMostrable
{
	//Variables privadas de la clase
	private ContadorSaturado nulo;
	private ContadorConExceso horas;
	private ContadorConExceso minutos;
	private ContadorConExceso segundos;
	private ContadorConExceso milisegundos;
	
	/**
	 * Crea un nuevo RelojSimple inicializado a 0 horas, 0 minutos y 0 segundos y 0 milisegundos
	 */
	public RelojSimple()
	{
		this(0,0,0,0);
	}
	
	/**
	 * Crea un nuevo RelojSimple inicializado a ciertos valores.
	 * @param hora Valor para asignar una hora al reloj.
	 * @param minu Valor para asignar unos minutos al reloj.
	 * @param segu Valor para asignar unos segundos al reloj.
	 * @param mili Valor para asignar unos milisegundos al reloj.
	 */
	public RelojSimple(int hora, int minu, int segu, int mili)
	{
		Globales globales = Globales.getInstance();
		
		nulo = new ContadorSaturado();
		horas = new ContadorConExceso(globales.getDIAS_A_HORAS(), nulo);
		minutos = new ContadorConExceso(globales.getHORAS_A_MINUTOS(), horas);
		segundos = new ContadorConExceso(globales.getMINUTOS_A_SEGUNDOS(), minutos);		
		milisegundos = new ContadorConExceso(globales.getSEGUNDOS_A_MILISEGUNDOS(), segundos);	
		
		milisegundos.setCuenta(mili);
		segundos.setCuenta(segu);
		minutos.setCuenta(minu);
		horas.setCuenta(hora);	
	}
	
	/**
	 * @return Devuelve el valor de las Horas
	 */
	public int getHoras() 
	{
		return horas.getCuenta();
	}

	/**
	 * @param hora Asigna una hora al reloj
	 */
	public void setHoras(int hora) 
	{
		horas.setCuenta(hora);
	}
	
	/**
	 * @return Devuelve el valor de los Minutos
	 */
	public int getMinutos() 
	{
		return minutos.getCuenta();
	}

	/**
	 * @param minuto Asigna unos minutos al reloj
	 */
	public void setMinutos(int minuto) 
	{
		minutos.setCuenta(minuto);
	}
	
	/**
	 * @return Devuelve el valor de los Segundos
	 */
	public int getSegundos() 
	{
		return segundos.getCuenta();
	}

	/**
	 * @param segundo Asigna unos segundos al reloj
	 */
	public void setSegundos(int segundo) 
	{
		segundos.setCuenta(segundo);
	}
	
	/**
	 * @return Devuelve el valor de los MiliSegundos
	 */
	public int getMiliSegundos() 
	{
		return milisegundos.getCuenta();
	}

	/**
	 * @param milisegundo Asigna unos milisegundos al reloj
	 */
	public void setMiliSegundos(int milisegundo) 
	{
		milisegundos.setCuenta(milisegundo);
	}
	
	/**
	 * Asigna un valor al Reloj.
	 * @param horas Valor para asignar una hora al reloj.
	 * @param minutos Valor para asignar unos minutos al reloj.
	 * @param segundos Valor para asignar unos segundos al reloj.
	 * @param milisegundos Valor para asignar unos milisegundos al reloj.
	 */
	public void setTiempo(int horas, int minutos, int segundos, int milisegundos)
	{
		this.horas.setCuenta(horas); 
		this.minutos.setCuenta(minutos);
		this.segundos.setCuenta(segundos);
		this.milisegundos.setCuenta(milisegundos);
	}
	
	/**
	 * @return Devuelve un String con el valor del Reloj en formato hh:mm:ss.
	 */
	public String getTiempo()
	{
		return  String.format("%02d:%02d:%02d", horas.getCuenta(), minutos.getCuenta(), segundos.getCuenta());
	}
	
	/**
	 * incrementa los milisegundos del reloj en 1.
	 */
	public void incrementa() 
	{
		this.incrementa(1);
	}
	
	/**
	 * Incrementa los milisegundos del reloj en un cierto valor.
	 * @param milisegundos 
	 */
	public void incrementa(int milisegundos)
	{
		this.milisegundos.incrementa(milisegundos);
	}
	
	/**
	 * Realiza una conversión desde String a RelojSimple
	 * @param cadena Cadena que se desea convertir.
	 * Debe tener el formato: "23:59:59" ó "23:59:59:999"
	 * @return RelojSimple con la hora indicada.
	 * @throws ParseException Lanza excepción si no es capaz de convertir la cadena original.
	 */
	public static RelojSimple parseRelojSimple(String cadena) throws ParseException
	{
		String[] subcadena = cadena.split(":");
		
		try
		{
			if(subcadena.length >= 3 && subcadena.length <= 4) //comprueba que tiene 3 partes divididas por :
			{
				if(subcadena[0].length() == 2 && subcadena[1].length() == 2 && subcadena[2].length() == 2)
				{			
					//comprueba que son enteros entre los límites
					int horas = Integer.parseInt(subcadena[0]);
					int minutos = Integer.parseInt(subcadena[1]);
					int segundos = Integer.parseInt(subcadena[2]);
					if (horas >= 0 && horas < Globales.getInstance().getDIAS_A_HORAS() && 
							minutos >= 0 && minutos < Globales.getInstance().getHORAS_A_MINUTOS() && 
							segundos >= 0 && segundos < Globales.getInstance().getMINUTOS_A_SEGUNDOS())
					{
						if (subcadena.length == 4)
						{
							if(subcadena[3].length() == 3)
							{
								int milisegundos = Integer.parseInt(subcadena[3]);
								if (milisegundos >= 0 && milisegundos < Globales.getInstance().getSEGUNDOS_A_MILISEGUNDOS())
									return new RelojSimple(horas, minutos, segundos, milisegundos);
							}
						}
						else //la hora está correcta, y es de 3 cifras
						{
							return new RelojSimple(horas, minutos, segundos, 0);
						}						
					}					
						
				}
			}	
			throw new ParseException("Error en la conversion de String a RelojSimple", 0);
		}
		catch (NumberFormatException e)
		{			
			throw new ParseException("Error en la conversion de String a RelojSimple. ", 0);	
		}
	}

	/**
	 * Método de ejecución del reloj. Incrementa la cuenta en función de la frecuencia de ejecución.
	 */
	public void ejecuta()
	{
		incrementa(Globales.getInstance().getSEGUNDOS_A_MILISEGUNDOS()/Globales.getInstance().getFRECUENCIA_EJECUCION()); //sería 1000/frec, para que si pone 1 de frec, avance el reloj rápido, y si pone 1000, avance lento.
	}
	
	/**
	 * Imprime el Tiempo actual, y muestra la hora en un fichero externo.
	 */
	public void muestra()
	{
		System.out.println(getTiempo());		
		new Salida().escribirEnFichero(Globales.getInstance().getRUTA_SALIDA() , getTiempo(), false);		
	}
}
