package model.data_structures;

import static org.junit.Assert.fail;



import org.junit.Before;
import org.junit.Test;



import junit.framework.TestCase;

public class TestStack extends TestCase{

private Stack<String> salu2;
	
	@Before
	public void setUp() {
		salu2 = new Stack<String>();
		
		System.out.println("Codigo de iniciacion");
		System.out.println("prueba");
	}
	
	@Test
	public void test() {
		
		salu2.push("n1");
		salu2.push("n2");
		salu2.push("n3");
		salu2.push("n4");
		salu2.push("n");
		if(salu2.darLongitud()!=5){
			fail("Error fatal en la clase queue");
		}
		
		assertEquals("La extraccion de info no funciona","n",salu2.pop());
		assertEquals("No se elimino el primer elemento despues de consultarlos","n4",salu2.darPrimero().darElemento());
		
		
		
	}
}