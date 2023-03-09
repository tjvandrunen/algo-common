package except;

public class ImbalanceException extends RuntimeException {
    private static final long serialVersionUID = 6517111100429986230L;
    public ImbalanceException(String msg) { super(msg); }
}