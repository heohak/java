package ee.taltech.iti0202.computerbuilder.components;

import java.math.BigDecimal;

public class Component {
    private static int nextId = 0;

    private  int id;
    private String name;
    private Type type;
    private BigDecimal price;
    private int amount = 1;
    private String manufacturer;
    private int performancePoints;
    private int powerConsumption;


    public enum Type {
        CPU, GPU, RAM, MOTHERBOARD, HDD, SSD, PSU, CASE, KEYBOARD, TOUCHPAD, SCREEN, BATTERY
    }

    public Component(String name, Type type, BigDecimal price, String manufacturer, int performancePoints,
                     int powerConsumption) {
        this.id = nextId++;
        this.name = name;
        this.type = type;
        this.price = price;
        this.manufacturer = manufacturer;
        this.performancePoints = performancePoints;
        this.powerConsumption = powerConsumption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPerformancePoints() {
        return performancePoints;
    }

    public void setPerformancePoints(int performancePoints) {
        this.performancePoints = performancePoints;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }
    public static void resetID() {
        nextId = 0;

    }

}
