package za.co.knonchalant.space.agent.domain;

import java.util.Date;

public class Transaction {
    private final Date time;
    private String details;
    private long creditChange;
    int id;

    public Transaction(String details, long creditChange, int id) {
        this.details = details;
        this.creditChange = creditChange;
        this.id = id;
        this.time = new Date();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public long getCreditChange() {
        return creditChange;
    }

    public void setCreditChange(long creditChange) {
        this.creditChange = creditChange;
    }

    public Date getTime() {
        return time;
    }
}
