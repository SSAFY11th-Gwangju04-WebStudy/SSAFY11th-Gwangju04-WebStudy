package com.ssafy.springsecurityjwt2.service;

import com.ssafy.springsecurityjwt2.dto.CustomUserDetails;
import com.ssafy.springsecurityjwt2.entity.UserEntity;
import com.ssafy.springsecurityjwt2.repository.UserRepository;
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

        UserEntity data = userRepository.findByUsername(username);

        if (data == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(data);
    }
}
