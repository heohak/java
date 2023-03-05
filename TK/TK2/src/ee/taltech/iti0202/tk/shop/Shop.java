package ee.taltech.iti0202.tk.shop;

import java.util.*;

public class Shop {
    private List<Product> products = new ArrayList<>();

    boolean addProduct(Product product) {
        if (product.getPrice() < 0) {
            return false;
        }
        for (Product prod : products) {
            if (prod.getName() == product.getName() && prod.getPrice() == product.getPrice()) {
                return false;
            }
        }
        products.add(product);
        return true;
    }

    Optional<Product> sellProduct(String name, int maxPrice) {
        Optional<Product> optionalProduct = products.stream()
                .filter(product -> product.getName() != null && product.getName().equals(name) && product.getPrice() <= maxPrice)
                .max(Comparator.comparingInt(Product::getPrice));
        optionalProduct.ifPresent(products::remove);
        return optionalProduct;


    }

    List<Product> getProducts() {
        return products;
    }
}
