package impl;


import adt.Map; 

public class SortedArrayMap<K extends Comparable<K>, V> extends ArrayMap<K, V> implements Map<K, V>  {

    // Invariant: The internal array is sorted by keys.
    
    /**
     * Return the index of the association containing the smallest
     * key less than or equal to the given key, or the first empty position
     * if no such association exists.
     */
    @Override
    protected int find(K key) {
        int low = 0, high = size;
        while (high - low > 0) {
            int mid = (low + high) / 2;
            int compar = key.compareTo(internal[mid].key);
            if (compar < 0) high = mid;
            else if (compar > 0) low = mid + 1;
            else {
                assert compar == 0;
                low = mid;
                high = mid;
            }
        }
        return low;
    }

    @Override
    public void put(K key, V val) {
        throw new UnsupportedOperationException();
        }
    }
    
    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    @Override
    public void remove(K key) {
        throw new UnsupportedOperationException();
    }

}
