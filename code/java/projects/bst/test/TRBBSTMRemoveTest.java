package test;

import except.DoubleRedException;
import except.IgnorantNodeException;
import except.InconsistentBlackHeightException;
import impl.BSTMap;
import impl.RBInfo;
import impl.TradRBBalancer;

public class TRBBSTMRemoveTest extends MapRemoveTest {


    private class TradRBBSTMap<K extends Comparable<K>,V> extends BSTMap<K, V, RBInfo<K,V>> {

        public TradRBBSTMap() { super(new TradRBBalancer<K,V>()); }
        
        private void verify(Node node) {
            if (! node.isNull()) {

                verify(node.getLeft());
                verify(node.getRight());

                int rightBlackHeight;
                int leftBlackHeight;
                int addedHeight;


                rightBlackHeight = node.getRight().getInfo().getBlackHeight(); 
                leftBlackHeight = node.getLeft().getInfo().getBlackHeight();
                if (node.getInfo().isRed()) {
                    addedHeight = 0;
                    if (node.getInfo().isRedR() || node.getInfo().isRedL() ) {
                        throw new DoubleRedException("Double Red"); 
                    }
                } else {
                    addedHeight = 1;
                }
                if (leftBlackHeight != rightBlackHeight)
                    throw new InconsistentBlackHeightException("Inconsistent Black Height: "+ node.toString(),leftBlackHeight,rightBlackHeight);
                int height = Math.max(leftBlackHeight,rightBlackHeight) + addedHeight;
                if (height != node.getInfo().getBlackHeight())
                    throw new IgnorantNodeException("The root of: "+node.toString()+ 
                            " thinks its black height is "+node.getInfo().getBlackHeight() +",but it is actually "+height);



            }
        }
        
        @Override
        public void put(K key, V val) {
            super.put(key, val);
            verify(root);
        }
        @Override
        public void remove(K key) {
            super.remove(key);
            verify(root);
        }
        
    }
 
    
    protected void reset() {
        testMap = new TradRBBSTMap<String,String>();
    }


    protected void resetInteger() {
        testMapInt = new TradRBBSTMap<Integer,Integer>();
    }
}
