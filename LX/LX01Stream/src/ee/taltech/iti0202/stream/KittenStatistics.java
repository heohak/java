package ee.taltech.iti0202.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class KittenStatistics {

    private List<Kitten> kittens;

    public void setKittens(List<Kitten> kittens) {
        this.kittens = kittens;
    }

    public OptionalDouble findKittensAverageAge() {
        return kittens
                .stream()
                .mapToDouble(Kitten::getAge)
                .average();
    }

    public Optional<Kitten> findOldestKitten() {
        return kittens
                .stream()
                .max(Comparator.comparingInt(Kitten::getAge));
    }

    public List<Kitten> findYoungestKittens() {
        return null;
    }

    public List<Kitten> findKittensAccordingToGender(Kitten.Gender gender) {
        return null;
    }

    public List<Kitten> findKittensBetweenAges(int minAge, int maxAge) {
        return null;
    }

    public Optional<Kitten> findFirstKittenWithGivenName(String givenName) {
        return Optional.empty();
    }

    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return null;
    }

    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return null;
    }
    
}