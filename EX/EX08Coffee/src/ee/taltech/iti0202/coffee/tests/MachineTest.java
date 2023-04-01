package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoBeansException;
import ee.taltech.iti0202.coffee.exceptions.TankEmptyException;
import ee.taltech.iti0202.coffee.exceptions.TrashIsFullException;
import ee.taltech.iti0202.coffee.machine.Machine;
import ee.taltech.iti0202.coffee.tank.WaterTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MachineTest {

    WaterTank watertank;

    Machine coffeemachine;
    Drink drink;

    Machine coffeemachine2;

    Machine coffeemachine1;

    @BeforeEach
    void setup() throws IOException {
        drink = new Drink(Drink.DrinkType.LATTE);
        watertank = new WaterTank(5);
        coffeemachine = new Machine.MachineBuilder()
                .setWaterTank(watertank)
                .setBeans(10)
                .setWasteCapacity(40)
                .createMachine();

        coffeemachine2 = new Machine.MachineBuilder()
                .setWasteCapacity(2)
                .setBeans(50)
                .setWaterTank(watertank)
                .createMachine();
        coffeemachine1 = new Machine.MachineBuilder()
                .setWasteCapacity(99)
                .setBeans(1)
                .setWaterTank(watertank)
                .createMachine();
    }

    @Test
    void testIfMachineHaveWater() throws NoBeansException, TankEmptyException, TrashIsFullException {
        assertTrue(coffeemachine.machineHaveWater());
        for (int i = 0; i < 6; i++) {
            try {
                coffeemachine.makeDrink(drink);
            } catch (TankEmptyException tankEmptyException) {
                assertEquals("No water in tank!", tankEmptyException.getResult());

            }
        }
        assertFalse(coffeemachine.machineHaveWater());
    }

    @Test
    void testIfMachineHaveBeans() throws NoBeansException, TankEmptyException, TrashIsFullException {
        assertTrue(coffeemachine1.machineHaveBeans());
        for (int i = 0; i < 6; i++) {
            try {
                coffeemachine1.makeDrink(drink);
            } catch (NoBeansException noBeansException) {
                assertEquals("No beans!", noBeansException.getResult());

            }
        }
        assertFalse(coffeemachine1.machineHaveBeans());
    }
    @Test
    void testIfTrashIsFull() throws NoBeansException, TankEmptyException, TrashIsFullException {
        assertFalse(coffeemachine2.trashisFull());
        for (int i = 0; i < 15; i++) {
            try {
                coffeemachine2.makeDrink(drink);
            } catch (TrashIsFullException trashIsFullException) {
                assertEquals("Trash is full!", trashIsFullException.getResult());
            }
        }
        assertTrue(coffeemachine2.trashisFull());
    }

}