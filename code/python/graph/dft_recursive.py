'''
Created on Sep 12, 2019

@author: tvandrun
'''

import graph

# Traverse graph g starting at s in depth-first order,
# applying function f to every vertex reachable from s.
# The work of the traversal is delegate to depth_first_r,
# which this function calls to start the recursive process.
def depth_first(g, s, f):
    parents = [None for v in range(g.num_vertices())]
    parents[s] = s
    depth_first_r(g, s, f, parents)
    return parents

# Visit the vertices in g reachable from v that haven't
# been visited yet according to the parents list,
# applying function f to each of them.
# Precondition: v is discovered but not visited
# Postcondition: v and every vertex reachable from v is visited
def depth_first_r(g, v, f, parents):
    f(v)
    for u in g.adjacents(v):
        if parents[u] == None :
            parents[u] = v
            depth_first_r(g, u, f, parents)
    



import unittest

builder = graph.ALGBuilder(9)
builder.union(0, 1)
builder.union(0, 2)
builder.union(0, 3)
builder.union(1, 4)
builder.union(1, 5)
builder.union(2, 1)
builder.union(2, 5)
builder.union(2, 6)
builder.union(3, 0)
builder.union(3, 6)
builder.union(4, 7)
builder.union(5, 8)
builder.union(6, 8)
builder.union(7, 5)
builder.union(8, 7)
builder.union(8, 3)
test_graph = builder.get_graph()

class TestDFTR(unittest.TestCase):
    def testSmall(self):
        parents = depth_first(test_graph, 0, lambda x: None)
        self.assertEqual([0, 0, 0, 8, 1, 7, 3, 4, 5], parents)

