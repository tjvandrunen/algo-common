'''
Created on May 26, 2015

@author: tvandrun
'''
import random
import copy
import math

def selection_sort(sequence, TO):
    for i in range(len(sequence)) :
        min_pos = i
        min = sequence[i]
        for j in range(i + 1, len(sequence)):
            if TO(sequence[j], min) < 0 :
                min = sequence[j]
                min_pos = j
        sequence[min_pos] = sequence[i]
        sequence[i] = min


def natural_ordering(a, b):
    if a < b :
        return -1
    elif a == b :
        return 0
    else : # if a > b
        return 1

def simple_counting_sort(sequence):
    max_val = max(sequence)
    counts = [0 for i in range(max_val + 1)]
    for x in sequence:
        assert x >= 0 and x <= max_val
        counts[x] += 1
    i = 0
    for x in range(max_val + 1) :
        for j in range(counts[x]):
            sequence[i] = x
            i += 1

    

def counting_sort(sequence, V):
    # Find the greatest value in the sequence,
    # to determine the range. (Assume no negatives,
    # treat 0 as the min value.)
    max_val = max([V(x) for x in sequence])

    # The counts for each key
    counts = [0 for i in range(max_val + 1)]
    # Invariant: counts[j] contains the number
    # of elements seen so far with key j
    for x in sequence:
        counts[V(x)] += 1

    # The locations for the next occurrences of each key
    next_place = [0 for i in range(max_val + 1)]
    # Invariant: Each position in next_place up to
    # i holds the number of elements whose keys are
    # less than that position
    for i in range(1, len(next_place)):
        next_place[i] = next_place[i-1] + counts[i-1]
    
    # A copy of the original sequence, so the
    # original list can be the destination
    aux = copy.copy(sequence)

    # Invariant: Each element seen so far is
    # in its sorted position in sequence and each position
    # in next_place indicates the sorted position of the
    # next element, if any, whose key is that position
    for x in aux :
        sequence[next_place[V(x)]] = x
        next_place[V(x)] += 1

    
def itself(x):
    return x

def radix_sort(sequence, r):
    # What is the maximum number of digits
    # of any number in the list? We can count digits
    # by taking the log base r and rounding up.
    digits = max([int(math.ceil(math.log(x, r))) for x in sequence])

    # The current power of r
    r_pow = 1
    # Invariant: The numbers in the sequence mod r_pow are
    # sorted.
    for i in range(digits):
        counting_sort(sequence, lambda x : (x / r_pow) % r)
        r_pow *= r


def bucket_sort(sequence, r, subsort):
    buckets = [[] for x in range(r)]
    for x in sequence :
        buckets[x / r].append(x)
    for bucket in buckets :
        subsort(bucket)
    result = []
    for bucket in buckets :
        result += bucket
    return result

test_seq = [random.randrange(100) for x in range(30)]
print test_seq
#selection_sort(test_seq, natural_ordering)
#counting_sort(test_seq, itself)
#radix_sort(test_seq, 10)
#simple_counting_sort(test_seq)
#print test_seq
print bucket_sort(test_seq, 10, lambda xx : selection_sort(xx, natural_ordering))

