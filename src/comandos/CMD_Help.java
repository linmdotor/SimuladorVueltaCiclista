/**
 * 
 */
package comandos;

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import principal.Presentador;
import interfaces.InterfaceComando;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class CMD_Help implements InterfaceComando {

	// A estas variables las tendrá que dar valor el parser y el configuracontexto
	GestorComandos gestorcomandos;
	
	/**
	 * SINTAXIS: "help"
	 */
	@Override
	public InterfaceComando parse(String cadena)
	{
		StringTokenizer tokens = new StringTokenizer(cadena);
		String stringanalizado = new String();
		CMD_Help comando = null;

		//guardo la primera palabra de cadena, que es mi argumento, ignorando el resto de elementos de la cadena
		if(tokens.hasMoreElements())
			stringanalizado = tokens.nextToken();	
		
		//Comprobamos que corresponde al comando
		//Si no, devolvemos un Comando NULL
		if(stringanalizado.equalsIgnoreCase("help"))
				comando = new CMD_Help();
		//else, comando = null (por defecto)
		
		return comando;
	}

	@Override
	public void configuraContexto()
	{
		gestorcomandos = Presentador.getInstance().getGestorcomandos();
	}
	
	@Override
	public void ejecuta()
	{
		JOptionPane.showMessageDialog(null, gestorcomandos.ayuda());
	}
	
	@Override
	public String help()
	{
		return "help";		
	}
}
