package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Bag;
import adt.Map;


/**
 * MapBag
 * 
 * Class to implement a bag using a map for a tally.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 10, 2014
 * @param <E> The base-type of the bag
 */
public class MapBag<E> implements Bag<E> {

    private Map<E, Integer> internal;

    public MapBag() {
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
     * @return True if the item is in the set, false otherwise
     */
    public int count(E item) {
         throw new UnsupportedOperationException();
    }

    /**
     * Remove (all occurrences of) an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(E item) {
         throw new UnsupportedOperationException();
    }

    /**
     * The number of items in the bag. This is the sum
     * of the counts, not the number of unique items.
     * Unfortunately using a map doesn't make this easy.
     * @return The number of items.
     */
    public int size() {
         throw new UnsupportedOperationException();
    }

    /**
     * Is the bag empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
         throw new UnsupportedOperationException();
    }

    /**
     * Iterator, the only hard method. This will return multiple
     * copies of the same item all in a row.
     * @return An iterator returning this bag's items the number
     * of times they're in the bag.
     */
    public Iterator<E> iterator() {
         throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	for (E item : this) {
    		toReturn += item + ", ";
    	}
    	return toReturn +"]";
    }
}
