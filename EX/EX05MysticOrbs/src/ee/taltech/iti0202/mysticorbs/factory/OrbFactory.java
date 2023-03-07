package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.MagicOven;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.oven.SpaceOven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrbFactory {
    private ResourceStorage resourceStorage;
    private List<Oven> ovens = new ArrayList<>();

    List<Orb> orbs = new ArrayList<>();

    List<Oven> unrepairableOvens = new ArrayList<>();
     private int orbCount = 0;


    /**
     *
     * @param resourceStorage
     */
    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    /**
     *
     * @param oven
     */
    public void addOven(Oven oven) {
        if (!ovens.contains(oven) && this.resourceStorage.equals(oven.getResourceStorage())) {
            ovens.add(oven);
        }
    }

    /**
     *
     * @return List
     */
    public List<Oven> getOvens() {
        return ovens;
    }

    /**
     *
     * @return List
     */
    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> currentList = new ArrayList<>(orbs);
        orbs.clear();
        return currentList;

    }

    /**
     *
     * @return int
     */
    public int produceOrbs() {
        for (Oven oven : ovens) {
            if (oven.isBroken()) {
                if (oven instanceof SpaceOven || oven instanceof MagicOven) {
                    try {
                        oven.fix();
                    } catch (CannotFixException e) {
                        if (e.getReason() == CannotFixException.Reason.FIXED_MAXIMUM_TIMES) {
                            unrepairableOvens.add(oven);
                        }
                    }
                } else {
                    unrepairableOvens.add(oven);
                    continue;
                }
            }
            if (!oven.isBroken()) {
                Optional<Orb> orb = oven.craftOrb();
                if (orb.isPresent()) {
                    orbs.add(orb.get());
                    orbCount++;
                }
            }
        }

        ovens.removeAll(unrepairableOvens);
        return orbCount;
    }

    /**
     *
     * @param cycles
     * @return int
     */
    public int produceOrbs(int cycles) {
        int i = 0;
        while (i < cycles) {
            produceOrbs();
            i++;
        }
        return orbCount;

    }

    /**
     *
     * @return List
     */
    public List<Oven> getOvensThatCannotBeFixed() {
        for (Oven oven : ovens) {
            if (!oven.canBeRepaired() || oven.getTimesFixed() >= Oven.MAX_REPAIRS) {
                unrepairableOvens.add(oven);
            }
        }
        return unrepairableOvens;
    }

    /**
     *
     */
    public void getRidOfOvensThatCannotBeFixed() {
        unrepairableOvens.clear();
    }

    /**
     *
     */
    public void optimizeOvensOrder() {
        ovens.sort(((o1, o2) -> o2.compareTo(o1)));
    }



}
