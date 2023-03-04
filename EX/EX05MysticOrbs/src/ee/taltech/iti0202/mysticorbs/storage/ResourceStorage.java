package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {
    private Map<String, Integer> resources = new HashMap<>();

    public boolean isEmpty() {
        return resources.isEmpty();

    }

    public void addResource(String resource, int amount) {
        if (resources.containsKey(resource.toLowerCase()) && !resource.toLowerCase().isBlank() && amount > 0) {
            resources.merge(resource.toLowerCase(), amount, Integer::sum);
        }
        else {
            if (!resource.toLowerCase().isBlank() && amount > 0) {
                resources.put(resource.toLowerCase(), amount);
            }
        }
    }

    public int getResourceAmount(String resource) {
        if (!resources.containsKey(resource.toLowerCase()) || resources.get(resource.toLowerCase()).equals(0)) {
            return 0;
        } else {
            return resources.get(resource.toLowerCase());
        }
    }

    public boolean hasEnoughResource(String resource, int amount) {
        return resources.get(resource.toLowerCase()) >= amount;
    }

    public boolean takeResource(String resource, int amount) {
        if (resources.containsKey(resource.toLowerCase()) && resources.get(resource.toLowerCase()) >= amount) {
            resources.merge(resource.toLowerCase(), amount, (oldValue, newValue) -> oldValue - newValue);
            return true;

        } else {
            return false;
        }
    }


}
