package graphTranspose;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestComputeTranspose {

    private static boolean areTransposeOfEachother(Graph g, Graph h) {
        if (g.numVertices() != h.numVertices()) return false;
        if (g.numEdges() != h.numEdges()) return false;
        for (int v = 0; v < g.numVertices(); v++)
            for (int u = 0; u < g.numVertices(); u++)
                if ((g.adjacent(u, v) && ! h.adjacent(v, u)) ||
                    (!g.adjacent(u, v)) && h.adjacent(v, u))
                    return false;
        return true;
                      
    }
    

    private AdjListGraph genGraph(int[][] description) {
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
        AdjListGraph g = genGraph(new int[][] {{1}, {0}}),
                h = ComputeTranspose.computeTranspose(g);
        assertTrue(areTransposeOfEachother(g, h));
    }
    @Test
    public void b() {
        AdjListGraph g = genGraph(new int[][] {{1, 2, 4}, {0, 3}, {0, 1}, {1, 4}, {3}}),
                h = ComputeTranspose.computeTranspose(g);
        assertTrue(areTransposeOfEachother(g, h));
    }
    @Test
    public void c() {
        AdjListGraph g = genGraph(new int[][] {{0, 4, 5}, {0, 2}, {1, 4}, {2, 4}, {3, 5}, {1, 2, 3}}),
                h = ComputeTranspose.computeTranspose(g);
        assertTrue(areTransposeOfEachother(g, h));
    }
    @Test
    public void d() {
        AdjListGraph g = genGraph(new int[][] {{0, 1, 2}, {0, 1, 2}, {0, 1, 2}}),
                h = ComputeTranspose.computeTranspose(g);
        assertTrue(areTransposeOfEachother(g, h));
    }

}
