package com.example.tenure_backend.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String job;

    @Builder
    public User(String id, String password, String job, String nickname) {
        this.id = id;
        this.password = password;
        this.job = job;
        this.nickname = nickname;
    }

}
