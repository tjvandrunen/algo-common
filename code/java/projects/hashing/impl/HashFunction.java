package impl;

/**
 * HashFunction
 * 
 * Interface for hash functions as function objects.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * Mar 17, 2015
 * @param <K> The key type that we are hashing.
 */

public interface HashFunction<K> {
    /**
     * Compute a hash for the given key.
     * @param key
     * @return A hash value; the range is dependent on the class or function object.
     */
    int hash(K key);

}
