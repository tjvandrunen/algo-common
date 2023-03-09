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
         throw new UnsupportedOperationException();
    }

    /**
     * Add an item to the set. (Do nothing if the item is 
     * already there.)
     * @param item The item to add
     */
    public void add(E item) {
         throw new UnsupportedOperationException();
    }

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the set, false otherwise
     */
    public boolean contains(E item) {
         throw new UnsupportedOperationException();
    }

    /**
     * Remove an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */
   public void remove(E item) {
        throw new UnsupportedOperationException();
    }

   /**
    * The number of itmes in the set
    * @return The number of items.
    */
    public int size() {
         throw new UnsupportedOperationException();
    }

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
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
