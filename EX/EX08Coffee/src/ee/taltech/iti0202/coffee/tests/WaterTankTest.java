package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.tank.WaterTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterTankTest {
    WaterTank watertank;

    @BeforeEach
    void setup() {
         watertank = new WaterTank(1);
    }

    @Test
    void testIfWaterInTank() {
        assertTrue(watertank.isWaterInTank());
        watertank.takeWater();
        assertFalse(watertank.isWaterInTank());

    }

    @Test
    void reFillWaterTank() {
        watertank.takeWater();
        assertFalse(watertank.isWaterInTank());
        watertank.reFillTank();
        assertTrue(watertank.isWaterInTank());

    }

}