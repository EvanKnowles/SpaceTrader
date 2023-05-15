
package za.co.knonchalant.space.domain;

import java.util.List;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Mount {

    @Expose
    private List<String> deposits;
    @Expose
    private String description;
    @Expose
    private String name;
    @Expose
    private Requirements requirements;
    @Expose
    private Long strength;
    @Expose
    private String symbol;

    public List<String> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<String> deposits) {
        this.deposits = deposits;
    }

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

    public Requirements getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }

    public Long getStrength() {
        return strength;
    }

    public void setStrength(Long strength) {
        this.strength = strength;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
