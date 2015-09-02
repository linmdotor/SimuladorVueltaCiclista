/**
 * File: P_Contador.java
 * -------------------
 * Este fichero define la clase de pruebas con JUnit de nuestro programa.
 */
package pruebas;

import static org.junit.Assert.*;

import contador.*;

import org.junit.Test;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class P_Contador {

	@Test  
    public void testContador() {  
		Contador contador = new Contador();  
		
        assertTrue(contador.getCuenta() == 0);
        contador.incrementa();
        assertTrue(contador.getCuenta() == 1);
        contador.incrementa(2);
        assertTrue(contador.getCuenta() == 3);
        contador.incrementa(-3);
        assertTrue(contador.getCuenta() == 0);
        contador.incrementa(-5);
        assertTrue(contador.getCuenta() == -5);
        contador.setCuenta(10);
        assertTrue(contador.getCuenta() == 10); 
        contador.setCuenta(-10);
        assertTrue(contador.getCuenta() == -10);
	}
	
	@Test  
    public void testContadorSaturado() {  
		ContadorSaturado contador = new ContadorSaturado();  
		
        /*assertTrue(contador.getCuenta() == 0);
        contador.incrementa();
        assertTrue(contador.getCuenta() == 1);
        contador.incrementa(2);
        assertTrue(contador.getCuenta() == 3);
        contador.incrementa(-3);
        assertTrue(contador.getCuenta() == 0);
        contador.incrementa(-5);
        assertTrue(contador.getCuenta() == -5);
        contador.setCuenta(10);
        assertTrue(contador.getCuenta() == 10); 
        contador.setCuenta(-10);
        assertTrue(contador.getCuenta() == -10);*/
        
        assertTrue(contador.getLimite() == 99);
        contador.setLimite(10);
        contador.setCuenta(8);
        assertTrue(contador.getCuenta() == 8);
        contador.incrementa();
        assertTrue(contador.getCuenta() == 9);
        contador.incrementa();
        assertTrue(contador.getCuenta() == 9);
        contador.incrementa(10);
        assertTrue(contador.getCuenta() == 9);
        contador.setCuenta(50);
        assertTrue(contador.getCuenta() == 9);
        contador.setLimite(-5);
        assertTrue(contador.getLimite() == -5);
        contador.incrementa();
        assertTrue(contador.getCuenta() == -6);
	}
	
	@Test  
    public void testContadorConExceso() {  
		ContadorSaturado contadorexceso = new ContadorSaturado(3);
		ContadorConExceso contador = new ContadorConExceso(contadorexceso);
		ContadorConExceso contador2 = new ContadorConExceso(contadorexceso);
		
        /*assertTrue(contador.getCuenta() == 0);
        contador.incrementa();
        assertTrue(contador.getCuenta() == 1);
        contador.incrementa(2);
        assertTrue(contador.getCuenta() == 3);
        contador.incrementa(-3);
        assertTrue(contador.getCuenta() == 0);
        contador.incrementa(-5);
        assertTrue(contador.getCuenta() == -5);
        contador.setCuenta(10);
        assertTrue(contador.getCuenta() == 10); 
        contador.setCuenta(-10);
        assertTrue(contador.getCuenta() == -10);
        
        assertTrue(contador.getLimite() == 99);
        contador.setLimite(10);
        contador.setCuenta(8);
        assertTrue(contador.getCuenta() == 8);
        contador.incrementa();
        assertTrue(contador.getCuenta() == 9);
        contador.incrementa();
        assertTrue(contador.getCuenta() == 9);
        contador.incrementa(10);
        assertTrue(contador.getCuenta() == 9);
        contador.setCuenta(50);
        assertTrue(contador.getCuenta() == 9);
        contador.setLimite(-5);
        assertTrue(contador.getLimite() == -5);
        contador.incrementa();
        assertTrue(contador.getCuenta() == -6);*/
		
		contador.setLimite(10);
		contador2.setLimite(10);
        contador.setCuenta(9);
        assertTrue(contador.getCuenta() == 9);
        contador.incrementa();
        assertTrue(contadorexceso.getCuenta() == 1);
        assertTrue(contador.getCuenta() == 0);
        contador2.incrementa(10);
        assertTrue(contadorexceso.getCuenta() == 2);
        assertTrue(contador2.getCuenta() == 0);
        contador.incrementa(10);
        assertTrue(contadorexceso.getCuenta() == 2);
        assertTrue(contador.getCuenta() == 0);
        contador.setContadorSuperior(contador2);
        contador.incrementa(10);
        assertTrue(contador2.getCuenta() == 1);
        assertTrue(contador.getCuenta() == 0);
	}
	
	@Test  
    public void testRelojSimple() {  
		RelojSimple reloj = new RelojSimple(30,70,29,123);
		assertEquals(reloj.getTiempo(), "23:59:29");
		reloj.incrementa();
		assertEquals(reloj.getTiempo(), "23:59:30");
		assertTrue(reloj.getHoras() == 23);
		assertTrue(reloj.getMinutos() == 59);
		assertTrue(reloj.getSegundos() == 30);
		reloj.incrementa(30);
		assertEquals(reloj.getTiempo(), "00:00:00");
		reloj.setTiempo(01,05,10,523);
		assertEquals(reloj.getTiempo(), "01:05:10");
		reloj.setHoras(25);
		reloj.setMinutos(65);
		reloj.setSegundos(62);
		assertEquals(reloj.getTiempo(), "23:59:59");
		assertTrue(reloj.getHoras() == 23);
		assertTrue(reloj.getMinutos() == 59);
		assertTrue(reloj.getSegundos() == 59);
	}
}
