package impl;

import java.util.NoSuchElementException;

import adt.Stack;

public class ListStack<E> implements Stack<E> {

    private class Node {
        E datum;
        Node next;
        Node(E datum, Node next) {
            this.datum = datum;
            this.next = next;
        }
    }
    
    private Node top;
    
    /**
     * Add (push) an item to the top of the stack.
     * @param item The item to push
     * @throws FullContainerException if the stack is full
     */
    public void push(E item) {
        top = new Node(item, top);
    }
    
    /**
     * Return but do not remove the top item, ie the
     * item most recently pushed of all the items still in
     * the stack.
     * @return The top item in the stack
     * @throws NoSuchSuchElementException if the stack is empty.
     */
    public E top() {
        if (top == null) throw new NoSuchElementException();
        else return top.datum;
    }

    
    /**
     * Return and remove the top item, ie the
     * item most recently pushed of all the items still in
     * the stack.
     * @return The top item in the stack
     * @throws NoSuchSuchElementException if the stack is empty.
     */
    public E pop() {
        if (top == null) throw new NoSuchElementException();
        else {
            E toReturn = top.datum;
            top = top.next;
            return toReturn;
        }

    }
    
    /**
     * Is the stack empty?
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }
    
    @Override
    public String toString() {
    	return ":)";
    }
    
}
