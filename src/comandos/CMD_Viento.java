/**
 * 
 */
package comandos;

import java.text.ParseException;
import java.util.StringTokenizer;

import principal.Presentador;
import contador.RelojSimple;
import factoresexternos.MapaViento;
import interfaces.InterfaceComando;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class CMD_Viento implements InterfaceComando {

	// A estas variables las tendrá que dar valor el parser y el configuracontexto
	MapaViento mapaviento;
	RelojSimple hora; //hh:mm:ss
	String estado; //AFAVOR, ENCONTRA, NULO
	double velocidad; // (m/s)

	
	/**
	 * SINTAXIS: "viento HORA ESTADO M/S"
	 */
	@Override
	public InterfaceComando parse(String cadena)
	{
		StringTokenizer tokens = new StringTokenizer(cadena);
		String[] stringanalizado = new String[4];
		CMD_Viento comando = null;

		//guardo las 6 primeras palabras de cadena, que son mis argumentos, ignorando el resto de elementos de la cadena
		int i = 0;
		while(tokens.hasMoreElements() && i < 4)
		{
			stringanalizado[i] = tokens.nextToken();
            i++;
        }
		if (stringanalizado.length == 4)
		{		
			/* Comprobamos que corresponde al comando
			Si no, devolvemos un Comando NULL */
			
			//Primero comprobamos que la sintaxis es correcta
			if(stringanalizado[0].equalsIgnoreCase("viento") &&
					ComprobarCasting.isTiempo(stringanalizado[1]) &&
					(stringanalizado[2].equalsIgnoreCase("AFAVOR") || stringanalizado[2].equalsIgnoreCase("ENCONTRA") || stringanalizado[2].equalsIgnoreCase("NULO") ) && 
					ComprobarCasting.isDouble(stringanalizado[3]) )
			{
				RelojSimple hora = new RelojSimple();
				try {
					hora = RelojSimple.parseRelojSimple(stringanalizado[1]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//Luego comprobamos que los valores son válidos para la aplicación
				String estado = stringanalizado[2];
				double velocidad = Double.parseDouble(stringanalizado[3]);
				
				if (velocidad >= 0 )
				{
					comando = new CMD_Viento();
					comando.hora = hora;
					comando.estado = estado;
					comando.velocidad = velocidad;				
				}		
				//else, comando = null (por defecto)		
			}
		}
		return comando;
		
	}

	@Override
	public void configuraContexto()
	{
		this.mapaviento = Presentador.getInstance().getViento();
	}
	
	@Override
	public void ejecuta()
	{
		mapaviento.almacenarViento(hora.getTiempo(), estado, Double.toString(velocidad));
	}
	
	@Override
	public String help()
	{
		return "viento HORA ESTADO M/S";		
	}
}
