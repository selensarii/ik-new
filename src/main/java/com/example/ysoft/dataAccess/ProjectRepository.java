package com.example.ysoft.dataAccess;

import com.example.ysoft.entities.Employee;
import com.example.ysoft.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.query.Param;


public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query("SELECT e.fullName FROM Employee e JOIN e.project p WHERE p.id = :projectId")
    List<String> findEmployeeNamesByProjectId(@Param("projectId") UUID projectId);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.project.id = :projectId")
    Long countEmployeesByProjectId(@Param("projectId") UUID projectId);

    @Query("SELECT e FROM Employee e WHERE e.project.id = :projectId")
    List<Employee> findEmployeesByProjectId(@Param("projectId") UUID projectId);



}
