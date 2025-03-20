package com.example.ysoft.business.dtos.requests;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeRequestDto {
    private String fullName;
    private String position;
    private String identityNumber;
    private String salary;
    private UUID projectId;
}
