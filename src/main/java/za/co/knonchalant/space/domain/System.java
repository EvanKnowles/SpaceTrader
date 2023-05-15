package za.co.knonchalant.space.domain;

import java.util.ArrayList;
import java.util.List;

public class System {
    private String symbol;
    private String sectorSymbol;
    private String type;
    private int x;
    private int y;
    private List<SystemWaypoint> waypoints;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSectorSymbol() {
        return sectorSymbol;
    }

    public void setSectorSymbol(String sectorSymbol) {
        this.sectorSymbol = sectorSymbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<SystemWaypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<SystemWaypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public List<SystemWaypoint> waypoints(EWaypointType waypointType) {
        ArrayList<SystemWaypoint> waypointArrayList = new ArrayList<>();
        for (SystemWaypoint waypoint : waypoints) {
            if (waypointType.is(waypoint.getType())) {
                waypointArrayList.add(waypoint);
            }
        }
        return waypointArrayList;
    }
}

