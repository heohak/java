package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int money;
    private List<Book> books = new ArrayList<>();

    /**
     *
     * @param name
     * @param money
     */
    public Person(String name, int money) {
        this.name = name;
        this.money = money;

    }

    public List<Book> getBooks() {
        return books;
    }

    /**
     *
     * @return int
     */
    public int getMoney() {
        return this.money;
    }

    /**
     *
     * @param sum
     */
    public void addMoney(int sum) {
        this.money = this.money + sum;
    }

    /**
     *
     * @param sum
     */
    public void takeMoney(int sum) {
        this.money = this.money - sum;
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param book
     * @return boolean
     */
    public boolean buyBook(Book book) {
        if (book == null) {
            return false;
        }
        if (book.getOwner() == null && this.getMoney() >= book.getPrice()) {
            book.setOwner(this);
            this.takeMoney(book.getPrice());
            this.books.add(book);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param book
     * @return boolean
     */
    public boolean sellBook(Book book) {
        if (book == null || book.getOwner() == null) {
            return false;
        }
        if (this == book.getOwner()) {
            this.addMoney(book.getPrice());
            this.books.remove(book);
            book.setOwner(null);
            return true;
        } else {
            return false;
        }
    }
}
