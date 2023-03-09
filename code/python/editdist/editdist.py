'''
Created on Jun 11, 2020

@author: tvandrun
'''

PRINT_TABLE = True


def edit_distance(a, b, costs):
    n = len(a)
    m = len(b)

    distances = [[None for j in range(m+1)] for i in range(n+1)]
    decisions = [[None for j in range(m+1)] for i in range(n+1)]
    
    for j in range(m+1):
        distances[0][j] = j * costs[0]
        decisions[0][j] = "ins-all"
    
    for i in range(n+1) :
        distances[i][0] = i * costs[1]
        decisions[i][0] = "del-all"

    # Add code here -- populate the distances and decisions table
    
    
    # print table
    if PRINT_TABLE :
        for j in reversed(range(m+1)):
            line = " " if j == 0 else str(b[j-1]) 
            for i in range(n+1) :
                line += "&" + str(distances[i][j]) + "/" + decisions[i][j]
            line += "\\\\"
            print line
        line = " "
        for i in range(n) :
            line += "&" + str(a[i])
        print line

    
    
    return distances[n][m]

edit_distance("carving", "craven", [2,2,1,1])

edit_distance("return", "enter", [2,0,1,0])

import unittest

class TestLCS(unittest.TestCase):
    
    def testTrivial(self):
        self.assertEquals(0, edit_distance("", "", [1,1,1,1]))
        
    def testAllIns(self):
        self.assertEquals(3, edit_distance("", "boo", [1,1,1,1]))
        
    def testAllDel(self):
        self.assertEquals(3, edit_distance("boo", "", [1,1,1,1]))
        
    def testJustSub(self):
        self.assertEquals(1, edit_distance("x", "y", [1,1,1,1]))
        
    def testOneSub(self):
        self.assertEquals(1, edit_distance("xxx", "xyx", [1,1,1,1]))
        
    def testManySubs(self):
        self.assertEquals(3, edit_distance("cemetary", "seminary", [1,1,1,1]))
        
    def testDefiantlyBySub(self):
        self.assertEquals(3, edit_distance("defiantly", "definitely", [1,1,1,1]))
