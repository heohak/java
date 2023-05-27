package ee.taltech.iti0202.deliveryrobot.tests;

import ee.taltech.iti0202.deliveryrobot.robot.Robot;
import ee.taltech.iti0202.deliveryrobot.robot.RobotBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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




}