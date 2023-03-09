'''
Created on Sep 17, 2019

@author: tvandrun
'''

import sys
import graph
import copy
from priorityqueue import PriorityQueue

# ----- Brute-force MST and its helper functions -----

# Test if all vertices in given graph g (assumed symmetric,
# and so undirected for practical purposes) are reachable 
# in the subgraph described by the given set of (undirected) edges.
# Do a simplified DFT from 0 and see if all vertices are discovered.
def reachable(g, edges):
    discovered = [False for v in range(g.num_vertices())]
    discovered[0] = True
    worklist = [0]
    while len(worklist) != 0 :
        v = worklist.pop()
        for u in g.adjacents(v) :
            if not discovered[u] and ((u,v) in edges or (v, u) in edges):
                discovered[u] = True
                worklist.append(u)
    return not False in discovered

# Generate a list of all edges (as undirected) in g
def generate_undirected_edgelist(g):
    edges = []
    for v in range(g.num_vertices()):
        for u in range(v, g.num_vertices()) :
            if g.adjacent(v, u) :
                assert g.adjacent(u, v)
                edges.append((v, u))
    return edges

# Generate the set of all given-sized subsets of elements in a given list
def subsets(xx, size):
    assert len(xx) >= size
    assert size > 0
    if size == 1 :
        return {frozenset({x}) for x in xx}
    elif len(xx) == size :
        return {frozenset(xx)}
    else :
        assert len(xx) > size
        without_zeroth = subsets(xx[1:], size)
        needs_zeroth = subsets(xx[1:], size-1)
        return without_zeroth.union({yy.union({xx[0]}) for yy in needs_zeroth})

# Compute the total weight of an edge set for a given graph
def total_weight(edge_set, g):
    return sum([g.weight(u,v) for (u,v) in edge_set])

# Compute an MST for a given graph by brute force
def mst_brute_force(g):
    # Generate all subsets of size V-1.
    # Function subsets() takes a list and an int and returns all
    # combinations (a set of all subsets) of a given length.
    edge_subsets = subsets(generate_undirected_edgelist(g), g.num_vertices() - 1)
    # Keep the subsets that are spanning trees.
    # Function reachable() takes a graph (assumed symmetric) and a set
    # of edges and tests if all vertices are reachable from 0 using
    # only the given edges and their opposites.
    spanning_trees = [t for t in edge_subsets if reachable(g, t)]
    # Find the spanning tree with least weight
    min_weight = sys.maxint
    mst = None
    for t in spanning_trees :
        # Compute the total weight for candidate spanning tree t
        # Function total_weight() sums the weights of the 
        # edges in a given edge set.
        weight = total_weight(t, g) 
        if weight < min_weight :
            min_weight = weight
            mst = t
    return mst

# ----- Kruskal MST and its helper functions -----
        
# A quick and simple array-forest disjoint set, no optimization
class DisjointSet:
    def __init__(self, n):
        self.parents = [i for i in range(n)]
        
    def find(self, i):
        if self.parents[i] == i :
            return i
        else :
            return self.find(self.parents[i])
        
    def connected(self, i, j):
        i_lead = self.find(i)
        j_lead = self.find(j)
        return i_lead == j_lead
    
    def union(self, i, j):
        i_lead = self.find(i)
        j_lead = self.find(j)
        if i_lead != j_lead :
            self.parents[i_lead] = j_lead

# Generate a list of all edges in g as undirected edges
# with weights, represented as triples: end point, end point, weight.
def undirected_weighted_edges(g):
    edges = [None for i in range(g.num_edges()/2)]
    i = 0
    for u in range(g.num_vertices()):
        for v in g.adjacents(u):
            if u < v :
                edges[i] = (u, v, g.weight(u, v))
                i += 1
    assert i == len(edges)
    return edges

