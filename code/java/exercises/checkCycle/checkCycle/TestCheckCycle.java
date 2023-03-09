package checkCycle;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCheckCycle {

    private Graph genGraph(int[][] description) {
        AdjListGraph.ALGBuilder builder = 
                new AdjListGraph.ALGBuilder(description.length);
        int i = 0;
        for (int[] adjList : description) {
            for (int adj : adjList) {
                builder.connect(i, adj);
            }
            i++;
        }
        return builder.getGraph();
    }
    
    @Test
    public void a() {
        assertTrue(CheckCycle.checkCycle(genGraph(new int[][] {{1}, {0}}), 0));
    }
    @Test
    public void b() {
        assertTrue(CheckCycle.checkCycle(genGraph(new int[][] {{1}, {2}, {0}}), 0));
    }
    @Test
    public void c() {
        assertTrue(CheckCycle.checkCycle(genGraph(new int[][] {{1}, {2,3}, {}, {0}}), 0));
    }
    @Test
    public void d() {
        assertTrue(CheckCycle.checkCycle(genGraph(new int[][] {{1}, {2}, {3}, {1,0}}), 0));
    }
    @Test
    public void e() {
        assertTrue(CheckCycle.checkCycle(genGraph(new int[][] {{1}, {2}, {1,3}, {4,5}, {0,2}, {}}), 0));
    }
    @Test
    public void f() {
        assertFalse(CheckCycle.checkCycle(genGraph(new int[][] {{1}, {}}), 0));
    }
    @Test
    public void g() {
        assertFalse(CheckCycle.checkCycle(genGraph(new int[][] {{1}, {2}, {}}), 0));
    }
    @Test
    public void h() {
        assertFalse(CheckCycle.checkCycle(genGraph(new int[][] {{1}, {2}, {1}}), 0));
    }
    @Test
    public void i() {
        assertFalse(CheckCycle.checkCycle(genGraph(new int[][] {{1}, {2}, {3}, {4}, {1}}), 0));
    }
    @Test
    public void j() {
        assertFalse(CheckCycle.checkCycle(genGraph(new int[][] {{1, 4}, {2, 3}, {3}, {5}, {3,5}, {}}), 0));
    }
    @Test
    public void k() {
        assertFalse(CheckCycle.checkCycle(genGraph(new int[][] {{}}), 0));
    }

    
}
