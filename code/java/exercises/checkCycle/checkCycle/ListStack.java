package checkCycle;

import java.util.NoSuchElementException;

/**
 * ListStack
 * 
 * A class that uses a list from this package checkCycle;
 * implement a stack.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the stack
 */

public class ListStack<E> implements Stack<E> {

	private List<E> internal;
	
	public ListStack() {
		internal = 
		        new LinkedList<E>();
	}

	public ListStack(Class<List<E>> internalClass) throws InstantiationException, IllegalAccessException {
	    this.internal = internalClass.newInstance();
	}

	
	/**
	 * Add (push) an item to the top of the stack.
	 * @param item The item to push
	 * @throws FullContainerException if the stack is full
	 */
	public void push(E item) {
		internal.add(item);
	}
	
	/**
	 * Return but do not remove the top item, ie the
	 * item most recently pushed of all the items still in
	 * the stack.
	 * @return The top item in the stack
	 * @throws NoSuchSuchElementException if the stack is empty.
	 */
	public E top() {
		if (internal.size() > 0)
			return internal.get(internal.size() - 1);
		else
			throw new NoSuchElementException();
	}

	
	/**
	 * Return and remove the top item, ie the
	 * item most recently pushed of all the items still in
	 * the stack.
	 * @return The top item in the stack
	 * @throws NoSuchSuchElementException if the stack is empty.
	 */
	public E pop() {
		if (internal.size() > 0) 
			return internal.remove(internal.size() - 1);
		else
			throw new NoSuchElementException();
	}
	
	/**
	 * Is the stack empty?
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return internal.size() == 0;
	}
	
}
