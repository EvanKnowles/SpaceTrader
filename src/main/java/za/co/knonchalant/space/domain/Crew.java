
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Crew {

    @Expose
    private Long capacity;
    @Expose
    private Long current;
    @Expose
    private Long morale;
    @Expose
    private Long required;
    @Expose
    private String rotation;
    @Expose
    private Long wages;

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getMorale() {
        return morale;
    }

    public void setMorale(Long morale) {
        this.morale = morale;
    }

    public Long getRequired() {
        return required;
    }

    public void setRequired(Long required) {
        this.required = required;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public Long getWages() {
        return wages;
    }

    public void setWages(Long wages) {
        this.wages = wages;
    }

}
