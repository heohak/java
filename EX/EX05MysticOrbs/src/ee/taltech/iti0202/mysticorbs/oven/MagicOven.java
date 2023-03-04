package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven {

    Integer count = 0;


    /**
     *
     * @param name
     * @param resourceStorage
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    /**
     *
     * @return boolean
     */
    @Override
    public boolean isBroken() {
        return createdOrbs >= 5;
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
                return Optional.of(magic1);

            }
        }
        return Optional.empty();
    }
}
