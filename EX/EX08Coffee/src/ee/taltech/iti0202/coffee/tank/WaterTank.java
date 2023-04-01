package ee.taltech.iti0202.coffee.tank;


import java.util.logging.Logger;

public class WaterTank {
    private static final Logger logger = Logger.getLogger(WaterTank.class.getName());

    private int capacity;
    private int currentWater;

    public WaterTank(int capacity) {
        this.capacity = capacity;
        this.currentWater = capacity;
    }

    public boolean isWaterInTank() {
        return currentWater > 0;
    }

    public  void takeWater() {
        currentWater--;
        logger.info("Water is taken from the water tank.");

    }

    public void reFillTank() {
        currentWater = capacity;
        logger.info("Water tank has been refilled.");
    }
}
