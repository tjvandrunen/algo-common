package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.OrderedMap;

public class RecursiveBSTMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

    private static interface Node<KK extends Comparable<KK>,VV> extends Iterable<KK>{
        Node<KK,VV> put(KK key, VV val);
        VV get(KK key);
        Node<KK,VV> remove(KK key);
        boolean containsKey(KK key);
        KK min();
        KK max();
    }

    private class RealNode implements Node<K,V> {
        K key;
        V value;
        Node<K,V> left, right;
        public RealNode(K key, V value, Node<K,V> left, Node<K,V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
        public Node<K, V> put(K key, V val) {
            int compare = key.compareTo(this.key);
            if (compare < 0) left = left.put(key, val);
            else if (compare == 0)  this.value = val;
            else right = right.put(key, val);
            return this;
        }
        public boolean containsKey(K key) {
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
        public Node<K, V> remove(K key) {
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
            return this;
        }

        public Iterator<K> iterator() {
            return new Iterator<K>() {
                Iterator<K> lit = left.iterator();
                boolean mine = true;
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
        public K max() {
            if (right == nully) return key;
            else return right.min();
        }
    }


    private Node<K,V> nully = new Node<K,V>() {
        public Node<K, V> put(K key, V val) {
            return new RealNode(key, val, this, this);
        }

        public V get(K key) { return null; }

        public Node<K, V> remove(K key) {
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

        public K max() {
            throw new UnsupportedOperationException();
        }

    };

    private Node<K,V> root;

    public RecursiveBSTMap() {
        root = nully;
    }

    public void put(K key, V val) { root = root.put(key, val); }

    public V get(K key) { return root.get(key); }

    public boolean containsKey(K key) { return root.containsKey(key); }

    public void remove(K key) { root = root.remove(key); }


    public Iterator<K> iterator() {
        return root.iterator();
    }

    public K min() {
        return root.min();
    }

    public K max() {
        return root.max();
    }

}
