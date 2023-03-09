package adt;

/**
 * Map
 * 
 * Interface to serve as an example of the map ADT.
 * Unlike Stack and Queue, this is not specified to
 * throw NoSuchElementException when get() or remove()
 * are called with non-existent keys. Instead get()
 * returns null and remove() does nothing. The only
 * reason for this decision is that that's what the tests
 * for Maps that I already had assumed. Similarly put() doesn't
 * throw a FullContainerException.
 * 
 * If overridden, the toString method for ampp should return
 * a comma separated list of the key-value of the stack in the 
 * format key=value. The list should be bordered by square braces. 
 * For instance, if  a map contained the keys A, B, and C with respective
 * values X, Y, and Z, it could return "[A=X, B=Y, C=Z]".
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * July 9, 2014
 * @param <K> The key-type of the map
 * @param <V> The value-type of the map
 */


public interface Map<K, V> extends Iterable<K> {

    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    void put(K key, V val);

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    V get(K key);

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    boolean containsKey(K key);


}
