package ee.taltech.iti0202.socialnetwork.message;

import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    void get_title_message_author() {
        User user3 = new User("Alex");
        Message mess1 = new Message("Pealkiri1", "Tere Pyython", user3);
        assertEquals("Pealkiri1", mess1.getTitle());
        assertEquals("Tere Pyython", mess1.getMessage());
        assertEquals(user3, mess1.getAuthor());
    }

}