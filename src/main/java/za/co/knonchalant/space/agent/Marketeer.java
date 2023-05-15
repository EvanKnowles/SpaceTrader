package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.Spacer;
import za.co.knonchalant.space.domain.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Marketeer implements IRole {
    private Waypoint waypoint;
    private boolean failedLast = false;
    private final Miner backupMining = new Miner();
    private final Surveyer backupSurveyer = new Surveyer();

    @Override
    public void perform(Spacer spacer, Ship ship) throws IOException {
        if (spacer.getAgentDetails().getCredits() < 1) {
            if (ContractsManager.hasContracts()) {
                backupSurveyer.perform(spacer, ship);
            }
            backupMining.perform(spacer, ship);
            return;
        }

        if (ship.inTransit()) {
            return;
        }

        if (waypoint == null || !Objects.equals(waypoint.getSymbol(), ship.getNav().getWaypointSymbol())) {
            waypoint = spacer.waypoint(ship.getNav());
        }


        Market market = spacer.market(ship.getNav());
        Contract contract = ContractsManager.getContracts().get(0);
        Deliver deliver = contract.getTerms().getDeliver().get(0);
        if (deliver.getUnitsRequired() == 0) {
            return;
        }

        ETradeSymbol deliverTradeSymbol = ETradeSymbol.valueOf(deliver.getTradeSymbol());

        List<Inventory> extraInventory = ship.getCargo().filter(ETradeSymbol.ANTIMATTER, deliverTradeSymbol);
        if (!extraInventory.isEmpty()) {
            log(ship, "Selling extra goods:");
            spacer.dock(ship);
            spacer.refuel(ship);

            for (Inventory inventory : extraInventory) {
                log(ship, "Selling: " + inventory);
                spacer.sell(ship, inventory);
            }
        }

        Inventory inventory = ship.getCargo().find(deliverTradeSymbol);
        if (inventory != null) {
            if (!ship.getNav().getWaypointSymbol().equals(deliver.getDestinationSymbol())) {
                spacer.orbit(ship);
                spacer.navigate(ship, deliver.getDestinationSymbol());
            } else {
                log(ship, "Delivering " + inventory);
                spacer.dock(ship);
                spacer.deliver(contract.getId(), ship, deliver.getTradeSymbol(), inventory.getUnits());
                spacer.refuel(ship);

                spacer.orbit(ship);
            }
        } else {
            if (!EWaypointTrait.MARKETPLACE.has(waypoint) || failedLast) {
                List<Waypoint> waypoints = EWaypointTrait.MARKETPLACE.get(spacer.waypoints(ship.getNav()));
                if (!waypoints.isEmpty()) {
                    Waypoint newWaypoint = waypoints.get(new Random().nextInt(waypoints.size()));
                    while (waypoints.size() > 1 && newWaypoint.getSymbol().equals(ship.getNav().getWaypointSymbol())) {
                        newWaypoint = waypoints.get(new Random().nextInt(waypoints.size()));
                    }
                    if (!newWaypoint.getSymbol().equals(ship.getNav().getWaypointSymbol())) {
                        spacer.dock(ship);
                        spacer.refuel(ship);
                        spacer.navigate(ship, newWaypoint);
                        if (failedLast) {
                            log(ship, "Trying a new marketplace...");
                            failedLast = false;
                        }
                    }
                    return;
                }
            }

            spacer.dock(ship);
            failedLast = true;
            if (market.getTradeGoods() != null) {
                for (TradeGood tradeGood : market.getTradeGoods()) {
                    if (tradeGood.getSymbol().equals(deliver.getTradeSymbol())) {
                        Long availableCredits = spacer.getAgentDetails().getCredits();
                        Long purchasePrice = tradeGood.getPurchasePrice();
                        long neededUnits = deliver.getUnitsRequired() - deliver.getUnitsFulfilled();
                        long units = Math.min(Math.min(availableCredits / purchasePrice, neededUnits), ship.getCargo().getCapacity() - ship.getCargo().getUnits());
                        log(ship, "Purchasing " + units + " of " + deliver.getTradeSymbol() + " for " + (units * purchasePrice));
                        spacer.dock(ship);
                        spacer.purchase(ship, deliver.getTradeSymbol(), units);
                        failedLast = false;
                    }
                }
            }

            inventory = ship.getCargo().find(deliverTradeSymbol);
            if (inventory != null) {
                spacer.orbit(ship);
                spacer.navigate(ship, deliver.getDestinationSymbol());
            }
        }
    }

    @Override
    public Date getResumeAfter() {
        return new Date();
    }
}
