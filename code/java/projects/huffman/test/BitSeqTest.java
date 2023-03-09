package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import impl.BitSequence;

public class BitSeqTest {

    private BitSequence bSeq;
    
    public void reset() {
        bSeq = new BitSequence();
    }
    
    public void checkSeq(boolean[] barray) {
        Iterator<Boolean> it = bSeq.iterator();
        for (boolean b : barray) {
            assertTrue(it.hasNext());
            assertEquals(b, it.next());
        }
        assertFalse(it.hasNext());
    }
    
    @Test
    public void testAddOneTrue() {
        reset();
        bSeq.add(true);
        checkSeq(new boolean[] { true });
    }
    
    @Test
    public void testAddOneFalse() {
        reset();
        bSeq.add(false);
        checkSeq(new boolean[] { false });
    }
    
   @Test
    public void testAddAllAtInstatiation() {
        boolean[] barray = { true, false, false, false, true, false, true, true,
            true, true, false, true, false, false, false, true, false, true,
            true, false, false, false, true, true, false, true, true, true,
            false, false, false, false, false, false, true, false, true, true,
            false, false, true, true, true, true, true, true, true, true, false,
            true, false, false, true, false, false, false, true, false, false,
            true, true, false, true, true, true, true, true, true, false, true};
        bSeq = new BitSequence(barray);
        checkSeq(barray);
    }

    @Test
    public void testAddAllAfterInstatiation() {
        boolean[] barray = { false, false, false, true, false, true, true,
                false, false, true,true, false, false, false, false, true, true,
                false, false, true, true, true, true, true, true, true, true, false,
                true, false, false, true, false, false, false, true, false, false,
                true, true, false, true, true, true, true, true, false, true, true};
        reset();
        bSeq.add(barray);
        checkSeq(barray);
    }

    @Test
    public void testAddOneAtATime() {
        boolean[] barray = { false, false, false, true, false, true, true,
                false, false, false, false, false, false, false, true, true,
                true, true, false, true, false, false, false, true, false, true, true,
                false, false, true,true, false, false, false, false, true, true,
                false, false, true, true, true, true, true, true, true, true, false,
                true, false,true, true, true, true, true, true, true, true, false,
                true, true, false, true, true, true, true, true, false, true, true};
        reset();
        for (boolean b : barray) bSeq.add(b);
        checkSeq(barray);
    }
    
    @Test
    public void testAddPiecemeal() {
        boolean[] barray = { false, false, true, false, true, true, true,
                false, true, false, true, false, false, false, true, false, true, true,
                false, false, true,true, false, false, false, false, true, true,
                false, false, false, false, false, false, false, true, true,
                true,false, false, false, false, false, true, false, true, false,
                true, false, false, true, false, false, false, true, false, false,
                true, true, false, true, true, true, true, true, false, true,true,
                true, false, false, false, false, false, true, false, true, true,
                false, false, true, true, true, true, true, true, true, true, false,
                true, false, false, true, true, true, false, true, true, true,
                false, false, true, true, true, true, true, true, true, true, false,
                true, false, false, false, false, false, false, true, true,
                true,false,false, false, false, false, true, false, true, true,
               true, true, false, true, true, true, true, true, false, true, true};
        int[] chunks = {0, 10, 50, 90, -1 };
        chunks[4] = barray.length;
        reset();
        for (int i = 0; i < chunks.length - 1; i++) { 
            final int k = i;
            bSeq.add(new Iterable<Boolean>() { 
                public Iterator<Boolean> iterator() { 
                    return new Iterator<Boolean>() {
                        int j = chunks[k];
                        public boolean hasNext() { return j < chunks[k+1]; }
                        public Boolean next() { return barray[j++]; }
                    };
                }
            });
        }
        checkSeq(barray);
    }
}
