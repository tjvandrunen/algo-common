package impl;


import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;
import adt.Stack;

/**
 * OptimalBSTMap
 * 
 * An implementation for map using a BST that is optimal for
 * its keys---that is, it is not necessarily balanced, but rather
 * the keys with the highest probability are nearest to the top.
 * The claim of optimality depends on how the tree is build,
 * and the building of it is handled by a separate factory class.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * Feb 25, 2015
 */

public class OptimalBSTMap implements Map<String, String> {

    /**
     * Common supertype for real (internal) nodes and
     * dummy (leaf) nodes.
     */
    protected static interface Node {
        /**
         * Retrieve the value, if any, for this key as found
         * in the subtree rooted here.
         */
        String get(String key);

        /**
         * Test whether this key exists in the subtree rooted here.
         */
        boolean containsKey(String key);

        /**
         * Compute the depth in this subtree of the node 
         * containing the given key or the  dummy node corresponding 
         * to where the key would be.
         * @param key
         * @return
         */
        int keyDepth(String key);
    }

    /**
     * Class to model internal (real) nodes.
     */
    protected static class Internal implements Node {
        final Node left, right;
        final String key, value;
        Internal(Node left, String key, String val, Node right) {
            this.left = left;
            this.key = key;
            this.value = val;
            this.right = right;
        }
        public String get(String key) {
            int compare = key.compareTo(this.key);
            if (compare < 0) return left.get(key);
            else if (compare == 0) return value;
            else {
                assert compare > 0;
                return right.get(key);
            }
        }
        public boolean containsKey(String key) {
            int compare = key.compareTo(this.key);
            if (compare < 0) return left.containsKey(key);
            else if (compare == 0) return true;
            else {
                assert compare > 0;
                return right.containsKey(key);
            }

        }
        public int keyDepth(String key) {
            int compare = key.compareTo(this.key);
            if (compare < 0) return left.keyDepth(key) + 1;
            else if (compare == 0) return 0;
            else {
                assert compare > 0;
                return right.keyDepth(key) + 1;
            }
        }

        public String toString() { return "(" + left + key + right + ")"; }
    }

    /**
     * Singleton class for leaf (dummy) nodes.
     */
    protected static final Node dummy = new Node() {
        public String get(String key) {
            return null;
        }
        public boolean containsKey(String key) {
            return false;
        }
        public int keyDepth(String key) {
            return 0;
        }
        public String toString() { return "*"; }
    };


    /**
     * The root of this tree.
     */
    private Node root;
    
    /**
     * Constructor. It is protected because it should be called
     * only by OptimalBSTMapFactory in this package, which constructs
     * the tree itself from nodes before calling this constructor.
     * @param root The root of this (fully-formed) tree
     */
    protected OptimalBSTMap(Node root) { this.root = root; }

    /**
     * Unsupported. You cannot change a tree after it is built.
     * (In principle we should allow puts for existing keys;
     * I left that out for simplicity.)
     */
    public void put(String key, String val) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public String get(String key) {
        return root.get(key);
    }

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(String key) {
        return root.containsKey(key);
    }
    
    /**
     * Compute the "node-based depth" (as opposed to
     * the traditional link-based depth) for a key:
     * the number of nodes that need to be visited to
     * find the node containing this key or the dummy
     * where this key would go.
     */
    public int nodesVisited(String key) {
        return root.keyDepth(key) + 1;
    }
    
    public String toString() {
        return root.toString();
    }

    public Iterator<String> iterator() {
        final Stack<Internal> st = new ListStack<Internal>();
        for (Node current = root; current != dummy; 
                current = ((Internal) current).left)
            st.push((Internal) current);
        return new Iterator<String>() {

            public boolean hasNext() {
                return ! st.isEmpty();
            }

            public String next() {
                if (st.isEmpty()) throw new NoSuchElementException();
                Internal nextNode = st.pop();
               
                for (Node current = nextNode.right; 
                           current != dummy;
                           current = ((Internal) current).left)
                       st.push((Internal) current);
               return nextNode.key;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };

    }

    @Override
    public void remove(String key) {
        throw new UnsupportedOperationException();
        
    }


}
