package vista;

import java.awt.*;

import javax.swing.*;

import ciclista.Ciclista;

import principal.Globales;
import principal.Presentador;
import controlador.ControladorAplicacion;
import factoresexternos.MapaViento;
import fecha.Fecha;
import interfaces.InterfaceVistaAuxiliar;

/**
 * Clase que forma la vista auxiliar de la aplicación, en la que se ve el log, 
 * la entrada de comandos, el control de velocidad y algunos datos extra.
 * Esta forma parte del modelo vista controlador (MVC) como vista y se relaciona con el controlador.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
@SuppressWarnings("serial")
public class VentanaDatos extends JFrame implements InterfaceVistaAuxiliar {

	private JFrame frame;
	
	private JPanel pnl_comandos;
	private JPanel pnl_login;
	private JPanel pnl_datos;
	
	private Ciclista modelo_ciclista;	
	private MapaViento viento;

	private StructDatosAuxiliar listastr_datos = new StructDatosAuxiliar();  //estructura que contiene todos los componentes dela vista de Datos
	
	public VentanaDatos(Ciclista ciclista, MapaViento viento)
	{
		pnl_comandos = construyePanelComandos();
		pnl_login = construyePanelLog();
		pnl_datos = construyePanelDatos();	
		
		modelo_ciclista = ciclista;
		this.viento = viento;
	}
	
	/**
	 * Contruye la parte del panel de introducir comandos
	 * @return JPanel
	 */
	JPanel construyePanelComandos()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());

		panel.add(new JLabel("COMANDOS (\"help\" para ayuda): "));	
		
		listastr_datos.getTxt_comandos().setActionCommand(InterfaceVistaAuxiliar.ENVIAR);
		panel.add(listastr_datos.getTxt_comandos());
		listastr_datos.getBtn_enviar().setActionCommand(InterfaceVistaAuxiliar.ENVIAR);
		panel.add(listastr_datos.getBtn_enviar());
		
		return panel;
	}
	
	/**
	 * Contruye la parte del panel para mostrar el log
	 * @return JPanel
	 */
	JPanel construyePanelLog()
	{
		JPanel panel = new JPanel();		
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		panel.add(new JLabel("LOG: "));
		
		listastr_datos.getLog().setEditable(false);
		panel.add(new JScrollPane(listastr_datos.getLog()));
		
		return panel;
	}
	
	/**
	 * Contruye un panel con FlowLayout
	 * @return JPanel
	 */
	JPanel construyePanelFlow()
	{
		JPanel panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		return panel;
	}
	
	/**
	 * Contruye el panel central, en el que se muestran todos los datos 
	 * @return JPanel
	 */
	JPanel construyePanelDatos()
	{
		JPanel panel = new JPanel(); 
		JPanel pnl_flow; //panel de cada una de las filas
		
		int COLUMNAS = 6; //5
		int FILAS = 2; //3
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(FILAS, COLUMNAS, 10,10));
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		pnl_flow = construyePanelFlow();
		pnl_flow.add(new JLabel("Reloj del sistema "));		
		listastr_datos.getTxt_reloj_siste().setName("txt_reloj_siste");
		listastr_datos.getTxt_reloj_siste().setEditable(false);		
		pnl_flow.add(listastr_datos.getTxt_reloj_siste());
		panel.add(pnl_flow);
		
		pnl_flow = construyePanelFlow();
		pnl_flow.add(new JLabel("Reloj de la aplicacion"));		
		listastr_datos.getTxt_reloj_aplic().setName("txt_reloj_aplic");
		pnl_flow.add(listastr_datos.getTxt_reloj_aplic());
		panel.add(pnl_flow);		
			
		pnl_flow = construyePanelFlow();
		pnl_flow.add(new JLabel("Aceleracion del viento (m/s2)"));		
		listastr_datos.getTxt_aceleracion_vient().setName("txt_aceleracion_vient");		
		pnl_flow.add(listastr_datos.getTxt_aceleracion_vient());
		panel.add(pnl_flow);
		
		pnl_flow = construyePanelFlow();
		listastr_datos.getBtn_disminuyevelocidad().setActionCommand(InterfaceVistaAuxiliar.MAS_LENTO);
		pnl_flow.add(listastr_datos.getBtn_disminuyevelocidad());
		listastr_datos.getBtn_Start_Stop().setActionCommand(InterfaceVistaAuxiliar.START_STOP);
		pnl_flow.add(listastr_datos.getBtn_Start_Stop());
		listastr_datos.getBtn_aumentavelocidad().setActionCommand(InterfaceVistaAuxiliar.MAS_RAPIDO);
		pnl_flow.add(listastr_datos.getBtn_aumentavelocidad());
		pnl_flow.add(new JLabel("frecuencia: "));
		pnl_flow.add(listastr_datos.getTxt_frecuencia_ej());
		panel.add(pnl_flow);
		
		return panel;
	}
	
	/**
	 * Asigna las porpiedades básicas al JFrame de la clase, y hace que comience su ejecución
	 */
	@Override
	public void arranca() 
	{
		frame = new JFrame("Datos Auxiliares");
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));  
        frame.add(pnl_comandos);
        frame.add(pnl_login);
        frame.add(pnl_datos);
        frame.pack();
        frame.setSize(new Dimension(450,500));
        frame.setMinimumSize(new Dimension(450,500));
        frame.setLocationRelativeTo(null);// centra la ventana en la pantalla
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Actualiza todos los campos de la vista.
	 * Actualiza todos los campos incluso si la ejecución está detenida.
	 */
	@Override
	public void actualizaVista()
	{
		double aceleracionviento = (double)Math.round(viento.getViento(modelo_ciclista.getReloj().getTiempo()) * 1000000000) / 1000000000.0;
		escribeDatos(Double.toString(aceleracionviento), getListaDatos().getTxt_aceleracion_vient());
		escribeDatos((modelo_ciclista.getReloj().getTiempo()), getListaDatos().getTxt_reloj_aplic());
		escribeDatos(new Fecha().getHoraActual(), getListaDatos().getTxt_reloj_siste());
		escribeDatos(Integer.toString(Globales.getInstance().getFRECUENCIA_EJECUCION()), getListaDatos().getTxt_frecuencia_ej());
		escribeDatos(Presentador.getInstance().getLog(), getListaDatos().getLog());
	}
	
	/**
	 * Asigna un controlador a todos los controles de la ventana.
	 */
	@Override
	public void setControlador(ControladorAplicacion c)
	{

		listastr_datos.getTxt_comandos().addActionListener(c);
		listastr_datos.getBtn_enviar().addActionListener(c);		
		listastr_datos.getBtn_Start_Stop().addActionListener(c);
		listastr_datos.getBtn_disminuyevelocidad().addActionListener(c);
		listastr_datos.getBtn_aumentavelocidad().addActionListener(c);
	}	
	
	/**
	 * Escribe un cierto String en un JTextField
	 */
	public void escribeDatos(String s, JTextField txt)
	{
		txt.setText(s);
	}
	
	/**
	 * Escribe un cierto String en un JTextArea
	 */
	public void escribeDatos(String s, JTextArea txt)
	{
		txt.setText(s);
	}
	
	/**
	 * Escribe un cierto String en un JTextField
	 */
	public String leeDatos(JTextField txt)
	{
		return txt.getText();
	}
	
	public StructDatosAuxiliar getListaDatos()
	{
		return listastr_datos;
	}
	
}