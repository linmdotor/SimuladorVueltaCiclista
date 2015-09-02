/**
 * 
 */
package factoresexternos;

import interfaces.InterfaceObjetoEjecutable;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import ciclista.Ciclista;

import principal.Globales;

/**
 * @author usuario_local
 *
 */

//FORMATO DE INTRODUCCION DE EL VIENTO: Viento HORA ESTADO KMS/H
public class MapaViento implements InterfaceObjetoEjecutable {
	//explicar por qué carajos se hace el enumerado como privao
	private enum Direccion 
	{
		AFAVOR(1), ENCONTRA(-1), NULO(0);
		
		private int direccion = 0;
		
		private Direccion(int direccion)
		{
			this.direccion = direccion;
		}
		
		public int getDireccion()
		{
			return direccion;
		}
	}
	
	private Globales globales = Globales.getInstance();
	
	Map <String, Double> mapadeviento = new TreeMap <String, Double> ();
	//Se enplea TreeMap porque lo ordena en la inserción (explicar en la doc)
	
	ArrayList<Ciclista> listaciclistas;
	
	public MapaViento(ArrayList<Ciclista> listaciclistas){
		
		this.listaciclistas = listaciclistas;
		
		almacenarViento("00:00:00", "AFAVOR", "12");
		almacenarViento("00:00:06", "NULO", "0");
		almacenarViento("00:00:15", "ENCONTRA", "50");
		almacenarViento("00:00:20", "NULO", "0");
		almacenarViento("00:00:40", "AFAVOR", "50");
		almacenarViento("00:01:00", "AFAVOR", "10");
		almacenarViento("00:02:00", "ENCONTRA", "15");
		almacenarViento("00:03:30", "NULO", "0");
		almacenarViento("00:05:20", "ENCONTRA", "60");
		almacenarViento("00:08:00", "NULO", "0");
		//almacenarViento("00:00:00", "NULO", "0");
		//almacenarViento("00:00:00", "ENCONTRA", "1200");
	}
	
		
	//Los datos que se le pases tienen que ser correctos.
	public void almacenarViento(String hora, String tipoviento, String velocidad)
	{
		double aceleracion = 0.0;
		aceleracion = convertirVelocidadVientoEnAceleracionPorBeaufort(Double.parseDouble(velocidad)/globales.getM_S_A_KM_H());
		mapadeviento.put(hora, getDireccion(tipoviento) * aceleracion);
	}
	
	// B = (Velocidad /0,837) exp (3/2)
	// Aceleracionviento = B/100
	private double convertirVelocidadVientoEnAceleracionPorBeaufort(double velocidad)
	{
		return (Math.pow((velocidad/0.837), 1.5))/100;
	}


	private double getDireccion(String tipoviento)
	{
		int direccion = 0;
		
		for(Direccion viento:EnumSet.allOf(Direccion.class))
		{  
			if(viento.toString().equalsIgnoreCase(tipoviento))
			{
				direccion = viento.getDireccion();
	        }
	    }
		
		return direccion;		
	}
	
	public double getViento(String tiempo)
	{
		double aceleracion_viento = 0.0;

		Iterator<Map.Entry<String, Double>> it = mapadeviento.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<String, Double> dato = it.next();
			if (dato.getKey().compareTo(tiempo)<0 || dato.getKey().compareTo(tiempo) == 0)	
				aceleracion_viento = dato.getValue();
		}
		
		return aceleracion_viento;
	}
	
	public void ejecuta()
	{
		for (Ciclista c: listaciclistas)
			c.getBici().setAceleracionexterna(c.getBici().getAceleracionexterna() + 
					getViento(c.getReloj().getTiempo()));
	}

}
