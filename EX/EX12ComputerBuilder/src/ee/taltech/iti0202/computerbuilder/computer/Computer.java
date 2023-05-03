package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;

import java.util.List;

public class Computer {

    public enum Type {
        LAPTOP, DESKTOP
    }

    public enum UseCase {
        GAMING, WORK
    }
    protected List<Component> components;

    public Computer(List<Component> components) {
        this.components = components;
    }

    public List<Component> getComponents() {
        return components;
    }
}
