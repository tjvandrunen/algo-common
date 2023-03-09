package test;

import impl.ArrayForestDisjointSet;

/**
 * "Compressing find" array forest disjoint set
 */
public class CFDSTest extends DisjointSetTest {

    protected void reset(int size) {
        testDisjSet = new ArrayForestDisjointSet(size, 1, 0);
    }

}
