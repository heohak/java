package ee.taltech.iti0202.deliveryrobot.tests;

import ee.taltech.iti0202.deliveryrobot.client.Client;
import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.order.Order;
import ee.taltech.iti0202.deliveryrobot.product.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientTest {
    @Test
    public void testConstructor() {
        Client client = new Client("client1", 100.0);

        assertEquals("client1", client.getName());
        assertEquals(100.0, client.getBalance());
    }

    @Test
    public void testPlaceOrder() {
        Client client = new Client("client2", 100.0);
        Company company = new Company();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Apple", 1.0, 0.5));
        Order order = new Order(products, client);

        client.placeOrder(company, order);

        assertTrue(company.getOrders().contains(order));
        assertTrue(company.getProductOrderCounts().containsKey(products.get(0)));
    }

    @Test
    public void testSetName() {
        Client client = new Client("client3", 100.0);
        client.setName("Jane Doe");

        assertEquals("Jane Doe", client.getName());
    }

    @Test
    public void testSetBalance() {
        Client client = new Client("client4", 100.0);
        client.setBalance(200.0);

        assertEquals(200.0, client.getBalance());
    }

}
