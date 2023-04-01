package ee.taltech.iti0202.coffee.kitchen;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoBeansException;
import ee.taltech.iti0202.coffee.exceptions.TankEmptyException;
import ee.taltech.iti0202.coffee.exceptions.TrashIsFullException;
import ee.taltech.iti0202.coffee.machine.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Kitchen {

    private List<Machine> machines = new ArrayList<>();

    private static final Logger LOGGER = Logger.getLogger(Kitchen.class.getName());



    public Kitchen() {
    }

    public void addMachine(Machine machine) {
        if (!machines.contains(machine)) {
            LOGGER.info("Machine has been added to the kitchen.");
            machines.add(machine);
        }
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public Drink.DrinkType makeCoffee(Machine machine, Drink drink) throws NoBeansException, TankEmptyException, TrashIsFullException {
        return machine.makeDrink(drink);


    }
}
