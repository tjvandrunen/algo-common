package impl;

import java.util.Iterator;

import adt.Bag;
import adt.List;

/**
 * ListBag
 * 
 * An implementation of Bag using a List as the underlying
 * implementation.
 * 
 * Algorithmic Commonplaces
 * Spring 2016
 * @param <E> The base-type of the bag
 */
public class ListBag<E> implements Bag<E> {

    /**
     * The internal representation (can be any implementation of
     * List)
     */
    private List<E> internal;
    
    public ListBag() {
            internal = new MapList<E>();
    }
    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
   public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Add an item to the bag, increasing its count if it's
     * already there.
     * @param item The item to add
     */
    public void add(E item) {
         throw new UnsupportedOperationException();
    }

    /**
     * How many times does this bag contain this item?
     * @param item The item to check
     * @return The number of occurences of this item in the bag
     */
    public int count(E item) {
         throw new UnsupportedOperationException();
    }

    /**
     * Remove (all occurrences of) an item from the bag, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(E item) {
         throw new UnsupportedOperationException();
    }

    /**
     * The number of items in the bag. This is the sum
     * of the counts, not the number of unique items.
     * @return The number of items.
     */
    public int size() {
         throw new UnsupportedOperationException();
    }

    /**
     * Is the bag empty?
     * @return True if the bag is empty, false otherwise.
     */
    public boolean isEmpty() {
         throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (E item : this) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += item;
    		prefix = true;
    	}
    	return toReturn +"]";
    }

}
