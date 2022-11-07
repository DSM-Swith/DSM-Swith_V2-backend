package com.swith.backend.domain.auth.presentation;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.swith.backend.domain.auth.presentation.dto.request.ChangePasswordRequest;
import com.swith.backend.domain.auth.presentation.dto.request.CheckNumberRequest;
import com.swith.backend.domain.auth.presentation.dto.request.LoginRequest;
import com.swith.backend.domain.auth.presentation.dto.request.UserInfoRequest;
import com.swith.backend.domain.auth.presentation.dto.response.AccountIdResponse;
import com.swith.backend.domain.auth.presentation.dto.response.TokenResponse;
import com.swith.backend.domain.auth.service.AccountService;
import com.swith.backend.domain.auth.service.AuthService;
import com.swith.backend.domain.auth.service.SmsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class AuthController {

    private final AuthService authService;
    private final AccountService userService;
    private final SmsService smsService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid UserInfoRequest userInfoRequest) {
        authService.signUp(userInfoRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) { return authService.login(loginRequest); }

    @PutMapping("/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse refresh(@RequestHeader("Refresh") String token) {
        return authService.issuance(token);
    }

    @DeleteMapping("/logout")
    public void logout() {
        authService.logout();
    }

    @GetMapping("/check-id/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void checkIdOverlap(@PathVariable(value = "accountId") String accountId) {
        userService.isIdOverlap(accountId);
    }

    @GetMapping("/find/account-id/{phoneNumber}")
    public AccountIdResponse findAccountId(@PathVariable("phoneNumber") String phoneNumber) {
        return userService.findAccountId(phoneNumber);
    }

    @PutMapping("/change/password")
    public void changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
    }

    @PutMapping("/change/phone-number/{phoneNumber}")
    public void changePhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        userService.changePhoneNumber(phoneNumber);
    }

    @PostMapping("/sms/{phoneNumber}")
    public void sendMessage(@PathVariable("phoneNumber") String phoneNumber) {
        smsService.sendMessage(phoneNumber);
    }

    @PostMapping("/my/sms")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMyMessage() {
        smsService.sendMyMessage();
    }

    @GetMapping("/sms")
    @ResponseStatus(HttpStatus.OK)
    public void checkNumber(CheckNumberRequest request) {
        smsService.checkNumber(request);
    }

}