# Counting sort, pasted from sort.py in other project
def counting_sort(sequence, V):
    max_val = max([V(x) for x in sequence])
    counts = [0 for i in range(max_val + 1)]
    for x in sequence:
        counts[V(x)] += 1
    next_place = [0 for i in range(max_val + 1)]
    for i in range(1, len(next_place)):
        next_place[i] = next_place[i-1] + counts[i-1]
    aux = copy.copy(sequence)
    for x in aux :
        sequence[next_place[V(x)]] = x
        next_place[V(x)] += 1

# Compute an MST for the given graph using Kruskal's algorithm            
def mst_kruskal(g):
    # Make a new disjoint set to track the connected  components. 
    # Each vertex initially is in its own component.
    components = DisjointSet(g.num_vertices())
    # The edges, sorted by weight
    edges = undirected_weighted_edges(g)
    counting_sort(edges, lambda e: e[2])
    # The running subset of a MST, initially empty
    A = set()
    # Iterate through the edges by order of increasing weight
    for (v,u,w) in edges :
        # If this edge connects two distinct components,
        # merge the components and add the edge to A
        if not components.connected(v, u) :
            components.union(v, u)
            A.add((v, u))
    return A

# ----- Prim MST and its helper functions -----


# Compute an MST for the given graph using Prim's algorithm
def mst_prim(g):
    # List representing a "parents" attribute for vertices:
    # What vertex would be a v's parent if the minimum
    # weight edge known to union v to the tree were
    # added to the tree?
    parents = [None for v in range(g.num_vertices())]
    # The minimum known weight of any edge to union v to the tree
    distances = [sys.maxint for v in range(g.num_vertices())]
    # The running subset of a MST, initially empty
    A = set()
    # Make a new priority queue with the vertices as keys,
    # all initially with equal, minimum priority (maximum weight)
    pq = PriorityQueue(g.num_vertices(), distances)

    # Iterate through vertices as retrieved from the worklist
    while not pq.is_empty() :
        v = pq.extract_max()
        # Add the edge from v's potential parent to the set,
        # unless v is the first vertex we process.
        # (v is the first vertex) iff (parents[v] == None) iff (len(A) == 0)
        if parents[v] == None :
            assert len(A) == 0
        else :
            A.add((parents[v], v))
        # For each of v's edges, test whether they represent
        # a better way to union any of v's unconnected adjacents
        # than previously known.
        for u in g.adjacents(v):
            if (pq.contains(u) and 
                #(parents[u] == None or g.weight(v, u) < g.weight(parents[u], u))) :
                (parents[u] == None or g.weight(v, u) < distances[u])) :
                parents[u] = v
                distances[u] = g.weight(v, u)
                pq.increase_key(u)
    return A
        
        
        
builder = graph.ALWGBuiler(7)
builder.union(0, 1, 6)
builder.union(1, 0, 6)
builder.union(0, 2, 8)
builder.union(2, 0, 8)
builder.union(0, 3, 1)
builder.union(3, 0, 1)
builder.union(1, 3, 12)
builder.union(3, 1, 12)
builder.union(2, 3, 3)
builder.union(3, 2, 3)
builder.union(2, 5, 5)
builder.union(5, 2, 5)
builder.union(3, 4, 11)
builder.union(4, 3, 11)
builder.union(3, 5, 9)
builder.union(5, 3, 9)
builder.union(4, 5, 2)
builder.union(5, 4, 2)
builder.union(4, 6, 4)
builder.union(6, 4, 4)
builder.union(5, 6, 10)
builder.union(6, 5, 10)
test_graph = builder.get_graph()

print "Brute force:"
brute_force_result = mst_brute_force(test_graph)
print brute_force_result
print total_weight(brute_force_result, test_graph)
print "Kruskal:"
kruskal_result = mst_kruskal(test_graph)
print kruskal_result
print total_weight(kruskal_result, test_graph)
print "Prim:"
prim_result = mst_prim(test_graph)
print prim_result
print total_weight(prim_result, test_graph)








