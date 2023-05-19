
package za.co.knonchalant.space.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Cargo {

    @Expose
    private Long capacity;
    @Expose
    private List<Inventory> inventory;
    @Expose
    private Long units;

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    public Inventory find(ETradeSymbol target) {
        if (target == null) {
            return null;
        }

        for (Inventory inventory1 : inventory) {
            if (target.is(inventory1)) {
                return inventory1;
            }
        }
        return null;
    }

    public List<Inventory> filter(ETradeSymbol... removeSymbols) {
        ArrayList<Inventory> filteredInventory = new ArrayList<>();
        for (Inventory inventory1 : inventory) {
            boolean keep = true;
            for (ETradeSymbol removeSymbol : removeSymbols) {
                if (removeSymbol != null && removeSymbol.is(inventory1.getSymbol())) {
                    keep = false;
                    break;
                }
            }
            if (keep) {
                filteredInventory.add(inventory1);
            }
        }
        return filteredInventory;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "capacity=" + capacity +
                ", inventory=" + inventory +
                ", units=" + units +
                '}';
    }
}
