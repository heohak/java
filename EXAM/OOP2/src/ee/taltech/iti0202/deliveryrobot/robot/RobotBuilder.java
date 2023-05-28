package ee.taltech.iti0202.deliveryrobot.robot;

public class RobotBuilder {
    private String name;
    private int maxWeight;

    /**
     * Sets the name of the Robot.
     *
     * @param name
     * @return RoboBuilder
     */
    public RobotBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the maximum weight the robot can carry.
     *
     * @param maxWeight
     * @return RobotBuilder
     */
    public RobotBuilder setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }

    /**
     * Creates a new Robot object.
     *
     * @return Robot
     */

    public Robot createRobot() {
        return new Robot(name, maxWeight);
    }
}
