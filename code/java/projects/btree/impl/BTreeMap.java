package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;

/**
 * BTreeMap
 * 
 * A simplified B-Tree implementation of a simplified map ADT.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * Oct 21, 2019
 */
public class BTreeMap<K extends Comparable<K>,V> implements Map<K,V> {

    /**
     * Exception class to signal that a split happened on a call to
     * put() on a node.
     */
    private static class SplitException extends Exception {
        private static final long serialVersionUID = 3834684944242782424L;
    }
    
    /**
     * Interface for nodes, implemented by RealNode and the singleton
     * class for the null object.
     */
    private abstract class Node implements Iterable<K> {
        abstract void put(K key, V val) throws SplitException;
        abstract V get(K key);
        abstract boolean containsKey(K key);
    }

    /**
     * Class for (real) B-tree nodes
     */
    private class RealNode extends Node {

        K[] keys;
        V[] values;
        Node[] children;
        int degree;

        // Invariant:
        // - keys.length == values.length == max_degree - 1
        // - children.length == max_degree
        // - max_degree / 2 <= degree <= max_degree
        // - all positions [0, degree-1) in keys and values are filled,
        //            all others null
        // - all positions [0, degree) in children are filled,
        //            all others null
        
        @SuppressWarnings("unchecked")
        RealNode() {
            keys = (K[]) new Comparable[maxDegree-1];
            values = (V[]) new Object[maxDegree - 1];
            children = new BTreeMap.Node[maxDegree];
            degree = 0;
        }

        /**
         * Find the index of the key that is the least upper bound
         * for the given key, or degree-1 if the given key is greater
         * than all keys here. The ideas is that the value returned
         * is the index of the key equal to the given key, if its exists
         * in this node, or the index of the child in which to continue
         * the search
         * @param key The key for which to search
         * @return The index where this key is or would be
         */
        int findHere(K key) {
            int start = 0, stop = degree-1;
            // Invariant: 
            //   -  0 <= start <= stop < degree
            //   -  all keys in positions less than start are less than the given key
            //   -  all keys in positions greater than stop are greater than the given key

            while (stop - start > 0) {
                int mid = (start + stop) / 2;
                int compare = key.compareTo(keys[mid]);
                if (compare < 0) stop = mid;
                else if (compare == 0) {
                    start = mid;
                    stop = mid;
                }
                else {
                    assert compare > 0;
                    start = mid+1;
                }
            }
            // Postcondition: start is position of the least upper bound key for
            // the given key
            return start;
        }
        
        /**
         * Add an association for a given key to the subtree rooted
         * at this node.
         * @param key The key to this association
         * @param val The value to which this key is associated
         */
        void put(K key, V val) throws SplitException {
            // The least upper bound index for this key
            int lub = findHere(key);
            // If the key is here, overwrite its value
            if (lub < degree - 1 && key.equals(keys[lub])) 
                values[lub] = val;
            // Otherwise try to insert it recursively in the appropriate child
            else
                try {
                    children[lub].put(key, val);
                } catch (SplitException e) {
                    // That child split. Need to handle it.

                     throw new UnsupportedOperationException();
                }
        }

        
        V get(K key) {
            int lub = findHere(key);
            if (lub < degree - 1 && key.equals(keys[lub])) return values[lub];
            else return children[lub].get(key);
        }

        boolean containsKey(K key) {
            int lub = findHere(key);
            if (lub < degree - 1 && key.equals(keys[lub])) return true;
            else return children[lub].containsKey(key);
        }

        /**
         * Iterate through the keys in this subtree
         */
        public Iterator<K> iterator() {
            return new Iterator<K>() {
                 throw new UnsupportedOperationException();
        }
        
        public String toString() {
            String toReturn = "[" + children[0].toString();
            for (int i = 1; i < degree; i++) {
                toReturn += keys[i-1].toString() + children[i].toString();
            }
            toReturn += "]";
            return toReturn;
        }
    }

    /**
     * Dummy object for a null node
     */
    private Node nully = new Node() {

        /**
         * "Split", which here really is just a signal that the
         * parent should attempt to absorb this new key and value.
         */
        void put(K key, V val)  throws SplitException { 
            splitLeftChild = this;
            splitRightChild = this;
            splitKey = key;
            splitValue = val;
            throw new SplitException();
        }

        V get(K key) { return null; }

        boolean containsKey(K key) { return false; }

        public Iterator<K> iterator() {
            return new Iterator<K>() {
                public boolean hasNext() { return false; }
                public K next() { throw new NoSuchElementException(); }
            };
        }

        public String toString() { return "(*)"; }
    };

    // --- Instance variables to hold the shards of a split ---
    private Node splitLeftChild, splitRightChild;
    private K splitKey;
    private V splitValue;

    /**
     * The maximum degree of any node. Nodes may have between floor(maxDegree / 2)
     * and maxDegree children, both inclusive; except the root, which may have as
     * few as two children
     */
    private int maxDegree;
    private Node root;

    
    public BTreeMap(int maxDegree) { 
        this.maxDegree = maxDegree; 
        this.root = nully;
    }

    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    public void put(K key, V val) { 
        try {
            root.put(key, val);
        } catch (SplitException e) {
            // If the root splits, start a new root with degree 2
             throw new UnsupportedOperationException();
        } 
    }

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
   public V get(K key) { return root.get(key); }

   /**
    * Test if this map contains an association for this key.
    * @param key The key to test.
    * @return true if there is an association for this key, false otherwise
    */
    public boolean containsKey(K key) { return root.containsKey(key); }    
    
    /**
     * Iterate over the keys
     */
    public Iterator<K> iterator() { return root.iterator(); }

    public String toString() { return root.toString(); }
}
