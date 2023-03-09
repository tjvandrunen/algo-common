package capitalGains;


import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class Transactor {

    private static int numShares = 0;
    private static int numTransactions = 0;
    private static int previousPrice = 50;

    private static Random randy = new Random();

    private static Queue<Integer> sharePrice = new LinkedList<Integer>();
    private static int capitalGain = 0;

    public static void reset() {
        numShares = 0;
        numTransactions = 0;
        previousPrice = 50;
        sharePrice = new LinkedList<Integer>();
        capitalGain = 0;
    }
    
    public static Transaction nextTransaction() {
        numTransactions++;


        int price;
        do {
            price = previousPrice + 
                (int) ((randy.nextGaussian()/5) * previousPrice);
        } while (price < 0);

        if (numShares == 0) {
            numShares += 1000;
            previousPrice = price;
            for (int i = 0; i < 1000; i++)
                sharePrice.offer(price);
            return new Transaction(true, 1000, price);
        }
        
        if (numTransactions > 25 && Math.random() > .75) {
            Transaction toReturn = new Transaction(false, numShares, price);
            numShares = 0;
            previousPrice = price;
              for (int i = 0; i < toReturn.shares; i++)
                  capitalGain += toReturn.price - sharePrice.poll();
            return toReturn;
        }

        boolean type;
        if (Math.random() > .6)
            type = false;
        else
            type = true;
        int shares = (int) (Math.random() * numShares);
        if (shares < 100) shares = 100;
        if (!type && shares > numShares) shares = numShares;
        if (type)
            numShares += shares;
        else
            numShares -= shares;
        previousPrice= price;

        if (type)
             for (int i = 0; i < shares; i++)
                sharePrice.offer(price);
        else
             for (int i = 0; i < shares; i++)
                  capitalGain += price - sharePrice.poll();

        return new Transaction(type, shares, price);
    }

    public static int capitalGain() { return capitalGain; }

    public static void main(String[] args) {
        do {
            Transaction next = nextTransaction();
            System.out.println(next.type + " " + next.shares + " " + next.price + 
                               "(" + numShares + ")");
            
        } while (numShares != 0);

    }
    
}



