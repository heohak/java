package ee.taltech.iti0202.delivery;

class Packet {
    private String name;
    private Location target;

    Packet(String name, Location target) {
        this.name = name;
        this.target = target;
    }

    String getName() {
        return name;
    }

    Location getTarget() {
        return target;
    }
}