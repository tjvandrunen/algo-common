'''
Created on Nov 21, 2019

@author: tvandrun
'''

def opt_bst(key_probs, miss_probs):
    assert len(key_probs) + 1 == len(miss_probs)
    n = len(key_probs)
    
    # total_probs[i][j] is the sum of all the key probabilities in range [i,j]
    # and all the miss probabilities in range [i,j+1]
    total_probs = [[None for j in range(n)] for i in range(n)]

    # total_weighted_depths[i][j] indicates the minimum total weighted depth
    # for any try for keys in range [i,j]
    total_weighted_depths = [[None for j in range(n)] for i in range(n)]

    # decisions[i][j] indicates the key at the root of the best tree for range [i,j]
    decisions = [[None for j in range(n)] for i in range(n)]
    
    # Base cases for trees with only one key
    for i in range(n):
        total_probs[i][i] = miss_probs[i] + key_probs[i] + miss_probs[i+1]
        total_weighted_depths[i][i] = 2* miss_probs[i] + key_probs[i] + 2 * miss_probs[i+1]
        decisions[i][i] = i
        
    # For each diagonal, identified by the difference between indices
    for d in range(1, n) :
        # For each cell or subproblem in that diagonal, i, j
        for i in range(n - d):
            j = i + d
            # The total probability for range [i, j]
            total_probs[i][j] = miss_probs[i] + key_probs[i] + total_probs[i+1][j]
            # The cost of making key i the root, which is our initial
            # best-so-far
            least_subtree_cost = miss_probs[i] + total_weighted_depths[i+1][j]
            best_root = i
            # For each candiate root r between i and j exclusive
            for r in range(i+1,j):
                # The cost of making key r the root
                current_subtree_cost = total_weighted_depths[i][r-1] + total_weighted_depths[r+1][j]
                # If its cost is better than best so far, it's the new best so far
                if current_subtree_cost < least_subtree_cost :
                    least_subtree_cost = current_subtree_cost
                    best_root = r
            # The cost of making key j the root
            current_subtree_cost = total_weighted_depths[i][j-1] + miss_probs[j+1]
            # If its cost is better than best so far, it's the new best so far
            if current_subtree_cost < least_subtree_cost :
                least_subtree_cost = current_subtree_cost
                best_root = j
            # Record the best option and corresponding cost in the tables
            total_weighted_depths[i][j] = total_probs[i][j] + least_subtree_cost
            decisions[i][j] = best_root

    return total_weighted_depths[0][n-1]

    
print str(opt_bst([.073, .045, .104, .05, .055, .103, .076, .042], [.001, .113, .107, .006, .001, .02, .081, .122, .001]))    
    
    
