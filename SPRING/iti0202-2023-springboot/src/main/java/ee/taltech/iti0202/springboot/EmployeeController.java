package ee.taltech.iti0202.springboot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     *
     * @return something
     */
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    /**
     *
     * @param id
     * @return something
     */
    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    /**
     *
     * @param company
     * @return something
     */
    @GetMapping("/employees/company")
    public List<Employee> getEmployeesByCompany(@RequestParam String company) {
        return employeeService.findEmployeesByCompany(company);
    }

    /**
     *
     * @param employee
     * @return something
     */
    @PostMapping("/employee/add")
    public String addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    /**
     *
     * @param id
     * @return something
     */
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    /**
     *
     * @param id
     * @param updatedEmployee
     * @return something
     */
    @PutMapping("/employee/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(id, updatedEmployee);
    }
}
