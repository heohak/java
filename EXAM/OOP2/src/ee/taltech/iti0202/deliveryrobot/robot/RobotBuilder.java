package ee.taltech.iti0202.deliveryrobot.robot;

public class RobotBuilder {
    private String name;
    private int maxWeight;

    public RobotBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RobotBuilder setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }



    public Robot createRobot() {
        return new Robot(name, maxWeight);
    }
}
