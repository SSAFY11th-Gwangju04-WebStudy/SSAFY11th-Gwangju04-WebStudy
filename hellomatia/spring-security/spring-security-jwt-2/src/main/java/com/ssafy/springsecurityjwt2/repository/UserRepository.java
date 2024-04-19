package com.ssafy.springsecurityjwt2.repository;

import com.ssafy.springsecurityjwt2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
    Boolean existsByUsername(String username);

}
