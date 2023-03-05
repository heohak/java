package ee.taltech.iti0202.tk.shop;
import java.util.*;

public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        if (name == null) {
            return "(" + price + ")";
        }
        return name + " " + "(" + price + ")";
    }
}
