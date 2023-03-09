package except;

public class InconsistentBlackHeightException extends RuntimeException {

    private static final long serialVersionUID = 5624546038065294822L;
    private String keyRep;
    private int leftHeight, rightHeight;

    public InconsistentBlackHeightException(String keyRep, int leftHeight, int rightHeight) {
        this.keyRep = keyRep;
        this.leftHeight = leftHeight;
        this.rightHeight = rightHeight;
    }

    @Override
    public String getMessage() {
        return keyRep + " has left height " + leftHeight + 
                " and right height " + rightHeight + ".";
    }
}