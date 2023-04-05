package ee.taltech.iti0202.delivery;

import java.util.*;

class World {
    private List<String> locations = new ArrayList<>();

    private Map<String, Location> locationsMap = new HashMap<>();
    private Map<String, Courier> couriersMap = new HashMap<>();

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        Set<String> otherLocationSet = new HashSet<>(otherLocations);
        Set<String> existingLocationsSet = new HashSet<>(locations);

        if (locationsMap.containsKey(name) || otherLocations.size() != distances.size()
                || locations.size() > otherLocations.size() || !existingLocationsSet.containsAll(otherLocationSet)) {
            return Optional.empty();
        } else {
            Location location = new Location(name);
            for (int i = 0; i < otherLocations.size(); i++) {
                String otherLocationName = otherLocations.get(i);
                int distance = distances.get(i);
                if (existingLocationsSet.contains(otherLocationName)) {
                    location.addDistance(otherLocationName, distance);
                    locationsMap.get(otherLocationName).addDistance(name, distance);
                }
            }
            locationsMap.put(name, location);
            locations.add(name);
            return Optional.of(location);
        }
    }

    Optional<Courier> addCourier(String name, String to) {
        if (couriersMap.containsKey(name) || !locationsMap.containsKey(to)) {
            return Optional.empty();
        }

        Location location = locationsMap.get(to);
        Courier courier = new Courier(name, location);
        couriersMap.put(name, courier);
        return Optional.of(courier);
    }

    boolean giveStrategy(String name, Strategy strategy) {
        Courier courier = couriersMap.get(name);

        if (courier == null) {
            return false;
        }

        courier.setStrategy(strategy);
        return true;
    }

    void tick() {
        for (Courier courier : couriersMap.values()) {
            courier.tick(locationsMap);
        }
    }
}