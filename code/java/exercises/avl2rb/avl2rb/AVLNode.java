package avl2rb;

import java.util.Stack;

public class AVLNode {

    public final AVLNode left, right;
    public final String key;
    public final int height;
    public final int balance;
    public AVLNode(AVLNode left, String key, AVLNode right) {
        this.left = left;
        this.key = key;
        this.right = right;
        int leftHeight = left == null? 0 : left.height;
        int rightHeight = right == null? 0 : right.height;
        height = 1 + (leftHeight > rightHeight? leftHeight : rightHeight);
        balance = leftHeight - rightHeight;
        assert -2 < balance && balance < 2;
    }
    
    public static AVLNode avlFactory(String description) {
        Stack<AVLNode> subtrees = new Stack<AVLNode>();
        Stack<String> keys = new Stack<String>();
        for (char c : description.toCharArray()) 
            switch(c) {
            case '.':subtrees.push(null);break;
            case '(':break;
            case ')': 
                AVLNode right = subtrees.pop(),
                        left = subtrees.pop();
                String key = keys.pop();
                subtrees.push(new AVLNode(left, key, right));
                break;
            default: keys.push(c + "");
            }
            
        return subtrees.pop();
    }
    
}
