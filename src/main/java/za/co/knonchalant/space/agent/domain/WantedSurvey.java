package za.co.knonchalant.space.agent.domain;

import za.co.knonchalant.space.domain.ETradeSymbol;

import java.util.Objects;

public class WantedSurvey {
    private final String waypointSymbol;
    private final ETradeSymbol wanted;

    public WantedSurvey(String waypointSymbol, ETradeSymbol wanted) {
        this.waypointSymbol = waypointSymbol;
        this.wanted = wanted;
    }

    public String getWaypointSymbol() {
        return waypointSymbol;
    }

    public ETradeSymbol getWanted() {
        return wanted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WantedSurvey that = (WantedSurvey) o;
        return Objects.equals(waypointSymbol, that.waypointSymbol) && wanted == that.wanted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(waypointSymbol, wanted);
    }

    @Override
    public String toString() {
        return waypointSymbol + ": " + wanted;
    }
}
