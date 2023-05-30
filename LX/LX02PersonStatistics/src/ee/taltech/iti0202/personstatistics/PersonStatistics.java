package ee.taltech.iti0202.personstatistics;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;

/**
 * For calculating and finding statistical info based on persons.
 */
public class PersonStatistics {
    /**
     * Constructor which stores the given list.
     */
    public PersonStatistics(List<Person> persons) {
    }

    /**
     * Return the number of people in the list.
     */
    public long countPersons() {
        return -1L;
    }

    /**
     * Find the average height of the persons.
     */
    public OptionalDouble findAverageHeight() {
        return null;
    }

    /**
     * Return the person with the lowest age.
     */
    public Optional<Person> findYoungestPerson() {
        return null;
    }

    /**
     * Return the person with the highest age.
     */
    public Optional<Person> findOldestPerson() {
        return null;
    }

    /**
     * Return the longest last name.
     */
    public Optional<String> findLongestLastName() {
        return null;
    }

    /**
     * Return a list of nationalities.
     */
    public List<String> getNationalityData() {
        return null;
    }

    /**
     * Converts persons heights from m to cm.
     *
     * @return list of heights in cm
     */
    public List<Double> getHeightInCm() {
        return null;
    }

    /**
     * Return a sublist with the given size.
     */
    public List<Person> findSamples(int sampleSize) {
        return null;
    }

    /**
     * Find first person matching provided parameters criterias.
     *
     * @return first matching person
     */
    public Optional<Person> findSamplePerson(String nationality, Gender gender, int age) {
        return null;
    }

    /**
     * Find unique first names.
     */
    public Set<String> getDistinctFirstNames() {
        return null;
    }

    /**
     * Order persons from tallest to shortest.
     *
     * @return ordered list of persons
     */
    public List<Person> getReverseOrderedByHeight() {
        return null;
    }

    /**
     * Return list of people whose age is between ageFrom to ageTo (inclusive).
     */
    public List<Person> findBetweenAge(int ageFrom, int ageTo) {
        return null;
    }

    /**
     * Find persons whose first name first letter is same as his/her nationality first letter.
     *
     * @return list of matching persons
     */
    public List<Person> findSameLetterNameAndNationality() {
        return null;
    }

    /**
     * Create map where each occupation has list of persons who have that occupation.
     *
     * @return map of occupations with persons
     */
    public Map<String, List<Person>> mapOccupationToPersons() {
        return null;
    }
  
    public static void main(String[] args) {
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        System.out.println(statistics.countPersons());
    }

}