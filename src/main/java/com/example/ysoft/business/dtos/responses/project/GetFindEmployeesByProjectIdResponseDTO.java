package com.example.ysoft.business.dtos.responses.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetFindEmployeesByProjectIdResponseDTO {

    private String fullName;
    private String position;
    private String identityNumber;
    private String salary;
}
