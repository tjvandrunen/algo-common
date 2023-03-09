package impl;

import except.RedNullException;

public class RBInfo<K extends Comparable<K>,V> implements NodeInfo {

    private boolean isRed;
    private int blackHeight;
    private BSTMap<K,V,RBInfo<K,V>>.Node node;
    public RBInfo(boolean isRed, int blackHeight, BSTMap<K,V,RBInfo<K,V>>.Node node) {
        this.blackHeight = blackHeight;
        this.isRed = isRed;
        this.node = node;
    }
    
    public boolean isRed() { return isRed; }
    public boolean isRedL() { return node.getLeft().getInfo().isRed; }
    public boolean isRedR() { return node.getRight().getInfo().isRed; }
    public boolean isRedLR() { return node.getLeft().getRight().getInfo().isRed; }
    public boolean isRedRL() { return node.getRight().getLeft().getInfo().isRed; }
    public boolean isRedLL() { return node.getLeft().getLeft().getInfo().isRed; }
    public boolean isRedRR() { return node.getRight().getRight().getInfo().isRed; }

    public void redden() { 
        if (node.isNull()) throw new RedNullException(); 
        else isRed = true; 
    }
    public void blacken() { isRed = false; }
    
    public void recompute() {
        if (! node.isNull()) {
            int leftBlackHeight = node.getLeft().getInfo().blackHeight;
            blackHeight = leftBlackHeight + (isRed? 0 : 1);
        }
    }

    public int getBlackHeight() { return blackHeight; }

    @Override
    public String toString() { 
        return "[" + blackHeight + "," +
                (isRed?"red":"black") + "]";
    }
}
