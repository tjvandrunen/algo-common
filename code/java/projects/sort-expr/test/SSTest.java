package test;

import alg.Sorts;

public class SSTest extends SortTest {

    protected void sort(int[] array) {
        (new Sorts.Selection()).sort(array);
    }

}
