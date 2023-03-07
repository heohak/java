package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class Oven implements Comparable<Oven>  {
    public static final int MAX_REPAIRS = 3;
    private boolean isBroken;
    private final int fifteen = 15;

    protected String name;
    protected ResourceStorage resourceStorage;
    protected Integer createdOrbs = 0;
    protected int timesFixed = 0;

    /**
     *
     * @param name
     * @param resourceStorage
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
        this.isBroken = false;
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return ResourceStorage
     */
    public ResourceStorage getResourceStorage() {
        return resourceStorage;
    }

    /**
     *
     * @return int
     */
    public int getCreatedOrbsAmount() {
        return createdOrbs;

    }

    /**
     *
     * @return boolean
     */
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
    public Optional<Orb> craftOrb() {
        Orb orb1 = new Orb(this.name);

        if (!this.isBroken() && this.resourceStorage
                .hasEnoughResource("pearl", 1) && this.resourceStorage
                .hasEnoughResource("silver", 1)) {
            this.resourceStorage.takeResource("pearl", 1);
            this.resourceStorage.takeResource("silver", 1);
            orb1.charge("pearl", 1);
            orb1.charge("silver", 1);
            createdOrbs++;
            if (createdOrbs >= fifteen) {
                setBroken(true);

            }
            return Optional.of(orb1);

    } else {
            return Optional.empty();
        }

    }


    /**
     *
     * @param o the object to be compared.
     * @return int
     */
    @Override
    public int compareTo(Oven o) {
        if (isBroken() && !(o.isBroken())) {
            return -1;
        } else if (!isBroken() && o.isBroken()) {
            return 1;
        } else if ((isBroken() && o.isBroken()) || (!isBroken() && !(o.isBroken()))) {
            if ((this instanceof SpaceOven && !(o instanceof SpaceOven))
                    || (this instanceof MagicOven && !(o instanceof MagicOven)) && !(o instanceof SpaceOven)) {
                return 1;
            } else if ((o instanceof SpaceOven && !(this instanceof SpaceOven))
                    || (!(this instanceof MagicOven) && !(this instanceof SpaceOven) && o instanceof MagicOven)) {
                return -1;
            } else if (this instanceof MagicOven && o instanceof MagicOven) {
                if (this.getCreatedOrbsAmount() == o.getCreatedOrbsAmount()) {
                    if (o instanceof InfinityMagicOven) {
                        return -1;
                    } else if (this instanceof InfinityMagicOven) {
                        return 1;
                    }
                } else if (this.getCreatedOrbsAmount() % 2 == 0 && o.getCreatedOrbsAmount() % 2 != 0) {
                    return -1;
                } else if (this.getCreatedOrbsAmount() % 2 != 0 && o.getCreatedOrbsAmount() % 2 == 0) {
                    return 1;
                } else if (this.getCreatedOrbsAmount() > o.getCreatedOrbsAmount()) {
                    return -1;
                } else if (this.getCreatedOrbsAmount() < o.getCreatedOrbsAmount()) {
                    return 1;
                }
            } else if (this.getCreatedOrbsAmount() > o.getCreatedOrbsAmount()) {
                return -1;
            } else if (this.getCreatedOrbsAmount() < o.getCreatedOrbsAmount()) {
                return 1;
            } else if (this.name.compareTo(o.name) > 0) {
                return 1;
            } else if (this.name.compareTo(o.name) < 0) {
                return -1;
            }
        }
        return 0;

    }

    /**
     *
     * @return boolean
     */
    public boolean canBeRepaired() {
        if (!(timesFixed >= MAX_REPAIRS)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return int
     */
    public int getTimesFixed() {
        return timesFixed;
    }


    /**
     *
     * @throws CannotFixException
     */
    public void fix() throws CannotFixException {

    }
}
