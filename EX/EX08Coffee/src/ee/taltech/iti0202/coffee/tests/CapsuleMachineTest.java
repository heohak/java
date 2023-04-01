package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.TankEmptyException;
import ee.taltech.iti0202.coffee.exceptions.TrashIsFullException;
import ee.taltech.iti0202.coffee.machine.AutomaticMachine;
import ee.taltech.iti0202.coffee.machine.CapsuleMachine;
import ee.taltech.iti0202.coffee.tank.WaterTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CapsuleMachineTest {

    WaterTank watertank;

    CapsuleMachine capsuleMachine;
    Drink drink;

    @BeforeEach
    void setup() throws IOException {
        drink = new Drink(Drink.DrinkType.CAPPUCCINO);
        watertank = new WaterTank(6);
        capsuleMachine = new CapsuleMachine.CapsuleMachineBuilder()
                .setWaterTank(watertank)
                .createCapsuleMachine();
    }

    @Test
    void testMakeDrinkWithCapsule() throws TankEmptyException, TrashIsFullException {
        assertEquals(Drink.DrinkType.CAPPUCCINO, capsuleMachine.makeDrink(drink));
    }

    @Test
    void testMakeDrinkWithEmptyCapsule() throws TankEmptyException, TrashIsFullException {
        capsuleMachine.makeDrink(drink);
        assertNull(capsuleMachine.makeDrink(drink));
    }

    @Test
    void testChangeCapsule() throws TankEmptyException, TrashIsFullException {
        capsuleMachine.makeDrink(drink);
        assertTrue(capsuleMachine.machineCapsuleIn());
        assertTrue(capsuleMachine.capsuleIsEmpty());
        capsuleMachine.changeCapsule();
        assertEquals(Drink.DrinkType.CAPPUCCINO, capsuleMachine.makeDrink(drink));
    }

}