package capitalGains;

import static org.junit.Assert.*;

import org.junit.Test;

import static capitalGains.Transaction.buy;
import static capitalGains.Transaction.sell;

public class CapitalGainsTest {

    @Test
    public void testTrivial() {
        StockTracker tracker = new StockTracker();
        tracker.processTransaction(buy(1000, 42));
        assertEquals(1000, tracker.numShares());
        assertEquals(42000, tracker.totalBasis());
        assertEquals(0, tracker.capitalGains());
       
    }
    
    @Test
    public void testA() {
        StockTracker tracker = new StockTracker();
        tracker.processTransaction(buy(1000, 42));
        tracker.processTransaction(buy(139, 49));
        tracker.processTransaction(buy(799, 44));
        tracker.processTransaction(sell(1352, 39));
        assertEquals(586, tracker.numShares());
        assertEquals(25784, tracker.totalBasis());
        assertEquals(-5455, tracker.capitalGains());
    }

    @Test
    public void testB() {
        StockTracker tracker = new StockTracker();
        tracker.processTransaction(buy(1000, 52));
        tracker.processTransaction(buy(530, 40));
        tracker.processTransaction(sell(1277, 35));
        assertEquals(253, tracker.numShares());
        assertEquals(10120, tracker.totalBasis());
        assertEquals(-18385, tracker.capitalGains());
    }

    @Test
    public void testC() {
        StockTracker tracker = new StockTracker();
        tracker.processTransaction(buy(1000, 49));
        tracker.processTransaction(buy(100, 43));
        tracker.processTransaction(sell(339, 23));
        tracker.processTransaction(sell(450, 26));
        tracker.processTransaction(buy(100, 19));
        assertEquals(411, tracker.numShares());
        assertEquals(16539, tracker.totalBasis());
        assertEquals(-19164, tracker.capitalGains());
    }

    
    @Test
    public void testD() {
        StockTracker tracker = new StockTracker();
        tracker.processTransaction(buy(1000, 50));
        tracker.processTransaction(sell(743, 65));
        tracker.processTransaction(sell(100, 93));
        tracker.processTransaction(sell(100, 79));
        assertEquals(57, tracker.numShares());
        assertEquals(2850, tracker.totalBasis());
        assertEquals(18345, tracker.capitalGains());
    }

    @Test
    public void stressTest() {
        Transactor.reset();
        StockTracker tracker = new StockTracker();
        int numShares = 0; //current.shares;
        Transaction current = null; //Transactor.nextTransaction();
        do {

            current = Transactor.nextTransaction();
            if (current.type) numShares += current.shares;
            else numShares -= current.shares;
            
            tracker.processTransaction(current);
        } while(numShares > 0);
        assertEquals(Transactor.capitalGain(), tracker.capitalGains());
    }
    
}
