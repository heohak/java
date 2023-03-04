package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrbFactory {
    private ResourceStorage resourceStorage;
    private List<Oven> ovens = new ArrayList<>();
    private List<Orb> currentList;

    List<Optional<Orb>> orbs = new ArrayList<Optional<Orb>>();


    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }
    public void addOven(Oven oven) {
        if (!ovens.contains(oven) && this.resourceStorage == oven.getResourceStorage()) {
            ovens.add(oven);
        }
    }
    public List<Oven> getOvens() {
        return ovens;
    }
    public List<Orb> getAndClearProducedOrbsList() {
        currentList = new ArrayList<>();
        orbs.clear();
        return currentList;
    }

    public int produceOrbs() {
        if (ovens.size() > 0) {
            for (Oven oven : ovens) {
                if (!oven.isBroken()) {
                    Optional<Orb> orb = oven.craftOrb();
                    orbs.add(orb);
                }
            }
            return ovens.size();
        }
        return 0;
    }

    public int produceOrbs(int cycles) {
        int totalProducedOrbsCount = 0;
        for (int i = 0; i < cycles; i++) {
            totalProducedOrbsCount += produceOrbs();
        }
        return totalProducedOrbsCount;


    }


}
