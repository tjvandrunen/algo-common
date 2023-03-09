package treesLLRB23;
/**
 * RBNode
 *
 * Class to represent a node in a red-black tree (standing also
 * for the tree rooted at this node).
 */

public class RBNode {
    /**
     * The children of this node (either may be null)
     */
    public final RBNode left, right;
    /**
     * The key stored here
     */
    public final String key;    
    /**
     * Is this a red node?
     */
    public final boolean isRed;
    /**
     * Plain old constructor
     */
    public RBNode(RBNode left, String key, 
                  RBNode right, boolean isRed) {
        this.left = left;
        this.key = key;
        this.right = right;
        this.isRed = isRed;
    }
   
    /**
     * Compare whether the trees rooted at this and another RBNode are identical
     * in structure, data, and color. (Used for testing.)
     */
    @Override
    public boolean equals(Object o) {
    	if (! (o instanceof RBNode)) return false;
    	else {
    		RBNode other = (RBNode) o;
    		return
    		        ((left == null && other.left == null) || 
                            (left != null && other.left != null && left.equals(other.left))) &&
                    ((right == null && other.right == null) ||
                            (right != null && other.right != null && right.equals(other.right))) &&
                    ((key == null && other.key == null) ||
                            (key != null && other.key != null && key.equals(other.key))) &&
                    (isRed == other.isRed);

    	}
    }
    @Override
    public int hashCode(){
    	return key.hashCode();
    }
    
}

