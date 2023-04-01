package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoBeansException;
import ee.taltech.iti0202.coffee.exceptions.TankEmptyException;
import ee.taltech.iti0202.coffee.exceptions.TrashIsFullException;
import ee.taltech.iti0202.coffee.tank.WaterTank;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Machine {
    protected static final Logger logger = Logger.getLogger(Machine.class.getName());
    protected Integer wasteCapacity;
    protected int currentWaste;

    protected int beans;

    protected WaterTank waterTank;

    public Machine(Integer wasteCapacity, WaterTank waterTank, int beans) throws IOException {
        if (wasteCapacity == null) {
            this.wasteCapacity = 5;
        }
        else {
            this.wasteCapacity = wasteCapacity;
        }
        this.waterTank = waterTank;
        this.currentWaste = 0;
        this.beans = beans;
        FileHandler fileName = new FileHandler("logs.txt");
        logger.addHandler(fileName);
    }

    public boolean machineHaveWater() {
        return waterTank.isWaterInTank();
    }
    public boolean machineHaveBeans() {
        return beans > 0;
    }

    public boolean trashisFull() {
        return currentWaste >= wasteCapacity;
    }

    public Drink.DrinkType makeDrink(Drink drink) throws TrashIsFullException, TankEmptyException, NoBeansException {
        if (!machineHaveBeans()) {
            throw new NoBeansException("No beans!");
        }

        if (trashisFull()) {
            throw new TrashIsFullException("Trash is full!");
        }
        if (!machineHaveWater()) {
            throw new TankEmptyException("No water in tank!");
        }

        else if (machineHaveBeans() && machineHaveWater() && !trashisFull()) {
            currentWaste++;
            beans--;
            waterTank.takeWater();
            logger.info("Drink has been made.");
            return drink.getDrinkType();
        }
        else {
            logger.info("Cannot make a drink!");
            return null;
        }


    }

    public static class MachineBuilder {
        private int wasteCapacity;
        private WaterTank waterTank;

        private int beans;

        public MachineBuilder setWasteCapacity(int wasteCapacity) {
            this.wasteCapacity = wasteCapacity;
            return this;
        }

        public MachineBuilder setWaterTank(WaterTank waterTank) {
            this.waterTank = waterTank;
            return this;
        }

        public MachineBuilder setBeans(int beans) {
            this.beans = beans;
            return this;
        }

        public Machine createMachine() throws IOException {
            return new Machine(wasteCapacity, waterTank, beans);
        }
    }
}
