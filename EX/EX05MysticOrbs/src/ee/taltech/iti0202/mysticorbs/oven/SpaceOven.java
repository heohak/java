package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven {

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        return createdOrbs >= 25;
    }

    @Override
    public Optional<Orb> craftOrb() {
        Orb orb1 = new Orb(this.name);
        SpaceOrb space1 = new SpaceOrb(this.name);

        if (this.isBroken() || !resourceStorage.hasEnoughResource("meteorite stone", 1) || !resourceStorage.hasEnoughResource("star fragment", 15)) {
            if (!this.isBroken() && resourceStorage.hasEnoughResource("pearl", 1) && resourceStorage.hasEnoughResource("silver", 1)) {
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
        this.resourceStorage.takeResource("meteorite stone", 1);
        this.resourceStorage.takeResource("star fragment", 15);
        createdOrbs++;
        return Optional.of(space1);

    }
}
