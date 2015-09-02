/**
 * File: P_FuncionamientoComandos.java
 * -------------------
 * Este fichero define la clase de pruebas con JUnit de nuestro programa.
 */
package pruebas;

import org.junit.Test;

/**
 * @author Jesús Martínez Dotor & María Picado Álvarez
 *
 */
public class P_GestorComandos {

	@Test  
    public void testPalabrasComando() {  

	}
}

/* Esto lo copio aquí para tenerlo más cerca y poder copiarlo más facilmente cuando haya que implementarlo
 Comando comando = new Comando("hola" , "1", "asd");

assertTrue(comando.getPalabraComando().equals("hola"));
assertTrue(comando.getArgumento1().equals("1"));
assertTrue(comando.getArgumento2().equals("asd"));
assertTrue(!comando.esDesconocido());
assertTrue(comando.tieneArgumento1());
assertTrue(comando.tieneArgumento2());

comando = new Comando("salir", "1", null);
assertTrue(!comando.esDesconocido());
assertTrue(comando.tieneArgumento1());
assertTrue(!comando.tieneArgumento2());

comando = new Comando("frenar", null, null);
assertTrue(!comando.esDesconocido());
assertTrue(!comando.tieneArgumento1());
assertTrue(!comando.tieneArgumento2());

comando = new Comando(null , null, null);
assertTrue(comando.esDesconocido());
assertTrue(!comando.tieneArgumento1());
assertTrue(!comando.tieneArgumento2());	 
  
  
  
 
int[] vectorpinones = {14,17,20,24,28};
int[] vectorplatos = {28,45,52};

assertTrue(bici.getDiametroRueda() == 26);
assertArrayEquals(bici.getDientesPinones(), vectorpinones);
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
assertTrue(bici.calcularTiempoPedalada(50) == 1.2);
assertTrue(bici.calcularVelocidadTeorica(50) == 4.8409848396716315);
bici.aumentarVelocidadCadencia(40);
assertTrue(bici.getVelocidadActual() == 3.872787871737305);
bici.aumentarVelocidadCadencia(60);
assertTrue(bici.getVelocidadActual() == 5.809181807605958);*/