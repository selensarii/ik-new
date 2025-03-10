package com.example.ysoft.business.dtos.responses.employee;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeResponseDTO {
    private UUID id;
    private String fullName;
    private String position;
    private String identityNumber;
    private String salary;
    private UUID projectId;


}
