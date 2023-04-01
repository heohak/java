package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoBeansException;
import ee.taltech.iti0202.coffee.exceptions.TankEmptyException;
import ee.taltech.iti0202.coffee.exceptions.TrashIsFullException;
import ee.taltech.iti0202.coffee.kitchen.Kitchen;
import ee.taltech.iti0202.coffee.machine.AutomaticMachine;
import ee.taltech.iti0202.coffee.machine.Machine;
import ee.taltech.iti0202.coffee.tank.WaterTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class KitchenTest {
    WaterTank waterTank;
    AutomaticMachine automaticMachine;

    Kitchen kitchen;

    Drink drink;

    @BeforeEach
    void setup() throws IOException {
        drink = new Drink(Drink.DrinkType.LATTE);
        waterTank = new WaterTank(50);
        kitchen = new Kitchen();

        automaticMachine = new AutomaticMachine.AutomaticMachineBuilder()
                .setWaterTank(waterTank)
                .setWasteCapacity(20)
                .createAutomaticMachine();
    }

    @Test
    void makeCoffeeInKitchen() throws NoBeansException, TankEmptyException, TrashIsFullException {
        kitchen.addMachine(automaticMachine);
        assertEquals(Drink.DrinkType.LATTE, kitchen.makeCoffee(automaticMachine, drink));

    }


}