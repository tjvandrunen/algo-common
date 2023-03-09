package stquit;

import java.util.Iterator;

public class StItTest extends StQuItTest {

    
    protected Iterator<Integer> getIterator(final int[] data) {
        return StQuIt.stackToIterator(new Stack<Integer>() {
            int i = 0;
            public void push(Integer item) { throw new UnsupportedOperationException(); }
            public Integer top() { return data[i]; }
            public Integer pop() { return data[i++]; }
            public boolean isEmpty() { return i >= data.length; }
        });
    }

}
