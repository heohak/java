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


    /**
     *
     * @return List
     */
    public List<Caretaker> getCaretakers() {
        return caretakers;
    }

    /**
     *
     * @return List
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     *
     * @param animal
     */
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    /**
     *
     * @param caretaker
     */
    public void addCaretaker(Caretaker caretaker) {
        this.caretakers.add(caretaker);
    }

    /**
     *
     * @return List
     */
    public List<Animal> getUnfedAnimals() {
        List<Animal> unfedAnimals = new ArrayList<>();
        for (Animal animal : this.animals) {
            if (animal.isHungry()) {
                unfedAnimals.add(animal);
            }
        }
        return unfedAnimals;
    }

    /**
     *
     * @return String
     */
    public String reportAnimalSounds() {
        String result = "";
        for (Animal animal : this.animals) {
            result = result + animal.getName() + ": " + animal.getSound() + "\n";
        }
        return result;
    }

    /**
     *
     * @return Caretaker
     */
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

    /**
     *
     */
    public void startNextDay() {
        for (Animal animal : this.animals) {
            animal.decrementHunger();
        }
    }



}
