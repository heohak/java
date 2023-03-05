package ee.taltech.iti0202.tk.shop;

public class Product {
    private String name;
    private int price;

    /**
     *
     * @param name
     * @param price
     */
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     *
     * @param price
     */
    public Product(int price) {
        this.price = price;
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return int
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        if (name == null) {
            return "(" + price + ")";
        }
        return name + " " + "(" + price + ")";
    }
}
