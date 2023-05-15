
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Frame {

    @Expose
    private Long condition;
    @Expose
    private String description;
    @Expose
    private Long fuelCapacity;
    @Expose
    private Long moduleSlots;
    @Expose
    private Long mountingPoints;
    @Expose
    private String name;
    @Expose
    private Requirements requirements;
    @Expose
    private String symbol;

    public Long getCondition() {
        return condition;
    }

    public void setCondition(Long condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(Long fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public Long getModuleSlots() {
        return moduleSlots;
    }

    public void setModuleSlots(Long moduleSlots) {
        this.moduleSlots = moduleSlots;
    }

    public Long getMountingPoints() {
        return mountingPoints;
    }

    public void setMountingPoints(Long mountingPoints) {
        this.mountingPoints = mountingPoints;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
