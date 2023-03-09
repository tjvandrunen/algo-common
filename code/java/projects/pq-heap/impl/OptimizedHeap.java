package impl;

import java.util.Comparator;
import java.util.Iterator;

public class OptimizedHeap<E extends HeapPositionAware> extends Heap<E> {

    // -----  Constructors -----

    /**
     * Make an empty heap
     * @param maxSize The maximum size (capacity) of this heap
     * @param compy The comparable encapsulating the priority relation
     */
    public OptimizedHeap(int maxSize, Comparator<E> compy) {
        super(maxSize, compy);
    }
   
    /**
     * Make a heap from an array of items
     * @param items The items initially to be in the heap
     * @param compy The comparable encapsulating the priority relation
     */
    protected OptimizedHeap(E[] items, Comparator<E> compy) {
        super(items, compy);
    }

    /**
     * Make a heap from an iterable of items of a known size.
     * This is more efficient than building a heap from an iterable
     * of unknown size because it avoids an extra, initial iteration
     * to count the items.
     * @param items The items initially to be in the heap
     * @param size The number of items
     * @param compy The comparable encapsulating the priority relation
     */
    public OptimizedHeap(Iterable<E> items, int size, Comparator<E> compy) {
        super(items, size, compy);
    }

    /**
     * Make a heap from an iterable of items of an uknown size.
     * This is less efficient than building a heap from an iterable
     * of known size because it requires an extra, initial iteration
     * to count the items.
     * @param items The items initially to be in the heap
     * @param compy The comparable encapsulating the priority relation
     */
    public OptimizedHeap(Iterable<E> items, Comparator<E> compy) {
        super(items, compy);
    }
    
    // --- Override methods from Heap ---
    
    
 
    
    /**
     * Set the value at a position in the underlying array.
     * This also informs the value itself where it is in the
     * array.
     * @param i
     * @param item
     */
    @Override
    public void set(int i, E item) {
        super.set(i, item);
        item.setPosition(i);
    }
  
    /**
     * Find the location of a given key. 
     * Since the keys know their position, we can ask them.
     * @param key The key whose location to find.
     * @return The location of that key or -1 if nowhere
     */
    @Override
    public int findKey(E key) {
        return key.getPosition();
    }

}
