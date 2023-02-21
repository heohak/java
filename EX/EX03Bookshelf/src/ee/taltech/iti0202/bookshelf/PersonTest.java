package ee.taltech.iti0202.bookshelf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void testConstructorAndGetters() {
        Person person = new Person("Alice", 100);
        assertEquals("Alice", person.getName());
        assertEquals(100, person.getMoney());
    }

    @Test
    public void testAddMoney() {
        Person person = new Person("Bob", 50);
        person.addMoney(30);
        assertEquals(80, person.getMoney());
    }

    @Test
    public void testTakeMoney() {
        Person person = new Person("Charlie", 200);
        person.takeMoney(100);
        assertEquals(100, person.getMoney());
    }

    @Test
    public void testBuyBookWithEnoughMoney() {
        Person buyer = new Person("David", 100);
        Book book = new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, 20);
        assertTrue(buyer.buyBook(book));
        assertEquals(buyer, book.getOwner());
        assertEquals(80, buyer.getMoney());
    }

    @Test
    public void testBuyBookWithoutEnoughMoney() {
        Person buyer = new Person("Eve", 10);
        Book book = new Book("To Kill a Mockingbird", "Harper Lee", 1960, 15);
        assertFalse(buyer.buyBook(book));
        assertNull(book.getOwner());
        assertEquals(10, buyer.getMoney());
    }

    @Test
    public void testSellBook() {
        Person seller = new Person("Frank", 0);
        Person buyer = new Person("Grace", 100);
        Book book = new Book("1984", "George Orwell", 1949, 30);
        book.setOwner(seller);
        assertTrue(seller.sellBook(book));
        assertNull(book.getOwner());
        assertEquals(30, seller.getMoney());
    }

    @Test
    public void testSellBookWithoutOwner() {
        Person seller = new Person("Henry", 50);
        Book book = new Book("Pride and Prejudice", "Jane Austen", 1813, 25);
        assertFalse(seller.sellBook(book));
        assertNull(book.getOwner());
        assertEquals(50, seller.getMoney());
    }

    @Test
    public void testSellBookWithDifferentOwner() {
        Person seller = new Person("Isabella", 0);
        Person buyer1 = new Person("John", 100);
        Person buyer2 = new Person("Kate", 200);
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 50);
        book.setOwner(buyer1);
        assertFalse(seller.sellBook(book));
        assertEquals(buyer1, book.getOwner());
        assertEquals(0, seller.getMoney());
        assertEquals(100, buyer1.getMoney());
        assertEquals(200, buyer2.getMoney());
    }

    @Test
    void testGetBooksReturnsEmptyListIfPersonHasNoBooks() {
        Person person = new Person("John", 100);
        assertTrue(person.getBooks().isEmpty());
    }

    @Test
    void testGetBooksReturnsAllOwnedBooks() {
        Person person = new Person("John", 100);
        Book book1 = Book.of("Java 101", "John Doe", 2021, 20);
        Book book2 = Book.of("Python for Beginners", "John Doe", 2022, 15);
        person.buyBook(book1);
        person.buyBook(book2);
        assertTrue(person.getBooks().contains(book1));
        assertTrue(person.getBooks().contains(book2));
    }

    @Test
    void testBuyBookReturnsFalseIfPersonDoesNotHaveEnoughMoney() {
        Person person = new Person("John", 10);
        Book book = Book.of("Java 101", "John Doe", 2021, 20);
        assertFalse(person.buyBook(book));
        assertTrue(person.getBooks().isEmpty());
    }

    @Test
    void testBuyBookReturnsTrueIfPersonHasEnoughMoney() {
        Person person = new Person("John", 30);
        Book book = Book.of("Java 101", "John Doe", 2021, 20);
        assertEquals("Java 101", book.getTitle());
        assertFalse(person.buyBook(book));
        assertFalse(person.getBooks().contains(book));
        assertEquals(30, person.getMoney());
    }

    @Test
    void testSellBookReturnsFalseIfPersonDoesNotOwnTheBook() {
        Person person1 = new Person("John", 100);
        Person person2 = new Person("Jane", 100);
        Book book = Book.of("Java 101", "John Doe", 2021, 20);
        assertFalse(person1.sellBook(book));
        assertTrue(person1.getBooks().isEmpty());
        assertTrue(person2.getBooks().isEmpty());
        assertEquals(100, person1.getMoney());
        assertEquals(100, person2.getMoney());
    }


}