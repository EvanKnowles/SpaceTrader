package za.co.knonchalant.space.domain;

import java.util.ArrayList;
import java.util.List;

public enum EWaypointType {

    PLANET,
    GAS_GIANT,
    MOON,
    ORBITAL_STATION,
    JUMP_GATE,
    ASTEROID_FIELD,
    NEBULA,
    DEBRIS_FIELD,
    GRAVITY_WELL;

    public boolean is(String type) {
        return this.name().equals(type);
    }

    public boolean is(Waypoint waypoint) {
        return this.name().equals(waypoint.getType());
    }

    public List<Waypoint> get(List<Waypoint> waypointList) {
        ArrayList<Waypoint> waypoints = new ArrayList<>();
        for (Waypoint waypoint : waypointList) {
            if (is(waypoint)) {
                waypoints.add(waypoint);
            }
        }
        return waypoints;
    }
}
