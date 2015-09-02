package interfaces;

import controlador.ControladorCiclistas;

/**
 * La interfaz InterfaceVistaCiclistas contiene los métodos y constantes de la parte de Vista del MVC.
 * Estos son todos los métodos con lso que interactuará el controlador.
 * 
 * Al principio los setActionCommand se hacían directamente con un string, pero después se vio
 * que era mejor hacerlo con unas constantes en la interfaz, ya que como la clase la implementa, 
 * puede acceder a ellas mediate el autocompletado de Eclipse y no da lugar a equivocaciones.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 */
public interface InterfaceVistaCiclistas {
	public void arranca(); // organiza los elementos y los visualiza
	public void actualizaVista(); //actualiza los elementos de la vista
	public void setControlador(int numero_ciclista, ControladorCiclistas c); //Controlador del MVC	
	
	public void setFuerza(int numero_ciclista, double fuerza, double fuerzamaxima);
	public void setCadencia(int numero_ciclista, int cadencia);
	public int getCadencia(int numero_ciclista);
	public void setPedalada(int numero_ciclista, double pedalada);
	public double getPedalada(int numero_ciclista);
	public void setPlato(int numero_ciclista, int plato);
	public int getPlato(int numero_ciclista);
	public void setPinon(int numero_ciclista, int pinon);
	public int getPinon(int numero_ciclista);
	public void setFreno(int numero_ciclista, int freno);
	public double getFreno(int numero_ciclista);
	public void setVelocidad(int numero_ciclista, double velocidad);
	public void setKilometro(int numero_ciclista, double kilometro);
	public void setPendiente(int numero_ciclista, double pendiente);
	
	// Constantes que definen las posibles operaciones
	static final String FUERZAMAS = "Btn_Fuerza+";
	static final String FUERZAMENOS = "Btn_Fuerza-";
	static final String CADENCIAMAS = "Btn_Cadencia+";
	static final String CADENCIAMENOS = "Btn_Cadencia-";
	static final String CADENCIATXT = "Txt_Cadencia";
	static final String PEDALADAMAS = "Btn_Pedalada+";
	static final String PEDALADAMENOS = "Btn_Pedalada-";
	static final String PEDALADATXT = "Txt_Pedalada";
	static final String PLATOMAS = "Btn_Plato+";
	static final String PLATOMENOS = "Btn_Plato-";
	static final String PLATOTXT = "Txt_Plato";
	static final String PINONMAS = "Btn_Pinon+";
	static final String PINONMENOS = "Btn_Pinon-";
	static final String PINONTXT = "Txt_Pinon";
	static final String FRENAR = "Btn_Frenar";
	static final String FRENOMAS = "Btn_Freno+";
	static final String FRENOMENOS = "Btn_Freno-";
	
}
