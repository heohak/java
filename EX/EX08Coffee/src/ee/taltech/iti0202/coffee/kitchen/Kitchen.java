package ee.taltech.iti0202.coffee.kitchen;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Kitchen {

    private List<Machine> machines = new ArrayList<>();

    private static final Logger logger = Logger.getLogger(Kitchen.class.getName());



    public Kitchen() {
    }

    public void addMachine(Machine machine) {
        if (!machines.contains(machine)) {
            machines.add(machine);
        }
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public Drink.DrinkType makeCoffee(Machine machine, Drink drink) {
        return null;


    }
}
