package za.co.knonchalant.space;

import za.co.knonchalant.space.agent.domain.Settings;
import za.co.knonchalant.space.domain.*;

import java.io.IOException;
import java.lang.System;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * This is an API walkthrough of the <a href="https://docs.spacetraders.io/quickstart/new-game">QuickStart</a>
 */
public class Tutorial {
    static String settingsLocation = "C:\\dev\\projects\\SpaceTrader\\settings-test.json";

    public static void main(String[] args) throws IOException {
        Spacer api;
        if (!Files.exists(Path.of(settingsLocation))) {
            // register as an agent if we haven't before
            api = Spacer.register("Viat-Test", "COSMIC");

            // persist our token locally
            Settings settings = new Settings();
            settings.setToken(api.getToken());
            settings.persist(settingsLocation);
        } else {
            // so we can re-run this class
            Settings settings = Settings.get(settingsLocation);
            api = Spacer.token(settings.getToken());
        }

        // view your agent details again
        Agent agent = api.agent();
        System.out.println(agent);

        // view starting location, you can get this by splitting the name of your
        // headquarters, but it's easier API-wise (if not rate limit-wise) to
        // just check where your ship is
        List<Ship> ships = api.ships();
        Ship commandShip = ships.get(0);
        Waypoint waypoint = api.waypoint(commandShip.getNav());
        System.out.println(waypoint);

        // need to accept the default contract with the contract ID, so we need
        // to see our contracts
        List<Contract> contracts = api.contracts();
        if (contracts.size() == 1) {
            Contract firstContract = contracts.get(0);
            if (!firstContract.getAccepted()) {
                contracts.set(0, api.accept(firstContract));
            }

            // re-retrieving a contract (because the tutorial does that, our object is actually up-to-date)
            Contract contract = api.contract(firstContract.getId());
            System.out.println(contract.getTerms());
        }

        // find a waypoint with the shipyard trait
        List<Waypoint> allWaypoints = api.waypoints(commandShip.getNav().getSystemSymbol());
        List<Waypoint> shipyardWaypoints = EWaypointTrait.SHIPYARD.get(allWaypoints);

        if (!shipyardWaypoints.isEmpty()) {
            Waypoint shipyardWaypoint = random(shipyardWaypoints);

            // view available ships
            Shipyard shipyard = api.shipyard(shipyardWaypoint);

            // find a mining drone
            boolean droneAvailable = EShipType.SHIP_MINING_DRONE.in(shipyard);
            if (droneAvailable) {
                // purchase a ship
                Ship miningDrone = api.purchaseShip(EShipType.SHIP_MINING_DRONE, shipyard);
                System.out.println("Bought a ship: " + miningDrone.getSymbol() + ", status is: " + miningDrone.getNav().getStatus());

                // fly to asteroid field
                List<Waypoint> asteroidWaypoints = EWaypointType.ASTEROID_FIELD.get(allWaypoints);
                if (!asteroidWaypoints.isEmpty()) {
                    Waypoint asteroidWaypoint = random(asteroidWaypoints);
                    api.navigate(miningDrone, asteroidWaypoint);
                    System.out.println("Navigated " + miningDrone.getSymbol() + " to " + asteroidWaypoint.getSymbol() + ", status is: " + miningDrone.getNav().getStatus());

                    waitForFlightToBeOver(miningDrone);

                    // dock the ship
                    api.dock(miningDrone);

                    // refuel
                    api.refuel(miningDrone);

                    // back into orbit to mine
                    api.orbit(miningDrone);

                    // extract
                    ExtractResponse extract = api.extract(miningDrone);
                    waitForCoolDown(extract.getCooldown());

                    // view market data
                    Market market = api.market(miningDrone.getNav());
                    System.out.println(market);

                    // view ship cargo per tutorial, although our ship object is up-to-date
                    // and you can actually pull just the cargo instead of the whole ship
                    Ship ship = api.ship(miningDrone.getSymbol());
                    System.out.println(ship.getCargo());

                    // sell the stuff we got except our contract target
                    api.dock(miningDrone);
                    Contract contract = contracts.get(0);
                    Deliver deliver = contract.getTerms().getDeliver().get(0);
                    ETradeSymbol contractTradeSymbol = ETradeSymbol.valueOf(deliver.getTradeSymbol());

                    List<Inventory> sellableCargo = ship.getCargo().filter(contractTradeSymbol);
                    for (Inventory cargo : sellableCargo) {
                        System.out.println("Selling: " + cargo);
                        api.sell(miningDrone, cargo);
                    }

                    // next, mine enough of the contract's trade symbol, then deliver it and fulfill the contract
                    // if that's possible.
                }
            }
        }

    }

    private static void waitForCoolDown(Cooldown cooldown) {
        while (cooldown.getExpiration().before(new Date())) {
            dodgyWait();
        }
    }

    /**
     * Use the ship's navigation data to work out when the ship has arrived, instead of querying
     * over and over again
     *
     * @param ship the ship
     */
    private static void waitForFlightToBeOver(Ship ship) {
        while (ship.inTransit()) {
            dodgyWait();
        }
    }

    private static void dodgyWait() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Waypoint random(List<Waypoint> waypoints) {
        return waypoints.get(new Random().nextInt(waypoints.size()));
    }
}
