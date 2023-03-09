package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;

/**
 * SeparateChainingHashMap
 * 
 * A simple implementation of the separate chaining
 * approach to hashing.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * May 19, 2017
 * @param <K> The key-type of the map
 * @param <V> The value-type of the map
 */
public class SeparateChainingHashMap<K, V> implements Map<K, V> {

    /**
     * Nested class to represent a key-value pair, 
     * which is also a node in the linked
     * list of pairs.
     */
    protected static class Pair<K,V> {
        K key;
        V value;
        Pair<K,V> next;
        Pair(K key, V value, Pair<K,V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    /**
     * The table itself
     */
    protected Pair<K,V>[] table;

    /**
     * The hash function
     */
    protected HashFunction<K> h;

    /**
     * The maximum proportion of the table we allow
     * to be filled before rehashing
     */
    private double loadFactor;

    /**
     * How full the table is; needs to be recorded
     * to know when to rehash.
     */
    private int numPairs;

    /**
     * Are we currently rehashing? Needed to make sure 
     * the rehash method isn't reentrant.
     */
    private boolean rehashing;

    /**
     * Plain constructor
     */
    @SuppressWarnings("unchecked")
    public SeparateChainingHashMap(){
        table = (Pair<K,V>[]) new Pair[41];
        h = HashFactory.plainOldHashFunction(table.length);
        loadFactor = 5;
        numPairs = 0;
        rehashing = false;

    }

    /**
     * Find the location where this key is, if anywhere,
     * as a reference to a pair.
     * @param key The key to search for.
     * @return The pair for this key, or null if none exists
     */
    private Pair<K,V> find(K key) {
         throw new UnsupportedOperationException();
    }

    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */   
    public void put(K key, V val) {
         throw new UnsupportedOperationException();
    }

    /**
     * Make the table bigger and rehash the elements.
     */ 
    @SuppressWarnings("unchecked")
    private void rehash() {
        assert !rehashing;
        rehashing = true;

        Pair<K,V>[] oldTable = table;
        table = (Pair<K,V>[]) new Pair[PrimeSource.nextOrEqPrime(oldTable.length*2)];
        h = HashFactory.plainOldHashFunction(table.length);
        int oldNumPairs = numPairs;
        numPairs = 0;

        for (Pair<K,V> p : oldTable)
            while(p != null) {
                put(p.key, p.value);
                p = p.next;
            }

        assert numPairs == oldNumPairs;

        rehashing = false;
    }


    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public V get(K key) {
        Pair<K,V> p = find(key);
        if (p == null) return null;
        else return p.value;
    }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */   
    public boolean containsKey(K key) {
        return find(key) != null;
    }

    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    public void remove(K key) {
         throw new UnsupportedOperationException();
   }

    /**
     * Produce an iterator for the keys of this map.
     */
    public Iterator<K> iterator() {
         throw new UnsupportedOperationException();
    }
}
