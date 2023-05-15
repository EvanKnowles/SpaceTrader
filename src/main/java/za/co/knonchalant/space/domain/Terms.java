
package za.co.knonchalant.space.domain;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Terms {

    @Expose
    private String deadline;
    @Expose
    private List<Deliver> deliver;
    @Expose
    private Payment payment;

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public List<Deliver> getDeliver() {
        return deliver;
    }

    public void setDeliver(List<Deliver> deliver) {
        this.deliver = deliver;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Terms{" +
                "deadline='" + deadline + '\'' +
                ", deliver=" + deliver +
                ", payment=" + payment +
                '}';
    }
}
