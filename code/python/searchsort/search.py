'''
Created on Jul 16, 2014

@author: tvandrun
'''

def bounded_linear_search(sequence, P):
    found = False
    i = 0
    # invariant: 
    #       for all j, 0 <= j < i - 1, not P(sequence[j])
    #   and found iff P(sequence[i-1])
    while not found and i < len(sequence) :
        found = P(sequence[i])
        i = i + 1
    if found :
        return i - 1
    else :
        return -1
    
def binary_search(sequence, TO, item):
    low = 0
    high = len(sequence)
    # invariant:
    #    if exists i, 0 <= i < n such that item == sequence[i]
    #    then exists i, low <= i < high such that item == sequence[i]
    while high - low > 1 :
        mid = (low + high) / 2
        compar = TO(item, sequence[mid])
        if compar < 0 :
            high = mid
        elif compar > 0 :
            low = mid + 1
        else :
            low = mid
            high = mid + 1
    if low < high and TO(item, sequence[low]) == 0 :
        return low
    else :
       return -1 
   
test_seq = [4, 5, 6, 7,  9, 10, 11]

def binary_search_recursive(sequence, TO, item, start, stop):
    if start == stop:
        return -1
    else :
        mid = (stop + start) / 2
        compar = TO(item, sequence[mid])
        if compar < 0 :
            return binary_search_recursive(sequence, TO, item, start, mid)
        elif compar > 0 :
            return binary_search_recursive(sequence, TO, item, mid + 1, stop) 
        else :
            return mid

def natural_ordering(a, b):
    if a < b :
        return -1
    elif a == b :
        return 0
    else : # if a > b
        return 1

    
for i in range(15) :
    boulinsea_result = bounded_linear_search(test_seq, lambda x : i == x)
    binsea_result = binary_search(test_seq, natural_ordering, i)
    binsearec_result = binary_search_recursive(test_seq, natural_ordering, i,  
                                               0, len(test_seq))
    print i, boulinsea_result, binsea_result, binsearec_result
                                   