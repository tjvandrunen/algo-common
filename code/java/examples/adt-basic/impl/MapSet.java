package impl;

import java.util.Iterator;

import adt.Set;
import adt.Map;

/**
 * MapSet
 * 
 * An implementation of Set that uses a Map as its
 * underlying implementation
 * 
 * Algorithmic Commonplaces
 * Spring 2016
 * @param <E> The base-type of the set
 */
public class MapSet<E> implements Set<E> {

    /**
     * The internal representation. Note this can be any 
     * map implementation. 
     */
    private Map<E, String> internal;
    
    public MapSet() {
        this.internal = new ArrayMap<E,String>();
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
     * Add an item to the set. (Do nothing if the item is 
     * already there.)
     * @param item The item to add
     */
    public void add(E item) {
        //begin solution, replace with: throw new UnsupportedOperationException();
        internal.put(item,  "");
        //end solution
    }

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the set, false otherwise
     */
    public boolean contains(E item) {
        //begin solution, replace with: throw new UnsupportedOperationException();
        return internal.containsKey(item);
        //end solution
    }

    /**
     * Remove an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
   public void remove(E item) {
       //begin solution, replace with: throw new UnsupportedOperationException();
       internal.remove(item);
       //end solution
    }

   /**
    * The number of itmes in the set
    * @return The number of items.
    */
    public int size() {
        //begin solution, replace with: throw new UnsupportedOperationException();
        int count = 0;
        for (Iterator<E> it = internal.iterator(); it.hasNext(); ){
            it.next();
            count++;
        }
       return count;
       //end solution
    }

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        //begin solution, replace with: throw new UnsupportedOperationException();
        return size() == 0;
        //end solution
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
