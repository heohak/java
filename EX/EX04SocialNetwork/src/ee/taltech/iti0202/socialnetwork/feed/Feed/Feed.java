package ee.taltech.iti0202.socialnetwork.feed.Feed;

import ee.taltech.iti0202.socialnetwork.message.Message.Message;
import ee.taltech.iti0202.socialnetwork.user.User.User;

import java.util.Collections;
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
