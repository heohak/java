package ee.taltech.iti0202.deliveryrobot.tests;

import ee.taltech.iti0202.deliveryrobot.client.Client;
import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.exceptions.CantAddItemToRobotException;
import ee.taltech.iti0202.deliveryrobot.exceptions.CantSendOutRobotException;
import ee.taltech.iti0202.deliveryrobot.exceptions.InsufficientBalanceException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoFreeRobotsException;
import ee.taltech.iti0202.deliveryrobot.exceptions.RobotAlreadyInACompanyException;
import ee.taltech.iti0202.deliveryrobot.order.Order;
import ee.taltech.iti0202.deliveryrobot.product.Product;
import ee.taltech.iti0202.deliveryrobot.robot.Robot;
import ee.taltech.iti0202.deliveryrobot.robot.RobotBuilder;
import ee.taltech.iti0202.deliveryrobot.robot.RobotStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompanyTest {
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

    Client client2;

    @BeforeEach
    void setup() {
        robot1 = new RobotBuilder()
                .setName("robot1")
                .setMaxWeight(500)
                .createRobot();
        robot2 = new RobotBuilder()
                .setName("robot2")
                .setMaxWeight(200)
                .createRobot();
        robot3 = new RobotBuilder()
                .setName("robot3")
                .setMaxWeight(10000)
                .createRobot();
        company1 = new Company();
        company2 = new Company();

        product1 = new Product("Tass", 12, 15);
        product2 = new Product("Laud", 100, 50);
        product3 = new Product("pastakas", 5, 5);
        product4 = new Product("Auto", 3000, 1000);
        product5 = new Product("Tool", 75, 50);
        List<Product> list1 = List.of(product1, product2, product3);
        List<Product> list2 = List.of(product5, product3);

        client1 = new Client("Harry", 7000);
        client2 = new Client("Marx", 3000);

        order1 = new Order(list1, client1);
        order2 = new Order(list2, client2);



    }

    @Test
    public void testAddRobotsToCompany() throws RobotAlreadyInACompanyException {
        assertEquals(0, company1.getRobots().size());
        company1.addRobot(robot1);
        company1.addRobot(robot2);
        assertEquals(2, company1.getRobots().size());
    }
    @Test
    public void testCantAddRobotToSameCompany() throws RobotAlreadyInACompanyException {
        company1.addRobot(robot1);
        assertThrows(RobotAlreadyInACompanyException.class, () -> company1.addRobot(robot1));
    }
    @Test
    public void testCantAddRobotThatIsAlreadyInOtherCompany() throws RobotAlreadyInACompanyException {
        company1.addRobot(robot1);
        assertThrows(RobotAlreadyInACompanyException.class, () -> company2.addRobot(robot1));
    }

    @Test
    public void testRobotInWrongStatusCantAddItem() throws RobotAlreadyInACompanyException,
            CantAddItemToRobotException {
        company1.addRobot(robot1);
        company1.addProduct(product2);
        company1.addProduct(product1);
        company1.addProductToRobot(robot1, product1);
        assertThrows(CantAddItemToRobotException.class, () -> company1.addProductToRobot(robot1, product2));

    }


    @Test
    public void testCheckIfRobotStatusChanges() throws RobotAlreadyInACompanyException,
            CantAddItemToRobotException, CantSendOutRobotException {
        company2.addProduct(product1);
        company2.addRobot(robot1);
        assertEquals(RobotStatus.IDLE, robot1.getStatus());
        company2.addProductToRobot(robot1, product1);
        assertEquals(RobotStatus.ACTIVE, robot1.getStatus());
        company2.sendRobotToDeliverPackage(robot1);
        assertEquals(RobotStatus.IDLE, robot1.getStatus());
        robot2.setStatus(RobotStatus.BROKEN);
        assertEquals(RobotStatus.BROKEN, robot2.getStatus());


    }
    @Test
    public void testSendRobotToDeliverAPackage() throws RobotAlreadyInACompanyException,
            CantAddItemToRobotException, CantSendOutRobotException {
        company1.addProduct(product1);
        company1.addRobot(robot1);
        company1.addProductToRobot(robot1, product1);
        assertEquals(0, company1.getProducts().size());
        assertEquals(1, robot1.getProducts().size());
        company1.sendRobotToDeliverPackage(robot1);
        assertEquals(0, company1.getProducts().size());
        assertEquals(0, robot1.getProducts().size());


    }

    @Test
    public void testCantSendRobotOutNoPackageWithRobot() throws RobotAlreadyInACompanyException,
            CantSendOutRobotException {
        company1.addRobot(robot1);
        assertThrows(CantSendOutRobotException.class, () -> company1.sendRobotToDeliverPackage(robot1));

    }
    @Test
    public void testShowWhichRobotsAreInWhichState() throws RobotAlreadyInACompanyException,
            CantAddItemToRobotException {

        company1.addProduct(product1);
        company1.addRobot(robot1);
        company1.addRobot(robot2);
        company1.addRobot(robot3);
        robot3.setStatus(RobotStatus.BROKEN);
        company1.addProductToRobot(robot1, product1);
        assertEquals(3, company1.getRobotsByStatus().size());
        assertEquals(RobotStatus.ACTIVE, company1.getRobotsByStatus().get(robot1.getId()));
        assertEquals(RobotStatus.IDLE, company1.getRobotsByStatus().get(robot2.getId()));
        assertEquals(RobotStatus.BROKEN, company1.getRobotsByStatus().get(robot3.getId()));

    }

    @Test
    public void testShowWhichPackagesAreSentOut() throws RobotAlreadyInACompanyException,
            CantAddItemToRobotException, CantSendOutRobotException {
        company1.addProduct(product1);
        company1.addProduct(product2);
        company1.addProduct(product3);
        company1.addRobot(robot1);
        company1.addProductToRobot(robot1, product1);
        company1.sendRobotToDeliverPackage(robot1);
        company1.addProductToRobot(robot1, product2);
        company1.sendRobotToDeliverPackage(robot1);
        assertEquals(2, company1.getSentOutProducts().size());
        assertEquals("Tass", company1.getSentOutProducts().get(0).getName());
    }

    @Test
    public void testCantAddTooHeavyItemToRobot() throws RobotAlreadyInACompanyException, CantAddItemToRobotException {
        company1.addProduct(product4);
        company1.addRobot(robot2);
        assertThrows(CantAddItemToRobotException.class, () -> company1.addProductToRobot(robot2, product4));


    }
    @Test

    public void testCompleteClientsOrders() throws RobotAlreadyInACompanyException,
            CantSendOutRobotException, NoFreeRobotsException, InsufficientBalanceException {
        company1.addRobot(robot1);
        company1.addRobot(robot2);
        company1.addRobot(robot3);
        client1.placeOrder(company1, order1);
        client2.placeOrder(company1, order2);
        assertFalse(order1.isCompleted());
        company1.processOrder();
        assertTrue(order1.isCompleted());
        assertTrue(order2.isCompleted());

    }
    @Test
    public void testCantCompleteOrderBecauseNoFreeRobots() throws RobotAlreadyInACompanyException,
            CantAddItemToRobotException, CantSendOutRobotException, NoFreeRobotsException {
        company1.addRobot(robot1);
        company1.addProduct(product1);
        client1.placeOrder(company1, order1);
        company1.addProductToRobot(robot1, product1);
        assertThrows(NoFreeRobotsException.class, () -> company1.processOrder());


    }
    @Test
    public void testClientOrderCompletedAndClientLosesMoney() throws RobotAlreadyInACompanyException,
            CantSendOutRobotException, NoFreeRobotsException, InsufficientBalanceException {
        company1.addRobot(robot1);
        assertEquals(7000, client1.getBalance());
        client1.placeOrder(company1, order1);
        company1.processOrder();
        assertTrue(order1.isCompleted());
        assertEquals(6925, client1.getBalance());


    }




}
