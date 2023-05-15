
package za.co.knonchalant.space.domain;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Survey {

    @Expose
    private List<Deposit> deposits;
    @Expose
    private Date expiration;
    @Expose
    private String signature;
    @Expose
    private String size;
    @Expose
    private String symbol;

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean contains(ETradeSymbol tradeSymbol) {
        for (Deposit deposit : getDeposits()) {
            if (tradeSymbol.is(deposit.getSymbol())) {
                return true;
            }
        }
        return false;
    }
}
