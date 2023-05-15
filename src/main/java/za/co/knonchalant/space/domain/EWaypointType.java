package za.co.knonchalant.space.domain;

public enum EWaypointType {

    PLANET,
    GAS_GIANT,
    MOON,
    ORBITAL_STATION,
    JUMP_GATE,
    ASTEROID_FIELD,
    NEBULA,
    DEBRIS_FIELD,
    GRAVITY_WELL;

    public boolean is(String type) {
        return this.name().equals(type);
    }

}
