/**
 * 
 */
package comandos;

import java.util.StringTokenizer;

import principal.Globales;
import principal.Presentador;
import factoresexternos.MapaPendiente;
import interfaces.InterfaceComando;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class CMD_Pendiente implements InterfaceComando {

	// A estas variables las tendrá que dar valor el parser y el configuracontexto
	MapaPendiente mapapendiente;
	double kilometro;
	double inclinacion; // porcentual: (-100 <= x => 100)
	String estado; //BAJADA, SUBIDA, LLANO

	
	/**
	 * SINTAXIS: "pendiente KILOMETRO PENDIENTEPORCENTUAL INCLINACION"
	 */
	@Override
	public InterfaceComando parse(String cadena)
	{
		StringTokenizer tokens = new StringTokenizer(cadena);
		String[] stringanalizado = new String[4];
		CMD_Pendiente comando = null;

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
			if(stringanalizado[0].equalsIgnoreCase("pendiente") &&
					ComprobarCasting.isDouble(stringanalizado[1]) &&
					ComprobarCasting.isDouble(stringanalizado[2]) && 
					(stringanalizado[3].equalsIgnoreCase("BAJADA") || stringanalizado[3].equalsIgnoreCase("SUBIDA") || stringanalizado[3].equalsIgnoreCase("LLANO") ))
			{
				//Luego comprobamos que los valores son válidos para la aplicación
				double kilometro = Double.parseDouble(stringanalizado[1]);
				double inclinacion = Double.parseDouble(stringanalizado[2]);
				String estado = stringanalizado[3];
				
				if (kilometro >= 0 && kilometro < Globales.getInstance().getKILOMETROS_CARRERA() &&
						inclinacion >= 0 && inclinacion <= 100)
				{
					comando = new CMD_Pendiente();
					comando.kilometro = kilometro;
					comando.inclinacion = inclinacion;
					comando.estado = estado;				
				}		
				//else, comando = null (por defecto)		
			}
		}
		return comando;
		
	}

	@Override
	public void configuraContexto()
	{
		this.mapapendiente = Presentador.getInstance().getPendiente();
	}
	
	@Override
	public void ejecuta()
	{
		mapapendiente.almacenarPendiente(Double.toString(kilometro), Double.toString(inclinacion), estado);
	}
	
	@Override
	public String help()
	{
		return "pendiente KILOMETRO PENDIENTEPORCENTUAL INCLINACION";		
	}
}
