package uy.edu.um.adt.queue;

public interface MyQueue<T> {

	void enqueue(T value);
	
	T dequeue() throws EmptyQueueException;
	
	boolean contains(T value);
	
	int size();
	
}
