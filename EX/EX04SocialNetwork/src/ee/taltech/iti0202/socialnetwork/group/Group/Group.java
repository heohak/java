package ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message.Message;
import ee.taltech.iti0202.socialnetwork.user.User.User;

import java.util.*;

public class Group {
    private String name;
    private User owner;
    private Set<User> users = new HashSet<>();
    private List<Message> messages = new ArrayList<>();

    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        users.add(owner);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public User getOwner() {
        return owner;
    }

    public void addUser(User user) {
        users.add(user);

    }

    public Set<User> getParticipants() {
        return users;
    }

    public void publishMessage(Message message) {
        if (users.contains(message.getAuthor())) {
            messages.add(message);
        }

    }

    public List<Message> getMessages() {
        return messages;
    }
}