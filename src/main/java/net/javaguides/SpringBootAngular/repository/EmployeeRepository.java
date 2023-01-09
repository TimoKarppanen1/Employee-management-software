package net.javaguides.SpringBootAngular.repository;

import net.javaguides.SpringBootAngular.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
