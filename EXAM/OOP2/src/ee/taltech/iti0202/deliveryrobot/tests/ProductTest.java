package ee.taltech.iti0202.deliveryrobot.tests;

import ee.taltech.iti0202.deliveryrobot.product.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void testConstructor() {
        Product product = new Product("Apple", 1.0, 0.5);
        assertEquals("Apple", product.getName());
        assertEquals(1.0, product.getWeight());
        assertEquals(0.5, product.getPrice());
    }

    @Test
    public void testSetWeight() {
        Product product = new Product("Apple", 1.0, 0.5);
        product.setWeight(2.0);
        assertEquals(2.0, product.getWeight());
    }

    @Test
    public void testSetPrice() {
        Product product = new Product("Apple", 1.0, 0.5);
        product.setPrice(1.0);
        assertEquals(1.0, product.getPrice());
    }

}