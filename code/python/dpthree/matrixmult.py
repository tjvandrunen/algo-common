'''
Created on Nov 15, 2019

@author: tvandrun
'''
import sys

def plan_mat_mult(dimensions):
    num_matrices = len(dimensions)- 1
    
    # num_mults[i][j] indicates the minimum number of scalar multiplications (cost)
    # necessary to multiply matrices i through j, inclusive
    num_mults = [[None for j in range(num_matrices)] for  i in range(num_matrices)]

    # decisions[i][j] indicates where to parenthsize matrices i through j, inclusive
    # in the grouping that has the minimum number of scalar multiplications.
    # If k = decisions[i][j], then group these matrices, in part, as 
    # (A_i ... A_k)(A_k+1 ... A_j)
    decisions = [[None for j in range(num_matrices)] for  i in range(num_matrices)]

    # Trivial (base) cases for single matrices
    for i in range(num_matrices):
        num_mults[i][i] = 0
        
    # For each diagonal, identified by the difference d between indices
    for d in range(1, num_matrices) :
        # For each cell or subproblem in that diagonal i, j
        for i in range(num_matrices - d) :
            j = i + d
            # The best option seen so far and the corresponding cost
            best_break = None
            best_num_mults = sys.maxint
            # For each place k where we could break this matrix sequence,
            # see if it's better than the best seen so far
            for k in range(i, j):
                current_num_mults = (num_mults[i][k] + 
                                     dimensions[i]*dimensions[k+1]*dimensions[j+1] +
                                     num_mults[k+1][j])
                if current_num_mults < best_num_mults :
                    best_break = k
                    best_num_mults = current_num_mults
            # Record the best option and corresponding cost in the tables
            num_mults[i][j] = best_num_mults
            decisions[i][j] = best_break

            
#     line = ""
#     for i in reversed(range(num_matrices)):
#         line += "\\ptb{\\textbf{" + str(i) + "}}&"
#     print line + "\\\\"
#     for j in reversed(range(num_matrices)) :
#         line = ""
#         for i in reversed(range(num_matrices)):
#             if i <= j :
#                 line += "\\ptb{" + str(num_mults[i][j]) + "/" + str(decisions[i][j]) + "}"
#             line += "&"
#         line += "\\ptb{\\textbf{" + str(j) + "}}\\\\"
#         print line
    

    # Reconstruct the solution from the decisions            
    return parenthesize(0, num_matrices-1, decisions)
                  
                                         
def parenthesize(i, j, decisions):
    if i == j:
        return str(i)
    else :
        #print str(i) + " "  +str(j) + " " + str(decisions[i][j])
        k = decisions[i][j]
        return "(" + parenthesize(i, k, decisions) + parenthesize(k+1, j, decisions) + ")"
 
print plan_mat_mult([10,8,4,7,3,6,9,5])

import unittest

class TestOMM(unittest.TestCase):
    
    def testCLRS(self):
        self.assertEqual("((0(12))((34)5))", plan_mat_mult([30, 35, 15, 5, 10, 20, 25]))

    def testAC(self):
        self.assertEqual("((01)2)", plan_mat_mult([5,8,3,6]))