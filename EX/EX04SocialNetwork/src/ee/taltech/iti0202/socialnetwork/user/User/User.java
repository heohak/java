package ee.taltech.iti0202.socialnetwork.user.User;
public class User {
    private String name;
    private Integer age;


    public User(String name) {
        this.name = name;

    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        if (age == null) {
            return null;
        } else {
            return age;
        }
    }
}