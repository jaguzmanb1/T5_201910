package model.data_structures;

public class Queue<T> {
	
	private Objeto<T> primero;


	public Queue()
	{	
		primero = null;

	}
	
	
	
	public boolean isEmpty(){
		boolean x=false;
		if(primero==null){
			x=true;
		}
		return x;

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
	 * Inserta un nuevo elemento en la Cola
	 * @param t el nuevo elemento que se va ha agregar
	 */
	public void enqueue(T t){
		if(primero==null){
			primero=new Objeto<T>(t);

		}
		else{
			primero.añadirAlFinal(t);
		}

	}

	/**
	 * Quita y retorna el elemento agregado menos recientemente
	 * @return el elemento agregado menos recientemente
	 */
	public T dequeue(){
		T x = null;
		if(primero!=null){
			x=primero.darT();
			primero=primero.darSiguiente();
		}
		return x;
	}
}
