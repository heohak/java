package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class Oven {
    private final int FIFTEEN = 15;

    protected String name;
    protected ResourceStorage resourceStorage;
    protected Integer createdOrbs = 0;

    /**
     *
     * @param name
     * @param resourceStorage
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
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
        return createdOrbs >= FIFTEEN;
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
            return Optional.of(orb1);

    } else {
            return Optional.empty();
        }

    }


}
