package adt;

public interface OrderedMap<K extends Comparable<K>,V> extends Map<K,V> {
    
    /** 
     * Find the minimum key, if any
     * @return Return the key that comes before every other key, 
     * or null if empty
     */
    K min();
    
    /** 
     * Find the maximum key, if any
     * @return Return the key that comes after every other key, 
     * or null if empty
     */
    K max();


}
