
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class WaypointResponse {

    @Expose
    private Waypoint data;

    public Waypoint getData() {
        return data;
    }

    public void setData(Waypoint data) {
        this.data = data;
    }

}
