package impl;

public abstract class RBBalancer<K extends Comparable<K>, V> implements Balancer<K, V, RBInfo<K,V>>{

    public RBInfo<K, V> newInfo(BSTMap<K, V, RBInfo<K, V>>.Node node) {
        return new RBInfo<K,V>(true, 0, node);
    }

    public RBInfo<K, V> nullInfo(BSTMap<K, V, RBInfo<K, V>>.Node node) {
        return new RBInfo<K,V>(false, 0, node);
    }

    public void rootFixup(BSTMap<K, V, RBInfo<K, V>>.Node fix) {
        fix.getInfo().blacken();
        fix.getInfo().recompute();
    }

}