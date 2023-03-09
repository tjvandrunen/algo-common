package findmissing;

import static org.junit.jupiter.api.Assertions.*;
import static findmissing.FindMissing.findMissing;
import org.junit.jupiter.api.Test;

class TestFindMissing {

    @Test
    void testFirstMissing() {
        assertEquals(0, findMissing(new int[] {1,2,3,4,5}));
    }

    @Test
    void testLastMissing() {
        assertEquals(5, findMissing(new int[] {0,1,2,3,4}));
    }
    
    // begin solution, replace with:
    @Test
    void testMiddleMissing() {
        assertEquals(5, findMissing(new int[] {0,1,2,3,4,6,7,8,9}));
    }

    @Test
    void testLopsideMissing() {
        assertEquals(4, findMissing(new int[] {0,1,2,3,5,6,7,8,9,10,11,12}));
    }
    // end solution
}
