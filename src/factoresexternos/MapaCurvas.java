package factoresexternos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import interfaces.InterfaceObjetoEjecutable;
import ciclista.Ciclista;

import principal.Globales;
import principal.Presentador;

public class MapaCurvas implements InterfaceObjetoEjecutable {

	private Globales globales = Globales.getInstance();
	
	Map <Double, Double> mapavelocidadesmaximas = new TreeMap <Double, Double> (); //en m/s
	private ArrayList<Ciclista> listaciclistas;
	
	public final static int CLAVE = 1; //se emplean estos 2 valores para hacer referencia desde fuera a la función transformarMapaEnArray 
	public final static int VALOR = 0;
	
	public MapaCurvas(ArrayList<Ciclista> listaciclistas){
		
		this.listaciclistas = listaciclistas;
		
		almacenarCurva("48", "120");
		almacenarCurva("62", "200");
		almacenarCurva("97", "100");
		
	}
	
	//Los datos que se le pases tienen que ser correctos. En m/s la velocidad y Km la distancia
	public void almacenarCurva(String kilometro, String velocidadmaxima)
	{
		mapavelocidadesmaximas.put(Double.parseDouble(kilometro), Double.parseDouble(velocidadmaxima));
	}
	
	/*
	 * Devuelve un Array con todas las claves o valores del mapa, dependiendo del parámetro que se le pase en al entrada
	 */
	public double[] transformarEnArray(int clavevalor)
	{
		double[] array = new double[mapavelocidadesmaximas.size()];
		
		Iterator<Map.Entry<Double, Double>> it = mapavelocidadesmaximas.entrySet().iterator();
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
	
	/*
	 * Tiene que estar en kilometros
	 * Devuelve un mapa con ls curvas qeu se ha encontrado en el tramo.
	 */
	public Map <Double, Double> curvasEntreDosPuntos(double kminicial, double kmfinal)
	{
		Map <Double, Double> curvas = new TreeMap <Double, Double>();
		Iterator<Map.Entry<Double, Double>> it = mapavelocidadesmaximas.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<Double, Double> dato = it.next();
			if (dato.getKey() >= kminicial && dato.getKey() < kmfinal)	
				curvas.put(dato.getKey(), dato.getValue());
		}
		
		return curvas;
	}
	
	public void ejecuta()
	{
		for (Ciclista c: listaciclistas)
		{
			Map <Double, Double> curvas = new TreeMap <Double, Double>();
			curvas = curvasEntreDosPuntos(c.getBici().getCuentametros()*globales.getMETROS_A_KILOMETROS(), c.getBici().calcularSiguientePosicion(c.getTiempoPedalada())*globales.getMETROS_A_KILOMETROS());
			Iterator<Map.Entry<Double, Double>> it = curvas.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry<Double, Double> dato = it.next();
				
				if(c.getBici().getVelocidadActual() > dato.getValue())
				{
					c.Muere(dato.getKey()/globales.getMETROS_A_KILOMETROS());
					Presentador.getInstance().nuevoMensajeLog("El ciclista " + c.getNumeroCiclista() + " ha MUERTO en la curva, en el: " + Presentador.getInstance().getTiempo().getTiempo());
				}
			}			
		}
	}
	
}
