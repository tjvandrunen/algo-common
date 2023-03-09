package except;

public class IgnorantNodeException extends RuntimeException {
    private static final long serialVersionUID = -8092328069970833574L;
    public IgnorantNodeException(String msg) { super(msg); }
}