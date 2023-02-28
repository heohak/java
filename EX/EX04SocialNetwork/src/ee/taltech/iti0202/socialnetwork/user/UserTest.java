package ee.taltech.iti0202.socialnetwork.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @Test
    void get_name() {
        User user1 = new User("Henry", 11);
        assertEquals("Henry", user1.getName());
        assertEquals(11, user1.getAge());

    }
    @Test
    void get_age_null() {
        User user2 = new User("Martin");
        assertNull(user2.getAge());
    }

}