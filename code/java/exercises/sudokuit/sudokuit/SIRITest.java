package sudokuit;

import static org.junit.Assert.*;

import org.junit.Test;

public class SIRITest extends SITest {

    @Test
    public void row0Test() {
        arrayVsIterable(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8}, SudokuIt.rowIt(0));
    }

    @Test
    public void row1Test() {
        arrayVsIterable(new int[] {9, 10, 11, 12, 13, 14, 15, 16, 17}, SudokuIt.rowIt(1));
    }

    @Test
    public void row7Test() {
        arrayVsIterable(new int[] {63, 64, 65, 66, 67, 68, 69, 70, 71}, SudokuIt.rowIt(7));
    }
}
