package controlador;

import vista.VentanaDatos;
import fecha.Fecha;
import interfaces.InterfaceVistaAuxiliar;

import java.awt.event.*;

import principal.Globales;
import principal.Presentador;

/**
 * Clase controlador de la ventana auxiliar de la aplicación, en la que se muestran los datos y
 * los controles del log, comandos, y datos auxiliares.
 * Se definirá un objeto controlador para toda la ventana.
 * El controlador se encarga de actualizar la vista y actualizar el modelo de negocio 
 * en función de las entradas del usuario (cuando se produzca un ActionPerformed).
 * 
 * En este caso no se necesita una refecrencia a la vista (la interfaz) como en el caso de los ciclistas.
 * Esto es porque las acciones se realizan desde la misma ventana, sin necesidad de una interfaz común,
 * que contenga los métodos para modificar la vista.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class ControladorAplicacion implements ActionListener {
	
		private VentanaDatos ventana;
		private boolean detenido = true;
		
	public ControladorAplicacion(VentanaDatos ventana)
	{
		this.ventana = ventana;
	}
		
	/**
	 * Selecciona, cuando hay un evento, en función del valor de ActionCommand, la acción a realizar,
	 * actualizando los datos del modelo de negocio y actualizando también la vista.
	 */
	@Override
	public void actionPerformed(ActionEvent evento) 
	{
		switch (evento.getActionCommand())
		{
			case (InterfaceVistaAuxiliar.ENVIAR):
			{
				Presentador.getInstance().getGestorcomandos().introducirComandosSwing(ventana.leeDatos(ventana.getListaDatos().getTxt_comandos()));			
				ventana.getListaDatos().getTxt_comandos().setText("");
			}
			break;
			case (InterfaceVistaAuxiliar.START_STOP):
			{
				if(ventana.getListaDatos().getBtn_Start_Stop().getText().equals("Stop"))
				{
					Presentador.getInstance().nuevoMensajeLog("deteniendo aplicación en: " + Presentador.getInstance().getTiempo().getTiempo() + ", (" + new Fecha().getHoraActual() + " hora real) ");
					ventana.getListaDatos().getBtn_Start_Stop().setText("Start");
					detenido = true;
				}
				else
				{
					Presentador.getInstance().nuevoMensajeLog("reiniciando aplicación en: " + Presentador.getInstance().getTiempo().getTiempo() + ", (" + new Fecha().getHoraActual() + " hora real) ");
					ventana.getListaDatos().getBtn_Start_Stop().setText("Stop");
					detenido = false;
				}	
			}
			break;
			case (InterfaceVistaAuxiliar.MAS_LENTO):
			{
				Globales.getInstance().setFRECUENCIA_EJECUCION(Globales.getInstance().getFRECUENCIA_EJECUCION()*2);
			}
			break;
			case (InterfaceVistaAuxiliar.MAS_RAPIDO):
			{
				Globales.getInstance().setFRECUENCIA_EJECUCION(Globales.getInstance().getFRECUENCIA_EJECUCION()/2);
			}
			break;
			default:{ }
		}
	}
	
	/**
	 * Devuelve si la aplicación está detenida o no.
	 * @return true si la aplicación está detenida, false en caso contrario.
	 */
	public boolean getDetenido()
	{
		return detenido;
	}
	
}