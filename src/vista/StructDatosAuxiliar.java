package vista;

import javax.swing.*;

/**
 * Clase que define la estructura de datos del control auxiliar, es decir, todos los
 * componentes que se muestran en la ventana auxiliar y sus métodos. 
 * De esta manera se reunen todos los componentes en una misma clase, para que sea más fácil hacer referencia a los mismos.
 * 
 * La primera opcion al realizar la ventana Datos era crear todos los JComponent en la propia clase, 
 * pero al trabajar con un MODELO-VISTA-CONTROLADOR, necesitaba acceder a las JComponent desde la clase 
 * controlador. 
 * Para solucionarlo se creó esta structura a la que se pudiera acceder desde los dos sitios.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class StructDatosAuxiliar {
	private JTextField txt_reloj_siste = new JTextField(10);
	private JTextField txt_reloj_aplic = new JTextField(10);
	private JTextField txt_comandos = new JTextField(38);
	private JTextField txt_aceleracion_cicli = new JTextField(10);
	private JTextField txt_aceleracion_vient = new JTextField(10);
	private JTextField txt_frecuencia_ej = new JTextField(5);
	
	private JTextArea log = new JTextArea(10, 38);
	
	private JButton btn_enviar = new JButton("Enviar");
	private JButton btn_start_stop = new JButton("Start");
	private JButton btn_disminuyevelocidad = new JButton("<<");
	private JButton btn_aumentavelocidad = new JButton(">>");
	
	
	//---------------------getters and setters--------------------------------
	

	/**
	 * @param txt_reloj_siste the txt_reloj_siste to set
	 */
	public void setTxt_reloj_siste(JTextField txt_reloj_siste)
	{
		this.txt_reloj_siste = txt_reloj_siste;
	}

	/**
	 * @return the txt_reloj_siste
	 */
	public JTextField getTxt_reloj_siste()
	{
		return txt_reloj_siste;
	}

	/**
	 * @param txt_reloj_aplic the txt_reloj_aplic to set
	 */
	public void setTxt_reloj_aplic(JTextField txt_reloj_aplic)
	{
		this.txt_reloj_aplic = txt_reloj_aplic;
	}

	/**
	 * @return the txt_reloj_aplic
	 */
	public JTextField getTxt_reloj_aplic()
	{
		return txt_reloj_aplic;
	}

	/**
	 * @param txt_comandos the txt_comandos to set
	 */
	public void setTxt_comandos(JTextField txt_comandos)
	{
		this.txt_comandos = txt_comandos;
	}

	/**
	 * @return the txt_comandos
	 */
	public JTextField getTxt_comandos()
	{
		return txt_comandos;
	}

	/**
	 * @param txt_aceleracion_cicli the txt_aceleracion_cicli to set
	 */
	public void setTxt_aceleracion_cicli(JTextField txt_aceleracion_cicli)
	{
		this.txt_aceleracion_cicli = txt_aceleracion_cicli;
	}

	/**
	 * @return the txt_aceleracion_cicli
	 */
	public JTextField getTxt_aceleracion_cicli()
	{
		return txt_aceleracion_cicli;
	}

	/**
	 * @param txt_aceleracion_vient the txt_aceleracion_vient to set
	 */
	public void setTxt_aceleracion_vient(JTextField txt_aceleracion_vient)
	{
		this.txt_aceleracion_vient = txt_aceleracion_vient;
	}

	/**
	 * @return the txt_aceleracion_vient
	 */
	public JTextField getTxt_aceleracion_vient()
	{
		return txt_aceleracion_vient;
	}

	/**
	 * @return the txt_frecuencia_ej
	 */
	public JTextField getTxt_frecuencia_ej() {
		return txt_frecuencia_ej;
	}

	/**
	 * @param txt_frecuencia_ej the txt_frecuencia_ej to set
	 */
	public void setTxt_frecuencia_ej(JTextField txt_frecuencia_ej) {
		this.txt_frecuencia_ej = txt_frecuencia_ej;
	}
	
	/**
	 * @param log the log to set
	 */
	public void setLog(JTextArea log)
	{
		this.log = log;
	}

	/**
	 * @return the log
	 */
	public JTextArea getLog()
	{
		return log;
	}

	/**
	 * @param btn_enviar the btn_enviar to set
	 */
	public void setBtn_enviar(JButton btn_enviar)
	{
		this.btn_enviar = btn_enviar;
	}

	/**
	 * @return the btn_enviar
	 */
	public JButton getBtn_enviar()
	{
		return btn_enviar;
	}

	/**
	 * @return the btn_start_stop
	 */
	public JButton getBtn_Start_Stop()
	{
		return btn_start_stop;
	}

	/**
	 * @param btn_start_stop the btn_start_stop to set
	 */
	public void setBtn_Start_Stop(JButton btn_start_stop)
	{
		this.btn_start_stop = btn_start_stop;
	}

	/**
	 * @return the btn_disminuyevelocidad
	 */
	public JButton getBtn_disminuyevelocidad()
	{
		return btn_disminuyevelocidad;
	}

	/**
	 * @param btn_disminuyevelocidad the btn_disminuyevelocidad to set
	 */
	public void setBtn_disminuyevelocidad(JButton btn_disminuyevelocidad)
	{
		this.btn_disminuyevelocidad = btn_disminuyevelocidad;
	}

	/**
	 * @return the btn_aumentavelocidad
	 */
	public JButton getBtn_aumentavelocidad()
	{
		return btn_aumentavelocidad;
	}

	/**
	 * @param btn_aumentavelocidad the btn_aumentavelocidad to set
	 */
	public void setBtn_aumentavelocidad(JButton btn_aumentavelocidad)
	{
		this.btn_aumentavelocidad = btn_aumentavelocidad;
	}


}
