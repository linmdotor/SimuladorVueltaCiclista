package vista;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import ciclista.Ciclista;
import factoresexternos.MapaCurvas;
import factoresexternos.MapaPendiente;
import java.io.File;
import java.util.ArrayList;
import principal.Globales;

/**
 * La clase DibujoCarrera define el objeto de representación de la carrera.
 * Para ello extiende la clase Canvas (marco), que es en la que se pintará la carrera.
 * En el constructor se encarga de inicializar todas las imágenes y componentes, después
 * sobreescribe los métodos update y paint de Canvas, de manera que se optimiza la velocidad
 * y se evitan parpadeos en el refresco del Canvas.
 * 
 * BIBLIO:
 * http://home.cogeco.ca/~ve3ll/jatutorg.htm 
 * http://javafrikitutorials.blogspot.com.es/2011/10/tutorial-para-manipular-imagenes-en.html
 * http://java.sun.com/docs/books/tutorial/download/tut-OLDui.zip
 * http://www.javalobby.org/articles/ultimate-image/#10
 * 
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
@SuppressWarnings("serial")
public class DibujoCarrera extends Canvas{
		
	private Globales globales = Globales.getInstance();
	
	private ArrayList<Ciclista> listaciclistas;
	
	private MapaPendiente mapapendientes;
	private MapaCurvas mapacurvas;
	//dos arrays con los distintos valores de kilómetro y pendiente en cada kilómetro, así como curva y velocidad máxima
	//se emplean para optimizar en velocidad, para no estar recorriendo siempre el Map
	private double kilometros_pendientes[]; 
	private double pendientes[];
	private double kilometros_curvas[];
	private double curvas[];
	//guarda la posición e inclinación en el gráfico de cada ciclista
	private double posicionciclistaX[];
	private double posicionciclistaY[];
	private double inclinacionciclista[];
	//imagenes de los ciclistas
	private BufferedImage imagenciclista;
	private BufferedImage imagenredimensionada;
		
	/**
	 * Constructor de la clase, que inicializa todos los valores necesarios para el pintado de la carrera.
	 * Carga las imágenes y las alamacena para utilizarlas después rápidamente.
	 */
	public DibujoCarrera(ArrayList<Ciclista> listaciclistas, MapaPendiente mapapendiente, MapaCurvas mapacurva)
	{
		this.listaciclistas = listaciclistas;
		posicionciclistaX = new double[listaciclistas.size()];
		posicionciclistaY = new double[listaciclistas.size()];
		inclinacionciclista = new double[listaciclistas.size()];
		
		mapapendientes = mapapendiente;
		kilometros_pendientes = mapapendientes.transformarEnArray(MapaPendiente.CLAVE);
		pendientes = mapapendientes.transformarEnArray(MapaPendiente.VALOR);
		
		mapacurvas = mapacurva;
		kilometros_curvas = mapacurvas.transformarEnArray(MapaCurvas.CLAVE);
		curvas = mapacurvas.transformarEnArray(MapaCurvas.VALOR);
				
		imagenciclista = null;		
		imagenciclista = cargarGif(Globales.getInstance().getRUTA_IMAGEN_CICLISTA());
		Image imagenredimensionadaaux;
		if (imagenciclista != null) 
		{
			imagenredimensionadaaux = imagenciclista.getScaledInstance(imagenciclista.getWidth()/globales.getREDIMENSION_IMG_CICLISTA(), imagenciclista.getHeight()/globales.getREDIMENSION_IMG_CICLISTA(), BufferedImage.SCALE_SMOOTH);
			imagenredimensionada = new BufferedImage(imagenredimensionadaaux.getWidth(null), imagenredimensionadaaux.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			imagenredimensionada.getGraphics().drawImage(imagenredimensionadaaux, 0, 0 , null);
			iniciarPosicionesCiclistas();	
		}
		else //si no ha cargado la imagenciclista, crea una vacía, para que pueda continuar la ejecución
		{
			imagenredimensionadaaux = null;
			imagenredimensionada = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		}	
		
	}
	
	/**
	 * Este método de actualización sobreescribe el método oriinal, llamando directamente al
	 * paint() personalizado de la clase, evitando el borrado del componente y, de esta manera,
	 * evitando el parpadeo en la imagen.
	 */
	public void update (Graphics g)
	{
		paint(g);
	}
	
	/**
	 * Llama a todos los métodos necesarios en el orden correspondiente para realizar el pintado de
	 * la carretera y de todos los ciclistas.
	 * Para ello va pintando en capas, y cada capa se pinta encima de la anterior.
	 * De esta manera lo que es pintado en último lugar queda en primer plano.
	 */
	public void paint (Graphics g)
    {
		//Se crea un BufferedImage y coge el Graphics2D del mismo, que es donde pintaremos. Ya que sólo se puede dibujar sobre un objeto Graphics2D
		BufferedImage buffer = new BufferedImage (getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics gbuffer = buffer.getGraphics();
		
		//Pinta el fondo con color azul
		pintarFondo(gbuffer, new Color(0, 180, 255));
		
		//Pinta la carretera con color marrón
		pintarCarretera(gbuffer, new Color(155, 100, 65));
		
		//Pinta separaciones kilométricas de negro
		pintarSeparacionesKilometricas(gbuffer, Color.BLACK, globales.getSEPARACION_MARCA_KILOMETROS());
		
		//Pinta curvas en la carretera de blanco
		pintarCurvas(gbuffer, Color.WHITE);
		
		//Recalcula las posiciones de los ciclistas en función de lo que han avanzado
		recalcularPosicionesCiclistas();
		
		//Recalcula las inclinaciones de los ciclistas en función del punto donde se encuentran
		calcularInclinacionCiclistas();
		
		//Pinta los ciclsitas en sus posiciones, y sus etiquetas
		pintarCiclistas(gbuffer, Color.BLACK);		
				
		//Actualiza el buffer en el canvas
		g.drawImage(buffer, 0, 0, this);
    }
		
	/**
	 * Dibuja el cielo sobre el buffer (Graphics) que sirve como imagen de fondo.
	 * @param g Graphics sobre el que se pintan todas las imágenes.
	 * @param color Color del fondo
	 */
	private void pintarFondo(Graphics g, Color color)
	{
		g.setColor(color);
		g.fillRect( 0, 0, getWidth() , getHeight());
	}
	
	/**
	 * Dibuja la carretera sobre el buffer (Graphics) que sirve como imagen de fondo.
	 * Para ello calcula la forma de la carretera mediante el método calcularPoligonoCarretera().
	 * @param g Graphics sobre el que se pintan todas las imágenes.
	 * @param color Color de la carretera
	 */
	private void pintarCarretera(Graphics g, Color color)
	{
		//se actualiza el mapa de pendientes por si ha habido algún cambio (ha introducido alguna pendiente por comando)
		kilometros_pendientes = mapapendientes.transformarEnArray(MapaPendiente.CLAVE);
		pendientes = mapapendientes.transformarEnArray(MapaPendiente.VALOR);
		
		g.setColor(color);
		g.fillPolygon(calcularPoligonoCarretera());
	}
	
	/**
	 * Crea un polígono con la forma de la carretera en función de los valores de las distancias e inclinaciones.
	 * Toma como ancho la medida de globales.getTAM_DIBUJO_CARRERA_X().
	 * @return Polígono que representa la carretera
	 */
	private Polygon calcularPoligonoCarretera()
	{
		double tam_carreraX = globales.getTAM_DIBUJO_CARRERA_X();
		int km_carrera = globales.getKILOMETROS_CARRERA();
		double pixels_km = (tam_carreraX/km_carrera); //correspondecia en pixels de 1 kilómetro
		
		Polygon poligono = new Polygon();
		double  punto_X = 0;
        double  punto_Y = globales.getPOS_Y_COMIENZO_CARRERA();
        poligono.addPoint((int) punto_X, (int) punto_Y);
        
		for (int i=0; i<kilometros_pendientes.length-1; i++)
		{	
			punto_X   = kilometros_pendientes[i+1]*pixels_km;
			punto_Y   = punto_Y + ((kilometros_pendientes[i+1]-kilometros_pendientes[i])*pendientes[i]/100)*pixels_km;
			poligono.addPoint((int)punto_X, (int)punto_Y);				
		}
		poligono.addPoint((int) tam_carreraX, (int) (punto_Y + ((km_carrera-kilometros_pendientes[kilometros_pendientes.length-1])*pendientes[kilometros_pendientes.length-1]/100)*pixels_km));
		poligono.addPoint(this.getWidth(), this.getHeight());
		poligono.addPoint(0 , this.getHeight());
		
		return poligono;		
	}
	
	/**
	 * Dibuja las marcas que marcan los Km sobre el buffer (Graphics) que sirve como imagen de fondo.
	 * @param g Graphics sobre el que se pintan todas las imágenes.
	 * @param color Color de la línea y número que indican el kilómetro
	 * @param distancia Distancia de separación (en Km) entre líneas
	 */
	private void pintarSeparacionesKilometricas(Graphics g, Color color, int distancia)
	{
		g.setColor(color);
		int numerodelineas = globales.getKILOMETROS_CARRERA()/distancia;
		double distanciapixelsentrelineas = distancia*globales.getTAM_DIBUJO_CARRERA_X()/globales.getKILOMETROS_CARRERA();
		
		for (int i=0; i<numerodelineas+1; i++) //se hace hasta +1 para que si KILOMETROS_CARRERA no es un múltiplo de SEPARACION_MARCA_KILOMETROS, pinte la última línea
		{
			g.drawLine((int)(i*distanciapixelsentrelineas),	(int)(globales.getTAM_DIBUJO_CARRERA_Y()), 
					(int)(i*distanciapixelsentrelineas), (int)(globales.getTAM_DIBUJO_CARRERA_Y()-15));
			g.drawString(Integer.toString(i*distancia),
					(int)(i*distanciapixelsentrelineas), (int)(globales.getTAM_DIBUJO_CARRERA_Y()-15));
		}
	}
	
	/**
	 * Dibuja las marcas que señalan la posición de las curvas sobre el buffer (Graphics) que sirve como imagen de fondo.
	 * @param g Graphics sobre el que se pintan todas las imágenes.
	 * @param color Color de la línea y número que indican la curva
	 */
	private void pintarCurvas(Graphics g, Color color)
	{
		//se actualiza el mapa de curvas por si ha habido algún cambio (ha introducido alguna curva por comando)
		kilometros_curvas = mapacurvas.transformarEnArray(MapaCurvas.CLAVE);
		curvas = mapacurvas.transformarEnArray(MapaCurvas.VALOR);
		
		g.setColor(color);

		for (int i=0; i<kilometros_curvas.length; i++)
		{
			int posicionlinea = (int)(kilometros_curvas[i]*globales.getTAM_DIBUJO_CARRERA_X()/(double)globales.getKILOMETROS_CARRERA());
			g.drawLine(posicionlinea, 0,
					posicionlinea, (int)globales.getTAM_DIBUJO_CARRERA_X());
			g.drawString(" "+Double.toString(curvas[i]), posicionlinea, 10);
			g.drawString(" (m/s)", posicionlinea, 22);
			g.drawString(" max.", posicionlinea, 35);
		}

	}
	
	/**
	 * Dibuja cada uno de los ciclistas sobre el buffer (Graphics) que sirve como imagen de fondo, 
	 * añadiendo además el número del ciclista.
	 * @param g Graphics sobre el que se pintan todas las imágenes.
	 * @param color Color del que se desea que se pinte el número de ciclista
	 */
	@SuppressWarnings("unused")
	private void pintarCiclistas(Graphics g, Color color)
	{
		g.setColor(color);
		int i=0;
		for( Ciclista c: listaciclistas)
		{	
		    g.drawString(Integer.toString(i), (int)posicionciclistaX[i], (int)posicionciclistaY[i]);	

		    BufferedImage imagenrotada = rotate(imagenredimensionada, inclinacionciclista[i]);
		    g.drawImage(imagenrotada, (int)posicionciclistaX[i], (int)posicionciclistaY[i], this);	
		    i++;
		}
	}
		
	/**
	 * Inicializa a todos los ciclistas en su posición inicial.
	 */
	@SuppressWarnings("unused")
	private void iniciarPosicionesCiclistas()
	{
		int i=0;
		for(Ciclista c: listaciclistas)
		{	
			posicionciclistaX[i] = 0.0 - imagenredimensionada.getWidth(null)/2;
			posicionciclistaY[i] = globales.getPOS_Y_COMIENZO_CARRERA() - imagenredimensionada.getHeight(null)*3/4;	
			i++;
		}	
	}
	
	/**
	 * Calcula la nueva posición (en pantalla) de cada ciclista. 
	 * Para ello, teniendo en cuenta la posición X e Y anteriores, y la pendiente actual, 
	 * suma o resta cierta cantidad correspondiente al avance que ha recorrido. 
	 */
	private void recalcularPosicionesCiclistas()
	{
		int i=0;
		for(Ciclista c: listaciclistas)
		{
			double Xanterior = posicionciclistaX[i];
			double Yanterior = posicionciclistaY[i];
			double inclinacion = mapapendientes.getPendiente(c.getBici().getCuentametros()/1000);
			
			posicionciclistaX[i] = (c.getBici().getCuentametros()*globales.getMETROS_A_KILOMETROS() * globales.getTAM_DIBUJO_CARRERA_X()/globales.getKILOMETROS_CARRERA()) - imagenredimensionada.getWidth(null)/2;    
			posicionciclistaY[i] = Yanterior - inclinacion*(Xanterior-posicionciclistaX[i])/100;
			i++;
		}
	}
	
	/**
	 * Calcula la inclinación de cada uno de los ciclistas. 
	 * en función de la inclinación de la pendiente del punto en que se encuentra cada uno.
	 * Para ello consulta el mapadependientes y mediante la arcotangente (devuelta en radianes)
	 * lo convierte en grados y lo almacena en el array inclinacionciclista[]. 
	 * 
	 * Empleamos, para calcular el ángulo de la inclinación: INCLIN = arctg(avance vertical/avance lateral)
	 * Por lo tanto con una inclinación del 100% -> arctg(100/100) = 45º
	 * Y para una inclinación de 20% -> arctg(20/100) = 11.30º
	 */
	private void calcularInclinacionCiclistas()
	{
		int i=0;
		for(Ciclista c: listaciclistas)
		{
			double inclinacion = mapapendientes.getPendiente(c.getBici().getCuentametros()*Globales.getInstance().getMETROS_A_KILOMETROS());
			inclinacionciclista[i] = Math.toDegrees(Math.atan(inclinacion/100)); //necesario convertirlo a grados, ya que Math lo devulve en radianes
			i++;
		}
	}
	
	/**
	 * Carga un gif desde una ruta determinada, devolviendo un BufferedImage.
	 * 
	 * Si no se encuentra la imagen, o esta no se puede cargar, salta una Exception controlada  
	 * que imprime un mensaje de error y la traza del error.
	 * 
	 * @param ruta Ruta completa de la imagen, desde la ruta del proyecto. Incluida la extensión (.gif). 
	 * Se debe emplear la nomenclatura "/" , por ejemplo: "ficheros/ciclista.gif".
	 * @return BufferedImage de la imagen correspondiente.
	 */
	private BufferedImage cargarGif(String ruta)
	{
		BufferedImage imagen = null;
		try 
	    {
	    	imagen=ImageIO.read(new File(ruta));  
	    }
	    catch(Exception e)
	    {
	    	System.out.println("ERROR CONTROLADO al leer la imagen " + ruta + " en: ");
	    	e.printStackTrace();
	    }
		return imagen;
	}
	
	/**
	 * Rota un BufferedImage un ángulo determinado. 
	 * 
	 * from: http://www.javalobby.org/articles/ultimate-image/#10
	 * 
	 * BIBLIO: http://stackoverflow.com/questions/12165977/java-image-rotation 
	 * http://sanjaal.com/java/401/java-graphics-2d/rotating-an-image-in-java-graphics-2d-by-specified-degree/
	 * 
	 * @param img BufferedImage que se desea rotar
	 * @param angle Ángulo de rotación (en grados)
	 * @return BufferedImage rotada
	 */
	public static BufferedImage rotate(BufferedImage img, double angle)
	{  
        int w = img.getWidth();  
        int h = img.getHeight();  
        BufferedImage dimg  = new BufferedImage(w, h, img.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
        RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(angle), w/2, h/2);  
        g.drawImage(img, null, 0, 0);  
        return dimg;  
    } 
	
}
