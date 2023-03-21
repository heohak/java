package ee.taltech.iti0202.zoo.inhabitants;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private String name;

    private List<Animal.Type> speciality;


    public Caretaker(String name, List<Animal.Type> speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal.Type> getSpeciality() {
        return speciality;
    }



    public boolean canFeedAnimal(Animal animal) {
        if (speciality.contains(animal.getType()) && animal.isHungry()) {
            return true;
        }
        return false;
    }

    public int feedAnimals(List<Animal> animalsToFeed) {
        int result = 0;
        for (Animal animal : animalsToFeed) {
            if (this.canFeedAnimal(animal)) {
                animal.setHungry(false);
                animal.setDaysToHungry(2);
                result++;
            }
        }
        return result;


    }
}
