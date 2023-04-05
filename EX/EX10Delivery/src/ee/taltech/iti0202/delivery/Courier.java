package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class Courier {
    private String name;
    private Location currentLocation;
    private Map<String, Packet> carriedPackets = new HashMap<>();
    private Strategy strategy;

    Courier(String name, Location location) {
        this.name = name;
        this.currentLocation = location;
    }

    Optional<Location> getLocation() {
        return Optional.ofNullable(currentLocation);
    }

    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    Strategy getStrategy() {
        return strategy;
    }

    void tick(Map<String, Location> locations) {
        // Implement tick logic based on the requirements
    }
}