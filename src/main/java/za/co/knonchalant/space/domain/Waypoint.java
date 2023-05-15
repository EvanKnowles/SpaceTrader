
package za.co.knonchalant.space.domain;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Waypoint {

    @Expose
    private Chart chart;
    @Expose
    private Faction faction;
    @Expose
    private List<Orbital> orbitals;
    @Expose
    private String symbol;
    @Expose
    private String systemSymbol;
    @Expose
    private List<Trait> traits;
    @Expose
    private String type;
    @Expose
    private Long x;
    @Expose
    private Long y;

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public List<Orbital> getOrbitals() {
        return orbitals;
    }

    public void setOrbitals(List<Orbital> orbitals) {
        this.orbitals = orbitals;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSystemSymbol() {
        return systemSymbol;
    }

    public void setSystemSymbol(String systemSymbol) {
        this.systemSymbol = systemSymbol;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "chart=" + chart +
                ", faction=" + faction +
                ", orbitals=" + orbitals +
                ", symbol='" + symbol + '\'' +
                ", systemSymbol='" + systemSymbol + '\'' +
                ", traits=" + traits +
                ", type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
