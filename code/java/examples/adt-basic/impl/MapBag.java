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
     * @return True if the item is in the set, false otherwise
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
        // Iterator over the keys in the internal map
        final Iterator<E> interIter = internal.iterator();
        // The first item
        final E firstItem;
        // The number of copies of the first item
        final int firstCopies;

        // set up the first item and its frequency
        if (interIter.hasNext()) {
            firstItem = interIter.next();
            firstCopies = internal.get(firstItem);
        }
        // special case for empty bag
        else {
            firstItem = null;
            firstCopies = 0;
        }

        return new Iterator<E>() {
            // The item to be returned next,
            // null if we're at the end of the iteration
            E currentItem = firstItem;

            // the number of copies of the current item
            // still to be returned, 0 if we're at the
            // end of the iteration
            int copiesLeft = firstCopies;

            public boolean hasNext() {
                return copiesLeft != 0;
            }

            public E next() {
                // iteration all done
                if (copiesLeft == 0) {
                    assert currentItem == null;
                    throw new NoSuchElementException();
                }
                // this is not the last one of the current item
                else if (copiesLeft > 1) {
                    copiesLeft--;
                    return currentItem;
                }
                // this is the last copy of the current item;
                // get ready for the next item, if any
                else { 
                    assert copiesLeft == 1;

                    // old item; save it to return
                    E lastCopy = currentItem;

                    // there is a next item
                    if (interIter.hasNext()) {
                        currentItem = interIter.next();
                        copiesLeft = internal.get(currentItem);
                    }
                    // there isn't
                    else { 
                        currentItem = null;
                        copiesLeft = 0;
                    }

                    // return that old item
                    return lastCopy;
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
