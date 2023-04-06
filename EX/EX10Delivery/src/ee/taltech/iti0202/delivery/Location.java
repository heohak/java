package ee.taltech.iti0202.delivery;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class Location {
    private String name;
    private Map<String, Packet> packets;
    private Map<String, Integer> distances;

    Location(String name) {
        this.name = name;
        this.packets = new HashMap<>();
        this.distances = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Integer getDistanceTo(String name) {
        return distances.getOrDefault(name, Integer.MAX_VALUE);
    }

    public void addPacket(Packet packet) {
        packets.put(packet.getName(), packet);
    }

    public Optional<Packet> getPacket(String name) {
        Packet packet = packets.remove(name);
        return packet != null ? Optional.of(packet) : Optional.empty();
    }

    public void addDistance(String location, int distance) {
        distances.put(location, distance);
    }

    public Map<String, Packet> getPackets() {
        return packets;
    }

    public void setPackets(Map<String, Packet> packets) {
        this.packets = packets;
    }
}
