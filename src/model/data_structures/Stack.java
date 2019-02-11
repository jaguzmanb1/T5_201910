package model.data_structures;

public class Stack<T> {


	private Objeto<T> primero;
	public Stack(){
		primero=null;
	}
public boolean isEmpty(){
	boolean f=false;
	if(primero==null){
		f=true;
	}
	return f;
}
	
	/**
	 * Retorna el numero de elementos contenidos
	 * @return el numero de elemntos contenidos
	 */
	public int size(){
		int cantidad=0;
		if(primero!=null){
			Objeto<T> prueba=primero;
			cantidad=1;
			boolean terminado=false;
			while(terminado==false){
				cantidad++;
				if(prueba.darSiguiente()==null){
					terminado=true;
				}
			}
		}
		return cantidad;
		
	}
	
	/**
	 * Inserta un nuevo elemento en la Pila
	 * @param t el nuevo elemento que se va ha agregar
	 */
	public void push(T t){
		Objeto<T> nuevo= new Objeto<T>(t);
		if(primero==null){
			primero=nuevo;
		}
		else{
			nuevo.cambiarSiguiente(primero);
			primero=nuevo;
		}
	}
	
	/**
	 * Quita y retorna el elemento agregado más recientemente
	 * @return el elemento agregado más recientemente
	 */
	public T pop(){
		T retorno=null;
		if(primero!=null){
			Objeto<T> prueba = primero;
		primero=prueba.darSiguiente();
		retorno=prueba.darT();
		}
		
		return retorno;
	}
}
