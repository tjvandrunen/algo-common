package stquit;

/**
 * Queue
 * 
 * Interface to serve as an example of the queue ADT
 * 
 * If overridden, the toString method for queue should return
 * a comma separated list of the elements of the queue bordered
 * by square braces. For instance a queue with elements A, B, and C
 * could return the String "[A, B, C]". The elements should be order
 * from left to right in the order they will be returned by remove().
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the queue
 */

public interface Queue<E> {

    /**
     * Add (enqueue) an item to the back of the queue.
     * @param item The item to enqueue
     * @throws FullContainerException if the queue is full
     */
    void enqueue(E item);

    /**
     * Return but do not remove the front item, ie the
     * item enqueued longest ago of all the items still 
     * in the stack.
     * @return The front item in the queue
     * @throws NoSuchSuchElementException if the queue is empty.
     */
    E front();


    /**
     * Return and remove the front item, ie the
     * item enqueued longest ago of all the items still 
     * in the stack.
     * @return The front item in the queue
     * @throws NoSuchSuchElementException if the queue is empty.
     */
    E remove();

    /**
     * Is the queue empty?
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();

}
