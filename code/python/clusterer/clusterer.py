'''
Created on Dec 17, 2019

@author: tvandrun
'''
import numpy
import sys

def natural_breaks(data, num_classes):
    num_points = len(data)
    assert num_classes <= num_points
    for i in range(num_points-1) :
        assert data[i] <= data[i+1]

    # min_withinss[m][j] indicates the minimum sum of squared differences from class
    # means (withinss) among ways to classify the data points [0, m) into j classes
    min_withinss = [[None for j in range(num_classes+1)] for m in range(num_points+1)]
    
    # decisions[m][j] indicates the lower bound of the top interval in the 
    # way to classify the data points [0, m) into j classes with minimum withinss
    decisions = [[None for j in range(num_classes+1)] for m in range(num_points+1)]
    
    # Base cases for one class (not valid when num_points - m < num_classes -1)
    for m in range(1,num_points-num_classes+2) :
        min_withinss[m][1] = single_class_withinss(data[:m])
        decisions[m][1] = 0

    # For each number number of classes, greater than one,
    # find the minimum withinss for data points [0, m) for each valid m
    for j in range(2, num_classes+1) :
        # Valid cases require j <= m <= n - k + j
        # Base case when m = j:
        min_withinss[j][j] = 0
        decisions[j][j] = j
        # Recursive cases
        # For each 
        for m in range(j+1, num_points - num_classes + j + 1) :
            # The best lower bound for the top interval seen so far
            # and its corresponding withinss
            best_bound = None
            best_withinss = sys.maxint
            # The lower bound must be greater than j so that there
            # are enough remaining points for the remaining classes.
            # For each candidate lower bound b, 
            # see if it's better than the best seen so far. 
            for b in range(j-1, m) :
                current_withinss = (min_withinss[b][j-1] + 
                                    single_class_withinss(data[b:m]))
                if current_withinss < best_withinss :
                    best_withinss = current_withinss
                    best_bound = b
            # Record teh best lower bound and corresponding withinss in the tables
            min_withinss[m][j] = best_withinss
            decisions[m][j] = best_bound
            
    for j in reversed(range(num_classes)):
        line = "\\textbf{" + str(j+1)+"}"
        for m in range(num_points) :
            line += "&" + (" " if min_withinss[m+1][j+1]==None else (("%.1f"%min_withinss[m+1][j+1]) + "/" + str(decisions[m+1][j+1])))
        line += "\\\\"
        print line
    line = ""
    for m in range(num_points) :
        line += "&\\textbf{" + str(m+1) + "}"
    line += "\\\\"
    print line
    line = ""
    for m in range(num_points) :
        line += "&" + str(data[m])
    line += "\\\\"
    print line
    
    # Reconstruct the solution from the decisions
    breaks = [None for j in range(num_classes)]
    upper_bound = num_points
    for j in reversed(range(num_classes)) :
        breaks[j] = decisions[upper_bound][j+1]
        upper_bound = breaks[j]

    breaks.append(num_points)        
    return breaks
                

def single_class_withinss(cluster):
    mu = numpy.average(cluster)
    return sum([pow(z-mu, 2) for z in cluster])

with open(sys.argv[1], 'r') as input_file:
    data = sorted(map(float, input_file))

try :
    num_classes = int(sys.argv[2])
except (IndexError, ValueError) :
    num_classes = 8

breaks = natural_breaks(data, num_classes)
print breaks
for i in range(num_classes) :
    print(data[breaks[i]:breaks[i+1]])

