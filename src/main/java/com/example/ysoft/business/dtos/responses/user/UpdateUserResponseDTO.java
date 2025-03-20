package com.example.ysoft.business.dtos.responses.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponseDTO {

    private UUID id;
    private String nickName;
    private String password;
}
