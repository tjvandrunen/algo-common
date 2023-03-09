package thereAndBackAgain;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * LinkedSet
 * 
 * An implementation of Set that uses a linked list
 * directly (as opposed to being built on another class).
 * 
 * Algorithmic Commonplaces
 * @param <E> The key type
 */
public class LinkedSet<E> implements Set<E> {

    private class Node {
        E datum;
        Node next;
        Node(E datum, Node next) {
            this.datum = datum;
            this.next = next;
        }
    }
     
    /**
     * The head node in the underlying list, null if the set is empty
     */
    private Node head;

    /**
     * The numer of keys (for efficiency)
     */
    private int size;
    
    /**
     * Add an key to the set. (Do nothing if the key is 
     * already there.)
     * @param key The key to add
     */
    public void add(E key) {
        if (! contains(key)) {
            head = new Node(key, head);
            size++;
        }
    }

    /**
     * Does this set contain the key?
     * @param key The key to check
     * @return True if the key is in the set, false otherwise
     */
    public boolean contains(E key) {
        boolean foundIt = false;
        for (Node current = head; !foundIt && current != null; current = current.next)
            foundIt = current.datum.equals(key);
        return foundIt;
    }

    /**
     * Remove an key from the set, if it's there;
     * ignore otherwise.
     * @param key The key to remove
     */
    public void remove(E key) {
        if (head == null) return;
        else if (head.datum.equals(key)) {
            head = head.next;
            size--;
        }
        else {
            Node previous = head, current = head.next;
            while (current != null &&  ! current.datum.equals(key) ) {
               previous = current;
                current = current.next;
            }
            if (current != null) {
                previous.next = current.next;
                size--;
            }
        }
    }

    /**
     * The number of keys in the set
     * @return The number of keys.
     */
    public int size() {
        return size;
    }

    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        assert (size == 0) == (head == null);
        return size == 0;
    }
    
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node current = head;
            public boolean hasNext() {
                return current != null;
            }
            public E next() {
                if (! hasNext()) throw new NoSuchElementException();
                E toReturn = current.datum;
                current = current.next;
                return toReturn;
            }
            
        };
    }


}
