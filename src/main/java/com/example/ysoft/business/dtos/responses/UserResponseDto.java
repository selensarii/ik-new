package com.example.ysoft.business.dtos.responses;

import com.example.ysoft.entities.User;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String id;
    private String nickName;
    private String password;

    public static UserResponseDto toResponse(User user){
        return UserResponseDto.builder()
                .id(user.getId().toString())
                .nickName(user.getNickName())
                .password(user.getPassword())
                .build();
    }
}
