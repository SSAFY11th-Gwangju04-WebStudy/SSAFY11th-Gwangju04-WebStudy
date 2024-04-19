package com.ssafy.springsecurityjwt2.service;

import com.ssafy.springsecurityjwt2.dto.UserDTO;
import com.ssafy.springsecurityjwt2.entity.UserEntity;
import com.ssafy.springsecurityjwt2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public void joinProcess(UserDTO userDTO) {
        UserEntity data = new UserEntity();
        data.setUsername(userDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
