package za.co.knonchalant.space.domain;

public class PurchaseRequest {
    private final String shipType;
    private final String waypointSymbol;

    public PurchaseRequest(String shipType, String waypointSymbol) {
        this.shipType = shipType;
        this.waypointSymbol = waypointSymbol;
    }

    public String getShipType() {
        return shipType;
    }

    public String getWaypointSymbol() {
        return waypointSymbol;
    }
}
