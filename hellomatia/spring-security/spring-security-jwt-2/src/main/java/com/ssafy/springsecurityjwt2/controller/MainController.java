package com.ssafy.springsecurityjwt2.controller;

import com.ssafy.springsecurityjwt2.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public ResponseEntity<UserDTO> mainP() {
        return ResponseEntity.internalServerError()
                .body(
                UserDTO.builder()
                        .username("ssafy")
                        .password("password")
                        .role("role")
                        .build()
        );
    }
}
