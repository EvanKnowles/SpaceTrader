package za.co.knonchalant.space.domain;

import java.util.ArrayList;
import java.util.List;

public enum EWaypointTrait {
    UNCHARTED, MARKETPLACE, SHIPYARD, OUTPOST, SCATTERED_SETTLEMENTS, SPRAWLING_CITIES, MEGA_STRUCTURES, OVERCROWDED, HIGH_TECH, CORRUPT, BUREAUCRATIC, TRADING_HUB, INDUSTRIAL, BLACK_MARKET, RESEARCH_FACILITY, MILITARY_BASE, SURVEILLANCE_OUTPOST, EXPLORATION_OUTPOST, MINERAL_DEPOSITS, COMMON_METAL_DEPOSITS, PRECIOUS_METAL_DEPOSITS, RARE_METAL_DEPOSITS, METHANE_POOLS, ICE_CRYSTALS, EXPLOSIVE_GASES, STRONG_MAGNETOSPHERE, VIBRANT_AURORAS, SALT_FLATS, CANYONS, PERPETUAL_DAYLIGHT, PERPETUAL_OVERCAST, DRY_SEABEDS, MAGMA_SEAS, SUPERVOLCANOES, ASH_CLOUDS, VAST_RUINS, MUTATED_FLORA, TERRAFORMED, EXTREME_TEMPERATURES, EXTREME_PRESSURE, DIVERSE_LIFE, SCARCE_LIFE, FOSSILS, WEAK_GRAVITY, STRONG_GRAVITY, CRUSHING_GRAVITY, TOXIC_ATMOSPHERE, CORROSIVE_ATMOSPHERE, BREATHABLE_ATMOSPHERE, JOVIAN, ROCKY, VOLCANIC, FROZEN, SWAMP, BARREN, TEMPERATE, JUNGLE, OCEAN, STRIPPED;

    public boolean is(String type) {
        return this.name().equals(type);
    }

    public List<Waypoint> get(List<Waypoint> waypointList) {
        ArrayList<Waypoint> waypoints = new ArrayList<>();
        for (Waypoint waypoint : waypointList) {
            if (has(waypoint)) {
                waypoints.add(waypoint);
            }
        }
        return waypoints;
    }

    public boolean has(Waypoint waypoint) {
        for (Trait trait : waypoint.getTraits()) {
            if (this.is(trait.getSymbol())) {
                return true;
            }
        }
        return false;
    }
}
