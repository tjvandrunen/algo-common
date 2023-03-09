'''
Created on Sept 10, 2019

@author: tvandrun
'''

from abc import abstractmethod

class Graph:
    @abstractmethod
    def num_vertices(self):
        pass

    @abstractmethod
    def num_edges(self):
        pass
    
    @abstractmethod
    def adjacents(self, v):
        pass
    
    @abstractmethod
    def adjacent(self, u, v):
        pass
    
class WeightedGraph:
    @abstractmethod
    def weight(self, u, v):
        pass
    
class AdjListGraph(Graph):
    def __init__(self, V):
        self.V = V
        self.edges = [[] for v in range(V)]
        
    def num_vertices(self):
        return self.V
    
    def num_edges(self):
        return sum([len(adjs) for adjs in self.edges])
    
    def adjacents(self, v):
        return self.edges[v]
    
    def adjacent(self, u, v):
        return v in self.edges[u]

class AdjListWeightedGraph(AdjListGraph, WeightedGraph):
    def __init__(self, V):
        self.V = V
        self.edges = [{} for v in range(V)]
    
    def weight(self, u, v):
        if v in self.edges[u] :
            return self.edges[u][v]
        else :
            return None
        
    
class ALGBuilder :
    def __init__(self, V):
        self.graph = AdjListGraph(V)
        
    def connect(self, u, v):
        self.graph.edges[u].append(v)
        
    def get_graph(self):
        return self.graph

class ALWGBuiler :
    def __init__(self, V):
        self.graph = AdjListWeightedGraph(V)
        
    def connect(self, u, v, w):
        self.graph.edges[u][v] = w

    def get_graph(self):
        return self.graph

