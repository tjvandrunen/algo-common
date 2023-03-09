package test;

import alg.Sorts;

public class ISTest extends SortTest {

    protected void sort(int[] array) {
        (new Sorts.Insertion()).sort(array);
    }
}
