package com.example.ysoft.dataAccess;

import com.example.ysoft.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query("SELECT e.fullName FROM Employee e WHERE e.id = :id")
    String findEmployeeFullNameById(@Param("id") UUID id);



}
