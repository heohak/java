package ee.taltech.iti0202.deliveryrobot.order;

import ee.taltech.iti0202.deliveryrobot.client.Client;
import ee.taltech.iti0202.deliveryrobot.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private boolean completed;

    private Client client;

    private List<Product> orderProducts = new ArrayList<>();

    public Order(List<Product> orderProducts, Client client) {
        this.orderProducts = orderProducts;
        this.completed = false;
        this.client = client;
    }

    public double getOrderWeight() {
        double result = 0;
        for (Product p : orderProducts) {
            result = result + p.getWeight();

        }
        return result;
    }

    public double getOrderValue() {
        double result = 0;
        for (Product p : orderProducts) {
            result = result + p.getPrice();
        }
        return result;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Client getClient() {
        return client;
    }
}
