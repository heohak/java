package ee.taltech.iti0202.lotr;

public class Person {


    private String race;
    private String name;
    private Ring ring;

    /**
     *
     * @param race
     * @param name
     * @param ring
     */
    public Person(String race, String name, Ring ring) {

        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    /**
     *
     * @param race
     * @param name
     */
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
        this.ring = null;
    }

    /**
     *
     * @param ring
     */
    public void setRing(Ring ring) {
        this.ring = ring;
    }

    /**
     *
     * @return String
     */
    public String isSauron() {

        if (getRing() == null) {
            if (getName().equals("Sauron")) {
                return "No, but he's claiming to be";
            } else {
                return "No";
            }
        }

        if (getName().equals("Sauron") && getRing().getType().equals(Ring.Type.THE_ONE)
                && getRing().getMaterial().equals(Ring.Material.GOLD)) {
            return "Affirmative";
        } else if (getName().equals("Sauron") && getRing().getType().equals(Ring.Type.THE_ONE)
                && !(getRing().getMaterial().equals(Ring.Material.GOLD))) {
            return "No, the ring is fake!";
        } else if (!(getName().equals("Sauron")) && getRing().getType().equals(Ring.Type.THE_ONE)
                && getRing().getMaterial().equals(Ring.Material.GOLD)) {
            return "No, he just stole the ring";
        } else if (getName().equals("Sauron") && !getRing().getType().equals(Ring.Type.THE_ONE)) {
            return "No, but he's claiming to be";
        } else {
            return "No";
        }
    }

    /**
     *
     * @return race
     */
    public String getRace() {
        return race;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return ring
     */
    public Ring getRing() {
        return ring;
    }
}
