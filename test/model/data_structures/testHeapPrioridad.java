package model.data_structures;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class testHeapPrioridad extends TestCase{

	private HeapPrioridad<String> salu2;
	
	@Before
	public void setUp() {
		salu2 = new HeapPrioridad<String>(10);
		
		System.out.println("Codigo de iniciacion");
		System.out.println("prueba");
	}
	
	@Test
	public void test() {
		
		salu2.insert("n2");
		assertEquals("No funciono el añadidido","n2",salu2.max());
		salu2.insert("n1");
		assertEquals("No esta ordenado como heap","n2",salu2.max());
		salu2.insert("n3");
		salu2.insert("n4");
		salu2.insert("n5");
		if(salu2.size()!=5){
			fail("Error fatal en la clase HeapPrioridad");
		}
		assertEquals("No dio el valor maximo","n5",salu2.delMax());
		assertEquals("No se borro el valor maximo","n4",salu2.max());
		
		
		
		
	}

}
