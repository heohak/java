package ee.taltech.iti0202.tk.shop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Shop {
    List<Product> products = new ArrayList<>();

    /**
     *
     * @param product
     * @return boolean
     */
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

    /**
     *
     * @param name
     * @param maxPrice
     * @return Optional
     */
    public Optional<Product> sellProduct(String name, int maxPrice) {
        Optional<Product> optionalProduct = products.stream()
                .filter(product -> product.getName() != null && product.getName().equals(name) && product
                        .getPrice() <= maxPrice)
                .max(Comparator.comparingInt(Product::getPrice));
        optionalProduct.ifPresent(products::remove);
        return optionalProduct;


    }

    /**
     *
     * @return List
     */
    public List<Product> getProducts() {
        return products;
    }
}
