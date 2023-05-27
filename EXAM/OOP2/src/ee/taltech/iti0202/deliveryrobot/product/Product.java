package ee.taltech.iti0202.deliveryrobot.product;

public class Product {

    private double weight;

    private double price;

    private String name;

    public Product(String name, double weight, double price) {
        this.weight = weight;
        this.price = price;
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }
}
