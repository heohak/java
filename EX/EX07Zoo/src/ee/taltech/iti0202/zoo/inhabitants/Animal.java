package ee.taltech.iti0202.zoo.inhabitants;

import java.util.Random;


public final class Animal {
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

    public enum Type {
        MAMMAL,
        BIRD,
        FISH,
        REPTILE,
        AMPHIBIAN,

    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return int
     */
    public int getDaysToHungry() {
        return daysToHungry;
    }

    /**
     *
     * @return boolean
     */
    public boolean isHungry() {
        return daysToHungry <= 0 && !this.name.equals("lamb");
    }

    /**
     *
     * @return Type
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @return String
     */
    public String getSound() {
        if (this.name.equals("turtle")) {
            this.sound = "";
            return sound;
        } else if (this.name.equals("lamb")) {
            this.sound = "Mää";
            return this.sound;
        } else if (this.name.equals("monkey")) {
            if (this.isHungry) {
                this.sound = "BANANA";
                return this.sound;
            } else {

            String[] sounds = {"uuh", "ääh"};
            Random rand = new Random();
            int randomIndex = rand.nextInt(sounds.length);
            return sounds[randomIndex];
            }
        } else {
            return sound;
        }
    }

    /**
     * Void.
     */
    public void decrementHunger() {
        this.daysToHungry = this.daysToHungry - 1;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param sound
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     *
     * @param daysToHungry
     */
    public void setDaysToHungry(int daysToHungry) {
        this.daysToHungry = daysToHungry;
    }

    /**
     *
     * @param hungry
     */
    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }


    /**
     *
     * @return String
     */
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

        /**
         *
         * @param name
         */
        public AnimalBuilder(String name) {
            this.name = name;

        }

        /**
         *
         * @param name
         * @return this
         */
        public AnimalBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         *
         * @param sound
         * @return this
         */
        public AnimalBuilder setSound(String sound) {
                this.sound = sound;
            return this;
        }

        /**
         *
         * @param daysToHungry
         * @return this
         */
        public AnimalBuilder setDaysToHungry(int daysToHungry) {
            this.daysToHungry = daysToHungry;
            return this;
        }

        /**
         *
         * @param type
         * @return this
         */
        public AnimalBuilder setType(Animal.Type type) {
            this.type = type;
            return this;
        }

        /**
         *
         * @param hungry
         * @return this
         */
        public AnimalBuilder setHungry(boolean hungry) {
            this.isHungry = hungry;
            return this;
        }

        /**
         *
         * @return Animal
         */
        public Animal build() {
            return new Animal(this);
        }
    }
    }
