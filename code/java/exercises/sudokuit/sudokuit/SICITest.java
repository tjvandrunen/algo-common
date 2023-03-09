package sudokuit;

import org.junit.Test;

public class SICITest extends SITest {
    @Test
    public void col0Test() {
        arrayVsIterable(new int[] {0, 9, 18, 27, 36, 45, 54, 63, 72}, SudokuIt.columnIt(0));
    }

    @Test
    public void col1Test() {
        arrayVsIterable(new int[] {1, 10, 19, 28, 37, 46, 55, 64, 73}, SudokuIt.columnIt(1));
    }

    @Test
    public void col7Test() {
        arrayVsIterable(new int[] {7, 16, 25, 34, 43, 52, 61, 70, 79}, SudokuIt.columnIt(7));
    }

}
