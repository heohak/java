package ee.taltech.iti0202.deliveryrobot.tests;

import ee.taltech.iti0202.deliveryrobot.client.Client;
import ee.taltech.iti0202.deliveryrobot.order.Order;
import ee.taltech.iti0202.deliveryrobot.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OrderTest {

    @Test
    public void testConstructor() {
        Client client = new Client("John", 40);
        List<Product> products = new ArrayList<>();
        products.add(new Product("Apple", 1.0, 0.5));
        Order order = new Order(products, client);

        Assertions.assertEquals(client, order.getClient());
        Assertions.assertEquals(products, order.getOrderProducts());
        Assertions.assertFalse(order.isCompleted());
    }

    @Test
    public void testGetOrderWeight() {
        Client client = new Client("John1", 500);
        List<Product> products = new ArrayList<>();
        products.add(new Product("Apple", 1.0, 0.5));
        Order order = new Order(products, client);

        Assertions.assertEquals(1.0, order.getOrderWeight());
    }

    @Test
    public void testGetOrderValue() {
        Client client = new Client("John5", 732);
        List<Product> products = new ArrayList<>();
        products.add(new Product("Apple", 1.0, 0.5));
        Order order = new Order(products, client);

        Assertions.assertEquals(0.5, order.getOrderValue());
    }

    @Test
    public void testSetCompleted() {
        Client client = new Client("John3", 11);
        List<Product> products = new ArrayList<>();
        products.add(new Product("Apple", 1.0, 0.5));
        Order order = new Order(products, client);

        order.setCompleted(true);
        Assertions.assertTrue(order.isCompleted());
    }

    @Test
    public void testSetOrderProducts() {
        Client client = new Client("John43", 43);
        List<Product> products = new ArrayList<>();
        products.add(new Product("Apple", 1.0, 0.5));
        Order order = new Order(products, client);

        List<Product> newProducts = new ArrayList<>();
        newProducts.add(new Product("Banana", 2.0, 1.0));

        order.setOrderProducts(newProducts);
        Assertions.assertEquals(newProducts, order.getOrderProducts());
    }

}
