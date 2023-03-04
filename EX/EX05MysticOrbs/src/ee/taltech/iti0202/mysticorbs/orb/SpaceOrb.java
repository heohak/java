package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {
    public SpaceOrb(String creator) {
        super(creator);
        this.energy = 100;
    }
    public void charge(String resource, int amount) {
    }
    public String toString() {
        return "SpaceOrb by " + creator;
    }

    public boolean absorb(Orb orb) {
        if (orb.getEnergy() < this.energy) {
            this.energy += orb.energy;
            orb.energy = 0;
            return true;
        }
        else {
            return false;
        }
    }




}
