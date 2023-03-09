package impl;

import java.util.Iterator;

import adt.List;
import adt.Map;

/**
 * Map
 * 
 * Class to implement the Map ADT using a simple list.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <K> The key-type of the map
 * @param <V> The value-type of the map
 */


public class ListMap<K, V> implements Map<K, V> {

    private class Entry {
        K key;
        V val;
        Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }
        @Override
        public String toString() {
        	return key + "=" +val;
        }
    }

    private List<Entry> internal;

    public ListMap() {
        internal = 
                new ArrayList<Entry>();
                //new LinkedList<Entry>();
                //new BackwardsLinkedList<Entry>();
                //new WimpList<Entry>();
    }

    // recommended helper function
    private Entry findEntry(K key) {
         throw new UnsupportedOperationException();
    }

    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     * @throws FullContainerException
     */
    public void put(K key, V val) {
         throw new UnsupportedOperationException();
    }

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public V get(K key) {
         throw new UnsupportedOperationException();
    }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(K key) {
         throw new UnsupportedOperationException();
    }

    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    public void remove(K key) {
        // findEntry() won't work because we need the index.
         throw new UnsupportedOperationException();
    }

    /**
     * Iterate over keys.
     */
    public Iterator<K> iterator() {
         throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (Entry item : internal) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += item;
    		prefix = true;
    	}
    	return toReturn +"]";
    }
    
}
