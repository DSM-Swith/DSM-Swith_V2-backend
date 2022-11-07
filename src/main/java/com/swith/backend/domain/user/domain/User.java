package com.swith.backend.domain.user.domain;

import com.swith.backend.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseIdEntity {

    @Column(nullable = false, length = 10)
    private String nickname;

    @Column(nullable = false, length = 10, unique = true)
    private String userId;

    private String number;

    @Column(nullable = false, length = 30)
    private String introduce;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    private String path;

    @Builder
    public User(String nickname, String userId, String number, String introduce, String password, String phoneNumber) {
        this.nickname = nickname;
        this.userId = userId;
        this.number = number;
        this.introduce = introduce;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.path = null;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public User changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

}
