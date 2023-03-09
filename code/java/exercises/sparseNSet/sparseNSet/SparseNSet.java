package sparseNSet;

import java.util.Iterator;

public class SparseNSet implements NSet {    
    
    private int[] sparse;
    private int[] dense;
    private int size;
    
    // Class invariant:
    // a. sparse.length == dense.length == N
    // b. 0 <= size < N 
    // c. size is the number of keys in the set
    // d. k is a member of the set iff
    //     (0 <= sparse[k] < size  and dense[sparse[k]] == k)
    
    public SparseNSet(int length) {
        sparse = new int[length];
        dense = new int[length];        
    }

    public int range() {
        return sparse.length;
    }

    public void add(Integer key) {
        if (! contains(key))  {
            // add code here
        }
    }

    public boolean contains(Integer key) {
        throw new UnsupportedOperationException()
    }

    public void remove(Integer key) {
        if (contains(key)) {
            throw new UnsupportedOperationException()
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<Integer> iterator() {
       throw new UnsupportedOperationException()
    }
    
    public NSet complement() {
       NSet toReturn = new SparseNSet(sparse.length);
       throw new UnsupportedOperationException()
    }

    public NSet union(NSet other) {
        assert other instanceof SparseNSet;
        SparseNSet o = (SparseNSet) other;
        assert o.sparse.length == sparse.length;
        NSet toReturn = new SparseNSet(sparse.length);
        throw new UnsupportedOperationException()
    }

    public NSet intersection(NSet other) {
        assert other instanceof SparseNSet;
        SparseNSet o = (SparseNSet) other;
        assert o.sparse.length == sparse.length;
        NSet toReturn = new SparseNSet(sparse.length);
        throw new UnsupportedOperationException()

    }

    public NSet difference(NSet other) {
        assert other instanceof SparseNSet;
        SparseNSet o = (SparseNSet) other;
        assert o.sparse.length == sparse.length;
        NSet toReturn = new SparseNSet(sparse.length);
        throw new UnsupportedOperationException()
        
    }


}
