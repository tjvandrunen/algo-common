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
 * CSCI 345, Wheaton College
 * July 10, 2014
 * @param <E> The base-type of the bag
 */
public class MapBag<E> implements Bag<E> {

    private Map<E, Integer> internal;

    public MapBag() {
        internal = new ListMap<E, Integer>();
    }

    /**
     * Add an item to the bag, increasing its count if it's
     * already there.
     * @param item The item to add
     */
    public void add(E item) {
        int oldCount;
        if (internal.containsKey(item))
            oldCount = internal.get(item);
        else
            oldCount = 0;
        internal.put(item, oldCount + 1);
    }

    /**
     * How many times does this bag contain this item?
     * @param item The item to check
     * @return The number of times this item is in the bag
     */
    public int count(E item) {
        if (internal.containsKey(item))
            return internal.get(item);
        else
            return 0;
    }

    /**
     * Remove (all occurrences of) an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(E item) {
        internal.remove(item);
    }

    /**
     * The number of items in the bag. This is the sum
     * of the counts, not the number of unique items.
     * Unfortunately using a map doesn't make this easy.
     * @return The number of items.
     */
    public int size() {
        int total = 0;
        for (E key : internal)
            total += internal.get(key);
        return total;
    }

    /**
     * Is the bag empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Iterator, the only hard method. This will return multiple
     * copies of the same item all in a row.
     * @return An iterator returning this bag's items the number
     * of times they're in the bag.
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<E> internalIt = internal.iterator();
            E item = internalIt.hasNext() ? internalIt.next(): null;
            int count = item == null? 0 : internal.get(item);
            public boolean hasNext() {
                return item != null;
            }
            public E next() {
                if (! hasNext()) throw new NoSuchElementException();
                else {
                    E toReturn = item;
                    count--;
                    if (count == 0) {
                        item = internalIt.hasNext() ? internalIt.next(): null;
                        count = item == null? 0 : internal.get(item);
                    }
                    return toReturn;
                }
            }
            
        };
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
