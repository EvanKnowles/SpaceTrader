package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.Spacer;
import za.co.knonchalant.space.domain.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Purchaser implements IRole {
    private Waypoint waypoint = null;
    long purchasePrice;
    private Date resumeAfter;
    private boolean newYard;

    @Override
    public void perform(Spacer spacer, Ship ship) throws IOException {
        if (ship.inTransit()) {
            return;
        }

        if (purchasePrice > spacer.getAgentDetails().getCredits()) {
            new Surveyer().perform(spacer, ship);
            return;
        }

        if (waypoint == null) {
            waypoint = spacer.waypoint(ship.getNav());
        }

        if (newYard || !EWaypointTrait.SHIPYARD.has(waypoint)) {
            List<Waypoint> waypoints = EWaypointTrait.SHIPYARD.get(spacer.waypoints(ship.getNav()));
            if (!waypoints.isEmpty()) {
                newYard = false;
                waypoint = waypoints.get(new Random().nextInt(waypoints.size()));
                if (!ship.isAt(waypoint)) {
                    spacer.navigate(ship, waypoint);
                    return;
                }
            }
        }

        Shipyard shipyard = spacer.shipyard(waypoint);
        EShipType targetShip = EShipType.SHIP_MINING_DRONE;
        if (ShipManager.getShips().size() > 1) {
            targetShip = EShipType.SHIP_ORE_HOUND;
        }

        AvailableShip availableShip = targetShip.get(shipyard);
        if (availableShip != null && availableShip.getPurchasePrice() < spacer.getAgentDetails().getCredits()) {
            log(ship, "Purchasing: " + targetShip);
            spacer.purchaseShip(targetShip, shipyard);
        } else if (availableShip != null) {
            purchasePrice = availableShip.getPurchasePrice();
        } else if (availableShip == null) {
            newYard = true;
        }
    }

    @Override
    public Date getResumeAfter() {
        return resumeAfter;
    }
}
