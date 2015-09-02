package vista;

import java.util.ArrayList;

import javax.swing.*;

import principal.Globales;
				
/**
 * Clase que define la estructura de datos del control del ciclista, es decir, todos los
 * componentes que se muestran en la ventana principal del control de los ciclistas y sus métodos. 
 * De esta manera se reunen todos los componentes en una misma clase, para que sea más fácil hacer referencia a los mismos.
 * 
 * La primera opcion al realizar la ventana Datos era crear todos los JComponent en la propia clase, 
 * pero al trabajar con un MODELO-VISTA-CONTROLADOR, necesitaba acceder a las JComponent desde la clase 
 * controlador. 
 * Para solucionarlo se creó esta structura a la que se pudiera acceder desde los dos sitios.
 * 
 * BIBLIO:
 * http://www.chuidiang.com/java/ejemplos/JFormattedTextField/EjemplosJFormattedTextField.php#integer
 * http://www.froses.com/Assets/Files/Articles/JFormattedTextField.pdf
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class StructDatosCiclista {
	//ArrayList para hacer ciertas acciones con todos los componentes a la vez (ej: enabled, visible...)
	private ArrayList<JComponent> arraylist_componentes = new ArrayList <JComponent>();
	
	private JLabel lbl_nombre = new JLabel("0");
	private JLabel lbl_numero = new JLabel("0");	
	private JProgressBar pgr_fuerza = new JProgressBar(0, (int)Globales.getInstance().getMAX_FUERZA_CICLISTA()); 	
	private JButton btn_fuerzamas = new JButton("+");
	private JButton btn_fuerzamenos = new JButton("-");
	private JFormattedTextField txt_cadencia = new JFormattedTextField(new Integer(0));
	private JButton btn_cadenciamas = new JButton("+");
	private JButton btn_cadenciamenos = new JButton("-");
	private JFormattedTextField txt_pedalada = new JFormattedTextField(new Double(0)); 
	private JButton btn_pedaladamas = new JButton("+");
	private JButton btn_pedaladamenos = new JButton("-");
	private JFormattedTextField txt_plato = new JFormattedTextField(new Integer(0));
	private JTextField txt_dientes_plato = new JTextField();
	private JButton btn_platomas = new JButton("+");
	private JButton btn_platomenos = new JButton("-");
	private JFormattedTextField txt_pinon = new JFormattedTextField(new Integer(0));
	private JTextField txt_dientes_pinon = new JTextField();
	private JButton btn_pinonmas = new JButton("+");
	private JButton btn_pinonmenos = new JButton("-");
	private JButton btn_frenar = new JButton("FRENAR");
	private JTextField txt_freno = new JTextField();
	private JButton btn_frenomas = new JButton("+");
	private JButton btn_frenomenos = new JButton("-");
	private JLabel lbl_velocidad = new JLabel();
	private JLabel lbl_kilometro = new JLabel();
	private JLabel lbl_pendiente = new JLabel();

	
	//---------------------getters and setters--------------------------------
	

	/**
	 * @return the lbl_nombre
	 */
	public JLabel getLbl_nombre()
	{
		return lbl_nombre;
	}

	/**
	 * @param lbl_nombre the lbl_nombre to set
	 */
	public void setLbl_nombre(JLabel lbl_nombre)
	{
		this.lbl_nombre = lbl_nombre;
	}
	
	/**
	 * @return the lbl_numero
	 */
	public JLabel getLbl_numero()
	{
		return lbl_numero;
	}

	/**
	 * @param lbl_numero the lbl_numero to set
	 */
	public void setLbl_numero(JLabel lbl_numero)
	{
		this.lbl_numero = lbl_numero;
	}
	
	/**
	 * @return the pgr_fuerza
	 */
	public JProgressBar getPgr_fuerza()
	{
		return pgr_fuerza;
	}
	
	/**
	 * @param pgr_fuerza the pgr_fuerza to set
	 */
	public void setPgr_fuerza(JProgressBar pgr_fuerza) 
	{
		this.pgr_fuerza = pgr_fuerza;
	}

	/**
	 * @return the btn_fuerzamas
	 */
	public JButton getBtn_fuerzamas()
	{
		return btn_fuerzamas;
	}

	/**
	 * @param btn_fuerzamas the btn_fuerzamas to set
	 */
	public void setBtn_fuerzamas(JButton btn_fuerzamas)
	{
		this.btn_fuerzamas = btn_fuerzamas;
	}

	/**
	 * @return the btn_fuerzamenos
	 */
	public JButton getBtn_fuerzamenos()
	{
		return btn_fuerzamenos;
	}

	/**
	 * @param btn_fuerzamenos the btn_fuerzamenos to set
	 */
	public void setBtn_fuerzamenos(JButton btn_fuerzamenos)
	{
		this.btn_fuerzamenos = btn_fuerzamenos;
	}

	/**
	 * @return the txt_cadencia
	 */
	public JFormattedTextField getTxt_cadencia()
	{
		return txt_cadencia;
	}

	/**
	 * @param txt_cadencia the txt_cadencia to set
	 */
	public void setTxt_cadencia(JFormattedTextField txt_cadencia)
	{
		this.txt_cadencia = txt_cadencia;
	}

	/**
	 * @return the btn_cadenciamas
	 */
	public JButton getBtn_cadenciamas()
	{
		return btn_cadenciamas;
	}

	/**
	 * @param btn_cadenciamas the btn_cadenciamas to set
	 */
	public void setBtn_cadenciamas(JButton btn_cadenciamas)
	{
		this.btn_cadenciamas = btn_cadenciamas;
	}

	/**
	 * @return the btn_cadenciamenos
	 */
	public JButton getBtn_cadenciamenos()
	{
		return btn_cadenciamenos;
	}

	/**
	 * @param btn_cadenciamenos the btn_cadenciamenos to set
	 */
	public void setBtn_cadenciamenos(JButton btn_cadenciamenos)
	{
		this.btn_cadenciamenos = btn_cadenciamenos;
	}

	/**
	 * @return the txt_pedalada
	 */
	public JFormattedTextField getTxt_pedalada()
	{
		return txt_pedalada;
	}

	/**
	 * @param txt_pedalada the txt_pedalada to set
	 */
	public void setTxt_pedalada(JFormattedTextField txt_pedalada)
	{
		this.txt_pedalada = txt_pedalada;
	}

	/**
	 * @return the btn_pedaladamas
	 */
	public JButton getBtn_pedaladamas()
	{
		return btn_pedaladamas;
	}

	/**
	 * @param btn_pedaladamas the btn_pedaladamas to set
	 */
	public void setBtn_pedaladamas(JButton btn_pedaladamas)
	{
		this.btn_pedaladamas = btn_pedaladamas;
	}

	/**
	 * @return the btn_pedaladamenos
	 */
	public JButton getBtn_pedaladamenos()
	{
		return btn_pedaladamenos;
	}

	/**
	 * @param btn_pedaladamenos the btn_pedaladamenos to set
	 */
	public void setBtn_pedaladamenos(JButton btn_pedaladamenos)
	{
		this.btn_pedaladamenos = btn_pedaladamenos;
	}

	/**
	 * @return the txt_plato
	 */
	public JFormattedTextField getTxt_plato()
	{
		return txt_plato;
	}

	/**
	 * @param txt_plato the txt_plato to set
	 */
	public void setTxt_plato(JFormattedTextField txt_plato)
	{
		this.txt_plato = txt_plato;
	}

	/**
	 * @return the txt_dientes_plato
	 */
	public JTextField getTxt_dientes_plato()
	{
		return txt_dientes_plato;
	}

	/**
	 * @return the btn_platomas
	 */
	public JButton getBtn_platomas()
	{
		return btn_platomas;
	}

	/**
	 * @param btn_platomas the btn_platomas to set
	 */
	public void setBtn_platomas(JButton btn_platomas)
	{
		this.btn_platomas = btn_platomas;
	}

	/**
	 * @return the btn_platomenos
	 */
	public JButton getBtn_platomenos()
	{
		return btn_platomenos;
	}

	/**
	 * @param btn_platomenos the btn_platomenos to set
	 */
	public void setBtn_platomenos(JButton btn_platomenos)
	{
		this.btn_platomenos = btn_platomenos;
	}

	/**
	 * @return the txt_pinon
	 */
	public JFormattedTextField getTxt_pinon()
	{
		return txt_pinon;
	}

	/**
	 * @param txt_pinon the txt_pinon to set
	 */
	public void setTxt_pinon(JFormattedTextField txt_pinon)
	{
		this.txt_pinon = txt_pinon;
	}

	/**
	 * @return the txt_dientes_pinon
	 */
	public JTextField getTxt_dientes_pinon()
	{
		return txt_dientes_pinon;
	}
	/**
	 * @return the btn_pinonmas
	 */
	public JButton getBtn_pinonmas()
	{
		return btn_pinonmas;
	}

	/**
	 * @param btn_pinonmas the btn_pinonmas to set
	 */
	public void setBtn_pinonmas(JButton btn_pinonmas)
	{
		this.btn_pinonmas = btn_pinonmas;
	}

	/**
	 * @return the btn_pinonmenos
	 */
	public JButton getBtn_pinonmenos()
	{
		return btn_pinonmenos;
	}

	/**
	 * @param btn_pinonmenos the btn_pinonmenos to set
	 */
	public void setBtn_pinonmenos(JButton btn_pinonmenos)
	{
		this.btn_pinonmenos = btn_pinonmenos;
	}
	
	/**
	 * @return the btn_frenar
	 */
	public JButton getBtn_frenar()
	{
		return btn_frenar;
	}

	/**
	 * @param btn_frenar the btn_frenar to set
	 */
	public void setBtn_frenar(JButton btn_frenar)
	{
		this.btn_frenar = btn_frenar;
	}

	/**
	 * @return the txt_freno
	 */
	public JTextField getTxt_freno()
	{
		return txt_freno;
	}

	/**
	 * @param txt_freno the txt_freno to set
	 */
	public void setTxt_freno(JTextField txt_freno)
	{
		this.txt_freno = txt_freno;
	}

	/**
	 * @return the btn_frenomas
	 */
	public JButton getBtn_frenomas()
	{
		return btn_frenomas;
	}

	/**
	 * @param btn_frenomas the btn_frenomas to set
	 */
	public void setBtn_frenomas(JButton btn_frenomas)
	{
		this.btn_frenomas = btn_frenomas;
	}

	/**
	 * @return the btn_frenomenos
	 */
	public JButton getBtn_frenomenos()
	{
		return btn_frenomenos;
	}

	/**
	 * @param btn_frenomenos the btn_frenomenos to set
	 */
	public void setBtn_frenomenos(JButton btn_frenomenos)
	{
		this.btn_frenomenos = btn_frenomenos;
	}

	/**
	 * @return the lbl_velocidad
	 */
	public JLabel getLbl_velocidad()
	{
		return lbl_velocidad;
	}

	/**
	 * @param lbl_velocidad the lbl_velocidad to set
	 */
	public void setLbl_velocidad(JLabel lbl_velocidad)
	{
		this.lbl_velocidad = lbl_velocidad;
	}

	/**
	 * @return the lbl_kilometro
	 */
	public JLabel getLbl_kilometro()
	{
		return lbl_kilometro;
	}

	/**
	 * @param lbl_kilometro the lbl_kilometro to set
	 */
	public void setLbl_kilometro(JLabel lbl_kilometro)
	{
		this.lbl_kilometro = lbl_kilometro;
	}

	/**
	 * @return the lbl_pendiente
	 */
	public JLabel getLbl_pendiente()
	{
		return lbl_pendiente;
	}

	/**
	 * @param lbl_pendiente the lbl_pendiente to set
	 */
	public void setLbl_pendiente(JLabel lbl_pendiente)
	{
		this.lbl_pendiente = lbl_pendiente;
	}

	/**
	 * @return the arraylist_componentes
	 */
	public ArrayList<JComponent> getArraylist_componentes()
	{
		return arraylist_componentes;
	}
	
}
