package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        currentList.addAll(orbs.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
        orbs.clear();
        return currentList;
    }

    public int produceOrbs() {
        int producedOrbs = 0;
        currentList = new ArrayList<>();
        for (Oven oven : ovens) {
            if (!oven.isBroken()) {
                Optional<Orb> orb = oven.craftOrb();
                if (orb.isPresent()) {
                    currentList.add(orb.get());
                    producedOrbs++;
                }
            }
        }
        orbs.clear();
        orbs.addAll(currentList.stream().map(Optional::of).collect(Collectors.toList()));
        return producedOrbs;
    }

    public int produceOrbs(int cycles) {
        int totalProducedOrbs = 0;
        for (int i = 0; i < cycles; i++) {
            totalProducedOrbs += produceOrbs();
        }
        return totalProducedOrbs;



    }


}
