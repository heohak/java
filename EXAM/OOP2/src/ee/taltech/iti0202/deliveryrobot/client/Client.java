package ee.taltech.iti0202.deliveryrobot.client;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.order.Order;

public class Client {

    String name;
    double balance;

    public Client(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void placeOrder(Company company, Order order) {
        if (!company.getOrders().contains(order)) {
            company.getOrders().add(order);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
