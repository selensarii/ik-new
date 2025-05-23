package com.example.ysoft.business.dtos.responses.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectResponseDTO {

    private UUID id;
    private String name;
    private Long maxEmployee;
    private Long minEmployee;
    private Long totalEmployee;



}
