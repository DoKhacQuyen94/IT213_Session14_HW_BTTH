package org.example.repository;

import org.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFullNameContainingIgnoreCase(String fullName);
    List<Employee> findByDepartmentNameIgnoreCase(String departmentName);
    long countByDepartmentNameIgnoreCase(String departmentName);
}
