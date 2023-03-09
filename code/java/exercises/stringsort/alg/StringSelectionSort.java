package alg;

import adt.StringSorter;

/**
 * StringSelectionSort
 * 
 * Plain old selection sort, using String's compareTo method.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */
public class StringSelectionSort implements StringSorter {

    public static final StringSorter sorter = new StringSelectionSort();
    
    private StringSelectionSort() {}
    
    public void sort(String[] array) {
        for (int i = 0; i < array.length; i++)  {
            String min = array[i];
            int minPos = i;
            for (int j = i + 1; j < array.length; j++) 
                if (array[j].compareTo(min) < 0) {
                    min = array[j];
                    minPos = j;
                }
            array[minPos] = array[i];
            array[i] = min;
          }
    }
}
