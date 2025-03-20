package com.example.ysoft.business.dtos.responses;

import com.example.ysoft.entities.User;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    
    private String id;
    private String nickName;
    private String password;


}
