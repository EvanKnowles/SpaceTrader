
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Nav {

    @Expose
    private String flightMode;
    @Expose
    private Route route;
    @Expose
    private String status;
    @Expose
    private String systemSymbol;
    @Expose
    private String waypointSymbol;

    public String getFlightMode() {
        return flightMode;
    }

    public void setFlightMode(String flightMode) {
        this.flightMode = flightMode;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSystemSymbol() {
        return systemSymbol;
    }

    public void setSystemSymbol(String systemSymbol) {
        this.systemSymbol = systemSymbol;
    }

    public String getWaypointSymbol() {
        return waypointSymbol;
    }

    public void setWaypointSymbol(String waypointSymbol) {
        this.waypointSymbol = waypointSymbol;
    }

    @Override
    public String toString() {
        return "Nav{" +
                "flightMode='" + flightMode + '\'' +
                ", route=" + route +
                ", status='" + status + '\'' +
                ", systemSymbol='" + systemSymbol + '\'' +
                ", waypointSymbol='" + waypointSymbol + '\'' +
                '}';
    }
}
