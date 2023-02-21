package ee.taltech.iti0202.bookshelf;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testGetTitle() {
        Book book1 = new Book("Kevade", "Ohak", 1900, 50);
        assertEquals("Kevade", book1.getTitle());
    }

    @Test
    void testGetAuthor() {
        Book book2 = new Book("Suvi", "Matu", 2004, 10);
        assertEquals("Matu", book2.getAuthor());
    }

    @Test
    void testGetYearOfPublishing() {
        Book book3 = new Book("kolmas", "Will", 2010, 100);
        assertEquals(2010, book3.getYearOfPublishing());
    }

    @Test
    void testGetOwner() {
        Book book4 = new Book("Sõda", "Lemps", 1950, 25);
        assertNull(book4.getOwner());
    }

    @Test
    void testSetOwner() {
        Book book5 = new Book("Aabits", "Joe", 1700, 40);
        Person owner = new Person("Ago", 100);
        book5.setOwner(owner);
        assertEquals(owner, book5.getOwner());
    }

    @Test
    void testGetPrice() {
        Book book6 = new Book("Ajalugu1", "Juhan", 1850, 30);
        assertEquals(30, book6.getPrice());
    }


    @Test
    void testBuyNullOwner() {
        Book suvakas = new Book("title", "author", 2022, 10);
        Person buyer66 = new Person("Alex", 100);
        assertTrue(suvakas.buy(buyer66));
        assertEquals(buyer66, suvakas.getOwner());
        assertEquals(90, buyer66.getMoney());
    }

    @Test
    void testBuyExistingOwner() {
        Book book = new Book("title", "author", 2022, 10);
        Person owner = new Person("name", 0);
        book.setOwner(owner);
        assertFalse(book.buy(owner));
        assertEquals(owner, book.getOwner());
        assertEquals(0, owner.getMoney());
    }

    @Test
    void testBuyInsufficientMoney() {
        Book book = new Book("title", "author", 2022, 10);
        Person buyer = new Person("name", 5);
        Person owner = new Person("name", 0);
        book.setOwner(owner);
        assertFalse(book.buy(buyer));
        assertEquals(owner, book.getOwner());
        assertEquals(5, buyer.getMoney());
    }

    @Test
    void testSellBookNullOwner() {
        Book book = new Book("title", "author", 2022, 10);
        Person seller = new Person("name", 0);
        assertFalse(seller.sellBook(book));
        assertNull(book.getOwner());
        assertEquals(0, seller.getMoney());
    }

    @Test
    void testOf() {
        Book book1 = Book.of("The Catcher in the Rye", "J.D. Salinger", 1951, 20);
        assertNotNull(book1);
        assertEquals("The Catcher in the Rye", book1.getTitle());
        assertEquals("J.D. Salinger", book1.getAuthor());
        assertEquals(1951, book1.getYearOfPublishing());
        assertEquals(20, book1.getPrice());

        // creating another book with the same title, author, and year
        Book book2 = Book.of("The Catcher in the Rye", "J.D. Salinger", 1951, 30);
        assertNotNull(book2);
        assertSame(book1, book2); // should return the same instance as the first book

        // creating a book with different year and author
        Book book3 = Book.of("The Catcher in the Rye", "John Doe", 2000, 40);
        assertNotNull(book3);
        assertNotSame(book1, book3); // should return a new instance of the book
    }

    @Test
    void testOfWithTitleAndPrice() {
        // creating a book with title and price, author and year should be set to null
        Book book1 = Book.of("The Great Gatsby", "Tammsaare", 2002,  25);;

        // creating another book with title and price, author and year should be set to previous book's values
        Book book2 = Book.of("The Great Gatsby second", 30);
        assertNotNull(book2);
        assertEquals("The Great Gatsby second", book2.getTitle());
        assertEquals("Tammsaare", book2.getAuthor());
        assertEquals(2002, book2.getYearOfPublishing());
        assertEquals(30, book2.getPrice());

        // creating a book with title that was not previously used, should return null
        Book book3 = Book.of("Moby Dick", 50);
        assertEquals("Tammsaare", book3.getAuthor());
    }

    @Test
    void testGetBooksByOwner() {
        Person person1 = new Person("John", 100);
        Person person2 = new Person("Jane", 200);
        Book book1 = Book.of("The Great Gatsby", "F. Scott Fitzgerald", 1925, 20);
        Book book2 = Book.of("To Kill a Mockingbird", "Harper Lee", 1960, 30);
        Book book3 = Book.of("1984", "George Orwell", 1949, 25);
        Book book4 = Book.of("Pride and Prejudice", "Jane Austen", 1813, 15);
        person1.buyBook(book1);
        person1.buyBook(book2);
        person2.buyBook(book3);
        person2.buyBook(book4);
        List<Book> booksByPerson1 = Book.getBooksByOwner(person1);
        List<Book> booksByPerson2 = Book.getBooksByOwner(person2);
        assertNotNull(booksByPerson1);
        assertEquals(2, booksByPerson1.size());
        assertTrue(booksByPerson1.contains(book1));
        assertTrue(booksByPerson1.contains(book2));
        assertNotNull(booksByPerson2);
        assertEquals(2, booksByPerson2.size());
        assertTrue(booksByPerson2.contains(book3));
    }

    @Test
    void of_createNewBook() {
        Book book = Book.of("The Lord of the Rings", "J.R.R. Tolkien", 1954, 25);
        assertNotNull(book);
        assertEquals("The Lord of the Rings", book.getTitle());
        assertEquals("J.R.R. Tolkien", book.getAuthor());
        assertEquals(1954, book.getYearOfPublishing());
        assertEquals(25, book.getPrice());
    }

    @Test
    void of_createExistingBook() {
        Book book1 = Book.of("The Lord of the Rings", "J.R.R. Tolkien", 1954, 25);
        Book book2 = Book.of("The Lord of the Rings", "J.R.R. Tolkien", 1954, 20);
        assertNotNull(book2);
        assertEquals(book1, book2);
    }

    @Test
    void of_createBookWithDefaults() {
        Book book1 = Book.of("The Lord of the Rings", "J.R.R. Tolkien", 1954, 25);
        Book book2 = Book.of("The Fellowship of the Ring", 20);
        assertNotNull(book2);
        assertEquals(book1.getAuthor(), book2.getAuthor());
        assertEquals(book1.getYearOfPublishing(), book2.getYearOfPublishing());
    }

    @Test
    void getBooksByOwner() {
        Person person1 = new Person("Alice", 100);
        Person person2 = new Person("Bob", 50);
        Book book1 = Book.of("The Lord of the Rings", "J.R.R. Tolkien", 1954, 25);
        Book book2 = Book.of("The Hobbit", "J.R.R. Tolkien", 1937, 15);
        Book book3 = Book.of("The Silmarillion", "J.R.R. Tolkien", 1977, 20);
        person1.buyBook(book1);
        person1.buyBook(book2);
        person2.buyBook(book3);
        List<Book> aliceBooks = Book.getBooksByOwner(person1);
        List<Book> bobBooks = Book.getBooksByOwner(person2);
        assertTrue(aliceBooks.contains(book1));
        assertTrue(aliceBooks.contains(book2));
        assertFalse(aliceBooks.contains(book3));
        assertFalse(bobBooks.contains(book1));
        assertFalse(bobBooks.contains(book2));
        assertTrue(bobBooks.contains(book3));
    }

    @Test
    void removeBook_removeExistingBook() {
        Book book = Book.of("The Lord of the Rings", "J.R.R. Tolkien", 1954, 25);
        Person mart = new Person("Alice", 100);
        mart.buyBook(book);
        assertTrue(Book.removeBook(book));
        assertEquals(100, mart.getMoney());
        assertFalse(mart.getBooks().contains(book));
        assertTrue(book.getBooksByAuthor("J.R.R. Tolkien").contains(book));
    }

    @Test
    void removeBook_removeNonexistentBook() {
        Book book = Book.of("The Lord of the Rings", "J.R.R. Tolkien", 1954, 25);
        assertFalse(Book.removeBook(null));
        assertFalse(Book.removeBook(new Book("The Hobbit", "J.R.R. Tolkien", 1937, 15)));
    }

    @Test
    void testOfWithTitleAndPriceOnly() {
        Book book1 = Book.of("Book 1", "Robin", 2004,  10);
        assertEquals("Book 1", book1.getTitle());
        assertEquals("Robin", book1.getAuthor());
        assertEquals(2004, book1.getYearOfPublishing());
        assertEquals(10, book1.getPrice());

        Book book2 = Book.of("Book 2", 20);
        assertEquals("Book 2", book2.getTitle());
        assertEquals("Robin", book2.getAuthor());
        assertEquals(2004, book2.getYearOfPublishing());
        assertEquals(20, book2.getPrice());
    }

    @Test
    void testOfWithAllFields() {
        Book book1 = Book.of("Book 1", "Author 1", 2000, 10);
        assertEquals("Book 1", book1.getTitle());
        assertEquals("Author 1", book1.getAuthor());
        assertEquals(2000, book1.getYearOfPublishing());
        assertEquals(10, book1.getPrice());

        Book book2 = Book.of("Book 2", "Author 2", 2020, 20);
        assertEquals("Book 2", book2.getTitle());
        assertEquals("Author 2", book2.getAuthor());
        assertEquals(2020, book2.getYearOfPublishing());
        assertEquals(20, book2.getPrice());
    }

    @Test
    void testOfDuplicateBook() {
        Book book1 = Book.of("Book 1", "Author 1", 2000, 10);
        Book book2 = Book.of("Book 1", "Author 1", 2000, 20);
        assertEquals(book1, book2);
        assertEquals(10, book1.getPrice());
        assertEquals(10, book2.getPrice());
    }

    @Test
    void testMultipleBooksBySameOwner() {
        Book raamat1 = Book.of("Raamat1", "Tammsaare", 1950, 50);
        Book raamat2 = Book.of("Raamat2", 40);
        assertEquals(5, Book.getBooksByAuthor("Tammsaare").size());

    }

    @Test
    private void assertEqualsBookList(List<Book> actual, List<Book> expected) {
        assertEquals(actual.size(), expected.size());
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
    }
    public void testRemoveBookAffectsOtherPlaces() throws Exception {
        Book b1 = Book.of("testRemoveAffects1", "testRemoveAffects Author1", 2001, 101);
        Book b2 = Book.of("testRemoveAffects2", "testRemoveAffects Author1", 2002, 101);
        Book b3 = Book.of("testRemoveAffects3", "testRemoveAffects Author1", 2003, 101);
        assertTrue(Book.removeBook(b1));
        // author search
        assertEqualsBookList(Book.getBooksByAuthor("testRemoveAffects Author1"), Arrays.asList(b2, b3));
        // Book.of
        Book b4 = Book.of("testRemoveAffects1", "testRemoveAffects Author1", 2001, 101);
        assertNotSame(b1, b4, "Got b1 (removed):" + b1 + " != b4 (new)" + b4);

        // remove everything
        assertTrue(Book.removeBook(b2));
        assertTrue(Book.removeBook(b3));
        assertTrue(Book.removeBook(b4));
        // we should have an empty list
        assertEquals(Book.getBooksByAuthor("testRemoveAffects Author1").size(), 0);



    }



}