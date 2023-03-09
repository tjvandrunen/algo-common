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
 * Recall that our Bag interface differs from what Sedgewick 
 * calls a "bag" (but he's wrong). 
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

    //begin example
    public ListBag(int implementation) {
        switch (implementation) {
            case 0:
                internal = new ArrayList<E>();
                break;
            case 1: 
                internal = new BackwardsLinkedList<E>();
                break;
            case 2:
                internal = new LinkedList<E>();
                break;
            case 3:
                internal = new WimpList<E>();
                break;
        }
    }
    //end example
    
    public ListBag() {
            internal = new MapList<E>();
    }
    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
   public Iterator<E> iterator() {
       //begin solution, replace with: throw new UnsupportedOperationException();
        return internal.iterator();
        //end solution
    }

    /**
     * Add an item to the bag, increasing its count if it's
     * already there.
     * @param item The item to add
     */
    public void add(E item) {
        //begin solution, replace with: throw new UnsupportedOperationException();
        internal.add(item);
        //end solution
    }

    /**
     * How many times does this bag contain this item?
     * @param item The item to check
     * @return The number of occurences of this item in the bag
     */
    public int count(E item) {
        //begin solution, replace with: throw new UnsupportedOperationException();
        int tally = 0;
        for (Iterator<E> it = iterator(); it.hasNext(); )
            if (it.next().equals(item))
                tally++;
        return tally;
        //end solution
    }

    /**
     * Remove (all occurrences of) an item from the bag, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
    public void remove(E item) {
        //begin solution, replace with: throw new UnsupportedOperationException();
        for (int i = 0; i < internal.size(); ) {
            if (internal.get(i).equals(item))
                internal.remove(i);
            else
                i++;
        }
        //end solution
    }

    /**
     * The number of items in the bag. This is the sum
     * of the counts, not the number of unique items.
     * @return The number of items.
     */
    public int size() {
        //begin solution, replace with: throw new UnsupportedOperationException();
        return internal.size();
        //end solution
    }

    /**
     * Is the bag empty?
     * @return True if the bag is empty, false otherwise.
     */
    public boolean isEmpty() {
        //begin solution, replace with: throw new UnsupportedOperationException();
        return internal.size() == 0;
        //end solution
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
