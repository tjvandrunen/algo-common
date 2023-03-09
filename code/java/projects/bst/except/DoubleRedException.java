package except;

/**
 * Exception to indicate two adjacent red nodes, that is,
 * a parent and a child are both red.
 */
public class DoubleRedException extends RuntimeException {
    private static final long serialVersionUID = -9146048206806015395L;
    public DoubleRedException(String msg) {
        super(msg);
    }
}