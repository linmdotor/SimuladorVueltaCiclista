/**
 * 
 */
package comandos;

import java.util.StringTokenizer;

import principal.Globales;
import principal.Presentador;

import ciclista.Ciclista;
import interfaces.InterfaceComando;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class CMD_Frena implements InterfaceComando {

	// A estas variables las tendrá que dar valor el parser y el configuracontexto
	Ciclista ciclista;
	int numerociclista;
	int cantidadfrenada; // la n en 1/n - 1/0, 1/1, 1/2... etc
	int tiempofrenada;	//segundos

	
	/**
	 * SINTAXIS: "ciclista N frena CANTIDAD en TIEMPO"
	 */
	@Override
	public InterfaceComando parse(String cadena) 
	{
		StringTokenizer tokens = new StringTokenizer(cadena);
		String[] stringanalizado = new String[6];
		CMD_Frena comando = null;

		//guardo las 6 primeras palabras de cadena, que son mis argumentos, ignorando el resto de elementos de la cadena
		int i = 0;
		while(tokens.hasMoreElements() && i < 6)
		{
			stringanalizado[i] = tokens.nextToken();
            i++;
        }
		if (stringanalizado.length == 6)
		{		
			/* Comprobamos que corresponde al comando
			Si no, devolvemos un Comando NULL */
			
			//Primero comprobamos que la sintaxis es correcta
			if(stringanalizado[0].equalsIgnoreCase("ciclista") &&
					ComprobarCasting.isInt(stringanalizado[1]) &&
					stringanalizado[2].equalsIgnoreCase("frena") && 
					ComprobarCasting.isInt(stringanalizado[3]) && 
					stringanalizado[4].equalsIgnoreCase("en") &&
					ComprobarCasting.isInt(stringanalizado[5]) )
			{
				//Luego comprobamos que los valores son válidos para la aplicación
				int numerociclista = Integer.parseInt(stringanalizado[1]);
				int cantidadfrenada = Integer.parseInt(stringanalizado[3]);
				int tiempofrenada = Integer.parseInt(stringanalizado[5]);
				
				if (numerociclista >= 0 && numerociclista < Globales.getInstance().getNUM_CICLISTAS() &&
						cantidadfrenada >= 0 &&
						tiempofrenada >= 0)
				{
					comando = new CMD_Frena();
					comando.numerociclista = numerociclista;
					comando.cantidadfrenada = cantidadfrenada;
					comando.tiempofrenada = tiempofrenada;				
				}		
				//else, comando = null (por defecto)		
			}
		}
		return comando;
		
	}

	@Override
	public void configuraContexto()
	{
		this.ciclista = Presentador.getInstance().getListaciclistas().get(numerociclista);		
	}
	
	@Override
	public void ejecuta()
	{
		ciclista.frenar(cantidadfrenada, tiempofrenada);
	}
	
	@Override
	public String help()
	{
		return "ciclista N frena CANTIDAD en TIEMPO";		
	}
	
	
}
