package adt;

/**
 * MultiMap
 * 
 * Interface to define a multi-map ADT
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */
public interface MultiMap<K,V> extends Iterable<K> {
    /**
     * Add an association for this key
     */
    void put(K key, V val);

    /**
     * Get all the values associated with this key. If
     * the key is not in this multimap, then the
     * returned iterator immediately has no next.
     */
    Iterable<V> get(K key);

    /**
     * Test whether there are any values associated with this key
     */
    boolean containsKey(K key);

    /**
     * Test whether the given value is one of the associations
     * of the given key.
     */
    boolean containsAssociation(K key, V val);

    /**
     * Remove a key and all of its associations
     */
    void removeKey(K key);

    /**
     * Remove a value from the associations of a given key.
     * If the key now has no associations, remove it also
     * from the multimap.
     */
    void removeAssociations(K key, V val);
}
