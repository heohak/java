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

    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.id = getAndIncrementNextId();
        this.owner = null;

    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYearOfPublishing() {
        return this.yearOfPublishing;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public boolean buy(Person buyer) {

        if (buyer == null) {
            if (owner != null) {
                owner.addMoney(this.price);
                this.setOwner(null);
                return true;
            }


        }
        else if (buyer == this.owner || buyer.getMoney() < this.price) {
            return false;
        }
        else if (owner.sellBook(this) && buyer.buyBook(this)) {
        owner.addMoney(this.price);
        this.setOwner(buyer);
        buyer.takeMoney(this.price);
        }
        return true;

    }

}