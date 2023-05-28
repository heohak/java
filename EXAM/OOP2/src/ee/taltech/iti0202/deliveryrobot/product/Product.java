package ee.taltech.iti0202.deliveryrobot.product;

public class Product {

    private double weight;

    private double price;

    private String name;

    /**
     * Creates a Product object with the specified name, weight, and price.
     *
     * @param name
     * @param weight
     * @param price
     */
    public Product(String name, double weight, double price) {
        this.weight = weight;
        this.price = price;
        this.name = name;
    }

    /**
     * Returns the weight of the product.
     *
     * @return double
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the product.
     *
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns the price of the product.
     *
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the name of the product.
     *
     * @return String
     */
    public String getName() {
        return name;
    }
}
