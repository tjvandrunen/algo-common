package test;

import org.junit.Test;

import alg.Sorts;

public class CountingSortTest extends SortTest {

    private Sorts.ToInteger<Integer> self = new Sorts.ToInteger<Integer>() {
        public int v(Integer a) {
            return a;
        }
    };
    
    protected void sort(Integer[] array) { 
        Sorts.countingSort(array, self);
    }

    @Test
    public void medSizeSmallRange() {
        setUpAndSort(new int[] {3, 3, 3, 0, 0, 4, 3, 1, 0, 1, 4, 2, 2, 3, 3, 4, 3, 0, 1, 4});
    }

    @Test
    public void medSizeBigRange() {
        setUpAndSort(new int[] {13, 4, 12, 10, 15, 6, 11, 2, 7, 10, 3, 5, 17, 18, 0, 1, 1, 10, 6, 17});
    }

    @Test
    public void bigSizesmallRange() {
        setUpAndSort(new int[] {3, 2, 0, 4, 2, 2, 1, 3, 2, 4, 2, 2, 4, 1, 0, 3, 2, 2, 1, 0, 4, 2, 0, 3, 0, 2, 1, 3, 0, 3, 4, 2, 3, 4, 2, 3, 4, 0, 2, 1, 0, 2, 0, 2, 0, 2, 1, 2, 1, 1, 1, 4, 1, 4, 4, 4, 1, 1, 1, 4, 1, 2, 0, 1, 1, 1, 1, 3, 3, 3, 2, 0, 1, 1, 0, 3, 3, 4, 3, 1, 0, 3, 0, 0, 4, 1, 3, 1, 0, 3, 0, 2, 1, 4, 4, 0, 1, 4, 0, 3});
    }
   
    
}
