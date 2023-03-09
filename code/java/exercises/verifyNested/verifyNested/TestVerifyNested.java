package verifyNested;

import static org.junit.Assert.*;

import org.junit.Test;

import static verifyNested.VerifyNested.verifyNested;

public class TestVerifyNested {

    @Test
    public void empty() {
        assertTrue(verifyNested(""));
    }

    @Test
    public void singleOpen() {
        assertFalse(verifyNested("("));
    }

    @Test
    public void singlePaired() {
        assertTrue(verifyNested("[]"));
    }

    @Test
    public void twoLevelPaired() {
        assertTrue(verifyNested("([])"));
    }

    @Test
    public void twoLevelSequence() {
        assertTrue(verifyNested("[<>()]"));
    }

    @Test
    public void threeLevelPaired() {
        assertTrue(verifyNested("[({})]"));
    }

    @Test
    public void topLevelSequence() {
        assertTrue(verifyNested("[({})]<{}>"));
    }

    @Test
    public void extraneous() {
        assertTrue(verifyNested("[5()  {k<1>} t]"));
    }

    @Test
    public void missingOneEnd() {
        assertFalse(verifyNested("({}[<>]"));
    }

    @Test
    public void missingSeveralEnd() {
        assertFalse(verifyNested("[[{}("));
    }

    @Test
    public void allSameKind() {
        assertTrue(verifyNested("[[[][[][]]][[[[]]]]][]"));
    }

    @Test
    public void mismatchedSimple() {
        assertFalse(verifyNested("(>"));
    }

    @Test
    public void slightlyMismatchedComplicated() {
        assertFalse(verifyNested("{[<>(<>))()}"));
    }

    @Test
    public void veryMismatchedComplicated() {
        assertFalse(verifyNested("([}<>][{(})>"));
    }
}
