/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PilaEncadenada.java,v 1.13 2007/06/05 01:19:16 man-muno Exp $
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementaci�n de una pila encadenada
 * @param <T> Tipo de datos a almacenar en la pila
 */
public class Stack<T> implements IStack<T>
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	/**
	 * Primer elemento de la pila encadenada
	 */
	private NodoPila<T> primero;

	/**
	 * N�mero de elementos de la pila
	 */
	private int numElems;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor de la cola encadenada vac�a<br>
	 * <b>post: </b> Se construy� una cola vac�a
	 */
	public Stack( )
	{
		primero = null;
		numElems = 0;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Se retorn� la longitud de la pila (n�mero de elementos).<br>
	 * <b>post: </b> Se retorn� la longitud de la pila.<br>
	 * @return Retorna el n�mero de elementos de la pila. Entero mayor o igual a cero.<br>
	 */
	public int darLongitud( )
	{
		return numElems;
	}

	/**
	 * Retorna el primer elemento y lo elimina de la pila.<br>
	 * <b>post: </b> Se retorn� y elimin� el primer elemento de la pila.<br>
	 * @return El primer elemento de la pila<br>
	 * @throws PilaVaciaException Si la pila no tiene elementos<br>
	 */
	public T pop( )
	{
		NodoPila<T> p = primero;
		primero = primero.desconectarPrimero( );
		numElems--;
		return p.darElemento( );

	}

	/**
	 * Inserta un elemento en el tope de la pila. <br>
	 * <b>post: </b> Se agreg� el elemento especificado en el tope de la pila.
	 * @param elemento El elemento a ser insertado
	 */
	public void push( T elemento )
	{
		NodoPila<T> nodo = new NodoPila<T>( elemento );
		if( primero == null )
		{
			primero = nodo;
		}
		else
		{
			primero = primero.insertarAntes( nodo );
		}
		numElems++;
	}

	/**
	 * Retorna el primer nodo de la pila. <br>
	 * <b>post: </b> Se retorn� el primer nodo de la pila.
	 * @return El primer nodo de la pila
	 */
	public NodoPila<T> darPrimero( )
	{
		return primero;
	}

	/**
	 * Indica si la pila se encuentra vac�a (no tiene elementos). <br>
	 * <b>post: </b> Se retorn� true si primero==null o false en caso contrario.
	 * @return True si primero==null o false en caso contrario
	 */
	public boolean estaVacia( )
	{
		return primero == null;
	}

	/**
	 * Convierte la pila a un String. <br>
	 * <b>post: </b> Se retorn� la representaci�n en String de la pila. El String tiene el formato "[numeroElementos]: e1->e2->e3..->en", donde e1, e2, ..., en son los los
	 * elementos de la pila y numeroElementos su tama�o.
	 * @return La representaci�n en String de la cola
	 */
	@Override
	public String toString( )
	{
		String resp = "[" + numElems + "]:";
		for( NodoPila<T> p = primero; p != null; p = p.darSiguiente( ) )
		{
			resp += p.darElemento( ).toString( ) + "->";
		}
		return resp;
	}

	@Override
	public Iterator<T> iterator() {
		return  new Iterator<T>() {

			private NodoPila<T> current = primero;
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

				NodoPila<T> item = current;
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
