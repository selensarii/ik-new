package com.example.ysoft.entities;

import com.example.ysoft.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employee")
public class Employee extends BaseEntity {  //

    private String fullName;
    private String position;
    private String identityNumber;
    private String salary;

    @ManyToOne
    @JoinColumn(name = "project_id") //
    private Project project;
}