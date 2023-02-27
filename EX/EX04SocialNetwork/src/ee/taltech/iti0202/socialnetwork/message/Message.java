package ee.taltech.iti0202.socialnetwork.message;

import ee.taltech.iti0202.socialnetwork.user.User;

public class Message {
    private String title;
    private String message;
    private User author;

    /**
     *
     * @param title
     * @param message
     * @param author
     */
    public Message(String title, String message, User author) {
        this.title = title;
        this.message = message;
        this.author = author;
    }

    /**
     * This is something.
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return User
     */
    public User getAuthor() {
        return author;
    }
}
