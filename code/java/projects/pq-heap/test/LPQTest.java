package test;

import impl.ListPriorityQueue;

public class LPQTest extends PriorityQueueTest {

    protected void resetWidgetPopulated() {
        wpq = new ListPriorityQueue<Widget>(itably, wCompo);
    }

    protected void resetMeasEqEmpty(int size) {
         mpq = new ListPriorityQueue<MeasEq>(mCompo);
        
    }

}
