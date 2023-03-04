package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb {


    public MagicOrb(String creator) {
        super(creator);
    }

    @Override
    public void charge(String resource, int amount) {
        int addedAmount = resource.length() * amount * 2;
        if (!resource.toLowerCase().equals("dust") && !resource.isBlank() && amount > 0) {
            this.energy += addedAmount;

        }
    }

    @Override
    public String toString() {
        return "MagicOrb by " + super.creator;
    }
}
