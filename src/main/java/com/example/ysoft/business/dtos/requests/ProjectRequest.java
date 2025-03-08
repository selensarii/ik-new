package com.example.ysoft.business.dtos.requests;

import com.example.ysoft.entities.Project;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProjectRequest {
    private String name;
    private Long maxEmployee;
    private Long minEmployee;
    private Long totalEmployee;
}
