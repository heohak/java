package ee.taltech.iti0202.delivery;


import java.util.*;

class Courier {
    private String name;
    private Optional<Location> currentLocation;
    private Strategy strategy;
    private Map<String, Packet> carriedPackets;
    private Location destination;
    private int remainingDistance;

    public Courier(String name, Location location) {
        this.name = name;
        this.currentLocation = Optional.of(location);
        this.carriedPackets = new HashMap<>();
        this.destination = null;
        this.remainingDistance = 0;
    }

    public Optional<Location> getLocation() {
        return currentLocation;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public String getName() {
        return name;
    }

    public boolean isMoving() {
        return destination != null;
    }

    public void moveOneUnit() {
        if (remainingDistance > 0) {
            remainingDistance--;
            if (remainingDistance == 0) {
                currentLocation = Optional.of(destination);
                destination = null;
            }
        }
    }

    public void executeAction() {
        Action action = strategy.getAction();

        // Deposit packets
        for (String packetName : action.getDeposit()) {
            Packet packet = carriedPackets.remove(packetName);
            if (packet != null && currentLocation.isPresent()) {
                currentLocation.get().addPacket(packet);
            }
        }

        // Take packets
        for (String packetName : action.getTake()) {
            Optional<Packet> packet = currentLocation.isPresent() ? currentLocation.get().getPacket(packetName) : Optional.empty();
            if (packet.isPresent()) {
                carriedPackets.put(packetName, packet.get());
            }
        }

        // Move to destination
        Location goToLocation = action.getGoTo();
        if (currentLocation.isPresent()) {
            remainingDistance = currentLocation.get().getDistanceTo(goToLocation.getName());
            currentLocation = Optional.empty();
            destination = goToLocation;
            moveOneUnit(); // Move the first unit immediately
        }
    }
}