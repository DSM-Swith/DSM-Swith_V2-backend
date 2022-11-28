package com.swith.backend.domain.diary.domain;

import com.swith.backend.domain.diary.domain.type.Status;
import com.swith.backend.global.entity.BaseTimeEntity;
import com.swith.backend.global.exception.TokenUnauthorizedException;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseTimeEntity {

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public Diary(String title, String content, String path, Status status, Long userId) {
        this.title = title;
        this.content = content;
        this.path = path;
        this.status = status;
        this.userId = userId;
    }

    public void matchUserId(Long userId) {
        if(!this.userId.equals(userId)) throw TokenUnauthorizedException.EXCEPTION;
    }

    public void update(String title, String content, Status status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

}
