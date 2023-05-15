
package za.co.knonchalant.space.domain;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Transaction {

    @Expose
    private Long pricePerUnit;
    @Expose
    private String shipSymbol;
    @Expose
    private String timestamp;
    @Expose
    private Long totalPrice;
    @Expose
    private String tradeSymbol;
    @Expose
    private String type;
    @Expose
    private Long units;
    @Expose
    private String waypointSymbol;

    public Long getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Long pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getShipSymbol() {
        return shipSymbol;
    }

    public void setShipSymbol(String shipSymbol) {
        this.shipSymbol = shipSymbol;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTradeSymbol() {
        return tradeSymbol;
    }

    public void setTradeSymbol(String tradeSymbol) {
        this.tradeSymbol = tradeSymbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    public String getWaypointSymbol() {
        return waypointSymbol;
    }

    public void setWaypointSymbol(String waypointSymbol) {
        this.waypointSymbol = waypointSymbol;
    }

}
