package test;

import alg.Sorts;

public class MSTest extends SortTest {


    protected void sort(int[] array) {
        (new Sorts.Merge()).sort(array);
    }
}
