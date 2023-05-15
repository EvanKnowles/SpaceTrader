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
}
