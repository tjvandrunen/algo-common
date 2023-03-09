package impl;

public class AVLBalancer<K extends Comparable<K>,V> implements Balancer<K,V,AVLInfo<K,V>> {

    public BSTMap<K, V, AVLInfo<K, V>>.Node putFixup(BSTMap<K, V, AVLInfo<K, V>>.Node fix) {
        fix.getInfo().recompute();

         // add code here
        
        return fix;
    }

    public BSTMap<K, V, AVLInfo<K, V>>.Node removeFixup(BSTMap<K, V, AVLInfo<K, V>>.Node fix) {
        return putFixup(fix);
    }

    public AVLInfo<K, V> newInfo(BSTMap<K, V, AVLInfo<K, V>>.Node node) {
        return new AVLInfo<K,V>(0, 0, node);
    }
    public AVLInfo<K, V> nullInfo(BSTMap<K, V, AVLInfo<K, V>>.Node node) {
        return newInfo(node);
    }

    public void rootFixup(BSTMap<K, V, AVLInfo<K, V>>.Node fix) { }

}
