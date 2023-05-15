
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Requirements {

    @Expose
    private Long crew;
    @Expose
    private Long power;
    @Expose
    private Long slots;

    public Long getCrew() {
        return crew;
    }

    public void setCrew(Long crew) {
        this.crew = crew;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Long getSlots() {
        return slots;
    }

    public void setSlots(Long slots) {
        this.slots = slots;
    }

}
