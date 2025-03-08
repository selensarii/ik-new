package com.example.ysoft.business.dtos.responses;

import com.example.ysoft.entities.Project;
import lombok.*;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private UUID id;
    private String name;
    private Long maxEmployee;
    private Long minEmployee;
    private Long totalEmployee;
}
