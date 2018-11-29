package pl.robert.restapp.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.robert.restapp.employee.model.Employee;
import pl.robert.restapp.employee.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("http://localhost:4200")
public class EmployeeRestController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRestController(EmployeeRepository employeeRepo) {
        this.employeeRepository = employeeRepo;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Employee employee) {
        employeeRepository.save(employee);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> read() {
        List<Employee> employees = employeeRepository.findAllByOrderByIdAsc();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> readById(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @RequestBody Employee employee) {
        if (employeeRepository.findById(id).isPresent()) {
            Employee employeeToReplace = employeeRepository.findById(id).get();
            employeeToReplace.setFirstName(employee.getFirstName());
            employeeToReplace.setLastName(employee.getLastName());
            employeeToReplace.setAge(employee.getAge());

            employeeRepository.save(employeeToReplace);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping
    public ResponseEntity<Employee> delete() {
        employeeRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable long id) {
        employeeRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
























