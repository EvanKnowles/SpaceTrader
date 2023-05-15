package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.Spacer;
import za.co.knonchalant.space.domain.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Purchaser implements IRole {
    public static final EShipType TARGET_SHIP = EShipType.SHIP_MINING_DRONE;
    private Waypoint waypoint = null;
    long purchasePrice;
    private Date resumeAfter;

    @Override
    public void perform(Spacer spacer, Ship ship) throws IOException {
        if (ship.getNav().getStatus().equals("IN_TRANSIT")) {
            return;
        }

        if (purchasePrice > spacer.getAgentDetails().getCredits()) {
            return;
        }

        if (waypoint == null) {
            waypoint = spacer.waypoint(ship.getNav());
        }

        if (!EWaypointTrait.SHIPYARD.has(waypoint)) {
            List<Waypoint> waypoints = EWaypointTrait.SHIPYARD.get(spacer.waypoints(ship.getNav()));
            if (!waypoints.isEmpty()) {
                waypoint = waypoints.get(0);
                spacer.navigate(ship, waypoint);
                return;
            }
        }

        Shipyard shipyard = spacer.shipyard(waypoint);
        AvailableShip availableShip = TARGET_SHIP.get(shipyard);
        if (availableShip != null && availableShip.getPurchasePrice() < spacer.getAgentDetails().getCredits()) {
            log(ship, "Purchasing: " + TARGET_SHIP);
            spacer.purchaseShip(TARGET_SHIP, shipyard);
        } else if (availableShip != null) {
            resumeAfter = cooldown(10);
            purchasePrice = availableShip.getPurchasePrice();

        }
    }

    @Override
    public Date getResumeAfter() {
        return resumeAfter;
    }
}
