package ee.taltech.iti0202.zoo.inhabitants;

import java.util.Random;

;

public class Animal {
    //All final attributes
    private String name;

    private  String sound;
    private int daysToHungry;
    private boolean isHungry;

    private Type type;

    private Animal(AnimalBuilder builder) {
        this.name = builder.name;
        this.sound = builder.sound;
        this.daysToHungry = builder.daysToHungry;
        this.isHungry = builder.isHungry;
        this.type = builder.type;
    }

    public enum Type{
        MAMMAL,
        BIRD,
        FISH,
        REPTILE,
        AMPHIBIAN,

    }

    //All getter, and NO setter to provde immutability
    public String getName() {
        return name;
    }

    public int getDaysToHungry() {
        return daysToHungry;
    }

    public boolean isHungry() {
        if (daysToHungry > 0 || this.name.equals("lamb")) {
            return false;
        }
        else {
            return true;
        }
    }

    public Type getType() {
        return type;
    }

    public String getSound() {
        if (this.name.equals("turtle")) {
            this.sound = "";
            return sound;
        }
        else if (this.name.equals("lamb")) {
            this.sound = "Mää";
            return this.sound;
        }
        else if (this.name.equals("monkey")) {
            if (this.isHungry) {
                this.sound = "BANANA";
                return this.sound;
            } else {

            String[] sounds = {"uuh", "ääh"};
            Random rand = new Random();
            int randomIndex = rand.nextInt(sounds.length);
            return sounds[randomIndex];
            }
        }
        else {
            return sound;
        }
    }

    public void decrementHunger() {
        this.daysToHungry = this.daysToHungry - 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setDaysToHungry(int daysToHungry) {
        this.daysToHungry = daysToHungry;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }



    @Override
    public String toString() {
        return "Animal: " + this.name;
    }


    public static class AnimalBuilder {
        private String name;
        private String sound;
        private int daysToHungry;
        private boolean isHungry;
        private Animal.Type type;

        public AnimalBuilder(String name) {
            this.name = name;

        }

        public AnimalBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AnimalBuilder setSound(String sound) {


                this.sound = sound;

            return this;
        }

        public AnimalBuilder setDaysToHungry(int daysToHungry) {
            this.daysToHungry = daysToHungry;
            return this;
        }

        public AnimalBuilder setType(Animal.Type type) {

            this.type = type;

            return this;
        }

        public AnimalBuilder setHungry(boolean hungry) {

            this.isHungry = hungry;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }

    }


    }
