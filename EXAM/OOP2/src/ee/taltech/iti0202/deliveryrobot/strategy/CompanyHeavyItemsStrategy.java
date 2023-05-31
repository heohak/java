package ee.taltech.iti0202.deliveryrobot.strategy;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.exceptions.CantSendOutRobotException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoFreeRobotsException;
import ee.taltech.iti0202.deliveryrobot.order.Order;
import ee.taltech.iti0202.deliveryrobot.product.Product;
import ee.taltech.iti0202.deliveryrobot.robot.Robot;
import ee.taltech.iti0202.deliveryrobot.robot.RobotStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompanyHeavyItemsStrategy extends Company {

    @Override
    public void processOrder() throws CantSendOutRobotException, NoFreeRobotsException {
        for (Order order : getOrders()) {
            if (order.isCompleted()) continue;
            boolean freeRobotFound = false;
            for (Robot robot : getRobots()) {
                if (robot.getStatus() == RobotStatus.IDLE) {
                    freeRobotFound = true;
                    List<Product> remainingProducts = new ArrayList<>();
                    double orderValue = order.getOrderValue();

                    // Sort the products in the order by weight
                    order.getOrderProducts().sort(Comparator.comparing(Product::getWeight).reversed());

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
                        order.getClient().setBalance(order.getClient().getBalance() - orderValue - transportFee);
                    }
                    if (!robot.getProducts().isEmpty()) {
                        sendRobotToDeliverPackage(robot);
                    }
                    break;
                }
            }
            if (!freeRobotFound) {
                throw new NoFreeRobotsException();
            }
        }
    }
}
