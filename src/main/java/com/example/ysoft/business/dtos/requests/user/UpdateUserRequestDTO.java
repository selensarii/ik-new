package com.example.ysoft.business.dtos.requests.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserRequestDTO {

    private UUID id;
    private String nickName;
    private String password;
}
