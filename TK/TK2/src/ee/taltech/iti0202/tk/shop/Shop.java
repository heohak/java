package ee.taltech.iti0202.tk.shop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Shop {

List<Product> products = new ArrayList<>();
    public boolean addProduct(Product product) {
        if (products.contains(product)) {
            return false;
        }
        if (product.getPrice() < 0) {
            return false;
        }
        products.add(product);
        return true;

    }

    public Optional<Product> sellProduct(String name, int maxPrice) {
        products
                .stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
        for (Product p : products) {
            if(p.getName().equals(name) && p.getPrice() <= maxPrice) {
                products.remove(p);
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public List<Product> getProducts() {
        return products;
    }
}
