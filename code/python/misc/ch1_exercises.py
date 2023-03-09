'''
Created on Sep 5, 2017

@author: tvandrun
'''


def sum(sequence) :
    sum_so_far = 0
    i = 0
    while i < len(sequence) :
        sum_so_far += sequence[i]
    return sum_so_far


def sum(sequence):
    sum_so_far = 0
    for x in sequence :
        sum_so_far += x
    return sum_so_far

def find_smallest(sequence):
    smallest_so_far = sequence[0]
    smallest_pos = 0
    i = 1
    while i < len(sequence) :
        if sequence[i] < smallest_so_far :
            smallest_pos = i
            smallest_so_far = sequence[i]
    return smallest_pos

            
        
    