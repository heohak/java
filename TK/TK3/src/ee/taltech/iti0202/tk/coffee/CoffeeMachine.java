package ee.taltech.iti0202.tk.coffee;

import java.util.*;

public class CoffeeMachine {

    Map<String,Integer> resources = new HashMap<>();

    Map<String, Map<String, Integer>> resNeededForCoffee = new HashMap<>();

    List<Coffee> coffees = new ArrayList<>();

    public CoffeeMachine() {
    }

    public void addResource(String name, int amount) {
        resources.put(name, resources.getOrDefault(name, 0) + amount);

    }

    public void addFlavourResourceRequirement(String flavour, String resource, int amount) {
        if (!resNeededForCoffee.containsKey(flavour)) {
            resNeededForCoffee.put(flavour, new HashMap<>());
        }
        Map<String, Integer> requirements = resNeededForCoffee.get(flavour);
        requirements.put(resource, requirements.getOrDefault(resource, 0) + amount);



    }

    public Optional<Coffee> makeCoffee(String flavour) {
        if (!resNeededForCoffee.containsKey(flavour)) {
            return Optional.empty();
        }

        for (Map.Entry<String , Integer> entry: resNeededForCoffee.get(flavour).entrySet()) {
            String resource = entry.getKey();
            int amountNeeded = entry.getValue();
            if (resources.getOrDefault(resource, 0) < amountNeeded) {
                return Optional.empty();
            }
        }

        for (Map.Entry<String, Integer> entry : resNeededForCoffee.get(flavour).entrySet()) {
            String resource = entry.getKey();
            int amountNeeded = entry.getValue();
            resources.put(resource, resources.get(resource) - amountNeeded);
        }
        Coffee coffee = new Coffee(flavour);
        coffees.add(coffee);
        return Optional.of(coffee);

    }

    public List<Coffee> getProducedCoffee() {
        return coffees;
    }

    public Map<String, Integer> getResources() {
        return resources;
    }

    public Map<String, Map<String, Integer>> getResNeededForCoffee() {
        return resNeededForCoffee;
    }



    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        System.out.println(machine.makeCoffee("latte"));  // empty
        machine.addResource("milk", 10);
        machine.addResource("water", 5);
        machine.addResource("milk", 7);
        System.out.println(machine.getResources());
        machine.addFlavourResourceRequirement("capu", "milk", 9);
        machine.addFlavourResourceRequirement("capu", "liiv", 4);
        machine.addFlavourResourceRequirement("latte", "milk", 3);
        machine.addFlavourResourceRequirement("latte", "water", 1);
        //System.out.println(machine.makeCoffee("latte"));  // Coffee object
        machine.addFlavourResourceRequirement("latte", "water", 3);
        System.out.println(machine.getResNeededForCoffee());
        //System.out.println(machine.makeCoffee("latte"));  // Coffee object (now latte requires 4 water)
        //System.out.println(machine.makeCoffee("latte"));  // empty  - no more water
    }
}
