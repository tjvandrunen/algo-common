package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.OrderedMap;
import adt.Stack;

/**
 * IterativeBSTMap
 * 
 * Class to implement an ordered map using a linked BST
 * with operations implemented iteratively.
 * 
 * @author Thomas VanDrunen
 * Feb 3, 2018
 */
public class IterativeBSTMap<K extends Comparable<K>,V> implements OrderedMap<K,V> {

    /**
     * Class standing for nodes in the tree.
     */
    private class Node {
        K key;
        V value;
        Node left, right;
        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
                   
        }
    }
    
    public Node root;

    private Node find(K key) {
        assert root != null;
        Node current = root;
        boolean foundIt = false;
        while (! foundIt) {
            int compare = key.compareTo(current.key);
            if (compare < 0) {
                if (current.left == null) foundIt = true;
                else current = current.left;
            }
            else if (compare == 0) foundIt = true;
            else {
                assert compare > 0;
                if (current.right == null) foundIt = true;
                else current = current.right;
            }
        }
        return current;
    }
    
    
    public void put(K key, V val) {
        // Case 1: The tree is empty, 
        // make a root with the given key
        if (root == null)
            root = new Node(key, val, null, null);
        else {
            Node placeOrParent = find(key);
            int compare = key.compareTo(placeOrParent.key);
            // Case 2: The given key comes before its parent,
            // insert new node to the left
            if (compare < 0) {
                assert placeOrParent.left == null;
                placeOrParent.left = new Node(key, val, null, null);
            }
            // Case 3: The given key is already present,
            // overwrite old value with given val
            else if (compare == 0) {
                assert placeOrParent.key.equals(key);
                placeOrParent.value = val;
            }
            // Case 4: The given key comes after its parent,
            // insert new node to the right
            else {
                assert compare > 0 && placeOrParent.right == null;
                placeOrParent.right = new Node(key, val, null, null);
            }
        }
    }

    public V get(K key) {
        if (root == null) return null;
        else {
            Node place = find(key);
            if (place.key.equals(key)) return place.value;
            else return null;
        }
    }

    public boolean containsKey(K key) {
        return root != null && find(key).key.equals(key);
    }

    public void remove(K key) {
        if (root != null) { // if the tree is empty, there is nothing to remove

            // -- Step 1: find the node containing the key to remove
            // Invariants: 
            // 1. (parent == null && current == root)
            //    || current = parent.left 
            //    || current = parent.right
            // 2. current == null || compare == key.compareTo(current.key)
            Node parent = null, current = root;
            int compare = key.compareTo(current.key);
            while (compare != 0) {
                parent = current;
                if (compare < 0) current = current.left;
                else current = current.right;
                if (current == null) compare = 0;
                else compare = key.compareTo(current.key);
            }
            // Exit condition: 
            // (key is not in the tree && current == null)
            // || (current.key.equals(key))
            
            if (current != null) { // if current is null, the key isn't in the tree

                // -- Step 2: Find the node that should be deleted from the tree
                
                // The node with the key to be removed
                Node remove = current;
                // The node to be deleted from the tree
                Node delete = remove;
                // If the node to be removed has two children,
                // then its successor should be deleted
                if (remove.left != null && remove.right != null) {
                    parent = current;
                    current = current.right;

                    // Invariants:
                    // current = parent.left || current = parent.right
                    while (current.left != null) {
                        parent = current;
                        current = current.left;
                    }
                    // Exit condition:
                    // current has no left child
                    assert current.left == null;
                    
                    delete = current;
                }

                // -- Step 3: fix up the tree
                
                // If the node with the key to be removed is different
                // from the node to be deleted, overwrite the
                // key and value to be removed
                if (delete != remove) {
                    remove.key = delete.key;
                    remove.value = delete.value;
                }
                assert delete.left == null || delete.right == null;

                // Find the replacement for the deleted node
                Node replacement = null;
                if (delete.left != null) 
                    replacement = delete.left;
                else if (delete.right != null)
                    replacement = delete.right;

                // Delete the node by replacing it with its replacement
                if (parent == null) 
                    root = replacement;
                else if (parent.left == delete)
                    parent.left = replacement;
                 else
                     parent.right = replacement;
                }
        }
    }

    public Iterator<K> iterator() {
        final Stack<Node> st = new LinkedStack<Node>();
        for (Node current = root; current != null; current = current.left)
            st.push(current);
        return new Iterator<K> () {
            public boolean hasNext() {
                return ! st.isEmpty();
            }

            public K next() {
                if (! hasNext()) throw new NoSuchElementException();
                else {
                    Node toReturn = st.pop();
                    for (Node current = toReturn.right; 
                            current != null; current = current.left)
                        st.push(current);
                    return toReturn.key;
                }
                    
            }
            
        };
    }

    public K min() {
        if (root == null) throw new NoSuchElementException();
        else {
            Node current = root;
            while (current.left != null)
                current = current.left;
            return current.key;
        }
    }

    public K max() {
        if (root == null) throw new NoSuchElementException();
        else {
            Node current = root;
            while (current.right != null)
                current = current.right;
            return current.key;
        }

    }

    
    
}
