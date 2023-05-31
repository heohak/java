package ee.taltech.iti0202.exam.transit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bus {

    private String licencePlate;

    private String lineNumber;

    private List<BusStop> busStops = new ArrayList<>();

    private BusStop currentStop;

    private boolean isMoving;

    /**
     *
     * @param licencePlate
     * @param lineNumber
     */
    public Bus(String licencePlate, String lineNumber) {
        this.licencePlate = licencePlate;
        this.lineNumber = lineNumber;
    }

    public String getLicensePlate() {
        return licencePlate;
    }

    public void setLicensePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    /**
     * If bus is moving then return the current stop.
     * Returns Optional.empty() if bus is not moving.
     */
    public Optional<BusStop> getCurrentStop() {
        if (isMoving) {
            return Optional.of(currentStop);
        } else {
            return Optional.empty();
        }
    }

    public void setCurrentStop(BusStop newStop) {
        this.currentStop = newStop;
    }

    public String getLineNumber() {
        return this.lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public List<BusStop> getStops() {
        return busStops;
    }

    public void setStops(List<BusStop> stops) {
        this.busStops = stops;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }
}


