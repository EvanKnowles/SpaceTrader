package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.domain.EShipType;
import za.co.knonchalant.space.domain.Ship;

import java.util.List;

public class ShipManager {
    private static List<Ship> ships;

    public static List<Ship> getShips() {
        return ships;
    }

    public static void setShips(List<Ship> ships) {
        ShipManager.ships = ships;
    }

}
