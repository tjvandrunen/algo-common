package impl;

import java.util.Comparator;
import java.util.NoSuchElementException;

import adt.FullContainerException;
import adt.PriorityQueue;

/**
 * HeapPriorityQueue.java
 *
 * Class to implement a priority queue using a  heap.
 *
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */

public class HeapPriorityQueue<K> implements PriorityQueue<K> {

    /**
     * The heap (as a wrapper for an array) that serves as
     * the internal representation of this priority queue.
     */
    protected Heap<K> internal;
    
    /**
     * Dummy constructor so that this class can be extended with
     * a child class that differs only in its constructors.
     * (Constructors in the child class must call a constructor
     * in the parent class. However, the whole point of the child
     * classes in this case is to replace the constructors.)
     */
    protected HeapPriorityQueue() {}
    
    
    /**
     * Constructor for an empty priority queue.
     * @param maxSize The capacity of this priority queue.
     * @param compy The Comparator defining the priority of
     * these items.
     */
    public HeapPriorityQueue(int maxSize, Comparator<K> compy) {
        internal = new Heap<K>(maxSize, compy);
    }

    /**
     * Constructor. Initialize this priority queue to the keys in the
     * given iterable. The number of keys in the iterable
     * collection is taken as the capacity of the pq.
     * @param items An iterable collection of keys taken as the
     * initial contents of the pq.
     * @param compy The Comparator defining the priority of
     * these items.
     */
    public HeapPriorityQueue(Iterable<K> items, Comparator<K> compy) {
        internal = new Heap<K>(items, compy);
    }
   
    /**
     * Constructor. Initialize this priority queue to the keys in the
     * given array. The size of the given array  is taken as the capacity
     * of the priority queue.
     * @param items An iterable collection of keys taken as the
     * initial contents of the pq.
     * @param compy The Comparator defining the priority of
     * these items.
     */
    public HeapPriorityQueue(K[] items, Comparator<K> compy) {
        internal = new Heap<K>(items, compy);
    }

    /**
     * Constructor. Initialize this priority queue to the keys in the
     * given iterable. The given size is taken as the priority queue's
     * capacity. 
     * @param items An iterable collection of keys taken as the
     * initial contents of the pq.
     * @param size The number of items, passed in to avoid an
     * extra iteration over the items to count them.
     * @param compy The Comparator defining the priority of
     * these items.
     */
    public HeapPriorityQueue(Iterable<K> items, int size, Comparator<K> compy) {
        internal = new Heap<K>(items, size, compy);

    }
    
    /**
     * Is this priority queue empty?
     * @return True if this is empty, false otherwise.
     */
    public boolean isEmpty() {
        return internal.isEmpty();
    }

    /**
     * Is this priority queue full?
     * @return True if this is full, false otherwise.
     */
    public boolean isFull() {
        return internal.isFull();
    }

    /**
     * Insert a new item into this priority queue.
     * @param key The item to insert.
     */
    public void insert(K key) {
        if (isFull()) throw new FullContainerException();
         throw new UnsupportedOperationException();
    }

    /**
     * Return (but do not remove) the maximum element.
     * According to the (max-) heap property, the maximum element
     * should be at position 0.
     * @return The maximum element.
     */
    public K max() { return internal.get(0); }


    /**
     * Return and remove the maximum element.
     * @return The maximum element.
     */
    public K extractMax() {

         throw new UnsupportedOperationException();
    }

    /**
     * Determine whether this key is in the priority queue.
     * @param key The key to look for.
     * @return True if this key is in the priority queue, false otherwise.
     */
    public boolean contains(K key) {
        int pos = internal.findKey(key);
        return pos != -1 && pos < internal.heapSize();
    }

    /**
     * Indicate that the priority of a key at a given key
     * has changed, which may affect the internal storage
     * of the priority queue.
     * @param key The key whose priority has changed.
     */
    public void increaseKey(K key) {
        int i = internal.findKey(key);
        if (i == -1)
            throw new NoSuchElementException();
        else 
            internal.increaseKeyAt(i);

    }

    /**
     * Indicate that the priority of a key at a given key
     * has changed, which may affect the internal storage
     * of the priority queue
     * @param key The key whose priority has changed.
     */
    public void decreaseKey(K key) {
        int i = internal.findKey(key);
        if (i == -1)
            throw new NoSuchElementException();
        else 
            internal.decreaseKeyAt(i);

    }

    /**
     * Produce a string representation of this priority queue,
     * showing the structure of the heap.
     */
    @Override
    public String toString() { return internal.toString(); }

}
