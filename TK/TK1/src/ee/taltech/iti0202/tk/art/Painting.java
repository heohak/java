package ee.taltech.iti0202.tk.art;

public class Painting {
    private String title;

    private String author;

    public Painting(String title) {
        this.title = title;
    }

    public Painting(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        if (author == null) {
            return "This is a painting named " + title + " and made by an unknown artist.";
        }
        else {
            return "This is a painting named " + title + " and made by " + author + ".";
        }
    }
}
