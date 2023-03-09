package capitalGains;


/**
 * StockTracker
 * 
 * Class to model a financial security of which an investor
 * buys and sells several lots. This accounts for capital
 * gains using the FIFO method.
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */
public class StockTracker {

     // Add instance variables
    
    /**
     * Process a given transaction
     * @param trans The transaction to process
     */
    public void processTransaction(Transaction trans) {
         throw new UnsupportedOperationException();
    }

    /**
     * Get the number of shares currently held (all lots)
     * @return The total number of shares held
     */
    public int numShares() {
         throw new UnsupportedOperationException();
    }

    /**
     * Get the capital gains of all shares sold so far.
     * @return The current capital gains on sales of this stock
     */
    public int capitalGains() {
         throw new UnsupportedOperationException();
    }
    
}
