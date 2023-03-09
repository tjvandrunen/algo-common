package test;

import impl.SortedListPriorityQueue;

public class SLPQTest extends PriorityQueueTest {

    protected void resetWidgetPopulated() {
        wpq = new SortedListPriorityQueue<Widget>(itably, wCompo);
    }

    @Override
    protected void resetMeasEqEmpty(int size) {
         mpq = new SortedListPriorityQueue<MeasEq>(mCompo);
    }

}
