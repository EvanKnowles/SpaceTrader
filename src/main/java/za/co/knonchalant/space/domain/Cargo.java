
package za.co.knonchalant.space.domain;

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
        for (Inventory inventory1 : inventory) {
            if (target.is(inventory1)){
                return inventory1;
            }
        }
        return null;
    }
}
