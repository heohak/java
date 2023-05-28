package ee.taltech.iti0202.deliveryrobot.robot;

import ee.taltech.iti0202.deliveryrobot.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private String name;

    private static int nextId = 1;

    private  int id;

    private int maxWeight;

    private RobotStatus status;

    private boolean isInACompany;

    private List<Product> products = new ArrayList<>();

    private double currentWeight;

    /**
     * Creates a Robot object with the specified name and max weight.
     * The robot is initially not in any company, its status is set to IDLE,
     * and it has a unique id which is an incrementing integer.
     *
     * @param name
     * @param maxWeight
     */
    public Robot(String name, int maxWeight) {
        this.name = name;
        this.id = nextId++;
        this.maxWeight = maxWeight;
        this.status = RobotStatus.IDLE;
        this.isInACompany = false;
    }

    /**
     * Returns the name of the robot.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the id of the robot.
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the maximum weight the robot can carry.
     *
     * @return int
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * Returns the current status of the robot.
     *
     * @return RobotStatus
     */
    public RobotStatus getStatus() {
        return status;
    }

    /**
     * Checks if the robot is in a company.
     *
     * @return boolean
     */
    public boolean isInACompany() {
        return isInACompany;
    }

    /**
     * Sets the status of the robot.
     *
     * @param status
     */

    public void setStatus(RobotStatus status) {
        this.status = status;
    }

    /**
     * Sets whether the robot is in a company or not.
     *
     * @param inACompany
     */
    public void setInACompany(boolean inACompany) {
        isInACompany = inACompany;
    }

    /**
     * Returns the list of products the robot is currently carrying.
     *
     * @return List
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Returns the current weight the robot is carrying.
     *
     * @return double
     */
    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Sets the current weight the robot is carrying.
     *
     * @param currentWeight
     */
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }
}
