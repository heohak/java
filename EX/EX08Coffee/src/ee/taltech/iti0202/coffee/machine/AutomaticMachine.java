package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.TankEmptyException;
import ee.taltech.iti0202.coffee.exceptions.TrashIsFullException;
import ee.taltech.iti0202.coffee.tank.WaterTank;

import java.io.IOException;

public class AutomaticMachine extends Machine {
    public AutomaticMachine(int wasteCapacity, WaterTank waterTank, int beans) throws IOException {
        super(wasteCapacity, waterTank, beans);
    }


    @Override
    public Drink.DrinkType makeDrink(Drink drink) throws TankEmptyException, TrashIsFullException {
        if (trashisFull()) {
            throw new TrashIsFullException("Trash is full!");
        }
        if (!machineHaveWater()) {
            throw new TankEmptyException("No water in tank!");
        }
        else if (machineHaveWater() && !trashisFull()) {
            currentWaste++;
            waterTank.takeWater();
            logger.info("Drink has been made.");
            return drink.getDrinkType();
        }
        else {
            logger.info("Cannot make a drink!");
            return null;
        }
    }

    public static class AutomaticMachineBuilder {
        private int wasteCapacity;
        private WaterTank waterTank;
        private int beans;

        public AutomaticMachineBuilder setWasteCapacity(int wasteCapacity) {
            this.wasteCapacity = wasteCapacity;
            return this;
        }

        public AutomaticMachineBuilder setWaterTank(WaterTank waterTank) {
            this.waterTank = waterTank;
            return this;
        }

        public AutomaticMachineBuilder setBeans(int beans) {
            this.beans = beans;
            return this;
        }

        public AutomaticMachine createAutomaticMachine() throws IOException {
            return new AutomaticMachine(wasteCapacity, waterTank, beans);
        }
    }
}
