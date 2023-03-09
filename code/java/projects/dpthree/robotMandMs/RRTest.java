package robotMandMs;

import static org.junit.Assert.*;

import org.junit.Test;

public class RRTest {

    @Test
    public void testA() {
        boolean[][] board = new boolean[][]{{true, true}, {false, true}};
        assertEquals(3, RobotRoute.mostMandMs(board));
    }
    
    @Test
    public void testB() {
        boolean[][] board = new boolean[][] {{false, true, false, true},
                {true, false, true, false},
                {true, false, false, false},
                {false, false, true, true},
                {true, false, true, false},
                {false, false, true, false},
                {true, false, false, false},
                {true, true, true, false}};
        assertEquals(7, RobotRoute.mostMandMs(board));
    }

    @Test
    public void testC() {
        boolean[][] board = new boolean[][] 
                {{true, false, false, false, false},
                {false, true, false, false, false},
                {false, false, true, false, false},
                {false, false, false, true, false},
                {false, false, false, false, true}};
        assertEquals(5, RobotRoute.mostMandMs(board));
    }

    @Test
    public void testD() {
        boolean[][] board = new boolean[][] 
                {{true, true, true, true, false, false, false, false},
                {true, false, false, false, false, false, false, false},
                {true, false, false, false, true, true, true, false},
                {true, false, false, false, false, false, true, false},
                {false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false}};
        assertEquals(9, RobotRoute.mostMandMs(board));
    }


}
