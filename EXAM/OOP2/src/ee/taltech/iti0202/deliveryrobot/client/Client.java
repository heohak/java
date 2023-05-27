package ee.taltech.iti0202.deliveryrobot.client;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.order.Order;

public class Client {

    String name;

    public Client(String name) {
        this.name = name;
    }

    public void placeOrder(Company company, Order order) {
        if (!company.getOrders().contains(order)) {
            company.getOrders().add(order);
        }
    }
}
