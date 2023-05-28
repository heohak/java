package ee.taltech.iti0202.deliveryrobot.order;

import ee.taltech.iti0202.deliveryrobot.client.Client;
import ee.taltech.iti0202.deliveryrobot.product.Product;

import java.util.List;

public class Order {

    private boolean completed;

    private Client client;

    private List<Product> orderProducts;

    /**
     * Represents an order made by a client. Each order is composed of a list of products.
     *
     * @param orderProducts
     * @param client
     */
    public Order(List<Product> orderProducts, Client client) {
        this.orderProducts = orderProducts;
        this.completed = false;
        this.client = client;
    }

    /**
     * Calculates the total weight of all products in the order.
     *
     * @return double
     */
    public double getOrderWeight() {
        double result = 0;
        for (Product p : orderProducts) {
            result = result + p.getWeight();

        }
        return result;
    }

    /**
     * Calculates the total value of all products in the order.
     *
     * @return double
     */
    public double getOrderValue() {
        double result = 0;
        for (Product p : orderProducts) {
            result = result + p.getPrice();
        }
        return result;
    }

    /**
     * Checks if the order has been completed.
     *
     * @return boolean
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion status of the order.
     *
     * @param completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns the list of products in the order.
     * @return List
     */
    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    /**
     * Sets the list of products for the order.
     *
     * @param orderProducts
     */
    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    /**
     * Returns the client who made the order.
     *
     * @return Client
     */
    public Client getClient() {
        return client;
    }
}
