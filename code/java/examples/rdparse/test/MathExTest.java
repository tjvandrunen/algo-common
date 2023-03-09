package test;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.List;
import adt.Map;
import impl.ListMap;
import mathex.Parser;
import static mathex.Syntax.*;

public class MathExTest {

    @Test
    public void testA() {
        Map<String, Integer> st = new ListMap<String,Integer>();
        List<Assignment> prog = Parser.parse("x=5");
        prog.get(0).execute(st);
        assertTrue(st.containsKey("x"));
        assertEquals(5, st.get("x").intValue());
    }

    @Test
    public void testB() {
        Map<String, Integer> st = new ListMap<String,Integer>();
        List<Assignment> prog = Parser.parse("x=(5-2)\ny=(8*(7+2))\nz=((2*y)/x)");
        for (Assignment stmt : prog)
            stmt.execute(st);
        assertTrue(st.containsKey("x"));
        assertEquals(3, st.get("x").intValue());
        assertTrue(st.containsKey("y"));
        assertEquals(72, st.get("y").intValue());
        assertTrue(st.containsKey("z"));
        assertEquals(48, st.get("z").intValue());
    }

}
