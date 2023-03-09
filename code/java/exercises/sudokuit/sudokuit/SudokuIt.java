package sudokuit;

import java.util.Iterator;

public class SudokuIt {

    public static Iterable<Integer> rowIt(int row) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                 throw new UnsupportedOperationException;
            }
        };
    }
    
    public static Iterable<Integer> columnIt(int col) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                 throw new UnsupportedOperationException;
            }
        };
    }
    
    public static Iterable<Integer> boxIt(int box) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                 throw new UnsupportedOperationException;
        };
    }
}
