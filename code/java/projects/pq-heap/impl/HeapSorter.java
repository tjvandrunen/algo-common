package impl;

import java.util.Comparator;
import java.util.Iterator;


/**
 * HeapSorter.java
 *
 * Class to implement the heapsort algorithm.
 *
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */

public class HeapSorter {

        
    /**
     * Sort this array, in place.
     * @param array The array to sort.
     */
    public static void sort(final int[] array) {
        
        Heap<Integer> heap = Heap.intHeap(array, 
                new Comparator<Integer>() {
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
        
        // insert code for completing the heap sort algorithm

        // postcondition: that heap.internal is sorted
        
        // copy elements from internal (now sorted) back to array
        for (int i = 0; i < array.length; i++)
            array[i] = heap.get(i);
        
        
    }

    
    /**
     * Sort this array, in place.
     * @param array The array to sort.
     */
    public static <E> void sort(final E[] array, Comparator<E> compy) {
        
        Heap<E> heap = Heap.array2Heap(array, array.length,
                new Comparator<E>() {
                    public int compare(E o1, E o2) {
                        return - compy.compare(o1, o2);
                    }
                });
        
        // insert code for completing the heap sort algorithm

        // postcondition: that heap.internal is sorted
        
    }

    
    
   
    
}
