package ee.taltech.iti0202.delivery;

import java.util.*;

public class World {
    private Map<String, Location> locations;
    private Map<String, Courier> couriers;

    public World() {
        locations = new HashMap<>();
        couriers = new HashMap<>();
    }

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        HashSet<String> otherLocationSet = new HashSet<>(otherLocations);
        HashSet<String> locationsSet = new HashSet<>(locations.keySet());

        if (locations.containsKey(name)
                || otherLocations.size() != distances.size()
                || locations.size() > otherLocations.size()
                || !otherLocationSet.containsAll(locationsSet)) {
            return Optional.empty();
        } else {
            Location location = new Location(name);
            for (int i = 0; i < otherLocations.size(); i++) {
                if (locationsSet.contains(otherLocations.get(i))) {
                    location.addDistance(otherLocations.get(i), distances.get(i));
                    locations.get(otherLocations.get(i)).addDistance(name, distances.get(i));
                }
            }
            locations.put(name, location);
            return Optional.of(location);
        }
    }

    public Optional<Courier> addCourier(String name, String to) {
        if (couriers.containsKey(name) || !locations.containsKey(to)) {
            return Optional.empty();
        }

        Courier courier = new Courier(name, locations.get(to));
        couriers.put(name, courier);
        return Optional.of(courier);
    }

    public boolean giveStrategy(String name, Strategy strategy) {
        Courier courier = couriers.get(name);
        if (courier == null) {
            return false;
        }

        courier.setStrategy(strategy);
        return true;
    }

    public void tick() {
        for (Courier courier : couriers.values()) {
            if (courier.isMoving()) {
                courier.moveOneUnit();
            } else {
                courier.executeAction();
            }
        }
    }
}