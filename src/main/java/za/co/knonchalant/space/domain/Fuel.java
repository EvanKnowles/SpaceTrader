
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Fuel {

    @Expose
    private Long capacity;
    @Expose
    private Consumed consumed;
    @Expose
    private Long current;

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Consumed getConsumed() {
        return consumed;
    }

    public void setConsumed(Consumed consumed) {
        this.consumed = consumed;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

}
