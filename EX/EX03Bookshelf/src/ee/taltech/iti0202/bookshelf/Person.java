package ee.taltech.iti0202.bookshelf;

public class Person {
    private String name;
    private int money;

    public Person(String name, int money) {
        this.name = name;
        this.money = money;

    }

    public int getMoney() {
        return this.money;
    }

    public void addMoney(int sum) {
        this.money = this.money + sum;
    }

    public void takeMoney(int sum) {
        this.money = this.money - sum;
    }

    public String getName() {
        return this.name;
    }

    public boolean buyBook(Book book) {
        if (book.getOwner() == null && this.getMoney() >= book.getPrice()) {
            book.setOwner(this);
            this.takeMoney(book.getPrice());
            return true;
        } else {
            return false;
        }



    }

    public boolean sellBook(Book book) {
        if (this == book.getOwner()) {
            this.addMoney(book.getPrice());
            book.setOwner(null);
            return true;
        } else {
            return false;
        }
    }
}