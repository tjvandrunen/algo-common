package test;

import alg.Sorts;

public class ShSTest extends SortTest {
    protected void sort(int[] array) {
        (new Sorts.Shell()).sort(array);
    }
}
