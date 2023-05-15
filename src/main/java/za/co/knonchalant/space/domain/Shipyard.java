
package za.co.knonchalant.space.domain;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Shipyard {

    @Expose
    private List<ShipType> shipTypes;
    @Expose
    private List<AvailableShip> ships;
    @Expose
    private String symbol;
    @Expose
    private List<Transaction> transactions;

    public List<ShipType> getShipTypes() {
        return shipTypes;
    }

    public void setShipTypes(List<ShipType> shipTypes) {
        this.shipTypes = shipTypes;
    }

    public List<AvailableShip> getShips() {
        return ships;
    }

    public void setShips(List<AvailableShip> ships) {
        this.ships = ships;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
