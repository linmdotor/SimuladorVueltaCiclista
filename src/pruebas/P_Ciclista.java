/**
 * File: P_Ciclista.java
 * -------------------
 * Este fichero define la clase de pruebas con JUnit de nuestro programa.
 */
package pruebas;

import static org.junit.Assert.*;

import ciclista.*;

import org.junit.Test;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class P_Ciclista {

	@Test  
    public void testCiclista() {  
		Ciclista ciclista = new Ciclista(1);
		
		assertEquals(ciclista.getNumeroCiclista(), 1);
		assertTrue(ciclista.getMasa() == 65);
		assertTrue(ciclista.getFuerzamaxima() == 100);
		assertTrue(ciclista.getCadencia() == 0);
		ciclista.setNombre("Pedro");
		ciclista.setMasa(70);
		ciclista.setFuerzamaxima(50);
		ciclista.setCadencia(70);
		assertEquals(ciclista.getNombre(), "Pedro");
		assertTrue(ciclista.getMasa() == 70);
		assertTrue(ciclista.getFuerzamaxima() == 50);
		assertTrue(ciclista.getCadencia() == 70);
		ciclista.aumentaCadencia(10);
		assertTrue(ciclista.getCadencia() == 80);
		ciclista.disminuyeCadencia(20);
		assertTrue(ciclista.getCadencia() == 60);
	}
}