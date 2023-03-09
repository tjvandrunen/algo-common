package impl;

import java.util.Random;

/**
 * HashFactory
 * 
 * Class to contain static factory methods for various kinds 
 * of hash functions.
 *
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * March 19, 2015
 */

public class HashFactory {

    /**
     * Mask for removing the sign bit (more reliable than
     * Math.abs()).
     */
    private static int signBitMask;
    
    
    /**
     * Random number generator
     */
    private static Random randy; 

    /**
     * Static initializer
     */
    static {

        signBitMask = 0x7fffffff;

        
        randy = new Random();
    }

    /**
     * Make a hash function based on an object's hashcode() method,
     * mod by a given upper bound.
     * @param m The upper bound of the range (must be positive).
     * @return A hash function that takes a String and returns an int
     *         value in the range [0, m).
     * @param m 
     * @return
     */
    public static <K> HashFunction<K> plainOldHashFunction(final int m) {
        assert m > 0;
        return new HashFunction<K>() {
            public int hash(K key) {
                return (key.hashCode() & signBitMask) % m;
            }
        };
    }
    
    /**
     * Make a universal hash function with the given p and m parameters
     * for objects, using the object's inherent hashCode() method.
     * @param p A prime number
     * @param m The exclusive upper bound on the range of hash values
     * @return A hash function
     */
    public static HashFunction<Object> universalHashFunction(final int p, final int m) {
        final int a = m <= 1 ? 0 : randy.nextInt(p-1) + 1;
        final int b = m <= 1 ? 0 : randy.nextInt(p);
        return new HashFunction<Object>() {
            public int hash(Object key) {
                return (((a * key.hashCode()+ b) & 0x7fffffff) % p) % m;
            }
            public String toString() {
                return "h(" + a + "," + b +")";
            }
       };
    }
    
   /**
     * Make a universal hash function with the given p and m parameters
     * for objects, using the object's inherent hashCode() method,
     * but bitwise-and the keys' inherent hash codes by a given bit mask,
     * which reduces the range of hash code values (and hence allows for
     * a smaller p).
     * @param p A prime number
     * @param m The exclusive upper bound on the range of hash values
     * @param f A bit mask for the key's hash codes.
     * @return A hash function
     */
    public static HashFunction<Object> universalHashFunction(final int p, final int m,
            final int f) {
        final int a = m <= 1 ? 0 : randy.nextInt(p-1) + 1;
        final int b = m <= 1 ? 0 : randy.nextInt(p);
        return new HashFunction<Object>() {
            public int hash(Object key) {
                return ((a * (key.hashCode() & f) + b) % p) % m;
            }
            public String toString() {
                return "h(" + a + "," + b +")";
            }
        };
    }
    
}
