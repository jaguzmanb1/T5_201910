package model.data_structures;

public interface IHeap <T extends Comparable<T>>{
	     

	public boolean isEmpty();
	public int size();
	public void insert(T v);
	public T delMax();
	
}
