package infix2RPN;

import static org.junit.Assert.*;

import org.junit.Test;

import static infix2RPN.InfixToRPN.infixToRPN;

public class TestInfixToRPN {

    @Test
    public void oneOp() {
        assertEquals(infixToRPN("(3+7)"), "3 7 +");
    }

    @Test
    public void leftSub() {
        assertEquals(infixToRPN("((2-3)*5)"), "2 3 - 5 *");
    }

    @Test
    public void rightSub() {
        assertEquals(infixToRPN("(8-(2+3))"), "8 2 3 + -");
    }

    @Test
    public void multiDigit() {
        assertEquals(infixToRPN("(22-4)"), "22 4 -");
    }

    @Test
    public void big1() {
        assertEquals(infixToRPN("(((2-3)*4)+(8/(3+7)))"), "2 3 - 4 * 8 3 7 + / +");
    }

    @Test
    public void big2() {
        assertEquals(infixToRPN("((2+((((21+3)-4)*7)/5))-301)"),
                "2 21 3 + 4 - 7 * 5 / + 301 -");
    }

    @Test
    public void big3() {
        assertEquals(infixToRPN("(7-(3+(44*(21-(3/(41*12))))))"),
                "7 3 44 21 3 41 12 * / - * + -");
    }
}
