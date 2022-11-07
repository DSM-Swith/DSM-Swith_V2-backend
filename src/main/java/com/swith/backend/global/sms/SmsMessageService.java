package com.swith.backend.global.sms;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Component
public class SmsMessageService {

    @Value("${sms.apikey}")
    private String apiKey;

    @Value("${sms.secret}")
    private String apiSecret;

    @Value("${sms.phoneNumber}")
    private String fromPhoneNumber;

    public void sendMessage(String toPhoneNumber, String randomNumber) {

        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", toPhoneNumber);
        params.put("from", fromPhoneNumber);
        params.put("type", "SMS");
        params.put("text", "[오늘의 나] 인증번호(" + randomNumber + ")입니다.");
        params.put("app_version", "test app 1.2");

        try {
            coolsms.send(params);
        } catch (CoolsmsException e) {
            e.getStackTrace();
        }
    }

}