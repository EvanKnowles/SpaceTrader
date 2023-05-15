
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Engine {

    @Expose
    private Long condition;
    @Expose
    private String description;
    @Expose
    private String name;
    @Expose
    private Requirements requirements;
    @Expose
    private Long speed;
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

    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
