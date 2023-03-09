package impl;

public class AVLInfo<K extends Comparable<K>,V> implements NodeInfo {
    private int height;
    private int balance;
    private BSTMap<K,V,AVLInfo<K,V>>.Node node;
    public AVLInfo(int height, int balance, BSTMap<K,V,AVLInfo<K,V>>.Node node) {
        this.height = height;
        this.balance = balance;
        this.node = node;
    }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }
    public void recompute() {
        int leftHeight = node.getLeft().getInfo().getHeight();
        int rightHeight = node.getRight().getInfo().getHeight();
        balance = leftHeight - rightHeight;
        height = Math.max(leftHeight, rightHeight) + 1;
    }
    public String toString() { return "[" + height + "," + balance + "]"; }
}
