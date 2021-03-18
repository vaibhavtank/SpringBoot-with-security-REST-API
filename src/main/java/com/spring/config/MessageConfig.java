package com.spring.config;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MessageConfig {

    public static final String MSG_COMMON_MSG_0 = "msg.common.msg-0";

    public static final String MSG_CARTYPE_MSG_1 = "msg.cartype.msg-1";
    public static final String MSG_CARTYPE_MSG_2 = "msg.cartype.msg-2";
    public static final String MSG_CARTYPE_MSG_3 = "msg.cartype.msg-3";

    public static final String DEFAULT_MESSAGE = "DEFAULT MESSAGE";

    @Autowired
    private Environment environment;

    public String getMessage(String messageKey) {
        String message = environment.getRequiredProperty(messageKey);
        return StringUtils.isEmpty(message) ? DEFAULT_MESSAGE : message;
    }
    
    public String getMessage(String messageKey, Object...args) {
        String message = environment.getRequiredProperty(messageKey);
        String msgText = StringUtils.isEmpty(message) ? DEFAULT_MESSAGE : message;
        return MessageFormat.format(msgText, args);
    }

    public String getMessage(String messageKey,String otp,String operation,String hashKey) {
        String message = environment.getRequiredProperty(messageKey);
        message = message.replace("{0}",otp);
        message = message.replace("{1}",operation);
        message = message.replace("{2}",hashKey);
        return StringUtils.isEmpty(message) ? DEFAULT_MESSAGE : message;
    }
}