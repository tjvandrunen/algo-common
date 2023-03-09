package sudokuit;

import org.junit.Test;

public class SIBITest extends SITest {
    @Test
    public void box0Test() {
        arrayVsIterable(new int[] {0, 1, 2, 9, 10, 11, 18, 19, 20}, SudokuIt.boxIt(0));
    }

    @Test
    public void box1Test() {
        arrayVsIterable(new int[] {3, 4, 5, 12, 13, 14, 21, 22, 23}, SudokuIt.boxIt(1));
    }

    @Test
    public void box7Test() {
        arrayVsIterable(new int[] {57, 58, 59, 66, 67, 68, 75, 76, 77}, SudokuIt.boxIt(7));
    }

}
