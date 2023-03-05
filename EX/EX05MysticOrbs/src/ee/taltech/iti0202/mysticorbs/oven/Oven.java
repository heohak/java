package ee.taltech.iti0202.mysticorbs.oven;

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
        // Compare broken status
        if (this.isBroken && !o.isBroken) {
            return -1;
        } else if (!this.isBroken && o.isBroken) {
            return 1;
        }

        // Compare oven type
        if (this instanceof SpaceOven && !(o instanceof SpaceOven)) {
            return 1;
        } else if (!(this instanceof SpaceOven) && o instanceof SpaceOven) {
            return -1;
        } else if (this instanceof MagicOven && !(o instanceof MagicOven)) {
            return 1;
        } else if (!(this instanceof MagicOven) && o instanceof MagicOven) {
            return -1;
        } else if (!(this instanceof SpaceOven) && !(this instanceof MagicOven)
                && (o instanceof SpaceOven || o instanceof MagicOven)) {
            return -1;
        }

        // Compare next magic ball
        if (this instanceof MagicOven && o instanceof MagicOven) {
            MagicOven m1 = (MagicOven) this;
            MagicOven m2 = (MagicOven) o;
            if (m1.getNextMagicBall() && !m2.getNextMagicBall()) {
                return -1;
            } else if (!m1.getNextMagicBall() && m2.getNextMagicBall()) {
                return 1;
            }
        }

        // Compare InfinityMagicOven
        if (this instanceof InfinityMagicOven && !(o instanceof InfinityMagicOven)) {
            return 1;
        } else if (!(this instanceof InfinityMagicOven) && o instanceof InfinityMagicOven) {
            return -1;
        }

        // Compare produced balls
        if (this.createdOrbs < o.createdOrbs) {
            return -1;
        } else if (this.createdOrbs > o.createdOrbs) {
            return 1;
        }

        // Compare names
        return this.name.compareTo(o.name);

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
}
