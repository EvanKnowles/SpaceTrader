package za.co.knonchalant.space.domain;

public enum EShipRole {
    FABRICATOR,
    HARVESTER,
    HAULER,
    INTERCEPTOR,
    EXCAVATOR,
    TRANSPORT,
    REPAIR,
    SURVEYOR,
    COMMAND,
    CARRIER,
    PATROL,
    SATELLITE,
    EXPLORER,
    REFINERY;

    public boolean is(String type) {
        return this.name().equals(type);
    }

    public boolean is(Ship ship) {
        return is(ship.getRegistration().getRole());
    }
}
