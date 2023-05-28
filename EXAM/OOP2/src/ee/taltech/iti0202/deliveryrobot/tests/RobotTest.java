package ee.taltech.iti0202.deliveryrobot.tests;

import ee.taltech.iti0202.deliveryrobot.robot.Robot;
import ee.taltech.iti0202.deliveryrobot.robot.RobotStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RobotTest {

    @BeforeEach
    void resetRobotId() throws NoSuchFieldException, IllegalAccessException {
        Field field = Robot.class.getDeclaredField("nextId");
        field.setAccessible(true);
        field.set(null, 1); // Reset nextId to 1
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

    @Test
    void testRobotId() {
        Robot robot1 = new Robot("robot1", 100);
        Robot robot2 = new Robot("robot2", 200);

        assertEquals(1, robot1.getId());
        assertEquals(2, robot2.getId());
    }

}
