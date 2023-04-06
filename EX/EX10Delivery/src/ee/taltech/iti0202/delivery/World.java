package ee.taltech.iti0202.delivery;

import java.util.*;

class World {
    private Map<String, Location> locations;
    private Map<String, Courier> couriers;

    public World() {
        locations = new HashMap<>();
        couriers = new HashMap<>();
    }

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (locations.containsKey(name) || otherLocations.size() != distances.size()) {
            return Optional.empty();
        }
        if (otherLocations.size() > locations.size()) {
            return Optional.empty();
        }

        Location newLocation = new Location(name);
        locations.put(name, newLocation);

        for (int i = 0; i < otherLocations.size(); i++) {
            String otherLocationName = otherLocations.get(i);
            int distance = distances.get(i);
            Location otherLocation = locations.get(otherLocationName);

            if (otherLocation == null) {
                locations.remove(name);
                return Optional.empty();
            }

            newLocation.addDistance(otherLocationName, distance);
            otherLocation.addDistance(name, distance);
        }

        return Optional.of(newLocation);
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
