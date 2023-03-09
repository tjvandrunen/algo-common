package stquit;

import java.util.Iterator;

public class QuItTest extends StQuItTest {

 
    protected Iterator<Integer> getIterator(final int[] data) {
        return StQuIt.queueToIterator(new Queue<Integer>() {
            int i = 0;
            public void enqueue(Integer item) { throw new UnsupportedOperationException(); }
            public Integer front() { return data[i]; }
            public Integer remove() { return data[i++]; }
            public boolean isEmpty() { return i >= data.length; }
        });
    }

}
