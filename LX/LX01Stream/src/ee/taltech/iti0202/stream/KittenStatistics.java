package ee.taltech.iti0202.stream;

import java.util.*;
import java.util.stream.Collectors;

public class KittenStatistics {

    private List<Kitten> kittens;

    /**
     *
     * @param kittens
     */
    public void setKittens(List<Kitten> kittens) {
        this.kittens = kittens;
    }

    /**
     *
     * @return OptionalDouble
     */
    public OptionalDouble findKittensAverageAge() {
        return kittens
                .stream()
                .mapToDouble(Kitten::getAge)
                .average();
    }

    /**
     *
     * @return Optional
     */
    public Optional<Kitten> findOldestKitten() {
        return kittens
                .stream()
                .max(Comparator.comparingInt(Kitten::getAge));
    }

    /**
     *
     * @return List
     */
    public List<Kitten> findYoungestKittens() {
        OptionalInt minAge = kittens
                .stream()
                .mapToInt(Kitten::getAge)
                .min();
        return kittens
                .stream()
                .filter(kitten -> kitten.getAge() == minAge.getAsInt())
                .collect(Collectors.toList());
    }

    /**
     *
     * @param gender
     * @return List
     */
    public List<Kitten> findKittensAccordingToGender(Kitten.Gender gender) {
        return kittens
                .stream()
                .filter(kitten -> kitten.getGender() == gender)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param minAge
     * @param maxAge
     * @return List
     */
    public List<Kitten> findKittensBetweenAges(int minAge, int maxAge) {
        return kittens
                .stream()
                .filter(kitten -> maxAge > kitten.getAge() && kitten.getAge() > minAge)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param givenName
     * @return Optional
     */
    public Optional<Kitten> findFirstKittenWithGivenName(String givenName) {
        return kittens
                .stream()
                .filter(kitten -> kitten.getName().equalsIgnoreCase(givenName))
                .findFirst();
    }

    /**
     *
     * @return List
     */
    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return kittens
                .stream()
                .sorted(Comparator.comparing(Kitten::getAge))
                .collect(Collectors.toList());
    }

    /**
     *
     * @return List
     */
    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return kittens
                .stream()
                .sorted(Comparator.comparing(Kitten::getAge).reversed())
                .collect(Collectors.toList());
    }
    
}
