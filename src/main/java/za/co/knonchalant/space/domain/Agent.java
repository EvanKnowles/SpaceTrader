
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Agent {

    @Expose
    private String accountId;
    @Expose
    private Long credits;
    @Expose
    private String headquarters;
    @Expose
    private String symbol;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "accountId='" + accountId + '\'' +
                ", credits=" + credits +
                ", headquarters='" + headquarters + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
