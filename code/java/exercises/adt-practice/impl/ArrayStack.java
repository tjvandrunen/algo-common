package impl;

import java.util.NoSuchElementException;

import adt.FullContainerException;
import adt.Stack;

/**
 * FixCapStack
 * 
 * Class to be a plain implementation of a stack,
 * implemented with an array, with a fixed capacity;
 * based on the implementation in Sedgewick, "Algorithms",
 * Fourth Edition, pg 133.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the stack
 */

public class ArrayStack<E> implements Stack<E> {


    /**
     * The internal representation.
     */
    private E[] internal;

    /**
     * The portion of the array being used; the
     * next available index; one greater than the index
     * of the top element.
     */
    private int size;
     
    /**
     * Constructor specifying the capacity of this stack.
     * @param capacity The max elements this stack can hold at a time.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        internal = (E[]) new Object[capacity];
        size = 0;
    }
    
    /**
     * Add (push) an item to the top of the stack.
     * @param item The item to push
     * @throws FullContainerException if the stack is full
     */
    public void push(E item) {
        if (size < internal.length)
            internal[size++] = item;
        else
            throw new FullContainerException();
    }
    
    /**
     * Return but do not remove the top item, ie the
     * item most recently pushed of all the items still in
     * the stack.
     * @return The top item in the stack
     * @throws NoSuchSuchElementException if the stack is empty.
     */
    public E top() {
        if (size > 0)
            return internal[size-1];
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
        if (size > 0)
            return internal[--size];
        else
            throw new NoSuchElementException();
        
    }
    
    /**
     * Is the stack empty?
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (int i = size-1; i >= 0; i--) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += internal[i];
    		prefix = true;
    	}
    	return toReturn + "]";
    }
    
}
