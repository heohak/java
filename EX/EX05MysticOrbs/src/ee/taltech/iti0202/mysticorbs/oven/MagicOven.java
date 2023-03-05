package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {
    public static final int MAX_REPAIRS = 10;

    Integer count = 0;
    private boolean isBroken;
    private int timesFixed = 0;
    private final int INITIAL_CLAY_AMOUNT = 25;
    private final int INITIAL_FREEZING_POWDER_AMOUNT = 100;
    private int clayAmount = INITIAL_CLAY_AMOUNT;
    private int freezingPowderAmount = INITIAL_FREEZING_POWDER_AMOUNT;


    /**
     *
     * @param name
     * @param resourceStorage
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        this.isBroken = false;
    }

    /**
     *
     * @return boolean
     */
    @Override
    public boolean isBroken() {
        return isBroken;
    }

    /**
     *
     * @return Optional
     */
    public void setBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }
    @Override
    public Optional<Orb> craftOrb() {
        Orb someOrb = new Orb(this.name);
        MagicOrb magic1 = new MagicOrb(this.name);

        if (count % 2 == 0) {
            if (resourceStorage.hasEnoughResource("gold", 1) && resourceStorage.hasEnoughResource("dust", 3)) {
                resourceStorage.takeResource("gold", 1);
                resourceStorage.takeResource("dust", 3);
                someOrb.charge("gold", 1);
                someOrb.charge("dust", 3);
                count++;
                createdOrbs++;
                if (createdOrbs >= 5) {
                    setBroken(true);
                }
                return Optional.of(someOrb);
            }
        } else {
            if (resourceStorage.hasEnoughResource("gold", 1) && resourceStorage.hasEnoughResource("dust", 3)) {
                resourceStorage.takeResource("gold", 1);
                resourceStorage.takeResource("dust", 3);
                someOrb.charge("gold", 1);
                someOrb.charge("dust", 3);
                count++;
                createdOrbs++;
                if (createdOrbs >= 5) {
                    setBroken(true);
                }
                return Optional.of(magic1);

            }
        }
        return Optional.empty();
    }

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }
        if (timesFixed >= 10) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        }
        if (!getResourceStorage().hasEnoughResource("clay", clayAmount)
                || !getResourceStorage().hasEnoughResource("freezing powder", freezingPowderAmount)) {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
        getResourceStorage().takeResource("clay", clayAmount);
        getResourceStorage().takeResource("freezing powder", freezingPowderAmount);
        timesFixed++;
        clayAmount += INITIAL_CLAY_AMOUNT;
        freezingPowderAmount += INITIAL_FREEZING_POWDER_AMOUNT;
        setBroken(false);


    }

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }

    public boolean getNextMagicBall() {
        if (count % 2 != 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
