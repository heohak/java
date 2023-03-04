package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven {
    private final int MAX_25 = 25;
    private final int FIFTEEN = 15;

    /**
     *
     * @param name
     * @param resourceStorage
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    /**
     *
     * @return boolean
     */
    @Override
    public boolean isBroken() {
        return createdOrbs >= MAX_25;
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

        if (starFragments >= FIFTEEN && meteoriteStones >= 1 && !this.isBroken()) {
            resourceStorage.takeResource("star fragment", 15);
            resourceStorage.takeResource("meteorite stone", 1);
            createdOrbs++;
            return Optional.of(new SpaceOrb(this.name));
        } else if (pearls >= 1 && silver >= 1) {
            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
            createdOrbs++;
            return Optional.of(new Orb(this.name));
        } else {
            return Optional.empty();
        }


    }
}
