package linkedheap;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;

/**
 * LinkedHeapPriorityQueue
 *
 * Class to implement a priority queue using the heap
 * abstraction implemented as a linked tree.
 *
 */
public class LinkedHeapPriorityQueue<K> implements PriorityQueue<K> {

    /**
     * Class for the nodes in the tree.
     */
    private class Node {
        K key;
        Node left, right, parent;
        Node(K datum, Node left, Node right, Node parent) {
            this.key = datum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    /**
     * The root of the tree
     */
    private Node root;

    /**
     * The comparator determining priority
     */
    private Comparator<K> compy;
    
    /**
     * Constructor for a populated priority queue,
     * given an iterable of keys and a comparable
     */
    public LinkedHeapPriorityQueue(Iterable<K> itably, Comparator<K> compy) {
        root = null;
        this.compy = compy;
        for (K item : itably)
            insert(item);
    }

    /**
     * Constructor for an empty prioirty queue, given 
     * only the comparable.
     */
    public LinkedHeapPriorityQueue(Comparator<K> compy) {
        root = null;
        this.compy = compy;
    }
    
    /**
     * "Brute force" constructor for debugging purposes.
     * @param keys The keys in breadth-first order of how they are
     * to appear in the heap.
     */
     public LinkedHeapPriorityQueue(K[] keys, Comparator<K> compy) {
         Node[] queue = (Node[]) Array.newInstance(this.getClass().getDeclaredClasses()[0], keys.length);
         root = queue[0] = new Node(keys[0], null, null, null);
         for (int i = 1; i < keys.length; i++) {
             int parent = (i - 1)/ 2;
             queue[i] = new Node(keys[i], null, null, queue[parent]);
             if (i % 2 == 1) queue[parent].left = queue[i];
             else queue[parent].right = queue[i];
         }
         this.compy = compy;
     }
    
    
    /**
     * Is this pq empty?
     * @return True if this is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Insert a new key into this pq.
     * @param key The key to insert.  
     */
    public void insert(K key) {
        if (root == null)
            root = new Node(key, null, null, null);
        else {
            Node newParent = findToInsert();
            Node newNode = new Node(key, null, null, newParent);
            if (newParent.left == null) newParent.left = newNode;
            else {
                assert newParent.right == null;
                newParent.right = newNode;
            }
            increaseKeyAt(newNode);
        }
    }

    /**
     * Return (but do not remove) the maximum key.
     * @return The maximum key.
     */
    public K max() {
        return root.key;
    }

    /**
     * Return and remove the maximum key.
     * @return The maximum key.
     */
    public K extractMax() {
        K toReturn = root.key;
        Node last = findLast();
        if (last == root)  root = null;
        else {
            if (last.parent.right == null) {
                assert last.parent.left == last;
                last.parent.left = null;
            }
            else {
                assert last.parent.right == last;
                last.parent.right = null;
            }
            root.key = last.key;
            decreaseKeyAt(root);
       }
        
        return toReturn;
    }

    /**
     * Determine whether this key is in the pq.
     * @param key The key to look for.
     * @return True if this key is in the pq, false otherwise.
     */
    public boolean contains(K key) {
        return findForKey(key) != null;
    }

    /**
     * Indicate that the priority of a key at a given key
     * has increased, which may affect the internal storage
     * of the pq.
     * @param key The key whose priority has changed.
     */
   public void increaseKey(K key) {
        Node x = findForKey(key);
        if (x != null)
            increaseKeyAt(x);
    }

   /**
    * Interchange the keys of two nodes
    * @param a
    * @param b
    */
    private void swap(Node a, Node b) {
        K temp = a.key;
        a.key = b.key;
        b.key = temp;
    }
    
    /**
     * Fix up the heap when a node is too low in the
     * tree for its priority.
     * PRECONDITION: The entire tree is a heap except for a possible
     * violation at the given node.
     * POSTCONDITION: The entire tree is a heap.
     * @param x The node which may be in violation.
     */
    private void increaseKeyAt(Node x) {
        while(x.parent != null && compy.compare(x.parent.key, x.key) < 0) {
            swap(x, x.parent);
            x = x.parent;
        }
    }

    /**
     * Fix up the heap when a node is too high in the 
     * tree for its priority.
     * PRECONDITION: The subtree rooted at the given node
     * tree is a heap except for a possible violation at the 
     * given node.
     * POSTCONDITION: The entire tree is a heap.
     * @param x The node which may be in violation.
     */
    private void decreaseKeyAt(Node x) {
        for (;;)
            if (x.right != null && compy.compare(x.right.key, x.key) > 0) {
                if (compy.compare(x.left.key, x.right.key) > 0) {
                    swap(x, x.left);
                    x = x.left;
                }
                else {
                    swap(x,  x.right);
                    x = x.right;
                }
            }
            else if (x.left != null && compy.compare(x.left.key, x.key) > 0) {
                swap(x, x.left);
                x = x.left;
            }
            else break;
            
    }
    
    /**
     * Indicate that the priority of a key at a given key
     * has decreased, which may affect the internal storage
     * of the pq.
     * @param key The key whose priority has changed.
     */
    public void decreaseKey(K key) {
        Node x = findForKey(key);
        if (x != null)
            decreaseKeyAt(x);
 
    }

    /**
     * Iterate through the nodes in breadth-first order
     */
    private Iterable<Node> bfIty() {
        final int cap = 10000;
        final Node[] queue = (Node[]) Array.newInstance(this.getClass().getDeclaredClasses()[0], cap);
        if (root != null) queue[0] = root;
        return new Iterable<Node>() {
            public Iterator<Node> iterator() {
                return new Iterator<Node>() {
                    int front = 0;
                    int n = queue[0] == null? 0 : 1;
                    public boolean hasNext() {
                        return n > 0;
                    }
                    public LinkedHeapPriorityQueue<K>.Node next() {
                        Node toReturn = queue[front++ % cap];
                        n--;
                        if (toReturn.left != null) {
                            if (n + 1 >= cap) throw new ArrayIndexOutOfBoundsException();
                            queue[(front + n++)%cap] = toReturn.left;
                            if (toReturn.right != null) {
                                if (n + 1 >= cap) throw new ArrayIndexOutOfBoundsException();
                                queue[(front + n++)%cap] = toReturn.right;
                            }
                        }
                        return toReturn;
                    }
                    
                };
            }
            
        };
    }

    /**
     * Find the node that will be the parent of the next
     * insertion, that is, the first node in breadth-first
     * order that has a null child.
     * @return
     */
    private Node findToInsert() {
        for (Node x : bfIty()) 
            if (x.right == null) return x;
        assert false;
        return null;
    }

    /**
     * Find the node that contains a given key.
     */
    private Node findForKey(K key) {
        for (Node x :bfIty())
            if (x.key.equals(key)) return x;
        return null;
    }

    /**
     * Find the last node in breadth-first order, that
     * is the right-most node in the lowest level.
     */
    private Node findLast() {
        Node toReturn = null;
        for (Node x : bfIty())
            toReturn = x;
        return toReturn;
    }

    /**
     * Display the contents of the heap in breadth-first order
     * (for debugging purposes).
     */
    public String toString() {
        String toReturn = "";
        for (Node x : bfIty())
            toReturn += x.key;
        return toReturn;
    }
}
