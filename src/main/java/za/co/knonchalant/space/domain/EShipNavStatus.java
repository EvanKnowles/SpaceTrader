package za.co.knonchalant.space.domain;

public enum EShipNavStatus {

    IN_TRANSIT,
    IN_ORBIT,
            DOCKED;
    public boolean is(String type) {
        return this.name().equals(type);
    }

    public boolean is(Ship ship) {
        return is(ship.getNav().getStatus());
    }
}
