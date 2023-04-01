package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.TankEmptyException;
import ee.taltech.iti0202.coffee.exceptions.TrashIsFullException;
import ee.taltech.iti0202.coffee.tank.WaterTank;

import java.io.IOException;

public class CapsuleMachine extends Machine {

    private boolean isCapsuleIn = true;

    private boolean isCapsuleEmpty = false;
    public CapsuleMachine(int wasteCapacity, WaterTank waterTank, int beans) throws IOException {
        super(10, waterTank, beans);
    }
    public boolean machineCapsuleIn() {
        return isCapsuleIn;
    }

    public boolean capsuleIsEmpty() {
        return isCapsuleEmpty;
    }

    public void changeCapsule() {

        isCapsuleIn = true;
        isCapsuleEmpty = false;
    }

    @Override
    public Drink.DrinkType makeDrink(Drink capsule) throws TankEmptyException, TrashIsFullException {
        if (!machineHaveWater()) {
            throw new TankEmptyException("No water in tank!");
        }
        if (trashisFull()) {
            throw new TrashIsFullException("Trash is full!");
        }
        else if (!machineCapsuleIn() || (machineCapsuleIn() && capsuleIsEmpty())) {
            logger.info("Water comes out.");
            return null;
        } else if (machineHaveWater() && !trashisFull()) {
            isCapsuleIn = true;
            currentWaste++;
            waterTank.takeWater();
            isCapsuleEmpty = true;
            logger.info("Drink has been made.");
            return capsule.getDrinkType();
        }
        else {
            logger.info("Cannot make a drink!");
            return null;
        }

    }


    public static class CapsuleMachineBuilder {
        private int wasteCapacity;
        private WaterTank waterTank;
        private int beans;

        public CapsuleMachineBuilder setWasteCapacity(int wasteCapacity) {
            this.wasteCapacity = wasteCapacity;
            return this;
        }

        public CapsuleMachineBuilder setWaterTank(WaterTank waterTank) {
            this.waterTank = waterTank;
            return this;
        }

        public CapsuleMachineBuilder setBeans(int beans) {
            this.beans = beans;
            return this;
        }

        public CapsuleMachine createCapsuleMachine() throws IOException {
            return new CapsuleMachine(wasteCapacity, waterTank, beans);
        }
    }
}
