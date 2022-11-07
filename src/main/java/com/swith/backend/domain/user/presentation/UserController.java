package com.swith.backend.domain.user.presentation;

import com.swith.backend.domain.user.presentation.dto.response.UserResponse;
import com.swith.backend.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserResponse getUser() {
        return userService.getUser();
    }

}
