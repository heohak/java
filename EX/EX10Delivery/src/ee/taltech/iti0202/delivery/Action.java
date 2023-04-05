package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

class Action {
    private Location location;
    private List<String> deposit = new ArrayList<>();
    private List<String> take = new ArrayList<>();

    public Action(Location location) {
        this.location = location;
    }

    List<String> getDeposit() {
        return deposit;
    }

    List<String> getTake() {
        return take;
    }

    Location getGoTo() {
        return location;
    }

    void addDeposit(String packetName) {
        deposit.add(packetName);
    }

    void addTake(String packetName) {
        take.add(packetName);
    }
}