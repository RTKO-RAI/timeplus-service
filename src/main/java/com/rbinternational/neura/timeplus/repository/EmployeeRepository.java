package com.rbinternational.neura.timeplus.repository;

import com.rbinternational.neura.timeplus.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
