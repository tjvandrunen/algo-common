package test;

import alg.Sorts;

public class BSTest extends SortTest {

    protected void sort(int[] array) {
        (new Sorts.Bubble()).sort(array);
    }
}
