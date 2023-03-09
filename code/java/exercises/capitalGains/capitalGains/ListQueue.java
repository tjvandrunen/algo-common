package capitalGains;

import java.util.NoSuchElementException;

/**
 * ListQueue
 * 
 * A class that uses a list from this package to
 * implement a queue.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the queue
 */

public class ListQueue<E> implements Queue<E> {

    private List<E> internal;
    
    //begin example
    public ListQueue(int implementation) {
        internal = new LinkedList<E>();
    }
    //end example
    public ListQueue() {
        this.internal = new LinkedList<E>();
    }
    
    /**
     * Add (enqueue) an item to the back of the queue.
     * @param item The item to enqueue
     * @throws FullContainerException if the queue is full
     */
    public void enqueue(E item) {
        internal.add(item);
    }
    
    /**
     * Return but do not remove the front item, ie the
     * item enqueued longest ago of all the items still 
     * in the stack.
     * @return The front item in the queue
     * @throws NoSuchSuchElementException if the queue is empty.
     */
    public E front() {
        if (internal.size() == 0)
            throw new NoSuchElementException();
        else
            return internal.get(0);
    }

    
    /**
     * Return and remove the front item, ie the
     * item enqueued longest ago of all the items still 
     * in the stack.
     * @return The front item in the queue
     * @throws NoSuchSuchElementException if the queue is empty.
     */
    public E remove() {
        if (internal.size() == 0)
            throw new NoSuchElementException();
        else
            return internal.remove(0);
    
    }
    
    /**
     * Is the queue empty?
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return internal.size() == 0;
    }
    
    @Override
    public String toString() {
    	return internal.toString();
    }

}
