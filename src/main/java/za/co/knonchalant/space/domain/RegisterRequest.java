package za.co.knonchalant.space;

public class RegisterRequest {
    String symbol;
    String faction;

    public RegisterRequest(String symbol, String faction) {
        this.symbol = symbol;
        this.faction = faction;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getFaction() {
        return faction;
    }
}
