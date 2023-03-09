package test;

import impl.AVLInfo;
import except.IgnorantNodeException;
import except.ImbalanceException;
import impl.AVLBalancer;
import impl.BSTMap;
import impl.Balancer;

public class AVLBSTMTest extends MapRemoveTest {

    private class AVLBSTMap<K extends Comparable<K>, V> extends BSTMap<K, V, AVLInfo<K,V>> {
        public AVLBSTMap() { super(new AVLBalancer<K,V>()); }
        private void verify(Node node) {
            if (! node.isNull()) {
                verify(node.getLeft());
                verify(node.getRight());
                int leftHeight = node.getLeft().getInfo().getHeight();
                int rightHeight = node.getRight().getInfo().getHeight();
                if (Math.abs(leftHeight-rightHeight) > 1)
                    throw new ImbalanceException("Left Height ="+ leftHeight +", Right Height =" + rightHeight);
                int height = Math.max(leftHeight, rightHeight) + 1;
                if (height != node.getInfo().getHeight())
                    throw new IgnorantNodeException("The root of: "+ node.toString() + "thinks its height is "+ 
                            node.getInfo().getHeight() + ", but it is actually "+height);

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
        testMap = new AVLBSTMap<String,String>();
    }

    protected void resetInteger() {
        testMapInt = new AVLBSTMap<Integer,Integer>();
    }

}
