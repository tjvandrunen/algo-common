package impl;

import java.util.NoSuchElementException;

import adt.FullContainerException;
import adt.List;
import adt.Stack;

public class ListStackTopFront<E> implements Stack<E> {

    private List<E> internal;
    
    public ListStackTopFront() {
        internal = 
                new LinkedList<E>();
                //begin example

                //new ArrayList<E>();
                //new BackwardsLinkedList<E>();
                //new WimpList<E>();

                //end example
    }

    public ListStackTopFront(Class<List<E>> internalClass) throws InstantiationException, IllegalAccessException {
        this.internal = internalClass.newInstance();
    }

    
    /**
     * Add (push) an item to the top of the stack.
     * @param item The item to push
     * @throws FullContainerException if the stack is full
     */
    public void push(E item) {
        internal.insert(0, item);
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
            return internal.get(0);
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
            return internal.remove(0);
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
    
    @Override
    public String toString() {
    	return internal.toString();
    }
    
}
