
package za.co.knonchalant.space.domain;

import com.google.gson.annotations.Expose;

import java.util.List;

@SuppressWarnings("unused")
public class WaypointsResponse {

    @Expose
    private List<Waypoint> data;

    public List<Waypoint> getData() {
        return data;
    }

    public void setData(List<Waypoint> data) {
        this.data = data;
    }

}
