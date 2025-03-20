package com.example.ysoft.business.dtos.requests;

import com.example.ysoft.entities.User;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserRequestDto {

    private String nickName;
    private String password;
    
}

