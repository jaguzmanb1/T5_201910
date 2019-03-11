package model.data_structures;

import static org.junit.Assert.fail;



import org.junit.Before;
import org.junit.Test;


import junit.framework.TestCase;


public class TestQueue extends TestCase{

	private Queue<String> salu2;
	
	@Before
	public void setUp() {
		salu2 = new Queue<String>();
		
		System.out.println("Codigo de iniciacion");
		System.out.println("prueba");
	}
	
	@Test
	public void test() {
		
		salu2.enqueue("n1");
		salu2.enqueue("n2");
		salu2.enqueue("n3");
		salu2.enqueue("n4");
		salu2.enqueue("n");
		if(salu2.darLongitud()!=5){
			fail("Error fatal en la clase queue");
		}
		assertEquals("No funciona el añadido del queue","n",salu2.darUltimo().darElemento());
		assertEquals("La extraccion de info no funciona","n1",salu2.dequeue());
		assertEquals("No se elimino el primer elemento despues de consultarlos","n2",salu2.darPrimero().darElemento());
		
		
	}

}