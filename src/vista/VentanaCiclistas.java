package vista;

import java.awt.*;
import javax.swing.*;

import ciclista.Ciclista;

import controlador.ControladorCiclistas;
import factoresexternos.MapaCurvas;
import factoresexternos.MapaPendiente;

import java.util.ArrayList;

import principal.Globales;
import interfaces.InterfaceVistaCiclistas;

/**
 * Clase que forma la vista principal de la aplicación, en la que se ven los ciclistas
 * con sus estados y controles.
 * Esta forma parte del modelo vista controlador (MVC) como vista y se relaciona con el controlador.
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
@SuppressWarnings("serial")
public class VentanaCiclistas extends JFrame implements InterfaceVistaCiclistas {
	
	private Globales globales = Globales.getInstance();

	private JPanel pnl_graficocarrera = new JPanel(); 
	private JPanel pnl_datoscarrera = new JPanel(new GridLayout(0,2));
	private ArrayList<JPanel> listapnl_datosciclista = new ArrayList <JPanel>();
	
	private DibujoCarrera cnv_carrera;
	
	private ArrayList<StructDatosCiclista> listastruct_ciclista = new ArrayList <StructDatosCiclista>();  //estructura que contiene todos los componentes del ciclista, para poder crearlos en tiempo ejecución

	private ArrayList<Ciclista> listaciclistas;
	
	private MapaPendiente pendiente; 
		
	/**
	 * Constructor de la clase, que forma toda la ventana principal de la aplicación,
	 * Con un Frame principal en el que se van insertando los distintos componentes y se les van
	 * añadiendo sus respectivos controladores.
	 */
	public VentanaCiclistas(ArrayList<Ciclista> listaciclistas, MapaPendiente mapapendiente, MapaCurvas curvas)
	{
		super("Carrera de Ciclistas, realizado por Jesús Martínez Dotor y María Picado Álvarez");
		
		this.listaciclistas = listaciclistas;
		pendiente = mapapendiente;
			//Marco con el dibujo de la carrera
		cnv_carrera = new DibujoCarrera(listaciclistas, pendiente, curvas);
			//Panel que contendrá al resto de paneles
		JPanel pnl_contenedor = new JPanel(new BorderLayout(10, 10)); 
							
		inicializarArrayLists();
		
		int i=0;
		for(Ciclista c: listaciclistas)
		{		
				//Panel de los componentes visuales del ciclista
			JPanel panelciclista = listapnl_datosciclista.get(i);
				//Structura con los componentes visuales del ciclista actual
			StructDatosCiclista structdatos = listastruct_ciclista.get(i);
				//Panel de 1 fila de los controles del ciclista
			JPanel pnl_fila;
			
			panelciclista.setLayout(new GridLayout(0,1));
			
			//Ahora se van añadiendo todos los componentes del ciclista FILA a FILA
			//Y tras completar una fila, esa fila se agrega al panelciclista para seguir con otra
			
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, new JLabel(), "Ciclista: ", null);
			addComponenteEnPanel(pnl_fila, structdatos.getLbl_nombre(), c.getNombre(), null);
			addComponenteEnPanel(pnl_fila, structdatos.getLbl_numero(), " - " + Integer.toString(c.getNumeroCiclista()) + " - ", null);
			panelciclista.add(pnl_fila);
							
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, new JLabel(), "Fuerza: ", null);
			addComponenteEnPanel(pnl_fila, structdatos.getPgr_fuerza(), (int)globales.getMAX_FUERZA_CICLISTA(), Double.toString(c.getFuerzaactual()*100/c.getFuerzamaxima()) + " %", structdatos.getArraylist_componentes());				
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_fuerzamenos(), new Dimension(45, 20), FUERZAMENOS, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_fuerzamas(), new Dimension(45, 20), FUERZAMAS, structdatos.getArraylist_componentes());
			panelciclista.add(pnl_fila);
			
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, new JLabel(), "Cadencia (ped/s): ", null);
			addComponenteEnPanel(pnl_fila, structdatos.getTxt_cadencia(), 3, Integer.toString(c.getCadencia()), CADENCIATXT, true, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_cadenciamenos(), new Dimension(45, 20), CADENCIAMENOS, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_cadenciamas(), new Dimension(45, 20), CADENCIAMAS, structdatos.getArraylist_componentes());
			panelciclista.add(pnl_fila);
			
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, new JLabel(), "Tiempo Pedalada (s): ", null);
			addComponenteEnPanel(pnl_fila, structdatos.getTxt_pedalada(), 3, Double.toString(c.getTiempoPedalada()), PEDALADATXT, true, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_pedaladamenos(), new Dimension(45, 20), PEDALADAMENOS, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_pedaladamas(), new Dimension(45, 20), PEDALADAMAS, structdatos.getArraylist_componentes());
			panelciclista.add(pnl_fila);
			
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, new JLabel(), "Plato: ", null);
			addComponenteEnPanel(pnl_fila, structdatos.getTxt_plato(), 3, Integer.toString(c.getBici().getPlatoSeleccionado()), PLATOTXT, true, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getTxt_dientes_plato(), 6, Integer.toString(c.getBici().getDientesPlatos(c.getBici().getPlatoSeleccionado())) + " dientes", null, false, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_platomenos(), new Dimension(45, 20), PLATOMENOS, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_platomas(), new Dimension(45, 20), PLATOMAS, structdatos.getArraylist_componentes());
			panelciclista.add(pnl_fila);
			
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, new JLabel(), "Piñon: ", null);
			addComponenteEnPanel(pnl_fila, structdatos.getTxt_pinon(), 3, Integer.toString(c.getBici().getPinonSeleccionado()), PINONTXT, true, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getTxt_dientes_pinon(), 6, Integer.toString(c.getBici().getDientesPinones(c.getBici().getPinonSeleccionado())) + " dientes", null, false, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_pinonmenos(), new Dimension(45, 20), PINONMENOS, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_pinonmas(), new Dimension(45, 20), PINONMAS, structdatos.getArraylist_componentes());
			panelciclista.add(pnl_fila);
			
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_frenar(), new Dimension(80, 20), FRENAR, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getTxt_freno(), 3, "1/" + Integer.toString(c.getCantidadfrenada()), null, false, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, new JLabel(), "(de velocidad) ", structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_frenomenos(), new Dimension(45, 20), FRENOMENOS, structdatos.getArraylist_componentes());
			addComponenteEnPanel(pnl_fila, structdatos.getBtn_frenomas(), new Dimension(45, 20), FRENOMAS, structdatos.getArraylist_componentes());
			panelciclista.add(pnl_fila);
			
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, new JLabel(), "Velocidad: ", null);
			addComponenteEnPanel(pnl_fila, structdatos.getLbl_velocidad(), "" + c.getBici().getVelocidadActual(), null);				
			structdatos.getArraylist_componentes().add(structdatos.getLbl_velocidad());
			panelciclista.add(pnl_fila);
			
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, new JLabel(), "Distancia: ", null);
			addComponenteEnPanel(pnl_fila, structdatos.getLbl_kilometro(), "" + c.getBici().getCuentametros(), null);				
			structdatos.getArraylist_componentes().add(structdatos.getLbl_kilometro());
			panelciclista.add(pnl_fila);
			
			pnl_fila = new JPanel(new FlowLayout());
			addComponenteEnPanel(pnl_fila, new JLabel(), "Pendiente: ", null);
			addComponenteEnPanel(pnl_fila, structdatos.getLbl_pendiente(), null, null);				
			structdatos.getArraylist_componentes().add(structdatos.getLbl_pendiente());
			panelciclista.add(pnl_fila);
			
			panelciclista.setBorder(BorderFactory.createEtchedBorder());
			pnl_datoscarrera.add(panelciclista, BorderLayout.SOUTH);
			
			i++;
		}	
		pnl_graficocarrera.setPreferredSize(new Dimension((int)globales.getTAM_DIBUJO_CARRERA_X(), (int)globales.getTAM_DIBUJO_CARRERA_Y()));
		pnl_graficocarrera.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cnv_carrera.setPreferredSize(new Dimension((int)globales.getTAM_DIBUJO_CARRERA_X(), (int)globales.getTAM_DIBUJO_CARRERA_Y()-10));
		pnl_graficocarrera.add(cnv_carrera);
		pnl_contenedor.add(pnl_graficocarrera, BorderLayout.NORTH);
		JScrollPane scroll = new JScrollPane(pnl_datoscarrera);
		scroll.getVerticalScrollBar().setUnitIncrement(15);
		pnl_contenedor.add(scroll);
		pnl_contenedor.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pnl_contenedor.setPreferredSize(new Dimension(globales.getRESOLUCION_X(), globales.getRESOLUCION_Y()));
		getContentPane().add(pnl_contenedor);
	}
	
	/**
	 * Asigna las porpiedades básicas al JFrame de la clase, y hace que comience su ejecución
	 */
	@Override
	public void arranca() 
	{
		pack();// coloca y comprime los componentes
		setMinimumSize(new Dimension(globales.getRESOLUCION_MIN_X(), globales.getRESOLUCION_MIN_Y())); //valores dados a ojímetro
		setLocationRelativeTo(null);// centra la ventana en la pantalla
		setVisible(true);// visualiza la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //hace que por defecto, al pulsar X, se cierre la app
	}
	
	/**
	 * Actualiza todos los campos de la vista.
	 * Los componentes por los que se pueden introducir datos no se actualizan si tienen el foco.
	 * Además, si el ciclista ha muerto, se desactivan sus controles.
	 */
	@Override
	public void actualizaVista()
	{
		for(Ciclista c: listaciclistas)
		{			
			//para redondear la estructura se emplea: (double)Math.round(valor * 1000) / 1000.0);
			int num = c.getNumeroCiclista();
			setFuerza(num, c.getFuerzaactual(), c.getFuerzamaxima());  
			setFreno(num, c.getCantidadfrenada());
			setVelocidad(num, (double)Math.round(c.getBici().getVelocidadActual() * 1000) / 1000.0);
			setKilometro(num, (double)Math.round(c.getBici().getCuentametros()*Globales.getInstance().getMETROS_A_KILOMETROS() * 1000) / 1000.0);
			setPendiente(num, pendiente.getPendiente(c.getBici().getCuentametros()*Globales.getInstance().getMETROS_A_KILOMETROS()));
			
			//estos controles pueden introducir datos, por lo que solo se actualizan si no tienen el foco
			setCadencia(num, c.getCadencia());
			setPedalada(num, c.getTiempoPedalada());
			setPlato(num, c.getBici().getPlatoSeleccionado());
			setPinon(num, c.getBici().getPinonSeleccionado());			
			
			if (!c.isVivo())
			{
				desactivaComponentes(c);
			}
		}
		
		getCnv_carrera().update(getCnv_carrera().getGraphics()); //actualiza el gráfico de la carrera
	}
	
	/**
	 * Asigna un controlador a todos los controles de un determinado ciclista.
	 */
	@Override
	public void setControlador(int numero_cilista, ControladorCiclistas c)
	{
		StructDatosCiclista structdatos = listastruct_ciclista.get(numero_cilista);
		
		for (JComponent comp: structdatos.getArraylist_componentes())
		{
			if (comp instanceof JButton)
					((AbstractButton) comp).addActionListener(c);
			else if (comp instanceof JFormattedTextField)
					((JTextField) comp).addActionListener(c);
		}
		//para evitar toda esta lista de addActionListener, empleamos el operador instanceof
		/*
		structdatos.getBtn_fuerzamas().addActionListener(c);
		structdatos.getBtn_fuerzamenos().addActionListener(c);
		structdatos.getBtn_cadenciamas().addActionListener(c);
		structdatos.getBtn_cadenciamenos().addActionListener(c);
		structdatos.getTxt_cadencia().addActionListener(c);
		structdatos.getBtn_pedaladamas().addActionListener(c);
		structdatos.getBtn_pedaladamenos().addActionListener(c);
		structdatos.getTxt_pedalada().addActionListener(c);
		structdatos.getBtn_platomas().addActionListener(c);
		structdatos.getBtn_platomenos().addActionListener(c);
		structdatos.getTxt_plato().addActionListener(c);
		structdatos.getBtn_pinonmas().addActionListener(c);
		structdatos.getBtn_pinonmenos().addActionListener(c);
		structdatos.getTxt_pinon().addActionListener(c);
		structdatos.getBtn_frenar().addActionListener(c);
		structdatos.getTxt_freno().addActionListener(c);
		structdatos.getBtn_frenomas().addActionListener(c);
		structdatos.getBtn_frenomenos().addActionListener(c);
		*/
	}
	
	/**
	 * Desactiva todos los componentes de un determinado ciclista
	 * @param c Ciclista del que queremos desactivar los componentes
	 */
	private void desactivaComponentes(Ciclista c)
	{
		StructDatosCiclista estructuraciclista = listastruct_ciclista.get(c.getNumeroCiclista());
		ArrayList<JComponent> componentes = estructuraciclista.getArraylist_componentes();

		for(JComponent comp: componentes) //se desactivan todos los componentes
		{
			comp.setEnabled(false);
		}
	}

	/**
	 * Inicializa 2 ArrayList con :
	 * Uno con un panel que contendrá todos los componentes de cada cilcista.
	 * Otro con una estructura de los componentes.
	 */
	private void inicializarArrayLists()
	{
		for(@SuppressWarnings("unused") Ciclista c: listaciclistas) //inicializa un panel y una StructDatosCiclista por cada ciclista
		{
			JPanel pnl_datosciclista = new JPanel(); //panel que contendrá los datos del ciclista
			listapnl_datosciclista.add(pnl_datosciclista);

			StructDatosCiclista str_ciclista = new StructDatosCiclista(); //estrucutras de componenentes visuales de los ciclistas
			listastruct_ciclista.add(str_ciclista);
		}		
	}
	
	/**
	 * Añade un JLabel a un JPanel, asignándole el nombre indicado en "text"
	 * Además agrega este componente a un ArrayList de componentes para que puedan ser desactivados con facilidad
	 * @param panel Panel en el que se quiere agregar el JLabel
	 * @param label Label que se quiere agregar
	 * @param text Texto asociado al JLabel
	 * @param list ArrayList en el que se quiere agregar el JLabel
	 */
	public void addComponenteEnPanel(JPanel panel, JLabel label, String text, ArrayList<JComponent> list)
	{
		label.setText(text);
		addComponenteEnPanelLista(panel, label, list);
	}
	
	/**
	 * Añade un JProgressBar, con un cierto valor y texto.
	 * Además agrega este componente a un ArrayList de componentes para que puedan ser desactivados con facilidad
	 * @param panel Panel en el que se quiere agregar el JLabel
	 * @param bar JProgressBar que se quiere agregar
	 * @param value Valor máximo del ProgressBar
	 * @param text Texto asociado al JLabel
	 * @param list ArrayList en el que se quiere agregar el JProgressBar
	 */
	public void addComponenteEnPanel(JPanel panel, JProgressBar bar, int value, String text, ArrayList<JComponent> list)
	{
		bar.setValue(value);
		bar.setString(text);
		bar.setStringPainted(true);
		addComponenteEnPanelLista(panel, bar, null);
	}
	
	/**
	 * Añade un JButton, de unas ciertas dimensiones, asignándole un ActionCommand.
	 * Además agrega este componente a un ArrayList de componentes para que puedan ser desactivados con facilidad
	 * @param panel Panel en el que se quiere agregar el JButton
	 * @param button JButton que se quiere agregar
	 * @param size Dimensiones del botón
	 * @param actioncommand String con el ActionCommand
	 * @param list ArrayList en el que se quiere agregar el JButton
	 */
	public void addComponenteEnPanel(JPanel panel, JButton button, Dimension size, String actioncommand, ArrayList<JComponent> list)
	{
		button.setPreferredSize(size);
		button.setActionCommand(actioncommand);
		addComponenteEnPanelLista(panel, button, list);
	}
	
	/**
	 * Añade un componente a un cierto panel y a una lista de componentes
	 * @param panel Panel en el que se quiere agregar el componente
	 * @param component JComponent que se quiere agregar
	 * @param listArrayList en el que se quiere agregar el componente
	 */
	public void addComponenteEnPanelLista(JPanel panel, JComponent component, ArrayList<JComponent> list)
	{
		panel.add(component);
		if (list != null)
			list.add(component);
	}
	
	/**
	 * Añade un JTextField, de unas ciertas columnas, asignándole un texto y un ActionCommand.
	 * Además agrega este componente a un ArrayList de componentes para que puedan ser desactivados con facilidad
	 * @param panel Panel en el que se quiere agregar el JTextField
	 * @param txt JTextField que se quiere agregar
	 * @param columns Número de columnas de ancho
	 * @param text Texto por defecto
	 * @param actioncommand String con el ActionCommand
	 * @param editable Si se quiere que el txt se pueda modificar
	 * @param list ArrayList en el que se quiere agregar el JTextField
	 */
	public void addComponenteEnPanel(JPanel panel, JTextField  txt, int columns, String text, String actioncommand, boolean editable, ArrayList<JComponent> list)
	{
		txt.setColumns(columns);
		txt.setText(text);
		txt.setEditable(editable);
		txt.setActionCommand(actioncommand);
		panel.add(txt);
		list.add(txt);
	}
	
	
	//---------------------getters and setters--------------------------------
	

	public DibujoCarrera getCnv_carrera()
	{
		return cnv_carrera;
	}
	
	@Override
	public void setFuerza(int numero_ciclista, double fuerza, double fuerzamaxima)
	{
		listastruct_ciclista.get(numero_ciclista).getPgr_fuerza().setValue((int)fuerza);	
		listastruct_ciclista.get(numero_ciclista).getPgr_fuerza().setString(Double.toString((double)Math.round((fuerza*100/fuerzamaxima) * 1000) / 1000.0) + " %"); 
	}

	@Override
	public void setCadencia(int numero_ciclista, int cadencia)
	{
		if (!listastruct_ciclista.get(numero_ciclista).getTxt_cadencia().isFocusOwner())
			listastruct_ciclista.get(numero_ciclista).getTxt_cadencia().setValue(cadencia);		
	}

	@Override
	public int getCadencia(int numero_ciclista)
	{
		return Integer.parseInt(listastruct_ciclista.get(numero_ciclista).getTxt_cadencia().getText());
	}

	@Override
	public void setPedalada(int numero_ciclista, double pedalada)
	{
		if (!listastruct_ciclista.get(numero_ciclista).getTxt_pedalada().isFocusOwner())
			listastruct_ciclista.get(numero_ciclista).getTxt_pedalada().setValue(pedalada);	
	}

	@Override
	public double getPedalada(int numero_ciclista)
	{
		return Double.parseDouble(listastruct_ciclista.get(numero_ciclista).getTxt_pedalada().getText());		
	}

	@Override
	public void setPlato(int numero_ciclista, int plato)
	{
		if (!listastruct_ciclista.get(numero_ciclista).getTxt_plato().isFocusOwner())
			listastruct_ciclista.get(numero_ciclista).getTxt_plato().setValue(plato);
		listastruct_ciclista.get(numero_ciclista).getTxt_dientes_plato().setText(
				Integer.toString(listaciclistas.get(numero_ciclista).getBici().getDientesPlatos(plato)) + " dientes");
	}

	@Override
	public int getPlato(int numero_ciclista)
	{
		return Integer.parseInt(listastruct_ciclista.get(numero_ciclista).getTxt_plato().getText());
	}

	@Override
	public void setPinon(int numero_ciclista, int pinon)
	{
		if (!listastruct_ciclista.get(numero_ciclista).getTxt_pinon().isFocusOwner())
			listastruct_ciclista.get(numero_ciclista).getTxt_pinon().setValue(pinon);
		listastruct_ciclista.get(numero_ciclista).getTxt_dientes_pinon().setText(
				Integer.toString(listaciclistas.get(numero_ciclista).getBici().getDientesPinones(pinon)) + " dientes");
	}

	@Override
	public int getPinon(int numero_ciclista)
	{
		return Integer.parseInt(listastruct_ciclista.get(numero_ciclista).getTxt_pinon().getText());
	}
	
	@Override
	public void setFreno(int numero_ciclista, int freno)
	{
		listastruct_ciclista.get(numero_ciclista).getTxt_freno().setText("1/" + Integer.toString(freno));
	}

	@Override
	public double getFreno(int numero_ciclista)
	{
		return Double.parseDouble(listastruct_ciclista.get(numero_ciclista).getTxt_freno().getText().substring(2));
	}

	@Override
	public void setVelocidad(int numero_ciclista, double velocidad)
	{
		listastruct_ciclista.get(numero_ciclista).getLbl_velocidad().setText(Double.toString(velocidad) + " (m/s)");
	}

	@Override
	public void setKilometro(int numero_ciclista, double kilometro)
	{
		listastruct_ciclista.get(numero_ciclista).getLbl_kilometro().setText(Double.toString(kilometro) + " (Km)");
	}

	@Override
	public void setPendiente(int numero_ciclista, double pendiente)
	{
		listastruct_ciclista.get(numero_ciclista).getLbl_pendiente().setText(Double.toString(pendiente) + " %");
	}
	
}