package com.example.ysoft.business.dtos.responses;

import com.example.ysoft.entities.Employee;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private UUID id;
    private String fullName;
    private String position;
    private String identityNumber;
    private String salary;
    private UUID projectId;

    public static EmployeeResponseDto toResponse(Employee employee) {
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .position(employee.getPosition())
                .identityNumber(employee.getIdentityNumber())
                .salary(employee.getSalary())
                .projectId(employee.getProject() != null ? employee.getProject().getId() : null)
                .build();
    }
}
