package test;

import static org.junit.Assert.*;
import impl.Heap;

import java.util.Comparator;
import java.util.Random;

import org.junit.Test;




public class HeapTest extends CollectionTest{ 
    private int comparisons;
    Random rand = new Random();
    protected int parent(int i) { return (i - 1) / 2; }
    protected int left(int i ) { return 2 * i + 1; }
    protected int right(int i) { return 2 * i + 2; }
    protected Comparator<Integer> comps = new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };
    protected Comparator<HeapObj> hcomps = new Comparator<HeapObj>() {
        public int compare(HeapObj o1, HeapObj o2) {
            comparisons++;
            return o1.kernel.compareTo(o2.kernel);
        }
    };
    
    
    private class HeapObj{
        
        private Integer kernel;
        public HeapObj(int kernel) { this.kernel = kernel;}
        @Override
        public int hashCode() {
            throw new UnsupportedOperationException();
        }
        /* The comparator should be used instead of equals*/
        @Override
        public boolean equals(Object obj) {
            throw new UnsupportedOperationException();
        }
        @Override
        public String toString() {
            if (DEBUG)
                return "H"+kernel;
            throw new UnsupportedOperationException();
        }
    }

    protected void decreaseKeyAtInteger(final Integer[] array, final int i, final int size) {
        Heap.array2Heap(array, size, comps).decreaseKeyAt(i);
    }
    
    protected void increaseKeyAtInteger(final Integer[] array, final int i, final int size) {
        Heap.array2Heap(array, size, comps).increaseKeyAt(i);
    }
    
    protected void decreaseKeyAtHeapObj(final HeapObj[] array, final int i, final int size) {
        Heap.array2Heap(array, size, hcomps).decreaseKeyAt(i);
    }
    

    
    protected void increaseKeyAtHeapObj(final HeapObj[] array, final int i, final int size) {
        Heap.array2Heap(array, size, hcomps).increaseKeyAt(i);
    }
    
    protected <T> void validateHeap(T[] heap,Comparator<T> comp) {
        validateHeap(heap, 0, heap.length,comp);
        
    }
    protected <T> void validateHeap(T[] heap,int heapStart,int heapEnd,Comparator<T> comp) {
        if (right(heapStart) < heapEnd) {
            assert(comp.compare(heap[heapStart], heap[right(heapStart)]) >= 0);
            validateHeap(heap,left(heapStart),heapEnd,comp);
        }
        if (left(heapStart) < heapEnd) {
            assert(comp.compare(heap[heapStart], heap[left(heapStart)]) >= 0);
            validateHeap(heap,left(heapStart),heapEnd,comp);
        }   
    }
    
    @Test
    public void decreaseKeyAtTrivial() {
        Integer[] array = { 5 };
        decreaseKeyAtInteger(array, 0, 1);
        assertEquals(array[0].intValue(), 5);
        validateHeap(array,comps);
    }

    
    
    @Test
    public void decreaseKeyAtSmallAlreadyHeap() {
        Integer[] array = {13, 12, 5};
        decreaseKeyAtInteger(array, 0, 3);
        assertEquals(array[0].intValue(), 13);
        assertEquals(array[1].intValue(), 12);
        assertEquals(array[2].intValue(), 5);
        validateHeap(array,comps);
    }
   
    
    @Test
    public void decreaseKeyAtSmallHeapBothViolate() {
        Integer[] array = {5, 12, 13};
        decreaseKeyAtInteger(array, 0, 3);
        assertEquals(array[0].intValue(), 13);
        assertEquals(array[1].intValue(), 12);
        assertEquals(array[2].intValue(), 5);
        validateHeap(array,comps);
    }

    @Test
    public void decreaseKeyAtSmallHeapLeftViolates() {
        Integer[] array = {5, 12, 3};
        decreaseKeyAtInteger(array, 0, 3);
        assertEquals(array[0].intValue(), 12);
        assertEquals(array[1].intValue(), 5);
        assertEquals(array[2].intValue(), 3);
        validateHeap(array,comps);
    }

    @Test
    public void decreaseKeyAtSmallHeapRightViolates() {
        Integer[] array = {5, 2, 13};
        decreaseKeyAtInteger(array, 0, 3);
        assertEquals(array[0].intValue(), 13);
        assertEquals(array[1].intValue(), 2);
        assertEquals(array[2].intValue(), 5);
        validateHeap(array,comps);
    }

    @Test
    public void decreaseKeyAtMedRightViolatesThenLeft() {
        Integer[] array = {4, 3, 6, 2, 1, 5};
        decreaseKeyAtInteger(array, 0, 6);
        assertEquals(array[0].intValue(), 6);
        assertEquals(array[1].intValue(), 3);
        assertEquals(array[2].intValue(), 5);
        assertEquals(array[3].intValue(), 2);
        assertEquals(array[4].intValue(), 1);
        assertEquals(array[5].intValue(), 4);
        validateHeap(array,comps);
        
    }
    
    @Test
    public void decreaseKeyAtLargeFullAlreadyHeap() {
        Integer[] array = { 8, 4, 7, 3, 1, 6, 2};
        decreaseKeyAtInteger(array, 0, 7);
        assertEquals(array[0].intValue(), 8);
        assertEquals(array[1].intValue(), 4);
        assertEquals(array[2].intValue(), 7);
        assertEquals(array[3].intValue(), 3);
        assertEquals(array[4].intValue(), 1);
        assertEquals(array[5].intValue(), 6);
        assertEquals(array[6].intValue(), 2);
        validateHeap(array,comps);
    }

    @Test
    public void decreaseKeyAtLargeNonFullAlreadyHeap() {
        Integer[] array = { 8, 4, 7, 3, 1, 6, 2, 2};
        decreaseKeyAtInteger(array, 0, 7);
        assertEquals(array[0].intValue(), 8);
        assertEquals(array[1].intValue(), 4);
        assertEquals(array[2].intValue(), 7);
        assertEquals(array[3].intValue(), 3);
        assertEquals(array[4].intValue(), 1);
        assertEquals(array[5].intValue(), 6);
        assertEquals(array[6].intValue(), 2);
        assertEquals(array[7].intValue(), 2);
        validateHeap(array,comps);
    }

    @Test
    public void decreaseKeyAtGivenExample() {
        Integer[] array = {10, 13, 17, 11, 7, 3, 15, 1, 9, 5 };
        decreaseKeyAtInteger(array, 0, 10);
        assertEquals(array[0].intValue(), 17);
        assertEquals(array[1].intValue(), 13);
        assertEquals(array[2].intValue(), 15);
        assertEquals(array[3].intValue(), 11);
        assertEquals(array[4].intValue(), 7);
        assertEquals(array[5].intValue(), 3);
        assertEquals(array[6].intValue(), 10);
        assertEquals(array[7].intValue(), 1);
        assertEquals(array[8].intValue(), 9);
        assertEquals(array[9].intValue(), 5);
        validateHeap(array,comps);
    }

    @Test
    public void decreaseKeyAtIgnoreNoiseBelow() {
        Integer[] array = {10, 13, 17, 11, 7, 3, 15, 1, 9, 5, 71, 82, 3, 99, 1 };
        decreaseKeyAtInteger(array, 0, 10);
        assertEquals(array[0].intValue(), 17);
        assertEquals(array[1].intValue(), 13);
        assertEquals(array[2].intValue(), 15);
        assertEquals(array[3].intValue(), 11);
        assertEquals(array[4].intValue(), 7);
        assertEquals(array[5].intValue(), 3);
        assertEquals(array[6].intValue(), 10);
        assertEquals(array[7].intValue(), 1);
        assertEquals(array[8].intValue(), 9);
        assertEquals(array[9].intValue(), 5);
        assertEquals(array[10].intValue(), 71);
        assertEquals(array[11].intValue(), 82);
        assertEquals(array[12].intValue(), 3);
        assertEquals(array[13].intValue(), 99);
        assertEquals(array[14].intValue(), 1);
        validateHeap(array,0,10,comps);
    }

    @Test
    public void decreaseKeyAtNoiseWithin() {
        Integer[] array = { 2, 10, 99, 17, 13, 27, 81, 3, 15, 11 };
        decreaseKeyAtInteger(array, 1, 10);
        assertEquals(array[0].intValue(), 2);
        assertEquals(array[1].intValue(), 17);
        assertEquals(array[2].intValue(), 99);
        assertEquals(array[3].intValue(), 15);
        assertEquals(array[4].intValue(), 13);
        assertEquals(array[5].intValue(), 27);
        assertEquals(array[6].intValue(), 81);
        assertEquals(array[7].intValue(), 3);
        assertEquals(array[8].intValue(), 10);
        assertEquals(array[9].intValue(), 11);
        validateHeap(array,1,10,comps);

    }

    @Test
    public void decreaseKeyAtWithDuplicates() {
        Integer[] array = { 13, 3, 7, 11, 11, 6, 4, 2 };
        decreaseKeyAtInteger(array, 1, 8);
        assertEquals(array[0].intValue(), 13);
        assertEquals(array[1].intValue(), 11);
        assertEquals(array[2].intValue(), 7);
        assertTrue((array[3].intValue() == 3 && array[4].intValue() ==11) ||
                (array[3].intValue() == 11 && array[4].intValue() ==3));
        assertEquals(array[5].intValue(), 6);
        assertEquals(array[6].intValue(), 4);
        assertEquals(array[7].intValue(), 2);
        validateHeap(array,comps);
    }
    @Test
    public void decreaseKeyAtWithDuplicatesHeapObj() {
        HeapObj[] array = { new HeapObj(13), new HeapObj(3),new HeapObj(7) , 
                new HeapObj(11), new HeapObj(11), new HeapObj(6), new HeapObj(4),new HeapObj(2)};
        decreaseKeyAtHeapObj(array, 1, 8);
        assertEquals(array[0].kernel.intValue(), 13);
        assertEquals(array[1].kernel.intValue(), 11);
        assertEquals(array[2].kernel.intValue(), 7);
        assertTrue((array[3].kernel.intValue() == 3 && array[4].kernel.intValue() ==11) ||
                (array[3].kernel.intValue() == 11 && array[4].kernel.intValue() ==3));
        assertEquals(array[5].kernel.intValue(), 6);
        assertEquals(array[6].kernel.intValue(), 4);
        assertEquals(array[7].kernel.intValue(), 2);
        validateHeap(array,hcomps);
    }
    
    @Test
    public void decreaseKeyAtStressTestRoot() {
        int tests=1000;
        int maxsize=10;
        for (int i = 0; i < tests; i++) {
            comparisons = 0;
            HeapObj[] array = new HeapObj[rand.nextInt(maxsize)+2];
            int size = array.length;
            int start = 0;
            int stop = size;
            array[0] = new HeapObj(rand.nextInt());
            array[1] = new HeapObj(rand.nextInt(Integer.MAX_VALUE));
            for (int j = 2; j < size; j++)
                array[j] = new HeapObj(rand.nextInt(array[j-1].kernel+1));
            decreaseKeyAtHeapObj(array,start,stop);
            /* Figure out the maximum number of comparisons allowed  for a heap of size size
             *  maxComparisons= C(size) where
             *  C(1) = 0
             *  C(2^m) = 2m-1 (0<m)
             *  C(2^m+l) = 2m (0<l<2^m)
            */
            int maxComparisons;
            if (size == 1)
                maxComparisons = 0;
            else if (Integer.bitCount(size) == 1)
                maxComparisons = 2*(Integer.BYTES*8 - Integer.numberOfLeadingZeros(size))-1;
            else 
                maxComparisons = 2*(Integer.BYTES*8 - Integer.numberOfLeadingZeros(size));
                
            //assert(comparisons <= maxComparisons);
            validateHeap( array,start,stop,hcomps);
            
        }
    }
    
    @Test
    public void trivialincreaseKeyAt() {
        Integer[] array = { 5 };
        increaseKeyAtInteger(array,0,array.length);
        assertEquals(5,array[0].intValue());
        validateHeap( array,0,array.length,comps);
    }
    
    @Test
    public void dontincreaseKeyAt() {
        Integer[] array = { 5, 4 };
        increaseKeyAtInteger(array,1,array.length);
        assertEquals(5,array[0].intValue());
        assertEquals(4,array[1].intValue());
        validateHeap( array,0,array.length,comps);
    }
    
    @Test
    public void doincreaseKeyAt() {
        Integer[] array = { 4, 5 };
        increaseKeyAtInteger(array,1,array.length);
        assertEquals(5,array[0].intValue());
        assertEquals(4,array[1].intValue());
        validateHeap( array,0,array.length,comps);
    }
    
    @Test
    public void doincreaseKeyAtIgnoreNoise() {
        Integer[] array = { 4, 5 ,5,9,42};
        increaseKeyAtInteger(array,1,2);
        assertEquals(5,array[0].intValue());
        assertEquals(4,array[1].intValue());
        validateHeap( array,0,2,comps);
    }
    
    @Test
    public void increaseKeyAtMultiLevel() {
        Integer[] array = { 10, 9 ,8,7,42};
        increaseKeyAtInteger(array,4,array.length);
        assertEquals(42,array[0].intValue());
        assertEquals(10,array[1].intValue());
        assertEquals(8,array[2].intValue());
        assertEquals(7,array[3].intValue());
        assertEquals(9,array[4].intValue());
        validateHeap( array,0,array.length,comps);
    }
    
    @Test
    public void increaseKeyAtDuplicates() {
        Integer[] array = { 9, 9 ,8,7,9};
        increaseKeyAtInteger(array,4,array.length);
        assertEquals(9,array[0].intValue());
        assertEquals(9,array[1].intValue());
        assertEquals(8,array[2].intValue());
        assertEquals(7,array[3].intValue());
        assertEquals(9,array[4].intValue());
        validateHeap( array,0,array.length,comps);
    }
    @Test
    public void increaseKeyAtDuplicatesComparison() {
        comparisons = 0;
        HeapObj[] array = { new HeapObj(9), new HeapObj(9) ,new HeapObj(8),new HeapObj(7),new HeapObj(9)};
        increaseKeyAtHeapObj(array,4,array.length);
        assertEquals(9,array[0].kernel.intValue());
        assertEquals(9,array[1].kernel.intValue());
        assertEquals(8,array[2].kernel.intValue());
        assertEquals(7,array[3].kernel.intValue());
        assertEquals(9,array[4].kernel.intValue());
        //assert(comparisons==1);
        validateHeap( array,0,array.length,hcomps);
        
    }
    
    /* I took this one out for SP 18. It seems it doesn't always
      increase the key. --TVD
    @Test
    public void increaseKeyAtStressComparisons() {
        
        int size = 10000;
        int passes = 100;
        HeapObj[] array = new HeapObj[size];
        for (int i = 0; i < size;i++)
            array[i] = new HeapObj(size - i);
        
        int biggest = size;
        int mid = size/2;
        //go through the array different order, increasing and sifting down as we go
        for (int i = size/passes-1; i < size; i += (size/passes) % (size+1)) {
            comparisons = 0;
            // sometimes make the changed entry the largest thing in the array, sometimes make it land in the middle
            int newVal = i%3==0 ? ++mid : ++biggest;
            array[i].kernel = new Integer(newVal);
            increaseKeyAtHeapObj(array,i,array.length);
            // comparisons should be less than or equal to floor(lg(i+1)) (the level of the node in the tree) 
            //assert(comparisons <= 31-Integer.numberOfLeadingZeros(i+1));
            validateHeap(array,0,array.length,hcomps);
        }
        
    }
    */    
    

}
