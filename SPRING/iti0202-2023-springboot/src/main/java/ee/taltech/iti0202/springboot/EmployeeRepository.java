package ee.taltech.iti0202.springboot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailIgnoreCase(String email);
    List<Employee> findAllByFirstNameIgnoreCase(String firstName);
    List<Employee> findAllByCompanyIgnoreCase(String company);
}