package treesLLRB23;

/**
 * TwoThreeNode
 *
 * Class to represent a node in a two-three tree (standing also
 * for the tree rooted at this node).
 *
 */
public class TwoThreeNode {
    /**
     * The left and right children of this node. They are valid
     * whether this is a two-node or a three-node. Either they are
     * both null (if this is a leaf) or neither of them are null.
     */
    public final TwoThreeNode left, right;
    /**
     * The only key (if this is a two-node) or the "left" key (if this
     * is a three-node).
     */
    public final String key1;
    // next two are valid only if a three-node; 
    // null if a two-node
    /**
     * The "right" key (VALID ONLY FOR THREE-NODES).
     */
    public final String key2;
    /**
     * The center child (VALID ONLY FOR THREE-NODES)
     */
    public final TwoThreeNode center;
    /**
     * Constructor for two-nodes
     */
    public TwoThreeNode(TwoThreeNode left, String key, 
            TwoThreeNode right) {
        this.left = left;
        this.key1 = key;
        this.right = right;
        this.key2 = null;
        this.center = null;
    }
    /**
     * Constructor for three-nodes
     */
    public TwoThreeNode(TwoThreeNode left, String key1, 
            TwoThreeNode center, String key2, 
            TwoThreeNode right) {
        this.left = left;
        this.key1 = key1;
        this.center = center;
        this.key2 = key2;
        this.right = right;
    }
    
    /**
     * Compare whether the trees rooted at this and another TwoThreeNode 
     * are identical in structure and data. (Used for testing.)
     */
    @Override
    public boolean equals(Object o) {
        if (! (o instanceof TwoThreeNode)) return false;
        else {
            TwoThreeNode other = (TwoThreeNode) o;
            return
                    ((left == null && other.left == null) || 
                            (left != null && other.left != null && left.equals(other.left))) &&
                    ((right == null && other.right == null) ||
                            (right != null && other.right != null && right.equals(other.right))) &&
                    ((center == null && other.center == null) ||
                            (center != null && other.center != null && center.equals(other.center))) &&
                    ((key1 == null && other.key1 == null) ||
                            (key1 != null && other.key1 != null && key1.equals(other.key1))) &&
                    ((key2 == null && other.key2 == null) ||
                            (key2 != null && other.key2 != null && key2.equals(other.key2))); 

        }
    }
    @Override 
    public int hashCode() {
    	return key1.hashCode()+(key2 == null ? 0 :key2.hashCode());
    }
}

