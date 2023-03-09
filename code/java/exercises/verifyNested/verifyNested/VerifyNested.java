package verifyNested;

/**
 * VerifyNested.java
 *
 * Program to determine whether a string contains
 * proper nesting of grouping symbols. For example, 
 * the line "( a ( {b & 6 } [ 1 t ^] ~ ) )" is 
 * properly nested; "( a ( {b & 6 ) [ 1 t ^] ~ ) }" is 
 * not.
 *
 * @author ThomasVanDrunen
 */

public class VerifyNested {

    public static boolean verifyNested(String s) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        if (verifyNested(args[0]))
            System.out.println("Yes");
        else
            System.out.println("No");

    }
}
