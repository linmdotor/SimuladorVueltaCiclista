/**
 * 
 */
package comandos;

import java.util.StringTokenizer;

import principal.Globales;
import principal.Presentador;
import factoresexternos.MapaCurvas;
import interfaces.InterfaceComando;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class CMD_Curva implements InterfaceComando
{

	// A estas variables las tendrá que dar valor el parser y el configuracontexto
	MapaCurvas mapacurvas;
	double kilometro;
	double velocidadmax;

	
	/**
	 * SINTAXIS: "curva KILOMETRO VELOCIDADMAX"
	 */
	@Override
	public InterfaceComando parse(String cadena)
	{

		StringTokenizer tokens = new StringTokenizer(cadena);
		String[] stringanalizado = new String[3];
		CMD_Curva comando = null;

		//guardo las 6 primeras palabras de cadena, que son mis argumentos, ignorando el resto de elementos de la cadena
		int i = 0;
		while(tokens.hasMoreElements() && i < 3)
		{
			stringanalizado[i] = tokens.nextToken();
            i++;
        }
		if (stringanalizado.length == 3)
		{		
			/* Comprobamos que corresponde al comando
			Si no, devolvemos un Comando NULL */
			
			//Primero comprobamos que la sintaxis es correcta
			if(stringanalizado[0].equalsIgnoreCase("curva") &&
					ComprobarCasting.isDouble(stringanalizado[1]) &&
					ComprobarCasting.isDouble(stringanalizado[2]))
			{
				//Luego comprobamos que los valores son válidos para la aplicación
				double kilometro = Double.parseDouble(stringanalizado[1]);
				double velocidadmax = Double.parseDouble(stringanalizado[2]);
				
				if (kilometro >= 0 && kilometro <= Globales.getInstance().getKILOMETROS_CARRERA() && 
						velocidadmax >= 0)
				{
					comando = new CMD_Curva();
					comando.kilometro = kilometro;
					comando.velocidadmax = velocidadmax;			
				}		
				
				//else, comando = null (por defecto)		
			}
		}
		return comando;
		
	}

	@Override
	public void configuraContexto()
	{	
		this.mapacurvas = Presentador.getInstance().getCurvas();
	}
	
	@Override
	public void ejecuta()
	{
		mapacurvas.almacenarCurva(Double.toString(kilometro), Double.toString(velocidadmax));	
	}
	
	@Override
	public String help()
	{
		return "curva KILOMETRO VELOCIDADMAX";		
	}
}
