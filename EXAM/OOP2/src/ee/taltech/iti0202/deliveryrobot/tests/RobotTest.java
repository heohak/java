package ee.taltech.iti0202.deliveryrobot.tests;

import ee.taltech.iti0202.deliveryrobot.robot.Robot;
import ee.taltech.iti0202.deliveryrobot.robot.RobotBuilder;
import ee.taltech.iti0202.deliveryrobot.robot.RobotStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RobotTest {
    Robot robot1;

    Robot robot2;

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


    }
    @Test
    public void testRobotId() {
        assertEquals(1, robot1.getId());
        assertEquals(2, robot2.getId());
    }

    @Test
    public void testConstructor() {
        Robot robot = new Robot("Robo1", 200);
        assertEquals("Robo1", robot.getName());
        assertEquals(200, robot.getMaxWeight());
        assertEquals(RobotStatus.IDLE, robot.getStatus());
        assertFalse(robot.isInACompany());
        assertEquals(0, robot.getCurrentWeight());
        assertTrue(robot.getProducts().isEmpty());
    }

    @Test
    public void testSetStatus() {
        Robot robot = new Robot("Robo1", 200);
        robot.setStatus(RobotStatus.ACTIVE);
        assertEquals(RobotStatus.ACTIVE, robot.getStatus());
    }

    @Test
    public void testSetInACompany() {
        Robot robot = new Robot("Robo1", 200);
        robot.setInACompany(true);
        assertTrue(robot.isInACompany());
    }
}
