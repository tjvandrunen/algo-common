package test;

import alg.Sorts;

public class QSTest extends SortTest {

    protected void sort(int[] array) {
        (new Sorts.Quick()).sort(array);
    }
}
