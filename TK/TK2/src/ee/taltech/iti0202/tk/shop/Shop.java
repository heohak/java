package ee.taltech.iti0202.tk.shop;

import java.util.*;

public class Shop {
    List<Product> products = new ArrayList<>();

    public boolean addProduct(Product product) {
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

    public Optional<Product> sellProduct(String name, int maxPrice) {
        Optional<Product> optionalProduct = products.stream()
                .filter(product -> product.getName() != null && product.getName().equals(name) && product.getPrice() <= maxPrice)
                .max(Comparator.comparingInt(Product::getPrice));
        optionalProduct.ifPresent(products::remove);
        return optionalProduct;


    }

    public List<Product> getProducts() {
        return products;
    }
}
