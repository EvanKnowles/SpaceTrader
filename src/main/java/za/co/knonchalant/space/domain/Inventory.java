
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Inventory {

    @Expose
    private String description;
    @Expose
    private String name;
    @Expose
    private String symbol;
    @Expose
    private Long units;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return units + " " + name;
    }
}
