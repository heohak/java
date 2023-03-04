package ee.taltech.iti0202.mysticorbs.orb;

public class Orb {

   protected String creator;
   protected Integer energy;

    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    public void charge(String resource, int amount) {
        int addedAmount = resource.length() * amount;
        if (!resource.equals("dust") && !resource.isBlank()) {
            this.energy += addedAmount;

        }

    }
    public int getEnergy() {
        return this.energy;
    }

    public String toString() {
        return "Orb by " + creator;
    }

}


