package ee.taltech.iti0202.delivery;

import java.util.*;

class World {
    private Map<String, Location> locations = new HashMap<>();
    private Map<String, Courier> couriers = new HashMap<>();

    Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (distances == null) {
            return Optional.empty();
        }
        if (locations.containsKey(name) || otherLocations.size() > locations.size() || otherLocations.size() != distances.size()) {
            return Optional.empty();
        }

        Location newLocation = new Location(name);

        for (int i = 0; i < otherLocations.size(); i++) {
            String otherLocationName = otherLocations.get(i);
            int distance = distances.get(i);
            Location otherLocation = locations.get(otherLocationName);

            if (otherLocation == null) {
                return Optional.empty();
            }

            newLocation.addDistance(otherLocationName, distance);
            otherLocation.addDistance(name, distance);
        }

        locations.put(name, newLocation);
        return Optional.of(newLocation);
    }

    Optional<Courier> addCourier(String name, String to) {
        if (couriers.containsKey(name) || !locations.containsKey(to)) {
            return Optional.empty();
        }

        Courier newCourier = new Courier(name, locations.get(to));
        couriers.put(name, newCourier);
        return Optional.of(newCourier);
    }

    boolean giveStrategy(String name, Strategy strategy) {
        Courier courier = couriers.get(name);
        if (courier == null) {
            return false;
        }
        courier.setStrategy(strategy);
        return true;
    }

    void tick() {
        for (Courier courier : couriers.values()) {
            courier.tick(locations);
        }
    }
}