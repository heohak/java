package ee.taltech.iti0202.socialnetwork.SocialNetwork;
    import ee.taltech.iti0202.socialnetwork.feed.Feed;
    import ee.taltech.iti0202.socialnetwork.group.Group;
    import ee.taltech.iti0202.socialnetwork.message.Message;
    import ee.taltech.iti0202.socialnetwork.user.User;

    import java.util.*;

public class SocialNetwork {
        Set<Group> networkGroups = new HashSet<>();

        public void registerGroup(Group group) {
            networkGroups.add(group);

        }

        public Set<Group> getGroups() {
            return networkGroups;
        }

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