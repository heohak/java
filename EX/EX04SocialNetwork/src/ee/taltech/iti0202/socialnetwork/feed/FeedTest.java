package ee.taltech.iti0202.socialnetwork.feed;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FeedTest {
    @Test
    void get_feed_user_and_messages() {
        User user4 = new User("Ants");
        Message message1 = new Message("T1", "Jou", user4);
        Set<Message> set1 = new HashSet<>();
        set1.add(message1);
        Feed feed1 = new Feed(user4, set1);
        assertEquals(user4, feed1.getUser());
        assertEquals(set1, feed1.getMessages());
    }

}