package test;

import impl.ArrayForestDisjointSet;

/**
 * "Aggressive Union" array forest disjoint set
 */
public class AUDSTest extends DisjointSetTest {

    protected void reset(int size) {
        testDisjSet = new ArrayForestDisjointSet(size, 0, 1);
    }

}
