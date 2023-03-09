package test;

import impl.ArrayForestDisjointSet;

/**
 * "Lazy union" array forest disjoint set
 */
public class LUDSTest extends DisjointSetTest {

    protected void reset(int size) {
        testDisjSet = new ArrayForestDisjointSet(size, 0 , 0);
    }

}
