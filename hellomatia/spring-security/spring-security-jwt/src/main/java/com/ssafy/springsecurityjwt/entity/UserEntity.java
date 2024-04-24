package com.ssafy.springsecurityjwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column
    private String password;

    @Column
    private String role;
}
