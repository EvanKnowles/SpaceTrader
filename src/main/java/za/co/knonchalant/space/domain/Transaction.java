
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Transaction {

    @Expose
    private String agentSymbol;
    @Expose
    private Long price;
    @Expose
    private String shipSymbol;
    @Expose
    private String timestamp;
    @Expose
    private String waypointSymbol;

    public String getAgentSymbol() {
        return agentSymbol;
    }

    public void setAgentSymbol(String agentSymbol) {
        this.agentSymbol = agentSymbol;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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

    public String getWaypointSymbol() {
        return waypointSymbol;
    }

    public void setWaypointSymbol(String waypointSymbol) {
        this.waypointSymbol = waypointSymbol;
    }

}
