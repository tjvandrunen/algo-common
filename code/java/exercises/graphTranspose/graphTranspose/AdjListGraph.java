package graphTranspose;

import java.util.Iterator;


/**
 * AdjListGraph
 * 
 * Graph implementation using an adjacency list.
 * Actually, we'll interact with these "lists"
 * as sets, which is what they are abstractly.
 * The fact that they are linked lists underneath
 * affects performance.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 17, 2015
 */
public class AdjListGraph implements Graph {

    /**
     * For each vertex u, the list of vertices v for
     * which there exists and edge from u to v.
     */
    private Set<Integer>[] adjSets;
    
    /**
     * Total number of edges, stored for performance.
     */
    private int numEdges;

    /**
     * Plain constructor.
     */
    @SuppressWarnings("unchecked") 
    private AdjListGraph(int numVertices) {
        adjSets = (Set<Integer>[]) new Set[numVertices];
        for (int i = 0; i < numVertices; i++)
        	adjSets[i] = new LinkedSet<Integer>();
    }

    /**
     * Builder for AdjListGraphs. This allows
     * a graph to be made by adding edges, but enforces
     * a graph to be immutable once construction is
     * finished. If, after a call to getGraph(), either
     * connect() is called or getGraph() is called again,
     * then a NullPointerException will be thrown (not an 
     * ideal exception for this, but unfortunately Java
     * doesn't seem to have a standard exception to use for
     * an expired operation).
     */
    public static class ALGBuilder {
        private AdjListGraph graph;
        public ALGBuilder(int numVertices) {
            graph = new AdjListGraph(numVertices);
        }
        public void connect(int u, int v) {
            graph.adjSets[u].add(v);
            graph.numEdges++;
        }
        public AdjListGraph getGraph() {
            AdjListGraph toReturn = graph;
            graph = null;
            return toReturn;
        }
    }
   
    
    /**
     * The number of vertices in the graph.
     */
   public int numVertices() {
        return adjSets.length;
    }
   
   /**
    * Iterate through the vertices adjacent to the given
    * vertex. Note that in a directed graph, these are
    * the vertices such that an edge exists from the
    * given one to them. 
    */
   public Iterable<Integer> adjacents(int v) {
       return adjSets[v];
   }

   /**
    * Determine adjacency between two given vertices.
    * For undirected graphs, this relationship is
    * symmetric and so this method returns the same
    * result whatever order the parameters are given.
    * For directed graphs, this method indicates whether
    * or not there exists an edge from u to v.
    */
    public boolean adjacent(int u, int v) {
        boolean foundIt = false;
        for (Iterator<Integer> it = adjSets[u].iterator(); 
                !foundIt && it.hasNext(); )
            foundIt |= it.next() == v;
        return foundIt;
    }
    
    /**
     * The number of edges in the graph.
     */
    public int numEdges() {
        return numEdges;
    }
}
