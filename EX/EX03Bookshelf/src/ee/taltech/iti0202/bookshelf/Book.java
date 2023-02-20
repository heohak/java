package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book {
    private String title;
    private String author;
    private int yearOfPublishing;
    private int price;
    private static int count = 0;
    private Person owner;
    private int id;

    private static Map<String, List<Book>> booksByAuthor = new HashMap<>();
    private static List<Book> allBooks = new ArrayList<>();

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

    public static Book of(String title, String author, int yearOfPublishing, int price) {
        for (Book book : allBooks) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author) && book.getYearOfPublishing() == yearOfPublishing) {
                return book;
            }
        }
        Book newBook = new Book(title, author, yearOfPublishing, price);
        List<Book> authorBooks = booksByAuthor.getOrDefault(author.toLowerCase(), new ArrayList<>());
        authorBooks.add(newBook);
        booksByAuthor.put(author.toLowerCase(), authorBooks);

        allBooks.add(newBook);
        return newBook;

    }

    public static Book of(String title, int price) {
        if (allBooks.isEmpty()) {
            return null;
        }
        Book lastBook = allBooks.get(allBooks.size() - 1);
        List<Book> authorBooks = booksByAuthor.getOrDefault(lastBook.author.toLowerCase(), new ArrayList<>());
        booksByAuthor.put(lastBook.author, authorBooks);
        return of(title, lastBook.getAuthor(), lastBook.getYearOfPublishing(), price);
    }

    public static List<Book> getBooksByOwner(Person owner) {

        return owner.getBooks();
    }

    public static boolean removeBook(Book book) {
        if (book == null || !allBooks.contains(book)) {
            return false;
        }
        if (book.getOwner() != null) {
            book.getOwner().addMoney(book.getPrice());
            book.getOwner().getBooks().remove(book);

            book.owner = null;
        }
            allBooks.remove(book);
            return true;

    }

    public static List<Book> getBooksByAuthor(String author) {
        return booksByAuthor.getOrDefault(author.toLowerCase(), new ArrayList<>());
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
        this.setOwner(buyer);
        return true;

        }
        return true;

    }

}
