package com.ssafy.springsecurityjwt.service;

import com.ssafy.springsecurityjwt.dto.CustomUserDetails;
import com.ssafy.springsecurityjwt.entity.UserEntity;
import com.ssafy.springsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            return new CustomUserDetails(user);
        }

        return null;
    }
}
