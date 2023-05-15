package za.co.knonchalant.space.domain;

public enum EShipType {
    SHIP_PROBE,
    SHIP_MINING_DRONE,
    SHIP_INTERCEPTOR,
    SHIP_LIGHT_HAULER,
    SHIP_COMMAND_FRIGATE,
    SHIP_EXPLORER,
    SHIP_HEAVY_FREIGHTER,
    SHIP_LIGHT_SHUTTLE,
    SHIP_ORE_HOUND,
    SHIP_REFINING_FREIGHTER;

    public boolean is(String type) {
        return this.name().equals(type);
    }

    public boolean in(Shipyard shipyard) {
        for (ShipType shipType : shipyard.getShipTypes()) {
            if (is(shipType.getType())) {
                return true;
            }
        }
        return false;
    }

    public AvailableShip get(Shipyard shipyard) {
        for (AvailableShip availableShip : shipyard.getShips()) {
            if (is(availableShip.getType())) {
                return availableShip;
            }
        }
        return null;
    }
}
