package impl;

import java.util.NoSuchElementException;

import adt.FullContainerException;
import adt.Queue;

/**
 * ArrayQueue
 * 
 * An implementation of a fixed-capacity queue using a ring buffer
 * in an array.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */
public class ArrayQueue<E> implements Queue<E> {

    /**
     * The internal representation
     */
    private E[] internal;

    /**
     * The index in the array where the front item
     * in the queue is, if any. Must be a valid index into
     * the internal array.
     */
    private int front;
    
    /**
     * The number of items currently in the queue
     */
    private int size;
    
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        internal = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Add (enqueue) an item to the back of the queue.
     * @param item The item to enqueue
     * @throws FullContainerException if the queue is full
     */
    public void enqueue(E item) {
        if (size < internal.length) 
            internal[(front + size++) % internal.length] = item;
        else throw new FullContainerException();
    }

    /**
     * Return but do not remove the front item, ie the
     * item enqueued longest ago of all the items still 
     * in the stack.
     * @return The front item in the queue
     * @throws NoSuchSuchElementException if the queue is empty.
     */
    public E front() {
        if (size > 0) return internal[front];
        else throw new NoSuchElementException();
    }


    /**
     * Return and remove the front item, ie the
     * item enqueued longest ago of all the items still 
     * in the stack.
     * @return The front item in the queue
     * @throws NoSuchSuchElementException if the queue is empty.
     */
    public E remove() {
        if (size > 0) {
            E toReturn = internal[front];
            front++;
            size--;
            if (front == internal.length) front = 0;
            return toReturn;
        }
        else throw new NoSuchElementException();
    }

    /**
     * Is the queue empty?
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

}
