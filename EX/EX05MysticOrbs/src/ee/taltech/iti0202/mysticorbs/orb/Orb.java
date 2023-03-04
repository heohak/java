package ee.taltech.iti0202.mysticorbs.orb;

public class Orb {

   protected String creator;
   protected Integer energy;

    /**
     *
     * @param creator
     */
    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    /**
     *
     * @param resource
     * @param amount
     */
    public void charge(String resource, int amount) {
        int addedAmount = resource.length() * amount;
        if (!resource.toLowerCase().equals("dust") && !resource.isBlank() && amount > 0) {
            this.energy += addedAmount;

        }

    }

    /**
     *
     * @return int
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     *
     * @return String
     */
    public String toString() {
        return "Orb by " + creator;
    }

}


