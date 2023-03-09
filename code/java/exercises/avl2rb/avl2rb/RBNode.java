package avl2rb;

public class RBNode {
    public final RBNode left, right;
    public final String key;
    private boolean isRed;
    public RBNode(RBNode left, String key, 
                  RBNode right) {
        this.left = left;
        this.key = key;
        this.right = right;
        this.isRed = false;
    }
    public boolean isRed() { return isRed; }
    public RBNode redden() { 
        isRed = true;
        return this;
    }
    public int blackHeight() {
        int lbh = left == null? 0 : left.blackHeight();
        int rbh = right == null? 0 : right.blackHeight();
        assert lbh == rbh;
        return isRed? lbh : lbh + 1;
    }
    public String toString() {
        return "(" + (left == null? "." : left) +
                (isRed? "[" + key + "]" : key) +
                (right == null? "." : right) + ")";
    }
    
}
