import sys

def make_change_us(amount):
    result = [0, 0, 0, 0]
    result[3] = amount / 25
    amount %= 25
    result[2] = amount / 10
    amount %= 10
    result[1] = amount / 5
    amount %= 5
    result[0] = amount
    return result

def make_change_wrong(amount, denoms):
    assert denoms[0] == 1
    result = [0 for i in range(len(denoms))]
    current_coin = len(denoms) - 1
    while amount > 0 :
        result[current_coin] = amount / denoms[current_coin]
        amount %= denoms[current_coin]
        current_coin -= 1
    return result

def min_change_naive(i, j, denoms):
    # base case 1: zero amount, zero coins
    if i == 0 :
        return 0
    # base case 2: only unit coins, quantity equals amount
    elif j == 0 :
        assert denoms[0] == 1
        return i
    # recursive case
    else :
        denom_val = denoms[j]
        denom_quant = 0
        # the fewest coins so far, initially the fewest coins
        # without using the largest denomination
        fewest_coins = min_change_naive(i, j-1, denoms)
        # iterate through other feasible quantities
        # of the largest denomination
        denom_quant = 1
        amount_left = i - denom_val        
        while amount_left >= 0 :
            current_coins = min_change_naive(i-amount_left, j-1, denoms)
            if current_coins < fewest_coins :
                fewest_coins = current_coins
            denom_quant += 1
            amount_left = i - denom_quant * denom_val
        return fewest_coins

def make_change_naive(amount, denoms):
    # The number of denominations in this subproblem
    n = len(denoms)
    # base case 1: if the amount is 0, give 0 of each coin
    if amount == 0:
        return [0 for i in range(n)]
    # base case 2: if there is only one denomination (of value 1),
    # give the entire amount in that denomination
    elif n == 1 :
        assert denoms[0] == 1
        return [amount]
    # recursive case...
    else :
        # the value of the largest denomination coin
        val_largest_denom = denoms[-1]
        # the list of remaining denominations
        other_denoms = denoms[:-1]
        # the quantity of the largest denomination coin we're currently trying
        quant_largest_denom = 0
        # the best set of coins we've seen so far, initially 
        # the best way to make change without the largest denomination
        best_set = make_change_naive(amount, other_denoms) + [0]
        # the number of coins in the best set so far
        best_num_coins = sum(best_set)
        # Now, iterate through the other quantities for the largest denominations
        quant_largest_denom = 1
        # the amount to make change for after using the current quantity
        # of the largest denomination
        remaining_amount = amount - quant_largest_denom * val_largest_denom
        # as long as the remaining amount is non-negative
        while remaining_amount >= 0 :
            # the best way to make change with the current quantity of the largets denomination
            current_set = make_change_naive(remaining_amount, other_denoms) + [quant_largest_denom]
            # the number of coins in the current set
            current_num_coins = sum(current_set)
            # if that's better than the best so far, it's the new best so far
            if current_num_coins < best_num_coins :
                best_set = current_set
                best_num_coins = current_num_coins
            # increment the quantity of the largest denomination and 
            # compute the corresponding remaining amount
            quant_largest_denom += 1
            remaining_amount = amount - quant_largest_denom * val_largest_denom
        return best_set

        

def min_change(amount, denoms):
    # Table holding the minimum number of coins for each subproblem.
    # fewest_coins[i][j] is the smallest number of coins needed to make 
    #     amount i using denominations 0 through j
    fewest_coins = [[None for j in range(len(denoms))] for i in range(amount + 1)]
    # Initialize the left column to 0.
    # If the amount is 0, use 0 coins of any denomination
    for j in range(len(denoms)) :
        fewest_coins[0][j] = 0
    # Initialize the bottom row to i.
    # If we are using only the unit-valued denomination, then take i of them
    for i in range(amount + 1) :
        fewest_coins[i][0] = i
    # Populate the rest of the table
    for i in range(1,amount + 1) :  # For each sub-amount
        for j in range(1,len(denoms)) :  # For each denomination
            # Initially assume the best we can do is take 0 coins
            # of the current (jth) denomination
            best_coins = fewest_coins[i][j-1]
            # Consider every other plausible number of coins, k
            k = 1
            while k * denoms[j] <= i :
                coins = k + fewest_coins[i - k * denoms[j]][j-1]
                if coins <= best_coins :
                    best_coins = coins
                k += 1
            # Record the smallest number of coins and how many of
            # this denomination to get that number of coins
            fewest_coins[i][j] = best_coins
    return fewest_coins[amount][len(denoms)-1]


