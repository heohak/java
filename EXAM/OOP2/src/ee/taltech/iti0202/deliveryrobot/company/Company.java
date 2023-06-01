package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.exceptions.CantAddItemToRobotException;
import ee.taltech.iti0202.deliveryrobot.exceptions.CantSendOutRobotException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoFreeRobotsException;
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

    private Map<Product, Integer> productOrderCounts = new HashMap<>();

    private List<Robot> robots = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private List<Product> sentOutProducts = new ArrayList<>();

    private List<Order> orders = new ArrayList<>();

    protected final double transportFee = 5;

    private int clientOrderCount;

    /**
     * Company class.
     */
    public Company() {
    }

    /**
     * Adds a robot to the company if it is not in a company already.
     *
     * @param robot
     * @throws RobotAlreadyInACompanyException
     */
    public void addRobot(Robot robot) throws RobotAlreadyInACompanyException {
        if (!robot.isInACompany()) {
            robots.add(robot);
            robot.setInACompany(true);
        } else {
            throw new RobotAlreadyInACompanyException();
        }
    }

    /**
     * Adds a product to the company if it is not already present.
     *
     * @param product
     */
    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }

    /**
     * Adds a product to a robot if certain conditions are met.
     *
     * @param robot
     * @param product
     * @throws CantAddItemToRobotException
     */
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
        } else {
            throw new CantAddItemToRobotException();
        }
    }

    /**
     * Sends a robot to deliver its package if certain conditions are met.
     *
     * @param robot
     * @throws CantSendOutRobotException
     */
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

    /**
     * Returns a list of the company's products.
     *
     * @return List
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Returns a list of products that have been sent out for delivery.
     *
     * @return List
     */
    public List<Product> getSentOutProducts() {
        return sentOutProducts;
    }

    /**
     *
     * @return int
     */
    public int getClientOrderCount() {
        return clientOrderCount;
    }

    /**
     * Returns a list of the company's robots.
     *
     * @return List
     */



    public List<Robot> getRobots() {
        return robots;
    }

    /**
     * Returns a list of the company's orders.
     *
     * @return List
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Returns a map of the company's products and their order counts.
     *
     * @return Map
     */
    public Map<Product, Integer> getProductOrderCounts() {
        return productOrderCounts;
    }

    /**
     * Returns a map of the company's robots and their statuses.
     *
     * @return Map
     */
    public Map<Integer, RobotStatus> getRobotsByStatus() {
        Map<Integer, RobotStatus> robotStatuses = new HashMap<>();
        for (Robot robot : robots) {
            robotStatuses.put(robot.getId(), robot.getStatus());
        }
        return robotStatuses;
    }

    /**
     * Processes all orders of the company, assigning free robots to orders and sending out robots to deliver.
     *
     * @throws CantSendOutRobotException
     * @throws NoFreeRobotsException
     */
    public void processOrder() throws CantSendOutRobotException, NoFreeRobotsException {
        for (Order order : orders) {
            if (order.isCompleted()) continue;
            boolean freeRobotFound = false;
            for (Robot robot : robots) {
                if (robot.getStatus() == RobotStatus.IDLE) {
                    freeRobotFound = true;
                    List<Product> remainingProducts = new ArrayList<>();
                    double orderValue = order.getOrderValue();

                    for (Product product : order.getOrderProducts()) {
                        if (product.getWeight() + robot.getCurrentWeight() <= robot.getMaxWeight()) {
                            robot.getProducts().add(product);
                            robot.setStatus(RobotStatus.ACTIVE);
                            robot.setCurrentWeight(robot.getCurrentWeight() + product.getWeight());
                        } else {
                            remainingProducts.add(product);
                            clientOrderCount++;
                        }
                    }
                    order.setOrderProducts(remainingProducts);
                    if (order.getOrderProducts().isEmpty()) {
                        order.setCompleted(true);
                        order.getClient().setBalance(order.getClient().getBalance() - orderValue - transportFee);
                    }
                    if (!robot.getProducts().isEmpty()) {
                        sendRobotToDeliverPackage(robot);
                        clientOrderCount++;
                    }
                    break;
                }
            }
            if (!freeRobotFound) {
                throw new NoFreeRobotsException();
            }
        }
    }

    /**
     *
     * @param name
     * @return List
     */
    public  List<Robot> searchRobotByName(String name) {
        List<Robot> result = new ArrayList<>();
        for (Robot robot : robots) {
            if (robot.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(robot);

            }

        }
        return result;

    }

    /**
     *
     * @param id
     * @return List
     */
    public  List<Robot> searchRobotByID(int id) {
        List<Robot> result = new ArrayList<>();
        for (Robot robot : robots) {
            if (robot.getId() == id) {
                result.add(robot);
            }

        }
        return result;

    }



}
