package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb {


    /**
     *
     * @param creator
     */
    public MagicOrb(String creator) {
        super(creator);
    }

    /**
     *
     * @param resource
     * @param amount
     */
    @Override
    public void charge(String resource, int amount) {
        int addedAmount = resource.length() * amount * 2;
        if (!resource.toLowerCase().equals("dust") && !resource.isBlank() && amount > 0) {
            this.energy += addedAmount;

        }
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "MagicOrb by " + super.creator;
    }
}
