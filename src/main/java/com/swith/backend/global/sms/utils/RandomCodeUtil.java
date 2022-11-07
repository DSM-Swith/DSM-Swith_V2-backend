package com.swith.backend.global.sms.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomCodeUtil {

    private RandomCodeUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateRandomNumber(Integer codeLength) {
        return RandomStringUtils.randomNumeric(codeLength);

    }

}

