'''
Created on Oct 16, 2017

@author: thomasvandrunen
'''


def eval_poly(coefficients, x):
    x_pow = 1.0
    result = 0.0
    for c in coefficients:
        result += c * x_pow
        x_pow *= x
    return result

def eval_poly_horner(coefficients, x):
    result = 0.0
    for c in reversed(coefficients) :
        result *= x
        result += c
    return result


print eval_poly([1,2,3], 5)
print eval_poly_horner([1,2,3], 5)
    