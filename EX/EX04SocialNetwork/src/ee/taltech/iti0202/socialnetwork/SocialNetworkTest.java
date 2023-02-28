package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkTest {

    @Test
    void add_group_get_group() {
        SocialNetwork sotsnet = new SocialNetwork();
        Set<Group> netigrupid = new HashSet<>();
        User user1 = new User("Joss");
        Group grupp1 = new Group("grupp1", user1);
        sotsnet.registerGroup(grupp1);
        netigrupid.add(grupp1);
        assertEquals(netigrupid, sotsnet.getGroups());
    }
    @Test
    void get_user_feed() {
        User user1 = new User("Joss");
        Message mess1 = new Message("A1", "B1",user1);
        Set<Message> sonumid = new HashSet<>();
        sonumid.add(mess1);
        Feed feed1 = new Feed(user1, sonumid);
        SocialNetwork sotsnet = new SocialNetwork();
        Set<Group> netigrupid = new HashSet<>();
        Group grupp1 = new Group("grupp1", user1);
        netigrupid.add(grupp1);
        grupp1.publishMessage(mess1);
        sotsnet.registerGroup(grupp1);
        netigrupid.add(grupp1);
        assertEquals(sonumid, sotsnet.getFeedForUser(user1));

    }


}