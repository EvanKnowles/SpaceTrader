package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.Spacer;
import za.co.knonchalant.space.client.exception.RestClientException;
import za.co.knonchalant.space.domain.*;

import java.io.IOException;
import java.lang.System;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Miner implements IRole {
    private Date resumeAfter;
    private Waypoint waypoint;

    @Override
    public void perform(Spacer spacer, Ship ship) throws IOException {
        if (ship.inTransit()) {
            return;
        }

        Contract contract = spacer.contracts().get(0);
        ETradeSymbol target = null;
        long amount = -1;
        for (Deliver deliver : contract.getTerms().getDeliver()) {
            target = ETradeSymbol.valueOf(deliver.getTradeSymbol());
            amount = deliver.getUnitsRequired() - deliver.getUnitsFulfilled();
        }

        Inventory targetInventory = ship.getCargo().find(target);
        if (targetInventory != null && targetInventory.getUnits().equals(ship.getCargo().getUnits())) {
            log(ship, "Need to deliver contract");
            Deliver deliver = contract.getTerms().getDeliver().get(0);
            String destinationSymbol = deliver.getDestinationSymbol();
            if (!ship.getNav().getWaypointSymbol().equals(destinationSymbol)) {
                spacer.navigate(ship, destinationSymbol);
                return;
            } else {
                spacer.dock(ship);
                spacer.deliver(contract.getId(), ship, target.name(), targetInventory.getUnits());
                System.out.println("Delivered " + targetInventory.getUnits());
                spacer.refuel(ship);

                spacer.orbit(ship);
                return;
            }
        }

        if (waypoint == null || !Objects.equals(waypoint.getSymbol(), ship.getNav().getWaypointSymbol())) {
            waypoint = spacer.waypoint(ship.getNav());
        }

        if (!EWaypointType.ASTEROID_FIELD.is(waypoint)) {
            List<Waypoint> waypoints = EWaypointType.ASTEROID_FIELD.get(spacer.waypoints(ship.getNav()));
            if (!waypoints.isEmpty()) {
                if (!ship.getNav().getStatus().equals("DOCKED")) {
                    spacer.dock(ship);
                }
                spacer.refuel(ship);
                spacer.navigate(ship, waypoints.get(0));
                return;
            }
        }

        if (amount > 0) {
            if (ship.getCargo().getCapacity() - ship.getCargo().getUnits() == 0) {
                log(ship, "Full, going to sell");
                spacer.dock(ship);

                for (Inventory inventory : ship.getCargo().filter(target, ETradeSymbol.ANTIMATTER)) {
                    log(ship, "Selling: " + inventory);
                    spacer.sell(ship, inventory);
                }
            }

            if (EShipNavStatus.IN_ORBIT.is(ship)) {
                try {
                    List<Survey> surveys = SurveyManager.get(ship.getNav().getWaypointSymbol(), target);
                    Survey survey = null;
                    if (!surveys.isEmpty()) {
                        survey = surveys.get(0);
                    }

                    if (survey == null) {
                        survey = SurveyManager.get(ship.getNav().getWaypointSymbol());
                    }

                    ExtractResponse extract = spacer.extract(ship, survey);

                    Yield yield = extract.getExtraction().getYield();
                    log(ship, "Extracted " + yield.getSymbol() + " " + yield.getUnits() + (survey != null ? " (surveyed)" : ""));
                    resumeAfter = cooldown(extract.getCooldown().getRemainingSeconds());
                } catch (RestClientException ex) {
                    ErrorResponse from = ErrorResponse.from(ex.getMessage());
                    if (from.getError().getData().getCooldown() != null) {
                        resumeAfter = from.getError().getData().getCooldown().getExpiration();
                    }else {
                        resumeAfter = cooldown(5);
                    }
                }

            } else if (EShipNavStatus.DOCKED.is(ship)) {
                spacer.orbit(ship);
            }
        }
    }

    @Override
    public Date getResumeAfter() {
        return resumeAfter;
    }
}
