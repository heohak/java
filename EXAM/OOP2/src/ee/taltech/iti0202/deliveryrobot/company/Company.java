package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.exceptions.RobotAlreadyInACompanyException;
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

    public void AddProductToRobot(Robot robot, Product product) {
        if (!robot.getProducts().contains(product) && product.getWeight() < robot.getMaxWeight() && robot.getStatus().equals(RobotStatus.IDLE)) {
            robot.getProducts().add(product);
        }
    }

    public void sendRobotToFillOrder(Robot robot) {
        if (robot.getProducts().size() > 0) {
            robot.setStatus(RobotStatus.ACTIVE);
            sentOutProducts.addAll(robot.getProducts());

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
}
