package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Map;

public class BSTMap<K extends Comparable<K>, V, I extends NodeInfo> implements Map<K,V> {

    private int ckCount;
    
    /**
     * Supertype defining services nodes support.
     * Conceptually this is an interface, but we use an 
     * abstract class instead so that it can be implicitly
     * generic. Some of these are not supported by the
     * null node. They are put in this supertype so that they
     * are visible to external balancing algorithms although the
     * RealNode class is kept private. This should be fine because 
     * correct balancing algorithms never call methods like
     * getLeft() on a null node.
     */
    public abstract class Node implements Iterable<K> {
        // --- Map operations supported recursively in the node ---
        abstract Node put(K key, V val);
        abstract V get(K key);
        abstract Node remove(K key);
        abstract boolean containsKey(K key);
        abstract K min();

        /**
         * Retrieve the bookkeeping information used by the
         * balancing scheme. 
         */
        public abstract I getInfo(); // find better name than "getInfo"?
        
        // Access to the children of this node,
        // as needed by the balancing schemes   
        public abstract Node getLeft();
        public abstract Node getRight();
        public abstract void setLeft(Node left);
        public abstract void setRight(Node right);
        
        // --- Rotations at this level ---
        /**
         * Rotate the subtree rooted at this node to the left
         * @return The root of the resulting subtree
         */
        abstract Node rotateLeft();

        /**
         * Rotate the subtree rooted at this node to the left
         * @return The root of the resulting subtree
         */
        abstract Node rotateRight();

        // --- Rotations one level down ---
        /**
         * Rotate the subtree rooted at the left child of this
         * node to the right.
         */
        abstract void leftRotateRight();
        /**
         * Rotate the subtree rooted at the left child of this
         * node to the right.
         */
        abstract void rightRotateRight();
        /**
         * Rotate the subtree rooted at the left child of this
         * node to the right.
         */
        abstract void leftRotateLeft();
        /**
         * Rotate the subtree rooted at the left child of this
         * node to the right.
         */
        abstract void rightRotateLeft();

        /**
         * Is this node null?
         */
        public abstract boolean isNull();
        
        
        // --- Operations for computing statistics about tree structure ---
        protected abstract int height();
        protected abstract int totalDepth(int depth);
        protected abstract int leaves();
    }

    private class RealNode extends Node {
        K key;
        V value;
        Node left, right;
        I info;
        public RealNode(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            info = balancer.newInfo(this);
            info.recompute();
        }
        public Node put(K key, V val) {
            int compare = key.compareTo(this.key);
            if (compare < 0) left = left.put(key, val);
            else if (compare == 0)  this.value = val;
            else right = right.put(key, val);
            return balancer.putFixup(this);
        }
        public boolean containsKey(K key) {
            ckCount++;
            int compare = key.compareTo(this.key);
            if (compare < 0) return left.containsKey(key);
            else if (compare == 0)  return true;
            else return right.containsKey(key);
        }
        public V get(K key) {
            int compare = key.compareTo(this.key);
            if (compare < 0) return left.get(key);
            else if (compare == 0)  return value;
            else return right.get(key);
        }
        public Node remove(K key) {
            int compare = key.compareTo(this.key);
            // 1. The key to remove is to the left
            if (compare < 0) left = left.remove(key);
            // 2. The key to remove is here
            else if (compare == 0) {
                // 2a. Zero children or right is only child
                if (left == nully) return right;
                // 2b. Left is only child
                else if (right == nully) return left;
                // 2c. Two children
                else {
                    // Get the successor key
                    this.key = right.min();
                    // Get the successor key's value
                    value = right.get(this.key);
                    // Delete successor node by removing
                    // successor key from right subtree
                    right = right.remove(this.key);
                }
            }
            // 3. The key to remove is to the right
            else right = right.remove(key);
            // In any case...
            return balancer.removeFixup(this);
        }

