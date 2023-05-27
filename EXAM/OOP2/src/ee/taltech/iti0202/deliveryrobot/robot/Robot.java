package ee.taltech.iti0202.deliveryrobot.robot;

import ee.taltech.iti0202.deliveryrobot.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private String name;

    private static int nextId = 0;

    private  int id;

    private int maxWeight;

    private RobotStatus status;

    private boolean isInACompany;

    private List<Product> products = new ArrayList<>();

    public Robot(String name, int maxWeight) {
        this.name = name;
        this.id = nextId++;
        this.maxWeight = maxWeight;
        this.status = RobotStatus.IDLE;
        this.isInACompany = false;
    }

    public String getName() {
        return name;
    }

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public RobotStatus getStatus() {
        return status;
    }

    public boolean isInACompany() {
        return isInACompany;
    }

    public void setStatus(RobotStatus status) {
        this.status = status;
    }

    public void setInACompany(boolean inACompany) {
        isInACompany = inACompany;
    }

    public List<Product> getProducts() {
        return products;
    }
}
