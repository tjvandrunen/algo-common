'''
Created on Jul 15, 2015

@author: tvandrun
'''

def fib(n):
    if n == 0 :
        return 0
    elif n == 1 :
        return 1
    else :
        return fib(n-1) + fib(n-2)
    
fibs = [0, 1]

def fib_topdn(n):
    if n < len(fibs) :
        return fibs[n]
    else :
        result = fib_topdn(n-1) + fib_topdn(n-2)
        fibs.append(result)
        return result
    
def fib_botup(n):
    f = [0 for x in range(n+1)]
    f[1] = 1
    for i in range(2, n+1) :
        f[i] = f[i-1] + f[i-2]
    return f[n]
    
    

    