
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Payment {

    @Expose
    private Long onAccepted;
    @Expose
    private Long onFulfilled;

    public Long getOnAccepted() {
        return onAccepted;
    }

    public void setOnAccepted(Long onAccepted) {
        this.onAccepted = onAccepted;
    }

    public Long getOnFulfilled() {
        return onFulfilled;
    }

    public void setOnFulfilled(Long onFulfilled) {
        this.onFulfilled = onFulfilled;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "onAccepted=" + onAccepted +
                ", onFulfilled=" + onFulfilled +
                '}';
    }
}
