package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class Oven {

    protected String name;
    protected ResourceStorage resourceStorage;
    protected Integer createdOrbs = 0;

    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
    }

    public String getName() {
        return name;
    }

    public ResourceStorage getResourceStorage() {
        return resourceStorage;
    }

    public int getCreatedOrbsAmount() {
        return createdOrbs;

    }
    public boolean isBroken() {
        return createdOrbs >= 15;
    }

    public Optional<Orb> craftOrb() {
        Orb orb1 = new Orb(this.name);

        if (!this.isBroken() && this.resourceStorage.hasEnoughResource("pearl", 1) && this.resourceStorage.hasEnoughResource("silver", 1)) {
            this.resourceStorage.takeResource("pearl", 1);
            this.resourceStorage.takeResource("silver", 1);
            orb1.charge("pearl", 1);
            orb1.charge("silver", 1);
            createdOrbs++;
            return Optional.of(orb1);
        }
        else {
            return Optional.empty();
        }

    }


}
