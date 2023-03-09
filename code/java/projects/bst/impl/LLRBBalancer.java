package impl;

public class LLRBBalancer<K extends Comparable<K>, V> extends RBBalancer<K, V>  {

    public BSTMap<K, V, RBInfo<K, V>>.Node putFixup(BSTMap<K, V, RBInfo<K, V>>.Node fix) {
        BSTMap<K, V, RBInfo<K, V>>.Node replace = fix;
        RBInfo<K,V> info = fix.getInfo();

         // add code here

        return replace;
    }

    public BSTMap<K, V, RBInfo<K, V>>.Node removeFixup(BSTMap<K, V, RBInfo<K, V>>.Node fix) {

        BSTMap<K, V, RBInfo<K, V>>.Node 
            left = fix.getLeft(),
            right = fix.getRight();
        RBInfo<K, V> 
            fInfo = fix.getInfo(),
            lInfo = left.getInfo(),
            rInfo = right.getInfo();
        int lbh = lInfo.getBlackHeight(),
            rbh = rInfo.getBlackHeight();
        BSTMap<K, V, RBInfo<K, V>>.Node replace = fix;

         // add code here
        
        return replace;

    }

}
