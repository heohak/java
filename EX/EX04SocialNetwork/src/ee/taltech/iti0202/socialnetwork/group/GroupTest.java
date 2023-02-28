package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {
    @Test
    void get_group_name_owner() {
        User user5 = new User("Ints");
        Group viimsikad = new Group("grupp1", user5);
        assertEquals("grupp1", viimsikad.getName());
        assertEquals(user5, viimsikad.getOwner());
        viimsikad.setName("uusnimi1");
        assertEquals("uusnimi1", viimsikad.getName());
    }
    @Test
    void add_user_and_get_users() {
        Set<User> liikmed = new HashSet<>();
        User omanik = new User("Helmut");
        User uusliige = new User("Ints");
        Group parmud = new Group("parmud", omanik);
        parmud.addUser(uusliige);
        liikmed.add(omanik);
        liikmed.add(uusliige);
        assertEquals(parmud.getParticipants(), liikmed);

    }
    @Test
    void publish_message() {
        User lambi = new User("Varro");
        Message mess1 = new Message("P1", "S1",lambi);
        List<Message> sonumid = new ArrayList<>();
        sonumid.add(mess1);
        User omanik = new User("Helmut");
        Group parmud = new Group("parmud", omanik);
        parmud.addUser(lambi);
        parmud.publishMessage(mess1);
        assertEquals(sonumid, parmud.getMessages());

    }

}