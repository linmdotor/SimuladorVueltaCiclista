package controlador;

import java.awt.event.*;

import comandos.GestorComandos;

import ciclista.Ciclista;

import interfaces.InterfaceVistaCiclistas;

/**
 * Clase controlador de la ventana principal de la aplicación, en la que se muestran los datos y
 * los controles de los ciclistas.
 * Se definirá un objeto controlador por cada ciclista.
 * El controlador se encarga de actualizar la vista y actualizar el modelo de negocio 
 * en función de las entradas del usuario (cuando se produzca un ActionPerformed).
 * 
 * BIBLIO:
 * http://www.javamex.com/tutorials/swing/jbutton.shtml
 * 
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 */
public class ControladorCiclistas implements ActionListener {

	int numero_ciclista = 0;
	private InterfaceVistaCiclistas vista;
	private Ciclista ciclista;
	
	public ControladorCiclistas(InterfaceVistaCiclistas vista, GestorComandos comandero, int numero_ciclista, Ciclista ciclista)//, ConversorEurosPesetas modelo)
	{
		this.vista = vista;
		this.numero_ciclista = numero_ciclista;
		this.ciclista = ciclista;
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
			case (InterfaceVistaCiclistas.FUERZAMAS):
			{
				ciclista.setFuerzaactual(ciclista.getFuerzaactual() + ciclista.getFuerzamaxima()/100);
				vista.setFuerza(numero_ciclista, ciclista.getFuerzaactual(), ciclista.getFuerzamaxima());
			}
			break;
			case (InterfaceVistaCiclistas.FUERZAMENOS):
			{
				ciclista.setFuerzaactual(ciclista.getFuerzaactual() - ciclista.getFuerzamaxima()/100);
				vista.setFuerza(numero_ciclista, ciclista.getFuerzaactual(), ciclista.getFuerzamaxima());
			}
			break;
			case (InterfaceVistaCiclistas.CADENCIAMAS):
			{
				ciclista.setCadencia(ciclista.getCadencia() + 1);
				vista.setCadencia(numero_ciclista, ciclista.getCadencia());
			}
			break;
			case (InterfaceVistaCiclistas.CADENCIAMENOS):
			{
				ciclista.setCadencia(ciclista.getCadencia() - 1);
				vista.setCadencia(numero_ciclista, ciclista.getCadencia());
			}
			break;
			case (InterfaceVistaCiclistas.CADENCIATXT):
			{
				ciclista.setCadencia(vista.getCadencia(numero_ciclista));
				vista.setCadencia(numero_ciclista, ciclista.getCadencia());
			}
			break;
			case (InterfaceVistaCiclistas.PEDALADAMAS):
			{
				ciclista.setTiempoPedalada(ciclista.getTiempoPedalada() + 0.01);
				vista.setPedalada(numero_ciclista, ciclista.getTiempoPedalada());
			}
			break;
			case (InterfaceVistaCiclistas.PEDALADAMENOS):
			{
				ciclista.setTiempoPedalada(ciclista.getTiempoPedalada() - 0.01);
				vista.setPedalada(numero_ciclista, ciclista.getTiempoPedalada());
			}
			break;
			case (InterfaceVistaCiclistas.PEDALADATXT):
			{
				ciclista.setTiempoPedalada(vista.getPedalada(numero_ciclista));
				vista.setPedalada(numero_ciclista, ciclista.getTiempoPedalada());
			}
			break;
			case (InterfaceVistaCiclistas.PLATOMAS):
			{
				ciclista.getBici().setPlatoSeleccionado(ciclista.getBici().getPlatoSeleccionado() + 1);
				vista.setPlato(numero_ciclista, ciclista.getBici().getPlatoSeleccionado());
			}
			break;
			case (InterfaceVistaCiclistas.PLATOMENOS):
			{
				ciclista.getBici().setPlatoSeleccionado(ciclista.getBici().getPlatoSeleccionado() - 1);
				vista.setPlato(numero_ciclista, ciclista.getBici().getPlatoSeleccionado());
			}
			break;
			case (InterfaceVistaCiclistas.PLATOTXT):
			{
				ciclista.getBici().setPlatoSeleccionado(vista.getPlato(numero_ciclista));
				vista.setPlato(numero_ciclista, ciclista.getBici().getPlatoSeleccionado());
			}
			break;
			case (InterfaceVistaCiclistas.PINONMAS):
			{
				ciclista.getBici().setPinonSeleccionado(ciclista.getBici().getPinonSeleccionado() + 1);
				vista.setPinon(numero_ciclista, ciclista.getBici().getPinonSeleccionado());
			}
			break;
			case (InterfaceVistaCiclistas.PINONMENOS):
			{
				ciclista.getBici().setPinonSeleccionado(ciclista.getBici().getPinonSeleccionado() - 1);
				vista.setPinon(numero_ciclista, ciclista.getBici().getPinonSeleccionado());
			}
			break;
			case (InterfaceVistaCiclistas.PINONTXT):
			{
				ciclista.getBici().setPinonSeleccionado(vista.getPinon(numero_ciclista));
				vista.setPinon(numero_ciclista, ciclista.getBici().getPinonSeleccionado());
			}
			break;
			case (InterfaceVistaCiclistas.FRENAR):
			{
				//Suponemos 1 segundo de tiempo de frenada si se modifica por la vista, esto habrá que cambiarlo si se agrega el timpo de frenada a la vista
				ciclista.frenar(vista.getFreno(numero_ciclista), 1); 
			}
			break;
			case (InterfaceVistaCiclistas.FRENOMAS):
			{
				ciclista.setCantidadfrenada(ciclista.getCantidadfrenada() + 1);
				vista.setFreno(numero_ciclista, ciclista.getCantidadfrenada());
			}
			break;
			case (InterfaceVistaCiclistas.FRENOMENOS):
			{
				ciclista.setCantidadfrenada(ciclista.getCantidadfrenada() - 1);
				vista.setFreno(numero_ciclista, ciclista.getCantidadfrenada());		
			}
			break;
			default:{ }
		}
	}
	
}
