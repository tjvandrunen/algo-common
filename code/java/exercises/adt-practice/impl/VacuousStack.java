package impl;

import java.util.NoSuchElementException;

import adt.FullContainerException;
import adt.Stack;

/**
 * VacuousStack
 * 
 * A class that contains nothing but nevertheless fulfills
 * all specifications for a stack. It essentially is
 * a stack with capacity zero.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the stack
 */

public class VacuousStack<E> implements Stack<E> {

    /**
     * Add (push) an item to the top of the stack.
     * @param item The item to push
     * @throws FullContainerException if the stack is full
     */
    public void push(E item) {
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
        throw new NoSuchElementException();
    }


    /**
     * Return and remove the top item, ie the
     * item most recently pushed of all the items still in
     * the stack.
     * @return The top item in the stack
     * @throws NoSuchSuchElementException if the stack is empty.
     */
    public E pop(){
        throw new NoSuchElementException();
    }

    /**
     * Is the stack empty?
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return true;
    }
    
    @Override
    public String toString() {
    	return "[]";
    }

}
