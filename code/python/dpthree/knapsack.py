'''
Created on Nov 15, 2019

@author: tvandrun
'''

def knapsack(weights, values, capacity):
    n = len(weights)
    assert n == len(values)
    
    # best_value[i][j] indicates the best value we can get by taking a
    # subset of objects 0 through j without exceeding total weight i
    best_value = [[None for j in range(n)] for i in range(capacity + 1)]

    # take[i][j] means we should take object j to get a set of 
    # object that maximizes the value using only objects through j
    # and not exceeding total weight i
    take = [[None for j in range(n)] for i in range(capacity + 1)]

    # For each "sub-capacity", that is, each whole number less than
    # or equal to the capacity
    for i in range(capacity + 1) :
        # Special case for the 0th item: take it if it fits
        if weights[0] > i :
            take[i][0] = False
            best_value[i][0] = 0
        else :
            take[i][0] = True
            best_value[i][0] = values[0]

        # For all other items
        for j in range(1, n) :
            if weights[j] > i : # If it doesn't fit, don't take it
                take[i][j] = False
                best_value[i][j] = best_value[i][j-1]
            else : # If it fits...
                # The value if we take this item and make best decisions thereafter
                take_value = values[j] + best_value[i - weights[j]][j-1]
                # The value if we skip this item and make best decisions thereafter
                skip_value = best_value[i][j-1]
                # Do whichever is better
                if take_value > skip_value :
                    take[i][j] = True
                    best_value[i][j] = take_value
                else :
                    take[i][j] = False
                    best_value[i][j] = skip_value
    
#     for j in reversed(range(n)):
#         line = "\\textbf{" + str(j) + "}" 
#         for i in range(capacity+1) :
#             line += "&"
#             line += str(best_value[i][j]) + "/"
#             line += "T" if take[i][j] else "S"
#         line += "\\\\"
#         print line
#     line = ""
#     for i in range(capacity+1):
#         line += "& \\textbf{" + str(i) + "}"
#     print line
    
    # Reconstruct the solution from the decisions
    result = [None for j in range(n)]
    capacity_left = capacity
    for j in reversed(range(n)) :
        result[j] = take[capacity_left][j]
        if result[j] :
            capacity_left -= weights[j]
    
    return result
            

import unittest

class TestKnapsack(unittest.TestCase):
    
    def check(self, weights, values, capacity, max_value):
        assert len(weights) == len(values)
        result = knapsack(weights, values, capacity)
        assert len(weights) == len(result)
        total_value = sum([values[i] if result[i] else 0 for i in range(len(values))])
        total_weight = sum([weights[i] if result[i] else 0 for i in range(len(values))])
        self.assertEqual(max_value, total_value)
        self.assertTrue(total_weight <= capacity)
    
    def testDMFP(self):
        # DMFP, pg 502
        self.check([2,3,4,7,8,13,15], [1,4,6,10,11,20,21], 15, 21)
         
    def testCLRS(self):
        # CLRS, pg 427
        self.check([10,20,30], [60,100,120], 50, 220)
 
    def testDPV(self):
        # Dasgupta et al, "Algorithms", pg 164
        self.check([6,3,4,2],[30,14,16,9], 10, 46)
        
    def testAC(self):
        # Algorithmic Commonplaces, Section 6.2
        self.check([1,11,6,5,4],[150,990,70,50,40], 10, 240)
        
    def testFromClass(self):
        # example used in class
        self.check([1,2,4,5],[20,15,90,100],7, 125)