package impl;

import java.util.Comparator;

/**
 * OptimizedHeapPriorityQueue.java
 *
 * Class to implement a priority queue using a (max) heap
 * optimized for elements that know where they are in
 * the underlying array.
 *
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces   
 */
public class OptimizedHeapPriorityQueue<E extends HeapPositionAware> extends HeapPriorityQueue<E> {

    
    /**
     * Constructor. Initialize this pq to empty.
     * @param maxSize The capacity of this priority queue.
     * @param compy The Comparator defining the priority of
     * these items.
     */
    public OptimizedHeapPriorityQueue(int maxSize, Comparator<E> compy) {
        super();
        internal = new OptimizedHeap<E>(maxSize, compy);
    }

    /**
     * Constructor. Initialize this pq to the keys in the
     * given iterable. The number of keys in the iterable
     * collection is taken as the capacity of the pq.
     * @param items An iterable collection of keys taken as the
     * initial contents of the pq.
     * @param compy The Comparator defining the priority of
     * these items.
     */
    public OptimizedHeapPriorityQueue(Iterable<E> items, Comparator<E> compy) {
        super();
        internal = new OptimizedHeap<E>(items, compy);
    }
  

    /**
     * Constructor. Initialize this pq to the keys in the
     * given iterable. The number of keys in the iterable
     * collection is taken as the capacity of the pq.
     * @param items An iterable collection of keys taken as the
     * initial contents of the pq.
     * @param compy The Comparator defining the priority of
     * these items.
     */
    public OptimizedHeapPriorityQueue(E[] items, Comparator<E> compy) {
        super();
        internal = new OptimizedHeap<E>(items, compy);
    }

    /**
     * Constructor. Initialize this pq to the keys in the
     * given iterable. The number of keys in the iterable
     * collection is taken as the capacity of the pq.
     * @param items An iterable collection of keys taken as the
     * initial contents of the pq.
     * @param size The number of items, passed in to avoid an
     * extra iteration over the items to count them.
     * @param compy The Comparator defining the priority of
     * these items.
     */
    public OptimizedHeapPriorityQueue(Iterable<E> items, int size, Comparator<E> compy) {
        super();
        internal = new OptimizedHeap<E>(items, size, compy);

    }

}
