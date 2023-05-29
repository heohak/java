package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;
import java.util.List;

public class Order {

    List<Product> orderProducts = new ArrayList<>();

    private int id;


    public Order(int id) {
        this.id = id;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public int getId() {
        return id;
    }

    public int getOrderSum() {
        int result = 0;
        for (Product prod : orderProducts) {
            result = result + prod.getPrice();

        }
        return result;
    }
}
