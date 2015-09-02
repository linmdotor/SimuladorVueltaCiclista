/**
 * File: P_Bicicleta.java
 * -------------------
 * Este fichero define la clase de pruebas con JUnit de nuestro programa.
 */
package pruebas;

import static org.junit.Assert.*;

import bicicleta.*;

import org.junit.Test;

import principal.Globales;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class P_Bicicleta {

	@Test  
    public void testBicicleta() {  
		Globales globales = Globales.getInstance();
		
		Bicicleta bici = new Bicicleta();
		int[] vectorpinones = {14,17,20,24,28};
		int[] vectorplatos = {28,45,52};
		
		assertTrue(bici.getRadioRueda() == 1/globales.getPI());
		bici.setRadioRueda(28);
		assertTrue(bici.getRadioRueda() == 28);
		assertArrayEquals(bici.getDientesPinones(), vectorpinones);
		assertArrayEquals(bici.getDientesPlatos(), vectorplatos);
		bici.setDientesPinones(0, 5);
		bici.setDientesPlatos(0, 10);
		assertTrue(bici.getDientesPinones(0) == 5);
		assertTrue(bici.getDientesPlatos(0) == 10);
		assertTrue(bici.getMasa() == 15000);
		bici.setMasa(900);
		assertTrue(bici.getMasa() == 900);
		assertTrue(bici.getPinonSeleccionado() == 0);
		bici.setPinonSeleccionado(2);
		assertTrue(bici.getPinonSeleccionado() == 2);
		assertTrue(bici.getPlatoSeleccionado() == 0);
		bici.setPlatoSeleccionado(2);
		assertTrue(bici.getPlatoSeleccionado() == 2);
		assertTrue(bici.getVelocidadActual() == 0);
		bici.setVelocidadActual(2);
		assertTrue(bici.getVelocidadActual() == 2);
		assertTrue(bici.calcularDistanciaPedalada() == 5.809181807605958);
		assertTrue(bici.calcularVelocidadTeorica(50) == 4.8409848396716315);
		//bici.aumentarVelocidadCadencia(40);
		assertTrue(bici.getVelocidadActual() == 3.872787871737305);
		//bici.aumentarVelocidadCadencia(60);
		assertTrue(bici.getVelocidadActual() == 5.809181807605958);
	}
}