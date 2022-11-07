package com.swith.backend.domain.auth.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    @Size(max = 10, min = 1)
    private String userId;

    @NotBlank
    @Size(max = 20, min = 6)
    private String password;

}
