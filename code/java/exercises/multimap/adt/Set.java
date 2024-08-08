package adt;

/**
 * Set
 * 
 * Interface to serve as an example of the set ADT.
 * 
 * If the toString method is overridden, it should return
 * a comma separated list of the elements of the set bordered
 * by curly braces. For instance, a set with elements A, B, and C
 * could return the String "{A, B, C}".
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <E> The base-type of the set
 */

public interface Set<E> extends Iterable<E> {

    /**
     * Add an item to the set. (Do nothing if the item is 
     * already there.)
     * @param item The item to add
     */
    void add(E item);

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the set, false otherwise
     */
    boolean contains(E item);

    /**
     * Remove an item from the set, if it's there;
     * ignore otherwise.
     * @param item The item to remove
     */
    void remove(E item);

    /**
     * The number of items in the set
     * @return The number of items.
     */
    int size();

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    boolean isEmpty();


}
