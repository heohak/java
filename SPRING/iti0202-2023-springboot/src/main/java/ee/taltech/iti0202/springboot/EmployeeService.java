package ee.taltech.iti0202.springboot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public String addEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findByEmailIgnoreCase(employee.getEmail());
        if (existingEmployee.isPresent()) {
            return "This email is already in database or something went wrong!";
        }
        employeeRepository.save(employee);
        return "Employee added to database";
    }

    public String deleteEmployee(Long id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return "Employee deleted";
        } else {
            return "No matching ID found in database!";
        }
    }

    public String updateEmployee(Long id, Employee newEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (!existingEmployee.isPresent()) {
            return "No matching ID found in database!";
        }
        Employee employee = existingEmployee.get();
        if (!employee.getEmail().equalsIgnoreCase(newEmployee.getEmail())) {
            if (employeeRepository.findByEmailIgnoreCase(newEmployee.getEmail()).isPresent()) {
                return "Cannot overwrite employee data!";
            }
        }
        employee.setFirstName(newEmployee.getFirstName());
        employee.setLastName(newEmployee.getLastName());
        employee.setEmail(newEmployee.getEmail());
        employee.setCompany(newEmployee.getCompany());
        employeeRepository.save(employee);
        return "Employee data overwritten";
    }

    public List<Employee> findEmployeesByCompany(String company) {
        return employeeRepository.findAllByCompanyIgnoreCase(company);
    }
}
