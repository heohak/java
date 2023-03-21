package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.inhabitants.Animal;
import ee.taltech.iti0202.zoo.inhabitants.Caretaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {
    Caretaker caretaker1;
    Caretaker caretaker2;
    Animal animal1;
    Animal animal2;

    Animal animal3;
    Zoo zoo;



    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        List<Animal.Type> types1 = List.of(Animal.Type.MAMMAL, Animal.Type.BIRD, Animal.Type.FISH);
        List<Animal.Type> types2 = List.of(Animal.Type.FISH);
        caretaker1 = new Caretaker("Robin", types1);
        caretaker2 = new Caretaker("Martin", types2);
        animal1 = new Animal.AnimalBuilder("lamb")
                .setName("lamb")
                .setType(Animal.Type.MAMMAL)
                .setSound("Mää")
                .setHungry(false)
                .build();
        animal2 = new Animal.AnimalBuilder("kajakas")
                .setName("kajakas")
                .setSound("bljat")
                .setType(Animal.Type.BIRD)
                .setDaysToHungry(-5)
                .setHungry(true)
                .build();
        animal3 = new Animal.AnimalBuilder("ahven")
                .setName("ahven")
                .setSound("blub")
                .setDaysToHungry(0)
                .setHungry(true)
                .setType(Animal.Type.FISH)
                .build();


    }
    @Test
    void getCaretakers() {
        zoo.addCaretaker(caretaker1);
        zoo.addCaretaker(caretaker2);
        assertEquals(2, zoo.getCaretakers().size());

    }

    @Test
    void getAnimals() {
        zoo.addAnimal(animal1);
        zoo.addAnimal(animal2);
        zoo.addAnimal(animal3);
        assertEquals(3, zoo.getAnimals().size());
    }
    @Test
    void getHungryAnimals() {
        zoo.addAnimal(animal1);
        zoo.addAnimal(animal2);
        zoo.addAnimal(animal3);
        assertEquals(2, zoo.getUnfedAnimals().size());
        zoo.startNextDay();
        zoo.startNextDay();
        assertEquals(2, zoo.getUnfedAnimals().size());
    }

    @Test
    void getAllSounds() {
        zoo.addAnimal(animal1);
        zoo.addAnimal(animal2);
        assertEquals("lamb: Mää\nkajakas: bljat\n", zoo.reportAnimalSounds());

    }

    @Test
    void getMostefficientWorker() {
        zoo.addAnimal(animal1);
        zoo.addAnimal(animal2);
        zoo.addAnimal(animal3);
        zoo.addCaretaker(caretaker1);
        zoo.addCaretaker(caretaker2);
        assertEquals(caretaker1, zoo.getMostEffectiveCaretaker());

    }






}