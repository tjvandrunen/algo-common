package capitalGains;

public class Transaction {

    public final boolean type;   // true - purchase; false = sale
    public final int shares;
    public final int price;

    public Transaction(boolean type, int shares, int price) {
        this.type = type;
        this.shares = shares;
        this.price = price;
    }

    public static Transaction buy(int shares, int price) {
        return new Transaction(true, shares, price);
    }
    
    public static Transaction sell(int shares, int price) {
        return new Transaction(false, shares, price);
    }
}
