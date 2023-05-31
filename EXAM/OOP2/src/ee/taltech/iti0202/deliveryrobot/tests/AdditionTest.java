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
import ee.taltech.iti0202.deliveryrobot.strategy.CompanyHeavyItemsStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionTest {

    Robot robot1;

    Robot robot2;
    Robot robot3;

    Company company1;

    Company company2;

    Product product1;
    Product product2;

    Product product3;

    Product product4;

    Product product5;
    Order order1;

    Order order2;


    Client client1;


    @BeforeEach

    void resetRobotId() throws NoSuchFieldException, IllegalAccessException {
        Field field = Robot.class.getDeclaredField("nextId");
        field.setAccessible(true);
        field.set(null, 1); // Reset nextId to 1
    }


    @Test
    public void testSearchRobotById() throws RobotAlreadyInACompanyException {
        robot1 = new RobotBuilder()
                .setName("MinU roBsu")
                .setMaxWeight(500)
                .createRobot();
        robot2 = new RobotBuilder()
                .setName("rObsU")
                .setMaxWeight(200)
                .createRobot();
        robot3 = new RobotBuilder()
                .setName("robot3")
                .setMaxWeight(10000)
                .createRobot();
        company1 = new Company();
        company1.addRobot(robot1);
        company1.addRobot(robot2);
        company1.addRobot(robot3);
        List<Robot> resultList = company1.searchRobotByID(1);
        assertEquals(1, resultList.size());
        assertEquals(1, resultList.get(0).getId());



    }

    @Test
    public void testSearchRobotByIdNoRobotWithThatId() throws RobotAlreadyInACompanyException {
        robot1 = new RobotBuilder()
                .setName("MinU roBsu")
                .setMaxWeight(500)
                .createRobot();
        robot2 = new RobotBuilder()
                .setName("rObsU")
                .setMaxWeight(200)
                .createRobot();
        robot3 = new RobotBuilder()
                .setName("robot3")
                .setMaxWeight(10000)
                .createRobot();
        company1 = new Company();
        company1.addRobot(robot1);
        company1.addRobot(robot2);
        company1.addRobot(robot3);
        List<Robot> resultList = company1.searchRobotByID(4);
        assertEquals(0, resultList.size());




    }

    @Test
    public void testSearchRobotByName() throws RobotAlreadyInACompanyException {
        robot1 = new RobotBuilder()
                .setName("MinU roBsu")
                .setMaxWeight(500)
                .createRobot();
        robot2 = new RobotBuilder()
                .setName("rObsU")
                .setMaxWeight(200)
                .createRobot();
        robot3 = new RobotBuilder()
                .setName("robot3")
                .setMaxWeight(10000)
                .createRobot();
        company1 = new Company();
        company1.addRobot(robot1);
        company1.addRobot(robot2);
        company1.addRobot(robot3);
        List<Robot> resultList = company1.searchRobotByName("robsu");
        assertEquals(2, resultList.size());
        assertEquals(robot1, resultList.get(0));
        assertEquals(robot2, resultList.get(1));

    }


    @Test
    public void testSearchRobotByNameMultipleWithSameName() throws RobotAlreadyInACompanyException {
        robot1 = new RobotBuilder()
                .setName("Rob")
                .setMaxWeight(500)
                .createRobot();
        robot2 = new RobotBuilder()
                .setName("ROBSU")
                .setMaxWeight(200)
                .createRobot();
        robot3 = new RobotBuilder()
                .setName("ROBSU")
                .setMaxWeight(10000)
                .createRobot();
        company1 = new Company();
        company1.addRobot(robot1);
        company1.addRobot(robot2);
        company1.addRobot(robot3);
        List<Robot> resultList = company1.searchRobotByName("robsu");
        assertEquals(2, resultList.size());
        assertEquals(robot2, resultList.get(0));
        assertEquals(robot3, resultList.get(1));

    }

    @Test
    public void testHeavyItemsFirstStrategy() throws CantSendOutRobotException,
            NoFreeRobotsException, RobotAlreadyInACompanyException {
        robot1 = new RobotBuilder()
                .setName("robot1")
                .setMaxWeight(30000)
                .createRobot();
        robot2 = new RobotBuilder()
                .setName("robot2")
                .setMaxWeight(5000)
                .createRobot();

        company1 = new CompanyHeavyItemsStrategy();
        company1.addRobot(robot1);
        company1.addRobot(robot2);

        product1 = new Product("Tass", 7, 15);
        product2 = new Product("Pliiats", 5, 50);
        product3 = new Product("Kapp", 600, 125);
        product4 = new Product("Klaver", 1000, 1000);
        List<Product> list1 = new ArrayList<>();
        list1.add(product3);
        list1.add(product2);
        list1.add(product1);
        list1.add(product4);
        client1 = new Client("Harry", 5000);
        order1 = new Order(list1, client1);
        client1.placeOrder(company1, order1);
        company1.processOrder();
        assertEquals("Klaver", company1.getSentOutProducts().get(0).getName());
        assertEquals("Kapp", company1.getSentOutProducts().get(1).getName());
        assertEquals("Tass", company1.getSentOutProducts().get(2).getName());
        assertEquals("Pliiats", company1.getSentOutProducts().get(3).getName());


    }



    @Test
    public void testHeavyItemsFirstStrategyMultipleOrders() throws CantSendOutRobotException, NoFreeRobotsException, RobotAlreadyInACompanyException {
        robot1 = new RobotBuilder()
                .setName("robot1")
                .setMaxWeight(30000)
                .createRobot();
        robot2 = new RobotBuilder()
                .setName("robot2")
                .setMaxWeight(5000)
                .createRobot();

        company1 = new CompanyHeavyItemsStrategy();
        company1.addRobot(robot1);
        company1.addRobot(robot2);

        product1 = new Product("Tass", 7, 15);
        product2 = new Product("Pliiats", 5, 50);
        product3 = new Product("Kapp", 600, 125);
        product4 = new Product("Klaver", 1000, 1000);
        product5 = new Product("Auto", 2500, 4000);
        List<Product> list1 = new ArrayList<>();
        list1.add(product2);
        list1.add(product3);

        List<Product> list2 = new ArrayList<>();
        list2.add(product3);
        list2.add(product5);
        client1 = new Client("Harry", 5000);
        order1 = new Order(list1, client1);
        order2 = new Order(list2, client1);
        client1.placeOrder(company1, order2);
        client1.placeOrder(company1, order1);
        company1.processOrder();
        assertEquals("Auto", company1.getSentOutProducts().get(0).getName());
        assertEquals("Kapp", company1.getSentOutProducts().get(1).getName());
        assertEquals("Kapp", company1.getSentOutProducts().get(2).getName());
        assertEquals("Pliiats", company1.getSentOutProducts().get(3).getName());


    }
    @Test
    public void testWithHowManyDeliversNeededForOrder() throws CantSendOutRobotException, NoFreeRobotsException, RobotAlreadyInACompanyException {
        robot1 = new RobotBuilder()
                .setName("robot1")
                .setMaxWeight(30)
                .createRobot();
        product1 = new Product("P1", 10, 5);
        product2 = new Product("P2", 30, 6);
        product3 = new Product("P3", 10, 7);
        company1 = new Company();
        company1.addRobot(robot1);
        client1 = new Client("Client", 1000);
        List<Product> list1 = new ArrayList<>();
        list1.add(product1);
        list1.add(product2);
        list1.add(product3);
        order1 = new Order(list1, client1);
        client1.placeOrder(company1, order1);
        company1.processOrder();
        assertEquals(2, company1.getClientOrderCount());


    }
}
