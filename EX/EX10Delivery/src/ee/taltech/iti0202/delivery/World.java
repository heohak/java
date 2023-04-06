package ee.taltech.iti0202.delivery;

import java.util.*;

public class World {
    private HashMap<String, Location> locationMap = new HashMap<>();
    private HashMap<String, Courier> courierMap = new HashMap<>();
    private List<String> locations = new ArrayList<>();

    public World() {
    }

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        HashSet<String> otherLocation = new HashSet<>(otherLocations);
        HashSet<String> locationsSet = new HashSet<>(locations);
        if (locationMap.containsKey(name)
                || otherLocations.size() != distances.size()
                || locations.size() > otherLocations.size()
                || !otherLocation.containsAll(locationsSet)) {
            return Optional.empty();
        } else {
            Location location = new Location(name);
            for (int i = 0; i < otherLocations.size(); i++) {
                if (locationsSet.contains(otherLocations.get(i))) {
                    location.addDistance(otherLocations.get(i), distances.get(i));
                    locationMap.get(otherLocations.get(i)).addDistance(name, distances.get(i));
                }
            }
            locationMap.put(name, location);
            locations.add(name);
            return Optional.of(location);
        }
    }

    public Optional<Courier> addCourier(String name, String to) {
        if (courierMap.containsKey(name) || !locationMap.containsKey(to)) {
            return Optional.empty();
        }

        Courier courier = new Courier(name, locationMap.get(to));
        courierMap.put(name, courier);
        return Optional.of(courier);
    }

    public boolean giveStrategy(String name, Strategy strategy) {
        Courier courier = courierMap.get(name);
        if (courier == null) {
            return false;
        }

        courier.setStrategy(strategy);
        return true;
    }

    public void tick() {
        for (Courier courier : courierMap.values()) {
            if (courier.isMoving()) {
                courier.moveOneUnit();
            } else {
                courier.executeAction();
            }
        }
    }
}