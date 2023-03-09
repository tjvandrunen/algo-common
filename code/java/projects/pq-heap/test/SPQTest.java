package test;

import impl.SetPriorityQueue;

public class SPQTest extends PriorityQueueTest {

    protected void resetMeasEqEmpty(int size) {
        mpq = new SetPriorityQueue<MeasEq>(size, mCompo);
    }

    protected void resetWidgetPopulated() {
        wpq = new SetPriorityQueue<Widget>(itably, wCompo);
    }

}
