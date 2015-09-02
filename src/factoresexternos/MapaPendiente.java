package factoresexternos;

import interfaces.InterfaceObjetoEjecutable;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;

import ciclista.Ciclista;

import principal.Globales;
/*
 * ¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡!!!!!!!!!!!!!!!!!!!!!!!
 * IMPORTANTE
 * Explicar en algún sitio de la doc, por qué se emplean los m/s como unidades, en vez de los km/h:
 * es porque son unidades más genéricas. ¿?
 */


public class MapaPendiente implements InterfaceObjetoEjecutable {
	//explicar por qué carajos se hace el enumerado como privao
	private enum Inclinacion 
	{
		BAJADA(1), SUBIDA(-1), LLANO(0);
		
		private int inclinacion = 0;
			
		private Inclinacion(int inclinacion)
		{
			this.inclinacion = inclinacion;
		}
		
		public int getInclinacion()
		{
			return inclinacion;
		}
	}
	
	private Globales globales = Globales.getInstance();
	
	Map <Double, Double> mapadeaceleraciones = new TreeMap <Double, Double> ();
	Map <Double, Double> mapadependientes = new TreeMap <Double, Double> ();
	//Se enplea TreeMap porque lo ordena en la inserción (explicar en la doc)
	public final static int CLAVE = 1; //se emplean estos 2 valores para hacer referencia desde fuera a la función transformarMapaEnArray 
	public final static int VALOR = 0;
	
	private ArrayList<Ciclista> listaciclistas;
	
	public MapaPendiente(ArrayList<Ciclista> listaciclistas){
		
		this.listaciclistas = listaciclistas;
		
		almacenarPendiente("0", "12", "SUBIDA");
		almacenarPendiente("13", "0", "LLANO");
		almacenarPendiente("25.5", "8", "BAJADA");
		almacenarPendiente("50.3", "18", "SUBIDA");
		almacenarPendiente("65", "5", "BAJADA");
		almacenarPendiente("90", "2", "SUBIDA");
		almacenarPendiente("100.9", "20", "SUBIDA");
		almacenarPendiente("110", "0", "LLANO");
		//almacenarPendiente("0", "0", "LLANO");
		//almacenarPendiente("15", "100", "SUBIDA");
		//almacenarPendiente("0", "100", "BAJADA");
	}
	
	//Los datos que se le pases tienen que ser correctos.
	public void almacenarPendiente(String kilometro, String pendienteporcentual, String inclinacion)
	{
		double aceleracion = 0.0;
		aceleracion = convertirPendienteEnAceleracion(Double.parseDouble(pendienteporcentual));
		mapadeaceleraciones.put(Double.parseDouble(kilometro), getInclinacion(inclinacion)*aceleracion);
		mapadependientes.put(Double.parseDouble(kilometro), getInclinacion(inclinacion)*Double.parseDouble(pendienteporcentual));
	}
	
	//Por simplificacion se emplea: Pendiente% = (metros ascendidos / metros recorridos) · 100
	//pendiente = subida/recorrido
	//aceleracionpendiente = 9.8m/s exp(2) * subida/recorrido
	
	//Otra forma si se toma el cálculo real: aceleracion = gravedad*sin(angulo)
	//Forma si se toma el cálculo de sube 12m cada 100m: gravedad*tg(angulo)
	
	//como se puede comprobar fácilmente, con una inclinación de menos del 20%, la diferencia entre ambas fórmulas es prácticamente despreciable (de 0.0038)
	//http://recursostic.educacion.es/gauss/web/materiales_didacticos/eso/actividades/geometria/trigonometria/pendiente_carretera/actividad.html
	private double convertirPendienteEnAceleracion(double pendienteporcentual) {
		return (pendienteporcentual/100)* globales.getGRAVEDAD(); 
	}

	//ver si esto tiene que calcular ya la aceleracion con la formula
	private double getInclinacion(String tipopendiente)
	{
		int inclinacion = 0;
		
		for(Inclinacion pendiente:EnumSet.allOf(Inclinacion.class))
		{  
			if(pendiente.toString().equalsIgnoreCase(tipopendiente))
			{
				inclinacion = pendiente.getInclinacion();
	        }
	    }
		
		return inclinacion;	
	}
	
	public double getAceleracion(double km)
	{
		double aceleracion = 0.0;

		Iterator<Map.Entry<Double, Double>> it = mapadeaceleraciones.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<Double, Double> dato = it.next();
			if (dato.getKey() <= km)	
				aceleracion = dato.getValue();
		}
		
		return aceleracion;
	}
	
	public double getPendiente(double km)
	{
		double pendiente = 0.0;

		Iterator<Map.Entry<Double, Double>> it = mapadependientes.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<Double, Double> dato = it.next();
			if (dato.getKey() <= km)	
				pendiente = dato.getValue();
		}
		
		return pendiente;
	}
	
	
	/*
	 * Devuelve un Array con todas las claves o valores del mapa, dependiendo del parámetro que se le pase en al entrada
	 */
	public double[] transformarEnArray(int clavevalor)
	{
		double[] array = new double[mapadependientes.size()];
		
		Iterator<Map.Entry<Double, Double>> it = mapadependientes.entrySet().iterator();
		int i = 0;
		while (it.hasNext())
		{
			Map.Entry<Double, Double> dato = it.next();
			if(clavevalor == CLAVE)
				array[i] = dato.getKey();
			else if (clavevalor == VALOR)
				array[i] = dato.getValue();
			i++;
		}
		
		return array;
	}
	
	public void ejecuta()
	{
		for (Ciclista c: listaciclistas)
			c.getBici().setAceleracionexterna( c.getBici().getAceleracionexterna() + 
					getAceleracion(c.getBici().getCuentametros()*globales.getMETROS_A_KILOMETROS()));
			
	}
	
}