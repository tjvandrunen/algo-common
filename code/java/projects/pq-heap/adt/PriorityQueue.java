package adt;

/**
 * PriorityQueue
 * 
 * Interface to define the PriorityQueue ADT.
 * How priority is determined is 
 * left to the implementation---as examples, the base
 * type can be assumed comparable or a comparator can
 * be supplied.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 2, 2015
 * @param <E> The base-type of the priority queue
 */

public interface PriorityQueue<E> {

    /**
     * Is this pq empty?
     * @return True if this is empty, false otherwise.
     */
    boolean isEmpty();
    
    /**
     * Insert a new key into this pq.
     * @param key The key to insert.  
     */
    void insert(E key);
    
    /**
     * Return (but do not remove) the maximum key.
     * @return The maximum key.
     */
    E max();
    
    /**
     * Return and remove the maximum key.
     * @return The maximum key.
     */
    E extractMax();

    /**
     * Determine whether this key is in the pq.
     * @param key The key to look for.
     * @return True if this key is in the pq, false otherwise.
     */
    boolean contains(E key);

    /**
     * Indicate that the priority of a key at a given key
     * has increased, which may affect the internal storage
     * of the pq.
     * @param key The key whose priority has changed.
     */
    void increaseKey(E key);
    
    /**
     * Indicate that the priority of a key at a given key
     * has decreased, which may affect the internal storage
     * of the pq.
     * @param key The key whose priority has changed.
     */
    void decreaseKey(E key);

}
