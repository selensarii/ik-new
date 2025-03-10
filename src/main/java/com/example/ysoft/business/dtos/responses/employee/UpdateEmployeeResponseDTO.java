package com.example.ysoft.business.dtos.responses.employee;

import com.example.ysoft.entities.Employee;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateEmployeeResponseDTO {
    private UUID id;
    private String fullName;
    private String position;
    private String identityNumber;
    private String salary;
    private UUID projectId;

    public static UpdateEmployeeResponseDTO fromEmployee(Employee employee) {
        return UpdateEmployeeResponseDTO.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .position(employee.getPosition())
                .identityNumber(employee.getIdentityNumber())
                .salary(employee.getSalary())
                .projectId(employee.getProject() != null ? employee.getProject().getId() : null)
                .build();
    }
}
