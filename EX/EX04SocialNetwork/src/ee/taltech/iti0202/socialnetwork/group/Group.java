package ee.taltech.iti0202.socialnetwork.group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {
    private String name;
    private User owner;
    private Set<User> users = new HashSet<>();
    private List<Message> messages = new ArrayList<>();

    /**
     *
     * @param name
     * @param owner
     */
    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        users.add(owner);

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
     * @param name
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     *
     * @return User
     */
    public User getOwner() {
        return owner;
    }

    /**
     *
     * @param user
     */
    public void addUser(User user) {
        users.add(user);

    }

    /**
     *
     * @return Set
     */
    public Set<User> getParticipants() {
        return users;
    }

    /**
     *
     * @param message
     */
    public void publishMessage(Message message) {
        if (users.contains(message.getAuthor())) {
            messages.add(message);
        }

    }

    /**
     *
     * @return List
     */
    public List<Message> getMessages() {
        return messages;
    }
}
