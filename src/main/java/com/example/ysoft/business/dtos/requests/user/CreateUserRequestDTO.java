package com.example.ysoft.business.dtos.requests.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequestDTO {

    private String nickName;
    private String password;
}
