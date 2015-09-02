/**
 * File: PruebasJUnit.java
 * -------------------
 * Este fichero define la clase de pruebas con JUnit de nuestro programa.
 * Automatiza la realización de los Test para que se ejecuten todos
 * conjuntamente desde esta clase.
 */

package pruebas;  
  
import org.junit.runner.RunWith;  
import org.junit.runners.Suite.SuiteClasses;  
import org.junit.runners.Suite;  

/**
 * En las pruebas siguientes se realiza una prueba de cada uno de los métodos de la clase
 * y luego se comprueba que el resultado previsto es igual al resultado que devuelve
 * la clase.
 */
@RunWith(Suite.class)  
@SuiteClasses({
	P_Bicicleta.class, 
	P_Ciclista.class, 
	P_Contador.class,
	P_GestorComandos.class
	})  
public class PruebasJUnit {}