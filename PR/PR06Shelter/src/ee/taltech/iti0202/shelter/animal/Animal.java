
package ee.taltech.iti0202.shelter.animal;

public abstract class Animal {
    public enum Type {
        HIROLA, TARANTULA, WOMBAT
    }
    private String color;

    /**
     *
     * @param color
     */
    public Animal(String color) {
        this.color = color;
    }

    /**
     *
     * @return String
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }
}
