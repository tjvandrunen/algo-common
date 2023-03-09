package alg;

import adt.StringSorter;

/**
 * StringRadixSort
 * 
 * Implementation of radix sort by character
 * (least significant character first), assuming
 * strings all of the same length.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */
public class StringRadixSort implements StringSorter {

    /**
     * Singleton instance of this class, since sort() is
     * essentially a static method we want to call polymorphically.
     */
    public static final StringSorter sorter = new StringRadixSort();

    /**
     * This class has no state, so this is a do-nothing constructor---but
     * I want it private.
     */
    private StringRadixSort() {}

    /**
     * Sort the given array.
     * @param array The array to sort
     * POSTCONDITION: The given array is sorted.
     */
    public void sort(String[] array) {
        if (array == null || array.length == 0) return;

        try {
            int d = array[0].length();
            String[] source = array,
                    destination = new String[array.length];
            int[] counts = new int[26];
            // for each string position
            for (int i = d-1; i >= 0; i--) {
                // tabulate the counts for each letter
                
                //Add code here (A)
                
                // determine the starting positions in the original
                // array for each letter
                int prev = counts[0];
                counts[0] = 0;

                //Add code here (B)

                assert prev + counts[counts.length - 1] == array.length;

 
                    
                // transfer strings from source to destination according to counts

                //Add code here (C)

                // zero out counts (to prepare for next round)
                for (int j = 0; j < counts.length; j++)
                    counts[j] = 0;

                // switch source and destination
                String[] temp = destination;
                destination = source;
                source = temp;
            }

            // if there were an odd number of letters, then we need 
            // one more switch
            if (source != array)
                for (int i = 0; i < array.length; i++)
                    array[i] = source[i];
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Not all strings of same length or some contain chars other than capital letters?");
            ioobe.printStackTrace();
            throw ioobe;
        }

    }

}
