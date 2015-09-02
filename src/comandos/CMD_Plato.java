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
public class CMD_Plato implements InterfaceComando {

	// A estas variables las tendrá que dar valor el parser y el configuracontexto
	Ciclista ciclista;
	int numerociclista;
	int numeroplato;

	
	/**
	 * SINTAXIS: "ciclista N cambia plato NUMEROP"
	 */
	@Override
	public InterfaceComando parse(String cadena)
	{
		StringTokenizer tokens = new StringTokenizer(cadena);
		String[] stringanalizado = new String[5];
		CMD_Plato comando = null;

		//guardo las 5 primeras palabras de cadena, que son mis argumentos, ignorando el resto de elementos de la cadena
		int i = 0;
		while(tokens.hasMoreElements() && i < 5)
		{
			stringanalizado[i] = tokens.nextToken();
            i++;
        }
		if (stringanalizado.length == 5)
		{		
			/* Comprobamos que corresponde al comando
			Si no, devolvemos un Comando NULL */
			
			//Primero comprobamos que la sintaxis es correcta
			if(stringanalizado[0].equalsIgnoreCase("ciclista") &&
					ComprobarCasting.isInt(stringanalizado[1]) &&
					stringanalizado[2].equalsIgnoreCase("cambia") &&  
					stringanalizado[3].equalsIgnoreCase("plato") &&
					ComprobarCasting.isInt(stringanalizado[4]) )
			{
				//Luego comprobamos que los valores son válidos para la aplicación
				int numerociclista = Integer.parseInt(stringanalizado[1]);
				int numeroplato = Integer.parseInt(stringanalizado[4]);
				
				if (numerociclista >= 0 && numerociclista < Globales.getInstance().getNUM_CICLISTAS() &&
						numeroplato >= 0)
				{
					comando = new CMD_Plato();
					comando.numerociclista = numerociclista;
					comando.numeroplato = numeroplato;				
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
		ciclista.getBici().setPlatoSeleccionado(numeroplato);
	}
	
	@Override
	public String help()
	{
		return "ciclista N cambia plato NUMEROP";		
	}
}
