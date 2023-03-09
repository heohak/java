package ee.taltech.iti0202.stream;

public class Kitten {

    private Gender gender;
    private String name;
    private int age;
    public enum Gender { MALE, FEMALE }

    /**
     *
     * @param name
     * @param gender
     * @param age
     */
    public Kitten(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return Gender
     */
    public Gender getGender() {
        return gender;
    }
}
