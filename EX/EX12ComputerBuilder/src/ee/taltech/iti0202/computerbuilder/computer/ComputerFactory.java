package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.CantMakeComputerException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComputerFactory {
    private static Database database = Database.getInstance();

    public ComputerFactory() {

    }

    public static List<Component> getAvailableComponents() {
        return database.getComponents().values().stream()
                .filter(component -> component.getAmount() > 0)
                .toList();
    }

    public static Component getBestComponent(float maxPrice, Component.Type type) throws CantMakeComputerException {
        return getAvailableComponents().stream()
                .filter(component -> component.getType().equals(type))
                .filter(component -> component.getPrice().floatValue() <= maxPrice)
                .max(Comparator.comparing(Component::getPerformancePoints))
                .orElseThrow(CantMakeComputerException::new);
    }

    public static Component getPsu(float maxPrice, List<Component> components) throws CantMakeComputerException {
        int requiredPower = components.stream().mapToInt(Component::getPowerConsumption).sum();

        return getAvailableComponents().stream()
                .filter(component -> component.getType().equals(Component.Type.PSU))
                .filter(component -> component.getPrice().floatValue() <= maxPrice)
                .filter(component -> component.getPowerConsumption() >= requiredPower)
                .max(Comparator.comparing(Component::getPerformancePoints))
                .orElseThrow(CantMakeComputerException::new);
    }

    public static Component getHddOrSsd(float maxPrice) throws CantMakeComputerException {
        return getAvailableComponents().stream()
                .filter(component -> component.getType().equals(Component.Type.SSD)
                        || component.getType().equals(Component.Type.HDD))
                .filter(component -> component.getPrice().floatValue() <= maxPrice)
                .max(Comparator.comparing(Component::getPerformancePoints))
                .orElseThrow(CantMakeComputerException::new);
    }

    public static Computer order(Computer.Type computerType, Computer.UseCase useCase, Customer customer)
            throws CantMakeComputerException {
        List<Component> result = new ArrayList<>();
        int budget = customer.getBalance().intValue();
        float[] maxPrices = useCase == Computer.UseCase.GAMING ? new float[]{1.0f / 3, 1.0f / 4, 1.0f / 12}
                : new float[]{1.0f / 4, 1.0f / 3, 1.0f / 12};

        result.add(getBestComponent(budget * maxPrices[0], Component.Type.GPU));
        result.add(getBestComponent(budget * maxPrices[1], Component.Type.CPU));
        result.add(getBestComponent(budget / 12, Component.Type.MOTHERBOARD));
        result.add(getBestComponent(budget / 12, Component.Type.RAM));
        result.add(getBestComponent(budget / 12, Component.Type.CASE));
        result.add(getHddOrSsd(budget / 12));

        if (computerType == Computer.Type.DESKTOP) {
            result.add(getPsu(budget * maxPrices[2], result));
            Desktop desktop = new Desktop(result);
            customer.addComputer(desktop);
            return desktop;
        } else {
            result.add(getBestComponent(budget / 12, Component.Type.KEYBOARD));
            result.add(getBestComponent(budget / 12, Component.Type.SCREEN));
            result.add(getBestComponent(budget / 12, Component.Type.TOUCHPAD));
            result.add(getBestComponent(budget / 12, Component.Type.BATTERY));
            result.add(getPsu(budget * maxPrices[2], result));
            Laptop laptop = new Laptop(result);
            customer.addComputer(laptop);
            return laptop;
        }
    }
}
