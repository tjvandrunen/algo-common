package thereAndBackAgain;

/**
 * Graph
 * 
 * Interface to define the external operations of a graph.
 * This is independent of whether the graph is directed
 * or undirected, though that may affect the interpretation
 * of the operations. A weighted graph will have additional
 * operations and is thus a subinterface.
 * 
 * Vertices are known by this interface only by number.
 * If the graph has V vertices, those vertices are labeled
 * 0 through V-1.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 16, 2015
 */
public interface Graph {

    /**
     * The number of vertices in the graph.
     */
    int numVertices();

    /**
     * The number of edges in the graph.
     */
    int numEdges();
    
    /**
     * Iterate through the vertices adjacent to the given
     * vertex. Note that in a directed graph, these are
     * the vertices such that an edge exists from the
     * given one to them. 
     */
    Iterable<Integer> adjacents(int v);
    
    /**
     * Determine adjacency between two given vertices.
     * For undirected graphs, this relationship is
     * symmetric and so this method returns the same
     * result whatever order the parameters are given.
     * For directed graphs, this method indicates whether
     * or not there exists an edge from u to v.
     */
    boolean adjacent(int u, int v);
}
