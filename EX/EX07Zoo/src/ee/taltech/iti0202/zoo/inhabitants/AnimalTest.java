package ee.taltech.iti0202.zoo.inhabitants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Animal.AnimalBuilder("monkey")
                .setName("monkey")
                .setSound("uuh")
                .setDaysToHungry(3)
                .setType(Animal.Type.MAMMAL)
                .setHungry(false)
                .build();
    }

    @Test
    void testGetName() {
        assertEquals("monkey", animal.getName());
    }

    @Test
    void testGetDaysToHungry() {
        assertEquals(3, animal.getDaysToHungry());
    }

    @Test
    void testIsHungry() {
        assertFalse(animal.isHungry());
        animal.setDaysToHungry(0);
        assertTrue(animal.isHungry());
    }

    @Test
    void testGetType() {
        assertEquals(Animal.Type.MAMMAL, animal.getType());
    }

    @Test
    void testGetSound() {
        animal.setName("turtle");
        assertEquals("", animal.getSound());
        animal.setName("monkey");
        animal.setHungry(true);
        assertEquals("BANANA", animal.getSound());
    }

    @Test
    void testDecrementHunger() {
        animal.decrementHunger();
        assertEquals(2, animal.getDaysToHungry());
    }


    @Test
    void testBuilder() {
        Animal animal2 = new Animal.AnimalBuilder("lamb")
                .setType(Animal.Type.MAMMAL)
                .build();
        assertEquals("lamb", animal2.getName());
        assertEquals("Mää", animal2.getSound());
        assertEquals(0, animal2.getDaysToHungry());
        assertEquals(Animal.Type.MAMMAL, animal2.getType());
        assertFalse(animal2.isHungry());
    }

    @Test
    void getAnimalString() {
        assertEquals("Animal: monkey", animal.toString());
    }

    @Test
    void changeAnimalSound() {
        Animal lehm1 = new Animal.AnimalBuilder("lehm")
                .setName("lehm")
                .setSound("Muu")
                .build();

        lehm1.setSound("Jou");
        assertEquals("Jou", lehm1.getSound());
    }

    @Test
    void monkeySounds() {
        String str1 = animal.getSound();
        String str2 = animal.getSound();
        String str3 = animal.getSound();
        String[] myArray = {str1, str2, str3};
        List<String> myList = Arrays.asList(myArray);
        assertTrue(myList.contains("uuh"));
        assertTrue(myList.contains("ääh"));

    }

}