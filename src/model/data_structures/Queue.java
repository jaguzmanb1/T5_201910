/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ColaEncadenada.java,v 1.17 2008/09/30 16:09:00 alf-mora Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package model.data_structures;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementaci�n de una cola encadenada
 * @param <T> Tipo de datos que contiene cada nodo de la cola
 */
public class Queue<T> implements Serializable, IQueue<T>
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante para la serializaci�n
	 */
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	/**
	 * Primer elemento de la cola encadenada
	 */
	protected NodoCola<T> primero;

	/**
	 * Ultimo elemento de la cola encadenada
	 */
	protected NodoCola<T> ultimo;

	/**
	 * N�mero de elementos de la cola
	 */
	protected int numElems;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor de la cola encadenada vac�a. <br>
	 * <b>post: </b> Se construy� una cola vac�a. primero==null, ultimo==null, numElems = 0<br>
	 */
	public Queue( )
	{
		primero = null;
		ultimo = null;
		numElems = 0;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------
	/**
	 * Retorna la longitud de la cola (n�mero de elementos). <br>
	 * <b>post: </b> Se retorn� la longitud de la cola<br>.
	 * @return El n�mero de elementos de la cola. Entero positivo o cero.<br>
	 */
	public int darLongitud( )
	{
		return numElems;
	}

	/**
	 * Retorna el primer elemento y lo elimina de la cola. <br>
	 * <b>post: </b> Se retorn� y elimin� el primer elemento de la cola. Si es el �nico elemento, el primero y el ultimo son null. La cantidad de los elementos se reduce en 1<br>
	 * @return El primer elemento de la cola. Diferente de null<br>
	 * @throws ColaVaciaException Si la cola no tiene elementos<br>
	 */
	@Override
	public T dequeue( )
	{
		NodoCola<T> p = primero;
		primero = primero.desconectarPrimero( );
		if( primero == null )
			ultimo = null;
		numElems--;
		return p.darElemento( );

	}
	
	@Override
	public void enqueue(T elemento) {
		NodoCola<T> nodo = new NodoCola<T>( elemento );
		if( primero == null )
		{
			primero = nodo;
			ultimo = nodo;
		}
		else
		{
			ultimo = ultimo.insertarDespues( nodo );
		}
		numElems++;
	}

	

	/**
	 * Retorna el primer nodo de la cola. Sin eliminarlo<br>
	 * <b>post: </b> Se retorn� el primer nodo de la cola.
	 * @return El primer nodo de la cola
	 */
	public NodoCola<T> darPrimero( )
	{
		return primero;
	}

	/**
	 * Retorna el �ltimo nodo de la cola. Sin eliminarlo<br>
	 * <b>post: </b> Se retorn� el �ltimo nodo de la cola.<br>
	 * @return El �ltimo nodo de la cola<br>
	 */
	public NodoCola<T> darUltimo( )
	{
		return ultimo;
	}

	/**
	 * Indica si la cola se encuentra vac�a (no tiene elementos). <br>
	 * <b>post: </b> Se retorn� true si primero==null o false en caso contrario.<br>
	 * @return True si primero==null o false en caso contrario<br>
	 */
	public boolean estaVacia( )
	{
		return primero == null;
	}

	/**
	 * Convierte la cola a un String. <br>
	 * <b>post: </b> Se retorn� la representaci�n en String de la cola. El String tiene el formato "[numeroElementos]: e1->e2->e3..->en", donde e1, e2, ..., en son los los
	 * elementos de la cola y numeroElementos su tama�o. <br>
	 * @return La representaci�n en String de la cola
	 */
	@Override
	public String toString( )
	{
		String resp = "[" + numElems + "]:";
		for( NodoCola<T> p = primero; p != null; p = p.darSiguiente( ) )
		{
			resp += p.darElemento( ).toString( ) + "->";
		}
		return resp;
	}


	@Override
	public Iterator<T> iterator() {
		return  new Iterator<T>() {

			private NodoCola<T> current = primero;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return current != null;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				if(!hasNext())
					throw new NoSuchElementException();

				NodoCola<T> item = current;
				current = current.darSiguiente();
				return item.darElemento();
			}
		};
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.darLongitud();
	}

	


}
