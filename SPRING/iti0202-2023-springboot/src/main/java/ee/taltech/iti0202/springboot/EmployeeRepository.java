package ee.taltech.iti0202.springboot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     *
     * @param email
     * @return something
     */
    Optional<Employee> findByEmailIgnoreCase(String email);

    /**
     *
     * @param firstName
     * @return something
     */
    List<Employee> findAllByFirstNameIgnoreCase(String firstName);

    /**
     *
     * @param company
     * @return something
     */
    List<Employee> findAllByCompanyIgnoreCase(String company);
}
