package interfaces;

/**
 * La interfaz InterfaceComando contiene los métodos que tiene que contener un comando.
 * 
 * Para usar comandos en nuestra aplicación empleamos el patrón Command, en el que cada comando
 * es responsable de reconocerse (parse), asignar valores a sus variables (configuraContexto), 
 * ejecutarse (ejecuta), y mostrar un mensaje de ayuda (help).
 * 
 * Todo comando de la aplicación debe implementar esta interfaz para poder ser ejecutado.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 */
public interface InterfaceComando
{
	public InterfaceComando parse(String cadena);
	public void configuraContexto();
	public void ejecuta();
	public String help();
}
