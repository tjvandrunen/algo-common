package test;

import org.junit.Test;

import alg.Sorts;

public class RadixSortTest extends SortTest {

    private int r = -1;
    
    protected void sort(Integer[] array) {
        Sorts.radixSort(array, r == -1? 10 : r);
    }

    @Test
    public void baseTenThrough100() {
        r = 10;
        setUpAndSort(new int[]{87, 50, 21, 47, 62, 79, 23, 51, 18, 15, 46, 76, 23, 52, 44, 93, 9, 62, 54, 50, 33, 93, 7, 10, 17, 32, 35, 21, 5, 6});
        r = -1;
    }

    @Test
    public void baseTenThrough1000() {
        r = 10;
        setUpAndSort(new int[]{495, 202, 516, 429, 401, 536, 421, 315, 321, 442, 105, 67, 149, 625, 621, 368, 400, 686, 222, 60, 198, 180, 944, 8, 399, 575, 890, 133, 885, 677});
        r = -1;
    }
    
    @Test
    public void baseTwoThrough32() {
        r = 2;
        setUpAndSort(new int[]{28, 10, 1, 0, 29, 24, 10, 26, 20, 16, 21, 22, 9, 3, 11, 8, 27, 19, 23, 0, 3, 18, 11, 24, 19, 23, 1, 1, 15, 6});
        r = -1;
    }

    @Test
    public void baseTwoThrough256() {
        r = 2;
        setUpAndSort(new int[] {137, 113, 215, 197, 52, 142, 198, 188, 9, 95, 198, 30, 128, 13, 49, 161, 163, 39, 239, 83, 234, 109, 226, 116, 79, 102, 15, 28, 197, 176});
        r = -1;
    }
    
    @Test
    public void baseSevenThrough2401() {
        r = 7;
        setUpAndSort(new int[] {360, 149, 2037, 1082, 1264, 1398, 2389, 1253, 158, 1661, 2301, 1707, 2307, 2208, 1222, 168, 28, 2196, 220, 888, 332, 2365, 1792, 777, 497, 150, 4, 1056, 2212, 473});
        r = -1;
    }
    @Test
    public void noCounting() {
        r = 7;
        setUpAndSort(new int[] {5,3,Integer.MAX_VALUE,0});
        r = -1;
    }
}
