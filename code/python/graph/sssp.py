'''
Created on Sep 24, 2019

@author: thomasvandrunen
'''

import sys
import graph
from priorityqueue import PriorityQueue

# Compute the shortest paths from a given source in a given graph
# using brute force
def sssp_brute_force(g, s):
    # List representing a "parents" attribute for vertices:
    # What vertex comes before v on the shortest path from
    # s to v currently known?
    parents = [None for v in range(g.num_vertices())]
    # List representing a "distances" attribute for vertices:
    # What is the total weight of the shortest known path
    # from s to v?
    distances = [sys.maxint for v in range(g.num_vertices())]
    # The source is its own parent with 0 distance
    parents[s] = s
    distances[s] = 0
    # Did we discover any improvements on the previous round?
    changed = True
    # Repeat rounds of relaxations until convergence.
    while changed :
        changed = False
        # A round of relaxations
        for v in range(g.num_vertices()) :            
            for u in g.adjacents(v) :
                # Relax edge (u, v)
                if distances[u] > distances[v] + g.weight(v, u) :
                    distances[u] = distances[v] + g.weight(v, u)
                    parents[u] = v
                    changed = True
    return parents

# Compute the shortest paths from a given source in a given graph
# using the Bellman-Ford algorithm
def sssp_bellman_ford(g, s):
    # List representing a "parents" attribute for vertices:
    # What vertex comes before v on the shortest path from
    # s to v currently known?
    parents = [None for v in range(g.num_vertices())]
    # List representing a "distances" attribute for vertices:
    # What is the total weight of the shortest known path
    # from s to v?
    distances = [sys.maxint for v in range(g.num_vertices())]
    # The source is its own parent with 0 distance
    parents[s] = s
    distances[s] = 0
    # Repeat V-1 times
    for i in range(g.num_vertices() - 1) :
        # A round of relaxations
        for v in range(g.num_vertices()) :            
            for u in g.adjacents(v) :
                if distances[u] > distances[v] + g.weight(v, u) :
                    distances[u] = distances[v] + g.weight(v, u)
                    parents[u] = v
    return parents


# Compute the shortest paths from a given source in a given graph
# using Dijkstra's algorithm
def sssp_dijkstra(g, s):
    # List representing a "parents" attribute for vertices:
    # What vertex comes before v on the shortest path from
    # s to v currently known?
    parents = [None for v in range(g.num_vertices())]
    # List representing a "distances" attribute for vertices:
    # What is the total weight of the shortest known path
    # from s to v?
    distances = [sys.maxint for v in range(g.num_vertices())]
    # The source is its own parent with 0 distance
    parents[s] = s
    distances[s] = 0
    # A priority queue with the vertices as keys,
    # all initially with equal, minimum priority (maximum weight)
    pq = PriorityQueue(g.num_vertices(),distances)
    # The only heapification we need to do for the priority queue is
    # get the source as the highest-priority element.
    pq.increase_key(s)
    # Iterate through vertices as retrieved from the worklist
    while not pq.is_empty() :
        v = pq.extract_max()
        # Relax v's outgoing edges
        for u in g.adjacents(v) :
            if distances[u] > distances[v] + g.weight(v, u) :
                distances[u] = distances[v] + g.weight(v, u)
                parents[u] = v
                pq.increase_key(u)
    return parents
    
def a_star(g, s, D, h):
    parents = [None for v in range(g.num_vertices())]
    distances = [sys.maxint for v in range(g.num_vertices())]
    parents[s] = s
    distances[s] = 0
    pq = PriorityQueue(g.num_vertices(),lambda x: distances[x] + h(x))
    pq.increase_key(s)
    d = None
    while d == None :
        v = pq.extract_max()
        if v in D :
            d = v
        else :
            for u in g.adjacents(v) :
                if distances[u] > distances[v] + g.weight(v, u) :
                    distances[u] = distances[v] + g.weight(v, u)
                    parents[u] = v
                    pq.increase_key(u)
    path = []
    v = d
    while v != s:
        path.insert(0, (parents[v], v))
        v = parents[v]
    return path    
    

builder = graph.ALWGBuiler(10)
builder.union(0, 1, 3)
builder.union(0, 2, 1)
builder.union(0, 3, 9)
builder.union(2, 3, 10)
builder.union(2, 5, 1)
builder.union(3, 4, 4)
builder.union(3, 6, 2)
builder.union(3, 5, 6)
builder.union(4, 7, 6)
builder.union(5, 6, 14)
builder.union(5, 8, 1)
builder.union(6, 4, 3)
builder.union(7, 6, 1)
builder.union(7, 8, 5)
builder.union(8, 9, 1)
builder.union(9, 7, 1)
test_graph = builder.get_graph()

print "Answer should be:"
print [0,0,0,0, 6, 2, 7, 9, 5, 8]
print "Brute force:"
brute_force_result = sssp_brute_force(test_graph, 0)
print brute_force_result
print "Bellman-Ford:"
bellman_ford_result = sssp_bellman_ford(test_graph, 0)
print bellman_ford_result
print "Dijkstra:"
dijkstra_result = sssp_dijkstra(test_graph, 0)
print dijkstra_result

    