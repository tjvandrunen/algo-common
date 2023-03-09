'''
Created on Sep 10, 2019

@author: thomasvandrunen
'''

import graph
from abc import abstractmethod

def traverse(g, s, worklist, f):
    # The traverse tree as a set of parent links,
    # represented as a list of parents indexed by vertex
    parents = [None for v in range(g.num_vertices())]
    
    # Initially the starting vertex is discovered,
    # and it is its own parent
    worklist.add(s)
    parents[s] = s

    while not worklist.is_empty() :
        v = worklist.remove()     # Retrieve the current vertex.
        f(v)                      # Visit it.
        for u in g.adjacents(v) : #Explore its adjacents
            # If any adjacent has no parent, it's newly discovered
            if parents[u] == None :
                # Mark it as discovered by making v its parent
                parents[u] = v
                # Add it to the worklist
                worklist.add(u)
    return parents
    
class TraversalIterator :
    def __init__(self, g, s, worklist):
        self.g = g
        self.s = s
        self.worklist = worklist
        self.parents = [None for v in range(g.num_vertices())]
        self.worklist.add(s)
        self.parents[s] = s

    def __iter__(self):
        return self

    def next(self):
        if self.worklist.is_empty() :
            raise StopIteration
        else :
            v = self.worklist.remove()
            for u in self.g.adjacents(v) : 
                if self.parents[u] == None :
                    self.parents[u] = v
                    self.worklist.add(u)
            return v
        
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

class Worklist:
    def __init__(self):
        self.internal = []
    def add(self, x):
        self.internal.append(x)
    @abstractmethod
    def remove(self):
        pass
    def is_empty(self):
        return len(self.internal) == 0
    
class Stack_Worklist(Worklist):
    def remove(self):
        return self.internal.pop()

class Queue_Worklist(Worklist):
    def remove(self):
        x = self.internal[0]
        del self.internal[0]
        return x
    
class TestTrav(unittest.TestCase):
    def testBFT(self):
        parents = traverse(test_graph, 0, Queue_Worklist(), lambda x: None)
        self.assertEqual([0, 0, 0, 0, 1, 1, 2, 4, 5], parents)
    def testDFT(self):
        parents = traverse(test_graph, 0, Stack_Worklist(), lambda x: None)
        self.assertEqual([0, 0, 0, 0, 1, 7, 3, 8, 6], parents)
    
    
    
    
    
    