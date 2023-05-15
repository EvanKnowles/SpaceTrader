
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;

import java.util.Objects;


@SuppressWarnings("unused")
public class Contract {

    @Expose
    private Boolean accepted;
    @Expose
    private String expiration;
    @Expose
    private String factionSymbol;
    @Expose
    private Boolean fulfilled;
    @Expose
    private String id;
    @Expose
    private Terms terms;
    @Expose
    private String type;

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getFactionSymbol() {
        return factionSymbol;
    }

    public void setFactionSymbol(String factionSymbol) {
        this.factionSymbol = factionSymbol;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Terms getTerms() {
        return terms;
    }

    public void setTerms(Terms terms) {
        this.terms = terms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "accepted=" + accepted +
                ", expiration='" + expiration + '\'' +
                ", factionSymbol='" + factionSymbol + '\'' +
                ", fulfilled=" + fulfilled +
                ", id='" + id + '\'' +
                ", terms=" + terms +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(id, contract.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
