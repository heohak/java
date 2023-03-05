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
    private final int initialClayAmount = 25;
    private final int initialFreezingPowderAmount = 100;
    private int clayAmount = initialClayAmount;
    private int freezingPowderAmount = initialFreezingPowderAmount;


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
     * @param isBroken
     */
    public void setBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }

    /**
     *
     * @return Optional
     */
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
        clayAmount += initialClayAmount;
        freezingPowderAmount += initialFreezingPowderAmount;
        setBroken(false);


    }

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }

    public boolean getNextMagicBall() {
        return count % 2 != 0;
    }
}
