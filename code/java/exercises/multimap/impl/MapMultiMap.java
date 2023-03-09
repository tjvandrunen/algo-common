package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;
import adt.MultiMap;
import adt.Set;

public class MapMultiMap<K,V> implements MultiMap<K, V> {

    private Map<K,Set<V>> internal;
    
    public MapMultiMap() {
        internal = new ListMap<K, Set<V>>();
    }
    
    /**
     * Add an association for this key
     */
    public void put(K key, V val) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get all the values associated with this key. If
     * the key is not in this multimap, then the
     * returned iterator immediately has no next.
     */
    public Iterable<V> get(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Test whether there are any values associated with this key
     */
    public boolean containsKey(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Test whether the given value is one of the associations
     * of the given key.
     */
    public boolean containsAssociation(K key, V val) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove a key and all of its associations
     */
    public void removeKey(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove a value from the associations of a given key.
     * If the key now has no associations, remove it also
     * from the multimap.
     */
    public void removeAssociations(K key, V val) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
