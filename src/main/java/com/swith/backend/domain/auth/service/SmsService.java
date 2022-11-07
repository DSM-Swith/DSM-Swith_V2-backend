package com.swith.backend.domain.auth.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.swith.backend.domain.auth.domain.UserAuthNumber;
import com.swith.backend.domain.auth.domain.repository.UserAuthNumberRepository;
import com.swith.backend.domain.auth.presentation.dto.request.CheckNumberRequest;
import com.swith.backend.domain.auth.service.util.AuthUtil;
import com.swith.backend.global.exception.IdConflictException;
import com.swith.backend.global.exception.PhoneNumberNotSmsException;
import com.swith.backend.global.sms.SmsMessageService;
import com.swith.backend.global.sms.utils.RandomCodeUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SmsService {

    private final UserAuthNumberRepository userAuthNumberRepository;

    private final AuthUtil authUtil;
    private final SmsMessageService smsMessageService;

    @Transactional
    public void sendMessage(String phoneNumber) {
        String number = RandomCodeUtil.generateRandomNumber(5);
        smsMessageService.sendMessage(phoneNumber, number);

        userAuthNumberRepository.save(UserAuthNumber.builder()
                .phoneNumber(phoneNumber)
                .number(number)
            .build());
    }

    // 비밀번호 변경, 아이디 찾기 등 이미 토큰이 있는 유저를 인증할때
    @Transactional
    public void sendMyMessage() {
        String phoneNumber = authUtil.getUser().getPhoneNumber();
        String number = RandomCodeUtil.generateRandomNumber(5);
        smsMessageService.sendMessage(phoneNumber, number);

        userAuthNumberRepository.save(UserAuthNumber.builder()
            .phoneNumber(phoneNumber)
            .number(number)
            .build());
    }

    public void checkNumber(CheckNumberRequest request) {
        UserAuthNumber userAuthNumber = userAuthNumberRepository.findById(request.getPhoneNumber())
            .orElseThrow(() -> PhoneNumberNotSmsException.EXCEPTION);

        if(!userAuthNumber.getNumber().equals(request.getNumber())) {
            throw IdConflictException.EXCEPTION;
        }
    }

}
