package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


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

    public List<Product> SortByCheapest(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
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
                for (Product product : SortByCheapest(products)) {
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
        int result = -1;
        for (Order order : orders) {
            if (order.getId() == orderNumber) {
                result = order.getOrderSum();
                return result;
            }
        }

        return result;
    }
    public boolean cancelOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getId() == orderNumber && !contrabandList.contains(order)) {
                for (Product prod : order.getOrderProducts()) {
                    products.add(prod);
                }
                order.getOrderProducts().clear();
                contrabandList.add(order);
                orders.remove(order);
                return true;
            }
        }
        return false;
    }

    public List<Product> getAvailableProducts() {
        return products;
    }


    public static void main(String[] args) {

        Shop topShop = new Shop();
        Product p1 = new Product("TV", 100);
        Product p2 = new Product("Radio", 60);
        Product p3 = new Product("TV", 90);
        topShop.addProduct(p1);
        topShop.addProduct(p2);
        topShop.addProduct(p3);

        int orderNumber1 = topShop.createNewOrder();
        System.out.println(orderNumber1); // 1
        System.out.println(topShop.addProductToOrder(orderNumber1, "Radio")); // true
        System.out.println(topShop.addProductToOrder(orderNumber1, "Cheese")); // false
        System.out.println(topShop.addProductToOrder(orderNumber1, "Radio")); // false

        Product p4 = new Product("TV", 100);
        System.out.println(topShop.addProduct(p3)); // false - same product instance not allowed
        System.out.println(topShop.addProduct(p4)); // true - another product with same values is allowed

        int orderNumber2 = topShop.createNewOrder();
        System.out.println(orderNumber2); // 2
        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // true
        System.out.println(topShop.getOrderSum(orderNumber2)); // 90
        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // true
        System.out.println(topShop.getOrderSum(orderNumber2)); // 190
        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // true
        System.out.println(topShop.getOrderSum(orderNumber2)); // 290
        System.out.println(topShop.addProductToOrder(orderNumber2, "TV")); // false
        System.out.println(topShop.getOrderSum(orderNumber2)); // 290

// cancel order
        topShop.cancelOrder(orderNumber1); // free all the products from order 1
        int orderNumber3 = topShop.createNewOrder();
        System.out.println(orderNumber3); // 3
        System.out.println(topShop.addProductToOrder(orderNumber3, "Radio")); // true
        System.out.println(topShop.getOrderSum(orderNumber3)); // 60
    }
}