def make_change(amount, denoms):
    # Table holding the minimum number of coins for each subproblem.
    # fewest_coins[i][j] is the smallest number of coins needed to make 
    #     amount i using denominations 0 through j
    fewest_coins = [[None for j in range(len(denoms))] for i in range(amount + 1)]
    # Table holding the decisions to make the minimum number of coins.
    # coins_to_take[i][j] is the number of coins of denomination j used in
    #     the smallest number of coins
    coins_to_take = [[None for j in range(len(denoms))] for i in range(amount + 1)]
    # Initialize the left column to 0.
    # If the amount is 0, use 0 coins of any denomination
    for j in range(len(denoms)) :
        fewest_coins[0][j] = 0
        coins_to_take[0][j] = 0
    # Initialize the bottom row to i.
    # If we are using only the unit-valued denomination, then take i of them
    for i in range(amount + 1) :
        fewest_coins[i][0] = i
        coins_to_take[i][0] = i
    # Populate the rest of the table
    for i in range(1,amount + 1) :  # For each sub-amount
        for j in range(1,len(denoms)) :  # For each range of denominations
            # Initially assume the best we can do is take 0 coins
            # of the current (jth) denomination
            best_take = 0
            best_coins = fewest_coins[i][j-1]
            # Consider every other plausible number of coins, k
            k = 1
            while k * denoms[j] <= i :
                coins = k + fewest_coins[i - k * denoms[j]][j-1]
                if coins <= best_coins :
                    best_take = k
                    best_coins = coins
                k += 1
            # Record the smallest number of coins and how many of
            # this denomination to get that number of coins
            fewest_coins[i][j] = best_coins
            coins_to_take[i][j] = best_take
    # Reconstruct the solution
    result = [None for j in range(len(denoms))]
    amount_left = amount
    # Iterate through the denominations in reverse order
    for j in reversed(range(len(denoms))) :
        # How many coins of denomination j should we take?
        result[j] = coins_to_take[amount_left][j]
        # After that, what amount is left?
        amount_left -= denoms[j] * result[j]
    assert amount_left == 0
    for j in reversed(range(len(denoms))):
        print str(denoms[j]) + "&" + str(j) + "&" + "&".join([str(fewest_coins[i][j]) + "/" + str(coins_to_take[i][j]) for i in range(amount+1)])        
    return result


import unittest
american = [ 1, 5, 10, 25]
micomicona = [1, 12, 13]
wizarding = [1, 29, 493]

    

class TestMakeChange(unittest.TestCase):
    def check_change(self, denoms, change, amount, min_coins):
        assert len(denoms) == len(change)
        changed_amount = 0
        num_coins = 0
        for i in range(len(change)) :
            changed_amount += change[i] * denoms[i]
            num_coins += change[i]
        self.assertEqual(amount, changed_amount)
        self.assertEqual(min_coins, num_coins)
        
    def testAmericanSmall(self) :
        self.check_change(american, make_change(15, american), 15, 2)
        
    def testAmericanMedium(self) :
        self.check_change(american, make_change(74, american), 74, 8)
        self.check_change(american, make_change(76, american), 76, 4)
        
    def testAmericanLarge(self) :
        self.check_change(american, make_change(99, american), 99, 9)
        
    def testMicoMiconaSmall(self):
        self.check_change(micomicona, make_change(11, micomicona), 11, 11)
        self.check_change(micomicona, make_change(12, micomicona), 12, 1)
        self.check_change(micomicona, make_change(13, micomicona), 13, 1)
        self.check_change(micomicona, make_change(14, micomicona), 14, 2)
        
    def testMicoMiconaMed(self):
        self.check_change(micomicona, make_change(24, micomicona), 24, 2)
        self.check_change(micomicona, make_change(25, micomicona), 25, 2)
        
    def testMicoMiconaBig(self):
        self.check_change(micomicona, make_change(51, micomicona), 51, 4)

    def testWizardingBig(self):
        self.check_change(wizarding, make_change(1000, wizarding), 1000, 16)
       