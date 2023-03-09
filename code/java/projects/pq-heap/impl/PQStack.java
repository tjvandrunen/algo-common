package impl;
/**
 * Stack.java
 *
 * Class to implement a stack using a priority queue.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */

import java.util.Comparator;
import java.util.NoSuchElementException;

import adt.FullContainerException;
import adt.Map;
import adt.Stack;

public class PQStack<E> implements Stack<E> {

    /**
     * The priority queue to use as an internal representation.
     */
    private HeapPriorityQueue<E> pq;

    /**
     * Place to store data associated with representative
     * values in the priority queue.
     */
    private Map<E, Integer> arrivalTimes;
    

 
    /**
     * Constructor.
     * @param maxSize The capacity of this stack.
     */
    public PQStack(int maxSize) {
        arrivalTimes = new ListMap<E, Integer>();
        //finish constructor here
    }

    /**
     * Is this stack empty? It is if the pq is empty.
     * @return True if this is empty, false otherwise.
     */
    public boolean isEmpty() { return pq.isEmpty(); }

    /**
     * Is this stack full? It is if the pq is full.
     * @return True if this is full, false otherwise.
     */
    public boolean isFull() { return pq.isFull(); }

    /**
     * Retrieve (but do not remove) the top element of this stack.
     * @return The top element.
     */
    public E top() { 
         throw new UnsupportedOperationException();
    }

    /**
     * Retrieve and remove the top element of this stack.
     * @return The top element.
     */
    public E pop() {
         throw new UnsupportedOperationException();
    }

    /**
     * Add an element to this stack.
     * @param x The element to add.
     */
    public void push(E x) {
         throw new UnsupportedOperationException();
    }

    public String toString() { return pq.toString(); }
    
}
