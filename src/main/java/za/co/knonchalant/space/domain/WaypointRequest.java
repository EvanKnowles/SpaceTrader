
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class WaypointRequest {

    public WaypointRequest(String waypointSymbol) {
        this.waypointSymbol = waypointSymbol;
    }

    @Expose
    private String waypointSymbol;

    public String getWaypointSymbol() {
        return waypointSymbol;
    }

    public void setWaypointSymbol(String waypointSymbol) {
        this.waypointSymbol = waypointSymbol;
    }

}
