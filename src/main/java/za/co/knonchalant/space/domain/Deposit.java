
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Deposit {

    @Expose
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
