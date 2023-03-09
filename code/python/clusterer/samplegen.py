'''
Created on Aug 22, 2014

@author: tvandrun
'''

import sys

with open(sys.argv[1], 'r') as input_file:
    data = sorted(map(float, input_file))

print data

points = map(lambda x : int(135 * (100 - x)), data)

print points

outfile = open(sys.argv[1] + ".fig", 'w')
outfile.write('''#FIG 3.2  Produced by xfig version 3.2.5b
Landscape
Center
Metric
A4
100.00
Single
-2
1200 2
''')
x = 450
for y in points :
    outfile.write("1 3 0 1 0 0 50 -1 20 0.000 1 0.0000 %s %s 23 23 %s %s 473 %s\n" % (x, y, x, y, y))
    #x = x + 10
outfile.write('''2 1 0 1 0 7 50 -1 -1 0.000 0 0 -1 0 0 2
     0 0 0 5400
2 1 0 1 0 7 50 -1 -1 0.000 0 0 -1 0 0 2
     -225 0 225 0
2 1 0 1 0 7 50 -1 -1 0.000 0 0 -1 0 0 2
     -225 5400 225 5400
2 1 0 1 0 7 50 -1 -1 0.000 0 0 -1 0 0 2
     -225 1350 225 1350
2 1 0 1 0 7 50 -1 -1 0.000 0 0 -1 0 0 2
     -225 2698 225 2698
2 1 0 1 0 7 50 -1 -1 0.000 0 0 -1 0 0 2
     -225 4052 225 4052''')
outfile.close()




