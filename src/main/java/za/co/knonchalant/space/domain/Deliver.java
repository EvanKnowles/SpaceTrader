
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Deliver {

    @Expose
    private String destinationSymbol;
    @Expose
    private String tradeSymbol;
    @Expose
    private Long unitsFulfilled;
    @Expose
    private Long unitsRequired;

    public String getDestinationSymbol() {
        return destinationSymbol;
    }

    public void setDestinationSymbol(String destinationSymbol) {
        this.destinationSymbol = destinationSymbol;
    }

    public String getTradeSymbol() {
        return tradeSymbol;
    }

    public void setTradeSymbol(String tradeSymbol) {
        this.tradeSymbol = tradeSymbol;
    }

    public Long getUnitsFulfilled() {
        return unitsFulfilled;
    }

    public void setUnitsFulfilled(Long unitsFulfilled) {
        this.unitsFulfilled = unitsFulfilled;
    }

    public Long getUnitsRequired() {
        return unitsRequired;
    }

    public void setUnitsRequired(Long unitsRequired) {
        this.unitsRequired = unitsRequired;
    }

    @Override
    public String toString() {
        return "Deliver{" +
                "destinationSymbol='" + destinationSymbol + '\'' +
                ", tradeSymbol='" + tradeSymbol + '\'' +
                ", unitsFulfilled=" + unitsFulfilled +
                ", unitsRequired=" + unitsRequired +
                '}';
    }
}
