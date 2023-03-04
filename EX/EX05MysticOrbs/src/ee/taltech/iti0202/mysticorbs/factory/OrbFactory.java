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
        int totalProduced = 0;
        for (int i = 0; i < cycles; i++) {
            int producedThisCycle = produceOrbs();
            totalProduced += producedThisCycle;
            if (producedThisCycle == 0) {
                // If no orbs were produced this cycle, stop producing
                break;
            }
        }
        return totalProduced;



    }


}
