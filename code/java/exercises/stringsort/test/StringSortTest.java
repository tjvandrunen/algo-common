package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import adt.StringSorter;
import alg.StringSortUtil;

public abstract class StringSortTest {

    protected StringSorter sorter;
    
    private void basicTest(int n, int d) {
        String[] orig = StringSortUtil.arrayGen(n, d);
        String[] working = StringSortUtil.copy(orig);
        sorter.sort(working);
        assertTrue(StringSortUtil.isSorted(working));
        assertTrue(StringSortUtil.containsSame(orig, working));
    }

    @Test
    public void testn0d0() {
        basicTest(0, 0);
    }

    @Test
    public void testn0d5() {
        basicTest(0, 5);
    }

    @Test
    public void testn1d1() {
        basicTest(1, 1);
    }

    @Test
    public void testn1d2() {
        basicTest(1, 2);
    }

    @Test
    public void testn1d3() {
        basicTest(1, 3);
    }

    @Test
    public void testn1d5() {
        basicTest(1, 5);
    }

    @Test
    public void testn3d1() {
        basicTest(3, 1);
    }

    @Test
    public void testn3d2() {
        basicTest(3, 2);
    }

    @Test
    public void testn3d3() {
        basicTest(3, 3);
    }

    @Test
    public void testn3d5() {
        basicTest(3, 5);
    }

    @Test
    public void testn10d3() {
        basicTest(10, 3);
    }

    @Test
    public void testn11d3() {
        basicTest(11, 3);
    }

    @Test
    public void testn20d3() {
        basicTest(20, 3);
    }

    @Test
    public void testn25d3() {
        basicTest(25, 3);
    }

    @Test
    public void testn100d3() {
        basicTest(100, 3);
    }

    @Test
    public void testn101d3() {
        basicTest(101, 3);
    }

    @Test
    public void testn102d3() {
        basicTest(102, 3);
    }

    @Test
    public void testn103d3() {
        basicTest(103, 3);
    }

    @Test
    public void testn1000d3() {
        basicTest(1000, 3);
    }

    @Test
    public void testn20d5() {
        basicTest(20, 5);
    }

    @Test
    public void testn21d5() {
        basicTest(21, 5);
    }

    @Test
    public void testn1000d5() {
        basicTest(1000, 5);
    }

}
