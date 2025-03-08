package com.example.ysoft.business.dtos.requests;

import com.example.ysoft.entities.User;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserRequest {
    private String nickName;
    private String password;

    public User toDto(){
        return User.builder()
                .password(password)
                .nickName(nickName)
                .build();
    }
}
