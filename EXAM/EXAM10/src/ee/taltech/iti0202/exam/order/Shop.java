package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.text.rtf.RTFAttributes.BooleanAttribute.False;
import static javax.swing.text.rtf.RTFAttributes.BooleanAttribute.True;

public class Shop {

    int id = 1;

    List<Product> products = new ArrayList<>();

    List<Order> orders = new ArrayList<>();

    List<Order> contrabandList = new ArrayList<>();

    public Shop() {
    }

    public boolean addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
            return true;
        }
        return false;
    }

    public int createNewOrder() {
        Order order = new Order();

        orders.add(order);


        return order.getId();
    }

    public boolean addProductToOrder(int orderNumber, String itemName) {
        for (Order order : contrabandList) {
            if (order.getId() == orderNumber) {
                return false;
            }
        }
        boolean orderFound = false;
        boolean productFound = false;
        for (Order order : orders) {
            if (order.getId() == orderNumber) {
                orderFound = true;
                for (Product product : products) {
                    if (product.getName().equals(itemName)) {
                        productFound = true;
                        order.getOrderProducts().add(product);
                        products.remove(product);
                        return true;
                    }
                }
            }
        }
            return false;
    }

    public int getOrderSum(int orderNumber) {
        int result = 0;
        for (Order order : orders) {
            if (order.getId() == orderNumber) {
                result = order.getOrderSum();
            }
        }
        return result;
    }
    public boolean cancelOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getId() == orderNumber) {
                for (Product prod : order.getOrderProducts()) {
                    products.add(prod);
                }
                order.getOrderProducts().clear();
                contrabandList.add(order);
                return true;
            }
        }
        return false;
    }

    public List<Product> getAvailableProducts() {
        return products;
    }




    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.createNewOrder();
        shop.createNewOrder();
        System.out.println(shop.orders.get(0).getId());
        System.out.println(shop.orders.get(1).getId());
    }
}
