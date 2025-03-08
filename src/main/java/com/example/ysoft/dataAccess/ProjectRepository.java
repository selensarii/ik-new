package com.example.ysoft.dataAccess;

import com.example.ysoft.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query("SELECT p FROM Project p JOIN p.employeeList e WHERE e.fullName = :employeeName")
    List<Project> findProjectsByEmployeeName(String employeeName);
}
