package com.swith.backend.domain.refresh.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

    @Id
    private String userId;

    private String refreshTokenValue;

    @Builder
    public RefreshToken(String userId, String refreshTokenValue) {
        this.userId = userId;
        this.refreshTokenValue = refreshTokenValue;
    }

    public void update(String refreshTokenValue) {
        this.refreshTokenValue = refreshTokenValue;
    }
}