package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {
    private Map<String, Integer> resources = new HashMap<>();

    /**
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return resources.isEmpty() || resources.values().stream().allMatch(amount -> amount == 0);

    }

    /**
     *
     * @param resource
     * @param amount
     */
    public void addResource(String resource, int amount) {
        if (resources.containsKey(resource.toLowerCase()) && !resource.toLowerCase().isBlank() && amount > 0) {
            resources.merge(resource.toLowerCase(), amount, Integer::sum);
        } else {
            if (!resource.toLowerCase().isBlank() && amount > 0) {
                resources.put(resource.toLowerCase(), amount);
            }
        }
    }

    /**
     *
     * @param resource
     * @return
     */
    public int getResourceAmount(String resource) {
        if (!resources.containsKey(resource.toLowerCase()) || resources.get(resource.toLowerCase()).equals(0)) {
            return 0;
        } else {
            return resources.get(resource.toLowerCase());
        }
    }

    /**
     *
     * @param resource
     * @param amount
     * @return boolean
     */
    public boolean hasEnoughResource(String resource, int amount) {
        if (amount < 1) {
            return false;
        }

        return resources.getOrDefault(resource.toLowerCase(), 0) >= amount;
    }

    /**
     *
     * @param resource
     * @param amount
     * @return boolean
     */
    public boolean takeResource(String resource, int amount) {
        if (resources.containsKey(resource.toLowerCase()) && resources.get(resource.toLowerCase()) >= amount) {
            resources.merge(resource.toLowerCase(), amount, (oldValue, newValue) -> oldValue - newValue);
            return true;

        } else {
            return false;
        }
    }


}
