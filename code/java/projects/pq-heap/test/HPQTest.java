package test;

import impl.HeapPriorityQueue;

public class HPQTest extends PriorityQueueTest {

    protected void resetMeasEqEmpty(int size) {
        mpq = new HeapPriorityQueue<MeasEq>(size, mCompo);
    }

    protected void resetWidgetPopulated() {
        wpq = new HeapPriorityQueue<Widget>(itably, wCompo); 
    }

}
