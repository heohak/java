package ee.taltech.iti0202.deliveryrobot.order;

import ee.taltech.iti0202.deliveryrobot.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Product> orderProducts = new ArrayList<>();

    public Order(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }


}
