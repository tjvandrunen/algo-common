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
                //new ArrayList<Entry>();
                //new LinkedList<Entry>();
                //new BackwardsLinkedList<Entry>();
                new WimpList<Entry>();
    }

    private Entry findEntry(K key) {
        Entry assoc = null;
        for (int i = 0; i < internal.size() && assoc == null; i++) {
            Entry current = internal.get(i);
            if (current.key.equals(key))
                assoc = current;
        }
        return assoc;
    }

    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     * @throws FullContainerException
     */
    public void put(K key, V val) {
        Entry old = findEntry(key);
        if (old == null)
            internal.add(new Entry(key, val));
        else
            old.val = val;
    }

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public V get(K key) {
        Entry assoc = findEntry(key);
        if (assoc == null)
            return null;
        else 
            return assoc.val;
    }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(K key) {
        return findEntry(key) != null;
    }

    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    public void remove(K key) {
        // findEntry() won't work because we need the index.
        int index = -1;
        for (int i = 0; i < internal.size() && index == -1; i++) {
            Entry current = internal.get(i);
            if (current.key.equals(key))
                index = i;
        }
        if (index != -1)
            internal.remove(index);
    }

    /**
     * Iterate over keys.
     */
    public Iterator<K> iterator() {

        // We rely on the iterator of the internal list.
        final Iterator<Entry> interIter = internal.iterator();

        // This iterator is really just a Decorator (hey,
        // a design pattern!) for the internal's iterator.
        // Specifically its next() grabs the key from the entry.
        return new Iterator<K>() {
            public boolean hasNext() {
                return interIter.hasNext();
            }
            // If there isn't a next, interIter.next() will
            // throw the appropriate exception
            public K next() {
                return interIter.next().key;
            }
            // If by chance the internal list's iterator supports
            // remove, this should work fine. Otherwise,
            // the appropriate exception will be passed on.
            public void remove() {
                interIter.remove();
            }
        };
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
