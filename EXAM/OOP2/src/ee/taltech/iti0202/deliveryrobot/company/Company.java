package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.exceptions.*;
import ee.taltech.iti0202.deliveryrobot.order.Order;
import ee.taltech.iti0202.deliveryrobot.product.Product;
import ee.taltech.iti0202.deliveryrobot.robot.Robot;
import ee.taltech.iti0202.deliveryrobot.robot.RobotStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {

    private List<Robot> robots = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private List<Product> sentOutProducts = new ArrayList<>();

    private List<Order> orders = new ArrayList<>();

    private final double transportFee = 5;


    public Company() {
    }

    public void addRobot(Robot robot) throws RobotAlreadyInACompanyException {
        if (!robot.isInACompany()) {
            robots.add(robot);
            robot.setInACompany(true);
        }
        else {
            throw new RobotAlreadyInACompanyException();
        }
    }

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }

    public void addProductToRobot(Robot robot, Product product) throws CantAddItemToRobotException {
        if (!robot.getProducts().contains(product)
                && product.getWeight() < robot.getMaxWeight()
                && robot.getStatus().equals(RobotStatus.IDLE)
                && robots.contains(robot)
                && products.contains(product)) {
            robot.getProducts().add(product);
            products.remove(product);
            robot.setCurrentWeight(robot.getCurrentWeight() + product.getWeight());
            robot.setStatus(RobotStatus.ACTIVE);
        }
        else {
            throw new CantAddItemToRobotException();
        }
    }

    public void sendRobotToDeliverPackage(Robot robot) throws CantSendOutRobotException {
        if (robot.getProducts().size() > 0 && robot.getStatus().equals(RobotStatus.ACTIVE)) {
            sentOutProducts.addAll(robot.getProducts());
            robot.getProducts().clear();
            robot.setCurrentWeight(0);
            robot.setStatus(RobotStatus.IDLE);

        } else {
            throw new CantSendOutRobotException();
        }

    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> getSentOutProducts() {
        return sentOutProducts;
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Map<Integer, RobotStatus> getRobotsByStatus() {
        Map<Integer,RobotStatus> robotStatuses = new HashMap<>();
        for (Robot robot : robots) {
            robotStatuses.put(robot.getId(), robot.getStatus());
        }
        return robotStatuses;
    }
    public void reset() {
        this.products.clear();
        this.robots.clear();
        this.orders.clear();
    }

    public void processOrder() throws CantSendOutRobotException, NoFreeRobotsException {
        for (Order order : orders) {
            if (order.isCompleted()) continue;
            for (Robot robot : robots) {
                if (robot.getStatus() == RobotStatus.IDLE) {
                    List<Product> remainingProducts = new ArrayList<>();
                    double orderValue = order.getOrderValue();  // Move this up before clearing products
                    for (Product product : order.getOrderProducts()) {
                        if (product.getWeight() + robot.getCurrentWeight() <= robot.getMaxWeight()) {
                            robot.getProducts().add(product);
                            robot.setStatus(RobotStatus.ACTIVE);
                            robot.setCurrentWeight(robot.getCurrentWeight() + product.getWeight());
                        } else {
                            remainingProducts.add(product);
                        }
                    }
                    order.setOrderProducts(remainingProducts);
                    if (order.getOrderProducts().isEmpty()) {
                        order.setCompleted(true);
                        order.getClient().setBalance(order.getClient().getBalance() - orderValue - transportFee);  // Use calculated orderValue
                    }
                    if (!robot.getProducts().isEmpty()) {
                        sendRobotToDeliverPackage(robot);
                    }
                } else {
                    throw new NoFreeRobotsException();
                }
            }
        }
    }
}
