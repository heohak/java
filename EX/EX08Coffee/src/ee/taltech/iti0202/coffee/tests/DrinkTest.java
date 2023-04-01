package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drinks.Drink;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    @Test
    void testGetDrinkType() {
        Drink drink = new Drink(Drink.DrinkType.LATTE);
        assertEquals(Drink.DrinkType.LATTE, drink.getDrinkType());
    }

}