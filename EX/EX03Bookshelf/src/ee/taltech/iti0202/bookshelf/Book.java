package ee.taltech.iti0202.bookshelf;
public class Book {
    private String title;
    private String author;
    private int yearOfPublishing;
    private int price;
    private static int count = 0;
    private Person owner;
    private int id;

    public static int getAndIncrementNextId() {
        return count++;
    }

    /**
     *
     * @param title
     * @param author
     * @param yearOfPublishing
     * @param price
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.id = getAndIncrementNextId();
        this.owner = null;

    }

    /**
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     *
     * @return String
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     *
     * @return int
     */
    public int getYearOfPublishing() {
        return this.yearOfPublishing;
    }

    /**
     *
     * @return Person
     */
    public Person getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     *
     * @return int
     */
    public int getPrice() {
        return this.price;
    }

    /**
     *
     * @return int
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * @param buyer
     * @return boolean
     */
    public boolean buy(Person buyer) {
        if (buyer == null) {
            if (owner != null) {
                owner.addMoney(this.price);
                this.setOwner(null);
                return true;
            }
        } else if (owner == null) {
            if (buyer.buyBook(this)) {
                this.setOwner(buyer);
                return true;
            }
        } else if (buyer == this.owner || buyer.getMoney() < this.price) {
            return false;

    } else if (owner.sellBook(this) && buyer.buyBook(this)) {
        owner.addMoney(this.price);
        buyer.takeMoney(this.price);
        this.setOwner(buyer);
        return true;

        }
        return true;

    }

}
