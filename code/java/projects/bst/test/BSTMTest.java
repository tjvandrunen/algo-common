package test;

import impl.BSTMap;
import impl.Balancer;
import impl.NodeInfo;

public class BSTMTest extends MapStressTest {

    private class DummyNodeInfo implements NodeInfo { public void recompute() { } }
    private DummyNodeInfo dummy = new DummyNodeInfo();
    
    protected void resetInteger() {
        testMapInt = new BSTMap<Integer,Integer,DummyNodeInfo>(new Balancer<Integer,Integer,DummyNodeInfo>() {
            public BSTMap<Integer,Integer,DummyNodeInfo>.Node putFixup(BSTMap<Integer,Integer,DummyNodeInfo>.Node fix) { return fix; }
            public BSTMap<Integer,Integer,DummyNodeInfo>.Node removeFixup(BSTMap<Integer,Integer,DummyNodeInfo>.Node fix) { return fix; }
            public DummyNodeInfo newInfo(BSTMap<Integer,Integer,DummyNodeInfo>.Node node) {
                return dummy;
            }
            public DummyNodeInfo nullInfo(BSTMap<Integer, Integer, DummyNodeInfo>.Node node) {
                return dummy;
            }
            public void rootFixup(BSTMap<Integer, Integer, DummyNodeInfo>.Node fix) {}
       });
    }

    protected void reset() {
        testMap = new BSTMap<String,String,DummyNodeInfo>(new Balancer<String,String,DummyNodeInfo>() {
            public BSTMap<String,String,DummyNodeInfo>.Node putFixup(BSTMap<String,String,DummyNodeInfo>.Node fix) { return fix; }
            public BSTMap<String,String,DummyNodeInfo>.Node removeFixup(BSTMap<String,String,DummyNodeInfo>.Node fix) { return fix; }
            public DummyNodeInfo newInfo(BSTMap<String,String,DummyNodeInfo>.Node node) {
                return dummy;
            }
            public DummyNodeInfo nullInfo(BSTMap<String, String, DummyNodeInfo>.Node node) {
                // TODO Auto-generated method stub
                return null;
            }
            public void rootFixup(BSTMap<String, String, DummyNodeInfo>.Node fix) {}
       });
        
    }

}
