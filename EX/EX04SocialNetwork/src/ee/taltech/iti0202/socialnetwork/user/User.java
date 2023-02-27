package ee.taltech.iti0202.socialnetwork.user;
public class User {
    private String name;
    private Integer age;


    /**
     *
     * @param name
     */
    public User(String name) {
        this.name = name;

    }

    /**
     *
     * @param name
     * @param age
     */
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;

    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Integer getAge() {
        if (age == null) {
            return null;
        } else {
            return age;
        }
    }
}
