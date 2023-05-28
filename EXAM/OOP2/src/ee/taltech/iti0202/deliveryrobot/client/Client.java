package ee.taltech.iti0202.deliveryrobot.client;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.order.Order;
import ee.taltech.iti0202.deliveryrobot.product.Product;
public class Client {
    private String name;
    private double balance;

    /**
     * Creates a new Client with the given name and balance.
     *
     * @param name    The client's name.
     * @param balance The client's balance.
     */
    public Client(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     * Places an order with a company. If the company does not already contain the order,
     * the order is added and the product order counts are updated.
     *
     * @param company The company with which the order is placed.
     * @param order   The order to be placed.
     */
    public void placeOrder(Company company, Order order) {
        if (!company.getOrders().contains(order)) {
            company.getOrders().add(order);
            for (Product product : order.getOrderProducts()) {
                company.getProductOrderCounts().put(product, company.getProductOrderCounts()
                        .getOrDefault(product, 0) + 1);
            }
        }
    }

    /**
     * Gets the client's name.
     *
     * @return The client's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the client's name.
     *
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the client's balance.
     *
     * @return The client's balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the client's balance.
     *
     * @param balance The new balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
