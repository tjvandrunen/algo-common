package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.DisjointSet;

public class ArrayForestDisjointSet implements DisjointSet {

    /**
     * The parent of each element in the forest of trees
     * representing sets in the universe. The following hold
     * for this array:
     * (a) The elements  of the universe are indices into id.
     * (b) parents[i] is another element in the universe,
     * that is, for all i in [0, parents.length), parents[i] is 
     * in [0, parents.length);
     * (c) (i, parents[i]) is a link in a tree representation of the 
     * current partition of the universe; 
     * (d) in the transitive closure of the relation make up of all 
     * pairs (i, parents[i]), i is related to the leader of its set.
     */
    protected int[] parents;

    /**
     * The number of sets in the current partition.
     */
    protected int count;

    private static interface FindStrategy { int find(int p); }
    private class PlainFind implements FindStrategy {
        public int find(int p) {
            checkIndex(p);
            while (p != parents[p]) p = parents[p];
            return p;
        }
    }
    private class CompressingFind implements FindStrategy {
        public int find(int p) {
            checkIndex(p);
            if (p == parents[p]) return p;
            else return parents[p] = find(parents[p]);
        }
    }
    
    
    private static interface UnionStrategy { void union(int p, int q); }
    private class LazyUnion implements UnionStrategy {
        public void union(int p, int q) {
            checkIndex(p);
            checkIndex(q);
            int pLeader = find(p);
            int qLeader = find(q);
            if (pLeader != qLeader) {
                parents[pLeader] = qLeader;
                count--;
            }
        }
    }
    private class AggressiveUnion implements UnionStrategy {
        public void union(int p, int q) {
            checkIndex(p);
            checkIndex(q);
            int qLeader = parents[q],
                pLeader = parents[p];
            if (qLeader != pLeader) {
                for (int i = 0; i < parents.length; i++)
                    if (parents[i] == qLeader)
                        parents[i] = pLeader;
                count--;
            }
        }
    }
    private class WeightedUnion implements UnionStrategy {
        private int[] sizes;
        public WeightedUnion(int size) {
            sizes = new int[size];
            for (int i = 0; i < sizes.length; i++)
                sizes[i] = 1;
        }
        public void union(int p, int q) {
            checkIndex(p);
            checkIndex(q);
            int pLeader = find(p);
            int qLeader = find(q);
            if (pLeader != qLeader) {
                if (sizes[pLeader] < sizes[qLeader]) {
                    parents[pLeader] = qLeader;
                    sizes[qLeader] += sizes[pLeader];
                }
                else {
                    parents[qLeader] = pLeader;
                    sizes[pLeader] += sizes[qLeader];
                }
                count--;
            }
        }
    }
    
    private FindStrategy finder;
    private UnionStrategy unioner;
    
    /**
     * Make a universe with the given size. Initially
     * each item is in its own set with itself as 
     * leader.
     * @param size The number of elements in the universe.
     * @param find A code indicating a strategy for the find 
     * operation: 0-plain, 1-compressing
     * @param union A code indicating a strategy for the union
     * operation: 0-lazy, 1-aggressive, 2-ranking
     */
    public ArrayForestDisjointSet(int size, int find, int union) {
        parents = new int[size];
        for (int i = 0; i < parents.length; i++)
            parents[i] = i;
        count = size;
        if (find == 0) finder = new PlainFind();
        else if (find == 1) finder = new CompressingFind();
        else throw new IllegalArgumentException("Bad code for find strategy: " + find);
        if (union == 0) unioner = new LazyUnion();
        else if (union == 1) unioner = new AggressiveUnion();
        else if (union == 2) unioner = new WeightedUnion(size);
        else throw new IllegalArgumentException("Bad code for union strategy: " + union);
        
    }

    /**
     * Verify that the index is valid.
     */
    protected void checkIndex(int p) {
        if (p < 0 || p >= parents.length)
            throw new NoSuchElementException();
    }

    /**
     * Test to see if these two elements are in the same
     * set.
     * @return True if p and q are in the same set, false
     * otherwise.
     * @throws NoSuchElementException If either element is
     * beyond the bounds of this universe
     */
    public boolean connected(int p, int q) {
        checkIndex(p);
        checkIndex(q);
    
        //works independently of rest of implementation
        return find(p) == find(q);  
    
    }

    /**
     * Determine the number of sets in the partition.
     * @return The number of equivalent classes.
     */
    public int count() {
        return count;
    }

    
    
    public int find(int p) {
        return finder.find(p);
    }

    public void union(int p, int q) {
        unioner.union(p, q);
    }

    
    /**
     * Iterate through the elements in a set identified by
     * an element in that set.
     * @param p An element in the universe.
     * @return An Iterable that will return an Iterator that 
     * will iterate over all elements that the given one
     * is connected to, including the given one itself.
     */
    public Iterable<Integer> findAll(int p) {
        checkIndex(p);
        final int leader = find(p);
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                 return null;  // add code here
            }
        };
    }

    /**
     * Iterate through all the leaders (and hence the sets)
     * of this universe.
     */
    public Iterator<Integer> iterator() {
         throw new UnsupportedOperationException();
    }

    public int totalDepth() {
        int totalDepth = 0;
        for (int i = 0; i < parents.length; i++) {
            int p = i;
            while (parents[p] != p) {
                totalDepth++;
                p = parents[p];
            }
        }
        return totalDepth;
    }

}
