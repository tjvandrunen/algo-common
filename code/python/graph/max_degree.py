'''
Created on Sep 10, 2019

@author: thomasvandrunen
'''
#from graph.graph import ALGBuilder
import graph

def max_degree(g):
    maxdeg = 0
    for v in range(g.num_vertices()) :
        deg = len(g.adjacents(v))
        if deg > maxdeg:
            maxdeg = deg
    return maxdeg

import unittest

class TestMD(unittest.TestCase):
    def testSmall(self):
        builder = graph.ALGBuilder(3)
        builder.union(0,1)
        builder.union(1,0)
        builder.union(2,1)
        builder.union(1,2)
        test_graph = builder.get_graph()
        self.assertEqual(2, max_degree(test_graph))
        
    def testBig(self):
        builder = graph.ALGBuilder(6)
        builder.union(0,1)
        builder.union(1,0)
        builder.union(0,2)
        builder.union(2,0)
        builder.union(0,3)
        builder.union(3,0)
        builder.union(1,2)
        builder.union(2,1)
        builder.union(1,5)
        builder.union(5,1)
        builder.union(2,3)
        builder.union(3,2)
        builder.union(2,5)
        builder.union(5,2)
        builder.union(2,4)
        builder.union(4,2)
        builder.union(3,4)
        builder.union(4,3)
        builder.union(4,5)
        builder.union(5,4)
        test_graph = builder.get_graph()
        self.assertEqual(5, max_degree(test_graph))
        
