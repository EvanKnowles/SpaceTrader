package za.co.knonchalant.space.domain;

public class MarketPurchaseRequest {
    private String symbol;
    private long units;

    public MarketPurchaseRequest(String symbol, long units) {
        this.symbol = symbol;
        this.units = units;
    }

    public String getSymbol() {
        return symbol;
    }

    public long getUnits() {
        return units;
    }
}
