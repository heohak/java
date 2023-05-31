package ee.taltech.iti0202.exam.transit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hub {

    List<Bus> buses = new ArrayList<>();

    List<Bus> movingBuses = new ArrayList<>();

    Map<String, BusStop> busStops = new HashMap<>();
    /**
     * Returns all the buses in the hub in insertion order.
     */
    public List<Bus> getBuses() {
        return buses;
    }

    /**
     * Returns all the moving buses in the hub in insertion order.
     */
    public List<Bus> getBusesOnTheMove() {
        return movingBuses;
    }

    /**
     * Returns a map of bus stops where the key is the name of the stop
     * and the value is the BusStop object.
     */
    public Map<String, BusStop> getBusStops() {
        return busStops;
    }

    /**
     * Add bus to the hub.
     * @param bus
     */
    public void addBus(Bus bus) {
        if (!buses.contains(bus)) {
            buses.add(bus);
        }
    }
    /**
     * Add bus stop to the hub.
     * @param busStop
     */
    public void addBusStop(BusStop busStop) {
        if (!busStops.containsKey(busStop.getName())) {
            busStops.put(busStop.getName(), busStop);
        }
    }
    /**
     * Start the route of the bus.
     * If the bus cannot move to the starting BusStop, then
     * bus does not start moving.
     *
     * See also passTime()
     * @param bus
     */
    public void startRoute(Bus bus) {
        for (BusStop busStop : bus.getStops()) {
            if (!bus.isMoving() && busStop.getBusLines().contains(bus.getLineNumber())) {
                bus.setCurrentStop(bus.getBusStops().get(0));
                movingBuses.add(bus);
                bus.setMoving(true);
            }
        }
    }
    /**
     * Stop the route of the bus.
     * @param bus
     */
    public void stopRoute(Bus bus) {
        if (bus.isMoving()) {
            bus.setMoving(false);
            movingBuses.remove(bus);
        }
    }

    /**
     * Pass time. Move all buses to the next stop.
     * If the bus is at the last stop, then move it to the first stop, update the location.
     * If there is a bus with the same line number in the stop already, then the bus is not moved.
     * Example:
     * bus with line number 11 is in stop "A" and wants to move to the next stop "B",
     * but there is already one bus with line no 11 in stop "B",
     * then the bus remains in the current stop "A".
     *
     * Buses move in the order they are added to the hub.
     *
     * Example:
     * Bus1, line no 11, stops: A, B
     * Bus2, line no 11, stops: A, B
     *
     * after startRoute(bus1):
     * Bus1 in stop A
     * Bus2 is nowhere
     *
     * after startRoute(bus2):
     * Bus1 in stop A
     * Bus2 is nowhere (cannot move to stop A, because other bus with line number 11 is already there)
     *
     * passTime()
     * Bus1 in stop B
     * Bus2 is nowhere (hasn't started)
     *
     * startRoute(bus2)
     * Bus1 in stop B
     * Bus2 in stop A
     *
     * passTime()
     * Bus1 in stop B (because cannot move to stop A)
     * Bus2 in stop A (because cannot move to stop B)
     *
     */
    public void passTime() {
    }

    /**
     * Get the list of buses in the hub.
     * The key is a bus stop name and the value is a list of buses in that stop.
     * If there is a bus stop which doesn't contain any buses, then
     * those stops can be left out from the map or they can point to an empty list.
     */
    public Map<String, List<Bus>> getLocations() {
            Map<String, List<Bus>> result = new HashMap<>();
            return null;
    }


}

