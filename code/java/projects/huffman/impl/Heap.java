package impl;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Heap.java
 *
 * An implementation of a heap that wraps an array,
 * supporting basic heap operations such as could be used
 * by a heap priority queue or heap sort.
 *
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */

public class Heap<E> {

    /**
     * The array containing the internal data of the heap.
     */
    private E[] internal;

    /**
     * The portion of the array currently used to store the heap.
     */
    private int heapSize;

    /**
     * Comparator to determine the priority of keys.
     */
    private Comparator<E> compy;

    // Invariant:
    // heapSize <= internal.length 
    // && for all i in [0, heapSize), 
    //      if left(i) < heapSize, then internal[i] <= internal[left(i)]
    //      and if right(i) < heapSize, then internal[i] <= internal[right(i)],
    //      according to the comparator compy.
    
    // -----  Constructors -----

    /**
     * Dummy constructor for use by testcases.
     */
    private Heap() {}
    
    /**
     * Make an empty heap
     * @param maxSize The maximum size (capacity) of this heap
     * @param compy The comparable encapsulating the priority relation
     */
    public Heap(int maxSize, Comparator<E> compy) {
        initializeInternal(maxSize, compy);
        heapSize = 0;
    }
   
    /**
     * Make a heap from an array of items. This makes a new array
     * and copies the given items to the new array. It does not 
     * store a reference to the given array or modify it. To
     * make a heap that wraps a given array, see {@link #array2Heap() 
     * the makeTestHeap() factory method}.
     * @param items The items initially to be in the heap
     * @param compy The comparable encapsulating the priority relation
     */
    protected Heap(E[] items, Comparator<E> compy) {
        initializeInternal(items.length, compy);
        heapSize = items.length;
        for (int i = 0; i < items.length; i++)
            set(i, items[i]);
        buildHeap();
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
    public Heap(Iterable<E> items, int size, Comparator<E> compy) {
        initializeInternal(size, compy);
        int i = 0;
        for (E item : items) 
           internal[i++] = item;
        assert i == size;
        buildHeap();
    }

    /**
     * Make a heap from an iterable of items of an uknown size.
     * This is less efficient than building a heap from an iterable
     * of known size because it requires an extra, initial iteration
     * to count the items.
     * @param items The items initially to be in the heap
     * @param compy The comparable encapsulating the priority relation
     */
    public Heap(Iterable<E> items, Comparator<E> compy) {
        int size = 0;
        for (Iterator<E> it = items.iterator(); it.hasNext(); it.next()) size++;
        initializeInternal(size, compy);
        int i = 0;
        for (E item : items) 
           internal[i++] = item;
        assert i == size;
        buildHeap();
    }

    /**
     * Factory method for an Integer heap built from an array of int
     * items. This cannot be handled by a constructor because we
     * need to convert from primitive type to object type.
     * @param items The items initially to be in the heap
     * @param compy The comparable encapsulating the priority relation
     * @return A heap with the given items
     */
    public static Heap<Integer> intHeap(int[] items, Comparator<Integer> compy) {
        Heap<Integer> toReturn = new Heap<Integer>(items.length, compy);
        for (int i = 0; i < items.length; i++)
            toReturn.add(items[i]);
        toReturn.buildHeap();
        return toReturn;
    }
    
    /**
     * Factory method to make an encapsulation-broken heap for
     * use by the test cases, using the given array as its internal
     * representation
     * @param items The items initially in the heap as an array
     * @param compy The comparable encapsulating the priority relation
     * @return A heap with the given items, using the given array as
     * its internal representation.
     */
    public static <E> Heap<E> array2Heap(E[] items, int size, Comparator<E> compy) {
        Heap<E> toReturn = new Heap<E>();
        toReturn.internal = items;
        toReturn.heapSize = size;
        toReturn.compy = compy;
        return toReturn;
    }
    
    // --- Helper methods for the constructors ---
    
    /**
     * Make a new, internal array and set the comparable.
     * This would naturally be done by a constructor that the
     * other constructors would call. We make it a separate method
     * for two reasons: Because its call wouldn't always be the first
     * statement in an overloaded constructor calling it(which Java 
     * requires for overloaded constructors calling each other)
     * and so that child class OptimizedHeap can override it.
     * @param maxSize The size of the internal array and maximum size of the heap
     * @param compy The comparator encapsulating the priority relation
     */
    @SuppressWarnings("unchecked")
    protected void initializeInternal(int maxSize, Comparator<E> compy) {
        this.internal = (E[]) new Object[maxSize];
        this.compy = compy;
    }
    
    /**
     * Arrange the internal array (arbitrarily ordered) into a heap.
     * POSTCONDITION: The class invariant holds.
     */
    private void buildHeap() {
        heapSize = internal.length;
        // Invariant: 
        // All subtrees rooted at positions [i+1, heapsize)
        // are valid heaps
        for (int i = heapSize - 1; i >= 0; i--)
            decreaseKeyAt(i);
    }

    // --- Helper methods for navigating the heap ---

    /**
     * Find the index of the parent of the node at a given index.
     * @param i The index whose parent we want.
     * @return The index of the parent.
     */
    private int parent(int i) { return (i - 1) / 2; }

    /**
     * Find the index of the left child of the node at a given index.
     * @param i The index whose left child we want.
     * @return The index of the left child.
     */
    private int left(int i ) { return 2 * i + 1; }

    /**
     * Find the index of the right child of the node at a given index.
     * @param i The index whose right child we want.
     * @return The index of the right child.
     */
    private int right(int i) { return 2 * i + 2; }

    
    // --- Public methods for the client code ---
    
    /**
     * Interchange the values at two locations. This method should be
     * used instead of direct changes to the internal array.
     * This method is to be overridden by OptimizedPriorityQueue.
     */
    public void swap(int i, int j) {
        assert 0 <= i && i < heapSize && 0 <= j && j < heapSize;
        E temp = internal[i];
        set(i, internal[j]);
        set(j, temp);
    }
    
    /** 
     * Enforce the heap property on the subtree rooted at the given index.
     * To be used when initially building a heap and when fixing up
     * a heap after an item has been removed.
     * @param i The index where we want to make a heap.
     * PRECONDITION: The subtrees rooted at the left and right
     * children of i are already heaps.
     * POSTCONDITION: The subtree rooted at i is a heap.
     */
    public void decreaseKeyAt(int i) {
         throw new UnsupportedOperationException();
     }
    
    /** 
     * Correct a heap in which one value is larger than its ancestors.
     * To be used with insert() and increaseKey().
     * @param i The index of the value that may be larger than its ancestors.
     * PRECONDITION: The heap property holds for all values in internal before heapSize,
     * except that i may be larger than its ancestors.
     * POSTCONDITION: internal bounded by heapSize is heap. (That is to say: the heap 
     * property holds for all values in internal before heapSize).
     * 
     */
    public void increaseKeyAt(int i) {
        
         throw new UnsupportedOperationException();
    }
    
    /**
     * Set the internal array at a given position to a
     * given item. The position must be within the heap range.
     * To extend the range and increment heapSize, use
     * {@link #add() add}.
     * @param i The position to set, which must be less than the heap size.
     * @param item The item to put at the given position
     */
    public void set(int i, E item) {
        assert i >= 0 && i < heapSize;
        internal[i] = item;
    }
    
    /**
     * Get the element at the given position in the internal array.
     * @param i The position in the internal array to retrieve.
     * @return The item at the given position in the internal array.
     */
    public E get(int i) {
        // NO ASSERTION about heapSize, since heapSort needs
        // to read the positions that are outside the heap.
        return internal[i];
    }

    
    /**
     * Add an item to the back of the heap in the internal array, 
     * incrementing the heap size.
     * @param item The item to add
     */
    public void add(E item) {
        assert heapSize < internal.length;
        heapSize++;
        set(heapSize-1, item);
    }

    /**
     * Decrement the heap size. The item at the end of the
     * heap range is not removed from the array, but it is no
     * longer considered part of the heap.
     */
    public void decrementHeapSize() {
        assert heapSize > 0;
        heapSize--;
    }
    
    /**
     * Retrieve the size of this heap, which is the number of items
     * in the array and considered part of the heap
     * @return The size of the heap
     */
    public int heapSize() { return heapSize; }
    
    /**
     * Is this heap empty?
     * It is if its heap size is zero.
     * @return True if this is empty, false otherwise.
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Is this heap full?
     * It is if its heap size is equal to the array's size.
     * @return True if this is full, false otherwise.
     */
    public boolean isFull() {
        return heapSize == internal.length;
    }

    
    /**
     * Find the location of a given key. 
     * This is the bottleneck in priority queue performance, since it reverts
     * to linear search. OptimizedHeapPriorityQueue should override this.
     * @param key The key whose location to find.
     * @return The location of that key or -1 if nowhere
     */
    public int findKey(E key) {
        int i = -1;
        for (int j = 0; i == -1 && j < heapSize; j++) 
            if (internal[j].equals(key)) i = j;
        return i;
    }

    
    /**
     * Display the state of the heap as an array. The entire 
     * array is displayed; a vertical bar (pipe) indicates the 
     * end of the heap.
     * @return A string displaying the state of the heap.     * 
     */
    @Override
    public String toString() {
        String toReturn = "[";
        for (int i = 0; i < internal.length; i++) {
            if (i == heapSize)
                toReturn += "| ";
            toReturn += internal[i] + " ";
        }
        if (heapSize == internal.length) 
            toReturn += "|";
        toReturn += "]";
        return toReturn;
            
    }

    
    
}
