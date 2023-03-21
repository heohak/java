package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.inhabitants.Animal;
import ee.taltech.iti0202.zoo.inhabitants.Caretaker;

import java.util.ArrayList;
import java.util.List;

public class Zoo {

    private List<Caretaker> caretakers;

    private List<Animal> animals;


    public Zoo() {
        this.animals = new ArrayList<>();
        this.caretakers = new ArrayList<>();

    }


    public List<Caretaker> getCaretakers() {
        return caretakers;
    }


    public List<Animal> getAnimals() {
        return animals;
    }
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public void addCaretaker(Caretaker caretaker) {
        this.caretakers.add(caretaker);
    }

    public List<Animal> getUnfedAnimals() {
        List<Animal> unfedAnimals = new ArrayList<>();
        for (Animal animal : this.animals) {
            if (animal.isHungry()) {
                unfedAnimals.add(animal);
            }
        }
        return unfedAnimals;
    }

    public String reportAnimalSounds() {
        String result = "";
        for (Animal animal : this.animals) {
            result = result + animal.getName() + ": " + animal.getSound() + "\n";
        }
        return result;
    }

    public Caretaker getMostEffectiveCaretaker() {
        Caretaker mostEffectiveCaretaker = null;
        int maxFedAnimals = 0;
        for (Caretaker caretaker : this.caretakers) {
            int fedAnimals = caretaker.feedAnimals(this.getUnfedAnimals());
            if (fedAnimals > maxFedAnimals) {
                mostEffectiveCaretaker = caretaker;
                maxFedAnimals = fedAnimals;
            }
        }
        return mostEffectiveCaretaker;
    }

    public void startNextDay() {
        for (Animal animal : this.animals) {
            animal.decrementHunger();
        }
    }



}

