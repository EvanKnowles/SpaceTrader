
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

import java.util.Date;

@SuppressWarnings("unused")
public class Cooldown {

    @Expose
    private Date expiration;
    @Expose
    private Long remainingSeconds;
    @Expose
    private String shipSymbol;
    @Expose
    private Long totalSeconds;

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Long getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(Long remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    public String getShipSymbol() {
        return shipSymbol;
    }

    public void setShipSymbol(String shipSymbol) {
        this.shipSymbol = shipSymbol;
    }

    public Long getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(Long totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

}
