package impl;
/**
 * PQQueue.java
 *
 * Class to implement a queue using a priority queue.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */

import java.util.Comparator;
import java.util.NoSuchElementException;

import adt.FullContainerException;
import adt.Map;
import adt.Queue;

public class PQQueue<E> implements Queue<E> {

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
     * @param maxSize The capacity of this queue.
     */
    public PQQueue(int maxSize) {
        arrivalTimes = new ListMap<E, Integer>();
        // finish this constructor
    }

    /**
     * Is this queue empty? It is if the pq is empty.
     * @return True if this is empty, false otherwise.
     */
    public boolean isEmpty() { return pq.isEmpty(); }

    /**
     * Is this queue full? It is if the pq is full.
     * @return True if this is full, false otherwise.
     */
    public boolean isFull() { return pq.isFull(); }

    /**
     * Retrieve (but do not remove) the front element of this queue.
     * @return The front element.
     */
    public E front() { 
         throw new UnsupportedOperationException();
    }

    /**
     * Retrieve and remove the front element of this queue.
     * @return The front element.
     */
    public E remove() {
         throw new UnsupportedOperationException();
    }

    /**
     * Add an element to the back of this queue.
     * @param x The element to add.
     */
    public void enqueue(E x) {
         throw new UnsupportedOperationException();
    }

}
