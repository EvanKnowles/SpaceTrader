
package za.co.knonchalant.space.domain;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Shipyard {

    @Expose
    private List<ShipType> shipTypes;
    @Expose
    private String symbol;

    public List<ShipType> getShipTypes() {
        return shipTypes;
    }

    public void setShipTypes(List<ShipType> shipTypes) {
        this.shipTypes = shipTypes;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Shipyard{" +
                "shipTypes=" + shipTypes +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
