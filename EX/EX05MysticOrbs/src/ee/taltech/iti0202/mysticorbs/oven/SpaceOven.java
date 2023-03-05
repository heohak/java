package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {
    public static final int MAX_REPAIRS = 5;
    private final int max = 25;
    private final int fifteen = 15;
    private boolean isBroken;

    private int timesFixed = 0;
    private final int LIQUID_SILVER_AMOUNT = 40;
    private final int STAR_ESSENCE_AMOUNT = 10;

    /**
     *
     * @param name
     * @param resourceStorage
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
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
    public void setBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }

    /**
     *
     * @return Optional
     */

    @Override
    public Optional<Orb> craftOrb() {
        int starFragments = resourceStorage.getResourceAmount("star fragment");
        int meteoriteStones = resourceStorage.getResourceAmount("meteorite stone");
        int pearls = resourceStorage.getResourceAmount("pearl");
        int silver = resourceStorage.getResourceAmount("silver");

        if (starFragments >= fifteen && meteoriteStones >= 1 && !this.isBroken()) {
            resourceStorage.takeResource("star fragment", fifteen);
            resourceStorage.takeResource("meteorite stone", 1);
            createdOrbs++;
            if (createdOrbs >= max) {
                setBroken(true);
            }
            return Optional.of(new SpaceOrb(this.name));
        } else if (pearls >= 1 && silver >= 1) {
            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
            createdOrbs++;
            if (createdOrbs >= max) {
                setBroken(true);
            }
            return Optional.of(new Orb(this.name));
        } else {
            return Optional.empty();
        }


    }

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }
        if (timesFixed >= 5) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        }
        if (getResourceStorage().hasEnoughResource("liquid silver", LIQUID_SILVER_AMOUNT)) {
            getResourceStorage().takeResource("liquid silver", LIQUID_SILVER_AMOUNT);
        } else if (getResourceStorage().hasEnoughResource("star essence", STAR_ESSENCE_AMOUNT)) {
            getResourceStorage().takeResource("star essence", STAR_ESSENCE_AMOUNT);
        } else {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
        timesFixed++;
        if (timesFixed >= 5) {
            setBroken(false);
        }

    }

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }
}
