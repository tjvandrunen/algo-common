package impl; 

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;

/**
 * OpenAddressingHashMap
 * 
 * A reboot of the linear-probing version, made so it can
 * be parameterized by probe strategy.
 * 
 * Most things that otherwise would be private are protected
 * so that the child class that optimizes removal
 * for linear probing has access to that stuff.
 *
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * May 18, 2017
 * @param <K> The key-type of the map
 * @param <V> The value-type of the map
 */
public class OpenAddressingHashMap<K, V> implements Map<K, V> {

    // --- nested classes ---

    // has to be static because of interaction between generics,
    // arrays, and nested classes
    /**
     * Class to represent pairs (associations) between keys
     * and values. This has to be static because of the interaction
     * between generics, arrays, and nested classes in Java, 
     * since there's no way to create an array of an implicitly
     * generic non-static member class, nor is an array of a super
     * type assignable to a variable of type array of an implicitly
     * generic non-static member class.
     */
    protected static class Pair<K,V> {
        K key;
        V value;
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    
    /**
     * A strategy type for probe sequencing. See CLRS pg 272
     * and TAOCP 6.4 (Vol III. p 526-531).
     * Since this depends on how the table is sized,
     * decisions about sizing the table also are delegated 
     * to this strategy. Should be an interface, but has to 
     * be an abstract class because interfaces can't be 
     * non-static, and I want this to be non-static so
     * it can be implicitly generic.
     */
    protected abstract class ProbeStrategy {
        /**
         * Produce a probe sequence for this key.
         * Note that under intended use in open addressing,
         * these iterators will never be used up (ie, never
         * get to the point where hasNext returns false):
         * the table is never full, so we'll get to an
         * empty location before we've searched the entire
         * table.
         * @param key The key to search for
         * @return The probe sequence, as an iterator
         */
        abstract Iterator<Integer> probe(K key);
        /**
         * Produce an appropriate initial size for a table
         * using this strategy.
         * @return The table's initial size
         */
        abstract int initialSize();
        /**
         * Produce an appropriate larger size when the
         * table needs to grow (about double, but depending
         * on constraints).
         * @param oldSize
         * @return
         */
        abstract int resize(int oldSize);
    }

    /**
     * Linear probing strategy. Works best with prime-number
     * sized table.
     */
    private class LinearProbing extends ProbeStrategy {
        public Iterator<Integer> probe(K key) {
            return new Iterator<Integer>() {
                /**
                 * The starting position for this probe in
                 * the array.
                 */
                int origin = h.hash(key);

                /**
                 * The distance from the origin of the next
                 * position to return. Invariant: offset
                 * equals the number of time next() has been
                 * called.
                 */
                int offset = 0;

                public boolean hasNext() {
                    return offset < table.length;
                }
                public Integer next() {
                    return (origin + offset++) % table.length;
                }
            };
        }
        
        /**
         * The initial size of the table is an arbitrarily-chosen
         * small prime number
         */
        int initialSize() {
            return 41;
        }        

        /**
         * When a table is rehashed, double its size and then find
         * the next greatest prime number.
         */
        int resize(int oldSize) {
            return PrimeSource.nextOrEqPrime(oldSize * 2);
        }

    }

    /**
     * Quadratic probing strategy. Needs a power of
     * two for the table size. See CLRS Problem 11-3
     * (pg 283) for details.
     */
    private class QuadraticProbing extends ProbeStrategy {
        Iterator<Integer> probe(K key) {
            final int start = h.hash(key);
            return new Iterator<Integer>() {
                int i = 0;
                public boolean hasNext() {
                    return i < table.length;
                }
                public Integer next() {
                    int toReturn = ((int) (start + .5*i*i + .5 * i)) % table.length;
                    i++;
                    return toReturn;
                }
                
            };
        }

        int initialSize() {
            return 32;
        }

        int resize(int oldSize) {
            return oldSize * 2;
        }
        
    }

    /**
     * Double hashing. Works best with prime-number
     * sized table.
     */
    private class DoubleHashing extends ProbeStrategy {

        /**
         * Secondary hash function
         */
        HashFunction<K> h2;
        
        DoubleHashing() {
            // Uses Gary Knott's solution described
            // on TAOCP III.529; cf CLRS pg 273.
            h2 = new HashFunction<K>() {
                public int hash(K key) {
                    int firstHash = h.hash(key);
                    if (firstHash == 0) return 1;
                    else return table.length - firstHash;
                }
            };
        }

