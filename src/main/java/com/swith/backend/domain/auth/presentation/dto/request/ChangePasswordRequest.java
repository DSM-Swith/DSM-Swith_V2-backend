package com.swith.backend.domain.auth.presentation.dto.request;

import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class ChangePasswordRequest {

    @Size(max = 35)
    private String phoneNumber;

    @Size(max = 60, min = 6)
    private String password;
}
