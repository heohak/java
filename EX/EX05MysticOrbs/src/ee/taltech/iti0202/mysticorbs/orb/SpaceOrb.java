package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {
    /**
     *
     * @param creator
     */
    public SpaceOrb(String creator) {
        super(creator);
        this.energy = 100;
    }

    /**
     *
     * @param resource
     * @param amount
     */
    public void charge(String resource, int amount) {
    }

    /**
     *
     * @return String
     */
    public String toString() {
        return "SpaceOrb by " + creator;
    }

    /**
     *
     * @param orb
     * @return boolean
     */
    public boolean absorb(Orb orb) {
        if (orb.getEnergy() < this.energy) {
            this.energy += orb.energy;
            orb.energy = 0;
            return true;
        } else {
            return false;
        }
    }



}
