package ee.taltech.iti0202.deliveryrobot.tests;

import ee.taltech.iti0202.deliveryrobot.client.Client;
import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.exceptions.CantSendOutRobotException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoFreeRobotsException;
import ee.taltech.iti0202.deliveryrobot.exceptions.RobotAlreadyInACompanyException;
import ee.taltech.iti0202.deliveryrobot.order.Order;
import ee.taltech.iti0202.deliveryrobot.product.Product;
import ee.taltech.iti0202.deliveryrobot.robot.Robot;
import ee.taltech.iti0202.deliveryrobot.robot.RobotBuilder;
import ee.taltech.iti0202.deliveryrobot.strategy.CompanyLowWeightsStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyLowWeightsStrategyTest {

    Robot robot1;



    CompanyLowWeightsStrategy company1;


    Product product1;
    Product product2;

    Product product3;

    Product product4;


    Order order1;


    Client client1;


    @BeforeEach
    void setup() {
        robot1 = new RobotBuilder()
                .setName("robot1")
                .setMaxWeight(3000)
                .createRobot();

        company1 = new CompanyLowWeightsStrategy();

        product1 = new Product("Tass", 7, 15);
        product2 = new Product("Pliiats", 5, 50);
        product3 = new Product("Kapp", 600, 125);
        product4 = new Product("Klaver", 1000, 1000);
        List<Product> list1 = new ArrayList<>();
        list1.add(product4);
        list1.add(product3);
        list1.add(product2);
        list1.add(product1);
        client1 = new Client("Harry", 5000);
        order1 = new Order(list1, client1);
    }

    @Test
    public void testLowWeightItemsFirstStrategy() throws RobotAlreadyInACompanyException, CantSendOutRobotException, NoFreeRobotsException {
        company1.addRobot(robot1);
        client1.placeOrder(company1, order1);
        company1.processOrder();
        assertEquals("Pliiats", company1.getSentOutProducts().get(0).getName());
        assertEquals("Tass", company1.getSentOutProducts().get(1).getName());
        assertEquals("Kapp", company1.getSentOutProducts().get(2).getName());
        assertEquals("Klaver", company1.getSentOutProducts().get(3).getName());
    }

}