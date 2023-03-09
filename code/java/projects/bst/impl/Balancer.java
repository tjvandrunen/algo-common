package impl;

public interface Balancer<K extends Comparable<K>, V, I extends NodeInfo> {
    
    public BSTMap<K,V,I>.Node putFixup(BSTMap<K,V,I>.Node fix);
    public BSTMap<K,V,I>.Node removeFixup(BSTMap<K,V,I>.Node fix);
    public void rootFixup(BSTMap<K,V,I>.Node fix);
    public I newInfo(BSTMap<K,V,I>.Node node);
    public I nullInfo(BSTMap<K,V,I>.Node node);
}
