package impl;

import java.util.Comparator;
import java.util.NoSuchElementException;

import adt.FullContainerException;
import adt.List;
import adt.PriorityQueue;

/**
 * SortedListPriorityQueue
 * 
 * A slightly less dumb implementation of a priority queue
 * that uses a sorted list as the underlying
 * implementation.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 3, 2015
 * @param <E> The base-type of the priority queue
 */
public class SortedListPriorityQueue<E> implements PriorityQueue<E> {

    private List<E> internal;
    private Comparator<E> compy;

    // Invariant: The items in internal are sorted
    // from least to greatest according to compy
    
    public SortedListPriorityQueue(Comparator<E> compy) {
        internal = new ArrayList<E>();
        this.compy = compy;
    }

    public SortedListPriorityQueue(Iterable<E> items, Comparator<E> compy) {
        internal = new ArrayList<E>();
        this.compy = compy;
        for (E item : items) {
            internal.add(item);
            decreaseKeyAt(internal.size()-1);
        }
    }

    /**
     * Insure that the internal list is sorted from the beginning to pos.
     * That is, move the item at pos forward to its right place,
     * insertion-sort style.
     * PRECONDITION: The portion of the internal list [0, pos) is sorted
     * POSTCONDITION: The portion of the internal list [0, pos] is sorted
     * @param pos The index which might have a key 
     */
    private void decreaseKeyAt(int pos) {        
        for (int i = pos; 
                i > 0 && compy.compare(internal.get(i-1), internal.get(i)) > 0;
                i--) {
            E temp = internal.get(i);
            internal.set(i, internal.get(i-1));
            internal.set(i-1, temp);
        } 
              
    }
    
    /**
     * Insure that the internal list is sorted from pos to the end.
     * That is, move the item at pos back to its right place,
     * insertion-sort style.
     * PRECONDITION: The portion of the internal list (pos, n) is sorted
     * POSTCONDITION: The portion of the internal list [pos, n) is sorted
     * @param pos
     */
    private void increaseKeyAt(int pos) {        
        for (int i = pos; 
                i < internal.size() - 1 && compy.compare(internal.get(i), internal.get(i+1)) > 0;
                i++) {
            E temp = internal.get(i);
            internal.set(i, internal.get(i+1));
            internal.set(i+1, temp);
        } 
              
    }
    
    /**
     * Is this pq empty?
     * @return True if this is empty, false otherwise.
     */
    public boolean isEmpty() {
        return internal.size() == 0;
    }

    /**
     * Insert a new key into this pq.
     * @param key The key to insert.  
     */
    public void insert(E key) {
        internal.add(key);
        decreaseKeyAt(internal.size() - 1);
    }

    /**
     * Return (but do not remove) the maximum key.
     * According to the (max-) heap property, the maximum key
     * should be at position 0.
     * @return The maximum key.
     */
    public E max() {
        if (isEmpty())
            throw new NoSuchElementException();
        return internal.get(internal.size() - 1);
    }

    /**
     * Return and remove the maximum key.
     * @return The maximum key.
     */
    public E extractMax() {
        if (isEmpty())
            throw new NoSuchElementException();
        return internal.remove(internal.size() - 1);
    }

    
    private int findKey(E key) {
        int pos = 0;
        boolean foundIt = false;
        while (pos < internal.size() && ! foundIt)
            foundIt = key.equals(internal.get(pos++));
        if (foundIt) return pos-1;
        else return -1;
  
    }
    
    /**
     * Determine whether this key is in the pq.
     * @param key The key to look for.
     * @return True if this key is in the pq, false otherwise.
     */
    public boolean contains(E key) {
        return findKey(key) != -1;
    }

    /**
     * Indicate that the priority of a key at a given key
     * has increased, which may affect the internal storage
     * of the pq.
     * @param key The key whose priority has changed.
     */
    public void increaseKey(E key) {
        int pos = findKey(key);
        if (pos != -1) increaseKeyAt(pos);
    }

    /**
     * Indicate that the priority of a key at a given key
     * has decreased, which may affect the internal storage
     * of the pq.
     * @param key The key whose priority has changed.
     */
    public void decreaseKey(E key) {
        int pos = findKey(key);
        if (pos != -1) decreaseKeyAt(pos);
    }

}
