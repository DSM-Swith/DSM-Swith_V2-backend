package com.swith.backend.domain.auth.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoRequest {

    @NotBlank
    @Size(max = 10, min = 2)
    private String userId;

    @NotBlank
    @Size(max = 20, min = 6)
    private String password;

    @Size(max = 20)
    private String nickname;

    @NotBlank
    @Size(max = 13)
    private String phoneNumber;

<<<<<<< HEAD
=======
    @NotBlank
>>>>>>> main
    private String introduce;
}
