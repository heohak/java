package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.TankEmptyException;
import ee.taltech.iti0202.coffee.exceptions.TrashIsFullException;
import ee.taltech.iti0202.coffee.machine.AutomaticMachine;
import ee.taltech.iti0202.coffee.tank.WaterTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AutomaticMachineTest {

    WaterTank watertank;

    AutomaticMachine autoMachine;
    Drink drink;

    @BeforeEach
    void setup() throws IOException {
        drink = new Drink(Drink.DrinkType.CACAO);
        watertank = new WaterTank(6);
        autoMachine = new AutomaticMachine.AutomaticMachineBuilder()
                .setWaterTank(watertank)
                .setWasteCapacity(10)
                .createAutomaticMachine();
    }

    @Test
    void testIfAutomaticMachineHaveWater() throws TankEmptyException, TrashIsFullException {
        assertTrue(autoMachine.machineHaveWater());
        for (int i = 0; i < 9; i++) {
            try {
                autoMachine.makeDrink(drink);
            } catch (TankEmptyException tankEmptyException) {
                assertEquals("No water in tank!",tankEmptyException.getResult());

            }
        }
        assertFalse(autoMachine.machineHaveWater());
    }

    @Test
    void testIfTrashIsFullAutomaticMachine() throws TankEmptyException, TrashIsFullException {
        assertFalse(autoMachine.trashisFull());
        for (int i = 0; i < 15; i++) {
            try {
                autoMachine.makeDrink(drink);
            } catch (TankEmptyException tankEmptyException) {
                assertEquals("No water in tank!",tankEmptyException.getResult());

            }
        }
        assertFalse(autoMachine.trashisFull());
    }

}
