package ee.taltech.iti0202.zoo.inhabitants;

import ee.taltech.iti0202.zoo.Zoo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaretakerTest {


    Caretaker caretaker;
    Animal animal;
    Zoo zoo;

    Animal animal2;

    Animal animal3;

    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        List<Animal.Type> types = List.of(Animal.Type.MAMMAL, Animal.Type.REPTILE);
        caretaker = new Caretaker("Peeter", types);
        animal = new Animal.AnimalBuilder("armadillo")
                .setName("armadillo")
                .setType(Animal.Type.MAMMAL)
                .setSound("möh")
                .setHungry(false)
                .setDaysToHungry(2)
                .build();
        animal2 = new Animal.AnimalBuilder("sisalik")
                .setName("sisalik")
                .setType(Animal.Type.REPTILE)
                .setHungry(true)
                .setDaysToHungry(-2)
                .build();
        animal3 = new Animal.AnimalBuilder("elevant")
                .setName("elevant")
                .setType(Animal.Type.MAMMAL)
                .setSound("karmauh")
                .setDaysToHungry(0)
                .setHungry(true)
                .build();


    }

    @Test
    void getCaretakerName() {
        assertEquals("Peeter", caretaker.getName());
        caretaker.setName("Juhan");
        assertEquals("Juhan", caretaker.getName());
    }

    @Test
    void getCaretakerSpeciality() {
        assertEquals(2,caretaker.getSpeciality().size());
    }

    @Test
    void caretakerFeedAnimal() {
        assertFalse(caretaker.canFeedAnimal(animal));
        zoo.addAnimal(animal);
        zoo.startNextDay();
        zoo.startNextDay();
        assertTrue(caretaker.canFeedAnimal(animal));

    }

    @Test

    void FeedListOfAnimals() {
        List<Animal> animals = List.of(animal, animal2, animal3);
        assertEquals(2, caretaker.feedAnimals(animals));

    }


}