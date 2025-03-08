package com.example.ysoft.business.dtos.responses;

import com.example.ysoft.entities.User;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String nickName;
    private String password;

    public static UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId().toString())
                .nickName(user.getNickName())
                .password(user.getPassword())
                .build();
    }
}
