package adt;

/**
 * Stack
 * 
 * Interface to serve as an example of the stack ADT
 * 
 * If overridden, the toString method for stack should return
 * a comma separated list of the elements of the stack bordered
 * by square braces. For instance a stack with elements A, B, and C
 * could return the String "[A, B, C]". The elements should be order
 * from left to right in the order they will be returned by pop().
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the stack
 */

public interface Stack<E> {

    /**
     * Add (push) an item to the top of the stack.
     * @param item The item to push
     * @throws FullContainerException if the stack is full
     */
    void push(E item);

    /**
     * Return but do not remove the top item, ie the
     * item most recently pushed of all the items still in
     * the stack.
     * @return The top item in the stack
     * @throws NoSuchSuchElementException if the stack is empty.
     */
    E top();


    /**
     * Return and remove the top item, ie the
     * item most recently pushed of all the items still in
     * the stack.
     * @return The top item in the stack
     * @throws NoSuchSuchElementException if the stack is empty.
     */
    E pop();

    /**
     * Is the stack empty?
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();


}
