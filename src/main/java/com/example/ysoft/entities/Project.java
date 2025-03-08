package com.example.ysoft.entities;

import com.example.ysoft.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "project")
public class Project extends BaseEntity { //

    private String name;
    private Long maxEmployee;
    private Long minEmployee;
    private Long totalEmployee;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employeeList;
}
