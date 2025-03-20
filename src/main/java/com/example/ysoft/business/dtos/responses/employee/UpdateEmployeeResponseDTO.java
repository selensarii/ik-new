package com.example.ysoft.business.dtos.responses.employee;

import com.example.ysoft.entities.Employee;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeResponseDTO {

    private UUID id;
    private String fullName;
    private String position;
    private String identityNumber;
    private String salary;
    private UUID projectId;
}
