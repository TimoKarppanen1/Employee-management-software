package net.javaguides.SpringBootAngular.controller;


import net.javaguides.SpringBootAngular.exception.ResourceNotFoundException;
import net.javaguides.SpringBootAngular.model.Employee;
import net.javaguides.SpringBootAngular.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 ""Blocked by CROS policy: No 'access-control-allow-Origin' header is privet on the requested resources""
- Ongelman korjaamiseksi käytetään @CrossOrigin - huomautusta.

 */

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    /* Haetaan kaikki henkilöt tietokannasta */

    @GetMapping("/employees")
    public List<Employee> getAllEmpoyees(){
        return employeeRepository.findAll();
    }

    /* Tallennetaan henkilöt tietokantaan */

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);   // tallentaa käyttäjän MySQL-tietokantaan.
    }

    /* Haetaan henkilö ID:n perusteella käyttämällä GET-metodia */

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesnt exist with id :" + id));
        return ResponseEntity.ok(employee);

    }

    /* Päivitetään employee ID:n perusteella */

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesnt exist with id :" + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok(updatedEmployee);

    }

    /* Poistetaan valittu henkilö ID:n perusteella */

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesnt exist with id :" + id));

        employeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);

        return ResponseEntity.ok(response);

    }

}
