
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Registration {

    @Expose
    private String factionSymbol;
    @Expose
    private String name;
    @Expose
    private String role;

    public String getFactionSymbol() {
        return factionSymbol;
    }

    public void setFactionSymbol(String factionSymbol) {
        this.factionSymbol = factionSymbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "factionSymbol='" + factionSymbol + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
