package test;

import impl.ArrayForestDisjointSet;

/**
 * "Ranking-union" array forest disjoint set
 */
public class WUDSTest extends DisjointSetTest {

    protected void reset(int size) {
        testDisjSet = new ArrayForestDisjointSet(size, 0, 2);
    }

}
