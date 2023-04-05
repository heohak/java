package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class Location {
    private String name;
    private Map<String, Integer> distances = new HashMap<>();
    private Map<String, Packet> packets = new HashMap<>();

    Location(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    Integer getDistanceTo(String name) {
        if (distances.containsKey(name)) {
            return distances.get(name);
        }
        return Integer.MAX_VALUE;
    }

    void addPacket(Packet packet) {
        packets.put(packet.getName(), packet);
    }

    Optional<Packet> getPacket(String name) {
        Packet packet = packets.remove(name);
        return packet == null ? Optional.empty() : Optional.of(packet);
    }

    public void addDistance(String location, int distance) {
        distances.put(location, distance);
    }
}