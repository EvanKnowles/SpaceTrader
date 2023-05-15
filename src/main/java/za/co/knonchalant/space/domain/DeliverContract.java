
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class DeliverContract {

    @Expose
    private String shipSymbol;
    @Expose
    private String tradeSymbol;
    @Expose
    private Long units;

    public DeliverContract(String tradeSymbol, String shipSymbol, long units) {
        this.tradeSymbol = tradeSymbol;
        this.shipSymbol = shipSymbol;
        this.units = units;
    }

    public String getShipSymbol() {
        return shipSymbol;
    }

    public void setShipSymbol(String shipSymbol) {
        this.shipSymbol = shipSymbol;
    }

    public String getTradeSymbol() {
        return tradeSymbol;
    }

    public void setTradeSymbol(String tradeSymbol) {
        this.tradeSymbol = tradeSymbol;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

}
