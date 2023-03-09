package test;

import impl.ArrayForestDisjointSet;

/**
 * "Compressing find + ranking union" array forest disjoint set
 */
public class CFRUDJTest extends DisjointSetTest {

    protected void reset(int size) {
        testDisjSet = new ArrayForestDisjointSet(size, 1, 2);
    }

}