        Iterator<Integer> probe(K key) {
            // result of h(k)
            final int a = h.hash(key);
            // result of h2(k)
            final int b = h2.hash(key);
            return new Iterator<Integer>() {
                int i = 0;
                public boolean hasNext() {
                    return i < table.length;
                }
                public Integer next() {
                    return  (a + i++ * b) % table.length;
                }
                
            };
        }

        int initialSize() {
            return 41;
        }

        int resize(int oldSize) {
            return PrimeSource.nextOrEqPrime(oldSize * 2);
        }
        
    }
    
    
    // --- the real class begins here ---

    /**
     * The table itself
     */
    protected Pair<K,V>[] table;

    /**
     * The hash function
     */
    protected HashFunction<K> h;

    /**
     * The probe strategy object. (I suppose this
     * could have been called "probe" since probe
     * is also a noun (as in "The alien approached me
     * with his probe"), but probe.probe() sounded
     * weird, and I wanted to call the iterator variable
     * "probe."
     */
    protected final ProbeStrategy prober;

    /**
     * The maximum proportion of the table we allow
     * to be filled before rehashing
     */
    private double loadFactor;
    
    /**
     * Sentinel object to indicate a place in the
     * table where a pair has been removed. In this
     * class, we remove pairs by replacing them with
     * this object, which is treated differently from
     * null in that indicates the continuation of a chain.
     * We do this because the algorithm for removal
     * works only for linear probing.
     */
    protected final Pair<K,V> deleted;

    /**
     * How full the table is; needs to be recorded
     * to know when to rehash.
     */
    protected int numPairs;

    /**
     * How many table positions are marked with a dummy
     * "deleted" value. Also needed to know when to rehash.
     * Although numPairs indicates how many positions are
     * unavailable for inserting a new item, the table is
     * full from the perspective of the find method when
     * all positions have either a pair or a dummy.
     */
    protected int deleteds;
    
    /**
     * Are we currently rehashing? Needed to make sure 
     * the rehash method isn't reentrant.
     */
    private boolean rehashing;
    
    public static final int LINEAR_PROBING = 1;
    public static final int QUADRATIC_PROBING = 2;
    public static final int DOUBLE_HASHING = 3;
    /**
     * Constructor that allows the selection of a probe strategy.
     * @param probeStrategy The strategy to use: 1 for linear
     * probing, 2 for quadratic probing, and 3 for double hashing.
     */
    @SuppressWarnings("unchecked")
    public OpenAddressingHashMap(int probeStrategy) {
        switch (probeStrategy) {
        case 1: prober = new LinearProbing(); break;
        case 2: prober = new QuadraticProbing(); break;
        case 3: prober = new DoubleHashing(); break;
        default: prober = null; assert false;
        }
        table = (Pair<K,V>[]) new Pair[prober.initialSize()];
        h = HashFactory.plainOldHashFunction(table.length);
        loadFactor = .75;
        deleted = new Pair<K,V>(null, null);
        numPairs = 0;
        deleteds = 0;
        rehashing = false;
    }

    /**
     * Default constructor that chooses linear probing.
     */
    public OpenAddressingHashMap() { this(1); }
    
    /**
     * Find the location where this key is, if anywhere.
     * Not appropriate as a helper for the put method,
     * as this is not specified to return a position where
     * the key could go.
     * @param key The key to search for.
     * @return The location where the key is, or -1 if nowhere.
     */
    protected int find(K key) {
        if (key == null) return -1;
        Iterator<Integer> probe = prober.probe(key);
        int i;
        do {
            assert probe.hasNext();
            i = probe.next();
        } while(table[i] != null && ! key.equals(table[i].key));
        if (table[i] == null) return -1;
        else return i;
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
    synchronized private void rehash() {
        assert !rehashing;
        rehashing = true;
        int oldNumPairs = numPairs;

         // add code here

        assert numPairs == oldNumPairs;
        rehashing = false;
         throw new UnsupportedOperationException();
    }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */   
    public boolean containsKey(K key) {
        return find(key) != -1;
    }

    /**
     * Remove the association for this key, if it exists.
     * @param key The key to remove
     */
    public void remove(K key) {
        int i = find(key);
        if (i != -1 && table[i] != deleted) {
            assert key.equals(table[i].key);
            table[i] = deleted;
            numPairs--;
            deleteds++;
        }
    }

    /**
     * Produce an iterator for the keys of this map.
     */
    public Iterator<K> iterator() {
         throw new UnsupportedOperationException();
    }

    public String toString() {
        String toReturn = "";
        for (Pair<K,V> p : table)
            if (p == null) toReturn += ".";
            else toReturn += "(" + p.key + ")";
        return toReturn;
    }
    
}
