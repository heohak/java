package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class Courier {
    private String name;
    private Location currentLocation;
    private Location destination;
    private int remainingDistance;
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

    public String getName() {
        return name;
    }

    void tick(Map<String, Location> locations) {
        if (destination == null) {
            if (strategy != null) {
                Action action = strategy.getAction();
                if (action != null) {
                    destination = action.getGoTo();
                    remainingDistance = currentLocation.getDistanceTo(destination.getName());

                    // Deposit packages
                    for (String packetName : action.getDeposit()) {
                        Packet packet = carriedPackets.remove(packetName);
                        if (packet != null) {
                            currentLocation.addPacket(packet);
                        }
                    }

                    // Take packages
                    for (String packetName : action.getTake()) {
                        Optional<Packet> packetOpt = currentLocation.getPacket(packetName);
                        if (packetOpt.isPresent()) {
                            carriedPackets.put(packetName, packetOpt.get());
                        }
                    }
                }
            }
        } else {
            remainingDistance--;
            if (remainingDistance == 0) {
                currentLocation = destination;
                destination = null;
            } else {
                currentLocation = null;
            }
        }
    }
}