        public Iterator<K> iterator() {
            return new Iterator<K>() {
                // Iterator over the keys in the left child
                Iterator<K> lit = left.iterator();
                // Does the key at this node still need to be returned?
                boolean mine = true;
                // Iterator over the keys in the right child
                Iterator<K> rit = right.iterator();

                public boolean hasNext() {
                    return lit.hasNext() || mine || rit.hasNext();
                }
                
                public K next() {
                    if (! hasNext()) throw new NoSuchElementException();
                    if (lit.hasNext()) return lit.next();
                    else if (mine) {
                        mine = false;
                        return key;
                    }
                    else {
                        assert rit.hasNext();
                        return rit.next();
                    }
                }

            };
        }
        public K min() {
            if (left == nully) return key;
            else return left.min();
        }
        public I getInfo() {
            return info;
        }
        public Node rotateLeft() {
            @SuppressWarnings("unchecked")
            RealNode oldRight = (RealNode) right;
             // add code here
            return oldRight;
        }
        public Node rotateRight() {
            @SuppressWarnings("unchecked")
            RealNode oldLeft = (RealNode) left;
             // add code here
            return oldLeft;
        }
        public void leftRotateRight() {
            left = left.rotateRight();
            info.recompute();
        }
        public void rightRotateRight() {
            right = right.rotateRight();
            info.recompute();
        }
        public void rightRotateLeft() {
            right = right.rotateLeft();
            info.recompute();
        }
        public void leftRotateLeft() {
            left = left.rotateLeft();
            info.recompute();
        }
        public Node getLeft() { return left; }
        public Node getRight() { return right; }
        public void setLeft(Node left) { this.left = left; }
        public void setRight(Node right) { this.right = right; }
        public String toString() {
            return "(" + key + info + left + right + ")";
        }
        public boolean isNull() { return false; }
        protected int height() {
            int lh = left.height(), rh = right.height();
            return 1 + (lh > rh?lh:rh);
        }
        protected int totalDepth(int depth) {
            return depth + left.totalDepth(depth + 1) + right.totalDepth(depth + 1);
        }
        protected int leaves() {
            if (left.isNull() && right.isNull()) return 1;
            else return left.leaves() + right.leaves();
        }
    }


    private Node nully = new Node() {
        public Node put(K key, V val) {
            return new RealNode(key, val, this, this);
        }

        public V get(K key) { return null; }

        public Node remove(K key) {
            return this;
        }

        public boolean containsKey(K key) {
            return false;
        }

        public Iterator<K> iterator() {
            return new Iterator<K> () {
                public boolean hasNext() {
                    return false;
                }
                public K next() {
                    throw new NoSuchElementException();
                }
            };
        }

        public K min() {
            throw new UnsupportedOperationException();
        }

        public I getInfo() { return nullInfo; }
        public Node rotateLeft() { throw new UnsupportedOperationException(); }
        public Node rotateRight() { throw new UnsupportedOperationException(); }
        public Node getLeft() { throw new UnsupportedOperationException(); }
        public Node getRight() { throw new UnsupportedOperationException(); }
        public void setLeft(Node left) { throw new UnsupportedOperationException(); }
        public void setRight(Node right) { throw new UnsupportedOperationException(); }
        void leftRotateRight() { throw new UnsupportedOperationException(); }
        void rightRotateRight() { throw new UnsupportedOperationException(); }
        void leftRotateLeft() { throw new UnsupportedOperationException(); }
        void rightRotateLeft() { throw new UnsupportedOperationException(); }
        public String toString() { return "*" + nullInfo.toString(); }
        public boolean isNull() { return true; }

        protected int height() {
            return 0;
        }

        protected int totalDepth(int depth) {
            return 0;
        }

        protected int leaves() {
            return 0;
        }
    };

    protected Node root;
    private Balancer<K,V,I> balancer;
    private I nullInfo;

    public BSTMap(Balancer<K,V,I> balancer) {
        root = nully;
        this.balancer = balancer;
        nullInfo = balancer.nullInfo(nully);
    }

    public void put(K key, V val) { 
        root = root.put(key, val);
        balancer.rootFixup(root);
    }

    public V get(K key) { return root.get(key); }

    public boolean containsKey(K key) { return root.containsKey(key); }

    public void remove(K key) { 
        root = root.remove(key); 
        balancer.rootFixup(root);
    }


    public Iterator<K> iterator() {
        return root.iterator();
    }

    public int height() { return root.height(); }
    public int totalDepth() { return root.totalDepth(1); }
    public int leaves() { return root.leaves(); }
    
    public String toString() { return root.toString(); }
    public String getBalancerClass() { return balancer.getClass().toString(); }

    public int getCkCount() { return ckCount; }
    public void resetCkCount() { ckCount = 0; }
}
