package alg;

import adt.StringSorter;

/**
 * StringMergeSort
 * 
 * Plain old merge sort, using String's compareTo method.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */
public class StringMergeSort implements StringSorter {

    private String[] aux;

    public static final StringSorter sorter = new StringMergeSort();
    
    private StringMergeSort() {}
    
    public synchronized void sort(String[] array) {
        aux = new String[array.length];
        sortR(array, 0, array.length);
    }

    private void sortR(String[] array, int start, int stop) {

        if (start + 1 < stop) {
            int n = stop - start;
            int midPoint = (start + stop) / 2;
            sortR(array, start, midPoint);
            sortR(array, midPoint, stop);
            int i = start, j = midPoint;
            for (int k = 0; k < n; k++) {
                if (i >= midPoint) {
                    aux[k] = array[j];
                    j++;
                }
                else if (j >= stop) {
                    aux[k] = array[i];
                    i++;
                }
                else if (array[i].compareTo(array[j]) < 0) {

                    aux[k] = array[i];
                    i++;
                }
                else {
                    aux[k] = array[j];
                    j++;
                }
            }
            for (int k = 0; k < n; k++)
                array[start + k] = aux[k];
        }
    }
}
