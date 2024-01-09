package exercises;

public class SelectionSort {
    /**
     * Uses selection sort to sort a generic array of objects. 
     * 
     * NOTE: the tests for this exercise assume you the selection sort. If you use any other sorting algorithm
     * or any variation on selection sort that does not make the same number of comparisons as selection sort,
     * the tests will fail.
     * 
     * array - An array of objects that implement Comparable, to sort
     * 
     * PRECONDITION: 
     *  -array contains comparable objects. Array is non-null.
     * 
     * POSTCONDITION: 
     *  - array is sort in decreasing order. More formally, for all n <= m, array[n].compareTo(array[m]) >= 0.
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] array) {
         throw new UnsupportedOperationException();
    }
    
    /**
     * Helper method to find the index of the minimum element in a range in a given array.
     * @param array An array of objects that implement Comparable, to sort
     * @param start The inclusive beginning of the range to search
     * @return The index of the smallest element in the given array 
     * in the range [start, array.length)
     * PRECONDITION:
     *  - start is in the range [0, array.length)
     */
    public static <T extends Comparable<? super T>> int selectSmallest(T[] array, int start) {
        assert start < array.length;
        throw new UnsupportedOperationException()
    }
}
