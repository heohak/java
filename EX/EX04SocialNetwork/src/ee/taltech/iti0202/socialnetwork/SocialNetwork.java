package ee.taltech.iti0202.socialnetwork;
import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Set;

public class SocialNetwork {
    private Set<Group> networkGroups = new HashSet<>();

    /**
     *
     * @param group
     */
    public void registerGroup(Group group) {
        networkGroups.add(group);

    }

    /**
     *
     * @return Set
     */
    public Set<Group> getGroups() {
        return networkGroups;
    }

    /**
     *
     * @param user
     * @return Feed
     */
    public Feed getFeedForUser(User user) {
        Set<Message> feed1 = new HashSet<>();

        for (Group group : networkGroups) {
            if (group.getParticipants().contains(user)) {
                feed1.addAll(group.getMessages());
            }
        }
        return new Feed(user, feed1);
    }
}
