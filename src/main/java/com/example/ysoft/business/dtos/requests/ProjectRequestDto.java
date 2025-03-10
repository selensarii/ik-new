package com.example.ysoft.business.dtos.requests;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProjectRequestDto {
    private String name;
    private Long maxEmployee;
    private Long minEmployee;
    private Long totalEmployee;
}
