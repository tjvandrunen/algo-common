package test;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class SortTest {

    public static boolean isSorted(Integer[] array) {
        boolean inOrderSoFar = true;
        for (int i = 1; inOrderSoFar && i < array.length; i++)
            inOrderSoFar &= array[i] >= array[i-1];
        return inOrderSoFar;
    }

    public static boolean allThere(Integer[] orig, Integer[] sorted) {
        assert orig.length == sorted.length;
        boolean[] taken = new boolean[orig.length];
        boolean allFound = true;
        for (int i = 0; allFound && i < orig.length; i++) {
            boolean found = false;
            for (int j = 0; !found && j < sorted.length; j++)
                 found = taken[j] = !taken[j] && orig[i].equals(sorted[j]);
            allFound &= found;
        }
        return allFound;
    }

    
    private static String displayArray(Integer[] array) {
        String toReturn = "[";
        for (Integer x : array)
            toReturn += x + ", ";
        toReturn = toReturn.substring(0, toReturn.length() - 1);
        toReturn += "]";
        return toReturn;
    }
    
    protected abstract void sort(Integer[] array);
    
    protected void setUpAndSort(int[] array) {
        Integer[] original = new Integer[array.length];
        Integer[] subject = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
            original[i] = subject[i] = array[i];
        sort(subject);
        assertTrue("Not sorted: \n" + displayArray(subject), isSorted(subject));
        assertTrue("Not all there:\n" + displayArray(original) + "\n" + 
                displayArray(subject), allThere(original, subject));
    }
    
    @Test
    public void empty() {
        setUpAndSort(new int[0]);
    }
    
    @Test
    public void sizeOne() {
        setUpAndSort(new int[] {5});
    }

    @Test
    public void smallSorted() {
        setUpAndSort(new int[]{1, 2, 3, 4, 5, 6, 7} );
    }
    
}
