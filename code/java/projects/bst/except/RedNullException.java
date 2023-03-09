package except;

/**
 * The RBNode interface gives the signature for a redden() operation.
 * However, null object nodes must be black. If the fixup code attempts
 * to redden a null object, this exception is thrown.
 */
public class RedNullException extends RuntimeException {

    private static final long serialVersionUID = 2816002625772829146L;

    public RedNullException() {
        super("Attempt to redden a null node");
    }
}