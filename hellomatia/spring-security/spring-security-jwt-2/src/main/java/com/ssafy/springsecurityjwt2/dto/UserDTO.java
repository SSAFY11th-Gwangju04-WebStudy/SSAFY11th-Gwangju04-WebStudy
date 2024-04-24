package com.ssafy.springsecurityjwt2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

    private String username;

    private String password;

    private String role;

}
