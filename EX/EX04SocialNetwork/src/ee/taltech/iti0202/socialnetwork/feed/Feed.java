package ee.taltech.iti0202.socialnetwork.feed;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Set;

public class Feed {
    private User user;
    private Set<Message> feedMessages = new HashSet<>();

    public Feed(User user, Set<Message> feedMessages) {
        this.user = user;
        this.feedMessages = feedMessages;
    }

    public User getUser() {
        return user;
    }

    public Set<Message> getFeedMessages() {
        return feedMessages;
    }
}
